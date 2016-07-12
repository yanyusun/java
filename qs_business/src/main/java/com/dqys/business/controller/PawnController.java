package com.dqys.business.controller;

import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.service.PawnService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yvan on 16/7/12.
 */
@Controller
@RequestMapping(value = "/pawn")
public class PawnController {

    @Autowired
    private PawnService pawnService;


    /**
     * @api {get} http://{url}/pawn/delete 删除抵押物
     * @apiName delete
     * @apiGroup pawn
     * @apiParam {number} id 抵押物ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.delete(id);
    }

    /**
     * @api {post} http://{url}/pawn/add 增加抵押物信息
     * @apiName add
     * @apiGroup pawn
     * @apiUse pawn
     * @apiSuccess {number} data 增加后的数据ID
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute PawnDTO pawnDTO) {
        if (CommonUtil.checkParam(pawnDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.add(pawnDTO);
    }

    /**
     * @api {post} http://{url}/pawn/update 修改抵押物信息
     * @apiName update
     * @apiGroup pawn
     * @apiUse pawn
     * @apiSuccess {number} data 增加后的数据ID
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute PawnDTO pawnDTO) {
        if (CommonUtil.checkParam(pawnDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.update(pawnDTO);
    }

    /**
     * @api {post} http://{url}/pawn/get 获取抵押物信息
     * @apiName get
     * @apiGroup pawn
     * @apiParam {number} id 抵押物ID
     * @apiSuccess {PawnDTO} data 抵押物信息
     * @apiUse PawnDTO
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse updatePawn(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.get(id);
    }

    /**
     * @api {post} http://{url}/iou/listPawn 获取借款人的抵押物信息
     * @apiName listPawn
     * @apiGroup iou
     * @apiParam {number} id 借款人ID
     * @apiSuccess {PawnDTO} data 抵押物信息
     * @apiUse PawnDTO
     */
    @RequestMapping(value = "/listPawn")
    @ResponseBody
    public JsonResponse listPawn(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.listPawnByLenderId(id);
    }

}
