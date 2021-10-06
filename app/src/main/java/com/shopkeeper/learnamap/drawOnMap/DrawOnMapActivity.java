package com.shopkeeper.learnamap.drawOnMap;

import android.os.Bundle;

import com.shopkeeper.learnamap.RedirectActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.Draw3DModelActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.DrawHeatMapActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.DrawLineActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.DrawPlaneActivity;
import com.shopkeeper.learnamap.drawOnMap.maps.DrawPointActivity;

public class DrawOnMapActivity extends RedirectActivity {

    private final String[] names = {"绘制点标记", "绘制线", "绘制平面", "绘制热力图", "绘制3D模型"};
    private final Class<?>[] classes = {
            DrawPointActivity.class,
            DrawLineActivity.class,
            DrawPlaneActivity.class,
            DrawHeatMapActivity.class,
            Draw3DModelActivity.class
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