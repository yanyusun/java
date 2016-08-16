package com.dqys.wms.init;

import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.service.followUp.FollowUpMessageService;
import com.dqys.business.service.service.followUp.FollowUpReadStatus;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pan on 16-5-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/spring-core.xml"})
@Transactional(transactionManager = "transactionManager")
@Component
public class MqClient {
    @Autowired
    private FollowUpReadStatus followUpReadStatus;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "mail_send_queue_online", durable = "true"),
            exchange = @Exchange(value = "mailExchange"))
    )
    public static void sendMailFromMessage(String[] msg) throws Exception {
        sendMail(msg[0], msg[1]);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sms_send_queue_online", durable = "true"),
            exchange = @Exchange(value = "smsExchange"))
    )
    public static void smsMailFromMessage(String[] msg) throws Exception {
//        发送短信
        sendSMS(msg[0], msg[1]);
    }

    /**
     *像数据库中添加更进未读信息
     * @param msg [0]对象id,[1]对像类型,[2]清收阶段
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "follow_message_online", durable = "true"),
            exchange = @Exchange(value = "followMessageExchange"))
    )
    /**
     *
     */
    public void setUnreadFollowMessage(String[] msg) throws Exception {
        // TODO: 16-8-11
        followUpReadStatus.addUnReadMessage(msg);
    }

    private static void sendSMS(String phone, String msg) {
        SmsClient smsClient = new SmsClient();
        smsClient.sendMessage(phone, msg);
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

    /* private static String htmlMailBody(String msg) {
         StringBuffer stringBuffer = new StringBuffer();
         stringBuffer.append("<p>欢迎加入多清平台</p><p>以下是的验证链接，<a href=\"http://www.duoqing.com/auth/confirm_mail?key=")
                 .append(msg)
                 .append("\" target=\"_blank\">请点击确认</a></p>");
         return stringBuffer.toString();
     }*/
    private static String htmlMailBody(String msg) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<p>欢迎加入多清平台</p><p>以下是的验证链接，<a href=\"http://www.iqingsou.com/typeChoose/")
                .append(msg)
                .append("\" target=\"_blank\">请点击确认</a></p>");
        return stringBuffer.toString();
    }



}
