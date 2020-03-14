package com.leyou.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.entity.dto.TbBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
public interface TbBrandMapper extends BaseMapper<TbBrand> {

    IPage<TbBrand> pageList(Page<TbBrand> tbBrandPage, String idOrTitle, String desc, String sortBy);
}
