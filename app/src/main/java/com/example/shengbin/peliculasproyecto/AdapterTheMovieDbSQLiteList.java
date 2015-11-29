package com.example.shengbin.peliculasproyecto;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import com.example.shengbin.peliculasproyecto.provider.movies.MoviesCursor;
import com.squareup.picasso.Picasso;
import com.example.shengbin.peliculasproyecto.provider.movies.MoviesColumns;
/**
 * Created by shengbin on 2015/11/27.
 */
public class AdapterTheMovieDbSQLiteList extends SimpleCursorAdapter {
    Context  context;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public AdapterTheMovieDbSQLiteList(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context= context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
        final String POSTER_SIZE="w185";
        /*
        Creo el cursor.
         */
        Cursor cursor = getCursor();
        MoviesCursor moviesCursor = new MoviesCursor(cursor);
        moviesCursor.moveToPosition(position);


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_personalit_movies, parent, false);
        }
        ImageView image = (ImageView)convertView.findViewById(R.id.imageView);
        String posterPath = cursor.getString(cursor.getColumnIndex(MoviesColumns.MOVIE_POSTER));
        Picasso.with(context)
                .load(POSTER_BASE_URL+POSTER_SIZE+posterPath)
                .fit()
                .into(image);
        /*
        Tornem la View plena
         */
        return convertView;
    }


}
