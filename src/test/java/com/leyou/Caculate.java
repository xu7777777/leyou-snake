package com.leyou;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu7777777
 * @date 2020/3/23 11:11 AM
 */
public class Caculate {

    @Test
    public void fun() {
        List<Long> list01 = new ArrayList<>();
        list01.add(0L);
        list01.add(-1L);
        list01.add(1L);
        list01.add(65535L);
        list01.add(65534L);
        list01.add(65536L);

        for (Long num01 : list01) {
            for (Long num02 : list01) {
                    System.out.println(num01 + " / " + num02 + "       应等于    " + (num01 / num02));

            }
        }
    }

}
