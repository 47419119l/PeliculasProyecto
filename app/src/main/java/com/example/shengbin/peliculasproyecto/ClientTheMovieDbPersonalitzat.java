package com.example.shengbin.peliculasproyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shengbin.peliculasproyecto.json.Movies;
import com.example.shengbin.peliculasproyecto.json.Result;

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

        Result pelicula =getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_personalit_movies, parent, false);
        }


        TextView titleMovies = (TextView) convertView.findViewById(R.id.titleMovie);
        TextView data = (TextView) convertView.findViewById(R.id.data);
        TextView popularity = (TextView) convertView.findViewById(R.id.popularity);
        /*
        Introduïm els valors
         */
        titleMovies.setText(pelicula.getTitle());
        data.setText(pelicula.getReleaseDate());
        popularity.setText((int) pelicula.getPopularity()+" %");




        /*
        Tornem la View plena
         */
        return convertView;
    }
}
