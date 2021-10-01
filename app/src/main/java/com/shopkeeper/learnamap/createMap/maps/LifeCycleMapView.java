package com.shopkeeper.learnamap.createMap.maps;

import android.content.Context;
import android.util.AttributeSet;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;

public class LifeCycleMapView extends MapView implements LifecycleObserver {
    public LifeCycleMapView(Context context) {
        super(context);
    }

    public LifeCycleMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LifeCycleMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public LifeCycleMapView(Context context, AMapOptions aMapOptions) {
        super(context, aMapOptions);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        this.onDestroy();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        this.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        this.onPause();
    }
}
