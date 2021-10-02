package com.shopkeeper.learnamap.interactWithMap.maps;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.shopkeeper.learnamap.MapActivity;

public class InteractWithFunctionActivity extends MapActivity {

    private final int BEIJING_RB = 1;
    private final int YANCHENG_RB = 2;
    private final int ZOOM_TO_15_RB = 3;
    private final int ZOOM_TO_8_RB = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        AMap aMap = binding.mapView.getMap();
        Log.i("InteractWithFunctionActivity", "initView: " + aMap.getCameraPosition());

//        设置地图视角移动动画
        CheckBox animateCB = new CheckBox(this);
        animateCB.setText("地图视角移动动画");

//        设置地图中心点
        RadioGroup centerRG = new RadioGroup(this);
        RadioButton beijingRB = new RadioButton(this);
        beijingRB.setId(BEIJING_RB);
        beijingRB.setText("去北京");
        RadioButton yanchengRB = new RadioButton(this);
        yanchengRB.setId(YANCHENG_RB);
        yanchengRB.setText("去盐城");
        centerRG.addView(beijingRB);
        centerRG.addView(yanchengRB);
        centerRG.setOrientation(LinearLayout.HORIZONTAL);
        centerRG.setOnCheckedChangeListener((view, id) -> {
            CameraUpdate cameraUpdate;
            if (id == BEIJING_RB) {
                //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(39.92421106207774, 116.39786327434547), 10, 0, 0));
            } else if (id == YANCHENG_RB) {
                cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(33.380000, 120.130000), 10, 0, 0));
            } else {
                return;
            }
            if (animateCB.isChecked()) {
                aMap.animateCamera(cameraUpdate, 2000L, null);
            } else {
                aMap.moveCamera(cameraUpdate);
            }
        });

//        设置缩放大小
        RadioGroup zoomRG = new RadioGroup(this);
        RadioButton zoomTo15RB = new RadioButton(this);
        zoomTo15RB.setId(ZOOM_TO_15_RB);
        zoomTo15RB.setText("放大地图");
        RadioButton zoomTo8RB = new RadioButton(this);
        zoomTo8RB.setId(ZOOM_TO_8_RB);
        zoomTo8RB.setText("缩小地图");
        zoomRG.addView(zoomTo15RB);
        zoomRG.addView(zoomTo8RB);
        zoomRG.setOrientation(LinearLayout.HORIZONTAL);
        zoomRG.setOnCheckedChangeListener((view, id) -> {
            CameraUpdate cameraUpdate;
            if (id == ZOOM_TO_15_RB) {
                cameraUpdate = CameraUpdateFactory.zoomTo(15);
            } else if (id == ZOOM_TO_8_RB) {
                cameraUpdate = CameraUpdateFactory.zoomTo(8);
            } else {
                return;
            }
            if (animateCB.isChecked()) {
                aMap.animateCamera(cameraUpdate, 2000L, null);
            } else {
                aMap.moveCamera(cameraUpdate);
            }
        });

//        限制地图显示范围
        LatLng southwestLatLng = new LatLng(30.00, 115.00);
        LatLng northeastLatLng = new LatLng(36.00, 125.00);
        LatLngBounds latLngBounds = new LatLngBounds(southwestLatLng, northeastLatLng);
        CheckBox limitsCB = new CheckBox(this);
        limitsCB.setText("只看江苏");
        limitsCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                aMap.setMapStatusLimits(latLngBounds);
            } else {
                aMap.setMapStatusLimits(null);
            }
        });


//        加入布局
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(animateCB);
        linearLayout.addView(centerRG);
        linearLayout.addView(zoomRG);
        linearLayout.addView(limitsCB);
        binding.constraintLayout.addView(linearLayout);
    }
}