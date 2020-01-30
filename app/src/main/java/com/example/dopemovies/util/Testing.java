package com.example.dopemovies.util;

import android.util.Log;

import com.example.dopemovies.models.Movie;

import java.util.List;

public class Testing {

    public static void printMovies(List<Movie> list, String tag){
        for (Movie movie: list){
            Log.d(tag, "onChanged: " + movie.getOriginal_title());
        }

    }
}
