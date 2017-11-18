package com.example.activity;

import android.view.View;

interface IButtonClickListener extends View.OnClickListener{
    void setActivity(DeckardActivity activity);
}
