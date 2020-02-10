package com.blueman.ammusic.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blueman.ammusic.Adapters.TracksCustomAdapter;
import com.blueman.ammusic.HttpRequest.MusixmatchApi;
import com.blueman.ammusic.HttpRequest.MusixmatchClient;
import com.blueman.ammusic.HttpResponse.TrackListResponse;
import com.blueman.ammusic.Models.TrackList;
import com.blueman.ammusic.R;
import com.blueman.ammusic.Utils.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class tab2 extends Fragment {
        private TracksCustomAdapter tracksCustomAdapter;
        private ArrayList<TrackList> data;
        private RecyclerView recyclerView;

    public tab2() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadJSON();
        return view;
    }

    private void loadJSON() {
        MusixmatchApi client = MusixmatchClient.getClient();
        Call<TrackListResponse> call = client.getTracks(Constants.API_KEY, 1);
        call.enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(Call<TrackListResponse> call, Response<TrackListResponse> response) {

                if(response.isSuccessful()){
                    if (response.body() != null) {
                        TrackListResponse trackListResponse = response.body();
                        Log.i("ResponseString", trackListResponse.toString());
                        data = new ArrayList<>(Arrays.asList(trackListResponse.getTracks()));
                        Log.d("MData", data.toString());
                        tracksCustomAdapter = new TracksCustomAdapter(getContext(),data);
                        recyclerView.setAdapter(tracksCustomAdapter);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                        Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Log.d("Not Successful", response.message());
                }
            }

            @Override
            public void onFailure(Call<TrackListResponse> call, Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
