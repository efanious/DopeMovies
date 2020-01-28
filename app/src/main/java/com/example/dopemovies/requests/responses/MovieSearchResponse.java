package com.example.dopemovies.requests.responses;

import com.example.dopemovies.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "movies=" + movies +
                '}';
    }
}
