package com.dqys.business.service.query.user;

import com.dqys.core.base.BasePagination;

import java.util.List;

/**
 * @apiDefine UserListQuery
 * @apiParam {number} [province] 省份
 * @apiParam {number} [city] 城市
 * @apiParam {number} [district] 区域
 * @apiParam {number} [accountType] 账号类型<平台方|委托方|处置方|个体用户|c用户>
 * @apiParam {number} [role] 角色<管理员|负责人|参与人>
 * @apiParam {number} [type] 类型<机构|个人>
 * @apiParam {number} [status] 状态<启用|禁用|未激活>
 * @apiParam {number} [statuss] 状态复选(集合)<启用|禁用|未激活>
 * @apiParam {number} [module] 模块（null组织架构，1用户管理）
 * @apiParam {string} [name] 模糊查询
 * @apiParam {number} [page] 页码
 * @apiParam {number} [pageCount] 显示条数
 * <p/>
 * Created by Yvan on 16/6/22.
 * 用户模块条件查询
 */
public class UserListQuery extends BasePagination {
    // 公司信息
    private Integer province; // 省份
    private Integer city; // 城市
    private Integer district; // 区域
    // 标签tag
    private Integer accountType; // 账号类型<1平台方|2委托方|处置方（31催收32律所33中介）|0个体用户0|c用户>
    private Integer role; // 角色<1管理员|2负责人|3参与人>
    private Integer type; // 类型<1机构|2个人>
    // 用户信息
    private Integer status; // 状态<1已激活|0未激活>
    private Integer useStatus; // 使用状态状态<0正常1停用2禁止登入>
    private List<Integer> statuss; // 多种状态集合（可以同时搜索两种状态以上的数据，字段status一样的参数值）
    private Integer module; // 模块（null组织架构，1用户管理）

    private String name;  // 多种数据搜索

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getStatuss() {
        return statuss;
    }

    public void setStatuss(List<Integer> statuss) {
        this.statuss = statuss;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
