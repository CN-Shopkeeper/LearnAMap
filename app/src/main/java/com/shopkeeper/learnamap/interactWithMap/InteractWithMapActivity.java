package com.shopkeeper.learnamap.interactWithMap;

import android.os.Bundle;

import com.shopkeeper.learnamap.RedirectActivity;
import com.shopkeeper.learnamap.interactWithMap.maps.InteractWithControlActivity;
import com.shopkeeper.learnamap.interactWithMap.maps.InteractWithGestureActivity;

public class InteractWithMapActivity extends RedirectActivity {

    private final String[] names = {"控件交互", "手势交互"};
    private final Class<?>[] classes = {
            InteractWithControlActivity.class,
            InteractWithGestureActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String[] getNames() {
        return names;
    }

    @Override
    protected Class<?>[] getClasses() {
        return classes;
    }
}