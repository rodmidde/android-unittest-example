package com.example.activity;

import android.view.View;
import android.widget.EditText;

import com.example.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ButtonClickListenerTest {
    @Mock
    DeckardActivity deckardActivity;

    @Mock
    IDataController dataController;

    @InjectMocks
    ButtonClickListener buttonClickListener;

    @Mock
    EditText editText;

    @Mock
    View eventSource;

    @Test
    public void whenButtonClickedTheEditTextContainsHelloOOSE()
    {
        when(deckardActivity.findViewById(R.id.editText)).thenReturn(editText);
        when(dataController.getData()).thenReturn("Hello OOSE");
        buttonClickListener.setActivity(deckardActivity);
        buttonClickListener.setDataController(dataController);
        buttonClickListener.onClick(eventSource);
        verify(editText).setText("Hello OOSE");
    }
}