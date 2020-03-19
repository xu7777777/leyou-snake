package com.leyou;

import com.leyou.entity.dto.TbBrand;
import com.leyou.entity.dto.TbHeat;
import com.leyou.entity.enums.HeatTypeEnums;
import com.leyou.service.ITbBrandService;
import com.leyou.service.ITbHeatService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author xu7777777
 * @date 2020/3/17 8:09 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LeyouApplication.class })
//@ActiveProfiles
//@Slf4j
//@ContextConfiguration
public class DBGenerater {

    @Resource
    private ITbBrandService brandService;

    @Resource
    private ITbHeatService heatService;

    /**
     * add info for table heat
     */
    @Test
    public void generateHeat(){
        List<TbBrand> allBrands = brandService.list();
        List<Long> allBrandIds = allBrands.stream().map(TbBrand::getId).collect(Collectors.toList());
//        List<TbHeat> heats = heatService.list();
        List<TbHeat> heats = new ArrayList<>();

        Random random = new Random();
        for (Long id : allBrandIds){
            TbHeat heat = new TbHeat();
            heat.setEntityId(id);
            heat.setHot((random.nextInt(1000000) + 1L));
            heat.setEntityType(HeatTypeEnums.BRAND.getCode());
//            heat.setEntityType(HeatTypeEnums.BRAND.getCode());

            heats.add(heat);
        }
        heatService.saveOrUpdateBatch(heats);
    }
}
