package org.techtown.Shin;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    //https://ghibliapi.herokuapp.com/films
    @GET("films")
    Call<List<MovieList>> getMovies();
}
