package com.example.h071211006_finalmobile.API;

import com.example.h071211006_finalmobile.Model.DataMoviesResponse;
import com.example.h071211006_finalmobile.Model.DataTVShowsResponse;
import com.example.h071211006_finalmobile.Model.Movies;
import com.example.h071211006_finalmobile.Model.TVShows;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular?/")
    Call<Movies> getMovies(@Query("api_key") String apikey);

    @GET("tv/popular?/")
    Call<TVShows> getTVShows(@Query("api_key") String apikey);

}
