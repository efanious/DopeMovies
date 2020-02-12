package com.example.dopemovies;


import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dopemovies.models.Movie;
import com.example.dopemovies.viewmodels.MovieViewModel;

import java.net.URL;

import okhttp3.HttpUrl;

import static com.example.dopemovies.util.Constants.BASE_IMAGE_URL;

public class MovieActivity extends BaseActivity {

    private static final String TAG = "MovieActivity";
    //UI components
    private ImageView mMovieImage, mMovieBackDrop;
    private TextView mMovieTitle, mMovieOverview;
//    private ConstraintLayout mConstraintLayout;

    private MovieViewModel mMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
//        mConstraintLayout.setVisibility(View.VISIBLE);

        mMovieImage = findViewById(R.id.movieImageView);
        mMovieBackDrop = findViewById(R.id.backdropImageView);
        mMovieTitle = findViewById(R.id.movieTitleTextView);
        mMovieOverview = findViewById(R.id.overviewTextView);
//        mConstraintLayout = findViewById(R.id.parent);


        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

//        showProgressBar(true);

        subscribeObservers();
        getIncomingIntent();


    }


    private void getIncomingIntent(){
        if (getIntent().hasExtra("movie")){
            Movie movie = getIntent().getParcelableExtra("movie");
            mMovieViewModel.getMoviesById(movie.getId());

            //Backdrop
            RequestOptions  requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_reel);

            String backDropUrl = movie.getBackdrop_path();
            URL url = HttpUrl.parse(BASE_IMAGE_URL + "w500/" + backDropUrl).url();
            String newBackDropUrl = url.toString();

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(newBackDropUrl)
                    .into(mMovieBackDrop);

            //Movie Image
            RequestOptions  requestOptions2 = new RequestOptions()
                    .placeholder(R.drawable.ic_reel);

            String imageUrl = movie.getPoster_path();
            URL url2 = HttpUrl.parse(BASE_IMAGE_URL + "w500/" + imageUrl).url();
            String newImageUrl = url2.toString();

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions2)
                    .load(newImageUrl)
                    .into(mMovieImage);

            //Other views
            mMovieTitle.setText(movie.getOriginal_title());
            mMovieOverview.setText(movie.getOverview());
        }
    }

    private void subscribeObservers(){
        mMovieViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null){
                    setMovieProperties(movie);
//                    Log.d(TAG, "Backdrop url: " + movie.getBackdrop_path());
//                    Log.d(TAG, "Title: " + movie.getOriginal_title());
//                    Log.d(TAG, "id: " + movie.getId());
//                    Log.d(TAG, "Poster Path: " + movie.getPoster_path());

                }
            }
        });
    }


    private void setMovieProperties(Movie movie){
        if (movie != null){

            //Backdrop
            RequestOptions  requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_reel);

            String backDropUrl = movie.getBackdrop_path();
            URL url = HttpUrl.parse(BASE_IMAGE_URL + "w500/" + backDropUrl).url();
            String newBackDropUrl = url.toString();

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(newBackDropUrl)
                    .into(mMovieBackDrop);

            //Movie Image
            RequestOptions  requestOptions2 = new RequestOptions()
                    .placeholder(R.drawable.ic_reel);

            String imageUrl = movie.getPoster_path();
            URL url2 = HttpUrl.parse(BASE_IMAGE_URL + "w500/" + imageUrl).url();
            String newImageUrl = url2.toString();

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions2)
                    .load(newImageUrl)
                    .into(mMovieImage);

            //Other views
            mMovieTitle.setText(movie.getOriginal_title());
            mMovieOverview.setText(movie.getOverview());
        }
//
//        //showParent();
//        showProgressBar(false);
    }

//    private void showParent(){
//        mConstraintLayout.setVisibility(View.VISIBLE);
//    }


//    // Utilizing the images URL to get poster image URL and converting to String
//    String posterUrl = mMovies.get(position).getPoster_path();
//    URL url = HttpUrl.parse(BASE_IMAGE_URL + "w500/" + posterUrl).url();
//    String newPosterUrl = url.toString();
}
