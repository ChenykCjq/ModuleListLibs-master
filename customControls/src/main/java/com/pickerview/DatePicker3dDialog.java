package com.pickerview;

import android.content.Context;

import com.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenyk on 2016/6/16.
 * 高仿IOS中的时间选择器
 */
public abstract class DatePicker3dDialog {
    TimePickerView pvTime;

    /**
     * @param context
     * @param date    格式：yyyy-MM-dd
     */
    public DatePicker3dDialog(Context context, String date) {
        // 时间选择器
        pvTime = new TimePickerView(context, TimePickerView.Type.YEAR_MONTH_DAY);
        // 控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 120, calendar.get(Calendar.YEAR) + 100);
        pvTime.setTime(date);
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        // 时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(date);
                onConfirm(dateStr);
            }
        });
        pvTime.show();
    }

    public abstract void onConfirm(String date);
}
