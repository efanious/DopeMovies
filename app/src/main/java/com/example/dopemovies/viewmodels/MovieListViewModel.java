package com.example.dopemovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dopemovies.models.Movie;
import com.example.dopemovies.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    MovieRepository mMovieRepository;

    public MovieListViewModel() {
        mMovieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return mMovieRepository.getMovies();
    }

    public void  searchMoviesApi(String query){
        mMovieRepository.searchMoviesApi(query);
    }
}
