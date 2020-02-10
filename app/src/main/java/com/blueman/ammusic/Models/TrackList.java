
package com.blueman.ammusic.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackList implements Serializable
{

    @SerializedName("track")
    @Expose
    private Track track;
    private final static long serialVersionUID = 2506416389437418925L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TrackList() {
    }

    /**
     *
     * @param track
     */
    public TrackList(Track track) {
        super();
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}
