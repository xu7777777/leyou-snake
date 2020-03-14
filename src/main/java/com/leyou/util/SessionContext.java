package com.leyou.util;

import com.alibaba.fastjson.JSONObject;
import com.leyou.entity.consts.UserConst;
import com.leyou.entity.dto.TbUser;
import org.apache.commons.lang3.StringUtils;

/**
 * session上下文
 */
public class SessionContext {

    private static final ThreadLocal<String> threadLocalToken = new ThreadLocal<>();

    /**
     * 获取当前用户AccessToken
     */
    public static String getCurrentToken() {
        return threadLocalToken.get();
    }

    /**
     * 设置当前用户accessToken到session上下文
     * @param accessToken
     */
    public static void setCurrentToken(String accessToken) {
        threadLocalToken.set(accessToken);
    }

    /**
     * 清除token
     */
    public static void clearContext() {
        threadLocalToken.remove();
    }

    /**
     * 获取当前用户信息,可能为null
     */
    public static TbUser getCurrentUser() {
        //获取当前token
        String curtAccessToken = getCurrentToken();
        //获取当前用户
        return getAccountByToken(curtAccessToken);
    }

    /**
     * 根据token获取用户
     * @param accessToken token mobile
     * @return 用户
     */
    public static TbUser getAccountByToken(String accessToken) {
        if (StringUtils.isNotEmpty(accessToken)) {
            RedisUtil redisUtil = SpringContextUtils.getBean("redisUtil", RedisUtil.class);
            String userStr =  redisUtil.get(UserConst.TOKEN_KEY + accessToken);
            return JSONObject.parseObject(userStr, TbUser.class);
        }
        return null;
    }

    /**
     * 是否正常用户,条件  1.accessToken不为空  2.缓存中存在
     */
    public static boolean isNormalUser(String accessToken) {
        if (StringUtils.isEmpty(accessToken) || getAccountByToken(accessToken) == null) {
            return false;
        }
        return true;
    }

}
