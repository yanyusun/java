package com.dqys.core.model;

/**
 * @apiDefine JsonResponse
 * @apiSuccess {number} code 错误码
 * @apiSuccess {String} msg 错误消息
 *
 * @apiSuccessExample Success-Response:
 *  HTTP/1.1 2000 ok
 *  {
 *      code:2000,
 *      msg:'成功',
 *      data:Object
 *  }
 *
 * @author by pan on 9/10/15.
 */
public class JsonResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
