package com.dqys.business.controller;

import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.core.utils.CommonUtil;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.service.constant.ContactTypeEnum;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
     *
     *
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getInit(){


        return null;
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
        return CommonUtil.responseBack(lenderService.deleteLenderRelation(id));
    }

    /**
     * @api {get} http://{url}/lender/add 新增借款人
     * @apiName add
     * @apiGroup lender
     *
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {} lenderDTO
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResponse addLenderRelation(
            @ModelAttribute List<ContactDTO> contactDTOs,
            @ModelAttribute LenderDTO lenderDTO) {
        if (CommonUtil.checkParam(contactDTOs, lenderDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // 增加借款人以及相关联系人的身份信息
        Integer lenderId = null;
        HashMap<Integer, String> relation = new HashMap<>();
        for (ContactDTO contactDTO : contactDTOs) {
            ContactTypeEnum contactTypeEnum = ContactTypeEnum.getContactTypeEnum(contactDTO.getType());
            if (contactTypeEnum == null) {
                return JsonResponseTool.paramErr("联系人类型参数错误");
            }
            Integer id = lenderService.addLenderInfo(AssetServiceUtils.toContactInfo(contactDTO));
            if (id > 0) {
                if (contactTypeEnum.getValue().equals(contactTypeEnum.LENDER.getValue())) {
                    lenderId = id;
                }
                if (relation.get(contactTypeEnum.getValue()) != null) {
                    relation.put(contactTypeEnum.getValue(), relation.get(contactTypeEnum.getValue()) + "," + id);
                } else {
                    relation.put(contactTypeEnum.getValue(), id.toString());
                }
            }
        }
        if (lenderId == null) {
            JsonResponseTool.failure("增加借款人基础信息失败");
        }

        return CommonUtil.responseBack(lenderService.addLenderRelation(AssetServiceUtils.toLenderInfo(lenderDTO)));
    }

    /**
     * 修改借款人
     *
     * @param contactDTOs
     * @param lenderDTO
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResponse updateLenderRelation(@ModelAttribute List<ContactDTO> contactDTOs,
                                             @ModelAttribute LenderDTO lenderDTO) {
        if (CommonUtil.checkParam(
                contactDTOs, lenderDTO, lenderDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }

        LenderInfo lenderInfo1 = lenderService.getLenderRelation(lenderDTO.getId());
        if (lenderInfo1 == null) {
            return JsonResponseTool.paramErr("参数错误,不存在该借款人信息");
        }

        HashMap<Integer, String> relation = new HashMap<>();
        for (ContactDTO contactDTO : contactDTOs) {
            ContactTypeEnum contactTypeEnum = ContactTypeEnum.getContactTypeEnum(contactDTO.getType());
            if (contactTypeEnum == null) {
                return JsonResponseTool.paramErr("联系人类型参数错误");
            }
            Integer id = contactDTO.getId();
            if (id == null) {
                // 新增联系人
                id = lenderService.addLenderInfo(AssetServiceUtils.toContactInfo(contactDTO));
            } else {
                // 修改联系人
                ContactInfo contactInfo1 = lenderService.getLenderInfo(id);
                if (!contactInfo1.toString().equals(contactDTO.toString())) {
                    lenderService.updateLenderInfo(AssetServiceUtils.toContactInfo(contactDTO));
                }
            }
            if (id > 0) {
                if (relation.get(contactTypeEnum.getValue()) != null) {
                    relation.put(contactTypeEnum.getValue(), relation.get(contactTypeEnum.getValue()) + "," + id);
                } else {
                    relation.put(contactTypeEnum.getValue(), id.toString());
                }
            }
        }

        if (lenderInfo1.toString().equals(lenderDTO.toString())) {
            return JsonResponseTool.success(lenderDTO.getId());
        } else {
            Integer id = lenderService.addLenderRelation(AssetServiceUtils.toLenderInfo(lenderDTO));
            if (id > 0) {
                return JsonResponseTool.success(id);
            } else {
                return JsonResponseTool.failure("修改失败");
            }
        }
    }

    /**
     * @api {get} http://{url}/lender/get 获取借款人信息
     * @apiName get
     * @apiGroup lender
     * @apiParam {numbder} id 借款人ID
     * @apiSuccess {LenderDTO} data 联系人信息
     * @apiUse lenderDTO
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse getLenderRelation(@RequestParam(required = true) Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        LenderDTO lenderDTO = AssetServiceUtils.toLenderDTO(lenderService.getLenderRelation(id));
        return JsonResponseTool.success(lenderDTO);
    }

    /**
     * 删除抵押物
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletePawn")
    @ResponseBody
    public JsonResponse deletePawn(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(lenderService.deletePawn(id));
    }

    /**
     * 增加抵押物信息
     *
     * @param pawnInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/addPawn")
    @ResponseBody
    public JsonResponse addPawn(@ModelAttribute PawnInfo pawnInfo, @PathVariable Integer id) {
        if (CommonUtil.checkParam(pawnInfo, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        ContactInfo contactInfo = lenderService.getLenderInfo(id);
        if (contactInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        pawnInfo.setLenderId(id);
        return CommonUtil.responseBack(lenderService.addPawn(pawnInfo));
    }

    /**
     * 修改抵押物信息
     *
     * @param pawnInfo
     * @return
     */
    @RequestMapping(value = "/updatePawn")
    @ResponseBody
    public JsonResponse updatePawn(@ModelAttribute PawnInfo pawnInfo) {
        if (CommonUtil.checkParam(pawnInfo)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = lenderService.updatePawn(pawnInfo);
        if (id == null) {
            return JsonResponseTool.failure("修改失败");
        } else {
            return JsonResponseTool.success(id);
        }
    }

    /**
     * 获取抵押物信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPawn")
    @ResponseBody
    public JsonResponse updatePawn(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(lenderService.getPawn(id));
    }



}
