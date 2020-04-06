package com.leyou.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class GoodsResp {
    /**
     * spu id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 特有规格参数及可选值信息，json格式
     */
    private String specialSpec;

    /**
     * 状态
     */
    private Integer saleable;

    /**
     * 热度
     */
    private Long heat;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;

    /**
     * 产品列表
     */
    List<ProductResp> productResps;
}
