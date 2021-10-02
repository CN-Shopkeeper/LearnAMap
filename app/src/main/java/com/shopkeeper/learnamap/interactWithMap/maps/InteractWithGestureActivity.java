package com.shopkeeper.learnamap.interactWithMap.maps;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.UiSettings;
import com.shopkeeper.learnamap.MapActivity;

public class InteractWithGestureActivity extends MapActivity {

    private UiSettings mUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        AMap aMap = binding.mapView.getMap();
        mUiSettings = aMap.getUiSettings();

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        CheckBox zoomGestureCB = new CheckBox(this);
        CheckBox scrollGesturesCB = new CheckBox(this);
        CheckBox rotateGesturesCB = new CheckBox(this);
        CheckBox tiltGesturesCB = new CheckBox(this);
        Button allGestureBtn = new Button(this);
//        初始化组件
        zoomGestureCB.setChecked(mUiSettings.isZoomGesturesEnabled());
        zoomGestureCB.setText("缩放手势");
        scrollGesturesCB.setChecked(mUiSettings.isScrollGesturesEnabled());
        scrollGesturesCB.setText("滑动手势");
        rotateGesturesCB.setChecked(mUiSettings.isRotateGesturesEnabled());
        rotateGesturesCB.setText("旋转手势");
        tiltGesturesCB.setChecked(mUiSettings.isTiltGesturesEnabled());
        tiltGesturesCB.setText("倾斜手势");
        allGestureBtn.setText("允许所有手势");
//        增加监听器
        zoomGestureCB.setOnCheckedChangeListener((view, zoomGesture) -> mUiSettings.setZoomGesturesEnabled(zoomGesture));
        scrollGesturesCB.setOnCheckedChangeListener((view, scrollGestures) -> mUiSettings.setScrollGesturesEnabled(scrollGestures));
        rotateGesturesCB.setOnCheckedChangeListener((view, rotateGestures) -> mUiSettings.setRotateGesturesEnabled(rotateGestures));
        tiltGesturesCB.setOnCheckedChangeListener((view, tiltGestures) -> mUiSettings.setTiltGesturesEnabled(tiltGestures));
        tiltGesturesCB.setOnClickListener((view) -> Toast.makeText(this, "用户可以在地图上放置两个手指，移动它们一起向下或向上去增加或减小倾斜角，也可以禁用倾斜手势。", Toast.LENGTH_SHORT).show());
        allGestureBtn.setOnClickListener((view) -> {
            zoomGestureCB.setChecked(true);
            scrollGesturesCB.setChecked(true);
            rotateGesturesCB.setChecked(true);
            tiltGesturesCB.setChecked(true);
            mUiSettings.setAllGesturesEnabled(true);
        });

//        增加到linearLayout中
        linearLayout.addView(zoomGestureCB);
        linearLayout.addView(scrollGesturesCB);
        linearLayout.addView(rotateGesturesCB);
        linearLayout.addView(tiltGesturesCB);
        linearLayout.addView(allGestureBtn);
        binding.constraintLayout.addView(linearLayout);
    }
}