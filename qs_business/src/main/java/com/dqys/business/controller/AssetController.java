package com.dqys.business.controller;

import com.dqys.business.service.constant.asset.AssetTypeEnum;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.dto.asset.PawnDTO;
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
    public JsonResponse get(@RequestParam("id") Integer id) {
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
    public JsonResponse addLenders(@RequestParam(required = true) Integer id, @RequestParam(required = true) MultipartFile file) {
        List<AssetDTO> assetDTOList = new ArrayList<>();
        List<ContactDTO> contactDTOList = new ArrayList<>();
        List<PawnDTO> pawnDTOList = new ArrayList<>();
        List<IouDTO> iouDTOList = new ArrayList<>();

        // 添加借款人基础信息
        HashMap<Integer, Integer> lenderMap = new HashMap<>();
        for (AssetDTO assetDTO : assetDTOList) {
            Integer index = assetDTO.getId();
            assetDTO.setId(null);
            return null;
//
//            Integer assetId = assetService.add(AssetServiceUtils.toAssetInfo(assetDTO));
//            if(assetId > 0){
//                lenderMap.put(index, assetId);
//            }else{
//                // 新增借款人失败处理
//
//            }
        }

        // 添加联系人信息
        contactDTOList.forEach(contactDTO -> {
            Integer index = contactDTO.getId();
//            contactDTO.setModeId(lenderMap.get(index));
//            contactDTO.setId(null);
//            Integer contactId = lenderService.addLenderInfo(AssetServiceUtils.toContactInfo(contactDTO));
//            if (contactId == null || contactId.equals("0")) {
//                // 添加联系人失败处理
//
//            }
        });

        // 添加抵押物
        pawnDTOList.forEach(pawnDTO -> {
            Integer index = pawnDTO.getId();
//            pawnDTO.setId(null);
//            pawnDTO.setLenderId(lenderMap.get(index));
//            Integer pawnId = pawnService.addPawn(AssetServiceUtils.toPawnInfo(pawnDTO));
//            if (CommonUtil.checkResult(pawnId)) {
//                // 添加抵押物失败处理
//
//            }
        });

        // 添加借据
        iouDTOList.forEach(iouDTO -> {
            Integer index = iouDTO.getId();
//            iouDTO.setLenderId(index);
//            iouDTO.setId(null);
//            Integer iouId = iouService.addIOUInfo(AssetServiceUtils.toIouInfo(iouDTO), null);
//            if (CommonUtil.checkResult(iouId)) {
//                // 添加借据失败处理
//
//            }
        });
        return JsonResponseTool.success("导入成功");
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
     * @apiUse AssetListQuery
     * @apiUse tabEnum
     * @apiSuccess {AssetDTO} data 资产包信息
     * @apiUse AssetDTO
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute AssetListQuery assetListQuery, @RequestParam(required = true) Integer type) {
        if(ObjectTabEnum.getObjectTabEnum(type) == null){
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.pageList(assetListQuery, type);
    }

    /**
     * @api {get} http://{url}/asset/assignedBatch 批量分配
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


}
