package com.dqys.auth.controller;


import com.dqys.auth.orm.query.UserListQuery;
import com.dqys.auth.service.dto.UserInsertDTO;
import com.dqys.auth.service.dto.UserListDTO;
import com.dqys.auth.service.facade.UserService;
import com.dqys.core.base.BaseApiContorller;
import com.dqys.core.base.BasePageDTO;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonControllerUtil;
import com.dqys.core.utils.JsonResponseTool;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/22.
 * 用户管理模块
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController extends BaseApiContorller {

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
     * 用户列表
     *
     * @param userListQuery
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute UserListQuery userListQuery) {






        // 伪造的数据
        Integer total = RandomUtils.nextInt(3, 50);
        List<UserListDTO> userListDTOList = newUserList(total);


        BasePageDTO<List<UserListDTO>> basePageDTO = new BasePageDTO<>();
        basePageDTO.setPage(userListQuery.getPage());
        basePageDTO.setPageCount(userListQuery.getPageCount());
        basePageDTO.setData(userListDTOList);
        basePageDTO.setTotal(total);
        return JsonResponseTool.success(basePageDTO);
    }

    /**
     * 增加用户
     *
     * @param userInsertDTO
     * @return
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResponse add(@ModelAttribute UserInsertDTO userInsertDTO) {
        if (CommonControllerUtil.checkParam(userInsertDTO, userInsertDTO.getUserName(),
                userInsertDTO.getRealName(), userInsertDTO.getSex(), userInsertDTO.getApartmentId(),
                userInsertDTO.getOccupationId())
                || (userInsertDTO.getMobile() == null && userInsertDTO.getEmail() == null)
                ) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return JsonResponseTool.success(11);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam(required = true) Integer id) {
        if(CommonControllerUtil.checkParam(id)){
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success("");
    }




    private List<UserListDTO> newUserList(Integer num) {
        List<UserListDTO> userListDTOList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            userListDTOList.add(newUser(i));
        }
        return userListDTOList;
    }

    private UserListDTO newUser(Integer index) {
        UserListDTO userListDTO = new UserListDTO();

        userListDTO.setId(index);
        userListDTO.setStatus(index % 2);
        userListDTO.setUserName("昵称" + index);
        userListDTO.setRealName("姓名" + index);
        userListDTO.setMobile("12312341234");
        userListDTO.setEmail(index + "@email");
        userListDTO.setArea("行政规划" + index);
        userListDTO.setCompany("分公司" + index);
        userListDTO.setTaskNum(index);
        userListDTO.setUserStatus(index % 3 == 0 ? 1 : 0);

        return userListDTO;
    }
}
