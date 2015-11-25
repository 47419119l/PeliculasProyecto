package com.example.shengbin.peliculasproyecto.provider.movies;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.shengbin.peliculasproyecto.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movies} table.
 */
public class MoviesContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MoviesColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MoviesSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MoviesSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MoviesContentValues putMovieTitle(@Nullable String value) {
        mContentValues.put(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesContentValues putMovieTitleNull() {
        mContentValues.putNull(MoviesColumns.MOVIE_TITLE);
        return this;
    }

    public MoviesContentValues putMovieDescription(@Nullable String value) {
        mContentValues.put(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesContentValues putMovieDescriptionNull() {
        mContentValues.putNull(MoviesColumns.MOVIE_DESCRIPTION);
        return this;
    }

    public MoviesContentValues putMoviePopularity(@Nullable String value) {
        mContentValues.put(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesContentValues putMoviePopularityNull() {
        mContentValues.putNull(MoviesColumns.MOVIE_POPULARITY);
        return this;
    }

    public MoviesContentValues putMoviePoster(@Nullable String value) {
        mContentValues.put(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesContentValues putMoviePosterNull() {
        mContentValues.putNull(MoviesColumns.MOVIE_POSTER);
        return this;
    }

    public MoviesContentValues putMovieDate(@Nullable String value) {
        mContentValues.put(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesContentValues putMovieDateNull() {
        mContentValues.putNull(MoviesColumns.MOVIE_DATE);
        return this;
    }
}
