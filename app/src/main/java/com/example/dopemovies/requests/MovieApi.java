package com.example.dopemovies.requests;

import com.example.dopemovies.requests.responses.MovieResponse;
import com.example.dopemovies.requests.responses.MovieSearchResponse;
import com.example.dopemovies.requests.responses.PopularMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //SEARCH correct**
//    Example
//    https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false
    @GET("3/search/movie?")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query

    );

//    Get Popular movies
//    Example:
//    https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    @GET("3/movie/popular?")
    Call<PopularMoviesResponse> popularMovies(
            @Query("api_key") String key
    );


    //GET MOVIE REQUEST
    @GET("3/movie/")
    Call<MovieResponse> getMovie(
            @Query("api_key") String key,
            @Query("movie_id") int movie_id

    );

}
