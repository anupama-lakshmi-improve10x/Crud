package com.example.crud.series;

import com.example.crud.message.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SeriesService {
    @GET("anupamaseries")
    Call<List<Series>> fetchSeries();

    @POST("anupamaseries")
    Call<Series> createSeries(@Body Series series);

    @DELETE("anupamaseries/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT("anupamaseries/{id}")
    Call<Void> updateSeries(@Path("id") String id, @Body Series series);
}
