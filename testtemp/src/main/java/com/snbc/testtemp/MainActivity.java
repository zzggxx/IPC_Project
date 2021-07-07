package com.snbc.testtemp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.snbc.testtemp.bean.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

        mFilePath = getFilesDir().getAbsoluteFile() + File.separator + "test.txt";
        File file = new File(mFilePath);
        Log.i(TAG, "onCreate: file path: " + file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                User will = new User(1, 18, "will");
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mFilePath));
                    outputStream.writeObject(will);
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                try {
                    try {
                        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(mFilePath));
                        User user1 = (User) inputStream.readObject();
                        Log.i(TAG, "onClick: " + user1.name);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
