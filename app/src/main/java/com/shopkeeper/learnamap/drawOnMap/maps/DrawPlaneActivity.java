package com.shopkeeper.learnamap.drawOnMap.maps;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.shopkeeper.learnamap.MapActivity;

import java.util.ArrayList;
import java.util.List;

public class DrawPlaneActivity extends MapActivity {

    private static final int LINEARLAYOUT_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        AMap aMap = binding.mapView.getMap();
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(4);
        aMap.moveCamera(mCameraUpdate);
        Circle circle = getCircle(aMap);
        Polygon oval = getOval(aMap);
        Polygon rectangle = getRectangle(aMap);
        initControl(circle, oval, rectangle);
    }

    private Circle getCircle(@NonNull AMap aMap) {
        LatLng latLng = new LatLng(39.984059, 116.307771);
        return aMap.addCircle(new CircleOptions().
                center(latLng).
                radius(100000).
                fillColor(Color.argb(255, 1, 1, 1)).
                strokeColor(Color.argb(255, 1, 1, 1)).
                strokeWidth(15));
    }

    private Polygon getOval(@NonNull AMap aMap) {
        PolygonOptions options = new PolygonOptions();
        int numPoints = 400;
        float semiHorizontalAxis = 5f;
        float semiVerticalAxis = 2.5f;
        double phase = 2 * Math.PI / numPoints;
        for (int i = 0; i <= numPoints; i++) {
            options.add(new LatLng(36.618305
                    + semiVerticalAxis * Math.sin(i * phase),
                    88.972806 + semiHorizontalAxis
                            * Math.cos(i * phase)));
        }
        // 绘制一个椭圆
        return aMap.addPolygon(options.strokeWidth(25)
                .strokeColor(Color.argb(50, 1, 1, 1))
                .fillColor(Color.argb(50, 1, 1, 1)));
    }

    /**
     * 生成一个长方形的四个坐标点
     */
    private List<LatLng> createRectangle(LatLng center, double halfWidth,
                                         double halfHeight) {
        List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
        latLngs.add(new LatLng(center.latitude - halfHeight, center.longitude + halfWidth));
        latLngs.add(new LatLng(center.latitude + halfHeight, center.longitude + halfWidth));
        latLngs.add(new LatLng(center.latitude + halfHeight, center.longitude - halfWidth));
        return latLngs;
    }

    private Polygon getRectangle(AMap aMap) {
        return aMap.addPolygon(new PolygonOptions()
                .addAll(createRectangle(new LatLng(28.404108, 103.079252), 1, 1))
                .fillColor(Color.LTGRAY).strokeColor(Color.RED).strokeWidth(1));
    }

    @SuppressLint("SetTextI18n")
    private void initControl(Circle circle, Polygon oval, Polygon rectangle) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setId(LINEARLAYOUT_ID);
        TextView titleTV = new TextView(this);
        titleTV.setText("设置Polyline属性");
        linearLayout.addView(titleTV);
//        颜色
        LinearLayout colorLL = new LinearLayout(this);
        TextView colorTV = new TextView(this);
        colorTV.setText("FillColor");
        SeekBar colorSB = new SeekBar(this);
        colorLL.addView(colorTV);
        colorLL.addView(colorSB);
        ViewGroup.LayoutParams colorLP = colorSB.getLayoutParams();
        colorLP.width = MATCH_PARENT;
        colorSB.setLayoutParams(colorLP);
        colorSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circle.setFillColor(Color.argb(255, progress * 256 / 100, 1, 1));
                oval.setFillColor(Color.argb(255, progress * 256 / 100, 1, 1));
                rectangle.setFillColor(Color.argb(255, progress * 256 / 100, 1, 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        边线颜色
        LinearLayout strokeLL = new LinearLayout(this);
        TextView strokeTV = new TextView(this);
        strokeTV.setText("StrokeColor");
        SeekBar strokeSB = new SeekBar(this);
        strokeLL.addView(strokeTV);
        strokeLL.addView(strokeSB);
        ViewGroup.LayoutParams strokeLP = strokeSB.getLayoutParams();
        strokeLP.width = MATCH_PARENT;
        strokeSB.setLayoutParams(strokeLP);
        strokeSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circle.setStrokeColor(Color.argb(255, progress * 256 / 100, 1, 1));
                oval.setStrokeColor(Color.argb(255, progress * 256 / 100, 1, 1));
                rectangle.setStrokeColor(Color.argb(255, progress * 256 / 100, 1, 1));
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
        widthTV.setText("StrokeWidth");
        SeekBar widthSB = new SeekBar(this);
        widthLL.addView(widthTV);
        widthLL.addView(widthSB);
        ViewGroup.LayoutParams widthLP = widthSB.getLayoutParams();
        widthLP.width = MATCH_PARENT;
        widthSB.setLayoutParams(widthLP);
        widthSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                circle.setStrokeWidth(progress);
                oval.setStrokeWidth(progress);
                rectangle.setStrokeWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        linearLayout.addView(colorLL);
        linearLayout.addView(strokeLL);
        linearLayout.addView(widthLL);
        binding.constraintLayout.addView(linearLayout);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.width = MATCH_PARENT;
        linearLayout.setLayoutParams(layoutParams);
    }
}