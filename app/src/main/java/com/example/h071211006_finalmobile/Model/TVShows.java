package com.example.h071211006_finalmobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShows {
    @SerializedName("results")

    private List<DataTVShowsResponse> dataTVShowsResponseList;

    public List<DataTVShowsResponse> getData() {
        return dataTVShowsResponseList;
    }
}
