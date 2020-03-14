package com.leyou.service.impl;

import com.leyou.entity.dto.TbSku;
import com.leyou.mapper.TbSkuMapper;
import com.leyou.service.ITbSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class SkuServiceImpl extends ServiceImpl<TbSkuMapper, TbSku> implements ITbSkuService {

}
