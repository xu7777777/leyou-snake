package com.leyou.entity.vo;

import lombok.Data;

@Data
public class OpUserResp {

    /**
     * id
     */
    private Long id;

    /**
     * 管理员权限角色
     */
    private String roles;

    /**
     * 管理员账户名
     */
    private String name;

    /**
     * 管理员头像
     */
    private String avatar;

    /**
     * 管理员介绍，例如对权限的介绍
     */
    private String introduction;

    /**
     * token
     */
    private String token;


}
