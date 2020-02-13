package com.blueman.ammusic.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LocalAudioTracks implements Parcelable {

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

    protected LocalAudioTracks(Parcel in) {
        aPath = in.readString();
        aName = in.readString();
        aAlbum = in.readString();
        aArtist = in.readString();
    }

    public static final Creator<LocalAudioTracks> CREATOR = new Creator<LocalAudioTracks>() {
        @Override
        public LocalAudioTracks createFromParcel(Parcel in) {
            return new LocalAudioTracks(in);
        }

        @Override
        public LocalAudioTracks[] newArray(int size) {
            return new LocalAudioTracks[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aPath);
        dest.writeString(aName);
        dest.writeString(aAlbum);
        dest.writeString(aArtist);
    }
}