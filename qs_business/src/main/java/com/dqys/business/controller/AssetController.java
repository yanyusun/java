package com.dqys.business.controller;

import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.core.utils.CommonUtil;
import com.dqys.business.service.utils.asset.AssetControllerUtils;
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
     * @param assetDTO
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public JsonResponse update(@ModelAttribute AssetDTO assetDTO) {
        if (CommonUtil.checkParam(assetDTO, assetDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = assetService.updateById(AssetControllerUtils.toAssetInfo(assetDTO));
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
            // 查询失败

            return JsonResponseTool.failure("获取失败");
        } else {
            // 成功

            return JsonResponseTool.success(AssetControllerUtils.toAssetDTO(assetInfo));
        }
    }

    /**
     * 分页获取资产包
     * @param page
     * @param pageCount
     * @return
     */
    @RequestMapping(value = "/pageList")
    @ResponseBody
    public JsonResponse pageList(@RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) Integer pageCount,
                                 @ModelAttribute AssetQuery assetQuery){
        assetQuery.setPage(page);
        assetQuery.setPageCount(pageCount);
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
            if (CommonUtil.checkResult(pawnId)) {
                // 添加抵押物失败处理

            }
        });

        // 添加借据
        iouDTOList.forEach(iouDTO -> {
            Integer index = iouDTO.getId();
            iouDTO.setLenderId(index);
            iouDTO.setId(null);
            Integer iouId = lenderService.addIOUInfo(AssetControllerUtils.toIouInfo(iouDTO), null);
            if (CommonUtil.checkResult(iouId)) {
                // 添加借据失败处理

            }
        });
        return JsonResponseTool.success("导入成功");
    }

    /**
     * 批量分配
     * @param ids 批量分配对象ID集合
     * @param id 被分配者ID
     * @return
     */
    @RequestMapping(value = "/assignedBatch")
    @ResponseBody
    public JsonResponse assignedBatch(@PathVariable Integer[] ids, @PathVariable Integer id){
        if(CommonUtil.checkParam(ids, id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        // auto 校验id是否存在
        if(id == null){
            return JsonResponseTool.paramErr("用户ID参数错误");
        }
        // 分配
        Integer result = assetService.delete(id);
        return CommonUtil.responseBack(result);
    }

    /**
     * 逻辑删除资产包
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(Integer id){
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = assetService.delete(id);
        return CommonUtil.responseBack(result);
    }

}
