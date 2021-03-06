package com.dqys.business.interceptor.cases;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yvan on 16/7/27.
 */
public class CaseInterceptor extends AuthenticationInterceptor {

    public static final String API_ADD = "add";
    public static final String API_LIST_ADD = "listAdd";
    public static final String API_DIVIDE = "divide";
    public static final String API_UPDATE = "update";
    public static final String API_LIST = "list";
    public static final String API_LIST_CASE = "listCase";
    public static final String API_LIST_BY_LENDER = "listByLender";
    public static final String API_LIST_BY_CASE = "listByCase";
    public static final String API_GET = "get";
    public static final String API_UPDATE_CASE_BASE = "updateCaseBase";
    public static final String API_UPDATE_CASE_ATTACHMENT = "updateCaseAttachment";
    public static final String API_UPDATE_CASE_LAWSUIT = "updateCaseLawsuit";
    public static final String API_UPDATE_CASE_MEMO = "updateCaseMemo";
    public static final String API_UPDATE_CASE_COURT = "updateCaseCourt";
    public static final String API_LIST_IOU_BY_CASE = "listIouByCase";
    public static final String API_DELETE = "delete";
    public static final String API_PROCESS = "process";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        String path = "";
        if (url != null && url.length() > 0) {
            path = url.substring(url.lastIndexOf("/") + 1, url.length());
        }
        if (API_ADD.equals(path)) {//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        } else if (API_DIVIDE.equals(path)) {
            return true;
        } else if (API_LIST_ADD.equals(path)) {
            return true;
        } else if (API_UPDATE.equals(path)) {
            return true;
        } else if (API_LIST.equals(path)) {
            return true;
        } else if (API_LIST_CASE.equals(path)) {
            return true;
        } else if (API_LIST_BY_LENDER.equals(path)) {
            return true;
        } else if (API_LIST_BY_CASE.equals(path)) {
            return true;
        } else if (API_GET.equals(path)) {
            return true;
        } else if (API_UPDATE_CASE_BASE.equals(path)) {
            return true;
        } else if (API_UPDATE_CASE_ATTACHMENT.equals(path)) {
            return true;
        } else if (API_UPDATE_CASE_COURT.equals(path)) {
            return true;
        } else if (API_UPDATE_CASE_MEMO.equals(path)) {
            return true;
        } else if (API_UPDATE_CASE_LAWSUIT.equals(path)) {
            return true;
        } else if (API_LIST_IOU_BY_CASE.equals(path)) {
            return true;
        } else if (API_DELETE.equals(path)) {
            return true;
        } else if (API_PROCESS.equals(path)) {
            return true;
        }else {
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:" + url);
            throw new UrlException("未知请求链接错误", UrlException.UNKNOWN_URL_ERROR);
        }
    }
}
