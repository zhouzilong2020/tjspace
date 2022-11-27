package com.tjspace.infoservice.entity.returnobject;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author zhouzilong
 */
@Data
public class Department {
    private String deptName;
    private String id;

    private Page<Course> courses = new Page<>();
}
