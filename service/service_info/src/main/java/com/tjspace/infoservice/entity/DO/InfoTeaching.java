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
@ApiModel(value = "InfoTeaches对象", description = "")
public class InfoTeaching implements Serializable {
    static {
        ID = "id";
        SEMESTER = "semester";
        YEAR = "year";
        TEACHER_ID = "teacher_id";
        COURSE_ID = "course_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
        RATED_NUMBER = "rared_number";
        SELECTED_NUMBER = "selected_number";
        START_STOP_WEEK = "start_stop_week";
        SCHOOL_TIME = "school_time";
        REMARK = "remark";
        OTHER = "other";
    }


    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "备注")
    private String remark;
    static final public String REMARK;

    @ApiModelProperty(value = "授课ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    final static public String ID;

    @ApiModelProperty(value = "授课学期{0:春;1:夏;2:秋;3:冬}")
    private Integer semester;
    final static public String SEMESTER;

    @ApiModelProperty(value = "授课学年")
    private Integer year;
    final static public String YEAR;

    @ApiModelProperty(value = "教师ID")
    private String teacherId;
    final static public String TEACHER_ID;

    @ApiModelProperty(value = "课程ID")
    private String courseId;
    final static public String COURSE_ID;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    final static public String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    final static public String GMT_MODIFIED;

    @ApiModelProperty(value = "额定人数(人数上限)")
    private Integer ratedNumber;
    final static public String RATED_NUMBER;

    @ApiModelProperty(value = "选课人数(实际选课的人数)")
    private Integer selectedNumber;
    final static public String SELECTED_NUMBER;

    @ApiModelProperty(value = "始止周")
    private String startStopWeek;
    final static public String START_STOP_WEEK;

    @ApiModelProperty(value = "上课时间")
    private String schoolTime;
    final static public String SCHOOL_TIME;


    @ApiModelProperty(value = "其他")
    private String other;
    final static public String OTHER;

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        if (o == this) {
            return true;
        }
        InfoTeaching teaching = (InfoTeaching) o;
        return teaching.getYear().equals(getYear())
                && teaching.getSemester().equals(getSemester())
                && teaching.getTeacherId().equals(getTeacherId())
                && teaching.getCourseId().equals(getCourseId())
                && teaching.getSchoolTime().equals(getSchoolTime());
    }
}
