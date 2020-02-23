package com.blueman.ammusic.Fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blueman.ammusic.Adapters.LocalTracksAdapter;
import com.blueman.ammusic.Models.LocalAudioTracks;
import com.blueman.ammusic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class tab3 extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private  LocalTracksAdapter localTracksAdapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;
    private LocalTracksAdapter.OnLocalSongsListener localSongsListener;

    public tab3() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        recyclerView = view.findViewById(R.id.localRecyclerView);

        searchView = view.findViewById(R.id.search_view);
        List<LocalAudioTracks> tracks = getSongs();
        LocalTracksAdapter localTracksAdapter = new LocalTracksAdapter(getContext(), tracks, localSongsListener);
        recyclerView.setAdapter(localTracksAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);

        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                localTracksAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                localTracksAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }

    private    List<LocalAudioTracks> getSongs(){
        List<LocalAudioTracks> tempAudioList = new ArrayList<>();
        ContentResolver contentResolver = Objects.requireNonNull(getActivity()).getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor c = contentResolver.query(uri, null, null, null, null);

        if(c!=null && c.moveToFirst()){
            //get columns
            int titleColumn = c.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = c.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = c.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            int albumColumn = c.getColumnIndex
                    (MediaStore.Audio.Media.ALBUM);
            int path = c.getColumnIndex
                    (MediaStore.Audio.Media.DATA);
            //add songs to list
            do {

                LocalAudioTracks audioModel = new LocalAudioTracks();
                long thisId = c.getLong(idColumn);
                String thisTitle = c.getString(titleColumn);
                String thisArtist = c.getString(artistColumn);
                String thisAlbum = c.getString(albumColumn);
                String thisPath = c.getString(path);

                audioModel.setName(thisTitle);
                audioModel.setAlbum(thisAlbum);
                audioModel.setArtist(thisArtist);
                audioModel.setPath(thisPath);

                tempAudioList.add(audioModel);
            }
            while (c.moveToNext());
            c.close();
        }

        return  tempAudioList;
    }
//    private class GetUserSongs extends AsyncTask< Void, Integer, List<LocalAudioTracks>>{
//
//        @Override
//        protected List<LocalAudioTracks> doInBackground(Void... voids) {
//         return l
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(List<LocalAudioTracks> localAudioTracks) {
//            super.onPostExecute(localAudioTracks);
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if (context instanceof LocalTracksAdapter.OnLocalSongsListener) {
            localSongsListener = (LocalTracksAdapter.OnLocalSongsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
