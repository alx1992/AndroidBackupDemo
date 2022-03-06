package com.lixin.camerademo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;


import java.util.List;

/**
 * @author LiXin
 * @date
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(v -> {
            requestPermission();
        });
    }

    /**
     *
     */
    public void requestPermission(){
        PermissionUtils
                .permission(
                        PermissionConstants.CAMERA,
                        PermissionConstants.STORAGE)
                .rationale((activity, shouldRequest) -> {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setTitle("请您同意相机和存储权限，否则应用无法使用");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            (dialog, which) -> shouldRequest.again(true));
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                            (dialog, which) -> shouldRequest.again(false));
                    alertDialog.show();
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(@NonNull List<String> granted) {
                        LogUtils.eTag(TAG, "通过的权限onGranted: " + granted);
                        CameraUtils.getInstance(MainActivity.this).openCamera(MainActivity.this);
                    }

                    @Override
                    public void onDenied(@NonNull List<String> deniedForever, @NonNull List<String> denied) {
                        LogUtils.eTag(TAG, "拒绝的onDenied: " + denied);
                    }
                })
                .request();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraUtils.CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (CameraUtils.isAndroidQ) {
                    // Android 10 使用图片uri加载
                   LogUtils.eTag(TAG, "onActivityResult: is AndroidQ");
                   Log.d(TAG, "AndroidQ path="+CameraUtils.mCameraUri.getPath());
                } else {
                    // 使用图片路径加载
                    LogUtils.eTag(TAG, "onActivityResult: is not AndroidQ");
                    Log.d(TAG, "path="+CameraUtils.mCameraUri.getPath());
                }
            } else {
                Toast.makeText(this,"取消",Toast.LENGTH_LONG).show();
            }
        }
    }
}