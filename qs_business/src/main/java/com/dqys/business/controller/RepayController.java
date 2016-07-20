package com.dqys.business.controller;

import com.dqys.business.service.service.RepayService;
import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.ProtocolTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
     * @api {post} repay/repayMoney 还款操作
     * @apiName repay/repayMoney
     * @apiSampleRequest repay/repayMoney
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型（1借据2抵押物）
     * @apiParam {int} repayType 还款类型（0还利息1还本金2还利息加本金）
     * @apiParam {int} repayWay 还款方式 （0直接还款1转贷还款2变卖还款3其他方式）
     * @apiParam {double} money 还款金额
     * @apiParam {string} remark 备注
     * * @apiParam {string} file 文件
     * @apiGroup Repay
     */
    @RequestMapping("/repayMoney")
    public JsonResponse repayMoney(@RequestParam("objectId") Integer objectId, @RequestParam("objectType") Integer objectType,
                                   @RequestParam("repayType") Integer repayType, @RequestParam("repayWay") Integer repayWay,
                                   @RequestParam("money") Double money, @RequestParam("remark") String remark,
                                   @RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) {
        if (CommonUtil.checkParam(objectId, objectType, repayType, repayWay, money)) {
            return JsonResponseTool.paramErr("参数错误");
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

}
