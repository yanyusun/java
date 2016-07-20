package com.dqys.business.orm.constant.company;

import com.dqys.business.orm.pojo.coordinator.CompanyTeam;

/**
 * Created by Yvan on 16/7/14.
 */
public enum  CompanyTypeEnum {

    bank(21, "银行"),
    ziguan(22, "资管公司"),
    fayuan(23, "法院"),
    lvsuo(24, "律所"),
    p2p(25, "P2P公司"),
    xiaodai(26, "小贷公司"),
    xiaofei(27, "消费金融公司"),
    paimai(28, "拍卖公司"),
    diandang(29, "典当行"),
    danbao(20, "担保公司");

    private Integer value;
    private String name;

    CompanyTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static CompanyTypeEnum getCompanyTypeEnum(Integer value){
        if(value != null){
            for(CompanyTypeEnum companyTypeEnum :CompanyTypeEnum.values()){
                if(companyTypeEnum.getValue().equals(value)){
                    return companyTypeEnum;
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
