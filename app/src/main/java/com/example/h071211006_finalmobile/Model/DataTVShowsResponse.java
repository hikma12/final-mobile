package com.example.h071211006_finalmobile.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DataTVShowsResponse implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("overview")
    private String overview;
    @SerializedName("name")
    private String name;

    public static final Creator<DataTVShowsResponse> CREATOR = new Creator<DataTVShowsResponse>() {
        @Override
        public DataTVShowsResponse createFromParcel(Parcel in) {
            return new DataTVShowsResponse(in);
        }

        @Override
        public DataTVShowsResponse[] newArray(int size) {
            return new DataTVShowsResponse[size];
        }
    };

    public DataTVShowsResponse(String valueOf, String title, String releaseDate, String posterPath, String synopsis, String backdropPath, String valueOf1) {
    }

    public String getId() {
        return id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataTVShowsResponse(Parcel in) {
        id = in.readString();
        backdropPath = in.readString();
        firstAirDate = in.readString();
        posterPath = in.readString();
        voteAverage = in.readString();
        overview = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(backdropPath);
        parcel.writeString(firstAirDate);
        parcel.writeString(posterPath);
        parcel.writeString(voteAverage);
        parcel.writeString(overview);
        parcel.writeString(name);
    }
}