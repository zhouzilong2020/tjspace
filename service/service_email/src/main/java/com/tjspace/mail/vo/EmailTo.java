package com.tjspace.mail.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouzilong
 */
@Data
@ApiModel(value = "邮件基本属性")
public class EmailTo {
    @ApiModelProperty(value = "单位/秒", required = false, example = "300")
    private String expireTime = "300";
    public static final String EXPIRE_TIME = "expireTime";

    @ApiModelProperty(value = "邮箱地址", required = true, example = "529620861@qq.com")
    private String emailAddr;
    public static final String EMAIL_ADDR = "emailAddr";

    @ApiModelProperty(value = "操作类型", required = true, example = "邮箱验证")
    private String operationType;
    public static final String OPERATION_TYPE = "operationType";


    @ApiModelProperty(value = "邮件主题", required = false, example = "TJSPACE·同济大学社群邮箱验证")
    private String subject = "TJSPACE·同济大学社群邮箱验证";
    public static final String SUBJECT = "subject";


}
