package com.example.reboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive: start  reboot");
        Utils.reBoot();
//        SPUtils.saveObject(context, DateTimeUtil.getCurrentDateTimeString());
    }
}
