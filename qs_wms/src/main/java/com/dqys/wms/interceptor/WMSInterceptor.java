package com.dqys.wms.interceptor;

import com.dqys.core.base.BaseInterceptor;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.HttpTool;
import com.dqys.core.utils.ProtocolTool;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author by pan on 16-4-7.
 */
public class WMSInterceptor extends BaseInterceptor {

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
            JsonResponse<Map> jsonResponse = HttpTool.postHttp(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_AUTH_URL_KEY).getPropertyValue() + "/auth/login", null, nameValuePairList);
            //JsonResponse<Map> jsonResponse = HttpTool.postHttp("http://localhost:9081/auth/login", null, nameValuePairList);;
            if(null != jsonResponse && jsonResponse.getCode().intValue() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
                if(!String.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_ADMINISTRATOR_KEY).getPropertyValue())
                        .equals(String.valueOf(jsonResponse.getData().get(AuthHeaderEnum.X_QS_ROLE.getValue())))) {

                    httpServletRequest.setAttribute("errMsg", "权限不够");
                    httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
                    return false;
                }

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

}
