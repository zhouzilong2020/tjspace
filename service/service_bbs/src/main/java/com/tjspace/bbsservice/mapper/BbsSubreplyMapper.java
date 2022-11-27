package com.tjspace.bbsservice.mapper;

import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjspace.bbsservice.entity.BO.SubreplyListBO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Mapper
public interface BbsSubreplyMapper extends BaseMapper<BbsSubreply> {
    @MapKey("replyId")
    Map<String, SubreplyListBO> selectSubreplyListMap(@Param("replyIdSet") Set<String> replyIdSet);
}
