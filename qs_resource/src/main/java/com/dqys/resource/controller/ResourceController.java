package com.dqys.resource.controller;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.ApiParseTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import com.dqys.resource.service.utils.ResourceTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author by pan on 16-4-20.
 */
@RestController
@RequestMapping("/res")
public class ResourceController {

    private static final String API_SYS_PROPERTY_KEY = "api_sys_property";

    @RequestMapping("/res_types")
    public Callable<JsonResponse<List>> businessResTypes() {
        return () -> JsonResponseTool.success(ApiParseTool.parseApiList(SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE), API_SYS_PROPERTY_KEY));
    }

    @RequestMapping("/upload")
    public Callable<JsonResponse<String>> upload(@RequestParam  String type, @RequestParam MultipartFile file) {
        Integer userId = UserSession.getCurrent().getUserId();
        return () -> {
            String fileName = ResourceTool.saveFileSyncTmp(type, userId, file);
            if(null == fileName) {
                return JsonResponseTool.failure("上传失败");
            }

            return JsonResponseTool.success(fileName);
        };
    }

}
