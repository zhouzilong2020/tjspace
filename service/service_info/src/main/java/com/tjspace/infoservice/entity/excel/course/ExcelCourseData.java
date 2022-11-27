package com.tjspace.infoservice.entity.excel.course;

import com.alibaba.excel.annotation.ExcelProperty;
import com.tjspace.infoservice.converter.CheckTypeConverter;
import lombok.Data;

/**
 * @author zhouzilong
 */
@Data
public class ExcelCourseData {

    /**
     * 课程编号
     */
    @ExcelProperty(index = 0)
    private String officialId;

    /**
     * 课程名
     */
    @ExcelProperty(index = 1)
    private String title;

    /**
     * 学时
     */
    @ExcelProperty(index = 2)
    private Integer period;

    /**
     * 学分
     */
    @ExcelProperty(index = 3)
    private Integer credit;


    /**
     * 考查方式
     */
    @ExcelProperty(index = 4, converter = CheckTypeConverter.class)
    private Integer checkType;

    /**
     * 始止周
     */
    @ExcelProperty(index = 5)
    private String startStopWeek;

    /**
     * 教师名
     */
    @ExcelProperty(index = 6)
    private String teacherName;

    /**
     * 职称
     */
    @ExcelProperty(index = 7)
    private String teacherTitle;

    /**
     * 额定人数
     */
    @ExcelProperty(index = 8)
    private Integer ratedNumber;

    /**
     * 选课人数
     */
    @ExcelProperty(index = 9)
    private Integer selectedNumber;

    /**
     * 上课时间
     */
    @ExcelProperty(index = 10)
    private String schoolTime;

    /**
     * 选课备注
     */
    @ExcelProperty(index = 11)
    private String remark;

    /**
     * 其他说明
     */
    @ExcelProperty(index = 12)
    private String other;

}
