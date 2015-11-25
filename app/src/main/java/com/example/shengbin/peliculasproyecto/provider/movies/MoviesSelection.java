package com.example.shengbin.peliculasproyecto.provider.movies;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.shengbin.peliculasproyecto.provider.base.AbstractSelection;

/**
 * Selection for the {@code movies} table.
 */
public class MoviesSelection extends AbstractSelection<MoviesSelection> {
    @Override
    protected Uri baseUri() {
        return MoviesColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MoviesCursor} object, which is positioned before the first entry, or null.
     */
    public MoviesCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MoviesCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MoviesCursor} object, which is positioned before the first entry, or null.
     */
    public MoviesCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MoviesCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MoviesCursor query(Context context) {
        return query(context, null);
    }


    public MoviesSelection id(long... value) {
        addEquals("movies." + MoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public MoviesSelection idNot(long... value) {
        addNotEquals("movies." + MoviesColumns._ID, toObjectArray(value));
        return this;
    }

    public MoviesSelection orderById(boolean desc) {
        orderBy("movies." + MoviesColumns._ID, desc);
        return this;
    }

    public MoviesSelection orderById() {
        return orderById(false);
    }

    public MoviesSelection movieTitle(String... value) {
        addEquals(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesSelection movieTitleNot(String... value) {
        addNotEquals(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesSelection movieTitleLike(String... value) {
        addLike(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesSelection movieTitleContains(String... value) {
        addContains(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesSelection movieTitleStartsWith(String... value) {
        addStartsWith(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesSelection movieTitleEndsWith(String... value) {
        addEndsWith(MoviesColumns.MOVIE_TITLE, value);
        return this;
    }

    public MoviesSelection orderByMovieTitle(boolean desc) {
        orderBy(MoviesColumns.MOVIE_TITLE, desc);
        return this;
    }

    public MoviesSelection orderByMovieTitle() {
        orderBy(MoviesColumns.MOVIE_TITLE, false);
        return this;
    }

    public MoviesSelection movieDescription(String... value) {
        addEquals(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesSelection movieDescriptionNot(String... value) {
        addNotEquals(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesSelection movieDescriptionLike(String... value) {
        addLike(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesSelection movieDescriptionContains(String... value) {
        addContains(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesSelection movieDescriptionStartsWith(String... value) {
        addStartsWith(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesSelection movieDescriptionEndsWith(String... value) {
        addEndsWith(MoviesColumns.MOVIE_DESCRIPTION, value);
        return this;
    }

    public MoviesSelection orderByMovieDescription(boolean desc) {
        orderBy(MoviesColumns.MOVIE_DESCRIPTION, desc);
        return this;
    }

    public MoviesSelection orderByMovieDescription() {
        orderBy(MoviesColumns.MOVIE_DESCRIPTION, false);
        return this;
    }

    public MoviesSelection moviePopularity(String... value) {
        addEquals(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesSelection moviePopularityNot(String... value) {
        addNotEquals(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesSelection moviePopularityLike(String... value) {
        addLike(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesSelection moviePopularityContains(String... value) {
        addContains(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesSelection moviePopularityStartsWith(String... value) {
        addStartsWith(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesSelection moviePopularityEndsWith(String... value) {
        addEndsWith(MoviesColumns.MOVIE_POPULARITY, value);
        return this;
    }

    public MoviesSelection orderByMoviePopularity(boolean desc) {
        orderBy(MoviesColumns.MOVIE_POPULARITY, desc);
        return this;
    }

    public MoviesSelection orderByMoviePopularity() {
        orderBy(MoviesColumns.MOVIE_POPULARITY, false);
        return this;
    }

    public MoviesSelection moviePoster(String... value) {
        addEquals(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesSelection moviePosterNot(String... value) {
        addNotEquals(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesSelection moviePosterLike(String... value) {
        addLike(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesSelection moviePosterContains(String... value) {
        addContains(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesSelection moviePosterStartsWith(String... value) {
        addStartsWith(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesSelection moviePosterEndsWith(String... value) {
        addEndsWith(MoviesColumns.MOVIE_POSTER, value);
        return this;
    }

    public MoviesSelection orderByMoviePoster(boolean desc) {
        orderBy(MoviesColumns.MOVIE_POSTER, desc);
        return this;
    }

    public MoviesSelection orderByMoviePoster() {
        orderBy(MoviesColumns.MOVIE_POSTER, false);
        return this;
    }

    public MoviesSelection movieDate(String... value) {
        addEquals(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesSelection movieDateNot(String... value) {
        addNotEquals(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesSelection movieDateLike(String... value) {
        addLike(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesSelection movieDateContains(String... value) {
        addContains(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesSelection movieDateStartsWith(String... value) {
        addStartsWith(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesSelection movieDateEndsWith(String... value) {
        addEndsWith(MoviesColumns.MOVIE_DATE, value);
        return this;
    }

    public MoviesSelection orderByMovieDate(boolean desc) {
        orderBy(MoviesColumns.MOVIE_DATE, desc);
        return this;
    }

    public MoviesSelection orderByMovieDate() {
        orderBy(MoviesColumns.MOVIE_DATE, false);
        return this;
    }
}
