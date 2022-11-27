package com.tjspace.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.ucenterservice.entity.InfoUser;
import com.tjspace.ucenterservice.entity.vo.LoginVo;
import com.tjspace.ucenterservice.entity.vo.RegisterVo;
import com.tjspace.ucenterservice.mapper.InfoUserMapper;
import com.tjspace.ucenterservice.service.InfoUserService;
import com.tjspace.utils.commonutils.JwtUtils;
import com.tjspace.utils.commonutils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-03
 */
@Service
public class InfoUserServiceImpl extends ServiceImpl<InfoUserMapper, InfoUser> implements InfoUserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String loginUser(LoginVo user) {
        // 获取登录信息
        String email = user.getEmail();
        String password = user.getPassword();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new MyException(20001, "用户名或密码为空");
        }
        // 判断email是否正确
        QueryWrapper<InfoUser> wrapper = new QueryWrapper<>();
        wrapper.eq(InfoUser.EMAIL, email);
        InfoUser userDB = baseMapper.selectOne(wrapper);
        if (userDB == null) {
            throw new MyException(20001, "用户不存在");
        }
        // 判断密码, 判断MD5加密后的方法
        if (!MD5.encrypt(password).equals(userDB.getPassword())) {
            throw new MyException(20001, "密码错误");
        }
        // 判断是否禁用
        if (userDB.getState()) {
            throw new MyException(20001, "用户被封禁，请联系系统管理员");
        }
        // 登录成功 生成JWT token
        String token = JwtUtils.getJwtToken(userDB.getId(), userDB.getNickname());
        // 如果redis中有数据，先删除
        String tokenDB = redisTemplate.opsForValue().get(userDB.getId());
        if (tokenDB != null) {
            redisTemplate.delete(userDB.getId());
        }
        // 存放到redis中, 设置2h过期
        redisTemplate.opsForValue().set(userDB.getId(), token, 2, TimeUnit.HOURS);
        return token;
    }

    @Override
    public void logout(String token) {
        String userId = JwtUtils.getUserIdByJwtToken(token);
        redisTemplate.delete(userId);
    }

    @Override
    public String registerUser(RegisterVo user) {
        String email = user.getEmail();
        String password = user.getPassword();
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new MyException(20001, "邮箱或者密码为空");
        }
        // 判断是否存在email
        QueryWrapper<InfoUser> wrapper = new QueryWrapper<>();
        wrapper.eq(InfoUser.EMAIL, email);
        Integer cnt = baseMapper.selectCount(wrapper);
        if (cnt != 0) {
            throw new MyException(20001, "该邮箱已经被注册过了");
        }
        // 生成新用户
        InfoUser newUser = new InfoUser();
        newUser.setEmail(email);
        newUser.setPassword(MD5.encrypt(password));

        this.save(newUser);
        InfoUser userDB = baseMapper.selectOne(wrapper);
        // 注册成功 生成JWT token
        String token = JwtUtils.getJwtToken(userDB.getId(), userDB.getNickname());
        // 存放到redis中, 设置2h过期
        redisTemplate.opsForValue().set(userDB.getId(), token, 2, TimeUnit.HOURS);
        return token;
    }

    @Override
    public InfoUser selectUserByEmail(String email) {
        QueryWrapper<InfoUser> wrapper = new QueryWrapper<>();
        wrapper.eq(InfoUser.EMAIL, email);
        return this.getOne(wrapper);
    }

    @Override
    public Integer countRegisterDay(String date) {
        return baseMapper.countRegisterDay(date);
    }

}
