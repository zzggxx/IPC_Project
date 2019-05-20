package com.example.reboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class TimeChangeReceiver extends BroadcastReceiver {

    private static int mTaskId = 0;
    private static final String TAG = TimeChangeReceiver.class.getName();
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {

        mContext = context;
        Log.i(TAG, "onReceive: time is change---------" + System.currentTimeMillis());

        cancelGet();
        //        设定的重启时间
        String restart_time = SPUtils.getString(context, "restart_time", null);

        if (!TextUtils.isEmpty(restart_time)) {

//            需要拿时分来算,因为可能又是需要定下一个任务
            String[] split = restart_time.split(":");
            int hourOfDay = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);

            startGet(Utils.getMillis(hourOfDay, minute));

        } else {

            long nextDayStartTime = Utils.getNextDayStartTime();
            startGet(nextDayStartTime);
            SPUtils.saveObject(mContext, "restart_time_millis", String.valueOf(nextDayStartTime));

        }

    }

    private void startGet(long millis) {

        Intent i = new Intent(mContext, AlarmService.class);
        i.putExtra("alarm_time", millis);
        i.putExtra("task_id", mTaskId);
        mContext.startService(i);
    }

    private void cancelGet() {
        AlarmManagerUtil.cancelAlarmBroadcast(mContext, mTaskId,
                AlarmReceiver.class);
    }
}
