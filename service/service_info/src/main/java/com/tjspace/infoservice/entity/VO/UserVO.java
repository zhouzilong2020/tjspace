package com.tjspace.infoservice.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户信息对象")
public class UserVO {
    static {
        PASSWORD = "password";
        NICKNAME = "nickname";
        GENDER = "gender";
        PHONE_NUMBER = "phone_number";
        GRADE = "grade";
        DEGREE = "degree";
        AVATAR = "avatar";
        MAJOR_ID = "major_id";
    }

    @ApiModelProperty(value = "MD5加密后的密码")
    private String password;
    public final static String PASSWORD;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    public final static String NICKNAME;

    @ApiModelProperty(value = "性别{0:女;1:男}")
    private Boolean gender;
    public final static String GENDER;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    public final static String PHONE_NUMBER;

    @ApiModelProperty(value = "年级")
    private Integer grade;
    public final static String GRADE;

    @ApiModelProperty(value = "学历{0:本科生;1:研究生;2:博士生}")
    private Integer degree;
    public final static String DEGREE;

    @ApiModelProperty(value = "头像图片(base64)")
    private String avatar;
    public final static String AVATAR;

    @ApiModelProperty(value = "专业ID")
    private String majorId;
    public final static String MAJOR_ID;

}
