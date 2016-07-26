package edu.swu.pulltorefreshswipemenulistview.library.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenyk on 2016/6/23.
 * 工具类
 */
public class Utils {
    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static boolean isTouchInView(View view, MotionEvent event) {
        if (view == null || event == null)
            return false;
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int xGalleryStart = location[0];
        int yGalleryStart = location[1];
        int xGalleryEnd = xGalleryStart + view.getWidth();
        int yGalleryEnd = yGalleryStart + view.getHeight();
        int xEvent = (int) event.getRawX();
        int yEvent = (int) event.getRawY();
        boolean isTouch = (xEvent > xGalleryStart && xEvent < xGalleryEnd)
                && (yEvent > yGalleryStart && yEvent < yGalleryEnd);
        return isTouch;
    }

    /**
     * 获取当前时间，格式2016-12-13 14:21
     * @return
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .format(new Date());
    }

}
