package com.leyou.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leyou.entity.dto.TbBrand;
import com.leyou.entity.dto.TbCategoryBrand;
import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.*;
import com.leyou.service.ITbBrandService;
import com.leyou.service.ITbCategoryBrandService;
import com.leyou.util.OutputUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 前端控制器
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/brands")
public class BrandController {

    @Resource
    private ITbBrandService brandService;

    @Resource
    private ITbCategoryBrandService categoryBrandService;

    /**
     * return all brands or the brands which eet the conditions
     *
     * @param pageParams pageSize pageNum conditions(title, id)
     * @return brands
     */
    @RequestMapping("list")
    public Output<PageData<BrandResp>> list(PageParams pageParams) {
        return brandService.pageList(pageParams);
    }

    /**
     * add brand and return the brand's id for display
     *
     * @param brandReq brand
     * @return brandId
     */
    @PostMapping("add")
    public Output<Long> add(@Valid BrandReq brandReq) {
        System.out.println(" ------- "+ brandReq);

        Long brandId = brandService.add(brandReq);
        if (brandId != null) {
            return OutputUtil.ok(brandId);
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

    /**
     * delete brand
     *
     * @param bid brandId
     * @return message
     */
    @RequestMapping("del/{bid}")
    public Output<String> del(@PathVariable("bid") Long bid) {
        // 最好加一层管理员权限验证
        if (brandService.del(bid)) {
            return OutputUtil.ok("Delete Success！");
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

    /**
     * update brand
     * @param brandReq brandReq
     * @return message
     */
    @PostMapping("update")
    public Output<String> update(@Valid BrandReq brandReq) {
        if (brandService.updateBrand(brandReq)) {
            return OutputUtil.ok("Update Success！");
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

    /**
     * get brands by cid
     * @param cid category ID
     * @return brands
     */
    @RequestMapping("getBrands/{cid}")
    public Output<List<BrandResp>> getBrandsByCId(@PathVariable("cid") Long cid) {
        // get all brand's id
        QueryWrapper<TbCategoryBrand> categoryBrandQw = new QueryWrapper<>();
        categoryBrandQw.eq("category_id", cid);
        List<TbCategoryBrand> categoryBrands = categoryBrandService.list(categoryBrandQw);

        List<Long> brandIds = categoryBrands.stream().map(TbCategoryBrand::getBrandId).collect(Collectors.toList());

        if (brandIds == null || brandIds.size() < 1) {
            return OutputUtil.ok(null);
        }

        // get all brands
        QueryWrapper<TbBrand> brandQw = new QueryWrapper<>();
        brandQw.in("id", brandIds);
        List<TbBrand> brands = brandService.list(brandQw);

        // build result
        List<BrandResp> brandResps = new ArrayList<>();
        for (TbBrand brand : brands) {
            BrandResp brandResp = new BrandResp();
            brandResp.setBrandId(brand.getId());
            BeanUtils.copyProperties(brand, brandResp);

            brandResps.add(brandResp);
        }

        return OutputUtil.ok(brandResps);

    }

}
