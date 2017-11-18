package com.example.activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.androidannotations.annotations.EBean;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
class RetrofitBuilder {
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public JsonPlaceHolderAPI create(Class<JsonPlaceHolderAPI> apiClass) {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(
                        GsonConverterFactory.create(gson)).build().create(apiClass);
    }
}
