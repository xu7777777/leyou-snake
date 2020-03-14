package com.leyou.util;


import com.leyou.entity.vo.Output;

public class OutputUtil {

    //成功
    public static <K> Output<K> ok(K data) {
        Output<K> output = new Output<>();
        output.setCode(0);
        output.setMsg("");
        output.setData(data);
        return output;
    }

    //失败
    public static <K> Output<K> fail(int code, String msg) {
        Output<K> output = new Output<>();
        output.setCode(code);
        output.setData(null);
        output.setMsg(msg);
        return output;
    }

    //是否成功
    public static boolean isOk(int code) {
        if (code == 0) {
            return true;
        } else {
            return false;
        }
    }

}
