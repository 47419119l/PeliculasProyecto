package com.example.shengbin.peliculasproyecto.provider.movies;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.shengbin.peliculasproyecto.provider.MovieProvider;
import com.example.shengbin.peliculasproyecto.provider.movies.MoviesColumns;

/**
 * Columns for the {@code movies} table.
 */
public class MoviesColumns implements BaseColumns {
    public static final String TABLE_NAME = "movies";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String MOVIE_TITLE = "movie_title";

    public static final String MOVIE_DESCRIPTION = "movie_description";

    public static final String MOVIE_POPULARITY = "movie_popularity";

    public static final String MOVIE_POSTER = "movie_poster";

    public static final String MOVIE_DATE = "movie_date";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            MOVIE_TITLE,
            MOVIE_DESCRIPTION,
            MOVIE_POPULARITY,
            MOVIE_POSTER,
            MOVIE_DATE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(MOVIE_TITLE) || c.contains("." + MOVIE_TITLE)) return true;
            if (c.equals(MOVIE_DESCRIPTION) || c.contains("." + MOVIE_DESCRIPTION)) return true;
            if (c.equals(MOVIE_POPULARITY) || c.contains("." + MOVIE_POPULARITY)) return true;
            if (c.equals(MOVIE_POSTER) || c.contains("." + MOVIE_POSTER)) return true;
            if (c.equals(MOVIE_DATE) || c.contains("." + MOVIE_DATE)) return true;
        }
        return false;
    }

}
