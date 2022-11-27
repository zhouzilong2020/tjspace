package com.tjspace.infoservice.entity.BO;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.infoservice.entity.VO.CourseVO;
import lombok.Data;

/**
 * @author zhouzilong
 */
@Data
public class DepartmentBO {
    private String deptName;
    private String id;

    private Page<CourseVO> courses = new Page<>();
}
