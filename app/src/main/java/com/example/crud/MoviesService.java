package com.example.crud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.PUT;

public interface MoviesService {
    @PUT("movies")
    Call<List<Movies>> fetchMovies();
}
