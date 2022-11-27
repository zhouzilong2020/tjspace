package com.tjspace.evlservice.service;

import com.tjspace.evlservice.entity.DO.EvlComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.evlservice.entity.VO.NewCommentVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface EvlCommentService extends IService<EvlComment> {
    /**
     * 新增评价
     *
     * @param userId  用户ID
     * @param courseId  课程ID
     * @param newCommentVO  新增评价对象
     * @return  完整评价对象
     */
    @Transactional(rollbackFor = {Exception.class})
    EvlComment addComment(String userId, String courseId, NewCommentVO newCommentVO);

    /**
     * 判断某个评价是否存在
     *
     * @param commentId  评价ID
     * @return  是否存在
     */
    Boolean isExist(String commentId);

    /**
     * 按排序分页查询某课程评价
     *
     * @param currentPage  预期页面索引(1-based)
     * @param limit  每页条目数
     * @param courseId  课程ID
     * @param userId
     * @param orderBy  排序方式{desc:降序;asc:升序}
     * @param sort  排序规则
     * @return  该页评价信息
     */
    Map<String,Object> getCommentsByOrder(Integer currentPage, Integer limit, String courseId, String userId, String orderBy, String... sort);

    /**
     * 分页查询个人评价
     *
     * @param currentPage  预期页面索引(1-based)
     * @param limit  每页条目数
     * @param userId  用户ID
     * @param attributes  查询字段
     * @return  该页评价信息
     */
    Map<String,Object> getMyComments(Integer currentPage, Integer limit, String userId, String... attributes);

    /**
     * 更新某个评价点赞或点踩数量
     *
     * @param commentId  评价ID
     * @param posCount  点赞更新数量
     * @param negCount  点踩更新数量
     */
    @Transactional(rollbackFor = {Exception.class})
    void updateAttitudeCount(String commentId, int posCount, int negCount);

    /**
     * 删除某个评价
     *
     * @param userId
     * @param commentId  评价ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removeComment(String userId, String commentId);
}
