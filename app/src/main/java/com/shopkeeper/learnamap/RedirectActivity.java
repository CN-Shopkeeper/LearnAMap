package com.shopkeeper.learnamap;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shopkeeper.learnamap.databinding.ActivityRedirectBinding;

public abstract class RedirectActivity extends AppCompatActivity {

    protected ActivityRedirectBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_redirect);
        initData();
    }

    protected abstract String[] getNames();

    protected abstract Class<?>[] getClasses();

    private void initData() {
        for (int i = 0; i < getNames().length; i++) {
            Button button = new Button(this);
            button.setText(getNames()[i]);
            button.setId(i);
            button.setOnClickListener(new RedirectBtnListener(this, button, getClasses()[i]));
            binding.linearLayout.addView(button);
        }
    }
}
