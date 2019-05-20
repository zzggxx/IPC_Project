package com.example.reboot;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    static boolean mHaveRoot;
    private static String TAG = BootCompleteReceiver.class.getName();

    /*距离下一天凌晨的时间值*/
    public static long getNextDayLongSecond() {

        try {
            SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
            long now = System.currentTimeMillis();
            long overTime = now - (sdfOne.parse(sdfOne.format(now)).getTime());
            long nextDaySecond = 24 * 60 * 60 * 1000 - overTime;
            return nextDaySecond;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }


//    下一天的凌晨一点

    public static long getNextDayStartTime() {

        try {
            long now = System.currentTimeMillis();
            SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
            long time = sdfOne.parse(sdfOne.format(now)).getTime();
            long overTime = 0l;
            if (now > time && now < time + 1 * 60 * 60 * 1000) {
                overTime = time + 1 * 60 * 60 * 1000;
            } else {
                overTime = time + 25 * 60 * 60 * 1000;
            }
            return overTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

//        获取当前设定时间的任务的毫秒值
    public static Long getMillis(int hourOfDay, int minute) {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        int hour_of_day = calendar.get(Calendar.HOUR_OF_DAY);
        int minute1 = calendar.get(Calendar.MINUTE);

//        当前的小时数大于规定的小时数 或者 小时数相等并且当前分钟数大于等于设定分钟数   ------>  设定下一天的时间值

        if (hour_of_day > hourOfDay || (hour_of_day == hourOfDay && minute1 >= minute)) {

            calendar.add(Calendar.DATE, 1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            date = calendar.get(Calendar.DATE);
        }

        calendar.clear();
        calendar.set(year, month, date, hourOfDay, minute);

        return calendar.getTimeInMillis();
    }


    public static void reBoot() {
        if (Utils.haveRoot()) {
            try {
                Runtime.getRuntime().exec("su -c reboot");
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static boolean haveRoot() {
        if (!mHaveRoot) {
            int ret = execRootCmdSilent("echo test"); // 通过执行测试命令来检测
            if (ret != -1) {
                Log.i(TAG, "have root!");
                mHaveRoot = true;
            } else {
                Log.i(TAG, "not root!");
            }
        } else {
            Log.i(TAG, "mHaveRoot = true, have root!");
        }
        return mHaveRoot;
    }

    // 执行命令但不关注结果输出
    public static int execRootCmdSilent(String cmd) {
        int result = -1;
        DataOutputStream dos = null;

        try {
            Process p = Runtime.getRuntime().exec("su");
            dos = new DataOutputStream(p.getOutputStream());

            Log.i(TAG, cmd);
            dos.writeBytes(cmd + "\n");
            dos.flush();
            dos.writeBytes("exit\n");
            dos.flush();
            p.waitFor();
            result = p.exitValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
