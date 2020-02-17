package com.blueman.ammusic.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blueman.ammusic.Activities.MusicListActivity;
import com.blueman.ammusic.Models.LocalAudioTracks;
import com.blueman.ammusic.R;
import com.blueman.ammusic.Utils.LocalSharedPreferences;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class LocalTracksAdapter extends RecyclerView.Adapter<LocalTracksAdapter.LocalTrackViewHolder> implements Filterable {

    private List<LocalAudioTracks> localAudioTracks;
    private List<LocalAudioTracks> filteredTracks;
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
        this.filteredTracks = localAudioTracks;
    }

    @NonNull
    @Override
    public LocalTrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.local_list, parent, false);
        return new LocalTrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalTrackViewHolder holder, int position) {

        holder.localSongName.setText(filteredTracks.get(position).getName());
        holder.localArtist.setText(filteredTracks.get(position).gtaArtist());

        localSharedPreferences.addSong(mContext, localAudioTracks.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredTracks.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charSequenceString = constraint.toString();
                if (charSequenceString.isEmpty()) {
                    filteredTracks = localAudioTracks;
                } else {
                    List<LocalAudioTracks> filteredList = new ArrayList<>();
                    for (LocalAudioTracks tracks : localAudioTracks) {
                        if (tracks.getName().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filteredList.add(tracks);
                        }
                        filteredTracks = filteredList;
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filteredTracks;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredTracks = (List<LocalAudioTracks>) results.values;
                notifyDataSetChanged();
            }
        };
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
