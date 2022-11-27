package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.infoservice.entity.BO.HistoryTeachingBO;
import com.tjspace.infoservice.entity.DO.InfoCourse;
import com.tjspace.infoservice.entity.DO.InfoTeacher;
import com.tjspace.infoservice.entity.DO.InfoTeaching;
import com.tjspace.infoservice.entity.excel.course.ExcelCourseData;
import com.tjspace.infoservice.mapper.InfoTeachingMapper;
import com.tjspace.infoservice.service.InfoTeachingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class InfoTeachingServiceImpl extends ServiceImpl<InfoTeachingMapper, InfoTeaching> implements InfoTeachingService {

    @Autowired
    InfoTeachingService teachesService;

    @Override
    public InfoTeaching isExist(InfoCourse course, InfoTeacher teacher, ExcelCourseData excelTeaches, Integer year, Integer semester) {
        QueryWrapper<InfoTeaching> wrapper = new QueryWrapper<>();
        wrapper.eq(InfoTeaching.COURSE_ID, course.getId())
                .eq(InfoTeaching.TEACHER_ID, teacher.getId())
                .eq(InfoTeaching.SCHOOL_TIME, excelTeaches.getSchoolTime())
                .eq(InfoTeaching.YEAR, year)
                .eq(InfoTeaching.SEMESTER, semester);
        return teachesService.getOne(wrapper);
    }

    @Override
    public void addTeaches(InfoCourse course, InfoTeacher teacher, ExcelCourseData excelTeaches, Integer year, Integer semester) {
        InfoTeaching teaches = new InfoTeaching();
        teaches.setCourseId(course.getId());
        teaches.setTeacherId(teacher.getId());

        // 将excelTeaches和InfoTeaches中共同字段利用工具一次性赋值
        BeanUtils.copyProperties(teaches, excelTeaches);

        teaches.setSemester(semester);
        teaches.setYear(year);

        teaches.setStartStopWeek(excelTeaches.getStartStopWeek());
        teachesService.save(teaches);
    }

    @Override
    public List<HistoryTeachingBO> selectHistoryTeaching(String courseId, Set<String> attributes) {
        return baseMapper.selectHistoryTeaching(courseId, attributes);
    }

    @Override
    public List<InfoTeaching> selectInfoTeaching(List<InfoTeaching> infoTeachingList) {
        return baseMapper.selectInfoTeaching(infoTeachingList);
    }

}
