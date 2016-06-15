package com.dqys.business.controller;

import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.facade.AssetService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Yvan on 16/6/1.
 */
@RestController
@RequestMapping(value = "/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    /**
     * 添加一个资产包信息
     * @param assetInfo
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JsonResponse add(@ModelAttribute AssetInfo assetInfo) {
        if (assetInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer id = assetService.add(assetInfo);
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
        if (assetInfo == null || assetInfo.getId() == null) {
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
            return JsonResponseTool.failure("获取失败");
        } else {
            return JsonResponseTool.success(assetInfo);
        }
    }

    /**
     * 获取所有的资产包信息
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public JsonResponse list() {
        List<AssetInfo> assetInfoList = assetService.listAll();
        if (assetInfoList == null) {
            return JsonResponseTool.failure("获取失败");
        } else {
            return JsonResponseTool.success(assetInfoList);
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
    @RequestMapping(value = "/addLenders")
    @ResponseBody
    public Integer addLenders(@RequestParam Integer id, MultipartFile file){


        return 1;
    }
}
