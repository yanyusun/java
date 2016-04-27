package com.dqys.wms.interceptor;

import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.HttpTool;
import com.dqys.core.utils.ProtocolTool;
import com.dqys.core.utils.SysPropertyTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author by pan on 16-4-7.
 */
public class WMSInterceptor implements HandlerInterceptor {

    private static final String SYS_AUTH_URL_KEY = "sys_auth_url";

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
            httpServletResponse.setCharacterEncoding(CharEncoding.UTF_8);

            //远程鉴权
            String[] paramKeys = new String[] {"email", "pwd", "captcha", "captchaKey", "mobile", "smsCode"};
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            for(String key : paramKeys) {
                if(StringUtils.isNotBlank(httpServletRequest.getParameter(key))) {
                    NameValuePair nameValuePair = new BasicNameValuePair(key, httpServletRequest.getParameter(key));
                    nameValuePairList.add(nameValuePair);
                }
            }
            HttpEntity result = HttpTool.postHttp(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, SYS_AUTH_URL_KEY).getPropertyValue() + "/auth/login", null, nameValuePairList);
            JsonResponse<Map> jsonResponse = HttpTool.parseToJsonResp(result);
            if(null != jsonResponse && jsonResponse.getCode().intValue() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
                try {
                    httpServletRequest.getSession().setAttribute(AuthHeaderEnum.X_QS_USER.getValue(), jsonResponse.getData().get(AuthHeaderEnum.X_QS_USER.getValue()));
                    httpServletRequest.getSession().setAttribute(AuthHeaderEnum.X_QS_TYPE.getValue(), jsonResponse.getData().get(AuthHeaderEnum.X_QS_TYPE.getValue()));
                    httpServletRequest.getSession().setAttribute(AuthHeaderEnum.X_QS_ROLE.getValue(), jsonResponse.getData().get(AuthHeaderEnum.X_QS_ROLE.getValue()));
                    httpServletRequest.getSession().setAttribute(AuthHeaderEnum.X_QS_STATUS.getValue(), jsonResponse.getData().get(AuthHeaderEnum.X_QS_STATUS.getValue()));
                    httpServletRequest.getSession().setAttribute(AuthHeaderEnum.X_QS_CERTIFIED.getValue(), jsonResponse.getData().get(AuthHeaderEnum.X_QS_CERTIFIED.getValue()));
                    return true;
                } catch (Exception e) {
                    httpServletRequest.setAttribute("errMsg", "exception");
                }
            }
            if(null == httpServletRequest.getAttribute("errMsg")) {
                httpServletRequest.setAttribute("errMsg", null==jsonResponse?"please login":jsonResponse.getMsg());
            }
            httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //clean
        if (UserSession.getCurrent() != null) {
            UserSession.setCurrent(null);
        }
    }
}
