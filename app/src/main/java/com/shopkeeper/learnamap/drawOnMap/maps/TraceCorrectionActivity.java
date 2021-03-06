package com.shopkeeper.learnamap.drawOnMap.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceStatusListener;
import com.shopkeeper.learnamap.MapActivity;
import com.shopkeeper.learnamap.R;

import java.util.ArrayList;
import java.util.List;

public class TraceCorrectionActivity extends MapActivity {
    LBSTraceClient traceClient = null;
    Polyline tracedPolyline = null;
    Polyline polyline = null;
    private AMap aMap;
    TraceStatusListener listener = (locations, rectifications, errorInfo) -> {
        try {
            if (!LBSTraceClient.TRACE_SUCCESS.equals(errorInfo)) {
                Log.e("amap", " source count->" + (locations == null ? "0" : locations.size()) + "   result count->" + (rectifications == null ? "0" : rectifications.size()));

                StringBuilder stringBuffer = new StringBuilder();

                if (locations != null) {
                    for (TraceLocation location : locations) {
                        stringBuffer.append("{");
                        stringBuffer.append("\"lon\":").append(location.getLongitude()).append(",");
                        stringBuffer.append("\"lat\":").append(location.getLatitude()).append(",");
                        stringBuffer.append("\"loctime\":").append(location.getTime()).append(",");
                        stringBuffer.append("\"speed\":").append(location.getSpeed()).append(",");
                        stringBuffer.append("\"bearing\":").append(location.getBearing());
                        stringBuffer.append("}");
                        stringBuffer.append("\n");
                    }
                }
                Log.i("amap", "?????????????????????????????????????????????:\n" +
                        "??????????????????\n" +
                        "onTraceStatus?????????????????? ??????????????????????????????????????????????????????\n" +
                        "?????????????????????????????????????????????????????????(lbs.amap.com)??????????????????????????? \n" + errorInfo + " \n "
                        + stringBuffer.toString());


                return;
            }

            showSourceLocations(locations);
            showTracedLocations(rectifications);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        aMap = binding.mapView.getMap();
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);

        LinearLayout linearLayout = new LinearLayout(this);
        Button startBtn = new Button(this);
        startBtn.setText("??????");
        startBtn.setOnClickListener(v -> {
            if (traceClient == null) {
                traceClient = LBSTraceClient.getInstance(TraceCorrectionActivity.this);
            }
            traceClient.startTrace(listener);
        });
        Button stopBtn = new Button(this);
        stopBtn.setText("??????");
        stopBtn.setOnClickListener((view) -> {
            if (traceClient == null) {
                traceClient = LBSTraceClient.getInstance(TraceCorrectionActivity.this);
            }
            traceClient.stopTrace();
        });

        linearLayout.addView(startBtn);
        linearLayout.addView(stopBtn);
        binding.constraintLayout.addView(linearLayout);
    }

    /**
     * ?????????????????????
     */
    private void showTracedLocations(List<LatLng> rectifications) {
        if (tracedPolyline == null) {
            tracedPolyline = aMap.addPolyline(new PolylineOptions()
                    .setCustomTexture(BitmapDescriptorFactory.fromResource(R.drawable.custtexture))
                    .width(20).zIndex(0));
        }
        if (rectifications == null) {
            return;
        }
        tracedPolyline.setPoints(rectifications);
    }

    /**
     * ?????????????????????
     */
    private void showSourceLocations(List<TraceLocation> locations) {
        if (polyline == null) {
            polyline = aMap.addPolyline(new PolylineOptions().color(Color.parseColor("#88FF0000")).width(40).zIndex(0));
        }
        if (locations == null) {
            return;
        }
        List<LatLng> latLngs = new ArrayList<>();
        for (TraceLocation traceLocation : locations) {
            latLngs.add(new LatLng(traceLocation.getLatitude(), traceLocation.getLongitude()));
        }
        polyline.setPoints(latLngs);
    }
}