package com.example.crud;

import com.example.crud.message.Message;
import com.example.crud.series.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MoviesService {
    @GET("anupamaMovies")
    Call<List<Movies>> fetchMovies();

    @POST("anupamaMovies")
    Call<Movies> createMovie(@Body Movies movies);

    @DELETE("anupamaMovies/{id}")
    Call<Void> deleteMovie(@Path("id") String id);

    @PUT("anupamaMovies/{id}")
    Call<Void> updateMovie(@Path("id") String id, @Body Movies movies);
}
