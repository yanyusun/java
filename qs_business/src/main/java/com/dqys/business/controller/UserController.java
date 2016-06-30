package com.dqys.business.controller;

import com.dqys.business.service.dto.company.UserInsertDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.UserService;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/30.
 *
 * 成员管理
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 二级导航数据统计
     *
     * @return
     */
    @RequestMapping(value = "/listData")
    @ResponseBody
    public JsonResponse listData() {
        Map resultMap = new HashMap<>();
        // 读取当前用户的权限
        String roleStr = UserSession.getCurrent().getRoleId();
        // 根据当前用户不同的权限展示不同的统计

        // 伪造数据
        //平台号
        resultMap.put("plateform", 1);
        resultMap.put("plateformCompany", 3);
        //委托号
        resultMap.put("entrustTotal", 6);
        resultMap.put("entrustAgency", 7);
        resultMap.put("entrustSingle", 8);
        //处置号
        resultMap.put("disposeTotal", 11);
        resultMap.put("urge", 4);
        resultMap.put("Judicial", 3);
        resultMap.put("dispose", 4);
        return JsonResponseTool.success(resultMap);
    }

    /**
     * 获取列表时
     * @return
     */
    @RequestMapping(value = "/getListInit")
    @ResponseBody
    public JsonResponse getListInit() {
        Map resultMap = new HashMap<>();

//        resultMap.put("userStatuss", UserStatusTypeEnum.values());
//        resultMap.put("accountType", SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE));
//        resultMap.put("roleType", SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE));

        return JsonResponseTool.success(resultMap);
    }



    /**
     * 获取用户页面选择配置
     * @return
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getInit() {
        Map resultMap = new HashMap<>();

//        resultMap.put("userStatuss", UserStatusTypeEnum.values());
//        resultMap.put("accountType", SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE));
//        resultMap.put("roleType", SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE));

        return JsonResponseTool.success(resultMap);
    }

    /**
     * 用户列表
     *
     * @param userListQuery
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute UserListQuery userListQuery) {



        return JsonResponseTool.success("");
    }

    /**
     * 增加用户
     *
     * @param userInsertDTO
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute UserInsertDTO userInsertDTO) {
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getUserName(),
                userInsertDTO.getRealName(), userInsertDTO.getSex(), userInsertDTO.getApartmentId(),
                userInsertDTO.getOccupationId())
                || (userInsertDTO.getMobile() == null && userInsertDTO.getEmail() == null)
                ) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(11);
    }

    /**
     * 修改用户
     * @param userInsertDTO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute UserInsertDTO userInsertDTO) {
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getUserName(),
                userInsertDTO.getRealName(), userInsertDTO.getSex(), userInsertDTO.getApartmentId(),
                userInsertDTO.getOccupationId())
                || (userInsertDTO.getMobile() == null && userInsertDTO.getEmail() == null)
                ) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(11);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam(required = true) Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success("");
    }

    /**
     * 查看用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam(required = true) Integer id) {
        if(CommonUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success("1");
    }

    /**
     * 批量分配
     * @param ids
     * @param id
     * @return
     */
    @RequestMapping(value = "/assignedBatch")
    @ResponseBody
    public JsonResponse assignedBatch(@RequestParam(required = true) Integer[] ids,
                                      @RequestParam(required = true) Integer id) {
        if(CommonUtil.checkParam(ids, id)){
            return JsonResponseTool.paramErr("参数错误");
        }



        return JsonResponseTool.success("");
    }

    /**
     * 批量设置状态
     * @param ids
     * @param id
     * @return
     */
    @RequestMapping(value = "/statusBatch")
    @ResponseBody
    public JsonResponse statusBatch(@RequestParam(required = true) Integer[] ids,
                                    @RequestParam(required = true) Integer id) {
        if(CommonUtil.checkParam(ids, id)){
            return JsonResponseTool.paramErr("参数错误");
        }



        return JsonResponseTool.success("");
    }




}
