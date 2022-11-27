package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.infoservice.entity.DO.InfoTeacher;
import com.tjspace.infoservice.entity.excel.course.ExcelCourseData;
import com.tjspace.infoservice.mapper.InfoTeacherMapper;
import com.tjspace.infoservice.service.InfoTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class InfoTeacherServiceImpl extends ServiceImpl<InfoTeacherMapper, InfoTeacher> implements InfoTeacherService {
    @Autowired
    InfoTeacherService teacherService;

    @Override
    public List<InfoTeacher> getTeacherByName(String... names) {
        QueryWrapper<InfoTeacher> wrapper = new QueryWrapper<>();
        wrapper.in(InfoTeacher.NAME, names);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public InfoTeacher addTeacher(ExcelCourseData excelTeacher) {
        InfoTeacher teacher = new InfoTeacher();
        teacher.setName(excelTeacher.getTeacherName());
        teacher.setTitle(excelTeacher.getTeacherTitle());
        if (teacherService.save(teacher)) {
            return teacher;
        } else {
            return null;
        }
    }
}
