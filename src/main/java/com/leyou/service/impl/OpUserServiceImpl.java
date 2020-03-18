package com.leyou.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leyou.entity.consts.UserConst;
import com.leyou.entity.dto.TbOpUser;
import com.leyou.mapper.TbOpUserMapper;
import com.leyou.service.ITbOpUserService;
import com.leyou.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator
 * @since 2020-03-16
 */
@Service
public class OpUserServiceImpl extends ServiceImpl<TbOpUserMapper, TbOpUser> implements ITbOpUserService {

    @Resource
    private RedisUtil redisUtil;

    /**
     * save opUser to redis
     * @param accessToken token
     * @param opUser opUser
     */
    @Override
    public void setOpUser2Redis(String accessToken, TbOpUser opUser) {
        redisUtil.set(UserConst.TOKEN_KEY + accessToken, JSONObject.toJSONString(opUser), UserConst.EXPIRE_TIME);
    }

    /**
     * get OpUser from redis
     * @param accessToken token
     * @return json
     */
    @Override
    public String getOpUserFromRedis(String accessToken) {
        return redisUtil.get(UserConst.TOKEN_KEY + accessToken);
    }

    /**
     * del redis opUser
     * @param accessToken token
     */
    @Override
    public void delRedisOpUser(String accessToken) {
        redisUtil.del(UserConst.TOKEN_KEY + accessToken);
    }

}
