package com.tjspace.infoservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.BO.HistoryTeachingBO;
import com.tjspace.infoservice.entity.DO.InfoCourse;
import com.tjspace.infoservice.entity.DO.InfoTeacher;
import com.tjspace.infoservice.entity.DO.InfoTeaching;
import com.tjspace.infoservice.entity.excel.course.ExcelCourseData;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */
public interface InfoTeachingService extends IService<InfoTeaching> {


    /**
     * 输入excel导入的数据，向数据库查询是否存在该条teaches信息
     *
     * @param course       课程信息
     * @param teacher      授课教师信息
     * @param excelTeaches excel相关数据
     * @param semester     开课学期
     * @param year         开课年份
     * @return 创建的teaches数据
     */
    InfoTeaching isExist(InfoCourse course, InfoTeacher teacher, ExcelCourseData excelTeaches, Integer year, Integer semester);

    /**
     * 输入excel导入的数据，向数据库添加该条数据包含的teaches信息
     *
     * @param course       数据库中的course
     * @param teacher      数据库中的teacher
     * @param semester     开课学期
     * @param year         开课年份
     * @param excelTeaches excel相关数据
     */
    void addTeaches(InfoCourse course, InfoTeacher teacher, ExcelCourseData excelTeaches, Integer year, Integer semester);


    /**
     * 查询课程历史开课信息
     *
     * @param courseId   课程ID
     * @param attributes 查询字段
     * @return 历史开课信息列表
     */
    List<HistoryTeachingBO> selectHistoryTeaching(String courseId, Set<String> attributes);

    //TODO 根据teacherId， courseId，year，semester查询开课记录

    List<InfoTeaching> selectInfoTeaching(List<InfoTeaching> infoTeachingList);
}
