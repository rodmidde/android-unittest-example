package com.example.activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestDataControllerTest {
    public static final String MESSAGE = "message";
    public static final String BODY = "body";

    @Mock
    RetrofitBuilder retrofitBuilder;

    @Mock
    Logger logger;

    @Mock
    JsonPlaceHolderAPI jsonPlaceHolderAPI;

    @Mock
    Call<Comment> commentCall;

    @Test
    public void whenExceptionIsThrownItsMessageGetsLogged() throws IOException {
        RestDataController restDataController = new RestDataController();
        restDataController.retrofitBuilder = retrofitBuilder;
        when(retrofitBuilder.create(JsonPlaceHolderAPI.class)).thenReturn(jsonPlaceHolderAPI);
        when(jsonPlaceHolderAPI.getComments(1)).thenReturn(commentCall);
        when(commentCall.execute()).thenThrow(new IOException(MESSAGE));
        Whitebox.setInternalState(RestDataController.class, "logger", logger);
        assertNull(restDataController.getData());
        verify(logger).log(Level.SEVERE, MESSAGE);

    }

    @Test
    public void whenCallSucceedsTheCorrectCommentBodyIsReturned() throws IOException {
        Comment comment = new Comment();
        comment.setBody(BODY);

        RestDataController restDataController = new RestDataController();
        restDataController.retrofitBuilder = retrofitBuilder;
        when(retrofitBuilder.create(JsonPlaceHolderAPI.class)).thenReturn(jsonPlaceHolderAPI);
        when(jsonPlaceHolderAPI.getComments(1)).thenReturn(commentCall);
        Response value =  Response.success(comment);
        when(commentCall.execute()).thenReturn(value);
        Whitebox.setInternalState(RestDataController.class, "logger", logger);
        assertEquals(restDataController.getData(), BODY);

    }
}