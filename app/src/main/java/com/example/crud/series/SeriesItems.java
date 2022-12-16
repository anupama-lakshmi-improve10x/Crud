package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesItems implements Serializable {

    @SerializedName("_id")
    public String id;
    public String seriesId;
    public String title;
    public String imageUrl;

    public SeriesItems() {
    }

    public SeriesItems(String seriesId, String title, String imageUrl) {
        this.seriesId = seriesId;
        this.title =  title;
        this.imageUrl = imageUrl;
    }
}
