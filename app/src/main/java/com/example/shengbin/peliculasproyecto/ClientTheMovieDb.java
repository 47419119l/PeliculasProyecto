package com.example.shengbin.peliculasproyecto;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.shengbin.peliculasproyecto.json.Movies;
import com.example.shengbin.peliculasproyecto.json.Result;
import com.example.shengbin.peliculasproyecto.provider.movies.MoviesColumns;
import com.example.shengbin.peliculasproyecto.provider.movies.MoviesContentValues;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by 47419119l on 03/11/15.
 */
public class ClientTheMovieDb {

    final String BASE_URL = "https://api.themoviedb.org/3/discover/";
    final String API_KEY = "eec33652afa70e666fc6d094216e0714";
    final Context context;
    //Conectamos con la api
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //Creamos el servicio

    ClientTheMovieDbInterface service = retrofit.create(ClientTheMovieDbInterface.class);

    public ClientTheMovieDb(Context context){
        super();
        this.context=context;
    }

    public void getPopularityMovies(final Cursor cursor, final AdapterTheMovieDbSQLiteList adapter){
        //Hacemos una llamada
        Call<Movies> moviesCall=service.popularityMovies(API_KEY);
        moviesCall.enqueue(new Callback<Movies>() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Movies movies = response.body();

                    for (Result pelicula : movies.getResults()) {

                        MoviesContentValues moviesValues = new MoviesContentValues();

                        moviesValues.putMovieDate(pelicula.getReleaseDate());
                        moviesValues.putMovieDescription(pelicula.getOverview());
                        moviesValues.putMoviePopularity(String.valueOf(pelicula.getPopularity()));
                        moviesValues.putMovieTitle(pelicula.getTitle());
                        moviesValues.putMoviePoster(pelicula.getPosterPath());

                        context.getContentResolver().insert(MoviesColumns.CONTENT_URI, moviesValues.values());

                    }
                    cargarPelis(cursor, adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                cargarPelis(cursor, adapter);
            }
        });
    }
    public void getVoteAverage(final Cursor cursor, final AdapterTheMovieDbSQLiteList adapter){

        //Hacemos una llamada
        Call<Movies> moviesCall=service.VoteAverage(API_KEY);
        moviesCall.enqueue(new Callback<Movies>() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Movies movies = response.body();

                    for (Result pelicula : movies.getResults()) {

                        MoviesContentValues moviesValues = new MoviesContentValues();
                        moviesValues.putMovieDate(pelicula.getReleaseDate());
                        moviesValues.putMovieDescription(pelicula.getOverview());
                        moviesValues.putMoviePopularity(String.valueOf(pelicula.getPopularity()));
                        moviesValues.putMovieTitle(pelicula.getTitle());
                        moviesValues.putMoviePoster(pelicula.getPosterPath());
                        context.getContentResolver().insert(MoviesColumns.CONTENT_URI, moviesValues.values());

                    }
                    cargarPelis(cursor, adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                cargarPelis(cursor, adapter);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)

    private void cargarPelis(Cursor cursor,AdapterTheMovieDbSQLiteList adapter){

        cursor = context.getContentResolver().query(
                MoviesColumns.CONTENT_URI,
                null,
                null,
                null,
                "_id");
        adapter.swapCursor(cursor);
    }

}
interface ClientTheMovieDbInterface
{
    /*
    Crida a la part variable del URL
     */
    @GET("movie?sort_by=popularity.desc")
    Call<Movies> popularityMovies(
            @Query("api_key") String api_key
    );

    @GET("movie?sort_by=vote_average.desc")
    Call<Movies> VoteAverage(
            @Query("api_key") String api_key
    );


}