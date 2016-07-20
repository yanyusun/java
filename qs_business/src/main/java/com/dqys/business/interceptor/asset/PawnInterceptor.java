package com.dqys.business.interceptor.asset;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/18.
 */
public class PawnInterceptor extends AuthenticationInterceptor {

    public static final String API_ADD = "/pawn/add";
    public static final String API_DELETE = "/pawn/delete";
    public static final String API_UPDATE = "/pawn/update";
    public static final String API_GET = "/pawn/get";
    public static final String API_LIST = "/pawn/listPawn";


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
        }else if(API_LIST.equals(url)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
