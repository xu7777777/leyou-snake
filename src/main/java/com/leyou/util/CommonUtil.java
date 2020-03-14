package com.leyou.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

public class CommonUtil {
    /**
     * 获取32位uuid
     */
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    //获取异常的堆栈信息
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}
