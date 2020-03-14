package com.leyou.service.impl;

import com.leyou.entity.dto.TbCategory;
import com.leyou.mapper.TbCategoryMapper;
import com.leyou.service.ITbCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<TbCategoryMapper, TbCategory> implements ITbCategoryService {

}
