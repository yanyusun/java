package com.dqys.business.controller;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.business.service.constant.asset.LenderTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.LenderInsertDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.asset.LenderServiceUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
     * 获取初始化数据
     * @return
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getInit() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("lenderType", LenderTypeEnum.list());
        resultMap.put("companyType", CompanyTypeEnum.list());
        Map<Integer, String> accountType = new HashMap<>();
        accountType.put(Integer.valueOf(
                SysPropertyTool.getProperty(
                        SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_INTERMEDIARY)
                        .getPropertyValue()), "市场处置"); // 中介
        accountType.put(Integer.valueOf(
                SysPropertyTool.getProperty(
                        SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                        .getPropertyValue()), "司法化解"); // 律所
        accountType.put(Integer.valueOf(
                SysPropertyTool.getProperty(
                        SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                        .getPropertyValue()), "常规催收"); // 催收
        accountType.put(0, "常规催收司法化解同时进行");
        resultMap.put("accountType", accountType);

        return JsonResponseTool.success(resultMap);
    }


    /**
     * 获取借款人列表
     * @param nav
     * @param lenderListQuery
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@RequestParam(required = true) Integer nav, @ModelAttribute LenderListQuery lenderListQuery) {
        if (ObjectTabEnum.getObjectTabEnum(nav) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.queryList(lenderListQuery, nav);
    }

    /**
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute LenderInsertDTO lenderInsertDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(lenderInsertDTO, lenderInsertDTO.getLenderDTO(),
                lenderInsertDTO.getContactDTOList().get(0))) {
            return JsonResponseTool.paramErr("参数错误");
        }

        String data = LenderServiceUtils.checkData(lenderInsertDTO.getLenderDTO());
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        data = LenderServiceUtils.checkData(lenderInsertDTO.getContactDTOList());
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        return lenderService.add_tx(lenderInsertDTO.getContactDTOList(), lenderInsertDTO.getLenderDTO());
    }

    /**
     * 删除借款人
     * @param id
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse deleteLenderRelation(@PathVariable Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.delete_tx(id);
    }

    /**
     * 修改借款人
     * @param lenderInsertDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResponse updateLenderRelation(@ModelAttribute LenderInsertDTO lenderInsertDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(

                lenderInsertDTO, lenderInsertDTO.getLenderDTO(), lenderInsertDTO.getLenderDTO().getId(),
                lenderInsertDTO.getLenderDTO().getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String data = LenderServiceUtils.checkData(lenderInsertDTO.getLenderDTO());
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        data = LenderServiceUtils.checkData(lenderInsertDTO.getContactDTOList());
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        return lenderService.update_tx(lenderInsertDTO.getContactDTOList(), lenderInsertDTO.getLenderDTO());
    }

    /**
     * 获取借款人信息
     * @param id
     * @return
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
     * 获取联系人所有相关信息
     * @param id
     * @return
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
