package com.tjspace.bbsservice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjspace.bbsservice.entity.DO.BbsReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjspace.bbsservice.entity.VO.ReplyVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface BbsReplyMapper extends BaseMapper<BbsReply> {
    Page<ReplyVO> selectReplyPage(Page<ReplyVO> page, @Param("postId") String postId, @Param("userId") String userId);
}