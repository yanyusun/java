package com.dqys.core.utils;

import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by pan on 16-5-26.
 */
@Component
public class EmailClientTool {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "mail_send_queue", durable = "true"),
            exchange = @Exchange(value = "mailExchange"))
    )
    public static void sendMailFromMessage(String[] msg) throws Exception {
        sendMail(msg[0], msg[1]);
    }

    private static void sendMail(String to, String msg) {
        try {
            Email emailClient = new HtmlEmail();
            emailClient.setCharset("UTF-8");
            emailClient.setHostName(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_HOST).getPropertyValue());
            emailClient.setSmtpPort(Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_SMTP_PORT).getPropertyValue()));
            emailClient.setSslSmtpPort(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_SMTP_PORT).getPropertyValue());
            emailClient.setSSLOnConnect(Boolean.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_SSL_ON).getPropertyValue()));
            emailClient.setFrom(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_FROM).getPropertyValue());
            emailClient.setAuthenticator(new DefaultAuthenticator(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_AUTH_USERNAME).getPropertyValue(),
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_MAIL_AUTH_PASSWORD).getPropertyValue()
            ));

            emailClient.addTo(to);
            emailClient.setSubject("多清平台帐号验证邮件");
            emailClient.setMsg(htmlMailBody(msg));
            emailClient.send();
        } catch (EmailException e) {
            e.printStackTrace();
            LogManager.getLogger("bizAsync").debug("发送邮件失败,目标:" + to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String htmlMailBody(String msg) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<p>欢迎加入多清平台</p><p>以下是的验证链接，<a href=\"http://www.duoqing.com/auth/confirm_mail?key=")
                .append(msg)
                .append("\" target=\"_blank\">请点击确认</a></p>");
        return stringBuffer.toString();
    }


}
