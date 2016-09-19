package com.dqys.business.controller;

import com.dqys.business.service.service.index.IndexService;
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
 * Created by mkfeng on 2016/8/1.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexSerice;

    /**
     * @api {post} index/general 任务统计
     * @apiSampleRequest index/general
     * @apiGroup INDEX
     * @apiName index/general
     */
    @RequestMapping("/general")
    @ResponseBody
    public JsonResponse general() {
        Map map = new HashMap<>();
        try {
            Integer userId = UserSession.getCurrent().getUserId();
            indexSerice.getStatistic(map, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResponseTool.success(map);
    }
}
