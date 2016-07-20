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

    public static final String API_LIST_OGA = "/api/company/listOrganization";
    public static final String API_ADD_OGA = "/api/company/addOrganization";
    public static final String API_DELETE_OGA = "/api/company/deleteOrganization";
    public static final String API_UPDATE_OGA = "/api/company/updateOrganization";
    public static final String API_GET_OGA = "/api/company/getOrganization";
    public static final String API_GET_DIS = "/api/company/getDistribution";
    public static final String API_JOIN_DIS = "/api/company/joinDistribution";
    public static final String API_INVITE_DIS = "/api/company/inviteDistribution";
    public static final String API_DESIGN_DIS = "/api/company/designDistribution";
    public static final String API_EXIST_DIS = "/api/company/exitDistribution";
    public static final String API_GET_REL = "/api/company/getRelation";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        if(API_LIST_OGA.equals(url)){//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        }else if(API_ADD_OGA.equals(url)){
            return true;
        }else if(API_DELETE_OGA.equals(url)){
            return true;
        }else if(API_UPDATE_OGA.equals(url)){
            return true;
        }else if(API_GET_OGA.equals(url)){
            return true;
        }else if(API_GET_DIS.equals(url)){
            return true;
        }else if(API_JOIN_DIS.equals(url)){
            return true;
        }else if(API_INVITE_DIS.equals(url)){
            return true;
        }else if(API_DESIGN_DIS.equals(url)){
            return true;
        }else if(API_EXIST_DIS.equals(url)){
            return true;
        }else if(API_GET_REL.equals(url)){
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
