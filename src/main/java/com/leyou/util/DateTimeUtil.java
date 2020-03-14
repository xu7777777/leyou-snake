package com.leyou.util;

import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateTimeUtil {

    public static final List<String> simpleDateFormat = Lists.newArrayList();
    static {
        simpleDateFormat.add("yyyy-MM-dd");
        simpleDateFormat.add("yyyy年MM月dd日");
        simpleDateFormat.add("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.add("yy/MM/dd HH:mm:ss");
        simpleDateFormat.add("yyyy/MM/dd");
        simpleDateFormat.add("yyyy年MM月dd日 HH时mm分ss秒");
        simpleDateFormat.add("yyyyMMddHHmmssSSS");
    }

    /**
     * 时间格式转换
     * @param dateTime
     * @param dateFormat
     * @return
     */
    public static String formatBySim(LocalDateTime dateTime, String dateFormat) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(dateFormat);
        return sdf.format(dateTime);
    }

    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dateTime);
    }
}
