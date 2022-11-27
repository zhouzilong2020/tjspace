package com.tjspace.infoservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjspace.infoservice.entity.BO.HistoryTeachingBO;
import com.tjspace.infoservice.entity.DO.InfoTeaching;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
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
public interface InfoTeachingMapper extends BaseMapper<InfoTeaching> {

    /**
     * 查询某课程历史开课信息
     *
     * @param courseId     课程ID
     * @param attributeSet 查询字段
     * @return 历史开课信息列表
     */
    List<HistoryTeachingBO> selectHistoryTeaching(@Param("courseId") String courseId, @Param("attributeSet") Set<String> attributeSet);

    List<InfoTeaching> selectInfoTeaching(@Param("infoTeachingList") List<InfoTeaching> infoTeachingList);
}
