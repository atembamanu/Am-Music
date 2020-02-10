package com.blueman.ammusic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blueman.ammusic.Models.TrackList;
import com.blueman.ammusic.R;

import java.util.ArrayList;

public class TracksCustomAdapter extends RecyclerView.Adapter<TracksCustomAdapter.TrackViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<TrackList> trackList;

    public TracksCustomAdapter(Context context, ArrayList<TrackList> trackList) {
        inflater = LayoutInflater.from(context);
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.online_song_list, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {

        holder.songName.setText(trackList.get(position).getTrack().getArtistName());
        holder.songArtist.setText(trackList.get(position).getTrack().getArtistName());
        holder.releaseDate.setText(trackList.get(position).getTrack().getUpdatedTime());

    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder{
        TextView songName, songArtist, releaseDate;
        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);

            songName =  itemView.findViewById(R.id.song_name);
            songArtist = itemView.findViewById(R.id.song_artist);
            releaseDate = itemView.findViewById(R.id.released_date);
        }
    }
}
