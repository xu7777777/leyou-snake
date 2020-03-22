package com.leyou.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.leyou.entity.dto.TbCategory;
import com.leyou.entity.dto.TbCategoryBrand;
import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.CategoryReq;
import com.leyou.entity.vo.CategoryResp;
import com.leyou.entity.vo.Output;
import com.leyou.service.ITbCategoryBrandService;
import com.leyou.service.ITbCategoryService;
import com.leyou.util.OutputUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 前端控制器
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ITbCategoryBrandService categoryBrandService;

    @Resource
    private ITbCategoryService categoryService;

    /**
     * Get child classification based on parent classification id
     *
     * @param cid parent classification id
     * @return list of category
     */
    @RequestMapping("list")
    public Output<List<CategoryResp>> list(@RequestParam("cid") Long cid) {
        QueryWrapper<TbCategory> categoryQW = new QueryWrapper<>();
        categoryQW.eq("parent_id", cid);
        List<TbCategory> categories = categoryService.list(categoryQW);

//        List<Long> categoryIds = categories.stream().map(TbCategory::getId).collect(Collectors.toList());
//        QueryWrapper<TbCategory> caQW = new QueryWrapper<>();
//        caQW.in("parent_id", categoryIds);
//        List<TbCategory> childCategories = categoryService.list(caQW);

//        Map<Long, List<TbCategory>> categoryMap = childCategories.stream().collect(Collectors.groupingBy(TbCategory::getParentId));

        List<CategoryResp> categoryResps = new ArrayList<>();
        for (TbCategory category : categories) {
            CategoryResp categoryResp = new CategoryResp();
//            categoryResp.setChildren(categoryMap.get(category.getId()));
            BeanUtils.copyProperties(category, categoryResp);

            categoryResps.add(categoryResp);
        }

        return OutputUtil.ok(categoryResps);
    }

    /**
     * add category
     *
     * @param categoryReq categoryReq
     * @return categoryId
     */
    @PostMapping("add")
    @Transactional
    public Output<Long> add(@Valid CategoryReq categoryReq) {
        TbCategory category = new TbCategory();

        // search the parent node and is_parent must be true
        UpdateWrapper<TbCategory> categoryUW = new UpdateWrapper<>();
        categoryUW.eq("id", categoryReq.getParentId()).set("is_parent", true);
        this.categoryService.update(categoryUW);

        BeanUtils.copyProperties(categoryReq, category);

        if (this.categoryService.save(category)) {
            return OutputUtil.ok(category.getId());
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

    /**
     * delete category
     * @param cid categoryId
     * @return message
     */
    @RequestMapping("del/{cid}")
    public Output<String> del(@PathVariable("cid") Long cid) {
        System.out.println("============= "+cid);
        // better to add ASD
        QueryWrapper<TbCategory> canDelCategoryQW = new QueryWrapper<>();
        canDelCategoryQW.eq("parent_id", cid);
        List<TbCategory> canDelCategorys = categoryService.list(canDelCategoryQW);

        if (canDelCategorys != null && canDelCategorys.size() > 0){
            return OutputUtil.fail(ErrorEnum.CATEGORY_DELETE_FAILE.getCode(), ErrorEnum.CATEGORY_DELETE_FAILE.getMsg());
        }

        if (this.categoryService.removeById(cid)) {
            return OutputUtil.ok("Delete Success !");
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

    /**
     * update category
     * @param categoryReq categoryReq
     * @return message
     */
    @PostMapping("update")
    public Output<String> update(@Valid  CategoryReq categoryReq) {
        TbCategory category = new TbCategory();
        BeanUtils.copyProperties(categoryReq, category);
        category.setId(categoryReq.getCategoryId());

        if (this.categoryService.updateById(category)) {
            return OutputUtil.ok("Update Success !");
        } else {
            return OutputUtil.fail(ErrorEnum.OPERATION_FAILED.getCode(), ErrorEnum.OPERATION_FAILED.getMsg());
        }
    }

    /**
     * get category by categoryId
     * @param cid categoryId
     * @return categoryResp
     */
    @RequestMapping("/get/{cid}")
    public Output<CategoryResp> getCategoryById(@PathVariable(value = "cid", required = false) String cid) {
        if ("undefined".equals(cid.trim())) {
            return OutputUtil.ok(null);
        }
        TbCategory category = this.categoryService.getById(cid.trim());
        CategoryResp categoryResp = new CategoryResp();

        BeanUtils.copyProperties(category, categoryResp);

        return OutputUtil.ok(categoryResp);
    }


    /**
     * get categories by brandId
     * @param bid brandId
     * @return categories
     */
    @RequestMapping("/categories/{bid}")
    public Output<List<CategoryResp>> getCategoryByBrandId(@PathVariable("bid") Long bid) {
        QueryWrapper<TbCategoryBrand> categoryBrandQW = new QueryWrapper<>();
        categoryBrandQW.eq("brand_id", bid);
        List<TbCategoryBrand> categoryBrands = categoryBrandService.list(categoryBrandQW);

        if (categoryBrands == null || categoryBrands.size() == 0) {
            return OutputUtil.ok(null);
        }

        List<Long> categoryIds = categoryBrands.stream().map(TbCategoryBrand::getCategoryId).collect(Collectors.toList());
        QueryWrapper<TbCategory> categoryQW = new QueryWrapper<>();
        categoryQW.in("id", categoryIds);
        List<TbCategory> categories = categoryService.list(categoryQW);

        List<CategoryResp> categoryResps = new ArrayList<>();
        for (TbCategory category : categories) {
            CategoryResp categoryResp = new CategoryResp();
            BeanUtils.copyProperties(category, categoryResp);

            categoryResps.add(categoryResp);
        }

        return OutputUtil.ok(categoryResps);
    }

}
