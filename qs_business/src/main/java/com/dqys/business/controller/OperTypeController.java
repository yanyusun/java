package com.dqys.business.controller;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.exception.bean.UndefinitionTypeException;
import com.dqys.business.service.service.permission.Permission;
import com.dqys.business.service.utils.common.buttonUtil.ListButtonShowerUtil;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by mkfeng on 2016/7/26.
 */
@Controller
@RequestMapping("/operType")
public class OperTypeController {

    //@Autowired
    // private OperTypeService operTypeService;

    @Autowired
    private Permission permission;

    /**
     * @api {post} operType/list 操作接口
     * @apiName operType/list
     * @apiSampleRequest operType/list
     * @apiParam {int} objectType 对象类型
     * @apiGroup　 OperType
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": [
     * {
     * "id": 119,
     * "operType": 111,
     * "operName": "修改/编辑"
     * },
     * {
     * "id": 120,
     * "operType": 112,
     * "operName": "添加关注"
     * },
     * {
     * "id": 121,
     * "operType": 113,
     * "operName": "评优/内部评级"
     * },
     * {
     * "id": 122,
     * "operType": 114,
     * "operName": "申请律师函"
     * },
     * {
     * "id": 123,
     * "operType": 115,
     * "operName": "申请延期"
     * },
     * {
     * "id": 124,
     * "operType": 116,
     * "operName": "设置为无效"
     * },
     * {
     * "id": 125,
     * "operType": 117,
     * "operName": "录跟进"
     * },
     * {
     * "id": 126,
     * "operType": 118,
     * "operName": "增加注释"
     * },
     * {
     * "id": 127,
     * "operType": 119,
     * "operName": "修改日志"
     * },
     * {
     * "id": 128,
     * "operType": 1110,
     * "operName": "操作日志"
     * },
     * {
     * "id": 129,
     * "operType": 1111,
     * "operName": "添加一条还款"
     * },
     * {
     * "id": 130,
     * "operType": 1112,
     * "operName": "分配借款人"
     * }
     * ]
     * }
     */
    /*@RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(@RequestParam("objectType") Integer objectType, @RequestParam("objectId") Integer objectId, Integer navId) {
        try {
            Integer roleType = RoleTypeEnum.GENERAL.getValue();//1-管理员;2-管理者;3-普通员工;4所属人;
            Integer userType = UserInfoEnum.USER_TYPE_COMMON.getValue();//0-普通用户;1-平台管理员;2-委托号;31-催收;32-律所;33-中介
            List<OperType> operTypes = operTypeService.getOperType(roleType, userType, objectType,objectId);
            return JsonResponseTool.success(operTypes);
        } catch (Exception e) {
            return JsonResponseTool.serverErr();
        }
    }*/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(@RequestParam("objectType") Integer objectType, @RequestParam("objectId") Integer objectId, @RequestParam("navId") Integer navId) {
        try {
            List<OperType> operTypes = permission.getOperTypes(objectType, objectId, navId);
            return JsonResponseTool.success(operTypes);
        } catch (Exception e) {
            return JsonResponseTool.serverErr();
        }
    }

    /**
     * @api {GET} http://{url}/operType/listbuttonShower 读取未读的数量
     * @apiName listbuttonShower
     * @apiGroup OperType
     * @apiParam {number} [objectId] 对象id
     * @apiParam {number} objectType 对象类型
     * @apiParam {number} navId 对象类型
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "hasRightButton": true,
     * "hasUserTeamButton": false,
     * "hasUserTeamButtonApply": false,
     * "hasUserTeamButtonAdd": false,
     * "hasCompanyTeamButton": true,
     * "hasCompanyTeamButtonApply": false,
     * "hasCompanyTeamButtonAdd": true,
     * "rightButtonList": [
     * [
     * "1",
     * "操作记录"
     * ]
     * ]
     * }
     * }
     */
    @RequestMapping("/listbuttonShower")
    @ResponseBody
    public JsonResponse getListButtonShower(Integer objectType, Integer objectId, Integer navId) throws UndefinitionTypeException {
        UserSession userSession = UserSession.getCurrent();
        String[] userTypes = userSession.getUserType().split(",");
        String[] roleId = userSession.getRoleId().split(",");
        return JsonResponseTool.success(ListButtonShowerUtil.getListButtonShowerBean(navId, objectType, userTypes[0], roleId[0]));

    }
    /**
     * @api {GET} http://{url}/operType/repayButtonShower  是否显示添加还款记录按钮
     * @apiName repayButtonShower
     * @apiGroup OperType
     * @apiParam {number} [lenderId] 对象id
     * @apiSuccessExample {json} Data-Response:
     *{
        "code": 2000,
        "msg": "成功",
        "data": true
        }
     */
    @RequestMapping("/repayButtonShower")
    @ResponseBody
    public JsonResponse getRepayButtonShower(Integer lenderId) throws UndefinitionTypeException {
        return JsonResponseTool.success(permission.hasRepayButton(lenderId));
    }


}
