package com.dqys.business.service.dto.followUp;

/**
 * Created by yan on 17-1-12.
 *
 * @apiDefine FollowUpSourceDTO
 * @apiParam {number} [id] id
 * @apiParam {number} [objectId]对象id
 * @apiParam {string} pathFilename 上传后返回的文件名
 * @apiParam {string} showFilename 文件展示名
 * @apiParam {number} [type] 对象类型:10资料实勘文件夹,11跟进文件夹,20资料实勘文件,21跟进文件
 * @apiParam {number} [followUpMessageId] 跟进信息id
 * @apiParam {number} [pid] 父文件夹id
 */
public class FollowUpSourceDTO {

    private Integer id;

    private String pathFilename;

    private String showFilename;

    private Integer followUpMessageId;

    private Integer type;

    private Integer objectType;

    private Integer objectId;

    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPathFilename() {
        return pathFilename;
    }

    public void setPathFilename(String pathFilename) {
        this.pathFilename = pathFilename;
    }

    public String getShowFilename() {
        return showFilename;
    }

    public void setShowFilename(String showFilename) {
        this.showFilename = showFilename;
    }

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
