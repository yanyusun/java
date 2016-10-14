package com.dqys.business.controller;

import com.dqys.business.service.dto.cases.*;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.cases.CaseService;
import com.dqys.business.service.utils.cases.CaseServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Yvan on 16/7/26.
 */
@RestController
@RequestMapping(value = "/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    /**
     * 创建案件信息
     *
     * @param caseDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(@ModelAttribute CaseDTO caseDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getPawnId(), caseDTO.getDefendant(), caseDTO.getPlaintiff(),
                caseDTO.getSpouse(), caseDTO.getMortgagor(), caseDTO.getMortgageTime(), caseDTO.getGuarantor(),
                caseDTO.getEvaluateExcellent(), caseDTO.getEvaluateLevel(), caseDTO.getLawsuitAccrual(),
                caseDTO.getLawsuitAmount(), caseDTO.getLawsuitCorpus(), caseDTO.getAttachmentStatus(),
                caseDTO.getCourtDTOList(), caseDTO.getIouIds())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return caseService.add_tx(caseDTO);
    }


    /**
     * 批量创建案件信息
     *
     * @param caseDTOList
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/listAdd", method = RequestMethod.POST)
    public JsonResponse listAdd(@ModelAttribute CaseDTOList caseDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTOList) || caseDTOList.getCaseDTOList().size() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return caseService.listAdd(caseDTOList);
    }


    /**
     * 根据案件Id获取案件详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(Integer id){
        if(id == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success(caseService.get(id));
    }

    /**
     * 修改案件信息
     *
     * @param caseDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResponse update(@ModelAttribute CaseDTO caseDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getPawnId(), caseDTO.getDefendant(), caseDTO.getPlaintiff(),
                caseDTO.getSpouse(), caseDTO.getMortgagor(), caseDTO.getMortgageTime(), caseDTO.getGuarantor(),
                caseDTO.getEvaluateExcellent(), caseDTO.getEvaluateLevel(), caseDTO.getLawsuitAccrual(),
                caseDTO.getLawsuitAmount(), caseDTO.getLawsuitCorpus(), caseDTO.getAttachmentStatus(),
                caseDTO.getCourtDTOList(), caseDTO.getIouIds(), caseDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return caseService.update_tx(caseDTO);
    }

    /**
     * 根据借款人查询案件信息
     *
     * @param id
     * @param index
     * @return
     */
    @RequestMapping(value = "/list")
    public JsonResponse list(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer index) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        CaseDTO caseDTO = caseService.getByLender(id, index);
        Integer count = caseService.getCountByLender(id);
        map.put("total", count);
        map.put("data", caseDTO);
        return JsonResponseTool.success(map);
    }

    /**
     * 根据案件查询子案件
     *
     * @param id
     * @param index
     * @return
     */
    @RequestMapping(value = "/listCase")
    public JsonResponse listCase(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer index) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        CaseDTO caseDTO = caseService.getByCase(id, index);
        Integer count = caseService.getCountByCase(id);
        map.put("total", count);
        map.put("data", caseDTO);
        return JsonResponseTool.success(map);
    }

    /**
     * 拆分案件
     *
     * @param id 案件id
     * @param ids 拆分之后的关联借据集合
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/divide")
    public JsonResponse divide(@RequestParam Integer id, @RequestParam String ids) throws BusinessLogException {
        if (CommonUtil.checkParam(id, ids) && ids.length() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        CaseDTO caseDTO = caseService.get(id);
        if (CommonUtil.checkParam(caseDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String[] pIouIds = caseDTO.getIouIds().split(",");
        String[] cIds = ids.split(",");
        Boolean flag = true; // 判断参数是否全部属于该借据下
        for (String i : cIds) {
            boolean flag1 = true; // 判断该案件的借据中是否存在该Id
            for (String j : pIouIds) {
                if (i.equals(j)) {
                    flag1 = false;
                    break;
                }
            }
            if (flag1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            caseDTO.setIouIds(ids);
        } else {
            return JsonResponseTool.paramErr("参数错误");
        }
        caseDTO.setType("1"); // 子类型
        caseDTO.setpId(id); // 父级类型ID
        return caseService.add_tx(caseDTO);
    }

    /**
     * 根据借款人信息查询案件信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/listByLender")
    public JsonResponse listByLender(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(caseService.listByLender(id));
    }

    /**
     * 根据母案件查询子案件
     * @param id
     * @return
     */
    @RequestMapping(value = "/listByCase")
    public JsonResponse listByCase(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        if(caseService.get(id) == null){
            return JsonResponseTool.paramErr("案件不存在");
        }
        return JsonResponseTool.success(caseService.listByCase(id));
    }

    /**
     * 修改案件的基础信息
     * @param caseBaseDTO
     * @return
     */
    @RequestMapping(value = "/updateCaseBase")
    public JsonResponse updateCaseBase(CaseBaseDTO caseBaseDTO){
        return caseService.updateCaseBase(caseBaseDTO);
    }

    /**
     * 修改案件的查封保全信息
     * @param caseAttachmentDTO
     * @return
     */
    @RequestMapping(value = "/updateCaseAttachment")
    public JsonResponse updateCaseAttachment(CaseAttachmentDTO caseAttachmentDTO){
        return caseService.updateCaseAttachment(caseAttachmentDTO);
    }

    /**
     * 修改案件的诉讼信息
     * @param caseLawsuitDTO
     * @return
     */
    @RequestMapping(value = "/updateCaseLawsuit")
    public JsonResponse updateCaseLawsuit(CaseLawsuitDTO caseLawsuitDTO){
        return caseService.updateCaseLawsuit(caseLawsuitDTO);
    }

    /**
     * 修改案件的备注
     * @param id
     * @param memo
     * @return
     */
    @RequestMapping(value = "/updateCaseMemo")
    public JsonResponse updateCaseMemo(Integer id, String memo){
        return caseService.updateCaseMemo(id, memo);
    }

    /**
     * 修改案件的相关联法院
     * @param caseCourtsDTO
     * @return
     */
    @RequestMapping(value = "/updateCaseCourt")
    public JsonResponse updateCaseCourt(CaseCourtsDTO caseCourtsDTO){
        if(CommonUtil.checkParam(caseCourtsDTO, caseCourtsDTO.getId(), caseCourtsDTO.getCaseCourtDTOList())){
            return JsonResponseTool.paramErr("参数错误");
        }
        for (CaseCourtDTO caseCourtDTO : caseCourtsDTO.getCaseCourtDTOList()) {
            String error = CaseServiceUtils.checkData(caseCourtDTO);
            if(error != null){
                return JsonResponseTool.paramErr(error);
            }
        }
        return caseService.updateCaseCourt(caseCourtsDTO.getId(), caseCourtsDTO.getCaseCourtDTOList());
    }



}
