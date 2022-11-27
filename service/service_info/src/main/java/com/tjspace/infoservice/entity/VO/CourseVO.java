package com.tjspace.infoservice.entity.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tjspace.infoservice.entity.BO.HistoryTeachingBO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据的实体类
 *
 * @author zhouzilong
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseVO {

    private String courseId;

    private String title;
    public static final String TITLE;

    private Integer credit;
    public static final String CREDIT;

    private Integer period;
    public static final String PERIOD;

    private Integer checkType;
    public static final String CHECK_TYPE;

    private Boolean favorite;
    public static final String FAVORITE;

    private String officialId;
    public static final String OFFICIAL_ID;

    private String brief;
    public static final String BRIEF;

    private String remark;
    public static final String REMARK;

    private String majorName;
    public static final String MAJOR_NAME;

    private String deptName;
    public static final String DEPT_NAME;

    private String teacherName;
    public static final String TEACHER_NAME;

    private String teacherTitle;
    public static final String TEACHER_TITLE;

    private String teacherDeptName;
    public static final String TEACHER_DEPT_NAME;

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

    @ApiModelProperty(value = "评价数量")
    private Integer commentCount;
    public static final String COMMENT_COUNT;

    private List<HistoryTeachingBO> historyTeachingList;

    public static final Map<String, String> ATTRIBUTE_MAP = new HashMap<>();

    public static boolean contains(String attribute) {
        return ATTRIBUTE_MAP.containsKey(attribute);
    }

    static {
        TITLE = "course.title as title";
        CREDIT = "credit";
        PERIOD = "period";
        CHECK_TYPE = "check_type";
        FAVORITE = "favorite";
        OFFICIAL_ID = "official_id";
        BRIEF = "brief";
        REMARK = "remark";
        MAJOR_NAME = "info_major.name as major_name";
        DEPT_NAME = "info_dept.name as dept_name";
        TEACHER_NAME = "info_teacher.name as teacher_name";
        TEACHER_TITLE = "info_teacher.title as teacher_title";
        TEACHER_DEPT_NAME = "info_teacher_dept.name as teacher_dept_name";
        AVG_TEACHING_SCORE = "avg_teaching_score";
        AVG_CONTENT_SCORE = "avg_content_score";
        AVG_GRADING_SCORE = "avg_grading_score";
        AVG_WORKLOAD_SCORE = "avg_workload_score";
        AVG_TOT_SCORE = "avg_tot_score";
        COMMENT_COUNT = "comment_count";

        ATTRIBUTE_MAP.put("title", TITLE);
        ATTRIBUTE_MAP.put("credit", CREDIT);
        ATTRIBUTE_MAP.put("period", PERIOD);
        ATTRIBUTE_MAP.put("checkType", CHECK_TYPE);
        ATTRIBUTE_MAP.put("officialId", OFFICIAL_ID);
        ATTRIBUTE_MAP.put("brief", BRIEF);
        ATTRIBUTE_MAP.put("remark", REMARK);
        ATTRIBUTE_MAP.put("majorName", MAJOR_NAME);
        ATTRIBUTE_MAP.put("deptName", DEPT_NAME);
        ATTRIBUTE_MAP.put("teacherName", TEACHER_NAME);
        ATTRIBUTE_MAP.put("teacherTitle", TEACHER_TITLE);
        ATTRIBUTE_MAP.put("teacherDeptName", TEACHER_DEPT_NAME);
        ATTRIBUTE_MAP.put("avgTeachingScore", AVG_TEACHING_SCORE);
        ATTRIBUTE_MAP.put("avgContentScore", AVG_CONTENT_SCORE);
        ATTRIBUTE_MAP.put("avgGradingScore", AVG_GRADING_SCORE);
        ATTRIBUTE_MAP.put("avg_workload_score", AVG_WORKLOAD_SCORE);
        ATTRIBUTE_MAP.put("avgTotScore", AVG_TOT_SCORE);
        ATTRIBUTE_MAP.put("commentCount", COMMENT_COUNT);
    }
}
