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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRestartTime = (TextView) findViewById(R.id.tv_restart_time);

        String restart_time = SPUtils.getString(this, "restart_time", null);
        if (!TextUtils.isEmpty(restart_time)) {

            String[] split = restart_time.split(":");
            int hourOfDay = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);

            startGet(Utils.getMillis(hourOfDay, minute));

            mTvRestartTime.setText("重启时间_" + hourOfDay + ":" + minute);

        } else {
            startGet(Utils.getNextDayStartTime());
        }

        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimerPicker();
            }
        });
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

}
