package com.dqys.business.service.constant;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiDefine AssetTypeEnum
 * @apiSuccessExcample {json} AssetTypeEnum
 * {
 *      1:"资产包"
 * }
 * Created by Yvan on 16/7/11.
 */
public enum  AssetTypeEnum {
    house(1, "房产包"),
    blend(2, "混合包"),
    fixed(3, "固定资产包"),
    mortgage(4, "抵押贷资产包"),
    credit(5, "信贷包"),
    loan(6, "小贷包"),
    other(7, "其他");

    private Integer value;
    private String name;

    AssetTypeEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static AssetTypeEnum getAssetTypeEnum(Integer value){
        if(value != null){
            for(AssetTypeEnum assetTypeEnum: AssetTypeEnum.values()){
                if(assetTypeEnum.getValue().equals(value)){
                    return assetTypeEnum;
                }
            }
        }
        return null;
    }

    public static List<BaseSelectonDTO> list(){
        List<BaseSelectonDTO> selectonDTOList = new ArrayList<>();
        for(AssetTypeEnum assetTypeEnum: AssetTypeEnum.values()){
            BaseSelectonDTO selectonDTO = new BaseSelectonDTO();
            selectonDTO.setKey(assetTypeEnum.getValue().toString());
            selectonDTO.setValue(assetTypeEnum.getName().toString());
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
