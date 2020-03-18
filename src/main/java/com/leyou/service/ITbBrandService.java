package com.leyou.service;

import com.leyou.entity.dto.TbBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.entity.vo.*;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
public interface ITbBrandService extends IService<TbBrand> {

    Output<PageData<BrandResp>> pageList(PageParams pageParams);

    Long add(BrandReq brandReq);

    Boolean del(Long bid);

    boolean updateBrand(BrandReq brandReq);
}
