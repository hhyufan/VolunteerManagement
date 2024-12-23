package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringConverter {
    public static String convertToString(Date date) {
        // 创建 SimpleDateFormat 实例，设置所需的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // 格式化 Date 对象为字符串
        return sdf.format(date);
    }
}
