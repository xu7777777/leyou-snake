package com.leyou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.leyou.entity.dto.TbSku;
import com.leyou.entity.enums.StatusEnum;
import com.leyou.entity.vo.ProductParams;
import com.leyou.entity.vo.ProductReq;
import com.leyou.mapper.TbSkuMapper;
import com.leyou.service.ITbSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public Boolean add(ProductReq productReq) {
        TbSku product = new TbSku();
        product.setSpuId(productReq.getGoodsId());
        product.setCreateTime(LocalDateTime.now());
        product.setEnable(StatusEnum.ABNORMAL.getCode());
        product.setLastUpdateTime(product.getCreateTime());
        BeanUtils.copyProperties(productReq, product);

        List<ProductParams> productParams = JSONObject.parseArray(productReq.getOwnSpec(), ProductParams.class);
        if (productParams != null && productParams.size() >= 1) {
            Map<Long, String> ownSpecMap = productParams.stream().collect(Collectors.toMap(ProductParams::getId, ProductParams::getValue));
            product.setOwnSpec(JSONObject.toJSONString(ownSpecMap));
        }

        return this.save(product);
    }
}
