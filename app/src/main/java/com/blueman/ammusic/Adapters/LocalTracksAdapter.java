package com.blueman.ammusic.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blueman.ammusic.Activities.MusicListActivity;
import com.blueman.ammusic.Models.LocalAudioTracks;
import com.blueman.ammusic.R;

import org.parceler.Parcels;

import java.util.List;

public class LocalTracksAdapter extends RecyclerView.Adapter<LocalTracksAdapter.LocalTrackViewHolder> {

    private List<LocalAudioTracks> localAudioTracks;
    private Context mContext;
    private LayoutInflater inflater;

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "localSongs";

    public LocalTracksAdapter(Context mContext, List<LocalAudioTracks> localAudioTracks) {
        this.localAudioTracks = localAudioTracks;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public LocalTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.local_list, parent, false);
        return new LocalTrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalTrackViewHolder holder, int position) {

        holder.localSongName.setText(localAudioTracks.get(position).getName());
        holder.localArtist.setText(localAudioTracks.get(position).gtaArtist());
    }

    @Override
    public int getItemCount() {
        return localAudioTracks.size();
    }

    public class LocalTrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       TextView localSongName, localArtist;
        public LocalTrackViewHolder(@NonNull View itemView) {

            super(itemView);

            localSongName = itemView.findViewById(R.id.local_song_name);
            localArtist = itemView.findViewById(R.id.local_song_artist);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int itemPosition = getLayoutPosition();
            sharedPreferences = mContext.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();


        }
    }
}
