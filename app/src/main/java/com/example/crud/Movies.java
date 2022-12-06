package com.example.crud;

import com.google.gson.annotations.SerializedName;

public class Movies {
    @SerializedName("_id")
    public String id;
    public String description;
    public int seriesId;
    public int movieId;
    @SerializedName("name")
    public String title;
    public String imageUrl;
}
