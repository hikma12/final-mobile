package com.example.h071211006_finalmobile.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movies {
    @SerializedName("results")

    private List<DataMoviesResponse> dataMoviesResponseList;

    public List<DataMoviesResponse> getData() {
        return dataMoviesResponseList;
    }

}
