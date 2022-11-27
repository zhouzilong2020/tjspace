package com.tjspace.infoservice.entity.returnobject;

import lombok.Data;

/**
 * 返回数据的实体类
 *
 * @author zhouzilong
 */
@Data
public class Course {
    private String title;
    private Integer credit;
    private Integer period;
    private Integer checkType;
    private String officialId;
    private String brief;
    private String remark;
    private String teacherName;
    private String teacherTitle;
}
