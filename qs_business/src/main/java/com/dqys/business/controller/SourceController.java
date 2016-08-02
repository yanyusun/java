package com.dqys.business.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Yvan on 16/8/1.
 */
@Controller
@RequestMapping(value = "/source")
public class SourceController {

    /**
     * @api {get} http://{url}/asset/uploadImg 批量分配(未完成)
     * @apiName uploadImg
     * @apiGroup source
     * @apiParam {file} file 文件类型
     * @apiParam {number} id 借款人ID
     * @apiParam {number} navId 导航栏Id
     */
    @RequestMapping(value = "/uploadImg")
    @ResponseBody
    public JsonResponse uploadImg(@RequestParam(required = true) MultipartFile file,
                                  @RequestParam Integer id, @RequestParam Integer navId){
        if(CommonUtil.checkParam(file, id, navId)){
            return JsonResponseTool.paramErr("参数错误");
        }

        return null;
    }
}
