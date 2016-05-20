package com.dqys.core.utils;


import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.model.JsonResponse;

/**
 * @author by pan on 9/10/15.
 */
public abstract class JsonResponseTool {

    /**
     * 通用成功返回消息
     *
     * @param data
     * @return
     */
    public static JsonResponse success(Object data) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.SUCCESS.getValue());
        jsonResponse.setMsg(ResponseCodeEnum.SUCCESS.getText());
        jsonResponse.setData(data);

        return jsonResponse;
    }

    /**
     * 失败返回消息
     *
     * @param msg
     * @return
     */
    public static JsonResponse failure(String msg) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.FAILURE.getValue());
        jsonResponse.setMsg(msg);
        jsonResponse.setData(null);

        return jsonResponse;
    }

    public static JsonResponse paramErr(String msg) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.PARAM_ERR.getValue());
        jsonResponse.setMsg(msg);
        jsonResponse.setData(null);

        return jsonResponse;
    }

    /**
     * 通用无数据返回消息
     *
     * @return
     */
    public static JsonResponse noData() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.NODATA.getValue());
        jsonResponse.setMsg(ResponseCodeEnum.NODATA.getText());
        jsonResponse.setData(null);

        return jsonResponse;
    }

    /**
     * 服务器异常
     *
     * @return
     */
    public static JsonResponse serverErr() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.SERVER_ERR.getValue());
        jsonResponse.setMsg(ResponseCodeEnum.SERVER_ERR.getText());
        jsonResponse.setData(null);

        return jsonResponse;
    }

    /**
     * 服务器异常
     *
     * @return
     */
    public static JsonResponse illegalRequest() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.ILLEGAL_REQUEST.getValue());
        jsonResponse.setMsg(ResponseCodeEnum.ILLEGAL_REQUEST.getText());
        jsonResponse.setData(null);

        return jsonResponse;
    }

    public static JsonResponse notFound() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.NOT_FOUND.getValue());
        jsonResponse.setMsg(ResponseCodeEnum.NOT_FOUND.getText());
        jsonResponse.setData(null);

        return jsonResponse;
    }

    /**
     * 帐号验证错误
     *
     * @return
     */
    public static JsonResponse authFailure(String msg) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ResponseCodeEnum.AUTH_FAILURE.getValue());
        jsonResponse.setMsg(null==msg?ResponseCodeEnum.AUTH_FAILURE.getText():msg);
        jsonResponse.setData(null);

        return jsonResponse;
    }

}
