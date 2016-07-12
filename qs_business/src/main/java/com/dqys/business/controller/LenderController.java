package com.dqys.business.controller;

import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.constant.asset.LenderTypeEnum;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/12.
 */
@Controller
@RequestMapping(value = "/lender")
public class LenderController {

    @Autowired
    private LenderService lenderService;

    /**
     * @api {get} http://{url}/lender/getInit 获取初始化数据
     * @apiName getInit
     * @apiGroup lender
     * @apiSuccess {SelectonDTO} lenderType 借款人联系人类型
     * @apiUse SelectonDTO
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getInit() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("lenderType", LenderTypeEnum.list());

        return JsonResponseTool.success(resultMap);
    }

    /**
     * @api {get} http://{url}/lender/list 获取借款人列表
     * @apiName list
     * @apiGroup lender
     * @apiUse LenderListQuery
     * @apiSuccess {LenderListDTO} data 借款人列表信息
     * @apiUse LenderListDTO
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute LenderListQuery lenderListQuery) {
        return lenderService.queryList(lenderListQuery);
    }

    /**
     * @api {get} http://{url}/lender/add 新增借款人
     * @apiName add
     * @apiGroup lender
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {ContactDTO} contactDTOs 联系人集合
     * @apiUse LenderDTO
     * @apiUse ContactDTO
     * @apiSuccess {number} data 增加后的数据ID
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResponse add(
            @ModelAttribute List<ContactDTO> contactDTOs,
            @ModelAttribute LenderDTO lenderDTO) {
        if (CommonUtil.checkParam(contactDTOs, lenderDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.add(contactDTOs, lenderDTO);
    }

    /**
     * @api {get} http://{url}/lender/delete 删除借款人
     * @apiName delete
     * @apiGroup lender
     * @apiParam {number} id 借款人Id
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse deleteLenderRelation(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.delete(id);
    }

    /**
     * @api {get} http://{url}/lender/update 修改借款人
     * @apiName update
     * @apiGroup lender
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {ContactDTO} contactDTOs 联系人集合
     * @apiUse LenderDTO
     * @apiUse ContactDTO
     * @apiSuccess {number} data 修改后的数据ID
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResponse updateLenderRelation(@ModelAttribute List<ContactDTO> contactDTOs,
                                             @ModelAttribute LenderDTO lenderDTO) {
        if (CommonUtil.checkParam(
                contactDTOs, lenderDTO, lenderDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.update(contactDTOs, lenderDTO);
    }

    /**
     * @api {get} http://{url}/lender/get 获取借款人信息
     * @apiName get
     * @apiGroup lender
     * @apiParam {numbder} id 借款人ID
     * @apiSuccess {LenderDTO} data 联系人信息
     * @apiUse LenderDTO
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.get(id);
    }

    /**
     * @api {get} http://{url}/lender/getLenderAll 获取联系人所有相关信息
     * @apiName getLenderAll
     * @apiGroup lender
     * @apiParam {numbder} id 借款人ID
     *
     * @apiSuccess {ContactDTO} contactDTOs 相关联系人信息
     * @apiSuccess {LenderDTO} lenderDTO 借款人基础信息
     * @apiSuccess {IouDTO} iouDTOs 借据信息
     * @apiSuccess {PawnDTO} pawnDTOs 抵押物信息
     * @apiUse ContactDTO
     * @apiUse LenderDTO
     * @apiUse IouDTO
     * @apiUse PawnDTO
     */
    @RequestMapping(value = "/getLenderAll")
    @ResponseBody
    public JsonResponse getAllLenderInfo(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.success("参数错误");
        }
        return lenderService.getLenderAll(id);
    }
}
