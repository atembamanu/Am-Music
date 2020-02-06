package com.blueman.ammusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blueman.ammusic.Activities.MusicListActivity;
import com.blueman.ammusic.Activities.MusicPreferenceActivity;
import com.blueman.ammusic.Adapters.SliderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.slideview_pager) ViewPager mSlideViewPager;
    @BindView(R.id.dotsLayout) LinearLayout mDotLayout;

    @BindView(R.id.backBtn) Button mBackBtn;
    @BindView(R.id.nextBtn) Button mNextBtn;

    private TextView[] mDots;
    private int mCurrentPage;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sliderAdapter = new SliderAdapter(this);
        ButterKnife.bind(this);


        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        //OnclickListeners for Buttons
        mNextBtn.setOnClickListener(v -> mSlideViewPager.setCurrentItem(mCurrentPage+1));

        mBackBtn.setOnClickListener(v -> mSlideViewPager.setCurrentItem(mCurrentPage - 1));
    }


    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparent));
            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            mCurrentPage =position;

            if(position == 0){

                mBackBtn.setEnabled(false);
                mNextBtn.setEnabled(true);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");
            }else if(position == mDots.length-1){

                mBackBtn.setEnabled(true);
                mNextBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");
                mNextBtn.setOnClickListener(v -> {
                    Intent mPreferenceIntent = new Intent(MainActivity.this, MusicListActivity.class);
                    startActivity(mPreferenceIntent);
                });
            }else {

                mBackBtn.setEnabled(true);
                mNextBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.VISIBLE);


                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
