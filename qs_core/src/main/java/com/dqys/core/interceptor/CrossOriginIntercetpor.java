package com.dqys.core.interceptor;

import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by pan on 16-4-25.
 */
public class CrossOriginIntercetpor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_ACCESS_ORIGIN_KEY).getPropertyValue());
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,x-qs-user,x-qs-type,x-qs-status," +
                "x-qs-role,x-qs-certified");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST");
        httpServletResponse.setHeader("Allow", "POST");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
