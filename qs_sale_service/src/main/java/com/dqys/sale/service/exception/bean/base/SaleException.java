package com.dqys.sale.service.exception.bean.base;

/**
 * Created by pan on 16-12-27.
 */
public interface SaleException {
    /**
     * 返回错误编号
     * @return 错误编号
     */
    int getExceptionCode();

    String getExceptionName();
}
