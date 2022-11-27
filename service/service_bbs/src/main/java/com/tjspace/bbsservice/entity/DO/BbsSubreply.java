package com.tjspace.bbsservice.entity.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "BbsSubreply对象", description = "")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BbsSubreply implements Serializable {

    static {
        ID = "id";
        CONTENT = "content";
        USER_ID = "user_id";
        REPLY_ID = "reply_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子子回复ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @JsonProperty("subreplyId")
    private String id;
    public static final String ID;

    @ApiModelProperty(value = "帖子子回复内容")
    private String content;
    public static final String CONTENT;

    @ApiModelProperty(value = "发布帖子子回复的用户ID")
    private String userId;
    public static final String USER_ID;

    @ApiModelProperty(value = "帖子子回复所属的帖子回复ID")
    private String replyId;
    public static final String REPLY_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty("createTime")
    private Date gmtCreate;
    public static final String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public static final String GMT_MODIFIED;

}
