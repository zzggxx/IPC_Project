package com.snbc.temptest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        get();
    }

    public static void get() {
        long milliSecond = System.currentTimeMillis();
        Date date = new Date();
        date.setTime(milliSecond);
        System.out.println(new SimpleDateFormat().format(date));
    }
}