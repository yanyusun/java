package com.dqys.resource.controller;

import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.ApiParseTool;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import com.dqys.core.utils.FileTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author by pan on 16-4-20.
 */
@RestController
@RequestMapping("/res")
public class ResourceController {

    @RequestMapping("/res_types")
    public Callable<JsonResponse<List>> businessResTypes() {
        return () -> JsonResponseTool.success(ApiParseTool.parseApiList(SysPropertyTool.getProperty(SysPropertyTypeEnum.FILE_BUSINESS_TYPE), KeyEnum.API_SYS_PROPERTY_KEY));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Callable<JsonResponse<String>> upload(@RequestParam  String type, MultipartFile file) {
        Integer userId = UserSession.getCurrent().getUserId();
        return () -> {
            String fileName = null;
            try {
                fileName = FileTool.saveFileSyncTmp(type, userId, file);
                if(null == fileName) {
                    return JsonResponseTool.failure("上传失败");
                }
            } catch (IOException e) {
                return JsonResponseTool.exception();
            }

            return JsonResponseTool.success(fileName);
        };
    }

}
