package com.dqys.business.service.exception.bean;

import com.dqys.business.service.exception.bean.base.BussinessException;

/**
 * 资源修改异常
 * Created by yan on 17-1-13.
 */
public class SourceEditException  extends Exception implements BussinessException {
    /**
     *业务日志异常名称
     */
    public static String EXCEPTION_NAME="SOURCE_EDIT_EXCEPTION";
    /**
     * 参数为空错误
     */
    public static int TYPE_ERROR=1000;

    public int exceptionCode;

    public SourceEditException(String msg, int exceptionCode)
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
