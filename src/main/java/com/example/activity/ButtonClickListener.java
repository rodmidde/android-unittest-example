package com.example.activity;

import android.view.View;
import android.widget.EditText;

import com.example.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

@EBean
class ButtonClickListener implements IButtonClickListener {
    private DeckardActivity activity;
    private IDataController dataController;

    @Bean(RestDataController.class)
    void setDataController(IDataController dataController){
        this.dataController = dataController;
    }

    @Override
    public void onClick(View view) {
        getAsyncComment();
    }

    @Background
    void getAsyncComment() {
        updateTextField(dataController.getData());
    }

    @UiThread
    void updateTextField(String data) {
        EditText editText = activity.findViewById(R.id.editText);
        editText.setText(data);
    }

    public void setActivity(DeckardActivity activity) {
        this.activity = activity;
    }
}
