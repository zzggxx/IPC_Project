package com.snbc.temptest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: zhougaoxiong
 * date: 2020/5/13,14:37
 * projectName:Temp
 * packageName:com.snbc.temptest
 */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.snbc.otaapp".equals(action)) {
            Toast.makeText(context, "发来了广播,我要开始更新了", Toast.LENGTH_SHORT).show();
            Log.i(TAG, System.currentTimeMillis() + "start:__onReceive: 自定义了闹钟");
            SPUtils.put(context, "on_receive" + System.currentTimeMillis(), get());
        }
    }

    public static String get() {
        long milliSecond = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(milliSecond);
        return new SimpleDateFormat().format(date);
    }
}
