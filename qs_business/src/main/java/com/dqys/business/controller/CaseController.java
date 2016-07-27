package com.dqys.business.controller;

import com.dqys.business.service.dto.cases.CaseDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.cases.CaseService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by Yvan on 16/7/26.
 */
@Controller
@RequestMapping(value = "/case")
public class CaseController {

    @Autowired
    private CaseService caseService;

    /**
     * @api {post} http://{url}/case/add 创建案件信息
     * @apiName add
     * @apiGroup case
     * @apiUse Case
     * @apiUse CaseCourt
     * @apiSuccess {number} data 新增后的ID
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute CaseDTO caseDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getPawnId(), caseDTO.getDefendant(), caseDTO.getPlaintiff(),
                caseDTO.getSpouse(), caseDTO.getMortgagor(), caseDTO.getMortgageTime(), caseDTO.getGuarantor(),
                caseDTO.getEvaluateExcellent(), caseDTO.getEvaluateLevel(), caseDTO.getLawsuitAccrual(),
                caseDTO.getLawsuitAmount(), caseDTO.getLawsuitCorpus(), caseDTO.getAttachmentStatus(),
                caseDTO.getCourtDTOList(), caseDTO.getIouIds())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = caseService.add_tx(caseDTO);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("增加失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

    /**
     * @api {post} http://{url}/case/update 修改案件信息
     * @apiName update
     * @apiGroup case
     * @apiUse Case
     * @apiUse CaseCourt
     * @apiSuccess {number} data 修改后的ID
     */
    public JsonResponse update(@ModelAttribute CaseDTO caseDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getPawnId(), caseDTO.getDefendant(), caseDTO.getPlaintiff(),
                caseDTO.getSpouse(), caseDTO.getMortgagor(), caseDTO.getMortgageTime(), caseDTO.getGuarantor(),
                caseDTO.getEvaluateExcellent(), caseDTO.getEvaluateLevel(), caseDTO.getLawsuitAccrual(),
                caseDTO.getLawsuitAmount(), caseDTO.getLawsuitCorpus(), caseDTO.getAttachmentStatus(),
                caseDTO.getCourtDTOList(), caseDTO.getIouIds(), caseDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = caseService.update_tx(caseDTO);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("修改失败");
        } else {
            return JsonResponseTool.success(caseDTO.getId());
        }
    }

    /**
     * @api {post} http://{url}/case/list 根据借款人查询案件信息
     * @apiName list
     * @apiGroup case
     * @apiParam {number} id 借款人ID
     * @apiParam {number} [index] 第N件案件
     * @apiSuccess {CaseDTO} data 案件信息
     * @apiUse CaseDTO
     * @apiUse CaseCourtDTO
     */
    public JsonResponse list(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer index) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        CaseDTO caseDTO = caseService.getByLender(id, index);
        Integer count = caseService.getCountBylender(id);
        if (CommonUtil.checkParam(caseDTO, count)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        map.put("total", count);
        map.put("data", caseDTO);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} http://{url}/case/listCase 根据案件查询
     * @apiName listCase
     * @apiGroup case
     * @apiParam {number} id 案件ID
     * @apiParam {number} [index] 第N件子案件
     * @apiSuccess {CaseDTO} data 案件信息
     * @apiUse CaseDTO
     * @apiUse CaseCourtDTO
     */
    public JsonResponse listCase(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer index) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        HashMap<String, Object> map = new HashMap<>();
        CaseDTO caseDTO = caseService.getByCase(id, index);
        Integer count = caseService.getCountByCase(id);
        if (CommonUtil.checkParam(caseDTO, count)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        map.put("total", count);
        map.put("data", caseDTO);
        return JsonResponseTool.success(map);
    }

    /**
     * @api {post} http://{url}/case/divide 拆分案件
     * @apiName divide
     * @apiGroup case
     * @apiParam {number} id 案件ID
     * @apiParam {string} ids 借据ID集(","隔开)
     * @apiSuccess {number} data 拆分后的ID
     */
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
        Boolean flag = true;
        for (String i : cIds) {
            boolean flag1 = true;
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
        Integer result = caseService.add_tx(caseDTO);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("增加失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

}
