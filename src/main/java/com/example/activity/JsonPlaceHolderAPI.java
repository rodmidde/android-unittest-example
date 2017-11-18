package com.example.activity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


interface JsonPlaceHolderAPI {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("comments/{id}")
    Call<Comment> getComments(@Path("id") int id);
}
