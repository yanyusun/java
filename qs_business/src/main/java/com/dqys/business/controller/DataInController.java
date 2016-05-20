package com.dqys.business.controller;

import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by pan on 16-5-19.
 */
@RestController
@RequestMapping("/api")
public class DataInController extends BaseApiContorller {

    @RequestMapping(value = "/asset_input", method = RequestMethod.POST)
    public Callable<JsonResponse> inputAssetPackage(@RequestBody Map assetInfo) {
        return () -> {
            System.out.println(assetInfo);
            //System.out.println(contactsInfo);
            return JsonResponseTool.success("");
        };
    }


}
