package com.example.crud.movie;

import com.example.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesApi {
    public MoviesService createMoviesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MoviesService moviesService = retrofit.create(MoviesService.class);
        return moviesService;
    }
}
