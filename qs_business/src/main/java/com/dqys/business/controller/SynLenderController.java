package com.dqys.business.controller;

import com.dqys.business.service.service.SynthLenderService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 催收机构的借款人详情
 * Created by mkfeng on 2016/9/9.
 */
@Controller
@RequestMapping("/synLender")
public class SynLenderController {

    @Autowired
    private SynthLenderService synthLenderService;

    /**
     * @api {post} zcy/pawnList 借款人信息并关联下抵押物和借据信息
     * @apiParam {int} lenderId 借款人id
     * @apiSampleRequest synLender/pawnList
     * @apiGroup synLender
     * @apiName synLender/pawnList
     */
    @RequestMapping("/pawnList")
    @ResponseBody
    public JsonResponse pawnList(Integer lenderId) {
        if (UserSession.getCurrent() == null) {
            return JsonResponseTool.authFailure("账号没登入");
        }
        Map map = new HashMap<>();
        map = synthLenderService.pawnList(lenderId);
        if ("yes".equals(MessageUtils.transMapToString(map, "result"))) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.successNullList();
        }
    }

}
