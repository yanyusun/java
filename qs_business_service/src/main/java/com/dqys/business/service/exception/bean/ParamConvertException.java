package com.dqys.business.service.exception.bean;

import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * 差数转换异常
 * Created by yan on 16-9-8.
 */
public class ParamConvertException extends Exception implements BussinessException {

    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="PARAM_CONVERT_EXCEPTION";
    /**
     * 参数为空错误
     */
    public static int PARAM_ISNOULL_ERROR=100;


    public int exceptionCode;

    public ParamConvertException(String message, int exceptionCode) {
        super(message);
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
