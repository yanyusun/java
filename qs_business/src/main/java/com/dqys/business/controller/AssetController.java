package com.dqys.business.controller;

import com.dqys.business.service.constant.asset.AssetTypeEnum;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.*;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.*;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/1.
 */
@RestController
@RequestMapping(value = "/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;
    @Autowired
    private LenderService lenderService;
    @Autowired
    private UserService userService;

    /**
     * @api {get} http://{url}/asset/getInit 获取初始化数据
     * @apiName getInit
     * @apiGroup asset
     * @apiSuccess {SelectonDTO} assetType 资产包类型
     * @apiSuccess {SelectonDTO} excellent 评优
     * @apiUse SelectonDTO
     * @apiUse AssetTypeEnum
     * @apiUse ExcellentTypeEnum
     * @apiSuccess {string} level 评级
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getInit() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("assetType", AssetTypeEnum.list()); // 资产包种类
        resultMap.put("excellent", ExcellentTypeEnum.list()); // 评优
        resultMap.put("level", PawnServiceUtils.initLevel(8)); // 评级

        return JsonResponseTool.success(resultMap);
    }

    /**
     * @api {POST} http://{url}/asset/add 添加资产包
     * @apiName add
     * @apiGroup asset
     * @apiUse Asset
     * @apiSuccess {number} data 新增的ID
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute AssetDTO assetDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getType(), assetDTO.getStartAt(),
                assetDTO.getEndAt(), assetDTO.getAccrual(), assetDTO.getLoan(),
                assetDTO.getAppraisal())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.add_tx(assetDTO);
    }

    /**
     * @api {get} http://{url}/asset/delete 删除资产包
     * @apiName delete
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.delete_tx(id);
    }

    /**
     * @api {POST} http://{url}/asset/update 修改资产包
     * @apiName update
     * @apiGroup asset
     * @apiUse Asset
     * @apiSuccess {number} data 修改的ID
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonResponse update(@ModelAttribute AssetDTO assetDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.updateById_tx(assetDTO);
    }


    /**
     * @api {get} http://{url}/asset/get 获取资产包
     * @apiName get
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     * @apiSuccess {AssetDTO} data 资产包信息
     * @apiUse AssetDTO
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam Integer id) {
        if (id == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.getById(id);
    }

    /**
     * @api {get} http://{url}/asset/listLenderSelect 获取资产包下联系人下拉
     * @apiName listLenderSelect
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     * @apiSuccess {SelectonDTO} data 借款人信息
     * @apiUse SelectonDTO
     * @apiUse LenderListDTO
     */
    @RequestMapping(value = "/listLenderSelect")
    @ResponseBody
    public JsonResponse listLenderSelect(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.listLender(id);
    }

    /**
     * @api {get} http://{url}/asset/excelIn excel导入资产包的借款人
     * @apiName excelIn
     * @apiGroup asset
     * @apiParam {number} id 公司ID
     * @apiParam {file} file excel文件
     */
    @RequestMapping(value = "/excelIn")
    @ResponseBody
    public JsonResponse addLenders(@RequestParam(required = true) Integer id,
                                   @RequestParam(required = true) MultipartFile file) throws BusinessLogException {
        if(CommonUtil.checkParam(id, file)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.excelImport_tx(id, file);
    }

    /**
     * @api {get} http://{url}/asset/listLender 查询资产包借款人
     * @apiName listLender
     * @apiGroup asset
     * @apiParam {number} id 资产包ID
     */
    @RequestMapping(value = "/listLender")
    @ResponseBody
    public JsonResponse listLender(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.listLender(id);
    }

    /**
     * @api {get} http://{url}/asset/list 获取资产包列表
     * @apiName list
     * @apiGroup asset
     * @apiParam {number} nav 子导航栏项目
     * @apiUse AssetListQuery
     * @apiUse tabEnum
     * @apiSuccess {AssetDTO} data 资产包信息
     * @apiUse AssetDTO
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute AssetListQuery assetListQuery, @RequestParam(required = true) Integer nav) {
        if(ObjectTabEnum.getObjectTabEnum(nav) == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.pageList(assetListQuery, nav);
    }

    /**
     * @api {get} http://{url}/asset/assignedBatch 批量分配(未完成)
     * @apiName assignedBatch
     * @apiGroup asset
     * @apiDescription 协作器时补充
     * todo 未完成
     * @apiParam {string} ids 批量分配对象ID集合
     * @apiParam {number} id  被分配者ID
     */
    @RequestMapping(value = "/assignedBatch")
    @ResponseBody
    public JsonResponse assignedBatch(@PathVariable String ids, @PathVariable Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(ids, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // todo 校验id是否存在
        if (userService.get(id).getData() == null) {
            return JsonResponseTool.paramErr("用户ID参数错误");
        }
        // 分配
        return assetService.assignedBatch(ids, id);
    }

    /**
     * @api {get} http://{url}/asset/addLender 添加资产包借款人(整合版)
     * @apiName addLender
     * @apiGroup asset
     * @apiParam {number} id  资产包ID
     * @apiParam {LenderDTO} lenderDTO 借款人基础信息
     * @apiParam {ContactDTO} contactDTOList 联系人集合
     * @apiParam {PawnDTO} pawnDTOList 抵押物集合
     * @apiParam {IouDTO} iouDTOList 借据集合
     * @apiUse Iou
     * @apiUse Pawn
     * @apiUse LenderDTO
     * @apiUse ContactDTO
     */
    @RequestMapping(value = "/addLender", method = RequestMethod.POST)
    public JsonResponse addLender(@RequestParam Integer id,
                                  @RequestParam List<ContactDTO> contactDTOList,
                                  @RequestParam List<PawnDTO> pawnDTOList,
                                  @RequestParam List<IouDTO> iouDTOList,
                                  @ModelAttribute LenderDTO lenderDTO) throws BusinessLogException{
        if(CommonUtil.checkParam(id, lenderDTO, pawnDTOList, contactDTOList, iouDTOList)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.addLender_tx(id, contactDTOList, lenderDTO, pawnDTOList, iouDTOList);
    }

}
