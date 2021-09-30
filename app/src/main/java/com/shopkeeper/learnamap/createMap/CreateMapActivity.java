package com.shopkeeper.learnamap.createMap;


import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.shopkeeper.learnamap.R;
import com.shopkeeper.learnamap.RedirectActivity;
import com.shopkeeper.learnamap.createMap.maps.DisplayMapActivity;
import com.shopkeeper.learnamap.databinding.ActivityCreateMapBinding;

public class CreateMapActivity extends RedirectActivity {

    private final String[] names = {"展示地图"};
    private final Class<?>[] classes = {DisplayMapActivity.class};

    private ActivityCreateMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_map);
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