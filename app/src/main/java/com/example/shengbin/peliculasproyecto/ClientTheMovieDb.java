package com.example.shengbin.peliculasproyecto;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.shengbin.peliculasproyecto.json.Movies;
import com.example.shengbin.peliculasproyecto.json.Result;

import java.util.Arrays;

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

    //Conectamos con la api
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //Creamos el servicio

    ClientTheMovieDbInterface service = retrofit.create(ClientTheMovieDbInterface.class);

    public ClientTheMovieDb(){
        super();
    }
    public void getPopularityMovies(final ArrayAdapter adapter){

        //Hacemos una llamada
        Call<Movies> moviesCall=service.popularityMovies(API_KEY);
        moviesCall.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    Movies movies = response.body();
                    adapter.clear();
                    for (Result list : movies.getResults()) {
                        String title = list.getTitle();
                        adapter.add(title);
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.w(null, Arrays.toString(t.getStackTrace()));
            }
        });
    }

}
interface ClientTheMovieDbInterface
{
    @GET("movie?sort_by=popularity.desc")
    Call<Movies> popularityMovies(
            @Query("api_key") String api_key
    );


}