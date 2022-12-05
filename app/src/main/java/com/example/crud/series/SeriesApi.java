package com.example.crud.series;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesApi {
    public SeriesService createSeriesService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://crudcrud.com/api/479dd07f8c1d482e9219f7dcb48e25f4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SeriesService seriesService = retrofit.create(SeriesService.class);
        return seriesService;
    }
}
