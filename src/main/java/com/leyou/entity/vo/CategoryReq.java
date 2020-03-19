package com.leyou.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xu7777777
 * @date 2020/3/18 10:50 PM
 */
@Data
public class CategoryReq {
    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 类目名称
     */
    @NotBlank(message = "分类名称不能为空！")
    private String label;

    /**
     * 父类目id,顶级类目填0
     */
    private Long parentId;

    /**
     * 是否为父节点，0为否，1为是
     */
    private Boolean isParent;

}
