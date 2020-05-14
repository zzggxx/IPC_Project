package com.snbc.temp;

/**
 * author: zhougaoxiong
 * date: 2020/5/14,16:21
 * projectName:Temp
 * packageName:com.snbc.temp
 */
public class RandomTimeUtil {

    /**
     * @param time format eg:13:00
     * @return
     */
    public String getRandomTime(String time) {
        String[] split = time.split(":");
        int i = (int) (Math.random() * 60);
        String randomTime = String.valueOf(i);
        if (i <= 9) {
            randomTime = "0" + i;
        }
        return split[0] + ":" + randomTime;
    }

}
