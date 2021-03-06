package com.dqys.business.controller;

import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 协作器（协作工具）
 * Created by mkfeng on 2016/7/12.
 */
@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private MessageService messageService;

    /**
     * @api {post} coordinator/list 协作器借款人或是资产包的参与者列表
     * @apiParam {int} companyId 公司ID
     * @apiParam {int} objectId 对象id
     * @apiParam {int} type 请求类型（1借款人2资产包3抵押物4资产源5案件）
     * @apiDescription 查询借款人或是资产包信息
     * @apiSampleRequest coordinator/list
     * @apiGroup Coordinator
     * @apiName coordinator/list
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",//反馈结果状态（成功yes）
     * "loan": 2221,//总贷款
     * "dayCount": 22,//逾期天数
     * "teams": [
     * {
     * "userId": 11,//用户id
     * "roleType": 0,//角色类型（0管理者1所属人2参与人）
     * "realName": "WZHPAN",//真实姓名
     * "teamName": "研发部门",//团队名称
     * "finishTask": 0,//完成任务数
     * "totalTask": 3,//总任务数
     * "ongoingTask": 0,//当前进行任务数
     * "leaveWordTime": ""//最后留言时间
     * }
     * ],
     * "companys": [
     * {
     * "company_name": "test", //公司名称
     * "id": 3, //公司id
     * "userType": 1 //用户类别（1平台 2委托 31催收 32律所 33中介）
     * }
     * ],
     * "name": null, //名称
     * "appraisal": 31, //抵押物总评估
     * "accrual": 313,//总利息
     * "userTeamId": 352,//协作器id
     * "people": [
     * {
     * "type": 0,//角色类型(0管理者，1所属人 ，2参与人)
     * "peopleNum": 1 //对应的人数
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse coordinatorList(Integer companyId, Integer objectId, Integer type) {
        Integer userId = UserSession.getCurrent().getUserId();
        Map<String, Object> map = new HashMap<>();
        try {
            if (objectId != null && type == 1) {
                coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.LENDER.getValue(), userId);//查询借款人团队
            } else if (objectId != null && type == 2) {
                coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);//查询资产包团队
            } else if (objectId != null && type == 3) {
                coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.PAWN.getValue(), userId);//查询抵押物
            } else if (objectId != null && type == 4) {
                coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.ASSETSOURCE.getValue(), userId);//查询资产源
            } else if (objectId != null && type == 5) {
                coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.CASE.getValue(), userId);//查询资产源
            } else {
                return JsonResponseTool.paramErr("参数错误");
            }
            if (map.get("result").toString().equals("yes")) {
                return JsonResponseTool.success(map);
            } else {
                return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
            }
        } catch (Exception e) {
            return JsonResponseTool.success(null);
        }
    }

    /**
     * @api {post} coordinator/history 协作器历史参与人
     * @apiParam {int} userTeamId 公司ID
     * @apiSampleRequest coordinator/history
     * @apiGroup Coordinator
     * @apiName coordinator/history
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "teams": [
     * {
     * "userId": 296,
     * "roleType": 1,
     * "realName": "admin",
     * "teamName": "清搜团队",
     * "finishTask": 0,
     * "totalTask": 0,
     * "ongoingTask": 0,
     * "leaveWordTime": "2016-11-18",
     * "status": 99,
     * "joinType": 0,
     * "avg": "1_296_1479464843315.jpg"
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/history")
    @ResponseBody
    public JsonResponse history(@RequestParam Integer userTeamId) throws Exception {
        return CommonUtil.jsonResponse(coordinatorService.history(userTeamId));
    }

    /**
     * @api {post} coordinator/getUserDetail 获取员工明信片
     * @apiParam {int} userId 用户id
     * @apiSampleRequest coordinator/getUserDetail
     * @apiGroup Coordinator
     * @apiName coordinator/getUserDetail
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "detail": {
     * "userId": 285,
     * "companyId": 409,
     * "realName": "测试丰",//真实姓名
     * "companyName": "风腾律所",//公司名称
     * "mobile": "18267903327",//手机号
     * "avg": null,//头像地址
     * "roleType": 1,//角色类型（1-管理员;2-管理者;3-普通员工）
     * "userType": 31,//用户类型（0普通用户1平台管理员2委托31催收32司法33中介）
     * "occupation": null//职位
     * }
     * }
     * }
     */
    @RequestMapping("/getUserDetail")
    @ResponseBody
    public JsonResponse getUserDetail(@RequestParam Integer userId) throws Exception {
        Map map = coordinatorService.getUserDetail(userId);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} coordinator/userList 选择人员
     * @apiParam {string} realName 用户名称或真实姓名
     * @apiParam {int} userTeamId 协作器id
     * @apiDescription 添加参与人的时候选择人员（公司所有员工）
     * @apiSampleRequest coordinator/userList
     * @apiGroup Coordinator
     * @apiName coordinator/userList
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "users": [
     * {
     * "realName": "张三",//姓名
     * "companyId": 3,//公司id
     * "companyName": "test",//公司名称
     * "userName": "zs",//用户名称
     * "roleType": 1,//角色（1管理员，2管理者，3普通）
     * "userType": 31,//用户类型（31催收32律所33中介）
     * "userId": 12 //用户id
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/userList")
    @ResponseBody
    public JsonResponse userList(String realName, Integer userTeamId) throws Exception {
        Integer userId = UserSession.getCurrent().getUserId();
        Map<String, Object> map = coordinatorService.getCompanyUserList(realName, userId, null, userTeamId);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} coordinator/addTeammate 添加邀请人员
     * @apiParam {int} userTeamId 协作器id
     * @apiParam {string} remark 验证消息
     * @apiParam {int[]} userIds 被邀请人的id
     * @apiDescription 添加邀请人员到参与人关联表
     * @apiSampleRequest coordinator/addTeammate
     * @apiGroup Coordinator
     * @apiName coordinator/addTeammate
     */
    @RequestMapping("/addTeammate")
    @ResponseBody
    public JsonResponse addTeammate(@RequestParam("userTeamId") Integer userTeamId, @RequestParam("remark") String remark, Integer... userIds) throws Exception {
        Integer userId = UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(userTeamId, userIds)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map map = coordinatorService.addTeammate(userTeamId, userId, remark, userIds);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/isAccept 被邀请人员同意或是拒绝请求
     * @apiParam {int} teammateId 参与人关联表id
     * @apiParam {int} operUserId 邀请发起人
     * @apiParam {int} status 状态（1同意2拒绝）
     * @apiDescription 被邀请人员确认和拒绝
     * @apiSampleRequest coordinator/isAccept
     * @apiGroup Coordinator
     * @apiName coordinator/isAccept
     */
    @RequestMapping("/isAccept")
    @ResponseBody
    public JsonResponse isAccept(Integer teammateId, Integer status, Integer operUserId) throws Exception {
        Integer userId = UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(teammateId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (status != 1 && status != 2) {
            return JsonResponseTool.paramErr("状态参数有误");
        }
        Map map = coordinatorService.isAccept(teammateId, status, userId, operUserId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }

    }

    /**
     * @api {post} coordinator/addInitiative 人员主动添加到案组
     * @apiParam {int} userTeammateId 协作器id
     * @apiDescription 主动加入案组
     * @apiSampleRequest coordinator/addInitiative
     * @apiGroup Coordinator
     * @apiName coordinator/addInitiative
     */
    @RequestMapping("/addInitiative")
    @ResponseBody
    public JsonResponse addInitiative(Integer userTeammateId) throws Exception {
        Integer userId = UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(userTeammateId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map map = coordinatorService.addTeammate(userTeammateId, userId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/auditBusiness 平台业务审核
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10资产包11借款人)
     * @apiParam {int} status 状态（0重新申请1通过2不通过）
     * @apiSampleRequest coordinator/auditBusiness
     * @apiGroup Coordinator
     * @apiName coordinator/auditBusiness
     */
    @RequestMapping("/auditBusiness")
    @ResponseBody
    public JsonResponse auditBusiness(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType, @RequestParam("status") Integer status) throws Exception {
        if (CommonUtil.checkParam(objectType, objectId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != ObjectTypeEnum.LENDER.getValue() && objectType != ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            return JsonResponseTool.paramErr("对象类型有误");
        }
        if (status != BusinessStatusEnum.init.getValue() && status != BusinessStatusEnum.platform_pass.getValue() && status != BusinessStatusEnum.platform_refuse.getValue()) {
            return JsonResponseTool.paramErr("状态有误");
        }
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        coordinatorService.auditBusiness(map, userId, objectId, objectType, status);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/publish 录入信息的发布
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10资产包11借款人)
     * @apiParam {int} status 状态（0发布）
     * @apiSampleRequest coordinator/publish
     * @apiGroup Coordinator
     * @apiName coordinator/publish
     */
    @RequestMapping("/publish")
    @ResponseBody
    public JsonResponse publish(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType, @RequestParam("status") Integer status) throws Exception {
        if (CommonUtil.checkParam(objectType, objectId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != ObjectTypeEnum.LENDER.getValue() && objectType != ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            return JsonResponseTool.paramErr("对象类型有误");
        }
        if (status != BusinessStatusEnum.init.getValue() && status != BusinessStatusEnum.platform_pass.getValue() && status != BusinessStatusEnum.platform_refuse.getValue()) {
            return JsonResponseTool.paramErr("状态有误");
        }
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        coordinatorService.publish(map, userId, objectId, objectType, status);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/isPause 借款人或资产包暂停无效操作
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10资产包11借款人16资产源)
     * @apiParam {int} status 状态（0开启1暂停2无效）
     * @apiSampleRequest coordinator/isPause
     * @apiGroup Coordinator
     * @apiName coordinator/isPause
     */
    @RequestMapping("/isPause")
    @ResponseBody
    public JsonResponse isPause(Integer objectId, Integer objectType, Integer status) throws Exception {

        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(objectType, objectId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != ObjectTypeEnum.LENDER.getValue() && objectType != ObjectTypeEnum.ASSETPACKAGE.getValue() &&
                objectType != ObjectTypeEnum.ASSETSOURCE.getValue()) {
            return JsonResponseTool.paramErr("对象类型有误");
        }
        if (status != 0 && status != 1 && status != 2) {
            return JsonResponseTool.paramErr("状态有误");
        }
        coordinatorService.isPause(map, objectId, objectType, status, userId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/c/isPause 借款人或资产包暂停无效操作（C端）
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10资产包11借款人16资产源)
     * @apiParam {int} status 状态（0开启1暂停2无效）
     * @apiSampleRequest coordinator/c/isPause
     * @apiGroup Coordinator
     * @apiName coordinator/c/isPause
     */
    @RequestMapping("/c/isPause")
    @ResponseBody
    public JsonResponse isPauseC(Integer objectId, Integer objectType, Integer status) throws Exception {

        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(objectType, objectId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != ObjectTypeEnum.LENDER.getValue() && objectType != ObjectTypeEnum.ASSETPACKAGE.getValue() &&
                objectType != ObjectTypeEnum.ASSETSOURCE.getValue()) {
            return JsonResponseTool.paramErr("对象类型有误");
        }
        if (status != 0 && status != 1 && status != 2) {
            return JsonResponseTool.paramErr("状态有误");
        }
        coordinatorService.isPause(map, objectId, objectType, status, userId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/delUser 协作器删除参与人
     * @apiParam {int} teamUserId 原参与处置的人userId
     * @apiParam {int} userTeamId 团队协作器id
     * @apiParam {int} status 状态（0同意1拒绝）
     * @apiParam {int} substitutionUid 替补人userId
     * @apiParam {int} operUserId 邀请发起人
     * @apiSampleRequest coordinator/delUser
     * @apiGroup Coordinator
     * @apiName coordinator/delUser
     */
    @RequestMapping("/delUser")
    @ResponseBody
    public JsonResponse deleteTeammatUser(@RequestParam("teamUserId") Integer teamUserId, @RequestParam("userTeamId") Integer userTeamId,
                                          Integer status, Integer substitutionUid,
                                          Integer operUserId) throws Exception {
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(teamUserId, userTeamId)) {
            return JsonResponseTool.paramErr("参数有误");
        }
        if (status != null && substitutionUid != null) {
            if (status != 0 && status != 1) {
                return JsonResponseTool.paramErr("状态有误");
            }
            status += 1;
        }
        map = coordinatorService.deleteTeammatUser(userId, teamUserId, userTeamId, status, substitutionUid, operUserId);
        if (map.get("result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }


    /**
     * @api {post} coordinator/businessFlow 业务流转通知平台
     * @apiParam {int} objectId      对象id
     * @apiParam {int} objectType    对象类型
     * @apiParam {int} flowId        流转对象id
     * @apiParam {int} flowType      流转对象类型
     * @apiParam {int} operType      操作类型（参考IouEnum 或PawnEnum）
     * @apiParam {int} companyTeamId 分配器id
     * @apiSampleRequest coordinator/businessFlow
     * @apiGroup companyRelation
     * @apiName coordinator/businessFlow
     */
    @RequestMapping("/businessFlow")
    @ResponseBody
    public JsonResponse businessFlow(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType,
                                     @RequestParam("flowId") Integer flowId, @RequestParam("flowType") Integer flowType
            , @RequestParam("operType") Integer operType, @RequestParam("companyTeamId") Integer companyTeamId) throws Exception {
        if (CommonUtil.checkParam(objectId, objectType, flowId, flowType, operType)) {
            return JsonResponseTool.paramErr("参数有误");
        }
        Map map = coordinatorService.businessFlow(objectId, objectType, flowId, flowType, operType, companyTeamId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/businessFlowResult 平台审核业务流转通知
     * @apiParam {int} objectId      对象id
     * @apiParam {int} objectType    对象类型
     * @apiParam {int} flowId        业务流转对象id
     * @apiParam {int} flowType      业务流转对象类型
     * @apiParam {int} operType     流转操作
     * @apiParam {int} receiveUserId 接收者id（请求公司）
     * @apiParam {int} status        状态（0拒绝1接收）
     * @apiParam {int} [messageId] 消息id
     * @apiParam {int} [operStatus]   操作状态（0默认未操作1同意2拒绝）
     * @apiParam {int} [inviteUserId] 被邀请用户id
     * @apiParam {int} flowBusinessId 业务流转状态id
     * @apiSampleRequest coordinator/businessFlowResult
     * @apiGroup companyRelation
     * @apiName coordinator/businessFlowResult
     */
    @RequestMapping("/businessFlowResult")
    @ResponseBody
    public JsonResponse businessFlowResult(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType,
                                           @RequestParam("flowId") Integer flowId, @RequestParam("flowType") Integer flowType,
                                           @RequestParam("operType") Integer operType, @RequestParam("receiveUserId") Integer receiveUserId,
                                           @RequestParam("status") Integer status, Integer messageId, Integer operStatus, Integer[] inviteUserId,
                                           Integer flowBusinessId) throws Exception {
        if (CommonUtil.checkParam(objectId, objectType, flowId, flowType, operType, receiveUserId, status)) {
            return JsonResponseTool.paramErr("参数有误");
        }
        if (status != 0 && status != 1) {
            return JsonResponseTool.paramErr("状态有误");
        }
        if (messageId != null && operStatus != null) {
            messageService.setOper(messageId, operStatus);
        }
        List<Integer> inviteUserIds = new ArrayList<>();
        if (inviteUserId != null && inviteUserId.length > 0) {
            for (Integer inv : inviteUserId) {
                inviteUserIds.add(inv);
            }
        }
        Map map = coordinatorService.sendBusinessFlowResult(objectId, objectType, flowId, flowType, operType, receiveUserId, status, inviteUserIds, flowBusinessId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} coordinator/setDeadline 设置委托期限
     * @apiParam {int} objectId      对象id
     * @apiParam {int} objectType    对象类型
     * @apiParam {int} dateTime     委托期限（格式：yyyy-MM-dd）
     * @apiSampleRequest coordinator/setDeadline
     * @apiGroup Coordinator
     * @apiName coordinator/setDeadline
     */
    @RequestMapping("/setDeadline")
    @ResponseBody
    public JsonResponse setDeadline(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType,
                                    @RequestParam("dateTime") String dateTime) throws Exception {
        if (CommonUtil.checkParam(objectId, objectType, dateTime)) {
            return JsonResponseTool.paramErr("参数有误");
        }
        if (!FormatValidateTool.isDate(dateTime)) {
            return JsonResponseTool.paramErr("日期格式有误");
        }
        Map map = coordinatorService.setDeadline(objectId, objectType, dateTime);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * 获取参与者，每个公司参与到清收案组中的案组展示
     *
     * @return
     */
    @RequestMapping("/c/participantList")
    @ResponseBody
    public JsonResponse participantList(@RequestParam Integer lenderId) throws BusinessLogException {
        Map map = coordinatorService.participantList(lenderId);
        return CommonUtil.jsonResponse(map);
    }


}
