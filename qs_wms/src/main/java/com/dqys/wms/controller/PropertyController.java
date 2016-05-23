package com.dqys.wms.controller;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author by pan on 16-4-7.
 */
@Controller
@RequestMapping("/property")
public class PropertyController {

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("types", SysPropertyTypeEnum.values());
        return "conf/index";
    }

    @RequestMapping("/add")
    public String propertyAdd(Model model, @ModelAttribute TSysProperty tSystemConf) {
        if(StringUtils.isBlank(tSystemConf.getPropertyValue()) || StringUtils.isBlank(tSystemConf.getPropertyName())) {
            model.addAttribute("errMsg", "增加失败");
        } else {
            if(!SysPropertyTool.addSysProperty(tSystemConf)) {
                model.addAttribute("errMsg", "新增配置项失败");
            }
        }
        return "common/detail_result";
    }

    @RequestMapping("/detail/{id}")
    public String propertyDetail(Model model, @PathVariable Integer id) {
        model.addAttribute("types", SysPropertyTypeEnum.values());
        TSysProperty tSysProperty = SysPropertyTool.getProperty(id);
        if(null != tSysProperty) {
            model.addAttribute("tSystemConf", tSysProperty);
        }
        if(!model.containsAttribute("tSystemConf")) {
            model.addAttribute("oper", "add");
        }

        return "conf/detail";
    }

    @RequestMapping("/update")
    public String propertyUpdate(Model model, @ModelAttribute TSysProperty tSystemConf) {
        if(StringUtils.isBlank(tSystemConf.getPropertyValue()) || StringUtils.isBlank(tSystemConf.getPropertyName())) {
            model.addAttribute("errMsg", "更新失败");
        }

        if(!SysPropertyTool.updateSysProperty(tSystemConf)) {
            model.addAttribute("errMsg", "更新失败");
        }

        return "common/detail_result";
    }

    @RequestMapping("/delete/{id}")
    public String propertyDel(Model model, @PathVariable Integer id) {
        if(!SysPropertyTool.deleteSysProperty(id)) {
            model.addAttribute("errMsg", "删除失败");
        }
        return "common/detail_result";
    }

    @ResponseBody
    @RequestMapping("/list")
    public Callable<JsonResponse> propertyList(@RequestParam(required = false) final Integer id, @RequestParam(required = false) final Integer type,
                                               @RequestParam(required = false) final String keyLike) {
        return () -> {
            List<TSysProperty> tSystemConfs = SysPropertyTool.getProperty();
            if (null != type) {
                SysPropertyTypeEnum e = SysPropertyTypeEnum.findByValue(type);
                tSystemConfs = SysPropertyTool.getProperty(e);
            }
            if (null != id) {
                tSystemConfs.add(SysPropertyTool.getProperty(id));
            }
            if (StringUtils.isNotBlank(keyLike)) {
                tSystemConfs = SysPropertyTool.getProperty(keyLike);
            }

            Map resultMap = new HashMap();
            resultMap.put("data", tSystemConfs);
            return JsonResponseTool.success(resultMap);
        };
    }
}
