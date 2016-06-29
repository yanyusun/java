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
import com.dqys.business.service.utils.asset.AssetControllerUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 删除借款人
     *
     * @param id
     * @return
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
     * 新增借款人
     *
     * @param contactDTOs
     * @param lenderDTO
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
            Integer id = lenderService.addLenderInfo(AssetControllerUtils.toContactInfo(contactDTO));
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

        return CommonUtil.responseBack(lenderService.addLenderRelation(AssetControllerUtils.toLenderInfo(lenderDTO)));
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
                id = lenderService.addLenderInfo(AssetControllerUtils.toContactInfo(contactDTO));
            } else {
                // 修改联系人
                ContactInfo contactInfo1 = lenderService.getLenderInfo(id);
                if (!contactInfo1.toString().equals(contactDTO.toString())) {
                    lenderService.updateLenderInfo(AssetControllerUtils.toContactInfo(contactDTO));
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
            Integer id = lenderService.addLenderRelation(AssetControllerUtils.toLenderInfo(lenderDTO));
            if (id > 0) {
                return JsonResponseTool.success(id);
            } else {
                return JsonResponseTool.failure("修改失败");
            }
        }
    }

    /**
     * 获取借款人
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse getLenderRelation(@PathVariable Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        LenderDTO lenderDTO = AssetControllerUtils.toLenderDTO(lenderService.getLenderRelation(id));
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

    /**
     * 删除借据
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteIou")
    @ResponseBody
    public JsonResponse deleteIou(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(lenderService.deleteIOUInfo(id));
    }

    /**
     * 增加借据信息
     *
     * @param IOUInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/addIou")
    @ResponseBody
    public JsonResponse addIou(@ModelAttribute IOUInfo IOUInfo, @PathVariable Integer id) {
        if (CommonUtil.checkParam(IOUInfo, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        ContactInfo contactInfo = lenderService.getLenderInfo(id);
        if (contactInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        IOUInfo.setLenderId(id);
        return CommonUtil.responseBack(lenderService.addIOUInfo(IOUInfo, contactInfo.getName()));
    }

    /**
     * 修改借据信息
     *
     * @param IOUInfo
     * @return
     */
    @RequestMapping(value = "/updateIou")
    @ResponseBody
    public JsonResponse updateIou(@ModelAttribute IOUInfo IOUInfo) {
        if (CommonUtil.checkParam(IOUInfo)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = lenderService.updateIOUInfo(IOUInfo);
        if (id == null) {
            return JsonResponseTool.failure("修改失败");
        } else {
            return JsonResponseTool.success(id);
        }
    }

    /**
     * 获取借据信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getIouInfo")
    @ResponseBody
    public JsonResponse getIou(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(lenderService.getIOUInfo(id));
    }

}
