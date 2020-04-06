package com.leyou.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyou.entity.dto.TbSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 Mapper 接口
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
public interface TbSpuMapper extends BaseMapper<TbSpu> {

    IPage<TbSpu> pageList(Page<TbSpu> tbSpuPage, String saleable, String idOrTitle, String brandName, String categoryName);
}
