package com.dqys.core.utils;

import com.dqys.core.model.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author by pan on 9/17/15.
 */
public abstract class HttpTool {

    /**
     *  发送http post请求
     *
     * @param url
     * @param header
     * @param params
     */
    public static JsonResponse postHttp(String url, Map<String, String> header, List<NameValuePair> params) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            if(null != header && !header.isEmpty()) {
                for(String k : header.keySet()) {
                    httpPost.addHeader(k, header.get(k));
                }
            }
            if(params != null && !params.isEmpty()) {
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            }
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                if(200  == response.getStatusLine().getStatusCode()) {
                    return parseToJsonResp(response.getEntity());
                } else {
                    return JsonResponseTool.failure("远程请求失败");
                }
            } finally {
                response.close();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     *  发送http get请求
     *
     * @param url
     */
    public static String getHttp(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    org.apache.http.HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("请求微信异常: " + status);
                }
            }
        };

        return httpclient.execute(httpget, responseHandler);
    }

    /**
     * 处理为JsonResponse
     *
     * @param httpEntity
     */
    private static JsonResponse parseToJsonResp(HttpEntity httpEntity) {
        try {
            if(httpEntity.getContentType().getValue().indexOf(MediaType.APPLICATION_JSON_VALUE) != -1) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(httpEntity.getContent(), JsonResponse.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
