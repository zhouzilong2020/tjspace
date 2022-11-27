package com.tjspace.evlservice.mapper;

import com.tjspace.evlservice.entity.DO.EvlComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Mapper
public interface EvlCommentMapper extends BaseMapper<EvlComment> {
}
