package com.example.zhougaoxiong.ipc_project;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.remoteservice.IMyAidlInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private IMyAidlInterface mStub;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            远程进程的引用
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

//        演示通能1,一个进程开启多个进程
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

//        演示功能2,调用远程服务
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

//        演示功能3,文件共享的方式IPC
        saveBookToFile();
    }

    private void saveBookToFile() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String filePath = getExternalCacheDir().getAbsolutePath() + File.separator + "Book";
                File file = new File(filePath);
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream(file));
                    outputStream.writeObject(new Book(1, "25"));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
