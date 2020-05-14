package com.snbc.temp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(this);

        try {
            Context context = createPackageContext("com.excellence.weather", Context.CONTEXT_IGNORE_SECURITY);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                AlarmUtils.start(this);
                break;
            case R.id.btn_cancel:
                AlarmUtils.cancle();
                break;
            default:
                break;
        }
    }
}
