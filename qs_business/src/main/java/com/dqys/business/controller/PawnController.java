package com.dqys.business.controller;

import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.dto.asset.PawnDTOList;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.PawnService;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yvan on 16/7/12.
 */
@Controller
@RequestMapping(value = "/pawn")
public class PawnController {

    @Autowired
    private PawnService pawnService;


    /**
     * 删除抵押物
     * @param id
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.delete_tx(id);
    }

    /**
     * 增加抵押物信息
     * @param pawnDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute PawnDTO pawnDTO) throws BusinessLogException {
        String data = PawnServiceUtils.checkData(pawnDTO);
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        return pawnService.add_tx(pawnDTO);
    }

    /**
     * 增加抵押物信息(多条)
     * @param pawnDTOList
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/listAdd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute PawnDTOList pawnDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(pawnDTOList, pawnDTOList.getPawnDTOList()) || pawnDTOList.getPawnDTOList().size() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String data = PawnServiceUtils.checkData(pawnDTOList.getPawnDTOList());
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        return pawnService.listAdd(pawnDTOList.getPawnDTOList());
    }

    /**
     * 修改抵押物信息
     * @param pawnDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute PawnDTO pawnDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(pawnDTO, pawnDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String data = PawnServiceUtils.checkData(pawnDTO);
        if(data != null){
            return JsonResponseTool.paramErr(data);
        }
        return pawnService.update_tx(pawnDTO);
    }

    /**
     * 获取抵押物信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return pawnService.get(id);
    }

    /**
     * 获取借款人的抵押物信息
     * @param id
     * @return
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
