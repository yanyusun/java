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

    public static final String API_GET_INIT = "/lender/getInit";
    public static final String API_ADD = "/lender/add";
    public static final String API_DELETE = "/lender/delete";
    public static final String API_UPDATE = "/lender/update";
    public static final String API_GET = "/lender/get";
    public static final String API_GET_ALL = "/lender/getLenderAll";
    public static final String API_LIST = "/lender/list";
    public static final String API_LIST_COMPANY = "/lender/listCompany";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        if(API_GET_INIT.equals(url)){//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        }else if(API_ADD.equals(url)){
            return true;
        }else if(API_DELETE.equals(url)){
            return true;
        }else if(API_UPDATE.equals(url)){
            return true;
        }else if(API_GET.equals(url)){
            return true;
        }else if(API_GET_ALL.equals(url)){
            return true;
        }else if(API_LIST.equals(url)){
            return true;
        }else if(API_LIST_COMPANY.equals(url)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
