package com.tjspace.bbsservice.entity.DO;

import com.baomidou.mybatisplus.annotation.*;
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
 * @author Fu Lin
 * @since 2020-12-5
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BbsPostAttitude对象", description="")
public class BbsPostAttitude implements Serializable {

    /*
      本字段和数据库字段直接对应对应，应当作为mapper接口
     */
    static {
        ID = "id";
        TYPE = "type";
        USER_ID = "user_id";
        POST_ID = "post_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价态度ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    static final public String ID;

    @ApiModelProperty(value = "态度{0:点赞;1:点踩}")
    @TableField(strategy = FieldStrategy.IGNORED)
    private Boolean type;
    static final public String TYPE;

    @ApiModelProperty(value = "提出态度的用户ID")
    private String userId;
    static final public String USER_ID;

    @ApiModelProperty(value = "被提出的帖子的评价ID")
    private String postId;
    static final public String POST_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    static final public String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    static final public String GMT_MODIFIED;
}
