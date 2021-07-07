package com.snbc.timerestart;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class TimeChangeService extends Service {


    private TimeChangeReceiver mTimeChangeReceiver = null;

    @Override
    public void onCreate() {
        if (mTimeChangeReceiver == null) {
            mTimeChangeReceiver = new TimeChangeReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_TIME_TICK);
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
            registerReceiver(mTimeChangeReceiver, filter);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public TimeChangeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mTimeChangeReceiver);
        super.onDestroy();
    }
}
