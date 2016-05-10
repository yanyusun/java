package com.dqys.core.interceptor;

import com.dqys.core.base.BaseInterceptor;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by pan on 16-4-29.
 */
public class HeaderInterceptor extends BaseInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );

        if(0 == userId) {
            httpServletResponse.setCharacterEncoding("utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(httpServletResponse.getWriter(), JsonResponseTool.authFailure(null));
            httpServletResponse.getWriter().close();
            return false;
        }

        return true;
    }
}
