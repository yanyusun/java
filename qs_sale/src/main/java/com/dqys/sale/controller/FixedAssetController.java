package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.query.FixedAssetQuery;
import com.dqys.sale.service.facade.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/fixedList")
    @ResponseBody
    public JsonResponse fixedList(FixedAssetQuery fixedAssetQuery) {
        return fixedAssetService.fixedList(fixedAssetQuery);
    }

    @RequestMapping("/getDetail")
    @ResponseBody
    public JsonResponse getDetail(Integer fixedAssetId) {
        return fixedAssetService.getDetail(fixedAssetId);
    }

}
