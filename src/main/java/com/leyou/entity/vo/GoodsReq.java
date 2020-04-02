package com.leyou.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsReq {
    /**
     * spu id
     */
    private Long spuId;

    /**
     * 标题
     */
    private String title;

    /**
     * 3级类目id
     */
    private Long cid3;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 商品所属品牌id
     */
    private Long brandId;

    /**
     * 商品描述信息
     */
    private String description;

    /**
     * 通用规格参数数据
     */
    private String genericSpec;

    /**
     * 特有规格参数及可选值信息，json格式
     */
    private String specialSpec;

    /**
     * 包装清单
     */
    private String packingList;

    /**
     * 售后服务
     */
    private String afterService;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    private LocalDateTime lastUpdateTime;

}
