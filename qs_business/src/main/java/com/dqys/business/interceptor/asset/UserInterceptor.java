package com.dqys.business.interceptor.asset;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/18.
 */
public class UserInterceptor extends AuthenticationInterceptor {

    public static final String API_GET_INIT = "/api/user/getInit";
    public static final String API_ADD = "/api/user/add";
    public static final String API_DELETE = "/api/user/delete";
    public static final String API_UPDATE = "/api/user/update";
    public static final String API_GET = "/api/user/get";
    public static final String API_LIST_USER = "/api/user/listUser";
    public static final String API_LIST_DATA = "/api/user/listData";
    public static final String API_STATUS_BATCH = "/api/user/statusBatch";
    public static final String API_ASSIGNED_BATCH = "/api/user/assignedBatch";
    public static final String API_LIST = "/api/user/list";
    public static final String API_USER_EXCEL = "/api/user/userExcel";
    public static final String API_SEND_MSG = "/api/user/sendMsg";
    public static final String API_SET_PWD_BATCH = "/api/user/setPwdBatch";
    public static final String API_SET_PWD = "/api/user/setPwd";


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
        }else if(API_LIST_USER.equals(url)){
            return true;
        }else if(API_STATUS_BATCH.equals(url)){
            return true;
        }else if(API_LIST_DATA.equals(url)){
            return true;
        }else if(API_LIST.equals(url)){
            return true;
        }else if(API_ASSIGNED_BATCH.equals(url)){
            return true;
        }else if(API_USER_EXCEL.equals(url)){
            return true;
        }else if(API_SEND_MSG.equals(url)){
            return true;
        }else if(API_SET_PWD.equals(url)){
            return true;
        }else if(API_SET_PWD_BATCH.equals(url)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
