package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Series implements Serializable {

//Todo : Change Series to SeriesItem
    @SerializedName("_id")
    public String id;
    public String seriesId;
    public String title;
    public String imageUrl;

    public Series() {
    }

    public Series(String seriesId, String title, String imageUrl) {
        this.seriesId = seriesId;
        this.title =  title;
        this.imageUrl = imageUrl;
    }
}
