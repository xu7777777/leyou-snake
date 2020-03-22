package com.leyou.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leyou.entity.dto.TbBrand;
import com.leyou.entity.dto.TbHeat;
import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.enums.HeatTypeEnums;
import com.leyou.entity.vo.HeatResp;
import com.leyou.entity.vo.Output;
import com.leyou.service.ITbBrandService;
import com.leyou.service.ITbHeatService;
import com.leyou.util.OutputUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author generator
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/heat")
public class HeatController {

    @Resource
    private ITbHeatService heatService;

    @Resource
    private ITbBrandService brandService;

    @RequestMapping("get")
    public Output<List<HeatResp>> get(@RequestParam(value = "title", required = false) String title) {
        // 查找品牌
        QueryWrapper<TbBrand> brandQW = new QueryWrapper<>();
        brandQW.like("name", title.trim());
        List<TbBrand> brands = brandService.list(brandQW);

        if (brands == null || brands.size() == 0) {
            return OutputUtil.fail(ErrorEnum.GET_BRAND_ERROR.getCode(), ErrorEnum.GET_CATEGORY_FAILE.getMsg());
        }

        // 查找热度
        List<Long> brandIds = brands.stream().map(TbBrand::getId).collect(Collectors.toList());
        Map<Long, TbBrand> brandMap = brands.stream().collect(Collectors.toMap(TbBrand::getId, o -> o));
        QueryWrapper<TbHeat> heatQW = new QueryWrapper<>();
        heatQW.in("entity_id", brandIds).
                eq("entity_type", HeatTypeEnums.BRAND.getCode());
        List<TbHeat> heats = heatService.list(heatQW);

        // 组合结果并返回
        List<HeatResp> heatResps = new ArrayList<>();
        for (TbHeat heat : heats) {
            HeatResp heatResp = new HeatResp();
            BeanUtils.copyProperties(heat, heatResp);
            heatResp.setEntityName(brandMap.get(heat.getEntityId()).getName());

            heatResps.add(heatResp);
        }

        return OutputUtil.ok(heatResps);
    }

}
