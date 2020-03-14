package com.leyou.entity.vo;


import lombok.Data;

@Data
public class PageParams {
    /**
     * 一页数量
     */
    private long size;

    /**
     * 当前页码
     */
    private long current;

    /**
     * 查询条件
     */
    private String cond;
}
