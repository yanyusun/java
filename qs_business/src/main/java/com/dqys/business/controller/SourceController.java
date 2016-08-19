package com.dqys.business.controller;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Yvan on 16/8/1.
 */
@RestController
@RequestMapping(value = "/source")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    /**
     * @api {get} http://{url}/asset/uploadImg 批量分配(未完成)
     * @apiName uploadImg
     * @apiGroup source
     * @apiParam {file} file 文件类型
     * @apiParam {number} id 借款人ID
     * @apiParam {number} navId 导航栏Id
     */
    @RequestMapping(value = "/listNavigation")
    public JsonResponse listNavigation(@RequestParam Integer lenderId, @RequestParam(defaultValue = "0") Integer type) {
        if (CommonUtil.checkParam(lenderId, type)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(sourceService.listNavigation(lenderId, type));
    }

    @RequestMapping(value = "/addNavigation", method = RequestMethod.POST)
    public JsonResponse addNavigation(@ModelAttribute SourceNavigation sourceNavigation) {
        if (CommonUtil.checkParam(sourceNavigation, sourceNavigation.getType(), sourceNavigation.getPid())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = sourceService.addNavigation(sourceNavigation);
        if(CommonUtil.checkResult(result)){
            return JsonResponseTool.failure("添加失败");
        }else{
            return JsonResponseTool.success(result);
        }
    }

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

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public JsonResponse get(@RequestParam Integer lenderId, @RequestParam Integer navId) {
        if (CommonUtil.checkParam(navId, lenderId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        SourceInfoDTO sourceInfoDTO = sourceService.getSource(navId, lenderId);
        if(sourceInfoDTO == null){
            return JsonResponseTool.failure("获取失败");
        }else{
            return JsonResponseTool.success(sourceInfoDTO);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResponse get(@ModelAttribute SourceInfoDTO sourceInfoDTO) {
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
