package com.leyou.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.leyou.entity.dto.TbOpUser;
import com.leyou.entity.enums.ErrorEnum;
import com.leyou.entity.enums.OpUserTypeEnum;
import com.leyou.entity.vo.LoginParam;
import com.leyou.entity.vo.OpUserResp;
import com.leyou.entity.vo.Output;
import com.leyou.service.ITbOpUserService;
import com.leyou.util.CommonUtil;
import com.leyou.util.OutputUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author xu7777777
 * @date 2020/3/16 2:06 PM
 */
@RestController
@RequestMapping("user")
public class OpUserController {

    @Resource
    private ITbOpUserService opUserService;

    /**
     * 后台管理员登陆
     * @param loginParam passwd and username(unique)
     * @return accessToken
     */
    @PostMapping("login")
    public Output<String> login(@Valid LoginParam loginParam){

        // check username and password
        QueryWrapper<TbOpUser> opUserQw = new QueryWrapper<>();
        opUserQw.eq("name", loginParam.getUsername());
        TbOpUser isExistOpUser = this.opUserService.getOne(opUserQw);
        // username is not exist
        if (isExistOpUser == null){
            OutputUtil.fail(ErrorEnum.USER_NOT_EXIST.getCode(), ErrorEnum.USER_NOT_EXIST.getMsg());
        }

        String pwdMd5 = DigestUtils.md5DigestAsHex(loginParam.getPassword().getBytes());
        opUserQw.eq("password", pwdMd5);
        TbOpUser opUser = opUserService.getOne(opUserQw);
        // password is not match
        if (opUser == null) {
            return OutputUtil.fail(ErrorEnum.PASSWORD_ERROR.getCode(), ErrorEnum.PASSWORD_ERROR.getMsg());
        }


        //del redis origin login key
        opUserService.delRedisOpUser(opUser.getToken());

        //save access_token to mysql
        String token = CommonUtil.getUUID32();
        opUser.setToken(token);
        opUserService.saveOrUpdate(opUser);

        //save to redis
        opUserService.setOpUser2Redis(token, opUser);

        return OutputUtil.ok(token);
    }

    /**
     * get the info of opUser
     * @param token token
     * @return opUserResp
     */
    @RequestMapping("info/{token}")
    public Output<OpUserResp> info(@PathVariable(value = "token", required = false) String token){

        if (StringUtils.isBlank(token)){
            OutputUtil.fail(ErrorEnum.INVALID_TOKEN.getCode(), ErrorEnum.INVALID_TOKEN.getMsg());
        }

        String jsonOpUser = this.opUserService.getOpUserFromRedis(token);
        if (StringUtils.isBlank(jsonOpUser)){
            OutputUtil.fail(ErrorEnum.INVALID_TOKEN.getCode(), ErrorEnum.INVALID_TOKEN.getMsg());
        }

        TbOpUser opUser = JSON.parseObject(jsonOpUser, TbOpUser.class);
        OpUserResp opUserResp = new OpUserResp();
        BeanUtils.copyProperties(opUser, opUserResp);
        opUserResp.setRoles(OpUserTypeEnum.map.get(opUser.getRoles()).getMsg());

        return OutputUtil.ok(opUserResp);
    }

    /**
     * logout the account
     * @param request request
     * @return null
     */
    @PostMapping("logout")
    public Output logout(HttpServletRequest request){

        String accessToken = request.getHeader("X-Token");
        // mysql del token
        UpdateWrapper<TbOpUser> updateWr = new UpdateWrapper<>();
        updateWr.eq("token", accessToken);
        updateWr.set("token", "");
        opUserService.update(updateWr);

        // redis del token
        opUserService.delRedisOpUser(accessToken);

        return OutputUtil.ok(null);
    }

}
