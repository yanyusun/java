package com.dqys.resource.controller;

import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TArea;
import com.dqys.core.utils.ApiParseTool;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.resource.extend.AreaList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by pan on 16-5-20.
 */
@RestController
@RequestMapping("/area")
public class AreaController extends BaseApiContorller {

    @RequestMapping(path = "/list")
    public Callable<JsonResponse> queryAreaByUpper(@RequestParam(defaultValue = "0") Integer aid,
                                                   @RequestParam(required = false) String nameLike) {
        return () -> {
            List<TArea> tAreaList = AreaTool.listAreaByUpperId(aid);
            if (null == tAreaList || tAreaList.isEmpty()) {
                return JsonResponseTool.noData();
            }

            if (StringUtils.isNoneEmpty(nameLike)) {
                tAreaList = AreaTool.filterAreaByName(tAreaList, nameLike);
            }

            return JsonResponseTool.success(ApiParseTool.parseApiList(tAreaList, KeyEnum.API_AREA_LIST));
        };
    }

    @RequestMapping(path = "/listAll")
    public Callable<JsonResponse> listAll(){
        return () -> {
            List<TArea> tAreaList = AreaTool.listAreaByUpperId(0);
            if (null == tAreaList || tAreaList.isEmpty()) {
                return JsonResponseTool.noData();
            }

            List<AreaList> result = new ArrayList<>();
            for (TArea tArea : tAreaList) {
                AreaList area = toAreaList(tArea);
                area.setAreaList(listAllChildAreaById(area.getId()));
                result.add(area);
            }
            return JsonResponseTool.success(result);
//            return JsonResponseTool.success(ApiParseTool.parseApiList(tAreaList, KeyEnum.API_AREA_LIST));
        };
    }


    private List<AreaList> listAllChildAreaById(Integer id){
        if(CommonUtil.checkParam(id)){
            return null;
        }
        try {
            List<TArea> areaList = AreaTool.listAreaByUpperId(id);
            if(CommonUtil.checkParam(areaList) || areaList.size() == 0){
                return null;
            }
            List<AreaList> result = new ArrayList<>();
            for (TArea tArea : areaList) {
                AreaList area = toAreaList(tArea);
                area.setAreaList(listAllChildAreaById(area.getId()));
                result.add(area);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private AreaList toAreaList(TArea tarea){
        if(CommonUtil.checkParam(tarea)){
            return null;
        }
        AreaList result = new AreaList();

        result.setId(tarea.getId());
        result.setIsLeaf(tarea.getIsLeaf());
        result.setLevel(tarea.getLevel());
        result.setName(tarea.getName());
        result.setUpper(tarea.getUpper());

        return result;
    }

}
