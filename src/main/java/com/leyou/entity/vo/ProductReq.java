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
    @NotNull(message = "商品异常，请重试！")
    private Long goodsId;

    /**
     * 商品标题
     */
    @NotBlank(message = "请输入商品名称！")
    private String title;

    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;

    /**
     * 销售价格，单位为分
     */
    @NotNull(message = "请给出售价！")
    private Long price;

    /**
     * 参数列表
     */
    private String ownSpec;

}
