package com.example.dopemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dopemovies.adapters.MovieRecyclerAdapter;
import com.example.dopemovies.adapters.OnMovieListener;
import com.example.dopemovies.models.Movie;
import com.example.dopemovies.viewmodels.MovieListViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnMovieListener {

    private MovieListViewModel mMovieListViewModel;
    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = findViewById(R.id.home_list);

        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        initRecyclerView();
        subscribeObservers();

        mMovieListViewModel.popularMoviesApi();
    }


    private void subscribeObservers(){
        mMovieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    mMovieRecyclerAdapter.setMovies(movies);
                }


            }
        });
    }

    private void initRecyclerView(){

        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this);
        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

    }

    @Override
    public void onMovieClick(int position) {

    }
}
