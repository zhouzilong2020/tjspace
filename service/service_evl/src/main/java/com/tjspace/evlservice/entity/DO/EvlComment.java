package com.tjspace.evlservice.entity.DO;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(value="EvlComment对象", description="")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvlComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @JsonProperty("commentId")
    private String id;
    public static final String ID;

    @ApiModelProperty(value = "教师教学评分")
    private Integer teachingScore;
    public static final String TEACHING_SCORE;

    @ApiModelProperty(value = "课程内容评分")
    private Integer contentScore;
    public static final String CONTENT_SCORE;

    @ApiModelProperty(value = "课程成绩评分")
    private Integer gradingScore;
    public static final String GRADING_SCORE;

    @ApiModelProperty(value = "工作量评分")
    private Integer workloadScore;
    public static final String WORKLOAD_SCORE;

    @ApiModelProperty(value = "评分总分")
    private BigDecimal totScore;
    public static final String TOT_SCORE;

    @ApiModelProperty(value = "评价文字内容")
    private String content;
    public static final String CONTENT;

    @ApiModelProperty(value = "点赞数")
    private Integer positiveCount;
    public static final String POSITIVE_COUNT;
    public static final String POSITIVE_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "点踩数")
    private Integer negativeCount;
    public static final String NEGATIVE_COUNT;
    public static final String NEGATIVE_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "点赞数-点踩数")
    @JsonIgnore
    private Integer differentCount;
    public static final String DIFFERENT_COUNT;
    public static final String DIFFERENT_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "点赞数+点踩数")
    @JsonIgnore
    private Integer totCount;
    public static final String TOT_COUNT;
    public static final String TOT_COUNT_UPDATE_SQL;

    @ApiModelProperty(value = "发布评价的用户ID")
    private String userId;
    public static final String USER_ID;

    @ApiModelProperty(value = "所评价的课程ID")
    private String courseId;
    public static final String COURSE_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonProperty("createTime")
    private Date gmtCreate;
    public static final String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    private Date gmtModified;
    public static final String GMT_MODIFIED;

    public static final Map<String,String> ATTRIBUTE_MAP = new HashMap<>();

    static {
        ID = "id";
        TEACHING_SCORE = "teaching_score";
        CONTENT_SCORE = "content_score";
        GRADING_SCORE = "grading_score";
        WORKLOAD_SCORE = "workload_score";
        TOT_SCORE = "tot_score";
        CONTENT = "content";
        POSITIVE_COUNT = "positive_count";
        NEGATIVE_COUNT = "negative_count";
        DIFFERENT_COUNT = "different_count";
        TOT_COUNT = "tot_count";
        USER_ID = "user_id";
        COURSE_ID = "course_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";

        POSITIVE_COUNT_UPDATE_SQL = "positive_count = positive_count +";
        NEGATIVE_COUNT_UPDATE_SQL = "negative_count = negative_count + ";
        DIFFERENT_COUNT_UPDATE_SQL = "different_count = different_count + ";
        TOT_COUNT_UPDATE_SQL = "tot_count = tot_count + ";

        ATTRIBUTE_MAP.put("commentId",ID);
        ATTRIBUTE_MAP.put("teachingScore",TEACHING_SCORE);
        ATTRIBUTE_MAP.put("contentScore",CONTENT_SCORE);
        ATTRIBUTE_MAP.put("gradingScore",GRADING_SCORE);
        ATTRIBUTE_MAP.put("workloadScore",WORKLOAD_SCORE);
        ATTRIBUTE_MAP.put("totScore",TOT_SCORE);
        ATTRIBUTE_MAP.put("content",CONTENT);
        ATTRIBUTE_MAP.put("positiveCount",POSITIVE_COUNT);
        ATTRIBUTE_MAP.put("negativeCount",NEGATIVE_COUNT);
        ATTRIBUTE_MAP.put("createTime",GMT_CREATE);
    }
}
