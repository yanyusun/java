package com.dqys.business.controller;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
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
 * 协作器（跨部门、跨机构协作工具）
 * Created by mkfeng on 2016/7/12.
 */
@Controller
@RequestMapping("/coordinator")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;


    /**
     * @api {post} coordinator/list 借款人或是资产包的参与者
     * @apiParam {int} companyId 公司ID
     * @apiParam {int} objectId 对象id
     * @apiParam {int} type 请求类型（1借款人2资产包）
     * @apiDescription 查询借款人或是资产包信息
     * @apiSampleRequest coordinator/list
     * @apiGroup Coordinator
     * @apiName coordinator/list
     */
    @RequestMapping("/list")
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
     * @apiParam {int} userIds 被邀请人的id
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
        Map map = coordinatorService.addTeammate(userTeamId, userId, remark, userIds);
        return JsonResponseTool.success(map);

    }

    /**
     * @api {post} coordinator/isAccept 被邀请人员同意或是拒绝请求
     * @apiParam {int} teammateId 参与人关联表id
     * @apiParam {string} status 状态（1同意2拒绝）
     * @apiDescription 被邀请人员确认和拒绝
     * @apiSampleRequest coordinator/isAccept
     * @apiGroup Coordinator
     * @apiName coordinator/isAccept
     */
    @RequestMapping("/isAccept")
    @ResponseBody
    public JsonResponse isAccept(Integer teammateId, Integer status) {
        Map map = coordinatorService.isAccept(teammateId, status);
        return JsonResponseTool.success(map);
    }


}
