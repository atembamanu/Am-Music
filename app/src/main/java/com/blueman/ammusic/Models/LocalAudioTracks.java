package com.blueman.ammusic.Models;

import java.io.Serializable;

public class LocalAudioTracks{

   private String aPath;
    private String aName;
    private String aAlbum;
    private String aArtist;

    public LocalAudioTracks(String aPath, String aName, String aAlbum, String aArtist) {
        this.aPath = aPath;
        this.aName = aName;
        this.aAlbum = aAlbum;
        this.aArtist = aArtist;
    }

    public LocalAudioTracks() {

    }

    public String getPath() {
        return aPath;
    }

    public void setPath(String aPath) {
        this.aPath = aPath;
    }

    public String getName() {
        return aName;
    }

    public void setName(String aName) {
        this.aName = aName;
    }

    public String getAlbum() {
        return aAlbum;
    }

    public void setAlbum(String aAlbum) {
        this.aAlbum = aAlbum;
    }

    public String gtaArtist() {
        return aArtist;
    }

    public void setArtist(String aArtist) {
        this.aArtist = aArtist;
    }
}