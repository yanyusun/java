package com.dqys.resource.interceptor;

import com.dqys.core.base.BaseInterceptor;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.utils.SysPropertyTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yan on 16-11-2.
 */
public class GetResourceIntercetpor extends BaseInterceptor {
    private String picType="2";//图片类型
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String fileName = httpServletRequest.getParameter("fileName");
        String endfix = fileName.substring(
                fileName.indexOf(".") + 1, fileName.length());
        TSysProperty tSysProperty = SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE, picType);
        if (0 > tSysProperty.getPropertyValue().indexOf(endfix)) {//不是图片格式就去下载
            httpServletRequest.getRequestDispatcher("/res/download").forward(httpServletRequest,httpServletResponse);
        }
        return true;
    }

}
