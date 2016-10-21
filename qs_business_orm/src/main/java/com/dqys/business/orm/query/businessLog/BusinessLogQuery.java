package com.dqys.business.orm.query.businessLog;

import com.dqys.core.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by yan on 16-7-13.
 *
 * @apiDefine BusinessLogQuery
 * @apiParam {number} [id] id
 * @apiParam {number} [objectType] objectType　对象类型
 * @apiParam {number} [operType]　operType 操作类型
 * @apiParam {number} [userId] userId　操作人员id
 * @apiParam {number} [teamId] teamId　协作团队
 * @apiParam {number} [businessId] businessId　业务号
 * @apiParam {boolean} [isPaging] isPaging　是否分页0分页，1不分页
 * @apiParam {number} [startPageNum] startPageNum　当前分页
 * @apiParam {number} [pageSize] pageSize　分页大小
 * @apiParam {date} [createAt] createAt　创建时间
 * @apiParam {date} [beginDate] beginDate　开始时间
 * @apiParam {date} [endDate] endDate　结束时间
 * @apiParam {String} [searchText] searchText　操作者或者内容
 *
 */

public class BusinessLogQuery extends BaseQuery {
    private Integer id;

    private Integer objectType;

    private Integer objectId;

    private Integer operType;

    private Integer userId;

    private Integer teamId;

    private Integer businessId;

    private String text;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date beginDate;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date endDate;

    private String searchText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
