package com.tjspace.evlservice.service;

import com.tjspace.evlservice.entity.DO.EvlAgvScore;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface EvlAgvScoreService extends IService<EvlAgvScore> {
    /**
     * 更新某个课程的平均分
     *
     * @param courseId  课程ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void updateAverageScore(String courseId);
}
