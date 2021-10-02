package com.shopkeeper.learnamap.createMap.maps;

import android.os.Bundle;

import com.amap.api.maps.model.CustomMapStyleOptions;
import com.shopkeeper.learnamap.MapActivity;

import java.io.IOException;
import java.io.InputStream;

public class CustomMapActivity extends MapActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String styleName = "style.data";
        String styleExtraName = "style_extra.data";
        InputStream inputStream1 = null;
        InputStream inputStream2 = null;
        try {
            inputStream1 = this.getAssets().open(styleName);
            byte[] b1 = new byte[inputStream1.available()];
            int read1 = inputStream1.read(b1);
            inputStream2 = this.getAssets().open(styleExtraName);
            byte[] b2 = new byte[inputStream2.available()];
            int read2 = inputStream2.read(b2);
            binding.mapView.getMap().setCustomMapStyle(
                    new CustomMapStyleOptions()
                            .setEnable(true)
                            .setStyleData(b1)
                            .setStyleExtraData(b2)
            );
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream1 != null) {
                    inputStream1.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}