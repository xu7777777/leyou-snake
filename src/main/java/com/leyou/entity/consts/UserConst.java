package com.leyou.entity.consts;

public class UserConst {
    //用户登录的redis键
    public static final String TOKEN_KEY = "leyou_login_";

    //用户登录的有效期
    public static final Long EXPIRE_TIME = 60 * 60 * 24 * 180L;
}
