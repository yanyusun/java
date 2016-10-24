package com.dqys.auth.service.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/11.
 */
public class MessageUtils {


    public static String transMapToString(Map map, String key) {
        if (map == null) {
            return "";
        }
        return map.get(key) == null ? "" : map.get(key).toString().trim();
    }

    public static Integer transStringToInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Double transStringToDou(Object obj) {
        try {
            return Double.parseDouble(obj.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer transMapToInt(Map map, String key) {
        return transStringToInt(transMapToString(map, key));
    }

    public static Double transMapToDou(Map map, String key) {
        return transStringToDou(transMapToString(map, key));
    }

    /**
     * 消息的操作地址operType的json格式
     *
     * @param accept            同意请求地址加参数
     * @param acceptRequestType 同意请求方式(默认get)
     * @param reject            拒绝请求地址加参数
     * @param rejectRequestType 拒绝请求方式(默认get)
     * @param distribution      跳转页面地址和参数
     * @return
     */
    public static String setOperUrl(String accept, String acceptRequestType, String reject, String rejectRequestType, String distribution) {
        String json = "{\"distribution\":\"" + (distribution == null ? "" : distribution) + "\",\"accept\":\"" + (accept == null ? "" : accept) + "\",\"acceptRequestType\":\""
                + (acceptRequestType == null ? "get" : acceptRequestType) + "\",\"reject\":\"" + (reject == null ? "" : reject) + "\",\"rejectRequestType\":\"" + (rejectRequestType == null ? "get" : rejectRequestType) + "\"}";
        return json;
    }

}
