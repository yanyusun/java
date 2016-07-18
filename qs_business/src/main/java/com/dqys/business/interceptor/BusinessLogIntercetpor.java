package com.dqys.business.interceptor;

import com.dqys.business.interceptor.base.AuthenticationInterceptor;
import com.dqys.business.service.exception.bean.UrlException;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 任务日志权限拦截器
 * Created by yan on 16-7-17.
 */
public class BusinessLogIntercetpor extends AuthenticationInterceptor {
    private String baseUrl="/b_log";
    private String listUrl=baseUrl+"list";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        if(listUrl.equals(url)){//待后期完善根据业务号，团队id，操作对象，人员控制权限
            return true;
        }else{
            LogManager.getLogger("businessAsync").warn("未知请求链接错误:"+url);
            throw new UrlException("未知请求链接错误",UrlException.UNKNOWN_URL_ERROR);
        }
    }

}
