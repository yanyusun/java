package com.dqys.auth.service.constant.asset;

/**
 * 资产包类型枚举类
 * Created by Yvan on 16/6/6.
 */

public enum  AssetTypeEnum {

    FAN_CHANG(1, "房产包"),
    HUN_HE(2, "混合包"),
    GU_DING(3, "固定资产包"),
    DI_YA_DAI(4, "抵押贷资产包"),
    XIN_DAI(5, "信贷包"),
    XIAO_DAI(6, "小贷包"),
    QI_TA(7, "其他");

    private Integer value;
    private String name;

    AssetTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public AssetTypeEnum getAssetTypeEnum(Integer value){
        for(AssetTypeEnum assetTypeEnum : AssetTypeEnum.values()){
            if(assetTypeEnum.getValue().equals(value)){
                return assetTypeEnum;
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
