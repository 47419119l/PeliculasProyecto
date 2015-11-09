package com.example.shengbin.peliculasproyecto;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shengbin.peliculasproyecto.json.Result;

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

        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        titulo = (TextView)view.findViewById(R.id.titulo);
        data = (TextView)view.findViewById(R.id.dataEstrena);
        popularity = (TextView)view.findViewById(R.id.popularidad);
        descripcio = (TextView)view.findViewById(R.id.descripcio);

        Intent i = getActivity().getIntent();
        Result item = (Result) i.getSerializableExtra("item");

        Toast.makeText(getContext(), item.getOriginalTitle(), Toast.LENGTH_LONG).show();


        titulo.setText(item.getOriginalTitle());
        data.setText(item.getReleaseDate());
        popularity.setText(item.getPopularity()+" %");
        descripcio.setText(item.getOverview());


        return view;
    }




}