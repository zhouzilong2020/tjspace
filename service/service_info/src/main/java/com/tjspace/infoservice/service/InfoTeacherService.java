package com.tjspace.infoservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.DO.InfoTeacher;
import com.tjspace.infoservice.entity.excel.course.ExcelCourseData;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface InfoTeacherService extends IService<InfoTeacher> {


    /**
     * 根据给定字段查出数据库中teacher
     *
     * @param names teacher的属性字段
     * @return
     */
    List<InfoTeacher> getTeacherByName(String... names);


    /**
     * 输入excel导入的数据，向数据库添加该条数据包含的teacher信息
     *
     * @param excelTeacher excel相关数据
     * @return 成功插入的teacher信息
     */
    InfoTeacher addTeacher(ExcelCourseData excelTeacher);
}
