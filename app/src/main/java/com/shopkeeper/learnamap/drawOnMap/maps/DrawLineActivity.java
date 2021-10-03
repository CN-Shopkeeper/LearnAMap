package com.shopkeeper.learnamap.drawOnMap.maps;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.shopkeeper.learnamap.MapActivity;

import java.util.ArrayList;
import java.util.List;

public class DrawLineActivity extends MapActivity {

    private static final int LINEARLAYOUT_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        AMap aMap = binding.mapView.getMap();
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(39.999391, 116.135972));
        latLngs.add(new LatLng(39.898323, 116.057694));
        latLngs.add(new LatLng(39.900430, 116.265061));
        latLngs.add(new LatLng(39.955192, 116.140092));
        Polyline polyline = aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setId(LINEARLAYOUT_ID);
        TextView titleTV = new TextView(this);
        titleTV.setText("设置Polyline属性");
        linearLayout.addView(titleTV);
//        颜色
        LinearLayout colorLL = new LinearLayout(this);
        TextView colorTV = new TextView(this);
        colorTV.setText("颜  色");
        SeekBar colorSB = new SeekBar(this);
        colorLL.addView(colorTV);
        colorLL.addView(colorSB);
        ViewGroup.LayoutParams colorLP = colorSB.getLayoutParams();
        colorLP.width = MATCH_PARENT;
        colorSB.setLayoutParams(colorLP);
        colorSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                polyline.setColor(Color.argb(255, progress, 1, 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        透明度
        LinearLayout alphaLL = new LinearLayout(this);
        TextView alphaTV = new TextView(this);
        alphaTV.setText("透明度");
        SeekBar alphaSB = new SeekBar(this);
        alphaLL.addView(alphaTV);
        alphaLL.addView(alphaSB);
        ViewGroup.LayoutParams alphaLP = alphaSB.getLayoutParams();
        alphaLP.width = MATCH_PARENT;
        alphaSB.setLayoutParams(alphaLP);
        alphaSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float[] prevHSV = new float[3];
                Color.colorToHSV(polyline.getColor(), prevHSV);
                polyline.setColor(Color.HSVToColor(progress, prevHSV));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        粗细
        LinearLayout widthLL = new LinearLayout(this);
        TextView widthTV = new TextView(this);
        widthTV.setText("透明度");
        SeekBar widthSB = new SeekBar(this);
        widthLL.addView(widthTV);
        widthLL.addView(widthSB);
        ViewGroup.LayoutParams widthLP = widthSB.getLayoutParams();
        widthLP.width = MATCH_PARENT;
        widthSB.setLayoutParams(widthLP);
        widthSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                polyline.setWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        linearLayout.addView(colorLL);
        linearLayout.addView(alphaLL);
        linearLayout.addView(widthLL);
        binding.constraintLayout.addView(linearLayout);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.width = MATCH_PARENT;
        linearLayout.setLayoutParams(layoutParams);
    }
}