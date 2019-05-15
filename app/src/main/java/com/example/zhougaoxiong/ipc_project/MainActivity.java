package com.example.zhougaoxiong.ipc_project;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.remoteservice.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private IMyAidlInterface mStub;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mStub = IMyAidlInterface.Stub.asInterface(service);
            if (mStub == null) {
                Log.i(TAG, "onServiceConnected: the stub is null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        //                启动服务
        Intent intent = new Intent();
        intent.setAction("com.example.remoteservice");
        intent.setPackage("com.example.remoteservice");
        bindService(intent, conn, BIND_AUTO_CREATE);

        final TextView textView = (TextView) findViewById(R.id.tv);
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                调用方法
                try {
                    int add = mStub.add(1, 2);
                    textView.setText(String.valueOf(add));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
