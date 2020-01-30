package com.example.dopemovies.requests;

import com.example.dopemovies.requests.responses.MovieResponse;
import com.example.dopemovies.requests.responses.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //SEARCH
    @GET("3/search/movie?")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query

    );


    //GET MOVIE REQUEST
    @GET("3/movie/")
    Call<MovieResponse> getMovie(
            @Query("api_key") String key,
            @Query("movie_id") int movie_id

    );

}
