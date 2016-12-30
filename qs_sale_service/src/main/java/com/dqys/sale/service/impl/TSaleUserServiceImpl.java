package com.dqys.sale.service.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.dao.facade.SaleUserTagMapper;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.SaleUserTag;
import com.dqys.auth.orm.query.SaleUserQuery;
import com.dqys.auth.service.facade.SaleUserService;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.auth.orm.pojo.saleUser.SaleUserModel;
import com.dqys.sale.orm.pojo.message.Message;
import com.dqys.sale.service.constant.MessageEnum;
import com.dqys.sale.service.facade.MessageService;
import com.dqys.sale.service.facade.TSaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/23.
 */
@Service
public class TSaleUserServiceImpl implements TSaleUserService {
    @Autowired
    private SaleUserMapper saleUserMapper;
    @Autowired
    private SaleUserService saleUserService;
    @Autowired
    private SaleUserTagMapper saleUserTagMapper;
    @Autowired
    private MessageService messageService;


    @Override
    public SaleUser getAdmin() {
        return saleUserMapper.getAdmin();
    }

    @Override
    public JsonResponse detail(Integer userId) {
        com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO detail = saleUserMapper.getUserDetail(userId);
        if (detail.getProvince() != null) {
            detail.setProvinceName(AreaTool.getAreaById(detail.getProvince()).getLabel());
        }
        if (detail.getCity() != null) {
            detail.setCityName(AreaTool.getAreaById(detail.getCity()).getLabel());
        }
        if (detail.getArea() != null) {
            detail.setAreaName(AreaTool.getAreaById(detail.getArea()).getLabel());
        }
        return JsonResponseTool.success(detail);
    }

    @Override
    public JsonResponse list(SaleUserQuery query) {
        List<SaleUser> dtos = saleUserMapper.list(query);
        Integer count = saleUserMapper.listCount(query);
        query.setTotalCount(count);
        query.setStartPage(query.getStartPage());
        Map map = new HashMap<>();
        map.put("userList", dtos);
        map.put("query", query);
        return JsonResponseTool.success(map);
    }

    @Override
    public JsonResponse addOrEdit(SaleUserModel model) throws Exception {
        if (model == null || (model.getSaleUser() == null || model.getSaleUserTag() == null)) {
            return JsonResponseTool.failure("请填写信息完整");
        }
        SaleUser saleUser = model.getSaleUser();
        if (saleUser.getId() == null) {
            //用户注册
            JsonResponse response = saleUserService.verifyUser(saleUser.getAccount(), saleUser.getEmail(), saleUser.getMobile());
            if (response.getCode() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
                if (model.getSaleUser().getPassword() == null) {
                    model.getSaleUser().setPassword("123456");
                    model.getSaleUser().setStatus(1);
                }
                return saleUserService.register(model);//注册
            }
            return response;
        } else {
            return updateUserMessage(model);//修改用户信息
        }
    }

    private JsonResponse updateUserMessage(SaleUserModel model) throws Exception {
        SaleUser saleUser = model.getSaleUser();
        SaleUser oldUser = saleUserMapper.selectByPrimaryKey(saleUser.getId());
        //验证用户名
        if (!oldUser.getAccount().equals(saleUser.getAccount())) {
            JsonResponse response = saleUserService.verifyUser(saleUser.getAccount(), null, null);
            if (response.getCode() != ResponseCodeEnum.SUCCESS.getValue().intValue()) {
                return response;
            }
        }
        //验证邮箱
        if (!oldUser.getEmail().equals(saleUser.getEmail())) {
            JsonResponse response = saleUserService.verifyUser(null, saleUser.getEmail(), null);
            if (response.getCode() != ResponseCodeEnum.SUCCESS.getValue().intValue()) {
                return response;
            }
        }
        //验证手机号
        if (!oldUser.getMobile().equals(saleUser.getMobile())) {
            JsonResponse response = saleUserService.verifyUser(null, null, saleUser.getMobile());
            if (response.getCode() != ResponseCodeEnum.SUCCESS.getValue().intValue()) {
                return response;
            }
        }
        saleUser.setPassword(null);
        saleUser.setSalt(null);
        Integer num = saleUserMapper.updateByPrimaryKeySelective(saleUser);
        SaleUserTag userTag = model.getSaleUserTag();
        userTag.setUserId(saleUser.getId());
        Integer num2 = saleUserTagMapper.updateByPrimaryKeySelective(userTag);
        if (num > 0 && num2 > 0) {
            return JsonResponseTool.success(null);
        } else if (num == 0 && num2 == 0) {
            return JsonResponseTool.failure("修改用户信息失败");
        } else {
            return JsonResponseTool.failure("修改部分用户信息成功");
        }
    }

    @Override
    public JsonResponse del(Integer userId) {
        Integer operId = UserSession.getCurrent().getUserId();
        if (operId == userId) {
            return JsonResponseTool.failure("无法对自己进行删除");
        }
        if (verifyAdmin(operId)) return JsonResponseTool.failure("不是管理员无法进行操作");
        if (saleUserMapper.deleteByPrimaryKey(userId) > 0) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("删除失败");
        }
    }

    //不是管理员
    private boolean verifyAdmin(Integer operId) {
        SaleUser user = saleUserMapper.getAdmin();
        if (user.getId() != operId) {
            return true;
        }
        return false;
    }

    @Override
    public Map setLogin(List<Integer> ids, Integer status) {
        Map map = new HashMap<>();
        map.put("result", "no");
        if (verifyAdmin(UserSession.getCurrent().getUserId())) {
            map.put("msg", "无权限操作");
            return map;
        }
        Integer num = saleUserMapper.updateStatusByIds(ids, status);
        if (num > 0) {
            map.put("result", "yes");
        } else {
            map.put("msg", "操作失败");
        }
        return map;
    }

    @Override
    public Map personal(Integer userId) {
        JsonResponse response = detail(userId);
        Map map = new HashMap<>();
        map.put("result", "yes");
        if (response.getCode() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            map.put("detail", response.getData());
        }
        Message sage = new Message();
        sage.setStatus(0);
        sage.setType(MessageEnum.PRODUCT.getValue());
        map.put("accTotal", messageService.selectCount(sage));//"帐号未读消息数");
        sage.setType(MessageEnum.SAFETY.getValue());
        map.put("sysTotal", messageService.selectCount(sage));//"系统未读消息数");
        sage.setType(MessageEnum.SERVE.getValue());
        map.put("pubTotal", messageService.selectCount(sage));//"发布未读消息数");
        return map;
    }
}
