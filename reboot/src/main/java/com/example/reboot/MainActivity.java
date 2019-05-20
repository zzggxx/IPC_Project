package com.example.reboot;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private static int mTaskId = 0;
    private TextView mTvRestartTime;
    private TimeChangeReceiver mTimeChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRestartTime = (TextView) findViewById(R.id.tv_restart_time);

//        设定的重启时间
        String restart_time = SPUtils.getString(this, "restart_time", null);

        if (!TextUtils.isEmpty(restart_time)) {

//            需要拿时分来算,因为可能又是需要定下一个任务
            String[] split = restart_time.split(":");
            int hourOfDay = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);

            startGet(Utils.getMillis(hourOfDay, minute));

            mTvRestartTime.setText("重启时间_" + hourOfDay + ":" + minute);

        } else {

            long nextDayStartTime = Utils.getNextDayStartTime();
            startGet(nextDayStartTime);
            SPUtils.saveObject(MainActivity.this, "restart_time_millis", String.valueOf(nextDayStartTime));

        }

        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimerPicker();
            }
        });

        startService(new Intent(this,TimeChangeService.class));
//        mTimeChangeReceiver = new TimeChangeReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_TIME_TICK);
//        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
//        registerReceiver(mTimeChangeReceiver, filter, null, null);
    }

    private void showTimerPicker() {

        int hourOfDay1 = 01;
        int minute1 = 00;

        String restart_time = SPUtils.getString(this, "restart_time", null);
        if (!TextUtils.isEmpty(restart_time)) {

            String[] split = restart_time.split(":");
            hourOfDay1 = Integer.parseInt(split[0]);
            minute1 = Integer.parseInt(split[1]);
        }

        TimePickerDialog time = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                cancelGet();

                startGet(Utils.getMillis(hourOfDay, minute));

                mTvRestartTime.setText("重启时间_" + hourOfDay + ":" + minute);

                SPUtils.saveObject(MainActivity.this, "restart_time", hourOfDay + ":" + minute);
                SPUtils.saveObject(MainActivity.this, "restart_time_millis",
                        String.valueOf(Utils.getMillis(hourOfDay, minute)));

            }
        }, hourOfDay1, minute1, true);
        time.show();

    }

    private void startGet(long millis) {

        Intent i = new Intent(this, AlarmService.class);
        i.putExtra("alarm_time", millis);
        i.putExtra("task_id", mTaskId);
        startService(i);
    }

    private void cancelGet() {
        AlarmManagerUtil.cancelAlarmBroadcast(this, mTaskId,
                AlarmReceiver.class);
    }

    @Override
    protected void onDestroy() {
//        unregisterReceiver(mTimeChangeReceiver);
        super.onDestroy();
    }
}
