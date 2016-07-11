package com.dqys.business.controller;

import com.dqys.business.service.constant.AssetModelTypeEnum;
import com.dqys.business.service.constant.AssetTypeEnum;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
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

    /**
     * @api {POST} http://{url}/asset/add 添加资产包
     * @apiName add
     * @apiGroup asset
     * @apiUse Asset
     * @apiSuccess {number} data 新增的ID
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute AssetDTO assetDTO) {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getType(), assetDTO.getStartAt(),
                assetDTO.getEndAt(), assetDTO.getAccrual(), assetDTO.getLoan(),
                assetDTO.getAppraisal())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.add(assetDTO);
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
    public JsonResponse update(@ModelAttribute AssetDTO assetDTO) {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return assetService.updateById(assetDTO);
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
     * @api {get} http://{url}/asset/getInit 获取初始化数据
     * @apiName getInit
     * @apiGroup asset
     * @apiSuccess {SelectonDTO} assetType 资产包类型
     * @apiUse SelectonDTO
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getInit() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("assetType", AssetTypeEnum.list()); // 资产包种类

        return JsonResponseTool.success(resultMap);
    }


    /**
     * @api {get} http://{url}/asset/list 获取资产包列表
     * @apiName list
     * @apiGroup asset
     * @apiUse AssetListQuery
     * @apiSuccess {AssetDTO} data 资产包信息
     * @apiUse AssetDTO
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute AssetListQuery assetListQuery) {
        return assetService.pageList(assetListQuery);
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
    public JsonResponse addLenders(@RequestParam(required = true) Integer id,@RequestParam(required = true) MultipartFile file) {
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
            contactDTO.setMode(AssetModelTypeEnum.LENDER);
            contactDTO.setModeId(lenderMap.get(index));
            contactDTO.setId(null);
            Integer contactId = lenderService.addLenderInfo(AssetServiceUtils.toContactInfo(contactDTO));
            if (contactId == null || contactId.equals("0")) {
                // 添加联系人失败处理

            }
        });

        // 添加抵押物
        pawnDTOList.forEach(pawnDTO -> {
            Integer index = pawnDTO.getId();
            pawnDTO.setId(null);
            pawnDTO.setLenderId(lenderMap.get(index));
            Integer pawnId = lenderService.addPawn(AssetServiceUtils.toPawnInfo(pawnDTO));
            if (CommonUtil.checkResult(pawnId)) {
                // 添加抵押物失败处理

            }
        });

        // 添加借据
        iouDTOList.forEach(iouDTO -> {
            Integer index = iouDTO.getId();
            iouDTO.setLenderId(index);
            iouDTO.setId(null);
            Integer iouId = lenderService.addIOUInfo(AssetServiceUtils.toIouInfo(iouDTO), null);
            if (CommonUtil.checkResult(iouId)) {
                // 添加借据失败处理

            }
        });
        return JsonResponseTool.success("导入成功");
    }

    /**
     * 批量分配
     * todo 未完成
     *
     * @param ids 批量分配对象ID集合
     * @param id  被分配者ID
     * @return
     */
    @RequestMapping(value = "/assignedBatch")
    @ResponseBody
    public JsonResponse assignedBatch(@PathVariable String ids, @PathVariable Integer id) {
        if (CommonUtil.checkParam(ids, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // auto 校验id是否存在
        if (id == null) {
            return JsonResponseTool.paramErr("用户ID参数错误");
        }
        // 分配
        return assetService.delete(id);
    }

    /**
     * 逻辑删除资产包
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.delete(id);
    }

}
