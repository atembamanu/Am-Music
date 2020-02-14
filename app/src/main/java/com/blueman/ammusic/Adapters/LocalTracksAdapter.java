package com.blueman.ammusic.Adapters;

import android.app.Activity;
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
import com.blueman.ammusic.Utils.LocalSharedPreferences;

import org.parceler.Parcels;

import java.util.List;

public class LocalTracksAdapter extends RecyclerView.Adapter<LocalTracksAdapter.LocalTrackViewHolder> {

    private List<LocalAudioTracks> localAudioTracks;
    private Context mContext;
    private OnLocalSongsListener listener;
    private LayoutInflater inflater;
    Activity activity;
    LocalSharedPreferences localSharedPreferences;

    public interface OnLocalSongsListener {
        void onLocalSongsListener(LocalAudioTracks localAudioTracks, String path, int position);
    }

    public LocalTracksAdapter(Context mContext, List<LocalAudioTracks> localAudioTracks, OnLocalSongsListener listener) {
        this.localAudioTracks = localAudioTracks;
        this.mContext = mContext;
        this.listener = listener;
        inflater = LayoutInflater.from(mContext);
        localSharedPreferences = new LocalSharedPreferences();
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

        localSharedPreferences.addSong(mContext, localAudioTracks.get(position));
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
            listener.onLocalSongsListener(localAudioTracks.get(itemPosition), localAudioTracks.get(itemPosition).getPath(),  itemPosition);

            localSharedPreferences.addSong(mContext, localAudioTracks.get(itemPosition));


        }
    }
}
