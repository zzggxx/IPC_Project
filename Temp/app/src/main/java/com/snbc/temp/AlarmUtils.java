package com.snbc.temp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * author: zhougaoxiong
 * date: 2020/5/13,17:32
 * projectName:Temp
 * packageName:com.snbc.temp
 */
public class AlarmUtils {

    private static final String TAG = AlarmUtils.class.getName();
    //    private static final int oneDayMillis = 1000 * 60 * 60 * 24;
    private static final int oneDayMillis = 1000 * 60 * 7;
    private static final int minUpdateMillis = 1000 * 60 * 5;
    private static PendingIntent mPendingIntent;
    private static AlarmManager mAm;

    public static void start(Context context) {

        Intent intent = new Intent();
        intent.setAction("com.snbc.otaapp11");
        mPendingIntent = PendingIntent.getBroadcast(context, 99, intent, 0);
        mAm = (AlarmManager) context.getSystemService(ALARM_SERVICE);

//        setTime
//        加入是每日02:00  关机在01:00和关机在02:30是不一样的
//        String installTimeSetting = Utils.getProperty("persist.install.time", "02:00");
        String installTimeSetting = "13:30";
//        String checkUpdateTimeSetting = SystemProperties.get("persist.check.update", "");
        if (TextUtils.isEmpty(installTimeSetting)) {
//            installTimeSetting = SystemProperties.get("persist.check.update.snbc", "01:00");
//            installTimeSetting = getRandomTime(installTimeSetting);
//            SystemProperties.set("persist.check.update", installTimeSetting);
        }

        String[] split = installTimeSetting.split(":");

        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

//        指定时间的毫秒值
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(split[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(split[1]));
        long settingTimesMills = calendar.getTimeInMillis();
        long currentTimeMillis = System.currentTimeMillis();

        long time = currentTimeMillis - settingTimesMills;
        if (time <= 0 && time >= -minUpdateMillis) {
//            当前时间已过设定的时间的前十分钟,那么本次将不予更新,更新在下一次
            time = settingTimesMills + oneDayMillis - currentTimeMillis;
            Log.i(TAG, "设置的系统的闹钟:下一次");
        } else if (time < minUpdateMillis) {
//            没有过设定时间的前十分钟之内,则在本次进行更新
            time = -time;
            Log.i(TAG, "设置的系统的闹钟:本次");
        }

//        everyday send broadcast to checkout update.
        mAm.setRepeating(AlarmManager.RTC_WAKEUP, currentTimeMillis + time, oneDayMillis, mPendingIntent);
        Log.i(TAG, "start: 设置的系统的闹钟:定时安装;延时时间:" + installTimeSetting + "  " + time + " " + (currentTimeMillis + time) + ";间隔时间:" + oneDayMillis);
    }

    /**
     * @param time format eg:13:00
     * @return
     */
    public static String getRandomTime(String time) {
        String[] split = time.split(":");
        int i = (int) (Math.random() * 60);
        String randomTime = String.valueOf(i);
        if (i <= 9) {
            randomTime = "0" + i;
        }
        return split[0] + ":" + randomTime;
    }

    public static void cancle() {
        if (mPendingIntent != null) {
            mAm.cancel(mPendingIntent);
            Log.i(TAG, "cancle: 设置的系统的闹钟:闹钟取消");
        }
    }

}
