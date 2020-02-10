package com.blueman.ammusic.HttpResponse;

import com.blueman.ammusic.Models.Body;
import com.blueman.ammusic.Models.Track;
import com.blueman.ammusic.Models.TrackList;
import com.blueman.ammusic.Models.TrackModel;

import java.util.List;

public class TrackListResponse {
    private List<Track> tracks;

    public TrackListResponse(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getAllTracks() {
        return tracks;
    }
}
