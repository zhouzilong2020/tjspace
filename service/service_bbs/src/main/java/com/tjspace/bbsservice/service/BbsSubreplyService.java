package com.tjspace.bbsservice.service;

import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.bbsservice.entity.BO.SubreplyListBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface BbsSubreplyService extends IService<BbsSubreply> {

    /**
     * 新增子回复
     *
     * @param userId  用户ID
     * @param replyId 回复ID
     * @param content 子回复内容
     * @return 子回复的完整内容
     */
    @Transactional(rollbackFor = {Exception.class})
    BbsSubreply addSubreply(String userId, String replyId, String content);

    /**
     * 查询若干回复的子回复
     *
     * @param replyIdList 若干回复ID
     * @return 回复ID到子回复列表的映射
     */
    Map<String, SubreplyListBO> getSubreplies(Set<String> replyIdList);

    /**
     * 删除某个子回复
     *
     * @param userId
     * @param subreplyId 子回复ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removeSubreply(String userId, String subreplyId);

    /**
     * 删除某个回复的子回复
     *
     * @param replyId 回复ID
     * @return 删除的子回复数量
     */
    @Transactional(rollbackFor = {Exception.class})
    int removeSubreplies(String replyId);

    /**
     * 删除若干回复的子回复
     *
     * @param replyIdList 若干回复ID
     * @return 删除的子回复数量
     */
    @Transactional(rollbackFor = {Exception.class})
    int removeSubreplies(List<String> replyIdList);

}
