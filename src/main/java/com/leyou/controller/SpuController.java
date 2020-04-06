package com.leyou.controller;


import com.leyou.entity.vo.GoodsResp;
import com.leyou.entity.vo.Output;
import com.leyou.entity.vo.PageData;
import com.leyou.entity.vo.PageParams;
import com.leyou.service.ITbSpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 前端控制器
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/goods")
public class SpuController {

    @Resource
    private ITbSpuService spuService;

    @RequestMapping("list")
    public Output<PageData<GoodsResp>> list(PageParams pageParams){
        return spuService.pageList(pageParams);
    }

}
