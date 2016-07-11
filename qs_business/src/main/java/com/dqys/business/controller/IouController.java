package com.dqys.business.controller;

import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.service.service.LenderService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Yvan on 16/7/11.
 */
@Controller
@RequestMapping(value = "/iou")
public class IouController {

    @Autowired
    private LenderService lenderService;

    /**
     * 删除借据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteIou")
    @ResponseBody
    public JsonResponse deleteIou(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(lenderService.deleteIOUInfo(id));
    }

    /**
     * 增加借据信息
     *
     * @param IOUInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/addIou")
    @ResponseBody
    public JsonResponse addIou(@ModelAttribute IOUInfo IOUInfo, @PathVariable Integer id) {
        if (CommonUtil.checkParam(IOUInfo, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        ContactInfo contactInfo = lenderService.getLenderInfo(id);
        if (contactInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        IOUInfo.setLenderId(id);
        return CommonUtil.responseBack(lenderService.addIOUInfo(IOUInfo, contactInfo.getName()));
    }

    /**
     * 修改借据信息
     *
     * @param IOUInfo
     * @return
     */
    @RequestMapping(value = "/updateIou")
    @ResponseBody
    public JsonResponse updateIou(@ModelAttribute IOUInfo IOUInfo) {
        if (CommonUtil.checkParam(IOUInfo)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = lenderService.updateIOUInfo(IOUInfo);
        if (id == null) {
            return JsonResponseTool.failure("修改失败");
        } else {
            return JsonResponseTool.success(id);
        }
    }

    /**
     * 获取借据信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getIouInfo")
    @ResponseBody
    public JsonResponse getIou(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(lenderService.getIOUInfo(id));
    }
}
