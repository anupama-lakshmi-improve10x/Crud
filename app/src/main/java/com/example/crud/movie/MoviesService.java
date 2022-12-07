package com.example.crud.movie;

import com.example.crud.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MoviesService {
    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movies>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    Call<Movies> createMovie(@Body Movies movies);

    @DELETE(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> deleteMovie(@Path("id") String id);

    @PUT(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> updateMovie(@Path("id") String id, @Body Movies movies);
}
