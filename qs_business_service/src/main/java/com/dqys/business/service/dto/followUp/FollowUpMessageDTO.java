package com.dqys.business.service.dto.followUp;

import java.util.List;

/**
 * Created by yan on 16-8-12.
 *
 * @apiDefine FollowUpMessageDTO
 * @apiParam {number} [id] id
 * @apiParam {number} objectId 对象id
 * @apiParam {number} objectType 对象类型
 * @apiParam {number} [secondObjectId] 二级对象id
 * @apiParam {number} [secondObjectType] 二级对象类型
 * @apiParam {string} [content] 内容
 * @apiParam {number} liquidateStage 清收阶段
 * @apiParam {number} [secondLiquidateStage] 二级清收阶段
 * @apiParam {list} [fileList] 文件名称列表
 */

public class FollowUpMessageDTO {
    private Integer id;

    private Integer objectId;

    private Integer objectType;

    private Integer secondObjectId;

    private Integer secondObjectType;

    private String content;

    private Integer liquidateStage;

    private Integer secondLiquidateStage;

    private List<String> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSecondObjectId() {
        return secondObjectId;
    }

    public void setSecondObjectId(Integer secondObjectId) {
        this.secondObjectId = secondObjectId;
    }

    public Integer getSecondObjectType() {
        return secondObjectType;
    }

    public void setSecondObjectType(Integer secondObjectType) {
        this.secondObjectType = secondObjectType;
    }

    public Integer getLiquidateStage() {
        return liquidateStage;
    }

    public void setLiquidateStage(Integer liquidateStage) {
        this.liquidateStage = liquidateStage;
    }

    public Integer getSecondLiquidateStage() {
        return secondLiquidateStage;
    }

    public void setSecondLiquidateStage(Integer secondLiquidateStage) {
        this.secondLiquidateStage = secondLiquidateStage;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
