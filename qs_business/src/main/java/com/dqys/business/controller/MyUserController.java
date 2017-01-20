package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.UserDetail;
import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.business.service.service.CompanyService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2017/1/11.
 */
@RestController
@RequestMapping("/my")
public class MyUserController {

    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private CompanyService companyService;

    /**
     * 获取我的信息
     *
     * @return
     */
    @RequestMapping("/c/getMy")
    public JsonResponse getMy() {
        Integer userId = UserSession.getCurrent().getUserId();
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        Map map = new HashMap<>();
        map.put("avg", detail.getAvg());//头像地址
        map.put("realName", detail.getRealName());//真实姓名
        map.put("account", detail.getAccount());//帐号
        return JsonResponseTool.success(map);
    }

    /**
     * 获取我的帐号信息
     *
     * @return
     */
    @RequestMapping("/c/myAccount")
    public JsonResponse myAccount(Integer userId) {
        if (userId == null) {
            userId = UserSession.getCurrent().getUserId();
        }
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        Map map = new HashMap<>();
        map.put("roleType", detail.getRold());//角色（1管理员2管理者3员工）
        map.put("userType", detail.getUserType());//帐号类型（2委托31催收32律所33中介）
        UserDetail admin = tUserInfoMapper.getUserDetail(tUserInfoMapper.getUserByCompanyAdmin(detail.getCompanyId()));//管理员信息
        map.put("admin", admin.getRealName());//管理员真实姓名
        map.put("adminId", admin.getId());//管理员id
        map.put("account", detail.getAccount());//帐号
        return JsonResponseTool.success(map);
    }

    /**
     * 根据相应用户id获取相关信息，没有userId就取当前用户
     *
     * @return
     */
    @RequestMapping("/c/myUser")
    public JsonResponse myUser(Integer userId) {
        if (userId == null) {
            userId = UserSession.getCurrent().getUserId();
        }
        UserDetail detail = tUserInfoMapper.getUserDetail(userId);
        Map map = new HashMap<>();
        map.put("avg", detail.getAvg());//头像地址
        map.put("realName", detail.getRealName());//真实姓名
        map.put("userName", detail.getUserName());//用户名
        map.put("sex", detail.getSex());//性别（1男0女）
        map.put("phone", detail.getMobile());//手机号
        StringBuilder builder = getStringBuilderByAddress(detail);
        map.put("address", builder.toString());//地址
        map.put("roleType", detail.getRold());//角色
        map.put("companyName", detail.getCompanyName());//公司
        map.put("companyId", detail.getCompanyId());//公司id
        map.put("weChat", detail.getWechat());//微信
        map.put("email", detail.getEmail());//邮箱
        return JsonResponseTool.success(map);
    }

    private StringBuilder getStringBuilderByAddress(UserDetail detail) {
        StringBuilder builder = new StringBuilder();
        if (detail.getProvince() != null) {
            builder.append(AreaTool.getAreaById(detail.getProvince()).getLabel());
        }
        if (detail.getCity() != null) {
            builder.append(AreaTool.getAreaById(detail.getCity()).getLabel());
        }
        if (detail.getArea() != null) {
            builder.append(AreaTool.getAreaById(detail.getArea()).getLabel());
        }
        if (detail.getAddress() != null) {
            builder.append(detail.getAddress());
        }
        return builder;
    }

    /**
     * 获取我的公司信息
     *
     * @return
     */
    @RequestMapping("/c/myCompany")
    public JsonResponse myCompany(Integer companyId) {
        if (companyId == null) {
            Integer userId = UserSession.getCurrent().getUserId();
            UserDetail detail = tUserInfoMapper.getUserDetail(userId);
            companyId = detail.getCompanyId();
        }
        Map map = new HashMap<>();
        UserDetail admin = tUserInfoMapper.getUserDetail(tUserInfoMapper.getUserByCompanyAdmin(companyId));//管理员信息
        map.put("avg", admin.getAvg());//管理员头像
        map.put("phone", admin.getMobile());//电话
        map.put("address", getStringBuilderByAddress(admin).toString());//地址
        map.put("companyName", admin.getCompanyName());//公司名称
        map.put("companyId", admin.getCompanyId());//公司id
        TUserQuery query = new TUserQuery();
        query.setCompanyId(companyId);
        map.put("companyPeopleNum", tUserInfoMapper.queryCount(query));//公司人数
        return JsonResponseTool.success(map);
    }

    /**
     * 获取公司成员,公司id不传就获取当前用户的公司
     *
     * @return
     */
    @RequestMapping("/c/myUserListByCompanyId")
    public JsonResponse myCompanyUser(Integer companyId) {
        if (companyId == null) {
            UserDetail detail = tUserInfoMapper.getUserDetail(UserSession.getCurrent().getUserId());
            companyId = detail.getCompanyId();
        }
        List<Map> list = tUserInfoMapper.getUserByCompanyId(companyId);
        return JsonResponseTool.success(list);
    }


}
