package com.leyou.controller;


import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.*;
import com.leyou.service.ITbBrandService;
import com.leyou.util.OutputUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
        if (brandService.updateBrand(brandReq)){
            return OutputUtil.ok("Update Success！");
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

}
