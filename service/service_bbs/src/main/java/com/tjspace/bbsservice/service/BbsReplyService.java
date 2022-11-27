package com.tjspace.bbsservice.service;

import com.tjspace.bbsservice.entity.DO.BbsReply;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface BbsReplyService extends IService<BbsReply> {

    /**
     * 新增回复
     *
     * @param userId  用户ID
     * @param postId  帖子ID
     * @param content 回复内容
     * @return 回复的完整内容
     */
    @Transactional(rollbackFor = {Exception.class})
    BbsReply addReply(String userId, String postId, String content);

    /**
     * 获取回复所属帖子的ID
     *
     * @param replyId 回复ID;
     * @return 帖子ID
     */
    String getPostId(String replyId);

    /**
     * 查询某个帖子的某页回复、子回复
     *
     * @param currentPage 需求页面
     * @param limit       每页回复数
     * @param postId      帖子ID
     * @return 该页回复、子回复信息
     */
    Map<String, Object> getReplies(Integer currentPage, Integer limit, String postId, Boolean onlyPoster);

    /**
     * 删除某个回复及其子回复
     *
     * @param userId
     * @param replyId 回复ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removeReply(String userId, String replyId);

    /**
     * 删除某个帖子的所有回复及其子回复
     *
     * @param postId 帖子ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removeReplies(String postId);
}
