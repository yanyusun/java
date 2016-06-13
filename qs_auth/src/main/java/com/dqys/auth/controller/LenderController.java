package com.dqys.auth.controller;

import com.dqys.auth.controller.util.CommonUtil;
import com.dqys.auth.orm.pojo.asset.IOUInfo;
import com.dqys.auth.orm.pojo.asset.LenderInfo;
import com.dqys.auth.orm.pojo.asset.LenderRelation;
import com.dqys.auth.orm.pojo.asset.PawnInfo;
import com.dqys.auth.service.constant.asset.LenderTypeEnum;
import com.dqys.auth.service.facade.asset.LenderService;
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
     * @param lenderInfos
     * @param lenderRelation
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResponse addLenderRelation(
            @ModelAttribute List<LenderInfo> lenderInfos,
            @ModelAttribute LenderRelation lenderRelation) {
        if (CommonUtil.checkParam(lenderInfos, lenderRelation)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // 增加借款人以及相关联系人的身份信息
        Integer lenderId = null;
        HashMap<Integer, String> relation = new HashMap<>();
        for (LenderInfo lenderInfo : lenderInfos) {
            LenderTypeEnum lenderTypeEnum = LenderTypeEnum.getLenderTypeInfo(lenderInfo.getType());
            if (lenderTypeEnum == null) {
                return JsonResponseTool.paramErr("联系人类型参数错误");
            }
            Integer id = lenderService.addLenderInfo(lenderInfo);
            if (id > 0) {
                if (lenderTypeEnum.getValue().equals(LenderTypeEnum.LENDER.getValue())) {
                    lenderId = id;
                }
                if (relation.get(lenderTypeEnum.getValue()) != null) {
                    relation.put(lenderTypeEnum.getValue(), relation.get(lenderTypeEnum.getValue()) + "," + id);
                } else {
                    relation.put(lenderTypeEnum.getValue(), id.toString());
                }
            }
        }
        if (lenderId == null) {
            JsonResponseTool.failure("增加借款人基础信息失败");
        }

        // 增加关系映射
        lenderRelation.setLenderId(lenderId);
        lenderRelation.setSlenderIds(relation.get(LenderTypeEnum.LENDER_WITH.getValue())); // 共同合伙人
        lenderRelation.setOtherIds(relation.get(LenderTypeEnum.OTHER.getValue())); // 其他
        lenderRelation.setGuaranteeIds(relation.get(LenderTypeEnum.GUALANTEE.getValue())); // 借贷方
        lenderRelation.setBankManagerIds(relation.get(LenderTypeEnum.BANK_MANAGER.getValue())); // 银行经理

        return CommonUtil.responseBack(lenderService.addLenderRelation(lenderRelation));
    }

    /**
     * 修改借款人
     *
     * @param lenderInfos
     * @param lenderRelation
     * @return
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResponse updateLenderRelation(@ModelAttribute List<LenderInfo> lenderInfos,
                                             @ModelAttribute LenderRelation lenderRelation) {
        if (CommonUtil.checkParam(
                lenderInfos, lenderRelation, lenderRelation.getId(),
                lenderRelation.getLenderId())) {
            return JsonResponseTool.paramErr("参数错误");
        }

        LenderRelation lenderRelation1 = lenderService.getLenderRelation(lenderRelation.getId());
        if (lenderRelation1 == null) {
            return JsonResponseTool.paramErr("参数错误,不存在该借款人信息");
        }

        HashMap<Integer, String> relation = new HashMap<>();
        for (LenderInfo lenderInfo : lenderInfos) {
            LenderTypeEnum lenderTypeEnum = LenderTypeEnum.getLenderTypeInfo(lenderInfo.getType());
            if (lenderTypeEnum == null) {
                return JsonResponseTool.paramErr("联系人类型参数错误");
            }
            Integer id = lenderInfo.getId();
            if (id == null) {
                // 新增联系人
                id = lenderService.addLenderInfo(lenderInfo);
            } else {
                // 修改联系人
                LenderInfo lenderInfo1 = lenderService.getLenderInfo(id);
                if (!lenderInfo1.toString().equals(lenderInfo.toString())) {
                    lenderService.updateLenderInfo(lenderInfo);
                }
            }
            if (id > 0) {
                if (relation.get(lenderTypeEnum.getValue()) != null) {
                    relation.put(lenderTypeEnum.getValue(), relation.get(lenderTypeEnum.getValue()) + "," + id);
                } else {
                    relation.put(lenderTypeEnum.getValue(), id.toString());
                }
            }
        }

        if (lenderRelation1.toString().equals(lenderRelation.toString())
                && lenderRelation.getLenderId().equals(relation.get(LenderTypeEnum.LENDER.getValue()))
                && lenderRelation.getLenderId().equals(relation.get(LenderTypeEnum.LENDER.getValue()))
                && lenderRelation.getLenderId().equals(relation.get(LenderTypeEnum.LENDER.getValue()))
                && lenderRelation.getLenderId().equals(relation.get(LenderTypeEnum.LENDER.getValue()))
                && lenderRelation.getSlenderIds().equals(relation.get(LenderTypeEnum.LENDER_WITH.getValue()))) {
            return JsonResponseTool.success(lenderRelation.getId());
        } else {
            lenderRelation.setLenderId(Integer.valueOf(relation.get(LenderTypeEnum.LENDER.getValue())));
            lenderRelation.setSlenderIds(relation.get(LenderTypeEnum.LENDER_WITH.getValue())); // 共同合伙人
            lenderRelation.setOtherIds(relation.get(LenderTypeEnum.OTHER.getValue())); // 其他
            lenderRelation.setGuaranteeIds(relation.get(LenderTypeEnum.GUALANTEE.getValue())); // 借贷方
            lenderRelation.setBankManagerIds(relation.get(LenderTypeEnum.BANK_MANAGER.getValue())); // 银行经理

            Integer id = lenderService.addLenderRelation(lenderRelation);
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


        return JsonResponseTool.success("");
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
        LenderInfo lenderInfo = lenderService.getLenderInfo(id);
        if (lenderInfo == null) {
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
     * @param iouInfo
     * @param id
     * @return
     */
    @RequestMapping(value = "/addPawn")
    @ResponseBody
    public JsonResponse addIou(@ModelAttribute IOUInfo iouInfo, @PathVariable Integer id) {
        if (CommonUtil.checkParam(iouInfo, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        LenderInfo lenderInfo = lenderService.getLenderInfo(id);
        if (lenderInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        iouInfo.setLenderId(id);
        return CommonUtil.responseBack(lenderService.addIOUInfo(iouInfo,lenderInfo.getName()));
    }

    /**
     * 修改借据信息
     *
     * @param iouInfo
     * @return
     */
    @RequestMapping(value = "/updateIou")
    @ResponseBody
    public JsonResponse updateIou(@ModelAttribute IOUInfo iouInfo) {
        if (CommonUtil.checkParam(iouInfo)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = lenderService.updateIOUInfo(iouInfo);
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
    @RequestMapping(value = "/getIOUInfo")
    @ResponseBody
    public JsonResponse getIou(@PathVariable Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(lenderService.getIOUInfo(id));
    }

}
