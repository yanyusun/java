package com.dqys.business.interceptor.asset;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/18.
 */
public class LenderInterceptor extends AuthenticationInterceptor {

    public static final String API_GET_INIT = "getInit";
    public static final String API_ADD = "add";
    public static final String API_DELETE = "delete";
    public static final String API_UPDATE = "update";
    public static final String API_GET = "get";
    public static final String API_GET_ALL = "getLenderAll";
    public static final String API_LIST = "list";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        String path = "";
        if(url != null && url.length() > 0){
            path = url.substring(url.lastIndexOf("/") + 1, url.length());
        }
        if(API_GET_INIT.equals(path)){//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        }else if(API_ADD.equals(path)){
            return true;
        }else if(API_DELETE.equals(path)){
            return true;
        }else if(API_UPDATE.equals(path)){
            return true;
        }else if(API_GET.equals(path)){
            return true;
        }else if(API_GET_ALL.equals(path)){
            return true;
        }else if(API_LIST.equals(path)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
