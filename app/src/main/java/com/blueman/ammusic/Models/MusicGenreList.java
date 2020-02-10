
package com.blueman.ammusic.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicGenreList implements Serializable
{

    @SerializedName("music_genre")
    @Expose
    private MusicGenre musicGenre;
    private final static long serialVersionUID = -7387261883403209324L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MusicGenreList() {
    }

    /**
     * 
     * @param musicGenre
     */
    public MusicGenreList(MusicGenre musicGenre) {
        super();
        this.musicGenre = musicGenre;
    }

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }

}
