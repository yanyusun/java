package com.dqys.resource.controller;

import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TArea;
import com.dqys.core.utils.ApiParseTool;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.commons.lang3.StringUtils;
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

    @RequestMapping(path = "/list")
    public Callable<JsonResponse> queryAreaByUpper(@RequestParam(defaultValue = "0") Integer aid, @RequestParam(required = false) String nameLike) {
        return () -> {
            List<TArea> tAreaList = AreaTool.listAreaByUpperId(aid);
            if (null == tAreaList || tAreaList.isEmpty()) {
                return JsonResponseTool.noData();
            }

            if (StringUtils.isNoneEmpty(nameLike)) {
                tAreaList = AreaTool.filterAreaByName(tAreaList, nameLike);
            }

            return JsonResponseTool.success(ApiParseTool.parseApiList(tAreaList, KeyEnum.API_AREA_LIST));
        };
    }
}
