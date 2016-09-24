package com.dqys.business.controller;

import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.business.service.utils.common.buttonUtil.ListButtonShowerBean;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkfeng on 2016/7/26.
 */
@Controller
@RequestMapping("/operType")
public class OperTypeController {

    @Autowired
    private OperTypeService operTypeService;

    @Autowired
    private TUserTagMapper tUserTagMapper;

    @Autowired
    private TeammateReMapper teammateReMapper;

    /**
     * @api {post} operType/list 操作接口
     * @apiName operType/list
     * @apiSampleRequest operType/list
     * @apiParam {int} objectType 对象类型
     * @apiGroup　 OperType
     * {
    "code": 2000,
    "msg": "成功",
    "data": [
    {
    "id": 119,
    "operType": 111,
    "operName": "修改/编辑"
    },
    {
    "id": 120,
    "operType": 112,
    "operName": "添加关注"
    },
    {
    "id": 121,
    "operType": 113,
    "operName": "评优/内部评级"
    },
    {
    "id": 122,
    "operType": 114,
    "operName": "申请律师函"
    },
    {
    "id": 123,
    "operType": 115,
    "operName": "申请延期"
    },
    {
    "id": 124,
    "operType": 116,
    "operName": "设置为无效"
    },
    {
    "id": 125,
    "operType": 117,
    "operName": "录跟进"
    },
    {
    "id": 126,
    "operType": 118,
    "operName": "增加注释"
    },
    {
    "id": 127,
    "operType": 119,
    "operName": "修改日志"
    },
    {
    "id": 128,
    "operType": 1110,
    "operName": "操作日志"
    },
    {
    "id": 129,
    "operType": 1111,
    "operName": "添加一条还款"
    },
    {
    "id": 130,
    "operType": 1112,
    "operName": "分配借款人"
    }
    ]
    }
     *
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(@RequestParam("objectType") Integer objectType,@RequestParam("objectId") Integer objectId, HttpServletRequest httpServletRequest) {
        try {
            Integer userId=UserSession.getCurrent().getUserId();
            List<TUserTag> tags = tUserTagMapper.selectByUserId(userId);
            Integer roleType = RoleTypeEnum.GENERAL.getValue();//1-管理员;2-管理者;3-普通员工;4所属人;
            //// TODO: 16-9-24 增加是不是所属人的判断
            Integer userType = 0;//0-普通用户;1-平台管理员;2-委托号;31-催收;32-律所;33-中介
            if (tags.size() > 0) {
                TUserTag tag = tags.get(0);
                roleType = (int) tag.getRoleId();
                userType = (int) tag.getUserType();
                String roleId = UserSession.getCurrent().getRoleId();
                if (!roleId.equals(RoleTypeEnum.ADMIN.getValue().toString())) {
                    TeammateRe teammateRe = new TeammateRe();
                    teammateRe.setUserId(userId);
                    List<TeammateRe> teammateReList = teammateReMapper.selectSelective(teammateRe);
                    if (teammateReList.size() > 0) {
                        TeammateRe team = teammateReList.get(0);
                        roleType = team.getType();
                    } else {
                        return JsonResponseTool.noData();
                    }
                }
            }
            List<OperType> operTypes = operTypeService.getOperType(roleType, userType, objectType);
            return JsonResponseTool.success(operTypes);
        } catch (Exception e) {
            return JsonResponseTool.serverErr();
        }
    }

    /**
     * @api {GET} http://{url}/operType/listbuttonShower 读取未读的数量
     * @apiName listbuttonShower
     * @apiGroup OperType
     * @apiParam {number} objectId 对象id
     * @apiParam {number} objectType 对象类型
     * @apiParam {number} navId 对象类型
     * @apiSuccessExample {json} Data-Response:
    {
    "code": 2000,
    "msg": "成功",
    "data": {
    "hasRightButton": true,
    "hasUserTeamButton": false,
    "hasUserTeamButtonApply": false,
    "hasUserTeamButtonAdd": false,
    "hasCompanyTeamButton": true,
    "hasCompanyTeamButtonApply": false,
    "hasCompanyTeamButtonAdd": true,
    "rightButtonList": [
    [
    "1",
    "操作记录"
    ]
    ]
    }
    }
     */
    @RequestMapping("/listbuttonShower")
    @ResponseBody
    public JsonResponse getListButtonShower(Integer objectType, Integer objectId ,Integer navId) {
        ListButtonShowerBean buttonShowerBean = new ListButtonShowerBean();
        List<String[]> list1=new ArrayList<>();
        String [] s = {"1","操作记录"};
        list1.add(s);
        buttonShowerBean.setHasCompanyTeamButton(true);
        buttonShowerBean.setHasCompanyTeamButtonAdd(true);
        buttonShowerBean.setRightButtonList(list1);
        return JsonResponseTool.success(buttonShowerBean);

    }
}
