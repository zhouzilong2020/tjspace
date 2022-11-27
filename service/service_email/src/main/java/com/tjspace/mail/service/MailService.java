package com.tjspace.mail.service;

import com.tjspace.mail.vo.EmailVo;

/**
 * @author zhouzilong
 */
public interface MailService {
    Boolean sendSimpleMail(EmailVo content);

    Boolean sendHtmlMail(EmailVo content);

    String getEmailContent(EmailVo content);

}
