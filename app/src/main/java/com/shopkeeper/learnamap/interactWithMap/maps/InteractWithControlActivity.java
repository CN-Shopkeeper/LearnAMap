package com.shopkeeper.learnamap.interactWithMap.maps;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintSet;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.UiSettings;
import com.shopkeeper.learnamap.MapActivity;

public class InteractWithControlActivity extends MapActivity {

    private final int BOTTOM_LEFT = 1;
    private final int BOTTOM_CENTER = 2;
    private UiSettings mUiSettings;//定义一个UiSettings对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        AMap aMap = binding.mapView.getMap();
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        CheckBox zoomCB = new CheckBox(this);
        CheckBox compassCB = new CheckBox(this);
        CheckBox locationCB = new CheckBox(this);
        CheckBox scaleCB = new CheckBox(this);
        RadioGroup logoRG = new RadioGroup(this);
        RadioButton bottomLeft = new RadioButton(this);
        RadioButton bottomCenter = new RadioButton(this);
//        初始化组件
        int LINEAR_LAYOUT = 111;
        linearLayout.setId(LINEAR_LAYOUT);
        zoomCB.setChecked(mUiSettings.isZoomControlsEnabled());
        zoomCB.setText("缩放按钮");
        compassCB.setChecked(mUiSettings.isCompassEnabled());
        compassCB.setText("地图方向指南针");
        locationCB.setChecked(mUiSettings.isMyLocationButtonEnabled());
        locationCB.setText("定位按钮");
        scaleCB.setChecked(mUiSettings.isScaleControlsEnabled());
        scaleCB.setText("比例尺");
        bottomLeft.setText("左下商标");
        bottomLeft.setId(BOTTOM_LEFT);
        bottomCenter.setText("中下商标");
        bottomCenter.setId(BOTTOM_CENTER);
        logoRG.addView(bottomCenter);
        logoRG.addView(bottomLeft);
        logoRG.check(BOTTOM_LEFT);
//        增加监听器
        zoomCB.setOnCheckedChangeListener((view, zoom) -> mUiSettings.setZoomControlsEnabled(zoom));
        compassCB.setOnCheckedChangeListener((view, compass) -> mUiSettings.setCompassEnabled(compass));
        locationCB.setOnCheckedChangeListener((view, location) -> {
            mUiSettings.setMyLocationButtonEnabled(location); //显示默认的定位按钮
            aMap.setMyLocationEnabled(location);// 可触发定位并显示当前位置
        });
        scaleCB.setOnCheckedChangeListener((view, scale) -> mUiSettings.setScaleControlsEnabled(scale));
        logoRG.setOnCheckedChangeListener((view, id) -> {
            switch (id) {
                case BOTTOM_CENTER:
                    mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);//设置logo位置
                    break;
                case BOTTOM_LEFT:
                    mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
                    break;
            }
        });

//        增加到linearLayout中
        linearLayout.addView(zoomCB);
        linearLayout.addView(compassCB);
        linearLayout.addView(locationCB);
        linearLayout.addView(scaleCB);
        linearLayout.addView(logoRG);
        binding.constraintLayout.addView(linearLayout);
        ConstraintSet c = new ConstraintSet();
        c.clone(binding.constraintLayout);
        c.connect(LINEAR_LAYOUT, ConstraintSet.TOP, binding.constraintLayout.getId(), ConstraintSet.TOP);
        c.connect(LINEAR_LAYOUT, ConstraintSet.BOTTOM, binding.constraintLayout.getId(), ConstraintSet.BOTTOM);
        c.applyTo(binding.constraintLayout);
    }
}