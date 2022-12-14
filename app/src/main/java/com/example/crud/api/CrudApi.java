package com.example.crud.api;

import com.example.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrudApi {
    //Todo : Add one empty line after every class
    public CrudService createCrudService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       CrudService crudService = retrofit.create(CrudService.class);
       //Todo: Missing indentation
        return crudService;
    }
}
