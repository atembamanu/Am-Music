
package com.blueman.ammusic.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicGenre implements Serializable
{

    @SerializedName("music_genre_id")
    @Expose
    private Integer musicGenreId;
    @SerializedName("music_genre_parent_id")
    @Expose
    private Integer musicGenreParentId;
    @SerializedName("music_genre_name")
    @Expose
    private String musicGenreName;
    @SerializedName("music_genre_name_extended")
    @Expose
    private String musicGenreNameExtended;
    @SerializedName("music_genre_vanity")
    @Expose
    private String musicGenreVanity;
    private final static long serialVersionUID = 2875317572631325244L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MusicGenre() {
    }

    /**
     * 
     * @param musicGenreVanity
     * @param musicGenreName
     * @param musicGenreParentId
     * @param musicGenreId
     * @param musicGenreNameExtended
     */
    public MusicGenre(Integer musicGenreId, Integer musicGenreParentId, String musicGenreName, String musicGenreNameExtended, String musicGenreVanity) {
        super();
        this.musicGenreId = musicGenreId;
        this.musicGenreParentId = musicGenreParentId;
        this.musicGenreName = musicGenreName;
        this.musicGenreNameExtended = musicGenreNameExtended;
        this.musicGenreVanity = musicGenreVanity;
    }

    public Integer getMusicGenreId() {
        return musicGenreId;
    }

    public void setMusicGenreId(Integer musicGenreId) {
        this.musicGenreId = musicGenreId;
    }

    public Integer getMusicGenreParentId() {
        return musicGenreParentId;
    }

    public void setMusicGenreParentId(Integer musicGenreParentId) {
        this.musicGenreParentId = musicGenreParentId;
    }

    public String getMusicGenreName() {
        return musicGenreName;
    }

    public void setMusicGenreName(String musicGenreName) {
        this.musicGenreName = musicGenreName;
    }

    public String getMusicGenreNameExtended() {
        return musicGenreNameExtended;
    }

    public void setMusicGenreNameExtended(String musicGenreNameExtended) {
        this.musicGenreNameExtended = musicGenreNameExtended;
    }

    public String getMusicGenreVanity() {
        return musicGenreVanity;
    }

    public void setMusicGenreVanity(String musicGenreVanity) {
        this.musicGenreVanity = musicGenreVanity;
    }

}
