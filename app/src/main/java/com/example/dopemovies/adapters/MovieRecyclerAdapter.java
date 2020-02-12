package com.example.dopemovies.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dopemovies.R;
import com.example.dopemovies.models.Movie;

import java.net.URI;
import java.net.URL;
import java.util.List;

import okhttp3.HttpUrl;

import static com.example.dopemovies.util.Constants.BASE_IMAGE_URL;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMovies;
    private OnMovieListener mOnMovieListener;

    public MovieRecyclerAdapter(OnMovieListener mOnMovieListener) {
        this.mOnMovieListener = mOnMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view, mOnMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // Utilizing the images URL to get poster image URL and converting to String
        String posterUrl = mMovies.get(position).getPoster_path();
        URL url = HttpUrl.parse(BASE_IMAGE_URL + "w500/" + posterUrl).url();
        String newPosterUrl = url.toString();

        //Glide
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_reel);

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(newPosterUrl)
                .into(((MovieViewHolder)holder).poster);

//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.drawable.ic_reel);
//
//        Glide.with(holder.itemView.getContext())
//                .setDefaultRequestOptions(requestOptions)
//                .load(mMovies.get(position).getPoster_path())
//                .into(((MovieViewHolder)holder).poster);



        ((MovieViewHolder)holder).original_title.setText(mMovies.get(position).getOriginal_title());
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public Movie getSelectedMovie(int position) {
        if (mMovies != null) {
            if (mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
        return null;
    }
}
