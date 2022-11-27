package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.infoservice.entity.DO.FavCourse;
import com.tjspace.infoservice.mapper.FavCourseMapper;
import com.tjspace.infoservice.service.FavCourseService;
import org.springframework.stereotype.Service;

@Service
public class FavCourseImpl extends ServiceImpl<FavCourseMapper, FavCourse> implements FavCourseService {

    @Override
    public Boolean addOrUpdateFavoriteCourse(String userId, String courseId) {
        QueryWrapper<FavCourse> wrapper = new QueryWrapper<>();
        wrapper.eq(FavCourse.USER_ID, userId)
                .eq(FavCourse.COURSE_ID, courseId);
        FavCourse favCourse = this.getOne(wrapper);
        if (favCourse == null) {
            favCourse = new FavCourse();
            favCourse.setUserId(userId);
            favCourse.setCourseId(courseId);
        } else {
            favCourse.setIsDeleted(!favCourse.getIsDeleted());
        }
        this.saveOrUpdate(favCourse);
        return !favCourse.getIsDeleted();
    }

    @Override
    public Boolean isFavorite(String userId, String courseId) {
        QueryWrapper<FavCourse> wrapper = new QueryWrapper<>();
        wrapper.eq(FavCourse.USER_ID, userId)
                .eq(FavCourse.COURSE_ID, courseId);
        FavCourse favCourse = this.getOne(wrapper);
        return favCourse != null && !favCourse.getIsDeleted();
    }
}
