package com.dqys.business.controller;

import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.orm.pojo.operLog.OperLogDTO;
import com.dqys.business.service.service.OperLogService;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/6/28.
 */
@Controller
@RequestMapping("/operLog")
public class OperLogController {
    @Autowired
    private OperLogService operLogService;

    @Autowired
    OperTypeService operTypeService;

    /**
     * @param operLog(page:分页,pageCount:每页展示数量,time:操作时间（时间段的就用“,”分隔）,userId:操作人)
     * @return
     * @api {post} operLog 操作记录
     * @apiParam {int} page 分页数
     * @apiParam {int} pageCount 显示数目
     * @apiParam {string} time 操作时间
     * @apiParam {int} userId 用户id
     * @apiDescription 查询操作记录信息
     * @apiSampleRequest operLog/pageList
     * @apiGroup USER
     * @apiName operLog/pageList
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public JsonResponse operLogList(OperLog operLog) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<OperLogDTO> operLogs = operLogService.selectByOperLog(operLog);
        List<Map<String, Object>> operators = operLogService.operator();
        map.put("operators", operators);
        map.put("operLogs", operLogs);
        return JsonResponseTool.success(map);
    }

    /**
     * 修改备注
     *
     * @param remark 备注
     * @param id
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResponse editByOperLog(String remark, Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer result = 0;
        if (id != null) {
            OperLog operLog = new OperLog();
            operLog.setId(id);
            operLog.setRemark(remark);
            result = operLogService.editByOperLog(operLog);
        }
        return JsonResponseTool.success(result);
    }

}
