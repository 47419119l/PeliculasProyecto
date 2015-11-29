package com.example.shengbin.peliculasproyecto;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.example.shengbin.peliculasproyecto.json.Movies;
import com.example.shengbin.peliculasproyecto.json.Result;

import com.example.shengbin.peliculasproyecto.provider.movies.MoviesColumns;
import com.example.shengbin.peliculasproyecto.provider.movies.MoviesContentValues;

import java.util.ArrayList;
import java.util.List;

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
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
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
    public void getMovies(){
        CargarPelisInternet pelisInternet = new CargarPelisInternet();
        pelisInternet.execute();
    }

    public void getPopularityMovies(){
        //Hacemos una llamada
        Call<Movies> moviesCall=service.popularityMovies(API_KEY);
        moviesCall.enqueue(new Callback<Movies>() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Movies movies = response.body();
                    ContentValues [] bulkToInsert;

                    List<ContentValues> mValueList = new ArrayList<>();

                    for (Result pelicula : movies.getResults()) {

                        MoviesContentValues moviesValues = new MoviesContentValues();
                        moviesValues.putMovieDate(pelicula.getReleaseDate());
                        moviesValues.putMovieDescription(pelicula.getOverview());
                        moviesValues.putMoviePopularity(String.valueOf(pelicula.getPopularity()));
                        moviesValues.putMovieTitle(pelicula.getTitle());
                        moviesValues.putMoviePoster(pelicula.getPosterPath());
                        mValueList.add(moviesValues.values());

                    }

                    bulkToInsert=new ContentValues[mValueList.size()];
                    mValueList.toArray(bulkToInsert);
                    context.getContentResolver().bulkInsert(MoviesColumns.CONTENT_URI,bulkToInsert);

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    public void getVoteAverage(){

        //Hacemos una llamada
        Call<Movies> moviesCall=service.VoteAverage(API_KEY);

        moviesCall.enqueue(new Callback<Movies>() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Movies movies = response.body();
                    ContentValues [] bulkToInsert;

                    List<ContentValues> mValueList = new ArrayList<>();

                    for (Result pelicula : movies.getResults()) {

                        MoviesContentValues moviesValues = new MoviesContentValues();
                        moviesValues.putMovieDate(pelicula.getReleaseDate());
                        moviesValues.putMovieDescription(pelicula.getOverview());
                        moviesValues.putMoviePopularity(String.valueOf(pelicula.getPopularity()));
                        moviesValues.putMovieTitle(pelicula.getTitle());
                        moviesValues.putMoviePoster(pelicula.getPosterPath());
                        mValueList.add(moviesValues.values());

                    }

                    bulkToInsert=new ContentValues[mValueList.size()];
                    mValueList.toArray(bulkToInsert);
                    context.getContentResolver().bulkInsert(MoviesColumns.CONTENT_URI,bulkToInsert);

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }



    private void deleteOldMovies(long syncTime) {
        context.getContentResolver().delete(
                MoviesColumns.CONTENT_URI,
                null,
                new String[]{Long.toString(syncTime)});

        context.getContentResolver().delete(
                MoviesColumns.CONTENT_URI,
                null,
                new String[]{Long.toString(syncTime)});
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
    class CargarPelisInternet extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] params) {

            getPopularityMovies();
            getVoteAverage();
            long syncTime = System.currentTimeMillis();
            deleteOldMovies(syncTime);
            return null;
        }
    }
}

