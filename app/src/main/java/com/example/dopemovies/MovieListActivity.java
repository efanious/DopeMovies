package com.example.dopemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.dopemovies.models.Movie;
import com.example.dopemovies.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel mMovieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        subscribeObservers();


    }



    private void subscribeObservers(){
        mMovieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

            }
        });
    }
}
