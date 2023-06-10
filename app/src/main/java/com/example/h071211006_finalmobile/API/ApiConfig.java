package com.example.h071211006_finalmobile.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "a48f2fbf2e8718cac04cd4530fdc05e6";

    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
