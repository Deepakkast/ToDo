package com.example.deepak.localto_doapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface todo
{

    String BASE_URL = "http://192.168.100.6:8000";

    @GET("/")
    Call<List<ToDoRetro>> getDetails();

}
