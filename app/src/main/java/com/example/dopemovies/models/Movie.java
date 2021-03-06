package com.example.dopemovies.models;

import android.os.Parcel;
import android.os.Parcelable;


public class Movie implements Parcelable {

    private String original_title;
    private String original_language;
    private String overview;
    private String homepage;
    private String poster_path;
    private String backdrop_path;
    private int id;

    public Movie(String original_title, String original_language, String overview, String homepage, String poster_path, String backdrop_path, int id) {
        this.original_title = original_title;
        this.original_language = original_language;
        this.overview = overview;
        this.homepage = homepage;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.id = id;
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        original_title = in.readString();
        original_language = in.readString();
        overview = in.readString();
        homepage = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {

        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "original_title='" + original_title + '\'' +
                ", original_language='" + original_language + '\'' +
                ", overview='" + overview + '\'' +
                ", homepage='" + homepage + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(original_title);
        dest.writeString(original_language);
        dest.writeString(overview);
        dest.writeString(homepage);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeInt(id);
    }
}
