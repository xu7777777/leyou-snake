package com.leyou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leyou.entity.dto.TbSku;
import com.leyou.entity.enums.StatusEnum;
import com.leyou.entity.vo.ImageVo;
import com.leyou.entity.vo.ProductParams;
import com.leyou.entity.vo.ProductReq;
import com.leyou.entity.vo.ProductResp;
import com.leyou.mapper.TbSkuMapper;
import com.leyou.service.ITbSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    public boolean add(ProductReq productReq) {
        TbSku product = new TbSku();
        product.setSpuId(productReq.getGoodsId());
        product.setCreateTime(LocalDateTime.now());
        product.setLastUpdateTime(product.getCreateTime());
        BeanUtils.copyProperties(productReq, product);
        product.setEnable(StatusEnum.ABNORMAL.getCode());

        List<ProductParams> productParams = JSONObject.parseArray(productReq.getOwnSpec(), ProductParams.class);
        if (productParams != null && productParams.size() >= 1) {
            Map<Long, String> ownSpecMap = productParams.stream().collect(Collectors.toMap(ProductParams::getId, ProductParams::getValue));
            product.setOwnSpec(JSONObject.toJSONString(ownSpecMap));
        }

        return this.save(product);
    }

    @Override
    public List<ProductResp> getList(Long goodsId) {
        QueryWrapper<TbSku> skuQueryWrapper = new QueryWrapper<>();
        skuQueryWrapper.eq("spu_id", goodsId);
        List<TbSku> skus = this.list(skuQueryWrapper);

        List<ProductResp> productResps = new ArrayList<>();
        for (TbSku product : skus) {
            ProductResp productResp = new ProductResp();
            List<ImageVo> imageVos = new ArrayList<>();
            // imgs
            List<String> imgs = Arrays.asList(product.getImages().split(";;"));
            for (String img : imgs) {
                ImageVo imageVo = new ImageVo();
                imageVo.setUrl(img);

                imageVos.add(imageVo);
            }
            productResp.setImages(imageVos);

            BeanUtils.copyProperties(product, productResp);
            productResp.setHeat(10L);
            productResp.setEnable(StatusEnum.map.get(product.getEnable()).getMsg());
            Map proObjectMap = JSONObject.parseObject(product.getOwnSpec().toString(), Map.class);
            List<ProductParams> proParamsList = new ArrayList<>();
            for (Object pid : proObjectMap.keySet()) {
                ProductParams productParams = new ProductParams();
                productParams.setId(Long.valueOf(pid.toString()));
                productParams.setValue((String) proObjectMap.get(pid));

                proParamsList.add(productParams);
            }

            productResp.setOwnSpec(proParamsList);
            productResps.add(productResp);
        }

        return productResps;
    }

    @Override
    public boolean mod(ProductReq productReq) {
        TbSku product = new TbSku();
        product.setEnable(productReq.getEnable());
        product.setLastUpdateTime(LocalDateTime.now());
        product.setId(productReq.getProId());
        BeanUtils.copyProperties(productReq, product);

        List<ProductParams> productParams = JSONObject.parseArray(productReq.getOwnSpec(), ProductParams.class);
        if (productParams != null && productParams.size() >= 1) {
            Map<Long, String> ownSpecMap = productParams.stream().collect(Collectors.toMap(ProductParams::getId, ProductParams::getValue));
            product.setOwnSpec(JSONObject.toJSONString(ownSpecMap));
        }

        return this.saveOrUpdate(product);
    }
}
