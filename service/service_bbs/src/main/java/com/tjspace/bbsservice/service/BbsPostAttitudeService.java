package com.tjspace.bbsservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.bbsservice.entity.DO.BbsPostAttitude;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Fu Lin
 * @since 2020-12-5
 */
public interface BbsPostAttitudeService extends IService<BbsPostAttitude> {

    /**
     * 更新对帖子的态度
     *
     * @param userId 更新态度的用户ID
     * @param postId 需要更新的帖子ID
     * @param type   新态度{0:点赞;1:点踩}
     * @return 更新后的态度
     */
    @Transactional(rollbackFor = {Exception.class})
    Boolean addOrUpdateAttitude(String userId, String postId, Boolean type);

    /**
     * 查询用户对帖子是否进行过点赞/踩
     *
     * @param postId 帖子ID
     * @param userId 用户ID
     * @return 帖子态度
     */
    Boolean getAttitude(String userId, String postId);

    /**
     * 查询用户对一系列帖子是否进行过点赞/踩
     *
     * @param userId     用户ID
     * @param postIdSet 若干帖子ID
     * @return 帖子ID到态度的映射
     */
    Map<String, Boolean> getAttitudes(String userId, Set<String> postIdSet);

    /**
     * 删除某个帖子的所有用户态度
     *
     * @param postId 帖子ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removeAttitude(String postId);
}
