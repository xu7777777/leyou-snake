package com.leyou.service.impl;

import com.leyou.entity.dto.TbSpu;
import com.leyou.mapper.TbSpuMapper;
import com.leyou.service.ITbSpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class SpuServiceImpl extends ServiceImpl<TbSpuMapper, TbSpu> implements ITbSpuService {

}
