package com.example.chenyk.chenyknotes.Utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by chenyk on 2016/6/28.
 * Toast工具类
 */
public class ToastUtil {
    /**
     * 原生Toast短显示
     *
     * @param activity
     * @param tips
     */
    public static void showNativeShortToast(Activity activity, String tips) {
        Toast.makeText(activity, tips, Toast.LENGTH_SHORT).show();
    }
    /**
     * 原生Toast长显示
     *
     * @param activity
     * @param tips
     */
    public static void showNativeLongToast(Activity activity, String tips) {
        Toast.makeText(activity, tips, Toast.LENGTH_LONG).show();
    }
}
