package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.query.AssetUserReQuery;
import com.dqys.sale.service.facade.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 业务操作管理
 * Created by mkfengs on 2016/12/26.
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * @api {post} /business/collect 前台收藏操作
     * @apiName /business/collect
     * @apiSampleRequest /business/collect
     * @apiGroup　 businessSale
     * @apiParam {int} status 是否收藏（0否1是）
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10固定资产11个人债权12逾期贷款13企业债权14司法拍卖15关注类资产16资产包)
     */
    @RequestMapping("/collect")
    @ResponseBody
    public JsonResponse collect(Integer status, Integer objectId, Integer objectType) {
        if (status == null || objectId == null || objectType == null) {
            return JsonResponseTool.failure("参数缺少");
        }
        Map map = businessService.collect(status, objectId, objectType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} /business/applyDispose 前台处置申请操作
     * @apiName /business/applyDispose
     * @apiSampleRequest /business/applyDispose
     * @apiGroup　 businessSale
     * @apiParam {int} status 是否申请处置（0否1是）
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型(10固定资产11个人债权12逾期贷款13企业债权14司法拍卖15关注类资产16资产包)
     */
    @RequestMapping("/applyDispose")
    @ResponseBody
    public JsonResponse applyDispose(Integer status, Integer objectId, Integer objectType) {
        Map map = businessService.applyDispose(status, objectId, objectType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} /business/release 发布业务操作
     * @apiName /business/release
     * @apiSampleRequest /business/release
     * @apiGroup　 businessSale
     * @apiParam {int} [reqUserId] 请求者id
     * @apiParam {int} businessId 业务id
     * @apiParam {int} businessLevel 业务所在位置
     * @apiParam {int} operType 操作
     */
    @RequestMapping("/release")
    @ResponseBody
    public JsonResponse release(Integer reqUserId, @RequestParam Integer businessId, @RequestParam Integer businessLevel, @RequestParam Integer operType) {
        Map map = businessService.release(reqUserId, businessId, businessLevel, operType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} /business/dispose 处置业务操作
     * @apiName /business/dispose
     * @apiSampleRequest /business/dispose
     * @apiGroup　 businessSale
     * @apiParam {int} [reqUserId] 请求者id
     * @apiParam {int} businessId 业务id
     * @apiParam {int} businessLevel 业务所在位置
     * @apiParam {int} operType 操作
     */
    @RequestMapping("/dispose")
    @ResponseBody
    public JsonResponse dispose(Integer reqUserId, @RequestParam Integer businessId, @RequestParam Integer businessLevel, @RequestParam Integer operType) {
        Map map = businessService.dispose(reqUserId, businessId, businessLevel, operType);
        return CommonUtil.jsonResponse(map);
    }

    /**
     * @api {post} /business/collectionList 收藏或无效的对象
     * @apiName /business/collectionList
     * @apiSampleRequest /business/collectionList
     * @apiGroup　 businessSale
     * @apiParam {int} status 状态（1收藏2无效）
     */
    @RequestMapping("/collectionList")
    @ResponseBody
    public JsonResponse collectionList(@ModelAttribute AssetUserReQuery query) {
        Map map = businessService.collectionList(query);
        return CommonUtil.jsonResponse(map);
    }

}
