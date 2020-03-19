package com.leyou.entity.vo;

import com.leyou.entity.dto.TbCategory;
import lombok.Data;

import java.util.List;

/**
 * @author xu7777777
 * @date 2020/3/18 6:52 PM
 */
@Data
public class CategoryResp {

    /**
     * 分类id
     */
    private Long id;

    /**
     * 类目名称
     */
    private String label;

    /**
     * 父类目id,顶级类目填0
     */
    private Long parentId;

    /**
     * 是否为父节点，0为否，1为是
     */
    private Boolean isParent;

    /**
     * 排序指数，越小越靠前
     */
    private Integer sort;

    List<TbCategory> children;
}
