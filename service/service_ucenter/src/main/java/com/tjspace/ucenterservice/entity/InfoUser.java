package com.tjspace.ucenterservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-03
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "InfoUser对象", description = "")
public class InfoUser implements Serializable {
    static {
        EMAIL = "email";
        PASSWORD = "password";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "邮箱用户名，域名统一为@tongji.edu.cn")
    private String email;
    public static final String EMAIL;

    @ApiModelProperty(value = "微信openid")
    private String wechatOpenId;

    @ApiModelProperty(value = "MD5加密后的密码")
    private String password;
    public static final String PASSWORD;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别{0:未知;1:男;2:女}")
    private Boolean gender;

    @ApiModelProperty(value = "账号状态{0:正常;1:封禁}")
    private Boolean state;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "学历{0:本科生;1:研究生;2:博士生}")
    private Boolean degree;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "专业ID")
    private String majorId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
