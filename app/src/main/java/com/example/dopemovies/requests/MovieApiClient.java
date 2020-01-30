package com.example.dopemovies.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dopemovies.AppExecutors;
import com.example.dopemovies.models.Movie;
import com.example.dopemovies.requests.responses.MovieSearchResponse;
import com.example.dopemovies.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.dopemovies.util.Constants.NETWORK_TIMEOUT;

public class MovieApiClient {

    private static final String TAG = "MovieApiClient";
    private static MovieApiClient instance;
    private MutableLiveData<List<Movie>> mMovies;
    private RetrieveMoviesRunnable mRetrieveMoviesRunnable;

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

    //Make request to the server
    public void searchMoviesApi(String query){

        if (mRetrieveMoviesRunnable != null){
            mRetrieveMoviesRunnable = null;
        }
        mRetrieveMoviesRunnable = new RetrieveMoviesRunnable(query);

        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Let the user know it's timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMovies(query).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200){
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    mMovies.postValue(list);
                }else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: "+ error);
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        private Call<MovieSearchResponse> getMovies(String query){
            return ServiceGenerator.getMovieApi().searchMovie(
                    Constants.API_KEY,
                    query
            );

        }

        private void cancelRequest(){
            Log.d(TAG, "cancelRequest: canceling the search request.");
            cancelRequest = true;
        }
    }
}
