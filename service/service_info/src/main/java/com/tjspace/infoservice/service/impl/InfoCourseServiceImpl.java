package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.infoservice.entity.BO.HistoryTeachingBO;
import com.tjspace.infoservice.entity.DO.InfoCourse;
import com.tjspace.infoservice.entity.VO.CourseVO;
import com.tjspace.infoservice.mapper.InfoCourseMapper;
import com.tjspace.infoservice.service.FavCourseService;
import com.tjspace.infoservice.service.InfoCourseService;
import com.tjspace.infoservice.service.InfoTeachingService;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.Const;
import com.tjspace.utils.commonutils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class InfoCourseServiceImpl extends ServiceImpl<InfoCourseMapper, InfoCourse> implements InfoCourseService {

    @Autowired
    private InfoCourseService infoCourseService;

    @Autowired
    private InfoTeachingService infoTeachingService;

    @Autowired
    private FavCourseService favCourseService;

    @Override
    public CourseVO getCourse(String userId, String courseId, String... attributes) {

        Set<String> courseAttributeSet = new HashSet<>();
        Set<String> historyTeachingAttributeSet = new HashSet<>();
        Boolean isFavorite = null;
        if (attributes != null) {
            for (String attribute : attributes) {
                if (CourseVO.ATTRIBUTE_MAP.containsKey(attribute)) {
                    courseAttributeSet.add(CourseVO.ATTRIBUTE_MAP.get(attribute));
                } else if (HistoryTeachingBO.ATTRIBUTE_MAP.containsKey(attribute)) {
                    historyTeachingAttributeSet.add(HistoryTeachingBO.ATTRIBUTE_MAP.get(attribute));
                } else if (attribute.equals(CourseVO.FAVORITE)) {
                    isFavorite = favCourseService.isFavorite(userId, courseId);
                } else {
                    throw new MyException(ResultCode.BAD_REQUEST, "Undefined Attribute: " + attribute);
                }
            }
        }
        CourseVO course = null;
        List<HistoryTeachingBO> historyTeachingList = null;
        if (courseAttributeSet.size() > 0 || historyTeachingAttributeSet.size() == 0) {
            course = baseMapper.selectCourseInfo(courseId, courseAttributeSet);
        } else if (isExist(courseId)) {
            course = new CourseVO();
        }
        if (course == null) {
            throw new MyException(ResultCode.NOT_FOUND, "课程不存在");
        }
        course.setFavorite(isFavorite);

        if (historyTeachingAttributeSet.size() > 0 || courseAttributeSet.size() == 0) {
            historyTeachingList = infoTeachingService.selectHistoryTeaching(courseId, historyTeachingAttributeSet);
            if (historyTeachingList == null) {
                course.setHistoryTeachingList(new ArrayList<>());
            } else {
                course.setHistoryTeachingList(historyTeachingList);
            }
        }

        return course;
    }

    @Override
    public Boolean isExist(String courseId) {
        QueryWrapper<InfoCourse> wrapper = new QueryWrapper<>();
        wrapper.eq(InfoCourse.ID, courseId);
        return this.count(wrapper) > 0;
    }

    @Override
    public Map<String, Object> getCoursesByDeptIds(Integer currentPage, Integer limit, String[] deptIds) {
        Map<String, Object> returnMap = new HashMap<>();

        Set<String> deptIdSet = new HashSet<>(Arrays.asList(deptIds));
        Page<CourseVO> coursePage = new Page<>(currentPage, limit);
        baseMapper.selectCoursesByDeptIds(coursePage, deptIdSet);

        returnMap.put("courseList", coursePage.getRecords());
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, coursePage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, coursePage.getPages()));
        return returnMap;
    }

    @Override
    public Map<String, Object> getCoursesByKeywords(Integer currentPage, Integer limit, String[] keywords) {
        Map<String, Object> returnMap = new HashMap<>();

        Page<CourseVO> coursePage = new Page<>(currentPage, limit);
        if (keywords.length > 0) {
            baseMapper.searchCourse(coursePage, keywords);
        }
        returnMap.put("courseList", coursePage.getRecords());
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, coursePage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, coursePage.getPages()));
        return returnMap;
    }

    @Override
    public Map<String, Object> getMyFavoriteCourses(Integer currentPage, Integer limit, String userId) {
        Map<String, Object> returnMap = new HashMap<>();

        Page<CourseVO> coursePage = new Page<>(currentPage, limit);
        baseMapper.selectCoursesByFavorite(coursePage, userId);

        returnMap.put("courseList", coursePage.getRecords());
        returnMap.put(Const.CURRENT_PAGE, Math.max(1, coursePage.getCurrent()));
        returnMap.put(Const.TOTAL_PAGE, Math.max(1, coursePage.getPages()));
        return returnMap;
    }

    /**
     * 判断info_course课程数据库是否有该课程, 判断指标为以下所有字段相同
     *
     * @param officialId excel中字段
     * @return 数据库中该字段表
     */
    @Override
    public List<InfoCourse> getCourseByOfficialId(String... officialId) {
        QueryWrapper<InfoCourse> wrapper = new QueryWrapper<>();
        wrapper.in(InfoCourse.OFFICIAL_ID, officialId);
        return baseMapper.selectList(wrapper);
    }


}
