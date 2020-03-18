package com.leyou.entity.vo;

import lombok.Data;

@Data
public class BrandResp {


    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌图片地址
     */
    private String image;

    /**
     * 品牌的首字母
     */
    private String letter;

    /**
     * 品牌热度
     */
    private Long heat;


}
