package com.lixin.commonlib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;




public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(lifecycleCallbacks);


    }

    /**
     * 监听 activity 生命周期
     */
    ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            LogUtils.eTag(TAG, "Activity TaskId: " + activity.getTaskId() + "\nActivity Name: " + activity.getLocalClassName() + "\nActivity: " + activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            //LogUtils.eTag(TAG, "onActivityStarted: "  + activity);
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            //LogUtils.eTag(TAG, "onActivityResumed: " + activity);
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
            //LogUtils.eTag(TAG, "onActivityPaused: " + activity);
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            //LogUtils.eTag(TAG, "onActivityStopped: " + activity);
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            //LogUtils.eTag(TAG, "onActivitySaveInstanceState: " + activity);
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            LogUtils.eTag(TAG, "Activity TaskId: " + activity.getTaskId() + "\nActivity Name: " + activity.getLocalClassName() + "\nActivity: " + activity);
        }
    };



}
