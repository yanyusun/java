package com.dqys.business.controller;

import com.dqys.business.orm.pojo.businessLog.BusinessLog;
import com.dqys.business.orm.query.businessLog.BusinessLogQuery;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 不分页：
     * {
    "code": 2000,
    "msg": "成功",
    "data": [
    {
    "id": 853,
    "version": null,
    "stateflag": null,
    "createAt": "2016-10-21",
    "updateAt": null,
    "remark": "",
    "objectType": 11,
    "objectId": null,
    "operType": null,
    "userId": null,
    "time": null,
    "text": "协作删除用户",
    "teamId": null,
    "businessId": null,
    "objectShowName": "借款人操作BO16100922",
    "userName": "朱明",
    "objectNo": "BO16100922",
    "operName": "协作删除用户",
    "objectName": "借款人操作"
    }］｝
     分页：
    {
    "code": 2000,
    "msg": "成功",
    "data": {
    "total": 21,
    "data": [
    {
    "id": 853,
    "version": null,
    "stateflag": null,
    "createAt": "2016-10-21",
    "updateAt": null,
    "remark": "",
    "objectType": 11,
    "objectId": null,
    "operType": null,
    "userId": null,
    "time": null,
    "text": "协作删除用户",
    "teamId": null,
    "businessId": null,
    "objectShowName": "借款人操作BO16100922",
    "userName": "朱明",
    "objectNo": "BO16100922",
    "operName": "协作删除用户",
    "objectName": "借款人操作"
    },.....]}
     *
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(@ModelAttribute BusinessLogQuery businessLogQuery) {
        List<BusinessLog> businessLogList= businessLogService.list(businessLogQuery);
        if(businessLogQuery.getIsPaging()){
            Map<String, Object> map = new HashMap<>();
            map.put("data", businessLogList);
            int total = businessLogService.queryCount(businessLogQuery);
            map.put("total", total);
            return  JsonResponseTool.success(map);
        }else{
            return  JsonResponseTool.success(businessLogList);
        }
    }


}
