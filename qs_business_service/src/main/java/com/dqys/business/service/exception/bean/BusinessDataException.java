package com.dqys.business.service.exception.bean;

import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * Created by yan on 16-11-24.
 */
public class BusinessDataException extends Exception implements BussinessException {

    public int exceptionCode;


    public static String EXCEPTION_NAME="BUSINESS_DATA_EXCEPTION";

    public static Integer OBJECT_USER_RELATION＿NOTONE = 1001;//用户对象重复关联

    public BusinessDataException(String msg, int exceptionCode)
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
