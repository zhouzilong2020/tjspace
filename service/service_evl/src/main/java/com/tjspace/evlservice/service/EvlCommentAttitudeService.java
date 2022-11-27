package com.tjspace.evlservice.service;

import com.tjspace.evlservice.entity.DO.EvlCommentAttitude;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
public interface EvlCommentAttitudeService extends IService<EvlCommentAttitude> {

    /**
     * 新增或更新某个用户对某个评价的态度
     *
     * @param userId  用户ID
     * @param commentId  评价ID
     * @param type  态度
     * @return  更新后的态度
     */
    @Transactional(rollbackFor = {Exception.class})
    Boolean addOrUpdateAttitude(String userId, String commentId, Boolean type);

    /**
     * 查询某个用户对若干评价的态度
     *
     * @param userId  用户ID
     * @param commentIdSet  若干评价ID
     * @return  评价ID到态度的映射
     */
    Map<String, Boolean> getAttitudes(String userId, Set<String> commentIdSet);

    /**
     * 删除某个评价的态度
     *
     * @param commentId  评价ID
     */
    @Transactional(rollbackFor = {Exception.class})
    void removeAttitude(String commentId);
}
