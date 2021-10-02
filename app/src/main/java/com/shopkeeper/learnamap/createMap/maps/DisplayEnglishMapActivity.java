package com.shopkeeper.learnamap.createMap.maps;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.shopkeeper.learnamap.MapActivity;

public class DisplayEnglishMapActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
          设置地图底图语言，目前支持中文底图和英文底图

          @param language AMap.CHINESE 表示中文，即"zh_cn", AMap.ENGLISH 表示英文，即"en"
         */
        binding.mapView.getMap().setMapLanguage(AMap.ENGLISH);
    }
}