package com.dqys.business.interceptor.base;

import com.dqys.core.model.UserSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yan on 16-7-17.
 */
public abstract class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 得到int类型的用户类型
     *
     * @return
     */
    public int getUserType() {
        return Integer.parseInt(UserSession.getCurrent().getUserType());
    }

    /**
     * 得到用户角色id
     *
     * @return
     */
    public int getUserRoleId() {
        return Integer.parseInt(UserSession.getCurrent().getRoleId());
    }
}
