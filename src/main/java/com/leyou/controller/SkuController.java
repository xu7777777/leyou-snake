package com.leyou.controller;


import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.vo.Output;
import com.leyou.entity.vo.ProductReq;
import com.leyou.entity.vo.ProductResp;
import com.leyou.service.ITbSkuService;
import com.leyou.util.OutputUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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

    /**
     * 添加产品
     * @param productReq 产品参数
     * @return message
     */
    @PostMapping("add")
    public Output<String> add(@Valid ProductReq productReq) {
        if (!skuService.add(productReq) || productReq.getGoodsId() == null) {
            OutputUtil.fail(ErrorEnum.PRODUCT_ADD_FAILE.getCode(), ErrorEnum.PRODUCT_ADD_FAILE.getMsg());
        }
        return OutputUtil.ok(null);
    }

    /**
     * 通过商品ID，返回其所有产品
     * @param goodsId 商品ID
     * @return 产品列表
     */
    @RequestMapping("list")
    public Output<List<ProductResp>> list(@RequestParam("goodsId") Long goodsId) {
        List<ProductResp> productResps = skuService.getList(goodsId);
        if (productResps == null) {
            OutputUtil.ok(null);
        }
        return OutputUtil.ok(productResps);
    }

    /**
     * 修改产品内容
     * @param productReq 更新后的产品对象
     * @return message
     */
    @PostMapping("mod")
    public Output<String> mod(@Valid ProductReq productReq) {
        if (!skuService.mod(productReq)) {
            return OutputUtil.fail(ErrorEnum.PRODUCT_MOD_FAILE.getCode(), ErrorEnum.PRODUCT_MOD_FAILE.getMsg());
        }
        return OutputUtil.ok(null);
    }

    /**
     * 删除产品
     * @param pid 产品ID
     * @return message
     */
    @RequestMapping("del")
    public Output<String> del(@RequestParam("pid") Long pid) {
        if (!skuService.removeById(pid)) {
            return OutputUtil.fail(ErrorEnum.PRODUCT_DEL_FAILE.getCode(), ErrorEnum.PRODUCT_DEL_FAILE.getMsg());
        }
        return OutputUtil.ok(null);
    }
}
