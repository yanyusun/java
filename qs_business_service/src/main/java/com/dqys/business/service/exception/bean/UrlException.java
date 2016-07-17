package com.dqys.business.service.exception.bean;

import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * Created by yan on 16-7-17.
 */
public class UrlException extends Exception implements BussinessException {
    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="URL_EXCEPTION";
    /**
     * 没有list访问权限
     */
    public static int UNKNOWN_URL_ERROR=10000;

    public int exceptionCode;

    public UrlException(String msg, int exceptionCode)
    {
        super(msg);
        this.exceptionCode=exceptionCode;

    }
    @Override
    public int getExceptionCode() {
        return exceptionCode;
    }

    @Override
    public String getExceptionName() {
        return EXCEPTION_NAME;
    }
}
