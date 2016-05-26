package com.dqys.auth.controller;

import com.dqys.auth.orm.pojo.TATOInfo;
import com.dqys.auth.service.constant.ATOInfoEnum;
import com.dqys.auth.service.facade.ATOInfoService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Yvan on 16/5/26.
 */
@Controller
@RequestMapping("/ato")
public class AtlInfoController extends BaseApiContorller {

    @Autowired
    private ATOInfoService atoInfoService;

    /**
     * 增加项目信息
     * @param name
     * @param type
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public JsonResponse add(@Param("name")String name, @Param("type")Integer type){
        ATOInfoEnum atoInfoEnum = ATOInfoEnum.getATOInfoEnum(type);
        if(atoInfoEnum == null){
            return JsonResponseTool.paramErr("类型参数丢失");
        }
        if(name == null || name.equals("")){
            return JsonResponseTool.paramErr("名称为空");
        }

        TATOInfo tatoInfo = new TATOInfo();
        tatoInfo.setName(atoInfoEnum.getName());
        tatoInfo.setType(atoInfoEnum.getType());
        Integer id = atoInfoService.add(tatoInfo).intValue();
        if(id > 0){
            return JsonResponseTool.success(id);
        }else{
            return JsonResponseTool.failure("新增失败");
        }
    }

    /**
     * 修改项目信息
     * @param id
     * @param typeName
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public JsonResponse update(@Param("id")Integer id, @Param("name")String typeName){
        if(id == null || id.equals("")){
            return JsonResponseTool.paramErr("id参数丢失");
        }
        if(typeName == null || typeName.equals("")){
            return JsonResponseTool.paramErr("名称为空");
        }

        TATOInfo TATOInfo = new TATOInfo();
        TATOInfo.setId(id);
        TATOInfo.setName(typeName);
        Integer result = atoInfoService.updateById(TATOInfo);
        if(result.intValue() > 0){
            return JsonResponseTool.success(id);
        }else{
            return JsonResponseTool.failure("修改失败");
        }
    }

    /**
     * 获取单个项目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    public JsonResponse get(@Param("id")Integer id){
        if(id == null || id.equals("")){
            return JsonResponseTool.paramErr("id参数丢失");
        }
        TATOInfo tatoInfo = atoInfoService.get(id);
        if(tatoInfo == null){
            return JsonResponseTool.failure("获取失败");
        }else{
            return JsonResponseTool.success(tatoInfo);
        }
    }

    /**
     * 获取项目所有信息
     * @param type
     * @return
     */
    @RequestMapping(value = "/listAll")
    public JsonResponse listAll(@Param("type")Integer type){
        ATOInfoEnum atoInfoEnum = ATOInfoEnum.getATOInfoEnum(type);
        if(atoInfoEnum == null){
            return JsonResponseTool.paramErr("类型参数丢失");
        }

        List<TATOInfo> tatoInfoList = atoInfoService.listAll(type);
        if(tatoInfoList == null){
            return JsonResponseTool.failure("加载" + atoInfoEnum.getName() + "列表失败");
        }
        return JsonResponseTool.success(tatoInfoList);
    }


}
