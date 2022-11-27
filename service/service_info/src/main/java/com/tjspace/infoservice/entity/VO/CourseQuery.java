package com.tjspace.infoservice.entity.VO;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhouzilong
 * @breif 课程查询的传入数据条件封装
 */
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称（模糊查询）")
    private String title;

    @ApiModelProperty(value = "课程所属学院")
    private Integer dept;

    @ApiModelProperty(value = "课程开设学期 1春季 2秋季 3暑假")
    private String season;

    @ApiModelProperty(value = "查询开始时间", example = "2019")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019")
    private String end;
}
