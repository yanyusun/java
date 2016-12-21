package com.dqys.auth.service.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.dao.facade.SaleUserTagMapper;
import com.dqys.auth.orm.pojo.SaleUser;
import com.dqys.auth.orm.pojo.SaleUserModel;
import com.dqys.auth.orm.pojo.SaleUserTag;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.service.facade.SaleUserService;
import com.dqys.auth.service.facade.UserService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.utils.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/20.
 */
@Service
public class SaleUserServiceImpl implements SaleUserService {
    @Autowired
    private SaleUserMapper saleUserMapper;
    @Autowired
    private SaleUserTagMapper saleUserTagMapper;
    @Autowired
    private UserService userService;

    @Override
    public JsonResponse enterLogin(String account, String paw) throws Exception {
        List<SaleUser> list = saleUserMapper.verifyUser(account, account, account);
        if (list == null || list.size() == 0) {
            return JsonResponseTool.failure("帐号不存在");
        }
        if (list.size() > 1) {
            return JsonResponseTool.failure("该帐号存在异常，请联系平台");
        }
        SaleUser user = list.get(0);
        if (user.getStatus() == 0) {
            return JsonResponseTool.failure("帐号不允许登入");
        }
        if (!SignatureTool.md5Encode(SignatureTool.md5Encode(paw, "utf-8") + user.getSalt(), "utf-8").equals(user.getPassword())) {
            return JsonResponseTool.failure("帐号或密码错误");
        }
        return JsonResponseTool.success(ProtocolTool.createUserHeader(user.getId(), user.getUserType() + ",", user.getRoleType() + ",",
                user.getAccount(), user.getStatus()));
    }

    @Override
    public JsonResponse register(SaleUserModel saleUserModel) throws Exception {
        SaleUser user = saleUserModel.getSaleUser();
        SaleUserTag tag = saleUserModel.getSaleUserTag();
        TUserInfo tUserInfo = userService.queryUser(null, null, user.getEmail());//查询的是清搜公众平台邮箱帐号是否存在
        if (tUserInfo != null) {
            return JsonResponseTool.failure("该邮箱已经注册");
        }
        List<SaleUser> list = saleUserMapper.verifyUser(user.getAccount(), user.getMobile(), user.getEmail());
        if (list == null && list.size() > 0) {
            return JsonResponseTool.failure("帐号或邮箱或手机号已存在");
        }
        user.setSalt(RandomStringUtils.randomAlphabetic(6));//生成六位数掩码
        user.setPassword(SignatureTool.md5Encode(SignatureTool.md5Encode(user.getPassword(), "utf-8") + user.getSalt(), "utf-8"));//密码加密
        Integer num = saleUserMapper.insertSelective(user);
        if (num > 0) {
            tag.setUserId(user.getId());
        } else {
            return JsonResponseTool.failure("注册失败");
        }
        saleUserTagMapper.insertSelective(tag);
        return JsonResponseTool.success(null);
    }

    @Override
    public SaleUser queryUser(String account, String mobile, String email) {
        List<SaleUser> list = saleUserMapper.verifyUser(account, mobile, email);
        if (list == null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String verifyUserMessage(SaleUserModel saleUserModel) {
        SaleUser user = saleUserModel.getSaleUser();
        if (user != null) {
            if (CommonUtil.checkParam(user.getAccount(), user.getName(), user.getEmail(), user.getMobile(), user.getPassword(), user.getSex())) {
                return "请把信息填写完整";
            }
            if (FormatValidateTool.checkEmail(user.getAccount()) || FormatValidateTool.checkEmail(user.getMobile())) {
                return "用户名不能为邮箱或手机号";
            }
            if (!FormatValidateTool.checkEmail(user.getEmail())) {
                return "邮箱格式有误";
            }
            if (!FormatValidateTool.checkEmail(user.getMobile())) {
                return "手机号格式有误";
            }
        }
        return "";
    }


}
