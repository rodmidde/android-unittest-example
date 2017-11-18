package com.example.activity;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class RestDataControllerIntegrationTest {
    @Test
    public void whenFirstCommentIsRequestedItShouldStartWithLaudantium()
    {
        RestDataController_ restDataController = RestDataController_.getInstance_(null);
        assertTrue(restDataController.getData().startsWith("laudantium"));
    }
}
