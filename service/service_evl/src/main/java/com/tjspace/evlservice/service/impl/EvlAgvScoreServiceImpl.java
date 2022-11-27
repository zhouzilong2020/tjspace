package com.tjspace.evlservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tjspace.evlservice.entity.DO.EvlAgvScore;
import com.tjspace.evlservice.mapper.EvlAgvScoreMapper;
import com.tjspace.evlservice.service.EvlAgvScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class EvlAgvScoreServiceImpl extends ServiceImpl<EvlAgvScoreMapper, EvlAgvScore> implements EvlAgvScoreService {

    @Override
    public void updateAverageScore(String courseId) {
        EvlAgvScore evlAgvScore = baseMapper.selectAverageScore(courseId);
        UpdateWrapper<EvlAgvScore> wrapper = new UpdateWrapper<>();
        wrapper.eq(EvlAgvScore.COURSE_ID, courseId);
        this.update(evlAgvScore, wrapper);
    }
}
