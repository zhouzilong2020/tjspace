package com.tjspace.infoservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.DO.FavCourse;
import org.springframework.transaction.annotation.Transactional;

public interface FavCourseService extends IService<FavCourse> {

    /**
     * 新增或更新收藏课程
     *
     * @param userId   用户ID
     * @param courseId 课程ID
     * @return 是否收藏
     */
    @Transactional(rollbackFor = {Exception.class})
    Boolean addOrUpdateFavoriteCourse(String userId, String courseId);

    /**
     * 判断是否收藏课程
     *
     * @param userId   用户ID
     * @param courseId 课程ID
     * @return 是否收藏
     */
    Boolean isFavorite(String userId, String courseId);
}
