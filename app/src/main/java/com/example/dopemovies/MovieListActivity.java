package com.example.dopemovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mRecyclerView = findViewById(R.id.movie_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);



        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        initRecyclerView();
        subscribeObservers();
//        testRetrofitRequest();
        initSearchView();


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
//        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this);
//        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//

        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this);
        mRecyclerView.setAdapter(mMovieRecyclerAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

    }

//    private void searchMoviesApi(String query){
//        mMovieListViewModel.searchMoviesApi(query);
//    }

    private void searchMoviesApi(String query){

    }

    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mMovieListViewModel.searchMoviesApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void testRetrofitRequest(){
        searchMoviesApi("Thor");
    }

    @Override
    public void onMovieClick(int position) {

    }



    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_home_activity:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
