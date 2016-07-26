package com.dqys.business.interceptor.company;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/26.
 */
public class NavigationIntegercepter extends AuthenticationInterceptor {

    public static final String API_ADD = "/nav/add";
    public static final String API_DELETE = "/nav/delete";
    public static final String API_UPDATE = "/nav/update";
    public static final String API_GET = "/nav/get";
    public static final String API_GET_TOP = "/nav/getTop";
    public static final String API_LIST_BY_ID = "/nav/listById";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        if (API_ADD.equals(url)) {//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        } else if (API_DELETE.equals(url)) {
            return true;
        } else if (API_UPDATE.equals(url)) {
            return true;
        } else if (API_GET.equals(url)) {
            return true;
        } else if (API_GET_TOP.equals(url)) {
            return true;
        } else if (API_LIST_BY_ID.equals(url)) {
            return true;
        } else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:" + url);
            throw new UrlException("未知请求链接错误", UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
