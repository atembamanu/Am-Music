package com.blueman.ammusic.Fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blueman.ammusic.Activities.MusicListActivity;
import com.blueman.ammusic.Adapters.LocalTracksAdapter;
import com.blueman.ammusic.Models.LocalAudioTracks;
import com.blueman.ammusic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab3 extends Fragment {

    private RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context mContext;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LocalTracksAdapter.OnLocalSongsListener localSongsListener;

    public tab3() {
    }

    // TODO: Rename and change types and number of parameters
    public static tab3 newInstance(String param1, String param2) {
        tab3 fragment = new tab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<LocalAudioTracks> musicFound= getLocalMusic();
        LocalTracksAdapter localTracksAdapter = new LocalTracksAdapter(getContext(), musicFound, localSongsListener);
        recyclerView.setAdapter(localTracksAdapter);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

        return view;
    }

    private List<LocalAudioTracks> getLocalMusic() {
        List<LocalAudioTracks> tempAudioList = new ArrayList<>();
        ContentResolver contentResolver = Objects.requireNonNull(getActivity()).getContentResolver();
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
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
        }
        return  tempAudioList;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
    //TODO check out this link http://developer.android.com/training/basics/fragments/communicating.html


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
