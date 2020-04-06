package com.leyou.service;

import com.leyou.entity.dto.TbSpu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.entity.vo.GoodsResp;
import com.leyou.entity.vo.Output;
import com.leyou.entity.vo.PageData;
import com.leyou.entity.vo.PageParams;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 服务类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
public interface ITbSpuService extends IService<TbSpu> {

    Output<PageData<GoodsResp>> pageList(PageParams pageParams);
}
