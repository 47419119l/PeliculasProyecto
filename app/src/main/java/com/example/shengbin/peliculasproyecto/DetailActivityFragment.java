package com.example.shengbin.peliculasproyecto;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shengbin.peliculasproyecto.json.Result;
import com.example.shengbin.peliculasproyecto.provider.movies.MoviesColumns;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private TextView titulo ;
    private TextView data;
    private TextView popularity;
    private TextView descripcio;
    private long id=-1;
    ImageView image;
    final  String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    final String POSTER_SIZE="w185";
    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detail, container, false);



        titulo = (TextView)view.findViewById(R.id.titulo);
        data = (TextView)view.findViewById(R.id.dataEstrena);
        popularity = (TextView)view.findViewById(R.id.popularidad);
        descripcio = (TextView)view.findViewById(R.id.descripcio);
        image = (ImageView)view.findViewById(R.id.poster);

        Intent i = getActivity().getIntent();
        id = i.getLongExtra("cursor",-1);
        if(id!=-1) {
            details();
        }
        return view;
    }

    public void details(){

        Cursor item = getContext().getContentResolver().query(
                MoviesColumns.CONTENT_URI,
                null,
                MoviesColumns._ID + " = ?",
                new String[]{String.valueOf(id)},
                null);
        if(item!=null) {

            item.moveToNext();

            titulo.setText(item.getString(item.getColumnIndex(MoviesColumns.MOVIE_TITLE)));
            data.setText(item.getString(item.getColumnIndex(MoviesColumns.MOVIE_DATE)));
            popularity.setText(item.getString(item.getColumnIndex(MoviesColumns.MOVIE_POPULARITY)));
            descripcio.setText(item.getString(item.getColumnIndex(MoviesColumns.MOVIE_DESCRIPTION)));

            Picasso.with(getContext())
                    .load(POSTER_BASE_URL + POSTER_SIZE + item.getString(item.getColumnIndex(MoviesColumns.MOVIE_POSTER)))
                    .fit()
                    .into(image);
        }
    }
}