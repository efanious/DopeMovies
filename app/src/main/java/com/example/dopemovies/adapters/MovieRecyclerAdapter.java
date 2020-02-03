package com.example.dopemovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dopemovies.R;
import com.example.dopemovies.models.Movie;

import java.util.List;

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
}
