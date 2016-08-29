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
     * @api {get} http://{url}/source/listNavigation 分类列表
     * @apiName listNavigation
     * @apiGroup source
     * @apiParam {number} lenderId 借款人ID
     * @apiParam {number} type 实勘1|证件合同0(默认)
     * @apiSuccess {object} data 参考git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/SourceNavigation.java
     */
    @RequestMapping(value = "/listNavigation")
    public JsonResponse listNavigation(@RequestParam Integer lenderId, @RequestParam(defaultValue = "0") Integer type) {
        if (CommonUtil.checkParam(lenderId, type)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(sourceService.listNavigation(lenderId, type));
    }

    /**
     * @api {post} http://{url}/source/addNavigation 增加分类
     * @apiName addNavigation
     * @apiGroup source
     * @apiParam {object} sourceNavigation 同listNavigation
     */
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

    /**
     * @api {get} http://{url}/source/deleteNavigation 删除分类
     * @apiName deleteNavigation
     * @apiGroup source
     * @apiParam {number} id 分类ID
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
     * @api {post} http://{url}/source/add 增加资源
     * @apiName add
     * @apiGroup source
     * @apiParam {object} sourceInfoDTO git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceInfoDTO.java
     * @apiSuccess {number} data 新增的ID
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
     * @api {get} http://{url}/source/get 获取资源信息
     * @apiName get
     * @apiGroup source
     * @apiParam {number} lenderId 借款人Id
     * @apiParam {number} navId 分类ID
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(@RequestParam Integer lenderId, @RequestParam Integer navId) {
        if (CommonUtil.checkParam(navId, lenderId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(sourceService.getSource(navId, lenderId));
    }

    /**
     * @api {post} http://{url}/source/update 修改资源信息
     * @apiName update
     * @apiGroup source
     * @apiParam {object} sourceInfoDTO 参考新增资源信息
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
