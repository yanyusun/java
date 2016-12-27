package com.dqys.sale.service.exception.bean;

import com.dqys.sale.service.exception.bean.base.SaleException;

/**
 * Created by yan on 16-12-27.
 */
public class UserBusTotalException extends Exception implements SaleException {

    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="UserBusTotalException";
    /**
     * 没有发现一个唯一的资源
     */
    public static int NOT_FOUND_SINGLE=100;

    public int exceptionCode;

    public UserBusTotalException(String msg, int exceptionCode)
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
