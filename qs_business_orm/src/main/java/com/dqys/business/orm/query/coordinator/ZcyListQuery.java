package com.dqys.business.orm.query.coordinator;

import com.dqys.core.base.BasePagination;

import java.util.List;

/**
 * Created by mkfeng on 2016/8/19.
 *
 * @apiDefine ZcyListQuery
 * @apiParam {int} status  状态（0待接收13待分配2正在处置99全部）
 * @apiParam {string} houses  楼盘小区名称
 * @apiParam {string} buildingNo  楼栋
 * @apiParam {string} startPriceTotal  总价（起始）
 * @apiParam {string} endPriceTotal   总价（结束）
 * @apiParam {string} houseNo  房源编号
 * @apiParam {string} houseS  室（居室）
 * @apiParam {string} houseT  厅（居室）
 * @apiParam {string} startFloor  实际楼层（起始）
 * @apiParam {string} endFloor  实际楼层（结束）
 * @apiParam {string} startDecade  建筑年代（起始）
 * @apiParam {string} endDecade  建筑年代（结束）
 * @apiParam {string} startAcreage  面积（起始）
 * @apiParam {string} endAcreage  面积（结束）
 * @apiParam {string} houseBelong  房屋权属
 * @apiParam {string} buildType  建筑类型
 * @apiParam {string} maintaining  维护人
 * @apiParam {string[]} orientationList 朝向（集合形式）
 * @apiParam {string} houseUse  房屋用途
 * @apiParam {string} houseCase  房屋现状
 * @apiParam {string} startEntrust  挂牌时间（起始）
 * @apiParam {string} endEntrust   挂牌时间（结束）
 * @apiParam {string[]} facilityList   标签集合
 */
public class ZcyListQuery extends BasePagination {
    private List<Integer> objectIdList;
    private Integer status;//状态
    private Integer objectType;
    private List<Integer> objectIds;
    private Integer startPage=0;
    private Integer userId;//

    /*筛选条件*/
    private String houses;//楼盘小区名称
    private String buildingNo;//楼栋
    private String startPriceTotal;//总价（起始）
    private String endPriceTotal;// 总价（结束）
    private String houseNo;//房源编号
    private String houseS;//室（居室）
    private String houseT;//厅（居室）
    private String startFloor;//实际楼层（起始）
    private String endFloor;//实际楼层（结束）
    private String startDecade;//建筑年代（起始）
    private String endDecade;//建筑年代（结束）
    private String startAcreage;//面积（起始）
    private String endAcreage;//面积（结束）
    private String houseBelong;//房屋权属
    private String buildType;//建筑类型
    private String maintaining;// 维护人(所属人)
    private List<String> orientationList;//朝向（集合形式）
    private String houseUse;//房屋用途
    private String houseCase;//房屋现状
    private String startEntrust;//挂牌时间（起始）
    private String endEntrust;//挂牌时间（结束）
    private List<String> facilityList;//标签集合
    private Integer resultStatus = 0;//结果状态（0默认1暂停2无效3完成）
    private String sameMonth;//当前年月(yyyyy-MM)
    private Integer dayNum;//获取几天内的记录
    private Integer hourNum;//获取超过几小时后的记录

    public Integer getHourNum() {
        return hourNum;
    }

    public void setHourNum(Integer hourNum) {
        this.hourNum = hourNum;
    }

    public String getSameMonth() {
        return sameMonth;
    }

    public void setSameMonth(String sameMonth) {
        this.sameMonth = sameMonth;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getHouses() {
        return houses;
    }

    public void setHouses(String houses) {
        this.houses = houses;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getStartPriceTotal() {
        return startPriceTotal;
    }

    public void setStartPriceTotal(String startPriceTotal) {
        this.startPriceTotal = startPriceTotal;
    }

    public String getEndPriceTotal() {
        return endPriceTotal;
    }

    public void setEndPriceTotal(String endPriceTotal) {
        this.endPriceTotal = endPriceTotal;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getHouseS() {
        return houseS;
    }

    public void setHouseS(String houseS) {
        this.houseS = houseS;
    }

    public String getHouseT() {
        return houseT;
    }

    public void setHouseT(String houseT) {
        this.houseT = houseT;
    }

    public String getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(String startFloor) {
        this.startFloor = startFloor;
    }

    public String getEndFloor() {
        return endFloor;
    }

    public void setEndFloor(String endFloor) {
        this.endFloor = endFloor;
    }

    public String getStartDecade() {
        return startDecade;
    }

    public void setStartDecade(String startDecade) {
        this.startDecade = startDecade;
    }

    public String getEndDecade() {
        return endDecade;
    }

    public void setEndDecade(String endDecade) {
        this.endDecade = endDecade;
    }

    public String getStartAcreage() {
        return startAcreage;
    }

    public void setStartAcreage(String startAcreage) {
        this.startAcreage = startAcreage;
    }

    public String getEndAcreage() {
        return endAcreage;
    }

    public void setEndAcreage(String endAcreage) {
        this.endAcreage = endAcreage;
    }

    public String getHouseBelong() {
        return houseBelong;
    }

    public void setHouseBelong(String houseBelong) {
        this.houseBelong = houseBelong;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getMaintaining() {
        return maintaining;
    }

    public void setMaintaining(String maintaining) {
        this.maintaining = maintaining;
    }

    public List<String> getOrientationList() {
        return orientationList;
    }

    public void setOrientationList(List<String> orientationList) {
        this.orientationList = orientationList;
    }

    public String getHouseUse() {
        return houseUse;
    }

    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }

    public String getHouseCase() {
        return houseCase;
    }

    public void setHouseCase(String houseCase) {
        this.houseCase = houseCase;
    }

    public String getStartEntrust() {
        return startEntrust;
    }

    public void setStartEntrust(String startEntrust) {
        this.startEntrust = startEntrust;
    }

    public String getEndEntrust() {
        return endEntrust;
    }

    public void setEndEntrust(String endEntrust) {
        this.endEntrust = endEntrust;
    }

    public List<String> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<String> facilityList) {
        this.facilityList = facilityList;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public List<Integer> getObjectIds() {
        return objectIds;
    }

    public void setObjectIds(List<Integer> objectIds) {
        this.objectIds = objectIds;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Integer> getObjectIdList() {
        return objectIdList;
    }

    public void setObjectIdList(List<Integer> objectIdList) {
        this.objectIdList = objectIdList;
    }
}
