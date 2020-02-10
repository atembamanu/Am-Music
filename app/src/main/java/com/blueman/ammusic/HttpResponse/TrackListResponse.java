package com.blueman.ammusic.HttpResponse;

import com.blueman.ammusic.Models.TrackList;

public class TrackListResponse {
    private TrackList[] tracks;

    public TrackListResponse(TrackList[] tracks) {
        this.tracks = tracks;
    }

    public TrackList[] getTracks(){
        return tracks;
    }
}
