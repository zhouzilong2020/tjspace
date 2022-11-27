package com.tjspace.infoservice.entity.DO;

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
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "InfoUser对象", description = "")
public class InfoUser implements Serializable {

    static {
        ID = "id";
        EMAIL = "email";
        PASSWORD = "password";
        NICKNAME = "nickname";
        GENDER = "gender";
        STATE = "state";
        PHONE_NUMBER = "phone_number";
        GRADE = "grade";
        DEGREE = "degree";
        AVATAR = "avatar";
        MAJOR_ID = "major_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    public final static String ID;

    @ApiModelProperty(value = "邮箱用户名，域名统一为@tongji.edu.cn")
    private String email;
    public final static String EMAIL;

    @ApiModelProperty(value = "MD5加密后的密码")
    private String password;
    public final static String PASSWORD;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    public final static String NICKNAME;

    @ApiModelProperty(value = "性别{0:女;1:男}")
    private Boolean gender;
    public final static String GENDER;

    @ApiModelProperty(value = "账号状态{0:正常;1:封禁}")
    private Boolean state;
    public final static String STATE;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    public final static String PHONE_NUMBER;

    @ApiModelProperty(value = "年级")
    private Integer grade;
    public final static String GRADE;

    @ApiModelProperty(value = "学历{0:本科生;1:研究生;2:博士生}")
    private Integer degree;
    public final static String DEGREE;

    @ApiModelProperty(value = "头像")
    private String avatar;
    public final static String AVATAR;

    @ApiModelProperty(value = "专业ID")
    private String majorId;
    public final static String MAJOR_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    public final static String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public final static String GMT_MODIFIED;

}
