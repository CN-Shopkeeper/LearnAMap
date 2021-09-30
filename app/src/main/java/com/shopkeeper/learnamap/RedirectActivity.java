package com.shopkeeper.learnamap;

import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public abstract class RedirectActivity extends AppCompatActivity {
    protected abstract String[] getNames();

    protected abstract Class<?>[] getClasses();

    protected abstract LinearLayout getLinearLayout();

    protected void initData() {
        for (int i = 0; i < getNames().length; i++) {
            Button button = new Button(this);
            button.setText(getNames()[i]);
            button.setId(i);
            button.setOnClickListener(new RedirectBtnListener(this, button, getClasses()[i]));
            getLinearLayout().addView(button);
        }
    }
}
