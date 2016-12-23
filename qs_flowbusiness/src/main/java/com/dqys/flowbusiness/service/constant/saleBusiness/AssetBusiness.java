package com.dqys.flowbusiness.service.constant.saleBusiness;

/**
 * 资产发布业务
 * Created by yan on 16-12-22.
 */
public class AssetBusiness {
    public static int type=1;
    public static BusinessStatusEnum getDRAFT(){
        return BusinessStatusEnum.DRAFT;
    };
    public static BusinessStatusEnum getBE_ANNOUNCED(){
        return BusinessStatusEnum.BE_ANNOUNCED;
    };
    public enum BusinessStatusEnum{
        DRAFT(0,"草稿"),
        BE_ANNOUNCED(1,"待发布");
        private Integer value;
        private String name;

        BusinessStatusEnum(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
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
}
