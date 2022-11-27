package com.tjspace.infoservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.infoservice.entity.DO.InfoCourse;
import com.tjspace.infoservice.entity.VO.CourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */
@Mapper
public interface InfoCourseMapper extends BaseMapper<InfoCourse> {
    /**
     * 查询课程信息
     *
     * @param courseId  课程ID
     * @param attributeSet  查询字段
     * @return  课程信息
     */
    CourseVO selectCourseInfo(@Param("courseId") String courseId, @Param("attributeSet") Set<String> attributeSet);

    /**
     * 分页按关键字搜索课程
     *
     * @param page  分页对象
     * @param keywords  关键字
     * @return  分页查询结果
     */
    Page<CourseVO> searchCourse(Page<CourseVO> page,@Param("keywords") String[] keywords);

    /**
     * 分页按院系ID搜索课程
     *
     * @param page  分页对象
     * @param deptIdSet  若干院系ID
     * @return  分页查询结果
     */
    Page<CourseVO> selectCoursesByDeptIds(Page<CourseVO> page, @Param("deptIdSet") Set<String> deptIdSet);

    /**
     * 分页查询个人收藏课程
     *
     * @param page  分页对象
     * @param userId  用户ID
     * @return  分页查询结果
     */
    Page<CourseVO> selectCoursesByFavorite(Page<CourseVO> page,@Param("userId") String userId);
}
