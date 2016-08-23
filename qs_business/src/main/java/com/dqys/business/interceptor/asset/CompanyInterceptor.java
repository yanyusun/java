package com.dqys.business.interceptor.asset;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/18.
 */
public class CompanyInterceptor extends AuthenticationInterceptor {

    public static final String API_LIST_COMPANY = "listCompany";
    public static final String API_LIST_OGA = "listOrganization";
    public static final String API_ADD_OGA = "addOrganization";
    public static final String API_DELETE_OGA = "deleteOrganization";
    public static final String API_UPDATE_OGA = "updateOrganization";
    public static final String API_GET_OGA = "getOrganization";
    public static final String API_GET_DIS = "getDistribution";
    public static final String API_JOIN_DIS = "joinDistribution";
    public static final String API_INVITE_DIS = "inviteDistribution";
    public static final String API_DESIGN_DIS = "designDistribution";
    public static final String API_EXIST_DIS = "exitDistribution";
    public static final String API_GET_REL = "getRelation";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        String path = "";
        if(url != null && url.length() > 0){
            path = url.substring(url.lastIndexOf("/") + 1, url.length());
        }
        if (API_LIST_COMPANY.equals(path)) {//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        } else if (API_LIST_OGA.equals(path)) {//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        } else if (API_ADD_OGA.equals(path)) {
            return true;
        } else if (API_DELETE_OGA.equals(path)) {
            return true;
        } else if (API_UPDATE_OGA.equals(path)) {
            return true;
        } else if (API_GET_OGA.equals(path)) {
            return true;
        } else if (API_GET_DIS.equals(path)) {
            return true;
        } else if (API_JOIN_DIS.equals(path)) {
            return true;
        } else if (API_INVITE_DIS.equals(path)) {
            return true;
        } else if (API_DESIGN_DIS.equals(path)) {
            return true;
        } else if (API_EXIST_DIS.equals(path)) {
            return true;
        } else if (API_GET_REL.equals(path)) {
            return true;
        } else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:" + url);
            throw new UrlException("未知请求链接错误", UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
