package com.leyou.service;

import com.leyou.entity.dto.TbSku;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.entity.vo.ProductReq;
import com.leyou.entity.vo.ProductResp;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 服务类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
public interface ITbSkuService extends IService<TbSku> {

    boolean add(ProductReq productReq);

    List<ProductResp> getList(Long goodsId);

    boolean mod(ProductReq productReq);
}
