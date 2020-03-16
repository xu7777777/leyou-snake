package com.leyou;

import com.leyou.entity.dto.TbOpUser;
import com.leyou.entity.enums.OpUserTypeEnum;
import com.leyou.service.ITbOpUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @program: sijie_bops
 * @description: ${description}
 * @author: zhanglijie
 * @date: 2020-02-10 10:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { LeyouApplication.class })
@ActiveProfiles
@Slf4j
@ContextConfiguration
public class UtilsTest {

    @Resource
    private ITbOpUserService userService;

    @Test
    public void insertUser(){

        String pas = "123456";

        TbOpUser op = new TbOpUser();
        op.setName("admin");
        pas = DigestUtils.md5DigestAsHex(pas.getBytes());
        op.setPassword(pas);
        op.setRealName("张三");
        op.setIntroduction("测试管理员");
        op.setRoles(OpUserTypeEnum.ADMIN.getCode());
        userService.save(op);

    }
}
