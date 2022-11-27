package com.tjspace.ucenterservice.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouzilong
 */
@Data
public class RegisterVo {

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String password;
}
