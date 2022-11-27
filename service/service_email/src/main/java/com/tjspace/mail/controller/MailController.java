package com.tjspace.mail.controller;


import com.tjspace.mail.service.MailService;
import com.tjspace.mail.vo.EmailTo;
import com.tjspace.mail.vo.EmailVo;
import com.tjspace.utils.commonutils.RandomUtil;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 前端控制器
 *
 * @author Zilong Zhou
 * @since 2020-12-03
 */
@RestController
@RequestMapping("/emailservice")
public class MailController {
    /**
     * 依赖注入：
     * 可以解决环境的切换， 由spring 的container来决定
     * 减少代码的重复
     */
    @Autowired
    private MailService mailService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "发送字符串邮件")
    @PostMapping("sendSimple")
    public UniformResult sendHtml(
            @RequestBody EmailTo emailContent
    ) {
        // 从redis中取数据
        String codeDB = redisTemplate.opsForValue().get(emailContent.getEmailAddr());
        if (!StringUtils.isEmpty(codeDB)) {
            return UniformResult.error().message("邮件发送过于频繁，请稍后再试");
        }
        // 建立vo对象
        EmailVo emailVo = new EmailVo();
        BeanUtils.copyProperties(emailContent, emailVo);
        String code = RandomUtil.getSixBitRandom();
        emailVo.setCode(code);
        Boolean success = mailService.sendSimpleMail(emailVo);
        if (success) {
            // 成功发送则存放到内存数据库中
            redisTemplate.opsForValue().set(emailContent.getEmailAddr(), code, 3, TimeUnit.MINUTES);
            return UniformResult.ok();
        } else {
            return UniformResult.error().message("邮件发送失败");
        }
    }

    @ApiOperation(value = "发送html邮件")
    @PostMapping("sendHtml")
    public UniformResult sendSimple(
            @RequestBody EmailTo emailContent
    ) {
        // 从redis中取数据
        String codeDB = redisTemplate.opsForValue().get(emailContent.getEmailAddr());
        if (!StringUtils.isEmpty(codeDB)) {
            return UniformResult.error().message("邮件发送过于频繁，请稍后再试");
        }
        // 建立vo对象
        EmailVo emailVo = new EmailVo();
        BeanUtils.copyProperties(emailContent, emailVo);
        String code = RandomUtil.getSixBitRandom();
        emailVo.setCode(code);
        Boolean success = mailService.sendHtmlMail(emailVo);
        if (success) {
            // 成功发送则存放到内存数据库中
            redisTemplate.opsForValue().set(emailContent.getEmailAddr(), code, 3, TimeUnit.MINUTES);
            return UniformResult.ok();
        } else {
            return UniformResult.error().message("邮件发送失败");
        }
    }

    @ApiOperation(value = "验证邮箱验证码")
    @GetMapping("validate/email/{addr}/{code}")
    public UniformResult isValidate(
            @PathVariable String code, @PathVariable String addr
    ) {
        // 从redis中取数据
        String codeDB = redisTemplate.opsForValue().get(addr);
        if (StringUtils.isEmpty(codeDB)) {
            return UniformResult.error().message("该邮箱还未通过验证").data("validity", false);
        } else if (codeDB != code) {
            return UniformResult.error().message("验证码错误").data("validity", false);
        } else {
            return UniformResult.ok().message("验证成功").data("validity", true);
        }
    }
}

