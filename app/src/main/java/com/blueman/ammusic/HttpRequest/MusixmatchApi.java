package com.blueman.ammusic.HttpRequest;

import com.blueman.ammusic.HttpResponse.TrackListResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusixmatchApi {

    @GET("chart.tracks.get")
    Call<TrackListResponse> getTracks(
            @Query("apikey") String apikey,
            @Query("f_has_lyrics") int value
    );
}
