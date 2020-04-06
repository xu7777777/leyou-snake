package com.leyou.entity.enums;


import java.util.HashMap;
import java.util.Map;

public enum GenericTypeEnums {

    GENERIC(1, "generic"),

    NOT_GENERIC(0, "not_generic");

    private Integer code;

    private String msg;

    GenericTypeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<Integer, GenericTypeEnums> map = new HashMap<>();

    static {
        GenericTypeEnums[] values = GenericTypeEnums.values();
        for (GenericTypeEnums value : values) {
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
