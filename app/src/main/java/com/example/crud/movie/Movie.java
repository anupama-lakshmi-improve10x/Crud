package com.example.crud.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    public String description;
    public String seriesId;
    public String movieId;
    @SerializedName("name")
    public String title;
    public String imageUrl;
}
