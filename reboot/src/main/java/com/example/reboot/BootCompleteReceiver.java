package com.example.reboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = BootCompleteReceiver.class.getName();

    // 模拟的task id
    private static int mTaskId = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Log.i(TAG, "onReceive: phone reboot completed");


            SPUtils.saveObject(context, "phone_reboot_complete", String.valueOf(System.currentTimeMillis()));

            String restart_time = SPUtils.getString(context, "restart_time", null);
            if (!TextUtils.isEmpty(restart_time)) {

                String[] split = restart_time.split(":");
                int hourOfDay = Integer.parseInt(split[0]);
                int minute = Integer.parseInt(split[1]);

                startGet(context, Utils.getMillis(hourOfDay, minute));

            } else {
                startGet(context, Utils.getNextDayStartTime());
            }
        }
    }

    private void startGet(Context context, long millis) {

        Intent i = new Intent(context, AlarmService.class);
        i.putExtra("alarm_time", millis);
        i.putExtra("task_id", mTaskId);
        context.startService(i);
    }

}
