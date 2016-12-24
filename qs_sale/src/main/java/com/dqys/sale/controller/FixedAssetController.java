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
