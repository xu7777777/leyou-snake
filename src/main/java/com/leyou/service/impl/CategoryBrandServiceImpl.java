package com.leyou.service.impl;

import com.leyou.entity.dto.TbCategoryBrand;
import com.leyou.mapper.TbCategoryBrandMapper;
import com.leyou.service.ITbCategoryBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<TbCategoryBrandMapper, TbCategoryBrand> implements ITbCategoryBrandService {

}
