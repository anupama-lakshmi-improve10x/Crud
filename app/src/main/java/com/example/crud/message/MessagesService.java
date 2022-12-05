package com.example.crud.message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MessagesService {

    @GET("anupamaMessageHistory")
    Call<List<Message>> fetchMessages();

    @POST("anupamaMessageHistory")
    Call<Message> createMessage(@Body Message message);

    @DELETE("anupamaMessageHistory/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT("anupamaMessageHistory/{id}")
    Call<Void> updateMessage(@Path("id") String id, @Body Message message);

}
