package com.dqys.business.service.exception.bean;

import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * Created by mkfeng on 2016/8/5.
 */
public class ArtificialException extends Exception implements BussinessException {
    /**
     * 业务日志异常名称
     */
    public static String EXCEPTION_NAME = "ARTIFICIAL_EXCEPTION";

    public int exceptionCode;

    public ArtificialException(String msg, int exceptionCode) {
        super(msg);
        this.exceptionCode = exceptionCode;

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
