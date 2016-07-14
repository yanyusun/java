package com.dqys.business.orm.constant.company;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 被操作对象类型
 * Created by mkfeng on 2016/7/4.
 */
public enum ObjectTypeEnum {
    PAWN(12, "抵押物"),
    ASSETPACKAGE(10, "资产包"),
    LENDER(11, "借款人"),
    IOU(13, "借据"),
    INFORMATION(14, "资料实勘"),
    CASE(15, "案件"),
    ASSETSOURCE(16, "资产源"),
    USER_INFO(99, "用户信息"),;

    private Integer value;
    private String name;

    ObjectTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ObjectTypeEnum getObjectTypeEnum(Integer value){
        if(value != null){
            for(ObjectTypeEnum objectTypeEnum : ObjectTypeEnum.values()){
                if(objectTypeEnum.getValue().equals(value)){
                    return objectTypeEnum;
                }
            }
        }
        return null;
    }

    public static List<BaseSelectonDTO> list(){
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();
        for(ObjectTypeEnum objectTypeEnum : ObjectTypeEnum.values()){
            BaseSelectonDTO selectonDTO = new BaseSelectonDTO();
            selectonDTO.setKey(objectTypeEnum.getValue().toString());
            selectonDTO.setValue(objectTypeEnum.getName());
            selectonDTOList.add(selectonDTO);
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
