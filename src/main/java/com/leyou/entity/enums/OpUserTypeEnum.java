package com.leyou.entity.enums;


import java.util.HashMap;
import java.util.Map;

public enum OpUserTypeEnum {

    // admin
    ADMIN(2, "admin"),

    // normal
    EDITOR(1, "editor");

    private Integer code;

    private String msg;

    OpUserTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<Integer, OpUserTypeEnum> map = new HashMap<>();

    static {
        OpUserTypeEnum[] values = OpUserTypeEnum.values();
        for (OpUserTypeEnum value : values) {
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
