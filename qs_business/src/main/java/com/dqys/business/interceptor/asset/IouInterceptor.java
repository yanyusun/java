package com.dqys.business.interceptor.asset;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/18.
 */
public class IouInterceptor extends AuthenticationInterceptor {

    public static final String API_ADD = "/iou/add";
    public static final String API_DELETE = "/iou/delete";
    public static final String API_UPDATE = "/iou/update";
    public static final String API_GET = "/iou/get";
    public static final String API_LIST_LENDER_SELECT = "/iou/listIou";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        if(API_ADD.equals(url)){
            return true;
        }else if(API_DELETE.equals(url)){
            return true;
        }else if(API_UPDATE.equals(url)){
            return true;
        }else if(API_GET.equals(url)){
            return true;
        }else if(API_LIST_LENDER_SELECT.equals(url)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
