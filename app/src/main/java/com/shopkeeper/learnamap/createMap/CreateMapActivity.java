package com.shopkeeper.learnamap.createMap;


import android.os.Bundle;

import com.amap.api.maps.offlinemap.OfflineMapActivity;
import com.shopkeeper.learnamap.RedirectActivity;
import com.shopkeeper.learnamap.createMap.maps.ChangeLayerActivity;
import com.shopkeeper.learnamap.createMap.maps.CustomMapActivity;
import com.shopkeeper.learnamap.createMap.maps.DisplayBlueDotActivity;
import com.shopkeeper.learnamap.createMap.maps.DisplayEnglishMapActivity;
import com.shopkeeper.learnamap.createMap.maps.DisplayInnerDoorActivity;
import com.shopkeeper.learnamap.createMap.maps.DisplayMapActivity;

public class CreateMapActivity extends RedirectActivity {

    private final String[] names = {"显示地图", "显示定位蓝点", "显示室内地图", "切换地图图层", "使用离线地图(官方UI组件)", "显示英文地图", "自定义地图"};
    private final Class<?>[] classes = {
            DisplayMapActivity.class,
            DisplayBlueDotActivity.class,
            DisplayInnerDoorActivity.class,
            ChangeLayerActivity.class,
            OfflineMapActivity.class,
            DisplayEnglishMapActivity.class,
            CustomMapActivity.class
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