package com.tjspace.mail.service.impl;

import com.tjspace.mail.service.MailService;
import com.tjspace.mail.vo.EmailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * @author zhouzilong
 */
@Service("MailService")
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送最简单的文本邮件
     */
    @Override
    public Boolean sendSimpleMail(EmailVo content) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(content.getEmailAddr());
            mailMessage.setSubject(content.getSubject());
            mailMessage.setText("尊敬的用户， 您好！ 您正在进行" + content.getOperationType() + "操作；您的验证码为：" + content.getCode() + "有效期为" + content.getExpireTime() + "秒");
            mailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            logger.error("发送邮件时发生异常!", e);
            return false;
        }
    }

    /**
     * 发送html邮件
     */
    @Override
    public Boolean sendHtmlMail(EmailVo content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //这里的true表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(content.getEmailAddr());
            messageHelper.setSubject(content.getSubject());
            // 得到html content
            String html = getEmailContent(content);
            messageHelper.setText(html, true);
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    @Override
    public String getEmailContent(EmailVo content) {
        // thymeleaf框架得到静态的html资源
        Context context = new Context();
        context.setVariable(EmailVo.CODE, content.getCode());
        context.setVariable(EmailVo.EXPIRE_TIME, content.getExpireTime());
        context.setVariable(EmailVo.OPERATION_TYPE, content.getOperationType());
        return templateEngine.process("emailTemplate2", context);
    }


}

