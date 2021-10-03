package com.shopkeeper.learnamap.drawOnMap.maps;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.RotateAnimation;
import com.shopkeeper.learnamap.MapActivity;
import com.shopkeeper.learnamap.R;

import java.util.ArrayList;

public class DrawPointActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        AMap aMap = binding.mapView.getMap();
        LatLng beijing = new LatLng(39.906901, 116.397972);
        LatLng zhengzhou = new LatLng(34.746303, 113.625351);
        LatLng yancheng = new LatLng(33.34832, 120.162417);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

//        添加marker
        ArrayList<MarkerOptions> markerOptions = new ArrayList<>();
        markerOptions.add(new MarkerOptions().position(beijing).title("北京").snippet("DefaultMarker: 北京(39.906901,116.397972)"));
        markerOptions.add(new MarkerOptions().position(yancheng).title("盐城").snippet("AnimationMarker: 盐城(33.34832,120.162417)"));
        MarkerOptions customMarkOption = new MarkerOptions().position(zhengzhou).title("郑州").snippet("CustomMarker: 郑州(34.746303,113.625351)");
        customMarkOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.drawable.location_marker)));
        customMarkOption.setFlat(true);
        customMarkOption.draggable(true);
        markerOptions.add(customMarkOption);

        ArrayList<Marker> markers = aMap.addMarkers(markerOptions, true);

//        增加监听器
        aMap.addOnMarkerClickListener((marker -> {
            Toast.makeText(this, "点击了点图标：" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
            return false;
        }));
        TextView dragStateTV = new TextView(this);
        dragStateTV.setText("长按郑州市的点图标进行拖拽");
        aMap.addOnMarkerDragListener(new AMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                dragStateTV.setText(String.format("正在拖动：%s", marker.getSnippet()));
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                dragStateTV.setText("长按郑州市的点图标进行拖拽");
            }
        });
        linearLayout.addView(dragStateTV);

//        为marker设置动画
        Marker yanchengMarker = markers.get(1);

        Button animationBtn = new Button(this);
        animationBtn.setText("播放动画");
        animationBtn.setOnClickListener((view) -> {
            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(yancheng, 8, 0, 0));
            aMap.animateCamera(mCameraUpdate, 1000L, new AMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    Animation animation = new RotateAnimation(yanchengMarker.getRotateAngle(), yanchengMarker.getRotateAngle() + 180, 0, 0, 0);
                    long duration = 1000L;
                    animation.setDuration(duration);
                    animation.setInterpolator(new LinearInterpolator());

                    yanchengMarker.setAnimation(animation);
                    yanchengMarker.startAnimation();
                }

                @Override
                public void onCancel() {

                }
            });
        });
        linearLayout.addView(animationBtn);

//        自定义infoWindow
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            View infoWindow = null;

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getInfoContents(Marker marker) {
                if (infoWindow == null) {
                    infoWindow = LayoutInflater.from(binding.mapView.getContext()).inflate(
                            R.layout.custom_info_window, null);
                }
                render(marker, infoWindow);
                return infoWindow;
            }

            private void render(Marker marker, View infoWindow) {
                ((TextView) infoWindow.findViewById(R.id.title)).setText(marker.getTitle());
                ((TextView) infoWindow.findViewById(R.id.snippet)).setText(marker.getSnippet());
            }
        });

//        绑定信息窗点击事件
        aMap.setOnInfoWindowClickListener(marker -> Toast.makeText(binding.getRoot().getContext(), "点击了信息框：" + marker.getSnippet(), Toast.LENGTH_SHORT).show());

        binding.constraintLayout.addView(linearLayout);
    }
}