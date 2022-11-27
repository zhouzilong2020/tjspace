//package com.tjspace.ucenterservice.com.tjspace.msmservice.service.impl;
//
//import com.tjspace.security.com.tjspace.msmservice.entity.SecurityUser;
//import com.tjspace.ucenterservice.com.tjspace.msmservice.entity.InfoUser;
//import com.tjspace.ucenterservice.com.tjspace.msmservice.service.InfoUserService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 实现spring security的接口类， 定义用户数据库查询服务
// *
// * @author zhouzilong
// */
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    InfoUserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // 从数据库中取出用户信息
//        InfoUser userDB = userService.selectUserByEmail(email);
//
//        // 判断用户是否存在
//        if (userDB == null) {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
//        // 返回UserDetails实现类
//        com.tjspace.security.com.tjspace.msmservice.entity.User curUser = new com.tjspace.security.com.tjspace.msmservice.entity.User();
//        BeanUtils.copyProperties(userDB, curUser);
//
//        List<String> authorities = new ArrayList<>();
//        authorities.add(userDB.getState().toString());
//        SecurityUser securityUser = new SecurityUser(curUser);
//        securityUser.setPermissionValueList(authorities);
//        return securityUser;
//    }
//}
