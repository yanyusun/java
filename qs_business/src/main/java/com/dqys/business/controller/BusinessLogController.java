package com.dqys.business.controller;

import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yan on 16-7-14.
 */
@RestController
@RequestMapping(value = "/b_log")
public class BusinessLogController {
    @Autowired
    private BusinessLogService businessLogService;


    /**
     * @api {GET} http://{url}/b_log/list 查看日志列表
     * @apiName list
     * @apiGroup businessLog
     * @apiUse BusinessLogQuery
     * @apiSuccessExample {json} Data-Response:
     * {
        "code": 2000,
        "msg": "成功",
        "data": [
            {
            "id": 1,
            "objectType": 1,
            "objectId": 1,
            "operType": 1,
            "remark": "1",
            "userId": 1,
            "time": null,
            "text": "1",
            "teamId": 1,
            "businessId": 1,
            "version": 1,
            "createAt": "2016-07-16",
            "updateAt": "2016-07-16",
            "stateflag": 1
            }
           ]
         }
     *
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(@ModelAttribute BusinessLogQuery businessLogQuery) {
        List<BusinessLog> BusinessLogList= businessLogService.list(businessLogQuery);
        return  JsonResponseTool.success(BusinessLogList);
    }


}
