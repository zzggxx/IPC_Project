package com.example.zhougaoxiong.ipc_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, Main3Activity.class));
            }
        });

        getObjectFromFile();
    }

    private void getObjectFromFile() {
        File file = new File(getExternalCacheDir().getAbsolutePath() + File.separator + "Book");
        if (file.exists()) {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                Book book = (Book) inputStream.readObject();
                Log.i(TAG, "getObjectFromFile: " + book.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
