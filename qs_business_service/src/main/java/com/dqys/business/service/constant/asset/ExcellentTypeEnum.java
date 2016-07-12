package com.dqys.business.service.constant.asset;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/11.
 */
public enum ExcellentTypeEnum {

    great("非常好"),
    nice("很好"),
    good("好"),
    normal("一般"),
    bad("差");

    private String name; // 名称

    ExcellentTypeEnum(String name){
        this.name = name;
    }

    public static ExcellentTypeEnum getExcellentTypeEnum(String name){
        if(name != null){
            for(ExcellentTypeEnum excellentTypeEnum : ExcellentTypeEnum.values()){
                if(excellentTypeEnum.name().equals(name)){
                    return excellentTypeEnum;
                }
            }
        }
        return null;
    }

    public static List<BaseSelectonDTO> list(){
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();

        for(ExcellentTypeEnum excellentTypeEnum : ExcellentTypeEnum.values()){
            BaseSelectonDTO baseSelectonDTO = new BaseSelectonDTO();
            baseSelectonDTO.setKey(excellentTypeEnum.name());
            baseSelectonDTO.setValue(excellentTypeEnum.getName());
            selectonDTOList.add(baseSelectonDTO);
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
