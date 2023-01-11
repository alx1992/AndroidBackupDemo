package com.a.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.util.Date;

/**
 * @ClassName: MyApplication
 * @Description: 作用描述：
 * @Author: alx1992
 * @CreateDate: 2022/7/7 09:51
 * @UpdateUser: 更新者：
 * @UpdateDate: 2022/7/7 09:51
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "MyApplication";
    private static final String LOG_DIR = "log_dir";
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivityCreated");
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivityStarted");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivityResumed");
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivityPaused");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        LogUtils.dTag(TAG,activity.getLocalClassName() + " onActivityDestroyed");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getConfig().setLogSwitch(true)// 设置 log 总开关
                .setGlobalTag("basic")// 设置 log 全局 tag
                .setGlobalTag("AndroidDemo")// 设置tag起始标识
                .setLog2FileSwitch(true)// 设置文件写入
                .setDir(getLogDir())// 设置文件保存路径
                .setConsoleSwitch(true)// 控制台日志
                .setLogHeadSwitch(true)// 头信息（调用位置，具体到方法名和行数）
                .setBorderSwitch(true)// 边框
                ;// 设置文件前缀

        registerActivityLifecycleCallbacks(this);

    }
    /**
     * 获取log日志文件夹
     *
     */
    public  String getLogDir() {
        String logDir;
        final String FILE_SEP = System.getProperty("file.separator");
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && this.getExternalCacheDir() != null) {
            logDir = this.getExternalCacheDir() + FILE_SEP + LOG_DIR + FILE_SEP
                    + TimeUtils.date2String(new Date(),"HH:mm:ss") + FILE_SEP;
        } else {
            logDir = this.getCacheDir() + FILE_SEP + LOG_DIR + FILE_SEP
                    + TimeUtils.date2String(new Date(),"HH:mm:ss") + FILE_SEP;
        }
        LogUtils.dTag(TAG,logDir);
        return logDir;
    }
}
