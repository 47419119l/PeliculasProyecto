package com.example.shengbin.peliculasproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shengbin.peliculasproyecto.json.Movies;
import com.example.shengbin.peliculasproyecto.json.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClientTheMovieDbPersonalitzat extends ArrayAdapter<Result> {
    /*
    Creem constructor amb el super.
     */
    public ClientTheMovieDbPersonalitzat(Context context, int resource, List<Result> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final  String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
        final String POSTER_SIZE="w185";

        Result pelicula =getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_personalit_movies, parent, false);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.imageView);

        Picasso.with(getContext())
                .load(POSTER_BASE_URL+POSTER_SIZE+pelicula.getPosterPath())
                .fit()
                .into(image);

        /*
        Tornem la View plena
         */
        return convertView;
    }
}
