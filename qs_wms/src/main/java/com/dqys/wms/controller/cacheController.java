package com.dqys.wms.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.wms.utils.CacheTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yan on 16-9-22.
 */
@RestController
@RequestMapping("/cache")
public class cacheController {
    @RequestMapping("/clearMybById")
    public JsonResponse clearById(String id) {
        CacheTool cacheTool = new CacheTool();
        cacheTool.clearById(id);
        return JsonResponseTool.success("清楚所有缓存成功!");
    }
    @RequestMapping("/selectMybById")
    public JsonResponse selectMybById(String id) {
        CacheTool cacheTool = new CacheTool();
        List<Object> list=cacheTool.getById(id);
        return JsonResponseTool.success(list);
    }

}
