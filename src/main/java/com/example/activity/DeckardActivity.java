package com.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.deckard)
public class DeckardActivity extends Activity {
    @ViewById(R.id.button)
    Button button;

    @ViewById(R.id.editText)
    TextView editText;

    @Bean(ButtonClickListener.class)
    IButtonClickListener buttonClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void afterCreate()
    {
        buttonClickHandler.setActivity(this);
        button.setOnClickListener(buttonClickHandler);
    }
}
