package test;

import com.tjspace.mail.MailApplication;
import com.tjspace.mail.service.MailService;
import com.tjspace.mail.vo.EmailVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = MailApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    private EmailVo defaultTemplate;

    @Test
    public void sendHtml() {
        defaultTemplate = new EmailVo();
        defaultTemplate.setCode("123123123");
        defaultTemplate.setEmailAddr("529620861@qq.com");
        defaultTemplate.setExpireTime("300");
        defaultTemplate.setOperationType("邮箱验证");
        defaultTemplate.setSubject("TJSPACE邮箱验证");
        mailService.sendHtmlMail(defaultTemplate);

    }

}

