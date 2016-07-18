package com.dqys.business.service.constant.asset;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/11.
 * @apiDefine LenderTypeEnum
 * @apiSuccessExample {json} LenderTypeEnum:
 * {
 *
 * }
 * git 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/constant/asset/LenderTypeEnum.java
 */
public enum LenderTypeEnum {

    lender("借款人"),
    oLender("共同借款人"),
    guarantee("担保方"),
    manager("银行客户经理"),
    other("其他相关联系人");

    private String name;

    LenderTypeEnum(String name){
        this.name = name;
    }

    public static LenderTypeEnum getLenderTypeEnum(String name){
        if(name != null){
            for(LenderTypeEnum lenderTypeEnum : LenderTypeEnum.values()){
                if(lenderTypeEnum.name().equals(name)){
                    return lenderTypeEnum;
                }
            }
        }
        return null;
    }

    public static List<BaseSelectonDTO> list(){
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();
        for(LenderTypeEnum lenderTypeEnum: LenderTypeEnum.values()){
            BaseSelectonDTO selectonDTO = new BaseSelectonDTO();
            selectonDTO.setKey(lenderTypeEnum.name());
            selectonDTO.setValue(lenderTypeEnum.getName());
            selectonDTOList.add(selectonDTO);
        }
        return selectonDTOList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
