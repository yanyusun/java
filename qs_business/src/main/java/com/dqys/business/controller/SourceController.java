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
     * @api {post} source/listNavigation 分类列表
     * @apiParam {int} [lenderId] 借款人id（用于资产包或借款人的资料实堪）
     * @apiParam {int} [estatesId] 资产源id（用于资产源的资料实堪）
     * @apiParam {int} type 资源类型:实勘1|证件合同0(默认)|2根进
     * @apiSampleRequest source/listNavigation
     * @apiGroup source
     * @apiName source/listNavigation
     */
    @RequestMapping(value = "/listNavigation")
    public JsonResponse listNavigation(Integer lenderId, Integer estatesId, @RequestParam(defaultValue = "0") Integer type) {
        if (CommonUtil.checkParam(lenderId, type)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((lenderId == null && estatesId == null) || (lenderId != null && estatesId != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        return JsonResponseTool.success(sourceService.listNavigation(lenderId, estatesId, type));
    }

    /**
     * 增加分类
     *
     * @param sourceNavigation
     * @return
     */
    @RequestMapping(value = "/addNavigation", method = RequestMethod.POST)
    public JsonResponse addNavigation(@ModelAttribute SourceNavigation sourceNavigation) {
        if (CommonUtil.checkParam(sourceNavigation, sourceNavigation.getType(), sourceNavigation.getPid(),
                sourceNavigation.getName())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return sourceService.addNavigation(sourceNavigation);
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteNavigation")
    public JsonResponse deleteNavigation(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return sourceService.deleteNavigation(id);
    }

    /**
     * 增加资源
     *
     * @param sourceInfoDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(@ModelAttribute SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getLenderId(), sourceInfoDTO.getNavId(),
                sourceInfoDTO.getCode())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return sourceService.addSource(sourceInfoDTO);
    }

    /**
     * @api {post} source/get 获取资源信息
     * @apiParam {int} [lenderId] 借款人id（用于资产包或借款人的资料实堪）
     * @apiParam {int} [estatesId] 资产源id（用于资产源的资料实堪）
     * @apiParam {int} navId 分类id
     * @apiSampleRequest source/get
     * @apiGroup source
     * @apiName source/get
     * @apiSuccessExample {json} Data-Response:
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(Integer lenderId, Integer estatesId, @RequestParam Integer navId) {
        if (CommonUtil.checkParam(navId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((lenderId == null && estatesId == null) || (lenderId != null && estatesId != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        return JsonResponseTool.success(sourceService.getSource(navId, lenderId, estatesId));
    }

    /**
     * 修改资源信息
     *
     * @param sourceInfoDTO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResponse update(@ModelAttribute SourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getLenderId(), sourceInfoDTO.getNavId(),
                sourceInfoDTO.getCode())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return sourceService.updateSource(sourceInfoDTO);
    }


}
