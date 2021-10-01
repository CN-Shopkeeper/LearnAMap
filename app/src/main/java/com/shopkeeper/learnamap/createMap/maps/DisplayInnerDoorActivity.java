package com.shopkeeper.learnamap.createMap.maps;

import android.os.Bundle;

import com.shopkeeper.learnamap.MapActivity;

public class DisplayInnerDoorActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.mapView.getMap().showIndoorMap(true);
    }
}
