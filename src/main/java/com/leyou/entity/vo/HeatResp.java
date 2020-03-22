package com.leyou.entity.vo;

import lombok.Data;

@Data
public class HeatResp {

    /**
     * id
     */
    private Long id;

    /**
     * 热度（购买+10/点击+1）
     */
    private Long hot;

    /**
     * 挂钩的商品或品牌名称
     */
    private String entityName;

}
