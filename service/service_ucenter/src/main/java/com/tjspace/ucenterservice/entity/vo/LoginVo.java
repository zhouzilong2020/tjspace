package com.tjspace.ucenterservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouzilong
 * @breif 用户查询封装
 */
@Data
@ApiModel(value = "查询对象", description = "")
public class LoginVo {


    @ApiModelProperty(value = "手机号（可选）", required = false)
    private String phoneNumber;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String password;

}
