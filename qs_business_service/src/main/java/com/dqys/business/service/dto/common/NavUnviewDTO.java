package com.dqys.business.service.dto.common;

import com.dqys.business.service.dto.sourceAuth.UnviewReIdMap;

/**
 *
 * 用于资料实勘实时更新的操作
 * Created by yan on 16-11-3.
 *
 * @apiDefine NavUnviewDTO
 * @apiParam {number} [navId] 资料实勘分类id
 * @apiParam {number} [objectId] 对象id
 * @apiParam {number} [objectType] 对象类型(借款人,资产源)
 * @apiParam {UnviewReIdMap}  [unviewReIdMap] 未选中的项
 *
 */
public class NavUnviewDTO {
    private Integer navId;
    private Integer ObjectId;
    private Integer ObjectType;
    private UnviewReIdMap unviewReIdMap;
    public Integer getNavId() {
        return navId;
    }

    public void setNavId(Integer navId) {
        this.navId = navId;
    }

    public Integer getObjectId() {
        return ObjectId;
    }

    public void setObjectId(Integer objectId) {
        ObjectId = objectId;
    }

    public Integer getObjectType() {
        return ObjectType;
    }

    public void setObjectType(Integer objectType) {
        ObjectType = objectType;
    }

    public UnviewReIdMap getUnviewReIdMap() {
        return unviewReIdMap;
    }

    public void setUnviewReIdMap(UnviewReIdMap unviewReIdMap) {
        this.unviewReIdMap = unviewReIdMap;
    }
}
