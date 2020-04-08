package com.leyou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.entity.dto.*;
import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.*;
import com.leyou.mapper.TbSpuMapper;
import com.leyou.service.*;
import com.leyou.util.OutputUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class SpuServiceImpl extends ServiceImpl<TbSpuMapper, TbSpu> implements ITbSpuService {

    @Resource
    private ITbSkuService skuService;

    @Resource
    private ITbBrandService brandService;

    @Resource
    private ITbCategoryService categoryService;

    @Resource
    private ITbSpuDetailService spuDetailService;

    @Override
    public Output<PageData<GoodsResp>> pageList(PageParams pageParams) {
        //check params
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(pageParams.getCond())) {
            jsonObject = JSONObject.parseObject(pageParams.getCond());
        }
        //get brands from DB
        IPage<TbSpu> spuIPage = this.baseMapper.pageList(new Page<>(pageParams.getCurrent(), pageParams.getSize()),
                jsonObject.getObject("saleable", String.class),
                jsonObject.getObject("idOrTitle", String.class),
                jsonObject.getObject("brandName", String.class),
                jsonObject.getObject("categoryName", String.class)
        );

        if (spuIPage.getRecords().size() == 0) {
            return OutputUtil.fail(ErrorEnum.GOODS_NOT_EXIST.getCode(), ErrorEnum.GOODS_NOT_EXIST.getMsg());
        }

        List<Long> spuIds = spuIPage.getRecords().stream().map(TbSpu::getId).collect(Collectors.toList());
        List<Long> categoryIds = spuIPage.getRecords().stream().map(TbSpu::getCid3).collect(Collectors.toList());
        List<Long> brandIds = spuIPage.getRecords().stream().map(TbSpu::getBrandId).collect(Collectors.toList());
        List<GoodsResp> goodsResps = new ArrayList<>();

        //search
        QueryWrapper<TbSku> skuQueryWrapper = new QueryWrapper<>();
        QueryWrapper<TbBrand> brandQueryWrapper = new QueryWrapper<>();
        QueryWrapper<TbCategory> categoryQueryWrapper = new QueryWrapper<>();
        QueryWrapper<TbSpuDetail> spuDetailQueryWrapper = new QueryWrapper<>();
        brandQueryWrapper.in("id", brandIds);
        skuQueryWrapper.in("spu_id", spuIds);
        categoryQueryWrapper.in("id", categoryIds);
        spuDetailQueryWrapper.in("spu_id", spuIds);
        List<TbSku> skus = skuService.list(skuQueryWrapper);
        List<TbBrand> brands = brandService.list(brandQueryWrapper);
        List<TbCategory> categories = categoryService.list(categoryQueryWrapper);
        List<TbSpuDetail> spuDetails = spuDetailService.list(spuDetailQueryWrapper);

        Map<Long, TbBrand> brandsMap = brands.stream().collect(Collectors.toMap(TbBrand::getId, o -> o));
        Map<Long, TbCategory> categoriesMap = categories.stream().collect(Collectors.toMap(TbCategory::getId, o -> o));
        Map<Long, TbSpuDetail> spuDetailsMap = spuDetails.stream().collect(Collectors.toMap(TbSpuDetail::getSpuId, o -> o));

        for (TbSpu spu : spuIPage.getRecords()) {
            GoodsResp goodsResp = new GoodsResp();
            String brandName = brandsMap.get(spu.getBrandId()).getName();
            String categoryName = categoriesMap.get(spu.getCid3()).getLabel();
            BeanUtils.copyProperties(spu, goodsResp);
            BeanUtils.copyProperties(spuDetailsMap.get(spu.getId()), goodsResp);
            goodsResp.setCid(spu.getCid3());
            goodsResp.setBrandName(brandName);
            goodsResp.setCategoryName(categoryName);

            goodsResps.add(goodsResp);
        }

        List<ProductResp> productResps = new ArrayList<>();
        for (TbSku sku : skus) {
            ProductResp productResp = new ProductResp();
            BeanUtils.copyProperties(sku, productResp);

            productResps.add(productResp);
        }

        // add
        Map<Long, List<ProductResp>> productsMap = productResps.stream().collect(Collectors.groupingBy(ProductResp::getSpuId));
        for (GoodsResp goodsResp : goodsResps) {
//            Long heat = productsMap.get(goodsResp.getId()).stream().mapToLong(ProductResp::getHeat).sum();
            Long heat = 10L;
            goodsResp.setProductResps(productsMap.get(goodsResp.getId()));
            goodsResp.setHeat(heat);
        }

        //make page result
        PageData<GoodsResp> pageData = new PageData<>();
        pageData.setList(goodsResps);
        pageData.setTotalCount(spuIPage.getTotal());

        return OutputUtil.ok(pageData);
    }
}
