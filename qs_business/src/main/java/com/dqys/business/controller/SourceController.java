package com.dqys.business.controller;

import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.common.*;
import com.dqys.business.service.dto.sourceAuth.SelectDtoMap;
import com.dqys.business.service.exception.bean.SourceEditException;
import com.dqys.business.service.service.common.SourceService;
import com.dqys.business.service.utils.common.SourceServiceUtls;
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
     * 分类列表
     */
    @RequestMapping(value = "c/listNavigationPerson")
    public JsonResponse listNavigationPerson(Integer lenderId, Integer estatesId, @RequestParam(defaultValue = "0") Integer type,@RequestParam(defaultValue = "0") Integer pid) {
        if (CommonUtil.checkParam(type)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((lenderId == null && estatesId == null) || (lenderId != null && estatesId != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        return JsonResponseTool.success(sourceService.listNavigationPerson(lenderId, estatesId, type,pid));
    }

    /**
     * 分类列表
     */
    @RequestMapping(value = "c/listNavigationSys")
    public JsonResponse listNavigationCommon(Integer lenderId, Integer estatesId, @RequestParam(defaultValue = "0") Integer type,@RequestParam(defaultValue = "0") Integer pid) {
        if (CommonUtil.checkParam(type)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((lenderId == null && estatesId == null) || (lenderId != null && estatesId != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        return JsonResponseTool.success(sourceService.listNavigationCommon(lenderId, estatesId, type,pid));
    }


    /**
     * 增加分类
     *
     * @param sourceNavigation
     * @return
     */
    @RequestMapping(value = {"addNavigation", "c/addNavigation"}, method = RequestMethod.POST)
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
        if (!sourceService.hasSourceAuth(sourceInfoDTO.getNavId(), sourceInfoDTO.getLenderId(), sourceInfoDTO.getEstatesId(), userId)) {
            return JsonResponseTool.authFailure("对不起,您不具备增加该导航下资产源的权限");
        }
        return sourceService.addSource(sourceInfoDTO);
    }

    /**
     * 增加资源(为了c端b端保持一致,新建文件的同时新建文件夹)
     *
     * @param sourceInfoDTO
     * @return
     */
    @RequestMapping(value = "c/add", method = RequestMethod.POST)
    public JsonResponse uploadFile(@ModelAttribute CSourceInfoDTO sourceInfoDTO) {
        if (CommonUtil.checkParam(sourceInfoDTO, sourceInfoDTO.getFilePathName(), sourceInfoDTO.getFileShowName(), sourceInfoDTO.getType())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if ((sourceInfoDTO.getLenderId() == null && sourceInfoDTO.getEstatesId() == null) || (sourceInfoDTO.getLenderId() != null && sourceInfoDTO.getEstatesId() != null)) {
            return JsonResponseTool.paramErr("资产源或借款人参数错误");
        }
        Integer userId = UserSession.getCurrent().getUserId();
        if (sourceInfoDTO.getpNavId() != null) {
            if (!sourceService.hasSourceAuth(sourceInfoDTO.getpNavId(), sourceInfoDTO.getLenderId(), sourceInfoDTO.getEstatesId(), userId)) {
                return JsonResponseTool.authFailure("对不起,您不具备增加该导航下资产源的权限");
            }
        }
        return sourceService.c_addSource(sourceInfoDTO);
    }

    /**
     * 获取资源信息
     *
     * @param lenderId
     * @param estatesId
     * @param navId
     * @return
     */
    @RequestMapping(value = {"get", "c/get"})
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
    public JsonResponse resetAndGetNewALL(@ModelAttribute NavUnviewDTO dto) {

        SelectDtoMap selectDtoMap = sourceService.resetAndGetNewALL(dto);
        if (selectDtoMap == null) {
            JsonResponseTool.failure("最新实勘权限获取异常");
        }
        return JsonResponseTool.success(selectDtoMap);
    }

    /**
     * @api {post} source/getSourceType 获取资料实堪分类操作的是否有权限
     * @apiParam {int} navId 分类id
     * @apiParam {int} objectId 对象id
     * @apiParam {int} objectType 对象类型
     * @apiSampleRequest source/getSourceType
     * @apiGroup source
     * @apiName source/getSourceType
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": [
     * {
     * "number": 145,
     * "name": "添加标签",
     * "url": ""
     * },
     * {
     * "number": 146,
     * "name": "重命名分类",
     * "url": ""
     * },
     * {
     * "number": 147,
     * "name": "删除分类",
     * "url": ""
     * }
     * ]
     * }
     */
    @RequestMapping(value = "/getSourceType", method = RequestMethod.POST)
    public JsonResponse getSourceType(Integer navId, @RequestParam Integer objectId, @RequestParam Integer objectType) {
        return sourceService.getSourceType(navId, objectId, objectType);
    }


    /**
     * @api {POST} http://{url}/source/c/rename 增加跟进信息,状态为未发送
     * @apiName c_rename
     * @apiGroup source
     * @apiUse SourceEditDto
     */
    @RequestMapping(value = {"rename", "c/rename"}, method = RequestMethod.POST)
    public JsonResponse renameSource(SourceEditDto sourceEditDto) throws SourceEditException {
        sourceService.renameSource(sourceEditDto);
        return JsonResponseTool.success(sourceEditDto);
    }

    /**
     * @api {DELETE} http://{url}/source/c/del 增加跟进信息,状态为未发送
     * @apiName c_del
     * @apiGroup source
     * @apiUse SourceDelDTO
     */
    @RequestMapping(value = "c/del", method = RequestMethod.POST)
    public JsonResponse delSource(SourceDelDTO sourceDelDTO) throws SourceEditException {
        sourceService.delSource(sourceDelDTO);
        return JsonResponseTool.success(sourceDelDTO);
    }


    /**
     * @api {DELETE} http://{url}/source/c/source 增加跟进信息,状态为未发送
     * @apiName c_del
     * @apiGroup source
     * @apiUse SourceDelDTO
     */
    //// TODO: 17-1-17 权限控制 
    @RequestMapping(value = "c/source", method = RequestMethod.GET)
    public JsonResponse getSource(Integer id) throws SourceEditException {
        return JsonResponseTool.success(SourceServiceUtls.toCSourceNavDTODetail(sourceService.getDetail(id)));
    }

}
