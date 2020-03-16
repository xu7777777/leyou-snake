package com.leyou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leyou.entity.dto.TbOpUser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author generator
 * @since 2020-03-16
 */
public interface ITbOpUserService extends IService<TbOpUser> {

    void delRedisOpUser(String accessToken);

    void setOpUser2Redis(String accessToken, TbOpUser opUser);

    String getOpUserFromRedis(String accessToken);

}
