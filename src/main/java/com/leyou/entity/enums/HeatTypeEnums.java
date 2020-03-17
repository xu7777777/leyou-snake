package com.leyou.entity.enums;


import java.util.HashMap;
import java.util.Map;

public enum HeatTypeEnums {

    // brand
    BRAND(1, "brand"),

    // goods
    GOODS(2, "goods");

    private Integer code;

    private String msg;

    HeatTypeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<Integer, HeatTypeEnums> map = new HashMap<>();

    static {
        HeatTypeEnums[] values = HeatTypeEnums.values();
        for (HeatTypeEnums value : values) {
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
