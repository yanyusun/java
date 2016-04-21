package com.dqys.wms.interceptor;

import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by pan on 16-4-7.
 */
public class WMSInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Integer userId =ProtocolTool.validateUser(
                String.valueOf(httpServletRequest.getSession().getAttribute(AuthHeaderEnum.X_QS_USER.getValue())),
                String.valueOf(httpServletRequest.getSession().getAttribute(AuthHeaderEnum.X_QS_TYPE.getValue())),
                String.valueOf(httpServletRequest.getSession().getAttribute(AuthHeaderEnum.X_QS_ROLE.getValue())),
                String.valueOf(httpServletRequest.getSession().getAttribute(AuthHeaderEnum.X_QS_STATUS.getValue())),
                String.valueOf(httpServletRequest.getSession().getAttribute(AuthHeaderEnum.X_QS_CERTIFIED.getValue()))
        );

        if(0 == userId) {
            //远程鉴权
            //httpServletRequest.re

            httpServletResponse.setCharacterEncoding("utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(httpServletResponse.getWriter(), JsonResponseTool.authFailure(null));
            httpServletResponse.getWriter().close();
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
