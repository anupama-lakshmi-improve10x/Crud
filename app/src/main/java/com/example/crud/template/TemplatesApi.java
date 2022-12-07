package com.example.crud.template;

import com.example.crud.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TemplatesApi {
    public TemplatesService createTemplateService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TemplatesService templateService = retrofit.create(TemplatesService.class);
        return templateService;
    }
}
