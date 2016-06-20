package com.dqys.business.controller;

import com.dqys.business.dto.asset.AssetDTO;
import com.dqys.business.dto.asset.ContactDTO;
import com.dqys.business.dto.asset.IouDTO;
import com.dqys.business.dto.asset.PawnDTO;
import com.dqys.business.util.CommonUtil;
import com.dqys.business.util.asset.AssetControllerUtils;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.constant.AssetModelTypeEnum;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.LenderService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * 添加一个资产包信息
     * @param assetDTO
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JsonResponse add(@ModelAttribute AssetDTO assetDTO) {
        if (CommonUtil.checkParam(assetDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = assetService.add(AssetControllerUtils.toAssetInfo(assetDTO));
        if (id == null || id.equals("0")) {
            return JsonResponseTool.failure("增加失败");
        } else {
            return JsonResponseTool.success(id);
        }
    }

    /**
     * 修改资产包信息
     * @param assetInfo
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public JsonResponse update(@ModelAttribute AssetInfo assetInfo) {
        if (CommonUtil.checkParam(assetInfo, assetInfo.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = assetService.updateById(assetInfo);
        if (id == null || id.equals("0")) {
            return JsonResponseTool.failure("增加失败");
        } else {
            return JsonResponseTool.success(id);
        }
    }

    /**
     * 获取资产包信息
     * @param id
     * @return
     */
    @RequestMapping(value = "get")
    @ResponseBody
    public JsonResponse get(@PathVariable Integer id) {
        if (id == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        AssetInfo assetInfo = assetService.getById(id);
        if (assetInfo == null) {
            //






            return JsonResponseTool.failure("获取失败");
        } else {
            return JsonResponseTool.success(assetInfo);
        }
    }

    /**
     * 分页获取资产包
     * @param page
     * @param count
     * @return
     */
    @RequestMapping(value = "/pageList")
    @ResponseBody
    public JsonResponse pageList(@PathVariable Integer page,
                                 @PathVariable Integer count){
        AssetQuery assetQuery = new AssetQuery();
        assetQuery.setPage(page);
        assetQuery.setPageCount(count);
        List<AssetInfo> assetInfoList = assetService.pageList(assetQuery);
        if (assetInfoList == null) {
            return JsonResponseTool.failure("获取失败");
        } else {
            return JsonResponseTool.success(assetInfoList);
        }
    }

    /**
     * excel导入资产包的借款人
     * @param id
     * @param file
     * @return
     */
    @RequestMapping(value = "/excelIn")
    @ResponseBody
    public JsonResponse addLenders(@RequestParam Integer id, MultipartFile file){
        List<AssetDTO> assetDTOList = new ArrayList<>();
        List<ContactDTO> contactDTOList = new ArrayList<>();
        List<PawnDTO> pawnDTOList = new ArrayList<>();
        List<IouDTO> iouDTOList = new ArrayList<>();

        // 添加借款人基础信息
        HashMap<Integer, Integer> lenderMap = new HashMap<>();
        for(AssetDTO assetDTO : assetDTOList){
            Integer index = assetDTO.getId();
            assetDTO.setId(null);
            Integer assetId = assetService.add(AssetControllerUtils.toAssetInfo(assetDTO));
            if(assetId > 0){
                lenderMap.put(index, assetId);
            }else{
                // 新增借款人失败处理

            }
        }

        // 添加联系人信息
        contactDTOList.forEach(contactDTO -> {
            Integer index = contactDTO.getId();
            contactDTO.setMode(AssetModelTypeEnum.LENDER);
            contactDTO.setModeId(lenderMap.get(index));
            contactDTO.setId(null);
            Integer contactId = lenderService.addLenderInfo(AssetControllerUtils.toContactInfo(contactDTO));
            if (contactId == null || contactId.equals("0")) {
                // 添加联系人失败处理

            }
        });

        // 添加抵押物
        pawnDTOList.forEach(pawnDTO -> {
            Integer index = pawnDTO.getId();
            pawnDTO.setId(null);
            pawnDTO.setLenderId(lenderMap.get(index));
            Integer pawnId = lenderService.addPawn(AssetControllerUtils.toPawnInfo(pawnDTO));
            if(CommonUtil.checkResult(pawnId)){
                // 添加抵押物失败处理

            }
        });

        // 添加借据
        iouDTOList.forEach(iouDTO -> {
            Integer index = iouDTO.getId();
            iouDTO.setLenderId(index);
            iouDTO.setId(null);
            Integer iouId = lenderService.addIOUInfo(AssetControllerUtils.toIouInfo(iouDTO), null);
            if(CommonUtil.checkResult(iouId)){
                // 添加借据失败处理
                
            }
        });

        return JsonResponseTool.success("");
    }

    /**
     * 批量分配
     * @param ids
     * @return
     */
    @RequestMapping(value = "/assignedBatch")
    @ResponseBody
    public JsonResponse assignedBatch(@PathVariable Integer[] ids){
        if(CommonUtil.checkParam(ids)){
            return JsonResponseTool.paramErr("参数错误");
        }





        return JsonResponseTool.success("");
    }
}
