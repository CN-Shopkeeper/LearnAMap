package com.shopkeeper.learnamap;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

class RedirectBtnListener implements View.OnClickListener {

    private final Activity originActivity;
    private final Button button;
    private final Class<?> tartetActivity;

    public RedirectBtnListener(Activity originActivity, Button button, Class<?> tartetActivity) {
        this.originActivity = originActivity;
        this.button = button;
        this.tartetActivity = tartetActivity;
    }

    @Override
    public void onClick(View v) {
        originActivity.startActivity(new Intent(originActivity, tartetActivity));
    }
}