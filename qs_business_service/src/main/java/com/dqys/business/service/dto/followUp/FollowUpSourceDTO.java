package com.dqys.business.service.dto.followUp;

import com.dqys.business.service.dto.base.SourceDTOBase;

/**
 * Created by yan on 17-1-12.
 *
 * @apiDefine FollowUpSourceDTO
 * @apiParam {number} [id] id
 * @apiParam {number} [objectId]对象id
 * @apiParam {string} pathFilename 上传后返回的文件名
 * @apiParam {string} showFilename 文件展示名
 * @apiParam {number} [type] 对象类型:11跟进文件夹,21跟进文件
 * @apiParam {number} [followUpMessageId] 跟进信息id
 * @apiParam {number} [pid] 父文件夹id
 */
public class FollowUpSourceDTO extends SourceDTOBase{

    private Integer followUpMessageId;

    private Integer type;

    private Integer objectType;

    private Integer objectId;

    private Integer pid;

    public Integer getFollowUpMessageId() {
        return followUpMessageId;
    }

    public void setFollowUpMessageId(Integer followUpMessageId) {
        this.followUpMessageId = followUpMessageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
