package com.tjspace.infoservice.service;

import com.tjspace.infoservice.entity.DO.InfoUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.VO.UserVO;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
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
public interface InfoUserService extends IService<InfoUser> {
    /**
     * 查询一系列用户的信息
     *
     * @param userIds  若干用户ID
     * @return 用户ID到用户信息的映射
     */
    Map<String, UserPublicInfoDTO> getUsersPublicInfo(Set<String> userIds, String... attributes);

    /**
     * 更新用户信息
     *
     * @param userId  用户ID
     * @param userVO  用户信息对象
     */
    @Transactional(rollbackFor = {Exception.class})
    void updateUserInfo(String userId, UserVO userVO);
}
