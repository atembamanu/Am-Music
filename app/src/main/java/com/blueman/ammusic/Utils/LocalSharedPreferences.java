package com.blueman.ammusic.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.blueman.ammusic.Models.LocalAudioTracks;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalSharedPreferences {
    public static final String PREFS_NAME = "local_MUSIC";
    public static final String SONGS = "Local_Music_List";
    Context context;
    public LocalSharedPreferences() {
        super();
    }

    //Methods used for  maintaining  Local music collections;

    public void saveLocalSongs(Context context, List<LocalAudioTracks> localAudioTracks) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(localAudioTracks);

        editor.putString(SONGS, jsonFavorites);

        editor.commit();
    }

    public void addSong(Context context, LocalAudioTracks localTrack) {
        List<LocalAudioTracks> localAudioTracks = getLocalSongs(context);
        if (localAudioTracks == null)
            localAudioTracks = new ArrayList<LocalAudioTracks>();
        localAudioTracks.add(localTrack);
        saveLocalSongs(context, localAudioTracks);
    }

    public void removeSongs(Context context, LocalAudioTracks product) {
        ArrayList<LocalAudioTracks> localSongs = getLocalSongs(context);
        if (localSongs != null) {
            localSongs.remove(product);
            saveLocalSongs(context, localSongs);
        }
    }

    public ArrayList<LocalAudioTracks> getLocalSongs(Context context) {
        SharedPreferences settings;
        List<LocalAudioTracks> localAudioTracks;


        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (settings.contains(SONGS)){
            String jsonLocalSongs = settings.getString(SONGS, null);
            Gson gson = new Gson();
            LocalAudioTracks[] mlocalSongs = gson.fromJson(jsonLocalSongs, LocalAudioTracks[].class);

            localAudioTracks = Arrays.asList(mlocalSongs);
            localAudioTracks = new ArrayList<LocalAudioTracks>(localAudioTracks);

        }else {
            return  null;

        }
        return (ArrayList<LocalAudioTracks>) localAudioTracks;

    }


}
