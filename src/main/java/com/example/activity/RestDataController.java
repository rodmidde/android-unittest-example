package com.example.activity;


import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

@EBean
public class RestDataController implements IDataController {

    @Bean
    RetrofitBuilder retrofitBuilder;

    @Override
    public String getData() {
        try {
            return retrofitBuilder.create(JsonPlaceHolderAPI.class).
                    getComments(1).execute().body().getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
