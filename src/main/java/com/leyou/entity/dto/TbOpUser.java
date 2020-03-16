package com.leyou.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author generator
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbOpUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 管理员权限角色
     */
    private Integer roles;

    /**
     * 管理员账户名
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;


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

    /**
     * 真实姓名
     */
    private String realName;


}
