package com.dqys.business.controller;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.repay.RepayEnum;
import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.orm.pojo.repay.Repay;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.RepayService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @api {post} repay/repayList 获取还款记录列表
     * @apiName repay/repayList
     * @apiSampleRequest repay/repayList
     * @apiParam {int} repayType 还款类型(0还利息1还本金2还利息加本金)
     * @apiParam {int} repayFidType 对象类型（1借据2抵押物）
     * @apiParam {int} lenderId 借款人id
     * @apiGroup Repay
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "repays": [
     * {
     * "id": 59,
     * "damageDate": "2016-09-22",//还款时间,
     * "repayType": 2,//还款类型(0还利息1还本金2还利息加本金)
     * "operUserId": 263,//操作员id
     * "repayM": 1123,// 还款总金额
     * "repayFid": 528,//还款主体id
     * "repayFidName": null,//还款主体名称
     * "repayWay": 2,//还款方式
     * "repayBills": null,//还款单据图片
     * "remark": "",//备注
     * "version": 0,//
     * "createAt": "2016-09-22",//
     * "updateAt": "2016-09-22",//
     * "stateflag": 0,//
     * "repayFidType": 1,//还款主体类型（借据或抵押物）
     * "status": 1,//状态（0还清，1还部分，2还款无效）
     * "lenderId": 346,//借款人id
     * "lenderName": "1",//借款人姓名
     * "operUserName": "催收测试" //操作员姓名
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("repayList")
    @ResponseBody
    public JsonResponse repayList(@RequestParam("repayType") Integer repayType, @RequestParam("repayFidType") Integer repayFidType, @RequestParam("lenderId") Integer lenderId) {
        Map map = new HashMap<>();
        Repay repay = new Repay();
        repay.setRepayType(repayType);
        repay.setRepayFidType(repayFidType);
        repay.setLenderId(lenderId);
        repayService.getRepayList(repay, map);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure("操作失败");
        }
    }


    /**
     * @api {post} repay/getIouAndPawnByLender 根据借款人id获取底下的借据和抵押物
     * @apiName repay/getIouAndPawnByLender
     * @apiSampleRequest repay/getIouAndPawnByLender
     * @apiParam {int} lenderId 借款人id
     * @apiGroup Repay
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "pawns": [
     * {
     * "number": "CO16090004",//编号
     * "id": 843 //抵押物id
     * }
     * ],
     * "ious": [
     * {
     * "number": "IO16090002",//编号
     * "id": 540 //借据id
     * }
     * ]
     * }
     * }
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
                                         @RequestParam("file") String file) throws Exception {
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
        Integer userId = UserSession.getCurrent().getUserId();
        map = repayService.updateRepayMoney(repayId, userId, objectId, objectType, repayType, repayWay, money, remark, file);
        if ("yes".equals(MessageUtils.transMapToString(map, "result"))) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }

    /**
     * @api {post} repay/repayMoney 还款操作
     * @apiName repay/repayMoney
     * @apiSampleRequest repay/repayMoney
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型（1借据2抵押物3不限对象（objectId为借款人id））
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
                                   @RequestParam("money") Double money, String remark,
                                   String file) {
        if (CommonUtil.checkParam(objectId, objectType, repayType, repayWay, money)) {
            return JsonResponseTool.paramErr("参数错误");
        }
//        if (file.getName().indexOf("jpg") < 0 && file.getName().indexOf("jpeg") < 0) {
//            return JsonResponseTool.paramErr("文件格式错误");
//        }
        if (remark != null && remark.length() > 255) {
            return JsonResponseTool.paramErr("备注填写过多，只可输入255个以内");
        }
        if (money <= 0 || !money.toString().matches("\\d*([.]\\d{1,2})?")) {
            return JsonResponseTool.paramErr("金额输入有误");
        }
        if (objectType != RepayEnum.OBJECT_IOU.getValue() && objectType != RepayEnum.OBJECT_PAWN.getValue() && objectType != RepayEnum.OBJECT_UNLIMITED.getValue()) {
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
            Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
            map = repayService.repayMoney(userId, objectId, objectType, repayType, repayWay, money, remark, file);
            if ("yes".equals(MessageUtils.transMapToString(map, "result"))) {
                return JsonResponseTool.success(map);
            } else {
                return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
            }
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
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            } else {
                return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
            }
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
    public JsonResponse postpone(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType, @RequestParam("postponeTime") String postponeTime) {
        if (CommonUtil.checkParam(objectId, objectType, postponeTime)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!postponeTime.matches("[1,2,3]\\d{3}[-](([0]?[1-9])|([1][0,1,2]))[-](([0]?[1-9])|([1,2][0-9])|([3][0,1]))")) {
            return JsonResponseTool.paramErr("日期格式错误");
        }
        if (objectType != ObjectTypeEnum.ASSETPACKAGE.getValue() && objectType != ObjectTypeEnum.LENDER.getValue()) {
            return JsonResponseTool.paramErr("对象类型错误");
        }
        Map map = new HashMap<>();
        try {
            Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
            DamageApply damageApply = new DamageApply();
            damageApply.setStatus(0);
            damageApply.setApply_object_id(objectId);
            damageApply.setApply_user_id(userId);
            damageApply.setDamage_date(DateFormatTool.parse(postponeTime, DateFormatTool.DATE_FORMAT_10_REG1));
            damageApply.setObject_type(objectType);
            repayService.postpone(damageApply, map);
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            } else {
                return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
            }
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
    public JsonResponse postpone(@RequestParam("applyId") Integer applyId, @RequestParam("status") Integer status) {
        Map map = new HashMap<>();
        if (CommonUtil.checkParam(applyId, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (status != 1 && status != 2) {
            return JsonResponseTool.paramErr("审批状态错误");
        }
        try {
            Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
            repayService.auditPostpone(applyId, status, userId, map);
            if (MessageUtils.transMapToString(map, "result").equals("yes")) {
                return JsonResponseTool.success(map);
            } else {
                return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
            }
        } catch (Exception e) {
            map.put("result", "exception");
            return JsonResponseTool.serverErr();
        }
    }

    /**
     * @return
     * @api {post} repay/caseRepayMoney 案件已还款操作
     * @apiName repay/caseRepayMoney
     * @apiSampleRequest repay/caseRepayMoney
     * @apiParam {int} caseId 案件id
     * @apiParam {string} [remark] 备注
     * @apiParam {string} file 单据图片
     * @apiGroup Repay
     */
    @RequestMapping("/caseRepayMoney")
    @ResponseBody
    public JsonResponse caseRepayMoney(@RequestParam Integer caseId, @RequestParam String file, String remark) throws Exception {
        if ("".equals(file.trim())) {
            return JsonResponseTool.failure("请上传单据");
        }
        Map map = repayService.caseRepayMoney(caseId, remark, file);
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.failure(MessageUtils.transMapToString(map, "msg"));
        }
    }


}
