package com.leyou.service.impl;

import com.leyou.entity.dto.TbStock;
import com.leyou.mapper.TbStockMapper;
import com.leyou.service.ITbStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 库存表，代表库存，秒杀库存等信息 服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-14
 */
@Service
public class StockServiceImpl extends ServiceImpl<TbStockMapper, TbStock> implements ITbStockService {

}
