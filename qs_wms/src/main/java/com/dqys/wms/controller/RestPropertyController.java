package com.dqys.wms.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pan on 17-1-22.
 */
@RestController
@RequestMapping("/r_property")
public class RestPropertyController {
    @RequestMapping("/update")
    public JsonResponse propertyUpdate(@ModelAttribute TSysProperty tSystemConf) {
        if(!SysPropertyTool.updateSysProperty(tSystemConf)) {
            JsonResponseTool.failure("更新失败");
        }
        return JsonResponseTool.success(tSystemConf);
    }
}
