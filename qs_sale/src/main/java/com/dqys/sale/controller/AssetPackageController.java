package com.dqys.sale.controller;

import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.ProtocolTool;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetBusiness;
import com.dqys.sale.orm.query.AssetPackageQuery;
import com.dqys.sale.service.dto.AssetPackageDTO;
import com.dqys.sale.service.facade.AssetPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 资产包管理
 * Created by mkfeng on 2016/12/23.
 */
@Controller
@RequestMapping("/asset")
public class AssetPackageController {
    @Autowired
    private AssetPackageService assetPackageService;

    /**
     * @api {post} asset/noVerify/assetList 获取资产包列表
     * @apiName asset/noVerify/assetList
     * @apiSampleRequest asset/noVerify/assetList
     * @apiGroup　 asset
     */
    @RequestMapping("/noVerify/assetList")
    @ResponseBody
    public JsonResponse assetList(AssetPackageQuery query, HttpServletRequest httpServletRequest) throws Exception {
        ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        query.setBusinessStatus(AssetBusiness.getHasAnnouncedLevel().getLevel());
        return assetPackageService.assetList(query);
    }

    /**
     * @api {post} asset/noVerify/getDetail 获取资产包详情
     * @apiName asset/noVerify/getDetail
     * @apiSampleRequest asset/noVerify/getDetail
     * @apiParam {int} assetId 资产包id
     * @apiGroup　 asset
     */
    @RequestMapping("/noVerify/getDetail")
    @ResponseBody
    public JsonResponse getDetail(Integer assetId, HttpServletRequest httpServletRequest) throws Exception {
        Integer userId = ProtocolTool.validateUser(
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_USER.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_TYPE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_ROLE.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_CERTIFIED.getValue()),
                httpServletRequest.getHeader(AuthHeaderEnum.X_QS_STATUS.getValue())
        );
        return assetPackageService.getDetail(assetId);
    }

    /**
     * @api {post} asset/list 获取资产包列表
     * @apiName asset/list
     * @apiSampleRequest asset/list
     * @apiGroup　 asset
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResponse list(AssetPackageQuery query) {
        if (!UserSession.getCurrent().getUserType().equals("1,")) {
            query.setUserId(UserSession.getCurrent().getUserId());
        }
        return assetPackageService.list(query);
    }

    /**
     * @api {post} asset/addAsset 添加资产包
     * @apiName asset/addAsset
     * @apiSampleRequest asset/addAsset
     * @apiGroup　 asset
     */
    @RequestMapping("/addAsset")
    @ResponseBody
    public JsonResponse addAsset(@ModelAttribute AssetPackageDTO assetPackageDTO) {
        return assetPackageService.addAsset_tx(assetPackageDTO);
    }

    /**
     * @api {post} asset/updateAsset 修改资产包
     * @apiName asset/updateAsset
     * @apiSampleRequest asset/updateAsset
     * @apiGroup　 asset
     */
    @RequestMapping("/updateAsset")
    @ResponseBody
    public JsonResponse updateAsset(@ModelAttribute AssetPackageDTO assetPackageDTO) {
        return assetPackageService.updateAsset_tx(assetPackageDTO);
    }


}
