package com.tjspace.infoservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.DO.InfoCourse;
import com.tjspace.infoservice.entity.VO.CourseVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface InfoCourseService extends IService<InfoCourse> {


    /**
     * 输入excel导入的数据，向数据库查询是否存在该条teaches信息
     *
     * @param officialId excel相关数据
     * @return 数据库符合条件的该条信息, 不存在则返回null
     */
    List<InfoCourse> getCourseByOfficialId(String... officialId);


    /**
     * 查询课程信息
     *
     * @param userId     用户ID
     * @param courseId   课程ID
     * @param attributes 查询字段
     * @return 课程信息对象
     */
    CourseVO getCourse(String userId, String courseId, String... attributes);

    /**
     * 判断某课程是否存在
     *
     * @param courseId 课程ID
     * @return 是否存在
     */
    Boolean isExist(String courseId);

    /**
     * 分页按院系ID查询课程
     *
     * @param currentPage 预期页面索引(1-based)
     * @param limit       每页条目数
     * @param deptIds     若干院系ID
     * @return 分页课程信息
     */
    Map<String, Object> getCoursesByDeptIds(Integer currentPage, Integer limit, String[] deptIds);

    /**
     * 分页按关键字搜索课程
     *
     * @param currentPage 预期页面索引(1-based)
     * @param limit       每页条目数
     * @param keywords    关键字
     * @return 分页课程信息
     */
    Map<String, Object> getCoursesByKeywords(Integer currentPage, Integer limit, String[] keywords);

    /**
     * 分页查询个人收藏课程
     *
     * @param currentPage 预期页面索引(1-based)
     * @param limit       每页条目数
     * @param userId      用户ID
     * @return 分页课程信息
     */
    Map<String, Object> getMyFavoriteCourses(Integer currentPage, Integer limit, String userId);
}
