package com.blueman.ammusic.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blueman.ammusic.Adapters.LocalTracksAdapter;
import com.blueman.ammusic.Adapters.MusicTabsAdapter;
import com.blueman.ammusic.Fragments.tab3;
import com.blueman.ammusic.Models.LocalAudioTracks;
import com.blueman.ammusic.R;
import com.blueman.ammusic.Utils.LocalSharedPreferences;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicListActivity extends AppCompatActivity implements tab3.OnFragmentInteractionListener, LocalTracksAdapter.OnLocalSongsListener {

    //TODO Bind all views using butterknife
    private TabLayout tabLayout;
    private TabItem tabItem1, tabItem2, tabItem3;
    private MusicTabsAdapter pagerAdapter;
    public ViewPager viewPager;

    @BindView(R.id.songs_title) TextView songsTitle;
    @BindView(R.id.songs_artist_name) TextView songsArtist;

    @BindView(R.id.imageButton2) ImageButton like;
    @BindView(R.id.imageButton2new) ImageButton notlike;
    @BindView(R.id.button) ImageButton dislike;
    @BindView(R.id.buttontwo) ImageButton notdislike;

    @BindView(R.id.play_button) ImageButton play;
    @BindView(R.id.pause_button) ImageButton pause;
    @BindView(R.id.play_button_main) ImageButton play_main;
    @BindView(R.id.pause_button_main) ImageButton pause_main;

    @BindView(R.id.activity_main) SlidingUpPanelLayout mLayout;
    private Context mContext;
    @BindView(R.id.share_app) TextView share;
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 400;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private static final String TAG = "MusicListActivity";
    List<LocalAudioTracks> tracks;
    LocalSharedPreferences localSharedPreferences;
    private MediaPlayer mediaPlayer;


    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        externalStoragePermission();
        shareWithFriends();

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        localSharedPreferences = new LocalSharedPreferences();
        tracks = localSharedPreferences.getLocalSongs(getApplicationContext());



        tabLayout = findViewById(R.id.tabLayout);
        tabItem1 = findViewById(R.id.tab_item_1);
        tabItem2 = findViewById(R.id.tab_item_2);
        tabItem3 = findViewById(R.id.tab_item_3);
        viewPager = findViewById(R.id.viewPager);

        pagerAdapter = new MusicTabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pagerAdapter.notifyDataSetChanged();
                }else if (tab.getPosition() == 1){
                    pagerAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notlike.setVisibility(View.VISIBLE);
                Toast.makeText(MusicListActivity.this,"You Like the Song",Toast.LENGTH_SHORT).show();
                if (notdislike.getVisibility() == View.VISIBLE){
                    notdislike.setVisibility(View.GONE);
                }
            }
        });

        notlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notlike.setVisibility(View.GONE);
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notdislike.setVisibility(View.VISIBLE);
                Toast.makeText(MusicListActivity.this,"You DisLike the Song",Toast.LENGTH_SHORT).show();
                if (notlike.getVisibility() == View.VISIBLE){
                    notlike.setVisibility(View.GONE);
                }
            }
        });

        notdislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notdislike.setVisibility(View.GONE);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                Toast.makeText(MusicListActivity.this,"Song Is now Playing",Toast.LENGTH_SHORT).show();
                if (play_main.getVisibility() == View.VISIBLE){
                    play_main.setVisibility(View.GONE);
                    pause_main.setVisibility(View.VISIBLE);
                }

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                Toast.makeText(MusicListActivity.this,"Song is Pause",Toast.LENGTH_SHORT).show();
                if (pause_main.getVisibility() == View.VISIBLE){
                    pause_main.setVisibility(View.GONE);
                    play_main.setVisibility(View.VISIBLE);
                }
            }
        });

        play_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_main.setVisibility(View.GONE);
                pause_main.setVisibility(View.VISIBLE);
                Toast.makeText(MusicListActivity.this,"Song Is now Playing",Toast.LENGTH_SHORT).show();
                if (play.getVisibility() == View.VISIBLE){
                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                }
            }
        });

        pause_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause_main.setVisibility(View.GONE);
                play_main.setVisibility(View.VISIBLE);
                Toast.makeText(MusicListActivity.this,"Song is Pause",Toast.LENGTH_SHORT).show();
                if (pause.getVisibility() == View.VISIBLE){
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void externalStoragePermission(){
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void shareWithFriends() {
        share.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Still Negotiating, I'll reveal once ready!");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
    }
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hide();
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onLocalSongsListener(LocalAudioTracks localAudioTracks, String path,  int position) {
        songsTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        songsTitle.setText(localAudioTracks.getName());
        songsTitle.setSelected(true);
        songsTitle.setSingleLine(true);
        songsArtist.setText(localAudioTracks.gtaArtist());


        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
            Uri uri = Uri.parse(path);
            Log.d(TAG, "onLocalSongsListener: "+ uri.toString());
            mediaPlayer = MediaPlayer.create(MusicListActivity.this, uri);
            mediaPlayer.start();

    }
}
