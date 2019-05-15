package com.example.reboot;

import android.app.AlarmManager;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Long alarmDateTime = intent.getLongExtra("alarm_time", 0L);

        // taskId用于区分不同的任务
        int taskId = intent.getIntExtra("task_id", 0);

        Log.d("AlarmService", "executed at " + new Date().toString()
                + " @Thread id：" + Thread.currentThread().getId());

//        long alarmDateTimeMillis = DateTimeUtil.stringToMillis(alarmDateTime);

        AlarmManagerUtil.sendAlarmBroadcast(this, taskId,
                AlarmManager.RTC_WAKEUP , alarmDateTime, AlarmReceiver.class);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Destroy", "Alarm Service Destroy");
    }

}
