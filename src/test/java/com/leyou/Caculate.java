package com.leyou;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leyou.entity.vo.ProductParams;
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

        List<Map<Long, String>> list = new ArrayList<>();
        list.add(map);

        Object obj = JSONObject.toJSON(list);

        String strr = "{\"4\": \"白色\", \"12\": \"3GB\", \"13\": \"16GB\"}";
        Map x2 = JSONObject.parseObject(strr, Map.class);
        List<ProductParams> productParamss = new ArrayList<>();
        System.out.println(x2);
        for (Object pid : x2.keySet()) {
            ProductParams productParams = new ProductParams();
            productParams.setId(Long.valueOf(pid.toString()));
            productParams.setValue((String) x2.get(pid));

            productParamss.add(productParams);
        }

        System.out.println(productParamss);

        List x1 = JSONObject.parseObject(obj.toString(), List.class);
        System.out.println(x1);
        System.out.println(JSONObject.parseObject(x1.get(0).toString(), Map.class));

    }

}
