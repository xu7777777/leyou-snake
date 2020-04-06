package com.leyou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu7777777
 * @date 2020/3/23 11:11 AM
 */
public class Caculate {

    @Test
    public void fun() {
        Map<Long, String> map = new HashMap<>();
        map.put(10L, "1213");
        map.put(11L, "113");

        String str = "{'10':'1212', '11':'1123'}";

        Map x = JSONObject.parseObject(str, Map.class);
        System.out.println(x.get("10"));



        System.out.println(JSONObject.toJSONString(map));
    }

}
