package com.example.shengbin.peliculasproyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shengbin.peliculasproyecto.json.Result;

import java.util.ArrayList;

// API KEY : eec33652afa70e666fc6d094216e0714
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayList<Result> items;
    private ClientTheMovieDbPersonalitzat adapter;
    public MainActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final ListView listMovies = (ListView) rootView.findViewById(R.id.lvForecasts);


        items = new ArrayList<>();
        adapter=new ClientTheMovieDbPersonalitzat(
                getContext(),
                R.layout.list_personalit_movies,
                items
        );

        items = new ArrayList<>();
        listMovies.setAdapter(adapter);
        /*
        Cridem a la nova activity quan apretem a un Item de la listView
         */
        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent i = new Intent (getContext(),DetailActivity.class);
              /*
              L'hi passem la opcio trida
               */
              i.putExtra("item", adapter.getItem(position));
              startActivity(i);
          }
      });

        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.menu_pelis_action, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void refresh () {

        ClientTheMovieDb apiClient = new ClientTheMovieDb();

        //apiClient.getPopularityMovies(adapter);
        /*
        Serveix per mostrar a les preferencies.
         */
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String tipusConsulta = preferences.getString("tipus_consulta", "vistes");

        if (tipusConsulta.equals("vistes")) {
            apiClient.getPopularityMovies(adapter);
        } else {
            apiClient.getVoteAverage(adapter);
        }


    }
    @Override
    public void onStart() {
                super.onStart();
               refresh();
            }

}
