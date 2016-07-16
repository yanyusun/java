package com.dqys.business.service.utils.businessLog.exception;

/**
 * 业务日志异常
 * Created by yan on 16-7-15.
 */
public class BusinessLogException extends Exception{
    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="BUSINESS_LOG_EXCEPTION";
    /**
     * 参数为空错误
     */
    public static int PARAM_ISNOULL_MSG=0;

    public BusinessLogException(String msg,int PARAM_ISNOULL_MSG)
    {
        super(EXCEPTION_NAME+"->"+PARAM_ISNOULL_MSG+"："+msg);
    }
}
