package com.dqys.core.model;


import com.dqys.core.base.BaseModel;

/**
 * @apiDefine TSysProperty
 * @apiSuccessExample {json} TSysProperty-Success-Response:
 * {
 *     "id": 6,
 *     "version": 0,
 *     "stateflag": 0,
 *     "createAt": "2016-04-08",
 *     "updateAt": "2016-04-08",
 *     "remark": "普通用户",
 *     "type": 3,
 *     "propertyName": "u_type_common_user",
 *     "propertyValue": "0"
 * }
 * @author by pan on 16-3-15.
 */
public class TSysProperty extends BaseModel {

    private Integer type;

    private String propertyName;

    private String propertyValue;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
