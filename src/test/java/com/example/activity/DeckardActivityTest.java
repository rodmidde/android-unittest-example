package com.example.activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class DeckardActivityTest {

    @Test
    public void whenActivityCreatedAppDependenciesAreSetup() throws Exception {
        DeckardActivity_ deckardActivity = Robolectric.setupActivity(DeckardActivity_.class);
        assertNotNull(deckardActivity.buttonClickHandler);
    }
}
