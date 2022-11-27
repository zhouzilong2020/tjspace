package com.tjspace.ucenterservice.controller;


import com.tjspace.ucenterservice.entity.InfoUser;
import com.tjspace.ucenterservice.entity.vo.LoginVo;
import com.tjspace.ucenterservice.entity.vo.RegisterVo;
import com.tjspace.ucenterservice.service.InfoUserService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 前端控制器
 *
 * @author Zilong Zhou
 * @since 2020-12-03
 */
@RestController
@RequestMapping("/ucenterservice")
public class InfoUserController {
    /**
     * 依赖注入：
     * 可以解决环境的切换， 由spring 的container来决定
     * 减少代码的重复
     */
    @Autowired
    private InfoUserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //    TODO:通过手机号登录
    //    TODO:验证手机号


    @PostMapping("login/{phone}")
    public UniformResult loginUserPhone(
            @RequestParam String phone
    ) {
        String token = userService.loginUser(null);

        return UniformResult.ok().data("token", token);
    }

    @PostMapping("login")
    public UniformResult loginUser(
            @ApiParam(value = "登录用户")
            @RequestBody LoginVo user
    ) {
        String token = userService.loginUser(user);
        return UniformResult.ok().data("token", token);
    }


    @PostMapping("register")
    public UniformResult registerUser(
            @RequestBody RegisterVo user
    ) {
        String token = userService.registerUser(user);
        return UniformResult.ok().data("token", token);
    }

    @GetMapping("getUserInfo")
    public UniformResult getUserInfo(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        InfoUser user = userService.getById(userId);
        user.setPassword("");
        return UniformResult.ok().data("userInfo", user);
    }


    @GetMapping("countRegister/{date}")
    public UniformResult countRegister(@PathVariable String date) {
        Integer count = userService.countRegisterDay(date);
        return UniformResult.ok().data("countRegister", count);
    }


}

