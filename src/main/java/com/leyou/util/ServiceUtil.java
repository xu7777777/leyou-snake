package com.leyou.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: sijie_bops
 * @description: ${description}
 * @author: zhanglijie 服务层公用
 * @date: 2020-02-26 14:45
 **/
public class ServiceUtil {


    // 解析图片地址
    public static String getImg(String img) {
        String result = "";
        if (StringUtils.isNotBlank(img)) {
            String[] imgs = img.split(",");
            for (String str : imgs) {
                if (StringUtils.isBlank(result)) {
                    result = str;
                } else {
                    result = result + ";;" + str;
                }
            }
        }
        return result;
    }

}
