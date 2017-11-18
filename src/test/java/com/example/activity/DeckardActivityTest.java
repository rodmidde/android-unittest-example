package com.example.activity;

import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeckardActivityTest {
    @Mock
    Button button;

    @Mock
    ButtonClickListener buttonClickListener;

    @Test
    public void whenActivityCreatedAppDependenciesAreSetup() throws Exception {
        DeckardActivity deckardActivity = new DeckardActivity();
        deckardActivity.button = button;
        deckardActivity.buttonClickHandler = buttonClickListener;
        deckardActivity.onCreate(null);
        deckardActivity.afterCreate();

        verify(buttonClickListener).setActivity(deckardActivity);
        verify(button).setOnClickListener(buttonClickListener);
    }
}
