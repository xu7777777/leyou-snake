package com.leyou.controller;


import com.leyou.entity.vo.Output;
import com.leyou.entity.vo.ProductReq;
import com.leyou.service.ITbSkuService;
import com.leyou.util.OutputUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * sku表,该表表示具体的商品实体,如黑色的 64g的iphone 8 前端控制器
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/product")
public class SkuController {

    @Resource
    private ITbSkuService skuService;

    @PostMapping("add")
    public Output add(@Valid ProductReq productReq) {
        skuService.add(productReq);
        return OutputUtil.ok(null);
    }

}
