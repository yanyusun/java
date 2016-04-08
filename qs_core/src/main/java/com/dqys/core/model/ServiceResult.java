package com.dqys.core.model;

/**
 * @author by pan on 9/22/15.
 */
public class ServiceResult<T> {

    private Boolean flag;
    private String message;
    private T data;

    public static <T> ServiceResult success(T t) {
        ServiceResult<T> serviceResult = new ServiceResult<T>();
        serviceResult.setFlag(true);
        serviceResult.setMessage("");
        serviceResult.setData(t);

        return serviceResult;
    }

    public static <T> ServiceResult failure(String message, T t) {
        ServiceResult<T> serviceResult = new ServiceResult<T>();
        serviceResult.setFlag(false);
        serviceResult.setMessage(message);
        serviceResult.setData(t);

        return serviceResult;
    }

    public Boolean getFlag() {
        return flag;
    }

    private void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
