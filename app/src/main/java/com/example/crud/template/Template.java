package com.example.crud.template;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Template  implements Serializable{

    @SerializedName("_id")
    public String id;
    public String messageText;

    public Template() {
    }

    public Template(String messageText) {
        this.messageText = messageText;
    }
}
