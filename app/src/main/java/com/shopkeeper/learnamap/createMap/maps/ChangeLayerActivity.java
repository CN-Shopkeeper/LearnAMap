package com.shopkeeper.learnamap.createMap.maps;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.shopkeeper.learnamap.MapActivity;

public class ChangeLayerActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
//        获取地图实例
        AMap aMap = binding.mapView.getMap();

//        创建组件
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button buttonNormal = new Button(this);
        buttonNormal.setText("标准地图");
        buttonNormal.setOnClickListener((view) -> aMap.setMapType(AMap.MAP_TYPE_NORMAL));
        Button buttonSatellite = new Button(this);
        buttonSatellite.setText("卫星地图");
        buttonSatellite.setOnClickListener((view) -> aMap.setMapType(AMap.MAP_TYPE_SATELLITE));
        Button buttonNight = new Button(this);
        buttonNight.setText("夜间模式");
        buttonNight.setOnClickListener((view) -> aMap.setMapType(AMap.MAP_TYPE_NIGHT));
        Button buttonNavi = new Button(this);
        buttonNavi.setText("导航地图");
        buttonNavi.setOnClickListener((view) -> aMap.setMapType(AMap.MAP_TYPE_NAVI));
        CheckBox showTraffic = new CheckBox(this);
        showTraffic.setText("显示路况");
        showTraffic.setOnCheckedChangeListener((checkBox, show) -> {
            aMap.setTrafficEnabled(show);//显示实时路况图层，aMap是地图控制器对象。
        });

        linearLayout.addView(buttonNormal);
        linearLayout.addView(buttonSatellite);
        linearLayout.addView(buttonNight);
        linearLayout.addView(buttonNavi);
        linearLayout.addView(showTraffic);
        binding.constraintLayout.addView(linearLayout);

    }


}
