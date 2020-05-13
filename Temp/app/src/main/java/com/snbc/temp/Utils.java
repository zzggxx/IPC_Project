package com.snbc.temp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * author: zhougaoxiong
 * date: 2020/5/13,17:32
 * projectName:Temp
 * packageName:com.snbc.temp
 */
public class Utils {

    private static final String TAG = Utils.class.getName();

    public static String getProperty(String key, String defaultValue) {

        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return value;
        }
    }

    public static void start(Context context) {

        Intent intent = new Intent();
        intent.setAction("com.snbc.otaapp");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);

//        setTime
//        加入是每日02:00  关机在01:00和关机在02:30是不一样的
        String checkUpdateTimeSetting = getProperty("persist.check.update.time.setting", "00:00");
        String[] split = checkUpdateTimeSetting.split(":");

//        当天0点的毫秒值
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long zero = calendar.getTimeInMillis();

//        指定时间的毫秒值
//        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(split[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(split[1]));
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
        long settingTimesMills = calendar.getTimeInMillis();
        Log.i(TAG, "start:_" + settingTimesMills);

        long currentTimeMillis = System.currentTimeMillis();
        Log.i(TAG, "start:_" + currentTimeMillis);

        long time = currentTimeMillis - settingTimesMills;
        if (time > 0) {
        } else {
        }

//        everyday send broadcast to checkout update.
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (3 * 1000), 24 * 60 * 60 * 1000, pendingIntent);
        Log.i(TAG, "start: 设置的系统的闹钟");
    }

}
