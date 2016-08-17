package com.dqys.business.interceptor.asset;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/18.
 */
public class AssetInterceptor extends AuthenticationInterceptor {

    public static final String API_GET_INIT = "getInit";
    public static final String API_ADD = "add";
    public static final String API_DELETE = "delete";
    public static final String API_UPDATE = "update";
    public static final String API_GET = "get";
    public static final String API_LIST_LENDER_SELECT = "listLenderSelect";
    public static final String API_EXCEL_IN = "excelIn";
    public static final String API_LIST_LENDER = "listLender";
    public static final String API_LIST = "list";
    public static final String API_ASSIGNED_BATCH = "assignedBatch";
    public static final String API_ADD_LENDER = "addLender";

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
        }else if(API_LIST_LENDER_SELECT.equals(path)){
            return true;
        }else if(API_EXCEL_IN.equals(path)){
            return true;
        }else if(API_LIST_LENDER.equals(path)){
            return true;
        }else if(API_LIST.equals(path)){
            return true;
        }else if(API_ASSIGNED_BATCH.equals(path)){
            return true;
        }else if(API_ADD_LENDER.equals(path)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
