package com.example.crud.template;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TemplatesService {

    @GET("anupamaTemplates")
    Call<List<Template>> fetchTemplates();

    @POST("anupamaTemplates")
    Call<Template> createTemplate(@Body Template template);

    @DELETE("anupamaTemplates/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT("anupamaTemplates/{id}")
    Call<Void> updateTemplate(@Path("id") String id, @Body Template template);
}
