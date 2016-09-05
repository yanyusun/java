package com.dqys.business.controller;

import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.impl.AssetServiceImpl;
import com.dqys.business.service.utils.excel.ExcelUtilAsset;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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


    /**
     * @api {post} coordinator/list 借款人或是资产包的参与者
     * @apiParam {int} companyId 公司ID
     * @apiParam {int} objectId 对象id
     * @apiParam {int} type 请求类型（1借款人2资产包3抵押物）
     * @apiDescription 查询借款人或是资产包信息
     * @apiSampleRequest coordinator/list
     * @apiGroup Coordinator
     * @apiName coordinator/list
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse coordinatorList(Integer companyId, Integer objectId, Integer type, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        Map<String, Object> map = new HashMap<>();
        if (objectId != null && type == 1) {
            coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.LENDER.getValue(), userId);//查询借款人团队
        } else if (objectId != null && type == 2) {
            coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.ASSETPACKAGE.getValue(), userId);//查询资产包团队
        } else if (objectId != null && type == 3) {
            coordinatorService.readByLenderOrAsset(map, companyId, objectId, ObjectTypeEnum.PAWN.getValue(), userId);//查询抵押物
        } else {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (map.get("result").toString().equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.noData();
        }
    }

    /**
     * @api {post} coordinator/userList 选择人员
     * @apiParam {string} realName 用户名称或真实姓名
     * @apiDescription 添加参与人的时候选择人员（公司所有员工）
     * @apiSampleRequest coordinator/userList
     * @apiGroup Coordinator
     * @apiName coordinator/userList
     */
    @RequestMapping("/userList")
    @ResponseBody
    public JsonResponse userList(String realName, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        Map<String, Object> map = coordinatorService.getCompanyUserList(realName, userId, null);
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
    public JsonResponse addTeammate(@RequestParam("userTeamId") Integer userTeamId, @RequestParam("remark") String remark, HttpServletRequest httpServletRequest, Integer... userIds) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        if (CommonUtil.checkParam(userTeamId, userIds)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map map = coordinatorService.addTeammate(userTeamId, userId, remark, userIds);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure("操作失败");
        }
    }

    /**
     * @api {post} coordinator/isAccept 被邀请人员同意或是拒绝请求
     * @apiParam {int} teammateId 参与人关联表id
     * @apiParam {int} status 状态（1同意2拒绝）
     * @apiDescription 被邀请人员确认和拒绝
     * @apiSampleRequest coordinator/isAccept
     * @apiGroup Coordinator
     * @apiName coordinator/isAccept
     */
    @RequestMapping("/isAccept")
    @ResponseBody
    public JsonResponse isAccept(Integer teammateId, Integer status, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        if (CommonUtil.checkParam(teammateId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (status == 1 || status == 2) {
            Map map = coordinatorService.isAccept(teammateId, status, userId);
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.paramErr("状态参数有误");
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
    public JsonResponse addInitiative(Integer userTeammateId, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        if (CommonUtil.checkParam(userTeammateId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map map = coordinatorService.addTeammate(userTeammateId, userId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else if (MessageUtils.transMapToString(map, "result").equals("exist")) {
            return JsonResponseTool.failure("已经存在");
        } else {
            return JsonResponseTool.failure("操作失败");
        }
    }

    /**
     * @api {post} coordinator/auditBusiness 平台业务审核
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10资产包11借款人)
     * @apiParam {int} status 状态（1通过2不通过）
     * @apiSampleRequest coordinator/auditBusiness
     * @apiGroup Coordinator
     * @apiName coordinator/auditBusiness
     */
    @RequestMapping("/auditBusiness")
    @ResponseBody
    public JsonResponse auditBusiness(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType, @RequestParam("status") Integer status, HttpServletRequest httpServletRequest) throws Exception {
        if (CommonUtil.checkParam(objectType, objectId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != ObjectTypeEnum.LENDER.getValue() && objectType != ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            return JsonResponseTool.paramErr("对象类型有误");
        }
        if (status != BusinessStatusEnum.platform_pass.getValue() && status != BusinessStatusEnum.platform_refuse.getValue()) {
            return JsonResponseTool.paramErr("状态有误");
        }
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        Map map = new HashMap<>();
        coordinatorService.auditBusiness(map, userId, objectId, objectType, status);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure("操作失败");
        }
    }

    /**
     * @api {post} coordinator/isPause 借款人或资产包暂停操作
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10资产包11借款人)
     * @apiParam {int} status 状态（0开启1暂停）
     * @apiSampleRequest coordinator/isPause
     * @apiGroup Coordinator
     * @apiName coordinator/isPause
     */
    @RequestMapping("/isPause")
    @ResponseBody
    public JsonResponse isPause(Integer objectId, Integer objectType, Integer status, HttpServletRequest httpServletRequest) throws Exception {

        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(objectType, objectId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != ObjectTypeEnum.LENDER.getValue() && objectType != ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            return JsonResponseTool.paramErr("对象类型有误");
        }
        if (status != 0 && status != 1) {
            return JsonResponseTool.paramErr("状态有误");
        }
        coordinatorService.isPause(map, objectId, objectType, status, userId);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else if (MessageUtils.transMapToString(map, "result").equals("repetition")) {
            return JsonResponseTool.failure("重复操作");
        } else {
            return JsonResponseTool.failure("操作失败");
        }
    }

    /**
     * @api {post} coordinator/delUser 协作器删除参与人（暂时）
     * @apiParam {int} teamUserId 原参与处置的人userId
     * @apiParam {int} userTeamId 团队协作器id
     * @apiParam {int} status 状态（0同意1拒绝）
     * @apiParam {int} substitutionUid 替补人userId
     * @apiSampleRequest coordinator/delUser
     * @apiGroup Coordinator
     * @apiName coordinator/delUser
     */
    @RequestMapping("/delUser")
    @ResponseBody
    public JsonResponse deleteTeammatUser(@RequestParam("teamUserId") Integer teamUserId, @RequestParam("userTeamId") Integer userTeamId,
                                          @RequestParam("status") Integer status, @RequestParam("substitutionUid") Integer substitutionUid, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
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
        map = coordinatorService.deleteTeammatUser(userId, teamUserId, userTeamId, status, substitutionUid);
        if (map.get("result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

}
