package com.dqys.business.interceptor.common;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/8/25.
 */
public class SourceInterceptor extends AuthenticationInterceptor {

    private static final String API_LIST_NAV  = "listNavigation";
    private static final String API_ADD_NAV  = "addNavigation";
    private static final String API_DELETE_NAV  = "deleteNavigation";
    private static final String API_ADD  = "add";
    private static final String API_GET  = "get";
    private static final String API_UPDATE  = "update";
    private static final String API_GET_NEW_NAV_ALL="getNewNavAll";
    private static final String API_GET_SOURCE_TYPE="getSourceType";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        String path = "";
        if(url != null && url.length() > 0){
            path = url.substring(url.lastIndexOf("/") + 1, url.length());
        }
        if (API_LIST_NAV.equals(path)) {//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        } else if (API_ADD_NAV.equals(path)) {
            return true;
        } else if (API_DELETE_NAV.equals(path)) {
            return true;
        } else if (API_UPDATE.equals(path)) {
            return true;
        } else if (API_ADD.equals(path)) {
            return true;
        } else if (API_GET.equals(path)) {
            return true;
        } else if(API_GET_NEW_NAV_ALL.equals(path)){
            return true;
        }else if(API_GET_SOURCE_TYPE.equals(path)){
            return true;
        }  else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:" + url);
            throw new UrlException("未知请求链接错误", UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
