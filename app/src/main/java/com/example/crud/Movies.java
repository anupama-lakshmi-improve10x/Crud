package com.example.crud;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movies implements Serializable {
    @SerializedName("_id")
    public String id;
    public String description;
    public String seriesId;
    public String movieId;
    @SerializedName("name")
    public String title;
    public String imageUrl;
}
