package com.example.reboot;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {

    public static void saveObject(Context context, String key, String vaule) {

        SharedPreferences sp = context.getSharedPreferences("reboot_task", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        if (vaule instanceof String) {
            edit.putString(key, vaule);
        }
        edit.commit();
    }

    public static String getString(Context context, String key, String defaultVaule) {

        SharedPreferences sp = context.getSharedPreferences("reboot_task", Context.MODE_PRIVATE);
        String string = sp.getString(key, defaultVaule);
        return string;
    }

}
