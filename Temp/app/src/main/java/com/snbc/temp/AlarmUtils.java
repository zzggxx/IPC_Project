package com.snbc.temp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.Calendar;

import android.os.SystemProperties;

import static android.content.Context.ALARM_SERVICE;

/**
 * author: zhougaoxiong
 * date: 2020/5/13,17:32
 * projectName:Temp
 * packageName:com.snbc.temp
 */
public class AlarmUtils {

    private static final int oneDayMillis = 1000 * 60 * 60 * 24;
    private static final int minUpdateMillis = 1000 * 60 * 10;
    private static PendingIntent mPendingIntent;
    private static AlarmManager mAm;

    public static void start(Context context) {

        Intent intent = new Intent();
        intent.setAction("com.snbc.otaapp");
        mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        mAm = (AlarmManager) context.getSystemService(ALARM_SERVICE);

//        setTime
//        加入是每日02:00  关机在01:00和关机在02:30是不一样的
        String checkUpdateTimeSetting = SystemProperties.get("persist.check.update", "01:18");
        if (TextUtils.isEmpty(checkUpdateTimeSetting)) {
            checkUpdateTimeSetting = SystemProperties.get("persist.check.update.snbc", "01:00");
            checkUpdateTimeSetting = getRandomTime(checkUpdateTimeSetting);
            SystemProperties.set("persist.check.update", checkUpdateTimeSetting);
        }

        String[] split = checkUpdateTimeSetting.split(":");

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
        } else if (time < minUpdateMillis) {
//            没有过设定时间的前十分钟之内,则在本次进行更新
            time = -time;
        }

//        everyday send broadcast to checkout update.
        mAm.setRepeating(AlarmManager.RTC_WAKEUP, currentTimeMillis + time, oneDayMillis, mPendingIntent);
//        Log.i(TAG, "start: 设置的系统的闹钟;延时时间:" + time + ";间隔时间:" + /*oneDayMillis*/testTime);
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
        }
    }

}
