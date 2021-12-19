package com.lixin.floatwindow;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.lixin.floatwindow.service.FloatWindowService;
import com.lixin.floatwindow.service.ForegroundService;


/**
 * @author Huanglinqing
 */
public class RemoteViewActivity extends AppCompatActivity {
    private static final String TAG = "RemoteView";
    private boolean hasBind = false;
    private FloatWindowService.MyBinder binder;
    private ServiceConnection mVideoServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取服务的操作对象
             binder = (FloatWindowService.MyBinder) service;
            binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder  = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        //启动前台服务
        //startForegroundService();
    }
    /**
     * 启动 前台服务 模仿微信
     */
    private void startForegroundService() {
        // 启动服务
        if (!ForegroundService.serviceIsLive) {
            // Android 8.0使用startForegroundService在前台启动新服务
            Intent mForegroundService = new Intent(this, ForegroundService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(mForegroundService);
            } else {
                startService(mForegroundService);
            }
        } else {
            LogUtils.dTag(TAG,"前台服务已经启动");
        }
    }
    /**
     * 缩小
     * @param v
     */
    public void zoom(View v) {
        if (DialogPermissionUtils.checkPermission(RemoteViewActivity.this)) {
            bindFloatWindowService();
            startActivity(new Intent(this,MainActivity.class));
        }else {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            new GlobalDialogSingle(this, "温馨提示", "当前未获取悬浮窗权限",
                    "去开启", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    DialogPermissionUtils.requestPermission(RemoteViewActivity.this);
                }
            }).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.eTag("RemoteView", "RemoteView重新显示了");
        if (DialogPermissionUtils.checkPermission(RemoteViewActivity.this)) {
            bindFloatWindowService();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.eTag("RemoteView", "RemoteView重新显示了");
        unBindFloatWindowService();
    }

    /**
     * 启动 前台服务 模仿微信
     */
    private void stopForegroundService() {
        // 停止服务
        if (ForegroundService.serviceIsLive) {
            Intent mForegroundService = new Intent(this, ForegroundService.class);
            stopService(mForegroundService);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.eTag("RemoteView", "RemoteView被销毁");
        stopForegroundService();
    }
    /**
     * 绑定 悬浮窗 服务
     */
    private void bindFloatWindowService() {
        moveTaskToBack(true);
        Intent intent = new Intent(RemoteViewActivity.this, FloatWindowService.class);
        hasBind = bindService(intent, mVideoServiceConnection, Context.BIND_AUTO_CREATE);
    }
    /**
     * 解绑 service
     */
    private void unBindFloatWindowService() {
        //不显示悬浮框
        if (hasBind){
            unbindService(mVideoServiceConnection);
            hasBind = false;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.eTag("RemoteView", "重新显示了onNewIntent");
    }


}
