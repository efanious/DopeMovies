package com.example.dopemovies.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dopemovies.models.Movie;

import java.util.List;

public class MovieApiClient {

    private static MovieApiClient instance;
    private MutableLiveData<List<Movie>> mMovies;

    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies(){
        return mMovies;
    }
}
