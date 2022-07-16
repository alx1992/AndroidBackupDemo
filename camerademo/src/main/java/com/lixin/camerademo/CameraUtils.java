package com.lixin.camerademo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author LiXin
 * @date 2021/3/17 14:27
 * @description CameraUtils was created at  2021/3/17 14:27 by LiXin
 */
public class CameraUtils {
    private static final String TAG = "CameraUtils";
    /**
     * 全局上下文对象
     */
    @SuppressLint("StaticFieldLeak")
    private  static Context mContext;
    /**
     *
     */
    public static  final int CAMERA_REQUEST_CODE = 10001;
    /**
     * 用于保存拍照图片的uri
     */
    public static  Uri mCameraUri;

    /**
     *  用于保存图片的文件路径，Android 10以下使用图片路径访问图片
     */
    public static  String mCameraImagePath;

    /**
     * 是否是Android 10以上手机
     */
    public  static boolean isAndroidQ = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    /**
     * 私有化构造方法
     */
    private CameraUtils() {
    }
    /**
     * 静态内部类实例化
     */
    private static class CameraUtilsHolder {
        @SuppressLint("StaticFieldLeak")
        public static CameraUtils INSTANCE = new CameraUtils();
    }

    /**
     * 获取单例
     * @param context
     * @return
     */
    public static CameraUtils getInstance(Context context) {
        mContext = context.getApplicationContext();
        return CameraUtilsHolder.INSTANCE;
    }


    /**
     * 调起相机拍照
     */
    @SuppressLint("QueryPermissionsNeeded")
    public void openCamera(Activity activity) {
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        Uri photoUri = null;
        // 判断是否有相机
        if (captureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            if (isAndroidQ) {
                // 适配android 10
                photoUri = createImageUri();
                LogUtils.eTag(TAG, "适配android 10 openCamera photoUri " + photoUri);
            } else {
                try {
                    photoFile = createImageFile();
                    LogUtils.eTag(TAG, "适配android 10 以下 openCamera photoFile " + photoFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {
                    mCameraImagePath = photoFile.getAbsolutePath();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        photoUri = FileProvider.getUriForFile(mContext, getPackageName() + ".fileprovider", photoFile);
                        LogUtils.eTag(TAG, "适配android 10 以下 openCamera photoUri " + photoUri);
                    } else {
                        photoUri = Uri.fromFile(photoFile);
                    }
                }
            }
            mCameraUri = photoUri;
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                activity.startActivityForResult(captureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private Uri createImageUri() {
        String status = Environment.getExternalStorageState();
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            return mContext.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    /**
     * 创建保存图片的文件
     */
    private File createImageFile() throws IOException {
        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        File tempFile = new File(storageDir, imageName);
        if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
            return null;
        }
        return tempFile;
    }
    /**
     * [获取应用程序版本名称信息]
     * @return 当前应用的版本名称
     */
    private String getPackageName() {
        try {
            PackageManager packageManager = mContext.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
