package com.dqys.business.controller;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.repay.RepayEnum;
import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.service.service.RepayService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 还款和延期操作管理
 * Created by mkfeng on 2016/7/19.
 */
@Controller
@RequestMapping("/repay")
public class RepayController {
    @Autowired
    private RepayService repayService;

    /**
     * @api {post} repay/getIouAndPawnByLender 根据借款人id获取底下的借据和抵押物
     * @apiName repay/getIouAndPawnByLender
     * @apiSampleRequest repay/getIouAndPawnByLender
     * @apiParam {int} lenderId 借款人id
     * @apiGroup Repay
     */
    @RequestMapping("/getIouAndPawnByLender")
    @ResponseBody
    public JsonResponse getIouAndPawnByLender(@RequestParam("lenderId") Integer lenderId) {
        if (CommonUtil.checkParam(lenderId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map map = new HashMap<>();
        repayService.getIouAndPawnByLender(lenderId, map);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} repay/updateRepayMoney 修改还款操作
     * @apiName repay/updateRepayMoney
     * @apiSampleRequest repay/updateRepayMoney
     * @apiParam {int} repayId 还款记录id
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型（1借据2抵押物）
     * @apiParam {int} repayType 还款类型（0还利息1还本金2还利息加本金）
     * @apiParam {int} repayWay 还款方式 （0直接还款1转贷还款2变卖还款3其他方式）
     * @apiParam {double} money 还款金额(单位：元)
     * @apiParam {string} remark 备注
     * @apiParam {file} file 文件
     * @apiGroup Repay
     */
    @RequestMapping("/updateRepayMoney")
    @ResponseBody
    public JsonResponse updateRepayMoney(@RequestParam("repayId") Integer repayId, @RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType,
                                         @RequestParam("repayType") Integer repayType, @RequestParam("repayWay") Integer repayWay,
                                         @RequestParam("money") Double money, @RequestParam("remark") String remark,
                                         @RequestParam("file") String file, HttpServletRequest httpServletRequest) throws Exception {
        if (CommonUtil.checkParam(repayId, objectId, objectType, repayType, repayWay, money)) {
            return JsonResponseTool.paramErr("参数错误");
        }
//        if (file.getName().indexOf("jpg") < 0 && file.getName().indexOf("jpeg") < 0) {
//            return JsonResponseTool.paramErr("文件格式错误");
//        }
        if (money <= 0 || !money.toString().matches("\\d*([.]\\d{1,2})?")) {
            return JsonResponseTool.paramErr("金额输入有误");
        }
        if (objectType != RepayEnum.OBJECT_IOU.getValue() && objectType != RepayEnum.OBJECT_PAWN.getValue()) {
            return JsonResponseTool.paramErr("对象类型错误");
        }
        if (repayType != RepayEnum.TYPE_ACCRUAL.getValue() && repayType != RepayEnum.TYPE_PRINCIPAL.getValue() && repayType != RepayEnum.TYPE_A_P.getValue()) {
            return JsonResponseTool.paramErr("还款类型错误");
        }
        if (repayType != RepayEnum.WAY_DIRECT.getValue() && repayType != RepayEnum.WAY_ENLENDING.getValue() && repayType != RepayEnum.WAY_SELL.getValue() && repayType != RepayEnum.WAY_OTHER.getValue()) {
            return JsonResponseTool.paramErr("还款方式错误");
        }
        Map map = new HashMap<>();
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        repayService.updateRepayMoney(repayId, userId, objectId, objectType, repayType, repayWay, money, remark, file, map);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} repay/repayMoney 还款操作
     * @apiName repay/repayMoney
     * @apiSampleRequest repay/repayMoney
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型（1借据2抵押物）
     * @apiParam {int} repayType 还款类型（0还利息1还本金2还利息加本金）
     * @apiParam {int} repayWay 还款方式 （0直接还款1转贷还款2变卖还款3其他方式）
     * @apiParam {double} money 还款金额(单位：元)
     * @apiParam {string} remark 备注
     * @apiParam {file} file 文件
     * @apiGroup Repay
     */
    @RequestMapping("/repayMoney")
    @ResponseBody
    public JsonResponse repayMoney(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType,
                                   @RequestParam("repayType") Integer repayType, @RequestParam("repayWay") Integer repayWay,
                                   @RequestParam("money") Double money, @RequestParam("remark") String remark,
                                   @RequestParam("file") String file, HttpServletRequest httpServletRequest) {
        if (CommonUtil.checkParam(objectId, objectType, repayType, repayWay, money)) {
            return JsonResponseTool.paramErr("参数错误");
        }
//        if (file.getName().indexOf("jpg") < 0 && file.getName().indexOf("jpeg") < 0) {
//            return JsonResponseTool.paramErr("文件格式错误");
//        }
        if (money <= 0 || !money.toString().matches("\\d*([.]\\d{1,2})?")) {
            return JsonResponseTool.paramErr("金额输入有误");
        }
        if (objectType != RepayEnum.OBJECT_IOU.getValue() && objectType != RepayEnum.OBJECT_PAWN.getValue()) {
            return JsonResponseTool.paramErr("对象类型错误");
        }
        if (repayType != RepayEnum.TYPE_ACCRUAL.getValue() && repayType != RepayEnum.TYPE_PRINCIPAL.getValue() && repayType != RepayEnum.TYPE_A_P.getValue()) {
            return JsonResponseTool.paramErr("还款类型错误");
        }
        if (repayType != RepayEnum.WAY_DIRECT.getValue() && repayType != RepayEnum.WAY_ENLENDING.getValue() && repayType != RepayEnum.WAY_SELL.getValue() && repayType != RepayEnum.WAY_OTHER.getValue()) {
            return JsonResponseTool.paramErr("还款方式错误");
        }
        Map map = new HashMap<>();
        try {
            Integer userId = ProtocolTool.validateUser(
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
            );
            map = repayService.repayMoney(userId, objectId, objectType, repayType, repayWay, money, remark, file);
            return JsonResponseTool.success(map);
        } catch (Exception e) {
            map.put("result", "exception");
            return JsonResponseTool.serverErr();
        }
    }

    @RequestMapping("/reversal2")
    @ResponseBody
    public JsonResponse reversal(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType) {
        if (CommonUtil.checkParam(objectId, objectType)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (objectType != RepayEnum.OBJECT_IOU.getValue() && objectType != RepayEnum.OBJECT_PAWN.getValue()) {
            return JsonResponseTool.paramErr("对象类型错误");
        }
        Map map = new HashMap<>();
        try {
            map = repayService.reversal(objectId, objectType);
            return JsonResponseTool.success(map);
        } catch (Exception e) {
            map.put("result", "exception");
            return JsonResponseTool.serverErr();
        }
    }

    /**
     * @api {post} repay/reversal 还款冲正操作
     * @apiName repay/reversal
     * @apiSampleRequest repay/reversal
     * @apiParam {int} repayId 还款记录id
     * @apiGroup Repay
     */
    @RequestMapping("/reversal")
    @ResponseBody
    public JsonResponse reversal(@RequestParam("repayId") Integer repayId) {
        if (CommonUtil.checkParam(repayId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Map map = new HashMap<>();
        try {
            map = repayService.reversal(repayId);
            return JsonResponseTool.success(map);
        } catch (Exception e) {
            map.put("result", "exception");
            return JsonResponseTool.serverErr();
        }
    }


    /**
     * @api {post} repay/postpone 延期申请操作
     * @apiName repay/postpone
     * @apiSampleRequest repay/postpone
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型（11借款人10资产包）
     * @apiParam {string} postponeTime 延期时间（格式：yyyy-MM-dd）
     * @apiGroup Repay
     */
    @RequestMapping("/postpone")
    @ResponseBody
    public JsonResponse postpone(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType, @RequestParam("postponeTime") String postponeTime, HttpServletRequest httpServletRequest) {
        if (CommonUtil.checkParam(objectId, objectType, postponeTime)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!FormatValidateTool.isDate(postponeTime)) {
            return JsonResponseTool.paramErr("日期格式错误");
        }
        if (objectType != ObjectTypeEnum.ASSETPACKAGE.getValue() && objectType != ObjectTypeEnum.LENDER.getValue()) {
            return JsonResponseTool.paramErr("对象类型错误");
        }
        Map map = new HashMap<>();
        try {
            Integer userId = ProtocolTool.validateUser(
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
            );
            DamageApply damageApply = new DamageApply();
            damageApply.setStatus(0);
            damageApply.setApply_object_id(objectId);
            damageApply.setApply_user_id(userId);
            damageApply.setDamage_date(DateFormatTool.parse(postponeTime, DateFormatTool.DATE_FORMAT_10_REG1));
            damageApply.setObject_type(objectType);
            repayService.postpone(damageApply, map);
            return JsonResponseTool.success(map);
        } catch (Exception e) {
            map.put("result", "exception");
            return JsonResponseTool.serverErr();
        }
    }

    /**
     * @api {post} repay/auditPostpone 延期申请审核操作
     * @apiName repay/auditPostpone
     * @apiSampleRequest repay/auditPostpone
     * @apiParam {int} applyId 延期记录id
     * @apiParam {int} status 审核状态（1通过2拒绝）
     * @apiGroup Repay
     */
    @RequestMapping("/auditPostpone")
    @ResponseBody
    public JsonResponse postpone(@RequestParam("applyId") Integer applyId, @RequestParam("status") Integer status, HttpServletRequest httpServletRequest) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(applyId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (status != 1 && status != 2) {
            return JsonResponseTool.paramErr("审批状态错误");
        }
        try {
            Integer userId = ProtocolTool.validateUser(
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                    httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
            );
            repayService.auditPostpone(applyId, status, userId, map);
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            } else {
                return JsonResponseTool.failure("操作失败");
            }
        } catch (Exception e) {
            map.put("result", "exception");
            return JsonResponseTool.serverErr();
        }
    }


}
