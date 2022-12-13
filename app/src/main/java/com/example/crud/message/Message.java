package com.example.crud.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {

    @SerializedName("_id")
    public String id;
    public String name;
    @SerializedName("phoneNumber")
    public String mobileNumber;
    @SerializedName("messageText")
    public String message;

    public Message() {

    }

    public Message(String name, String phoneNumber, String messageText) {
        this.name = name;
        this.mobileNumber = phoneNumber;
        this.message = messageText;
    }
}


