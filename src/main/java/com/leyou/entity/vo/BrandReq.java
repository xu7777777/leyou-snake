package com.leyou.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xu7777777
 * @date 2020/3/18 10:32 AM
 */
@Data
public class BrandReq {

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    @NotBlank(message = "Please input the brand's name")
    private String name;

    /**
     * 品牌图片
     */
    @NotBlank(message = "Please upload the image of brand")
    private String image;

}
