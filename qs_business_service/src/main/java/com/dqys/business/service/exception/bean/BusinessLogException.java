package com.dqys.business.service.exception.bean;


import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * 业务日志异常
 * Created by yan on 16-7-15.
 */
public class BusinessLogException extends Exception implements BussinessException {
    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="BUSINESS_LOG_EXCEPTION";
    /**
     * 参数为空错误
     */
    public static int PARAM_ISNOULL_ERROR=100;

    /**
     * 没有list访问权限
     */
    public static int NOTHAS_LISTAUTH_ERROR=101;

    public int exceptionCode;

    public BusinessLogException(String msg, int exceptionCode)
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
