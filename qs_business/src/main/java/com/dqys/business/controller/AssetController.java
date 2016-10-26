package com.dqys.business.controller;

import com.dqys.business.service.constant.asset.AssetTypeEnum;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.AssetLenderInsertDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.business.service.service.AssetService;
import com.dqys.business.service.service.LenderService;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.asset.AssetServiceUtils;
import com.dqys.business.service.utils.asset.IouServiceUtils;
import com.dqys.business.service.utils.asset.LenderServiceUtils;
import com.dqys.business.service.utils.asset.PawnServiceUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @Qualifier("b_loginService")
    private UserService userService;

    /**
     * 获取初始化数据
     * @return
     */
    @RequestMapping(value = "/getInit")
    public JsonResponse getInit() {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("assetType", AssetTypeEnum.list()); // 资产包种类
        resultMap.put("excellent", ExcellentTypeEnum.list()); // 评优
        resultMap.put("level", CommonUtil.initLevel(8)); // 评级

        return JsonResponseTool.success(resultMap);
    }

    /**
     * 添加资产包
     * @param assetDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResponse add(@ModelAttribute AssetDTO assetDTO) throws BusinessLogException {
        String checkParam = AssetServiceUtils.checkAssetData(assetDTO);
        if (checkParam != null) {
            return JsonResponseTool.paramErr(checkParam);
        }
        return assetService.add_tx(assetDTO);
    }

    /**
     * 删除资产包
     * @param id
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/delete")
    public JsonResponse delete(Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.delete_tx(id);
    }

    /**
     * 修改资产包
     * @param assetDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/update")
    public JsonResponse update(@ModelAttribute AssetDTO assetDTO) throws BusinessLogException {
        String checkParam = AssetServiceUtils.checkAssetData(assetDTO);
        if (checkParam != null) {
            return JsonResponseTool.paramErr(checkParam);
        }
        if (assetDTO.getId() == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.updateById_tx(assetDTO);
    }


    /**
     * 获取资产包
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(@RequestParam Integer id) {
        if (id == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.getById(id);
    }

    /**
     * 获取资产包下联系人下拉
     * @param id
     * @return
     */
    @RequestMapping(value = "/listLenderSelect")
    public JsonResponse listLenderSelect(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return lenderService.listLender(id);
    }

    /**
     * excel导入资产包的借款人
     * @param id
     * @param file
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/excelIn")
    public JsonResponse addLenders(@RequestParam(required = true) Integer id,
                                   @RequestParam(required = true) String file) throws Exception {

        if (CommonUtil.checkParam(id, file)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.excelImport_tx(id, file);
    }

    /**
     * 查询资产包借款人
     * @param id
     * @return
     */
    @RequestMapping(value = "/listLender")
    public JsonResponse listLender(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.listLender(id);
    }

    /**
     * 获取资产包列表
     * @param assetListQuery
     * @param nav
     * @return
     */
    @RequestMapping(value = "/list")
    public JsonResponse list(@ModelAttribute AssetListQuery assetListQuery, @RequestParam(required = true) Integer nav) {
        if (ObjectTabEnum.getObjectTabEnum(nav) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return assetService.pageList(assetListQuery, nav);
    }

    /**
     * 批量分配(未完成)
     * @param ids
     * @param id
     * @return
     * @throws BusinessLogException
     */
//    @RequestMapping(value = "/assignedBatch")
//    public JsonResponse assignedBatch(@RequestParam("ids") String ids, @RequestParam("id") Integer id) throws BusinessLogException {
//        if (CommonUtil.checkParam(ids, id)) {
//            return JsonResponseTool.paramErr("参数错误");
//        }
//        // todo 校验id是否存在
//        if (userService.get(id).getData() == null) {
//            return JsonResponseTool.paramErr("用户ID参数错误");
//        }
//        // 分配
//        return assetService.assignedBatch(ids, id);
//    }

    /**
     * 添加资产包借款人(整合版)
     * @param assetLenderInsertDTO
     * @return
     * @throws BusinessLogException
     */
    @RequestMapping(value = "/addLender", method = RequestMethod.POST)
    public JsonResponse addLender(@ModelAttribute AssetLenderInsertDTO assetLenderInsertDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(assetLenderInsertDTO, assetLenderInsertDTO.getId(),
                assetLenderInsertDTO.getLenderDTO(), assetLenderInsertDTO.getContactDTOList())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String data = LenderServiceUtils.checkData(assetLenderInsertDTO.getLenderDTO());
        if (data != null) {
            return JsonResponseTool.paramErr(data);
        }
        data = LenderServiceUtils.checkData(assetLenderInsertDTO.getContactDTOList());
        if (data != null) {
            return JsonResponseTool.paramErr(data);
        }
        if(assetLenderInsertDTO.getPawnDTOList() != null && assetLenderInsertDTO.getPawnDTOList().size() > 0){
            data = PawnServiceUtils.checkData(assetLenderInsertDTO.getPawnDTOList());
            if(data != null){
                return JsonResponseTool.paramErr(data);
            }
        }
        if(assetLenderInsertDTO.getIouDTOList() != null && assetLenderInsertDTO.getIouDTOList().size() > 0){
            data = IouServiceUtils.checkData(assetLenderInsertDTO.getIouDTOList());
            if(data != null){
                return JsonResponseTool.paramErr(data);
            }
        }

        return assetService.addLender_tx(assetLenderInsertDTO.getId(),
                assetLenderInsertDTO.getContactDTOList(),
                assetLenderInsertDTO.getLenderDTO(),
                assetLenderInsertDTO.getPawnDTOList(),
                assetLenderInsertDTO.getIouDTOList());
    }

}
