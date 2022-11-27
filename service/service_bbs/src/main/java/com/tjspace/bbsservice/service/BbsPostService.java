package com.tjspace.bbsservice.service;

import com.tjspace.bbsservice.entity.DO.BbsPost;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.bbsservice.entity.VO.NewPostVO;
import com.tjspace.bbsservice.entity.VO.PostBriefVO;
import com.tjspace.servicebase.exception.MyException;
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
public interface BbsPostService extends IService<BbsPost> {

    /**
     * 判断某个帖子是否存在
     *
     * @param postId 帖子ID
     * @return 是否存在
     */
    Boolean isExist(String postId);

    /**
     * 新增帖子信息
     *
     * @param userId  发布的用户ID
     * @param newPostVO   新增帖子对象
     * @return 完整帖子信息
     */
    @Transactional(rollbackFor = {Exception.class})
    BbsPost addPost(String userId, NewPostVO newPostVO);

    /**
     * 查询某个帖子的若干字段
     *
     * @param postId     帖子ID
     * @param attributes 若干字段名
     * @return 查询结果
     * @throws MyException 帖子不存在
     */
    BbsPost getPost(String postId, String... attributes) throws MyException;

    /**
     * 获取某个帖子的简略信息
     *
     * @param userId 查询的用户ID
     * @param postId 帖子ID
     * @return 帖子简略信息
     */
    PostBriefVO getPostBrief(String userId, String postId);

    /**
     * 根据排序条件获取某页帖子信息
     *
     * @param currentPage 预期的页面索引
     * @param limit   每页条目数
     * @param userId
     * @param orderBy 排序方式{desc:降序;acs:升序}
     * @param sort    排序规则(若干个Post类的成员变量名)
     * @return 帖子信息、实际的页面索引、总页数
     */
    Map<String, Object> getPostsByOrder(Integer currentPage, Integer limit, String userId, String orderBy, String... sort);

    /**
     * 获取某页个人历史发帖信息
     *
     * @param currentPage 预期的页面索引
     * @param limit       每页条目数
     * @param userId      用户ID
     * @return 帖子信息、实际的页面索引、总页数
     */
    Map<String, Object> getMyPosts(Integer currentPage, Integer limit, String userId, String... attributes);

    /**
     * 将某个帖子的回复数加1
     *
     * @param postId 帖子ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void increaseReplyCount(String postId);

    /**
     * 将某个帖子的回复数减去一定数量
     *
     * @param postId 帖子ID
     * @param count  减少的数量
     */
    @Transactional(rollbackFor = {Exception.class})
    void decreaseReplyCount(String postId, int count);

    /**
     * 更新某个帖子的点赞数和点踩数
     *
     * @param postId  帖子ID
     * @param posCount  点赞数变更值
     * @param negCount  点踩数变更值
     */
    @Transactional(rollbackFor = {Exception.class})
    void updateAttitudeCount(String postId, int posCount, int negCount);

    /**
     * 删除某个帖子及其回复、子回复、态度
     *
     * @param userId
     * @param postId 帖子ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removePost(String userId, String postId);
}
