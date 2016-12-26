package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.sale.service.dto.FixedAssetDTO;
import com.dqys.sale.orm.query.FixedAssetQuery;
import com.dqys.sale.service.facade.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mkfeng on 2016/12/21.
 */
@Controller
@RequestMapping("/fixed")
public class FixedAssetController {

    @Autowired
    private FixedAssetService fixedAssetService;

    /**
     * @api {post} fixed/noVerify/fixedList 获取固定资产列表
     * @apiName fixed/noVerify/fixedList
     * @apiSampleRequest parter/noVerify/fixedList
     * @apiGroup　 fixed
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "query": {
     * "page": 0,
     * "pageCount": 20,
     * "startPage": 0,
     * "totalCount": 4,
     * "ids": null,
     * "isHomePage": null,
     * "checkStatus": null,
     * "enable": null
     * },
     * "fixedAssetList": [
     * {
     * "id": 1,
     * "assetType": 1,//资产类型:房产0设备1土地2
     * "isSpecial": null,//是否专项:0是，1不是
     * "entrustBegintime": "2016-12-27",;//开始委托时间
     * "entrustEndtime": "2016-12-29",//委托结束时间
     * "floor": null,//楼层<300
     * "year": null,//'年代
     * "address": "gasdfasdf",//地址
     * "righterType": 1,//产权方类型:个人，企业
     * "disposeStatus": 0,// 处置状态（0待处置1处置中2已处置）
     * "collectionNum": null,//收藏数量
     * "disposeNum": null,//申请处置数量
     * "no": null,//固定资产编号
     * "title": "asate",//标题
     * "orientation": null,//房朝向：0南北，1东南，2西南，3东北，4西北，5东，6西，7南，8北
     * "labels": [
     * {
     * "id": 1,
     * "type": 1,//标签类型
     * "name": "测试"//标签名称
     * }
     * ],
     * "disposes": [
     * {
     * "id": 1,
     * "disposeType": 1,//处置方式类型
     * "assetId": 1,
     * "assetType": 10,
     * "alg": 1,//处置方式算法
     * "value": "12"//处置方式数值
     * }
     * ],
     * "assetFiles": [
     * {
     * "id": 1,
     * "assetId": 1,
     * "filename": "11.jpg",//文件名称
     * "type": 1,//文件类型
     * "assetType": 10
     * }
     * ]
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/noVerify/fixedList")
    @ResponseBody
    public JsonResponse fixedList(FixedAssetQuery fixedAssetQuery) {
        return fixedAssetService.fixedList(fixedAssetQuery);
    }

    /**
     * @api {post} fixed/noVerify/getDetail 获取固定资产详情
     * @apiName fixed/noVerify/getDetail
     * @apiSampleRequest parter/noVerify/getDetail
     * @apiGroup　 fixed
     */
    @RequestMapping("/noVerify/getDetail")
    @ResponseBody
    public JsonResponse getDetail(Integer fixedAssetId) {
        return fixedAssetService.getDetail(fixedAssetId);
    }

    /**
     * @api {post} fixed/list 获取固定资产列表
     * @apiName fixed/list
     * @apiSampleRequest fixed/list
     * @apiGroup　 fixed
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(FixedAssetQuery fixedAssetQuery) {
        return fixedAssetService.list(fixedAssetQuery);
    }


    /**
     * @api {post} fixed/addFixed 添加固定资产
     * @apiName fixed/addFixed
     * @apiSampleRequest parter/addFixed
     * @apiGroup　 fixed
     */
    @RequestMapping("/addFixed")
    @ResponseBody
    public JsonResponse addFixed(@ModelAttribute FixedAssetDTO fixedAssetDTO) {
        return fixedAssetService.addFixed_tx(fixedAssetDTO);
    }

    /**
     * @api {post} fixed/updateFixed 修改固定资产
     * @apiName fixed/updateFixed
     * @apiSampleRequest parter/updateFixed
     * @apiGroup　 fixed
     */
    @RequestMapping("/updateFixed")
    @ResponseBody
    public JsonResponse updateFixed(@ModelAttribute FixedAssetDTO fixedAssetDTO) {
        return fixedAssetService.updateFixed(fixedAssetDTO);
    }


}
