package com.example.dopemovies.requests.responses;

import androidx.annotation.NonNull;

import com.example.dopemovies.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("object")
    @Expose()
    private Movie movie;

    public Movie getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
