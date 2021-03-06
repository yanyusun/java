package com.dqys.core.utils;

import com.dqys.core.constant.SmsEnum;

import java.io.*;
import java.util.Properties;

/**
 * Created by mkfeng on 2016/8/10.
 */
public class SmsUtil {
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
                is = this.getClass().getResourceAsStream("/sms.properties");//将地址加在到文件输入流中
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
        return properties.getProperty("templateSMS" + code);
    }

    /**
     * 根据编号获取短信模版，进行发送
     *
     * @param mobilePhone 手机号
     * @param code        编号
     * @param content     替换内容
     * @return
     */
    public String sendSms(Integer code, String mobilePhone, String... content) {
        String msg = this.getKeyValue(code);
        if (msg != null && !msg.equals("")) {
            for (int i = 0; i < content.length; i++) {
                msg = msg.replace("{" + i + "}", content[i]);
            }
            msg = replaceMsg(code, msg);//填充好数据后的还需要特殊处理
        } else {
            msg = "";
        }
        if (mobilePhone != null && FormatValidateTool.checkMobile(mobilePhone.trim())) {
            //发送短信接口
            RabbitMQProducerTool.addToSMSSendQueue(mobilePhone.trim(), msg);//加入短信队列
        }
        return msg;
    }

    /**
     * 还要经过特殊处理的文字
     *
     * @param code
     * @param msg
     * @return
     */
    private String replaceMsg(Integer code, String msg) {
        if (code == SmsEnum.FlOW_OPER.getValue().intValue() && msg.lastIndexOf("[加入]") > -1) {
            return msg.replace("[也不能]", "").replace("[已不能]", "");
        }
        if (code == SmsEnum.REGISTER_AUDIT.getValue().intValue() || code == SmsEnum.REGISTER_AUDIT_RESULT.getValue().intValue()) {
            return msg.replace("处置方", "处置号");
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
