package com.example.crud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface MoviesService {
    @GET("movies")
    Call<List<Movies>> fetchMovies();
}
