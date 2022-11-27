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
 * static final public 字段与数据库唯一对应
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "InfoCourse对象", description = "课程对象")
public class InfoCourse implements Serializable {

    /*
      本字段和数据库字段直接对应对应，应当作为mapper接口
     */
    static {
        ID = "id";
        OFFICIAL_ID = "official_id";
        GMT_CREATE = "gmt_create";
        GMT_MODIFIED = "gmt_modified";
        TEACHER_ID = "teacher_id";
        PERIOD = "period";
        CHECK_TYPE = "check_type";
        MAJOR_ID = "dept_id";
        CREDIT = "credit";
        BRIEF = "brief";
        TITLE = "title";
    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    static final public String ID;

    @ApiModelProperty(value = "4m3上的课程编号")
    private String officialId;
    static final public String OFFICIAL_ID;

    @ApiModelProperty(value = "课程标题")
    private String title;
    static final public String TITLE;

    @ApiModelProperty(value = "课程简介")
    private String brief;
    static final public String BRIEF;

    @ApiModelProperty(value = "课程学分")
    private Integer credit;
    static final public String CREDIT;

    @ApiModelProperty(value = "所属专业ID")
    private String majorId;
    static final public String MAJOR_ID;

    @ApiModelProperty(value = "课程考查方式 0:考察、1:考试")
    private Integer checkType;
    static final public String CHECK_TYPE;

    @ApiModelProperty(value = "课程学时")
    private Integer period;
    static final public String PERIOD;

    @ApiModelProperty(value = "授课教师ID")
    private String teacherId;
    static final public String TEACHER_ID;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    static final public String GMT_CREATE;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    static final public String GMT_MODIFIED;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        InfoCourse course = (InfoCourse) obj;
        if (this.teacherId == null || course.teacherId == null) {
            return false;
        }
        return course.getOfficialId().equals(this.officialId) && course.getTeacherId().equals(this.teacherId);
    }
}
