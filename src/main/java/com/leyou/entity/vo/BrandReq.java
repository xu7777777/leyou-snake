package com.leyou.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    @NotBlank(message = "  品牌名称不能为空！")
    private String name;

    /**
     * 品牌图片
     */
    @NotBlank(message = "  请上传该品牌图片！")
    private String image;

    /**
     * 类目id
     */
    private List<Long> ids;

}
