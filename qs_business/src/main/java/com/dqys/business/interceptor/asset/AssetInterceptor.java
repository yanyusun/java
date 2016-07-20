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

    public static final String API_GET_INIT = "/asset/getInit";
    public static final String API_ADD = "/asset/add";
    public static final String API_DELETE = "/asset/delete";
    public static final String API_UPDATE = "/asset/update";
    public static final String API_GET = "/asset/get";
    public static final String API_LIST_LENDER_SELECT = "/asset/listLenderSelect";
    public static final String API_EXCEL_IN = "/asset/excelIn";
    public static final String API_LIST_LENDER = "/asset/listLender";
    public static final String API_LIST = "/asset/list";
    public static final String API_ASSIGNED_BATCH = "/asset/assignedBatch";


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
        }else if(API_LIST_LENDER_SELECT.equals(url)){
            return true;
        }else if(API_EXCEL_IN.equals(url)){
            return true;
        }else if(API_LIST_LENDER.equals(url)){
            return true;
        }else if(API_LIST.equals(url)){
            return true;
        }else if(API_ASSIGNED_BATCH.equals(url)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
