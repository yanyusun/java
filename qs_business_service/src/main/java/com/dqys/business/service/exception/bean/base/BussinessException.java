package com.dqys.business.service.exception.bean.base;

/**
 * Created by yan on 16-7-16.
 */
public interface BussinessException {
    /**
     * 返回错误编号
     * @return 错误编号
     */
    int getExceptionCode();

    String getExceptionName();
}
