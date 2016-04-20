package com.dqys.auth.interceptor;

import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by pan on 16-4-5.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String userHeader = httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue());
        String userType = httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue());
        String roleId = httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue());
        String status = httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue());
        String certified = httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue());
        Integer  userId = ProtocolTool.validateUser(userHeader, userType, roleId, status, certified);
        if(0 == userId) {
            httpServletResponse.setCharacterEncoding("utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(httpServletResponse.getWriter(), JsonResponseTool.authFailure(null));
            httpServletResponse.getWriter().close();
            return false;
        }

        UserSession userSession = new UserSession();
        userSession.setUserId(userId);
        userSession.setUserType(Integer.decode(userType));
        userSession.setRoleId(null == roleId?null:Integer.decode(roleId));
        userSession.setStatus(Boolean.parseBoolean(status));
        userSession.setCertified(Boolean.parseBoolean(certified));
        UserSession.setCurrent(userSession);
        ProtocolTool.refreshUserHeader(userId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
