package com.leyou.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {

    NORMAL(1, "上架"),

    ABNORMAL(-1, "下架");

    private Integer code;

    private String msg;

    StatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<Integer, StatusEnum> map = new HashMap<>();

    static {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum value : values) {
            map.put(value.getCode(), value);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
