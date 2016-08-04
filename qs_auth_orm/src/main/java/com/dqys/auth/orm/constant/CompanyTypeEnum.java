package com.dqys.auth.orm.constant;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/14.
 */
public enum  CompanyTypeEnum {

    bank(21, "银行"),
    management(22, "资管公司"),
    court(23, "法院"),
    law(24, "律所"),
    p2p(25, "P2P公司"),
    loan(26, "小贷公司"),
    finance(27, "消费金融公司"),
    auction(28, "拍卖公司"),
    pledge(29, "典当行"),
    entrust(30, "担保公司"),
    other(31, "其他"),
    urge(32, "专业催收公司"),
    lawyer(33, "律师事务所"),
    factor(34, "保理公司"),
    trust(35, "信托公司"),
    investment(36, "投资公司"),
    intermediary(37, "中介公司");

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

    public static List<BaseSelectonDTO> list(){
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();
        for(CompanyTypeEnum companyTypeEnum:CompanyTypeEnum.values()){
            BaseSelectonDTO baseSelectonDTO = new BaseSelectonDTO();
            baseSelectonDTO.setKey(companyTypeEnum.getValue().toString());
            baseSelectonDTO.setValue(companyTypeEnum.getName());
            selectonDTOList.add(baseSelectonDTO);
        }
        return selectonDTOList;
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
