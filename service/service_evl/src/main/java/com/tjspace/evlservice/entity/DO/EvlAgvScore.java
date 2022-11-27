package com.tjspace.evlservice.entity.DO;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="EvlAgvScore对象", description="")
public class EvlAgvScore implements Serializable {

    static {
        ID = "id";
        AVG_TEACHING_SCORE = "avg_teaching_score";
        AVG_CONTENT_SCORE = "avg_content_score";
        AVG_GRADING_SCORE = "avg_grading_score";
        AVG_WORKLOAD_SCORE = "avg_workload_score";
        AVG_TOT_SCORE = "avg_tot_score";
        COMMENT_COUNT = "comment_count";
        COURSE_ID = "course_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";

    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价均分ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    public static final String ID;

    @ApiModelProperty(value = "教师教学评分均分")
    private BigDecimal avgTeachingScore;
    public static final String AVG_TEACHING_SCORE;

    @ApiModelProperty(value = "课程内容评分均分")
    private BigDecimal avgContentScore;
    public static final String AVG_CONTENT_SCORE;

    @ApiModelProperty(value = "课程成绩评分均分")
    private BigDecimal avgGradingScore;
    public static final String AVG_GRADING_SCORE;

    @ApiModelProperty(value = "工作量评分均分")
    private BigDecimal avgWorkloadScore;
    public static final String AVG_WORKLOAD_SCORE;

    @ApiModelProperty(value = "总评分均分")
    private BigDecimal avgTotScore;
    public static final String AVG_TOT_SCORE;

    @ApiModelProperty(value = "课程ID")
    private String courseId;
    public static final String COURSE_ID;

    @ApiModelProperty(value = "评价数量")
    private Integer commentCount;
    public static final String COMMENT_COUNT;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    public static final String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    private Date gmtModified;
    public static final String GMT_MODIFIED;

}
