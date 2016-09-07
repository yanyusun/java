package com.dqys.business.controller;

import com.dqys.business.orm.pojo.sysNotice.SysNotice;
import com.dqys.business.orm.query.sysNotice.SysNoticeQuery;
import com.dqys.business.service.dto.sysNotice.SysNoticeDTO;
import com.dqys.business.service.service.sysNotice.SysNoticeService;
import com.dqys.business.service.utils.sysNotice.SysNoticeUtil;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on 16-9-7.
 */
@RestController
@RequestMapping(value = "/sys_notice")
public class SysNoticeController extends BaseApiContorller {
    @Autowired
    private SysNoticeService sysNoticeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(SysNoticeQuery sysNoticeQuery) {
        List<SysNotice> list=sysNoticeService.list(sysNoticeQuery);
        if(sysNoticeQuery.getIsPaging()){
            Map<String,Object> map = new HashMap<>();
            map.put("data",list);
            int total = sysNoticeService.queryCount(sysNoticeQuery);
            map.put("total",total);
            return JsonResponseTool.success(map);
        }else{
            return JsonResponseTool.success(list);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(SysNoticeDTO sysNoticeDTO) {
        SysNotice sysNotice = SysNoticeUtil.toSysNotice(sysNoticeDTO);
        sysNoticeService.insert(sysNotice);
        return JsonResponseTool.success(sysNotice);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(SysNoticeDTO sysNoticeDTO) {
        SysNotice sysNotice = SysNoticeUtil.toSysNotice(sysNoticeDTO);
        sysNoticeService.updateByPrimaryKeySelective(sysNotice);
        return JsonResponseTool.success(sysNotice);
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse update(int id) {
        sysNoticeService.deleteByPrimaryKey(id);
        return JsonResponseTool.success(null);
    }

}

