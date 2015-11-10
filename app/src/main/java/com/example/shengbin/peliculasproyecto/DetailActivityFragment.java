package com.example.shengbin.peliculasproyecto;

import android.content.Intent;
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
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private TextView titulo ;
    private TextView data;
    private TextView popularity;
    private TextView descripcio;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final  String POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
        final String POSTER_SIZE="w185";

        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        titulo = (TextView)view.findViewById(R.id.titulo);
        data = (TextView)view.findViewById(R.id.dataEstrena);
        popularity = (TextView)view.findViewById(R.id.popularidad);
        descripcio = (TextView)view.findViewById(R.id.descripcio);
        ImageView image = (ImageView)view.findViewById(R.id.poster);
        /*
        Fem un Intent per poder extreure el objecte que hem sel·lecionat
         */
        Intent i = getActivity().getIntent();
        Result item = (Result) i.getSerializableExtra("item");

        Toast.makeText(getContext(), item.getOriginalTitle(), Toast.LENGTH_LONG).show();

        /*
        Extraiem la informació que volem mostrar
         */
        titulo.setText(item.getOriginalTitle());
        data.setText("Release Date : "+item.getReleaseDate());
        popularity.setText("Popularity : "+item.getPopularity() + " %");
        descripcio.setText(item.getOverview());

        Picasso.with(getContext())
                .load(POSTER_BASE_URL+POSTER_SIZE+item.getPosterPath())
                .resize(690,760)
                .into(image);

        return view;
    }




}