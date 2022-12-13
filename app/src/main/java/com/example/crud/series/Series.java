package com.example.crud.series;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Series implements Serializable {

    @SerializedName("_id")
    public String id;
    public String seriesId;
    public String title;
    public String imageUrl;
}
