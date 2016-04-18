package com.dqys.core.utils;

import com.dqys.core.constant.SysPropertyTypeEnum;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author by pan on 16-4-12.
 */
public class EmailClientTool implements MessageListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String SYS_MAIL_HOST = "sys_mail_host";
    private static final String SYS_MAIL_SMTP_PORT = "sys_mail_smtp_port";
    private static final String SYS_MAIL_SSL_ON = "sys_mail_ssl_on";
    private static final String SYS_MAIL_FROM = "sys_mail_from";
    private static final String SYS_MAIL_AUTH_USERNAME = "sys_mail_auth_username";
    private static final String SYS_MAIL_AUTH_PASSWORD = "sys_mail_auth_password";

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String[] msgBody = ((RedisSerializer<String[]>) NoSQLWithRedisTool.getMsgRedisTemplate().getValueSerializer()).deserialize(message.getBody());        //0-目标; 1-消息体
        if(null == msgBody || msgBody.length != 2) {
            return;
        }

        this.sendMail(msgBody[0], msgBody[1]);
    }

    public void sendMailFromMessage(String[] msg) throws Exception {
        this.sendMail(msg[0], msg[1]);
    }

    private void sendMail(String to, String msg) {
        try {
            Email emailClient = new HtmlEmail();
            emailClient.setCharset("UTF-8");
            emailClient.setHostName(SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_HOST).getPropertyValue());
            emailClient.setSmtpPort(Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_SMTP_PORT).getPropertyValue()));
            emailClient.setSslSmtpPort(SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_SMTP_PORT).getPropertyValue());
            emailClient.setSSLOnConnect(Boolean.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_SSL_ON).getPropertyValue()));
            emailClient.setFrom(SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_FROM).getPropertyValue());
            emailClient.setAuthenticator(new DefaultAuthenticator(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_AUTH_USERNAME).getPropertyValue(),
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, SYS_MAIL_AUTH_PASSWORD).getPropertyValue()
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


    private String htmlMailBody(String msg) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<p>欢迎加入多清平台</p><p>以下是的验证链接，<a href=\"http://www.duoqing.com/api/confirm_email?key=")
                .append(msg)
                .append("\" target=\"_blank\">请点击确认</a></p>");
        return stringBuffer.toString();
    }
}
