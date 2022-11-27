package com.tjspace.evlservice.entity.DO;

import com.baomidou.mybatisplus.annotation.*;

import java.time.Year;
import java.util.Date;

import java.io.Serializable;
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
@ApiModel(value="EvlCommentAttitude对象", description="")
public class EvlCommentAttitude implements Serializable {

    static {
        ID = "id";
        TYPE = "type";
        USER_ID = "user_id";
        COMMENT_ID = "comment_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价态度ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    public static final String ID;

    @ApiModelProperty(value = "态度{0:点赞;1:点踩}")
    @TableField(strategy = FieldStrategy.IGNORED)
    private Boolean type;
    public static  final  String TYPE;

    @ApiModelProperty(value = "提出态度的用户ID")
    private String userId;
    public static final String USER_ID;

    @ApiModelProperty(value = "被提出态度的评价ID")
    private String commentId;
    public static final String COMMENT_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    public static final String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public static final String GMT_MODIFIED;

}
