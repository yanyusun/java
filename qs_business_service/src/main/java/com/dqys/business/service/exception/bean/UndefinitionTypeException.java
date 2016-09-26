package com.dqys.business.service.exception.bean;

import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * Created by yan on 16-9-26.
 */
public class UndefinitionTypeException  extends Exception implements BussinessException {


    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="UNDEFINITION_TYPE_EXCEPTION";
    /**
     * 参数为空错误
     */
    public static int TYPE_NOTFOUNFD_ERROR=100;


    public int exceptionCode;

    public UndefinitionTypeException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }


    @Override
    public int getExceptionCode() {
        return TYPE_NOTFOUNFD_ERROR;
    }

    @Override
    public String getExceptionName() {
        return EXCEPTION_NAME;
    }
}
