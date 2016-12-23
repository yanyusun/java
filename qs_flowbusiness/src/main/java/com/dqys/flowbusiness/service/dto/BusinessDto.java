package com.dqys.flowbusiness.service.dto;

/**
 * Created by yan on 16-12-22.
 * 业务dto
 */
public class BusinessDto {
    /**
     * 对象类型
     */
    private Integer objcetType;
    /**
     * 对象id
     */
    private Integer ObjectId;

    public Integer getObjcetType() {
        return objcetType;
    }

    public void setObjcetType(Integer objcetType) {
        this.objcetType = objcetType;
    }

    public Integer getObjectId() {
        return ObjectId;
    }

    public void setObjectId(Integer objectId) {
        ObjectId = objectId;
    }
}
