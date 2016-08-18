package com.dqys.business.controller;

import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.IouService;
import com.dqys.business.service.service.LenderService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yvan on 16/7/11.
 */
@Controller
@RequestMapping(value = "/iou")
public class IouController {

    @Autowired
    private IouService iouService;

    /**
     * @api {get} http://{url}/iou/delete 删除借据
     * @apiName delete
     * @apiGroup iou
     * @apiParam {number} id 借据ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam Integer id) throws BusinessLogException{
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.delete_tx(id);
    }

    /**
     * @api {post} http://{url}/iou/add 新增借据
     * @apiName add
     * @apiGroup iou
     * @apiUse Iou
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute IouDTO iouDTO) throws BusinessLogException{
        if (CommonUtil.checkParam(iouDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.add_tx(iouDTO);
    }

    /**
     * @api {post} http://{url}/iou/add 新增借据
     * @apiName add
     * @apiGroup iou
     * @apiUse Iou
     */
    @RequestMapping(value = "/listAdd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute List<IouDTO> iouDTO) throws BusinessLogException{
        if (CommonUtil.checkParam(iouDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.listAdd(iouDTO);
    }

    /**
     * @api {post} http://{url}/iou/update 修改借据信息
     * @apiName update
     * @apiGroup iou
     * @apiUse Iou
     * @apiSuccess {number} data 修改后的ID
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute IouDTO iouDTO) throws BusinessLogException{
        if (CommonUtil.checkParam(iouDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.update_tx(iouDTO);
    }

    /**
     * @api {post} http://{url}/iou/get 获取借据信息
     * @apiName get
     * @apiGroup iou
     * @apiParam {number} id 借据ID
     * @apiUse IouDTO
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.get(id);
    }

    /**
     * @api {post} http://{url}/iou/listIou 获取借款人的借据信息
     * @apiName listIou
     * @apiGroup iou
     * @apiParam {number} id 借款人ID
     * @apiSuccess {IouDTO} data 借据信息
     * @apiUse IouDTO
     */
    @RequestMapping(value = "/listIou")
    @ResponseBody
    public JsonResponse listIou(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.listIouByLenderId(id);
    }


}
