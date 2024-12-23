package com.example.util;

import java.time.Duration;

public class DurationToStringConverter {

    public static String convertToString(Duration duration) {
        long totalSeconds = duration.getSeconds();

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        StringBuilder result = new StringBuilder();
        if (hours > 0) {
            result.append(hours).append("小时");
        }

        if (minutes > 0) {
            result.append(minutes).append("分");
        }

        if (seconds > 0) {
            result.append(seconds).append("秒");
        }

        // 如果结果为空，返回"0秒"
        if (result.isEmpty()) {
            return "0秒";
        }

        return result.toString();
    }
}
