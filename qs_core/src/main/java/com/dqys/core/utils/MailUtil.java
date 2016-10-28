package com.dqys.core.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by mkfeng on 2016/8/10.
 */
public class MailUtil {
    private Properties properties;

    {
        properties = getProp();
    }

    /**
     * 加载项目redis连接属性文件
     */
    private Properties getProp() {
        if (properties == null || properties.isEmpty()) {
            properties = new Properties();
            InputStream is = null;
            BufferedReader bf = null;
            try {
                is = this.getClass().getResourceAsStream("/mail.properties");//将地址加在到文件输入流中
                bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));//转为字符流，设置编码为UTF-8防止出现乱码
                properties.load(bf);
            } catch (UnsupportedEncodingException e) {
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            } catch (Exception e) {
            } finally {
                try {//文件流关闭
                    if (bf != null) {
                        bf.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                }
            }
        }
        return properties;
    }

    public String getKeyValue(Integer code) {
        return properties.getProperty("templateMAIL" + code);
    }

    /**
     * 根据编号获取短信模版，进行发送
     *
     * @param email   手机号
     * @param code    编号
     * @param content 替换内容
     * @return
     */
    public String sendMail(Integer code, String email, String... content) {
        String msg = this.getKeyValue(code);
        if (msg != null && !msg.equals("")) {
            for (int i = 0; i < content.length; i++) {
                msg = msg.replace("{" + i + "}", content[i]);
            }
        } else {
            msg = "";
        }
        if (email != null && FormatValidateTool.checkEmail(email.trim())) {
            //发送邮件接口
            RabbitMQProducerTool.addToMailSendQueue(email.trim(), msg);//加入短信队列
        }
        return msg;
    }

    /**
     * 根据编号获取短信模版
     *
     * @param code    编号
     * @param content 替换内容
     * @return
     */
    public String getSendContent(Integer code, String... content) {
        String msg = this.getKeyValue(code);
        if (msg != null && !msg.equals("")) {
            for (int i = 0; i < content.length; i++) {
                msg = msg.replace("{" + i + "}", content[i]);
            }
        }
        return msg;
    }

}
