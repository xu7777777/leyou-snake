package com.leyou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.entity.dto.TbBrand;
import com.leyou.entity.dto.TbHeat;
import com.leyou.entity.enums.HeatTypeEnums;
import com.leyou.entity.vo.BrandResp;
import com.leyou.entity.vo.Output;
import com.leyou.entity.vo.PageData;
import com.leyou.entity.vo.PageParams;
import com.leyou.mapper.TbBrandMapper;
import com.leyou.service.ITbBrandService;
import com.leyou.service.ITbHeatService;
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
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class BrandServiceImpl extends ServiceImpl<TbBrandMapper, TbBrand> implements ITbBrandService {

    @Resource
    private ITbHeatService heatService;

    /**
     * return all brands or the brands which eet the conditions
     * @param pageParams pageSize pageNum conditions(title, id)
     * @return brands
     */
    @Override
    public Output<PageData<BrandResp>> pageList(PageParams pageParams) {
        //check params
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(pageParams.getCond())){
            jsonObject = JSONObject.parseObject(pageParams.getCond());
        }
        //get brands from DB
        IPage<TbBrand> brandPage = this.baseMapper.pageList(new Page<>(pageParams.getCurrent(), pageParams.getSize()),
                jsonObject.getObject("idOrTitle", String.class),
                jsonObject.getObject("desc", String.class),
                jsonObject.getObject("sortBy", String.class));

        //search heat
        List<Long> brandIds = brandPage.getRecords().stream().map(TbBrand::getId).collect(Collectors.toList());
        QueryWrapper<TbHeat> heatQW = new QueryWrapper<>();
        heatQW.eq("entity_type", HeatTypeEnums.BRAND.getCode()).in("entity_id", brandIds);
        List<TbHeat> heats = heatService.list(heatQW);
        Map<Long, TbHeat> heatMaps = heats.stream().collect(Collectors.toMap(TbHeat::getEntityId, o -> o));

        //copy properties
        List<BrandResp> brandResps = new ArrayList<>();
        for (TbBrand brand : brandPage.getRecords()){
            BrandResp brandResp = new BrandResp();
            brandResp.setHeat(heatMaps.get(brand.getId()).getHot());
            BeanUtils.copyProperties(brand, brandResp);

            brandResps.add(brandResp);
        }


        //make page result
        PageData<BrandResp> pageData = new PageData<>();
        pageData.setList(brandResps);
        pageData.setTotalCount(brandPage.getTotal());

        return OutputUtil.ok(pageData);
    }
}
