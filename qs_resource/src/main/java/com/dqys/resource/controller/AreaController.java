package com.dqys.resource.controller;

import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TArea;
import com.dqys.core.utils.AreaTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by pan on 16-5-20.
 */
@RestController
@RequestMapping("/area")
public class AreaController extends BaseApiContorller {

    @RequestMapping("/list")
    public Callable<JsonResponse> queryAreaByUpper(@RequestParam(defaultValue = "0") Integer aid) {
        return new Callable<JsonResponse>() {
            @Override
            public JsonResponse call() throws Exception {
                List<TArea> tAreaList = AreaTool.listAreaByUpperId(aid);


                return null;
            }
        };
    }
}
