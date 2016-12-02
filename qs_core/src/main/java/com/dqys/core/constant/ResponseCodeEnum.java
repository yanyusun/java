package com.dqys.core.constant;

/**
 * @author by pan on 9/10/15.
 */
public enum ResponseCodeEnum {
    FAILURE(0, "失败"),
    PARAM_ERR(1001, "参数错误"),
    SUCCESS(2000, "成功"),
    NODATA(3000, "没有数据"),
    AUTH_FAILURE(4000, "账号验证异常，请重新登陆"),
    AUTH_BUSINESS_FAILURE(4001,"对当前访问的链接没有操作权限"),
    SERVER_ERR(5000, "服务器错误"),
    ILLEGAL_REQUEST(5001, "非法请求"),
    NOT_FOUND(5002, "资源不存在"),
    BUSINESS_ERROR(9000,"业务操作错误")
    ;

    private Integer value;
    private String text;

    ResponseCodeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
