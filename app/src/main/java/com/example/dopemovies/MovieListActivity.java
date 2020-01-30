package com.example.dopemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dopemovies.models.Movie;
import com.example.dopemovies.util.Testing;
import com.example.dopemovies.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private static final String TAG = "MovieListActivity";
    private MovieListViewModel mMovieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        subscribeObservers();

        findViewById(R.id.buttonTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
    }



    private void subscribeObservers(){
        mMovieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    Testing.printMovies(movies, "Movies test");

                }


            }
        });
    }

    private void searchMoviesApi(String query){
        mMovieListViewModel.searchMoviesApi(query);
    }

    private void testRetrofitRequest(){
        searchMoviesApi("Thor");
    }
}
