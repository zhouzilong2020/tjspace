package com.tjspace.evlservice.mapper;

import com.tjspace.evlservice.entity.DO.EvlAgvScore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Mapper
public interface EvlAgvScoreMapper extends BaseMapper<EvlAgvScore> {
    EvlAgvScore selectAverageScore(@Param("courseId") String courseId);
}
