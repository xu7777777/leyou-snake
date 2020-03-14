package com.leyou.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页返回的信息
 */
@Data
public class PageData<T> {

    /**
     * 总数
     */
    private long totalCount;

    /**
     * 列表内容
     */
    private List<T> list;
}
