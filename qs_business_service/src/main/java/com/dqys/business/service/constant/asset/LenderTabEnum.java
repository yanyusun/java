package com.dqys.business.service.constant.asset;

/**
 * Created by Yvan on 16/7/19.
 */
public enum LenderTabEnum {

    accept(0, "待接收"),
    apply(1, "待申请"),
    handling(2, "正在处置"),
    focus(3, "聚焦"),
    month(4, "当月"),
    stock(5, "存量"),
    over(6, "完成"),
    outTime(7, "超时"),
    invalid(8, "无效"),
    join(9, "待参与"),
    joined(10, "已参与"),
    check(11, "待审核"),
    handle(12, "待处置"),
    assign(13, "待分配"),
    new48h(14, "48H 新")
    ;

    private Integer value;
    private String name;

    LenderTabEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static LenderTabEnum getLenderTabEnum(Integer value){
        if(value != null){
            for(LenderTabEnum lenderTabEnum :LenderTabEnum.values()){
                if(lenderTabEnum.getValue().equals(value)){
                    return lenderTabEnum;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
