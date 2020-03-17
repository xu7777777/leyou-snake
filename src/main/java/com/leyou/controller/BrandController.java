package com.leyou.controller;


import com.leyou.entity.vo.BrandResp;
import com.leyou.entity.vo.Output;
import com.leyou.entity.vo.PageData;
import com.leyou.entity.vo.PageParams;
import com.leyou.service.ITbBrandService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    /**
     * return all brands or the brands which eet the conditions
     * @param pageParams pageSize pageNum conditions(title, id)
     * @return brands
     */
    @RequestMapping("list")
    public Output<PageData<BrandResp>> list(PageParams pageParams){
        return brandService.pageList(pageParams);
    }

}
