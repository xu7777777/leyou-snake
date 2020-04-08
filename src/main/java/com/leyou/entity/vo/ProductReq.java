package com.leyou.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductReq {

    /**
     * sku id
     */
    private Long proId;

    /**
     * spu id
     */
    private Long goodsId;

    /**
     * 商品标题
     */
    @NotBlank(message = "请输入正确的商品名称！")
    private String title;

    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;

    /**
     * 销售价格，单位为分
     */
    @NotNull(message = "请给出正确的售价！")
    private Long price;

    /**
     * 参数列表
     */
    private String ownSpec;

    /**
     * 是否上架
     */
    private Integer enable;

}
