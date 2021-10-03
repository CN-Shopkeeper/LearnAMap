package com.shopkeeper.learnamap.drawOnMap;

import android.os.Bundle;

import com.shopkeeper.learnamap.RedirectActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.DrawLineActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.DrawPointActivity;

public class DrawOnMapActivity extends RedirectActivity {

    private final String[] names = {"绘制点标记", "绘制线"};
    private final Class<?>[] classes = {
            DrawPointActivity.class,
            DrawLineActivity.class
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