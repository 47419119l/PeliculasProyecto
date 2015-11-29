package com.example.shengbin.peliculasproyecto;

import android.annotation.TargetApi;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.shengbin.peliculasproyecto.provider.movies.MoviesColumns;

// API KEY : eec33652afa70e666fc6d094216e0714
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private AdapterTheMovieDbSQLiteList adapter;

    public MainActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView listMovies = (GridView) rootView.findViewById(R.id.lvForecasts);

        adapter=new AdapterTheMovieDbSQLiteList(
                getContext(),
                R.layout.list_personalit_movies,
                null,
                new String []{MoviesColumns.MOVIE_POSTER},
                new int[]{R.id.imageView},
                0);
        getLoaderManager().initLoader(0,null,this);
        listMovies.setAdapter(adapter);

        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("cursor", id);
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

        ClientTheMovieDb apiClient = new ClientTheMovieDb(getContext());
        apiClient.getMovies();

    }
    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String tipusConsulta = preferences.getString("tipus_consulta", "vistes");

        if (tipusConsulta.equals("vistes")) {
            return new android.support.v4.content.CursorLoader(getContext(),
                    MoviesColumns.CONTENT_URI,
                    null,
                    null,
                    null,
                    "_id");
        } else {
            return new android.support.v4.content.CursorLoader(
                    getContext(),
                    MoviesColumns.CONTENT_URI,
                    null,
                    null,
                    null,
                    "_id");
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
