package com.example.dopemovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.dopemovies.models.Movie;
import com.example.dopemovies.repositories.MovieRepository;

public class MovieViewModel extends ViewModel {

    private MovieRepository mMovieRepository;

    public MovieViewModel() {
        mMovieRepository = MovieRepository.getInstance();
    }

    public LiveData<Movie> getMovie(){
        return mMovieRepository.getMovie();
    }

    public void getMoviesById(int id){
        mMovieRepository.getMovieById(id);
    }

}
