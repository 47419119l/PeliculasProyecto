package com.example.shengbin.peliculasproyecto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import com.example.shengbin.peliculasproyecto.json.Movies;
import com.example.shengbin.peliculasproyecto.json.Result;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;

// API KEY : eec33652afa70e666fc6d094216e0714
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
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

        String[] data = {
                "Mision imposible",
                "Harry Potter",
                "Sr de los anillos"
        };

        items = new ArrayList<>(Arrays.asList(data));
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.list_row,
                R.id.tvForecast,
                items
        );

        ListView lvForecast = (ListView) rootView.findViewById(R.id.lvForecasts);
        lvForecast.setAdapter(adapter);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void refresh () {
        //Conectamos con la api
        final String BASE_URL = "https://api.themoviedb.org/3/discover/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Creamos el servicio
         final ClientTheMovieDb service = retrofit.create(ClientTheMovieDb.class);

        //Hacemos una llamada
        Call<Movies> moviesCall=service.dailyForecast();
        moviesCall.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                Movies movies = response.body();
                for(Result list : movies.getResults()){

                    String title = list.getOriginalTitle();
                    Double popularity= list.getPopularity();
                    Log.w("list", String.format(title, " : ", popularity));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.w(null, Arrays.toString(t.getStackTrace()));
            }
        });

    }
    public interface ClientTheMovieDb
    {
        @GET("movie?api_key=eec33652afa70e666fc6d094216e0714&sort_by=popularity.desc")
        Call<Movies> dailyForecast();
    }



}
