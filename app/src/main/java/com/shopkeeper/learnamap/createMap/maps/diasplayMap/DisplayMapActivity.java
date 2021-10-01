package com.shopkeeper.learnamap.createMap.maps.diasplayMap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.shopkeeper.learnamap.R;
import com.shopkeeper.learnamap.databinding.ActivityDisplayMapBinding;

public class DisplayMapActivity extends AppCompatActivity {

    private ActivityDisplayMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_map);
        getLifecycle().addObserver(binding.mapView);
        binding.mapView.onCreate(savedInstanceState);
//        AMap aMap = null;
//        aMap = binding.mapView.getMap();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.mapView.onSaveInstanceState(outState);
    }
}