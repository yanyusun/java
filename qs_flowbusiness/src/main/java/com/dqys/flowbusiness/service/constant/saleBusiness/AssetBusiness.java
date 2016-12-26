package com.dqys.flowbusiness.service.constant.saleBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness.BeAnnouncedLevel;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.BusinessLevel;

/**
 * 资产发布业务
 * Created by yan on 16-12-22.
 */
public class AssetBusiness {
    public static int type = 1;

    public static BusinessLevel getBeAnnounced() {
        return new BeAnnouncedLevel();
    }
    ;

    public static BusinessLevel getBeCheck() {
        return null;
    }
    ;

    public static BusinessLevel getReject() {
        return null;
    }
    ;

    public static BusinessLevel getOpen() {
        return null;
    }
    ;

    public static BusinessLevel getUnderLine() {
        return null;
    }
    ;

    public static BusinessLevel getUnable() {
        return null;
    }
    ;


//    public enum BusinessStatusEnum {
//        be_announced(10, "待发布"),
//        be_check(20, "待审核"),
//        reject(30, "已驳回"),
//        open(40, "已发布"),
//        under_line(50, "已下架"),
//        unable(99, "无效");
//        private Integer value;
//        private String name;
//        BusinessStatusEnum(int value, String name) {
//            this.value = value;
//            this.name = name;
//        }
//
//        public int getValue() {
//            return value;
//        }
//
//        public void setValue(Integer value) {
//            this.value = value;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }

   // private enum
}
