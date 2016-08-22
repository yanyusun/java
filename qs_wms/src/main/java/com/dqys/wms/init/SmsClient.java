package com.dqys.wms.init;

import com.dqys.core.constant.KeyEnum;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Yvan on 16/5/30.
 */
@Component
public class SmsClient {

    public static CloseableHttpResponse sendMessage(String phone, String content) {
        CloseableHttpClient client = HttpClients.createDefault();
        String encodedContent = null;
        try {
            encodedContent = URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuffer strBuf = new StringBuffer(KeyEnum.SMS_URL);
        strBuf.append("?account=").append(KeyEnum.SMS_ACCOUNT);
        strBuf.append("&pswd=").append(KeyEnum.SMS_PASSWORD);
        strBuf.append("&mobile=").append(phone);
        strBuf.append("&msg=").append(encodedContent);
        strBuf.append("&needstatus=true");
        HttpGet get = new HttpGet( strBuf.toString() );

        try {
            return client.execute(get);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

   /*public static void main(String[] args){
      SmsClient smsClient = new SmsClient();
       smsClient.sendMessage("13575716050", "【清搜网】这只是一个测试短信发送");


   }*/

}
