package com.example.shengbin.peliculasproyecto.provider.movies;

import com.example.shengbin.peliculasproyecto.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code movies} table.
 */
public interface MoviesModel extends BaseModel {

    /**
     * Get the {@code movie_title} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMovieTitle();

    /**
     * Get the {@code movie_description} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMovieDescription();

    /**
     * Get the {@code movie_popularity} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMoviePopularity();

    /**
     * Get the {@code movie_poster} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMoviePoster();

    /**
     * Get the {@code movie_date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getMovieDate();
}
