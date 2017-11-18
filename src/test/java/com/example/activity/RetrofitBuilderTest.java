package com.example.activity;

import org.junit.Test;

import static org.junit.Assert.*;

public class RetrofitBuilderTest {
    @Test
    public void builderCreatesANonNullInstanceOfJSonPlaceHolderAPI()
    {
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        assertNotNull(retrofitBuilder.create(JsonPlaceHolderAPI.class));
    }
}