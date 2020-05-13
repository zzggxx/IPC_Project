package com.snbc.temptest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
            Log.i(TAG, System.currentTimeMillis() / 1000 + "__onReceive: 自定义了闹钟");
        }
    }
}
