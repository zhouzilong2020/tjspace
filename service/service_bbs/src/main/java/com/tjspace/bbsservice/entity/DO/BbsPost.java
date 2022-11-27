package com.tjspace.bbsservice.entity.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
@ApiModel(value = "BbsPost对象", description = "")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BbsPost implements Serializable {

    /*
      本字段和数据库字段直接对应对应，应当作为mapper接口
     */


    public static final Map<String, String> ATTRIBUTE_MAP = new HashMap<>();

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "帖子ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @JsonProperty("postId")
    private String id;
    static final public String ID;

    @ApiModelProperty(value = "帖子标题")
    private String title;
    static final public String TITLE;

    @ApiModelProperty(value = "帖子内容")
    private String content;
    static final public String CONTENT;

    @ApiModelProperty(value = "发布帖子的用户ID")
    private String userId;
    static final public String USER_ID;

    @ApiModelProperty(value = "点赞数")
    private Integer positiveCount;
    static final public String POSITIVE_COUNT;
    static final public String POSITIVE_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "点踩数")
    private Integer negativeCount;
    static final public String NEGATIVE_COUNT;
    static final public String NEGATIVE_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "回复与子回复总数")
    private Integer replyCount;
    static final public String REPLY_COUNT;
    static final public String REPLY_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty("createTime")
    private Date gmtCreate;
    static final public String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonProperty("latestTime")
    private Date gmtModified;
    static final public String GMT_MODIFIED;

    static {
        ID = "id";
        TITLE = "title";
        CONTENT = "content";
        USER_ID = "user_id";
        POSITIVE_COUNT = "positive_count";
        NEGATIVE_COUNT = "negative_count";
        REPLY_COUNT = "reply_count";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";

        POSITIVE_COUNT_UPDATE_SQL = "positive_count = positive_count + ";
        NEGATIVE_COUNT_UPDATE_SQL = "negative_count = negative_count + ";
        REPLY_COUNT_UPDATE_SQL = "reply_count = reply_count + ";

        ATTRIBUTE_MAP.put("postId", "id");
        ATTRIBUTE_MAP.put( "title", "title");
        ATTRIBUTE_MAP.put("content", "content");
        ATTRIBUTE_MAP.put("userId", "user_id");
        ATTRIBUTE_MAP.put("positiveCount", "positive_count");
        ATTRIBUTE_MAP.put("negativeCount", "negative_count");
        ATTRIBUTE_MAP.put("replyCount", "reply_count");
        ATTRIBUTE_MAP.put("createTime", "gmt_create");
        ATTRIBUTE_MAP.put("latestTime", "gmt_modified");
    }
}
