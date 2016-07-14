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


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(@ModelAttribute BusinessLogQuery businessLogQuery) {
        List<BusinessLog> BusinessLogList= businessLogService.list(businessLogQuery);
        return  JsonResponseTool.success(BusinessLogList);
    }


}
