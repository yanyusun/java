package com.dqys.business.controller;

import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.dto.asset.IouDTOList;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.IouService;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yvan on 16/7/11.
 */
@Controller
@RequestMapping(value = "/iou")
public class IouController {

    @Autowired
    private IouService iouService;

    /**
     * 删除借据
     *
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
        return iouService.delete_tx(id);
    }

    /**
     * 新增借据
     *
     * @param iouDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute IouDTO iouDTO) throws BusinessLogException {
        String data = IouServiceUtils.checkData(iouDTO);
        if (data != null) {
            return JsonResponseTool.paramErr(data);
        }
        return iouService.add_tx(iouDTO);
    }

    /**
     * 新增借据
     *
     * @param iouDTOList
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/listAdd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute IouDTOList iouDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(iouDTOList, iouDTOList.getIouDTOList()) || iouDTOList.getIouDTOList().size() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String data = IouServiceUtils.checkData(iouDTOList.getIouDTOList());
        if (data != null) {
            return JsonResponseTool.paramErr(data);
        }
        return iouService.listAdd(iouDTOList.getIouDTOList());
    }

    /**
     * 修改借据信息
     *
     * @param iouDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute IouDTO iouDTO) throws BusinessLogException {
        String data = IouServiceUtils.checkData(iouDTO);
        if (data != null) {
            return JsonResponseTool.paramErr(data);
        }
        if (iouDTO.getId() == null) {
            return JsonResponseTool.paramErr("参数错误,找不到该借据");
        }
        return iouService.update_tx(iouDTO);
    }

    /**
     * 获取借据信息
     *
     * @param id
     * @return
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
     * 获取借据信息(C端)
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/c/get")
    @ResponseBody
    public JsonResponse getC(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.get(id);
    }

    /**
     * 获取借款人的借据信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/listIou")
    @ResponseBody
    public JsonResponse listIou(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.listIouByLenderId(id);
    }

    /**
     * 获取借款人的借据信息(C端)
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/c/listIou")
    @ResponseBody
    public JsonResponse listIouC(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return iouService.listIouByLenderIdC(id);
    }


}
