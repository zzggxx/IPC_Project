package com.example.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 看详细步骤中有说明文档的
 */

public class RemoteService extends Service {

    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {
        @Override
        public int add(int i, int j) throws RemoteException {
            return i + j;
        }

        @Override
        public int subtract(int i, int j) throws RemoteException {
            return i - j;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
}
