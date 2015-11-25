package com.example.shengbin.peliculasproyecto.provider.movies;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shengbin.peliculasproyecto.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movies} table.
 */
public class MoviesCursor extends AbstractCursor implements MoviesModel {
    public MoviesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MoviesColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code movie_title} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieTitle() {
        String res = getStringOrNull(MoviesColumns.MOVIE_TITLE);
        return res;
    }

    /**
     * Get the {@code movie_description} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieDescription() {
        String res = getStringOrNull(MoviesColumns.MOVIE_DESCRIPTION);
        return res;
    }

    /**
     * Get the {@code movie_popularity} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviePopularity() {
        String res = getStringOrNull(MoviesColumns.MOVIE_POPULARITY);
        return res;
    }

    /**
     * Get the {@code movie_poster} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviePoster() {
        String res = getStringOrNull(MoviesColumns.MOVIE_POSTER);
        return res;
    }

    /**
     * Get the {@code movie_date} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieDate() {
        String res = getStringOrNull(MoviesColumns.MOVIE_DATE);
        return res;
    }
}
