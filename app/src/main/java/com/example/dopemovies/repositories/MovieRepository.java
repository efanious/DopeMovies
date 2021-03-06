package com.example.dopemovies.repositories;

import androidx.lifecycle.LiveData;

import com.example.dopemovies.models.Movie;
import com.example.dopemovies.requests.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private MovieApiClient mMovieApiClient;

    public static MovieRepository getInstance(){
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository(){
        mMovieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return mMovieApiClient.getMovies()  ;
    }

    public LiveData<Movie> getMovie(){
        return mMovieApiClient.getMovie()  ;
    }

    public void searchMoviesApi(String query){
        mMovieApiClient.searchMoviesApi(query);
    }

    public void getMovieById(int movieId){
        mMovieApiClient.getMovieById(movieId);
    }

    public void popularMoviesApi(){
        mMovieApiClient.popularMoviesApi();
    }
}
