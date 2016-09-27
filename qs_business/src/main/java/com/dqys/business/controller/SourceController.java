package com.dqys.business.controller;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yvan on 16/8/1.
 */
@RestController
@RequestMapping(value = "/source")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    /**
     * 分类列表
     * @param lenderId
     * @param type
     * @return
     */
    @RequestMapping(value = "/listNavigation")
    public JsonResponse listNavigation(@RequestParam Integer lenderId, @RequestParam(defaultValue = "0") Integer type) {
        if (CommonUtil.checkParam(lenderId, type)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(sourceService.listNavigation(lenderId, type));
    }

    /**
     * 增加分类
     * @param sourceNavigation
     * @return
     */
    @RequestMapping(value = "/addNavigation", method = RequestMethod.POST)
    public JsonResponse addNavigation(@ModelAttribute SourceNavigation sourceNavigation) {
        if (CommonUtil.checkParam(sourceNavigation, sourceNavigation.getType(), sourceNavigation.getPid(),
                sourceNavigation.getName())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = sourceService.addNavigation(sourceNavigation);
        if(CommonUtil.checkResult(result)){
            return JsonResponseTool.failure("添加失败");
        }else{
            return JsonResponseTool.success(result);
        }
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteNavigation")
    public JsonResponse deleteNavigation(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if(sourceService.deleteNavigation(id)){
            return JsonResponseTool.success(null);
        }else{
            return JsonResponseTool.failure("删除失败");
        }
    }

    /**
     * 增加资源
     * @param sourceInfoDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(@ModelAttribute SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getLenderId(), sourceInfoDTO.getNavId(),
                sourceInfoDTO.getCode())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = sourceService.addSource(sourceInfoDTO);
        if(CommonUtil.checkResult(result)){
            return JsonResponseTool.failure("添加失败");
        }else{
            return JsonResponseTool.success(result);
        }
    }

    /**
     * 获取资源信息
     * @param lenderId
     * @param navId
     * @return
     */
    @RequestMapping(value = "/get")
        public JsonResponse get(@RequestParam Integer lenderId, @RequestParam Integer navId) {
        if (CommonUtil.checkParam(navId, lenderId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(sourceService.getSource(navId, lenderId));
    }

    /**
     * 修改资源信息
     * @param sourceInfoDTO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResponse update(@ModelAttribute SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getLenderId(), sourceInfoDTO.getNavId(),
                sourceInfoDTO.getCode())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = sourceService.updateSource(sourceInfoDTO);
        if(CommonUtil.checkResult(result)){
            return JsonResponseTool.failure("修改失败");
        }else{
            return JsonResponseTool.success(result);
        }
    }




}
