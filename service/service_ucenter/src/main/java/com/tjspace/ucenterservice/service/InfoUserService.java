package com.tjspace.ucenterservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.ucenterservice.entity.InfoUser;
import com.tjspace.ucenterservice.entity.vo.LoginVo;
import com.tjspace.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-03
 */
public interface InfoUserService extends IService<InfoUser> {
    /**
     * 登录用户
     *
     * @param user 用户信息
     * @return 返回token
     */
    String loginUser(LoginVo user);

    /**
     * 退出登录
     */
    void logout(String token);


    /**
     * 注册
     *
     * @param user
     * @return
     */
    String registerUser(RegisterVo user);

    /**
     * 根据email选出用户
     */
    InfoUser selectUserByEmail(String email);

    Integer countRegisterDay(String date);

}
