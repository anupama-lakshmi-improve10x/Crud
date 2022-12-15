package com.example.crud.api;

import com.example.crud.Constants;
import com.example.crud.message.Message;
import com.example.crud.movie.Movie;
import com.example.crud.series.Series;
import com.example.crud.template.Template;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudService {

    @GET(Constants.MESSAGES_END_POINT)
    Call<List<Message>> fetchMessages();

    @POST(Constants.MESSAGES_END_POINT)
    Call<Message> createMessage(@Body Message message);

    @DELETE(Constants.MESSAGES_END_POINT + "/{id}")
    Call<Void> deleteMessage(@Path("id") String id);

    @PUT(Constants.MESSAGES_END_POINT + "/{id}")
    Call<Void> updateMessage(@Path("id") String id, @Body Message message);

    @GET(Constants.TEMPLATES_END_POINT)
    Call<List<Template>> fetchTemplates();

    @POST(Constants.TEMPLATES_END_POINT)
    Call<Template> createTemplate(@Body Template template);

    @DELETE(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> deleteTemplate(@Path("id") String id);

    @PUT(Constants.TEMPLATES_END_POINT + "/{id}")
    Call<Void> updateTemplate(@Path("id") String id, @Body Template template);

    @GET(Constants.SERIES_END_POINT)
    //Todo: change methodName to fetchSeriesItems,createSeriesItem, deleteSeriesItem, updateSeriesItem
    Call<List<Series>> fetchSeriesItems();

    @POST(Constants.SERIES_END_POINT)
    Call<Series> createSeriesItem(@Body Series series);

    @DELETE(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> deleteSeriesItem(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> updateSeriesItem(@Path("id") String id, @Body Series series);

    @GET(Constants.MOVIES_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIES_END_POINT)
    //Todo: Change obj name to movie in createMovie and updateMovie methods
    Call<Movie> createMovie(@Body Movie movies);

    @DELETE(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> deleteMovie(@Path("id") String id);

    @PUT(Constants.MOVIES_END_POINT + "/{id}")
    Call<Void> updateMovie(@Path("id") String id, @Body Movie movies);
}
