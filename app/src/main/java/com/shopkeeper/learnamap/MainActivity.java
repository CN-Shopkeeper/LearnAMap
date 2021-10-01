package com.shopkeeper.learnamap;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.shopkeeper.learnamap.createMap.CreateMapActivity;
import com.shopkeeper.learnamap.databinding.ActivityMainBinding;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends RedirectActivity {

    private final String[] names = {"创建地图"};
    private final Class<?>[] classes = {CreateMapActivity.class};
    private ActivityMainBinding binding;

    //如果设置了target > 28，需要增加这个权限，否则不会弹出"始终允许"这个选择框
    private static final String BACK_LOCATION_PERMISSION = "android.permission.ACCESS_BACKGROUND_LOCATION";
    private static final int PERMISSION_REQUEST_CODE = 0;
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            BACK_LOCATION_PERMISSION
    };

    @Override
    protected String[] getNames() {
        return names;
    }

    @Override
    protected Class<?>[] getClasses() {
        return classes;
    }

    @Override
    protected LinearLayout getLinearLayout() {
        return binding.linearLayout;
    }

    //是否需要检测后台定位权限，设置为true时，如果用户没有给予后台定位权限会弹窗提示
    private boolean needCheckBackLocation = false;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
        if (Build.VERSION.SDK_INT > 28
                && getApplicationContext().getApplicationInfo().targetSdkVersion > 28) {
            needPermissions = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE,
                    BACK_LOCATION_PERMISSION
            };
            needCheckBackLocation = true;
        }
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @TargetApi(23)
    private void checkPermissions(String... permissions) {
        try {
            if (getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissionList = findDeniedPermissions(permissions);
                if (null != needRequestPermissionList
                        && needRequestPermissionList.size() > 0) {
                    try {
                        String[] array = needRequestPermissionList.toArray(new String[0]);
                        Method method = getClass().getMethod("requestPermissions", String[].class, int.class);
                        method.invoke(this, array, 0);
                    } catch (Throwable ignored) {

                    }
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     */
    @TargetApi(23)
    private List<String> findDeniedPermissions(String[] permissions) {
        try {
            List<String> needRequestPermissionList = new ArrayList<>();
            if (getApplicationInfo().targetSdkVersion >= 23) {
                for (String perm : permissions) {
                    if (checkMySelfPermission(perm) != PackageManager.PERMISSION_GRANTED
                            || shouldShowMyRequestPermissionRationale(perm)) {
                        if (!needCheckBackLocation
                                && BACK_LOCATION_PERMISSION.equals(perm)) {
                            continue;
                        }
                        needRequestPermissionList.add(perm);
                    }
                }
            }
            return needRequestPermissionList;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer checkMySelfPermission(String perm) {
        try {
            Method method = getClass().getMethod("checkSelfPermission", String.class);
            return (Integer) method.invoke(this, perm);
        } catch (Throwable ignored) {
        }
        return -1;
    }

    private Boolean shouldShowMyRequestPermissionRationale(String perm) {
        try {
            Method method = getClass().getMethod("shouldShowRequestPermissionRationale", String.class);
            return (Boolean) method.invoke(this, perm);
        } catch (Throwable ignored) {
        }
        return false;
    }

    /**
     * 检测是否说有的权限都已经授权
     */
    private boolean verifyPermissions(int[] grantResults) {
        try {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return true;
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] paramArrayOfInt) {
        super.onRequestPermissionsResult(requestCode, permissions, paramArrayOfInt);
        try {
            if (requestCode == PERMISSION_REQUEST_CODE) {
                if (!verifyPermissions(paramArrayOfInt)) {
                    showMissingPermissionDialog();
                    isNeedCheck = false;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示提示信息
     */
    private void showMissingPermissionDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("当前应用缺少必要权限。\\n\\n请点击\\\"设置\\\"-\\\"权限\\\"-打开所需权限");

            // 拒绝, 退出应用
            builder.setNegativeButton("取消",
                    (dialog, which) -> {
                        try {
                            finish();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    });

            builder.setPositiveButton("设置",
                    (dialog, which) -> {
                        try {
                            startAppSettings();
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    });

            builder.setCancelable(false);

            builder.show();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        try {
            Intent intent = new Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}