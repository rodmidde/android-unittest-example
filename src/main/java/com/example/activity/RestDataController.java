package com.example.activity;


import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@EBean
public class RestDataController implements IDataController {
    private final static Logger logger = Logger.getLogger(RestDataController.class.getName());

    @Bean
    RetrofitBuilder retrofitBuilder;

    @Override
    public String getData() {
        try {
            return retrofitBuilder.create(JsonPlaceHolderAPI.class).
                    getComments(1).execute().body().getBody();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }
}
