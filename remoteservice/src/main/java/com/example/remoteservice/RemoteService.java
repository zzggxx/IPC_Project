package com.example.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.remoteservice.aidltojava.IMyAidlInterface;

/**
 * 看详细步骤中有说明文档的
 */

public class RemoteService extends Service {

//    这一这个接口的巧妙之处,外层接口的抽象方法需要实现,内部类是自动生成的
    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {

//        以下两个方式是 IMyAidlInterface这个接口中的抽象方法
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
