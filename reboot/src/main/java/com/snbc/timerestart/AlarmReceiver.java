package com.snbc.timerestart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = AlarmReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "onReceive: start  reboot");

//        验证时间是否相符合,不符合的话直接cancel
        String restart_time_millis = SPUtils.getString(context, "restart_time_millis", null);

        if (!TextUtils.isEmpty(restart_time_millis)) {
            long restart_time_l = Long.parseLong(restart_time_millis);
            if (restart_time_l <= (System.currentTimeMillis() + 1 * 60 * 1000) &&
                    restart_time_l >= (System.currentTimeMillis() - 1 * 60 * 1000)) {
                Utils.reBoot();
            } else {
                Log.i(TAG, "onReceive: the task time is overtime,beause the system is change");
            }
        }
    }
}
