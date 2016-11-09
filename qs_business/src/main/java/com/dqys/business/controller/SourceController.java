package com.dqys.business.controller;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.common.NavUnviewDTO;
import com.dqys.business.service.dto.common.SourceInfoDTO;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yvan on 16/8/1.
 */
@RestController
@RequestMapping(value = "/source")
public class SourceController extends BaseApiContorller {

    @Autowired
    private SourceService sourceService;

    /**
     * 分类列表
     */
    @RequestMapping(value = "/listNavigation")
    public JsonResponse listNavigation(Integer lenderId, Integer estatesId, @RequestParam(defaultValue = "0") Integer type) {
        if (CommonUtil.checkParam(type)) {
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
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getNavId(),
                sourceInfoDTO.getCode())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((sourceInfoDTO.getLenderId() == null && sourceInfoDTO.getEstatesId() == null) || (sourceInfoDTO.getLenderId() != null && sourceInfoDTO.getEstatesId() != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        Integer userId = UserSession.getCurrent().getUserId();
        if(!sourceService.hasSourceAuth(sourceInfoDTO.getNavId(),sourceInfoDTO.getLenderId(),sourceInfoDTO.getEstatesId(),userId)){
            return JsonResponseTool.authFailure("对不起,您不具备增加该导航下资产源的权限");
        }
        return sourceService.addSource(sourceInfoDTO);
    }

    /**
     * 获取资源信息
     *
     * @param lenderId
     * @param estatesId
     * @param navId
     * @return
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
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getNavId(),
                sourceInfoDTO.getCode())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((sourceInfoDTO.getLenderId() == null && sourceInfoDTO.getEstatesId() == null) || (sourceInfoDTO.getLenderId() != null && sourceInfoDTO.getEstatesId() != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        return sourceService.updateSource(sourceInfoDTO);
    }

    /**
     * 获取资料实勘最新权限
     *
     * @param dto 没有被勾选的对象
     * @return
     */
    @RequestMapping(value = "/getNewNavAll", method = RequestMethod.POST)
    public JsonResponse getNewNavAll(@ModelAttribute NavUnviewDTO dto) {

        SelectDtoMap selectDtoMap = sourceService.getNewNavALL(dto);
        if (selectDtoMap == null) {
            JsonResponseTool.failure("最新实勘权限获取异常");
        }
        return JsonResponseTool.success(selectDtoMap);
    }


}
