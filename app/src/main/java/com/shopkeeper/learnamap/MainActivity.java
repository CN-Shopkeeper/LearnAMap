package com.shopkeeper.learnamap;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.shopkeeper.learnamap.createMap.CreateMapActivity;
import com.shopkeeper.learnamap.databinding.ActivityMainBinding;

public class MainActivity extends RedirectActivity {

    private final String[] names = {"创建地图"};
    private final Class<?>[] classes = {CreateMapActivity.class};
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    @Override
    protected String[] getNames() {
        return names;
    }

    @Override
    protected Class<?>[] getClasses() {
        return classes;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return binding.linearLayout;
    }
}