package com.snbc.temp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static android.content.Intent.ACTION_BOOT_COMPLETED;

/**
 * author: zhougaoxiong
 * date: 2020/5/13,14:27
 * projectName:Temp
 * packageName:com.snbc.temp
 */
public class ReStartCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION_BOOT_COMPLETED.equals(action)) {
//            重新定时闹钟
//            new Timer()
            Utils.start(context);
        }
    }

}
