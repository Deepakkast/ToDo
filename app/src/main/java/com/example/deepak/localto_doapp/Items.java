package com.example.deepak.localto_doapp;

import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("subject")
    String subject;

    @SerializedName("date")
    String date;

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

}
