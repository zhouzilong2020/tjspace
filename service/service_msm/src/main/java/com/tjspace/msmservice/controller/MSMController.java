package com.tjspace.msmservice.controller;


import com.tjspace.msmservice.service.MSMService;
import com.tjspace.utils.commonutils.RandomUtil;
import com.tjspace.utils.commonutils.UniformResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzilong
 */
@RestController
@RequestMapping("/msmservice")
public class MSMController {
    @Autowired
    private MSMService msmService;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("validate/{code}/{phone}")
    public UniformResult isValidate(@PathVariable String code, @PathVariable String phone) {
        String codeDB = redisTemplate.opsForValue().get(phone);
        if (StringUtils.isEmpty(codeDB)) {
            return UniformResult.error().message("该手机号还未进行验证").data("validity", false);
        } else if (codeDB != code) {
            return UniformResult.error().message("验证码输入错误").data("validity", false);
        } else {
            // 清除内存数据库中改的key
            redisTemplate.delete(phone);
            return UniformResult.ok().message("验证成功").data("validity", true);
        }
    }

    @PostMapping("send/{phone}")
    public UniformResult sendMsm(@PathVariable String phone) {
        // 从redis获取验证码，如果获取到直接返回
        // 说明发送过于频繁
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return UniformResult.error().message("该手机号发送短信过于频繁, 请稍后再试");
        }

        // 得到四位随机数
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>(2);
        param.put("code", code);

        // 调用service发送短信的方法
        Boolean isSend = msmService.send(param, phone);
        if (isSend) {
            // 发送成功，把发送成功验证码放到redis里面
            // 设置有效时间
            redisTemplate.opsForValue().set(phone, code, 3, TimeUnit.MINUTES);
            return UniformResult.ok();
        } else {
            return UniformResult.error().message("短信发送失败");
        }
    }
}
