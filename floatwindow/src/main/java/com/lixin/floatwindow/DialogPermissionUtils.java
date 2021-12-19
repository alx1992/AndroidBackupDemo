package com.lixin.floatwindow;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;
import com.lixin.floatwindow.utils.MiuiUtils;
import com.lixin.floatwindow.utils.RomUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DialogPermissionUtils {

    private static final String TAG = "DialogPermissionUtils";
    /**
     * 悬浮窗权限判断
     *
     * @param context 上下文
     * @return [ true, 有权限 ][ false, 无权限 ]
     */
    public static boolean checkPermission(Context context) {
        Boolean hasPermission = false;
        if (Build.VERSION.SDK_INT < 19) {
            hasPermission = true;
            LogUtils.eTag(TAG, "< 19: hasPermission" + hasPermission);
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 23) {
            if (
                    RomUtils.checkIsMiuiRom() ||
                    RomUtils.checkIsMeizuRom() ||
                    RomUtils.checkIsHuaweiRom() ||
                    RomUtils.checkIs360Rom()

            || MiuiUtils.checkFloatWindowPermission(context)) {// 特殊机型
                hasPermission = opPermissionCheck(context, 24);
                LogUtils.eTag(TAG, "特殊机型 >= 19 < 23: hasPermission" + hasPermission);
            } else {// 其他机型
                hasPermission = true;
                LogUtils.eTag(TAG, ">= 19 < 23: hasPermission" + hasPermission);
            }
        } else if (Build.VERSION.SDK_INT >= 23) {// 6.0 版本之后由于 google 增加了对悬浮窗权限的管理，所以方式就统一了
            hasPermission = highVersionPermissionCheck(context);
            LogUtils.eTag(TAG, " >= 23:hasPermission " + hasPermission);
        }
        return hasPermission;
    }
    /**
     * [19-23]之间版本通过[AppOpsManager]的权限判断
     *
     * @param context 上下文
     * @param op
     * @return [ true, 有权限 ][ false, 无权限 ]
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean opPermissionCheck(Context context, int op) {
        try {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Class clazz = AppOpsManager.class;
            Method method = clazz.getDeclaredMethod("checkOp", int.class, int.class, String.class);
            return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
        } catch (Exception e) {
            LogUtils.eTag(Log.getStackTraceString(e));
        }
        return false;
    }

    /**
     * Android 6.0 版本及之后的权限判断
     *
     * @param context 上下文
     * @return [ true, 有权限 ][ false, 无权限 ]
     */
    public static boolean highVersionPermissionCheck(Context context) {
        try {
            Class clazz = Settings.class;
            Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
            if (MiuiUtils.getMiuiVersion()==10) {

            }
            return (Boolean) canDrawOverlays.invoke(null, context);
        } catch (Exception e) {
            LogUtils.eTag(Log.getStackTraceString(e));
        }
        return false;
    }
    /**
     * Android 6.0 版本及之后的跳转权限申请界面
     *
     * @param context 上下文
     */
    public static void highVersionJump2PermissionActivity(Context context) {
        try {
            Class clazz = Settings.class;
            Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");
            Intent intent = new Intent(field.get(null).toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtils.eTag(TAG, Log.getStackTraceString(e));
        }
    }
    /**
     * 跳转应用详情界面
     *
     * @param context
     */
    public static void jump2DetailActivity(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }
    /**
     * 请求悬浮窗权限
     *
     * @param context 上下文
     */
    public static void requestPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 23) {
            jump2DetailActivity(context);
            LogUtils.eTag(TAG, "jump2DetailActivity: ");
        } else if (Build.VERSION.SDK_INT >= 23) {
            highVersionJump2PermissionActivity(context);
            LogUtils.eTag(TAG, "highVersionJump2PermissionActivity: ");
        }
    }
}
