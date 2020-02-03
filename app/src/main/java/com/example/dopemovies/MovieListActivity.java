package com.example.dopemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dopemovies.adapters.MovieRecyclerAdapter;
import com.example.dopemovies.adapters.OnMovieListener;
import com.example.dopemovies.models.Movie;
import com.example.dopemovies.util.Testing;
import com.example.dopemovies.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private static final String TAG = "MovieListActivity";
    private MovieListViewModel mMovieListViewModel;
    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mRecyclerView = findViewById(R.id.movie_list);


        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        testRetrofitRequest();


    }



    private void subscribeObservers(){
        mMovieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    Testing.printMovies(movies, "Movies test");
                    mMovieRecyclerAdapter.setMovies(movies);
                }


            }
        });
    }

    private void initRecyclerView(){
        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this);
        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchMoviesApi(String query){
        mMovieListViewModel.searchMoviesApi(query);
    }

    private void testRetrofitRequest(){
        searchMoviesApi("Thor");
    }

    @Override
    public void onMovieClick(int position) {

    }
}
