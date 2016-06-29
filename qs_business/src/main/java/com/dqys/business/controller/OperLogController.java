package com.dqys.business.controller;

import com.dqys.business.orm.pojo.operLog.OperLog;
import com.dqys.business.service.service.OperLogService;
import com.dqys.core.base.BasePageDTO;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonControllerUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     * 查询操作记录信息
     *
     * @param operLog
     * @return
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public JsonResponse operLogList(OperLog operLog) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<OperLog> operLogs = operLogService.selectByOperLog(operLog);
        return JsonResponseTool.success(operLogs);
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
