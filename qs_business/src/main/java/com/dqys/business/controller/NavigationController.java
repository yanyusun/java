package com.dqys.business.controller;

import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.orm.query.company.NavigationQuery;
import com.dqys.business.service.dto.company.NavigationDTO;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.service.company.NavigationService;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/25.
 */
@RequestMapping(value = "/nav")
@Controller
public class NavigationController {

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private UserService userService;


    /**
     * @api {POST} http://{url}/nav/add 增加导航栏
     * @apiName add
     * @apiGroup navigation
     * @apiUse Navigation
     * @apiSuccess {number} data 新增key
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse add(@ModelAttribute Navigation navigation) {
        if (CommonUtil.checkParam(navigation, navigation.getValue(), navigation.getName())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = navigationService.insert(navigation);
        if (CommonUtil.checkParam(result)) {
            return JsonResponseTool.failure("增加失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

    /**
     * @api {POST} http://{url}/nav/delete 删除导航栏
     * @apiName delete
     * @apiGroup navigation
     * @apiParam {number} id 导航栏key
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = navigationService.deleteByPrimaryKey(id);
        if (CommonUtil.checkParam(result)) {
            return JsonResponseTool.failure("删除失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

    /**
     * @api {POST} http://{url}/nav/update 修改导航栏
     * @apiName update
     * @apiGroup navigation
     * @apiUse Navigation
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute Navigation navigation) {
        if (CommonUtil.checkParam(navigation, navigation.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer result = navigationService.update(navigation);
        if (CommonUtil.checkParam(result)) {
            return JsonResponseTool.failure("修改失败");
        } else {
            return JsonResponseTool.success(result);
        }
    }

    /**
     * @api {POST} http://{url}/nav/get 获取导航栏
     * @apiName get
     * @apiGroup navigation
     * @apiParam {number} id 导航栏KEY
     * @apiSuccess {NavigationDTO} data 导航栏数据
     * @apiUse NavigationDTO
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        NavigationDTO navigationDTO = navigationService.get(id);
        if (CommonUtil.checkParam(navigationDTO)) {
            return JsonResponseTool.failure("获取失败");
        } else {
            return JsonResponseTool.success(navigationDTO);
        }
    }

    /**
     * @api {POST} http://{url}/nav/getInit 获取初始导航栏
     * @apiName getInit
     * @apiGroup navigation
     * @apiSuccess {NavigationDTO} data 导航栏数据
     * @apiUse NavigationDTO
     */
    @RequestMapping(value = "/getTop")
    @ResponseBody
    public JsonResponse getTop() {
        NavigationQuery navigationQuery = createNavigationQuery();
        if (CommonUtil.checkParam(navigationQuery)) {
            return JsonResponseTool.failure("未注册用户不具有该权限");
        }
        navigationQuery.setPid(SysProperty.DEFAULT);
        List<NavigationDTO> navigationDTOList = navigationService.queryList(navigationQuery);
        if (navigationDTOList != null && navigationDTOList.size() > 0) {
            return JsonResponseTool.success(navigationDTOList);
        } else {
            return JsonResponseTool.success(navigationDTOList);
        }
    }

    /**
     * @api {POST} http://{url}/nav/listById 根据导航栏key获取子导航栏
     * @apiName listById
     * @apiGroup navigation
     * @apiParam {number} id 导航栏key
     * @apiSuccess {NavigationDTO} data 导航栏数据
     * @apiUse NavigationDTO
     */
    @RequestMapping(value = "/listById")
    @ResponseBody
    public JsonResponse listById(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        List<NavigationDTO> navigationDTOList = listChild(id);
        if (navigationDTOList != null && navigationDTOList.size() > 0) {

            return JsonResponseTool.success(navigationDTOList);
        } else {
            return JsonResponseTool.success(navigationDTOList);
        }
    }

    /**
     * 递归调用获取导航栏菜单
     *
     * @param id
     * @return
     */
    private List<NavigationDTO> listChild(Integer id) {
        NavigationQuery navigationQuery = createNavigationQuery();
        navigationQuery.setPid(id);
        List<NavigationDTO> navigationDTOList = navigationService.queryList(navigationQuery);
        List<NavigationDTO> result = new ArrayList<>();
        if (navigationDTOList != null && navigationDTOList.size() > 0) {
            for (NavigationDTO navigationDTO : navigationDTOList) {
                if (navigationDTO.getChild() != null && navigationDTO.getChild()) {
                    navigationDTO.setGroup(listChild(navigationDTO.getKey()));
                }
                result.add(navigationDTO);
            }
            return result;
        } else {
            return null;
        }
    }


    /**
     * 根据用户不同的导航栏搜索选项
     */
    private NavigationQuery createNavigationQuery() {
        // 本地调试时开启
        UserInsertDTO userInsertDTO = (UserInsertDTO) userService.get(53).getData();
        if (CommonUtil.checkParam(userInsertDTO)) {
            return null;
        }
        // TODO 这边NosqlWithRedisTool 获取不到数据,先以死数据填充后,请后续修正
        NavigationQuery navigationQuery = new NavigationQuery();
        // 平台部分
        if (userInsertDTO.getUserType().equals(32)) {
            // 律所
            navigationQuery.setLaw(true);
        } else if (userInsertDTO.getUserType().equals(33)) {
            // 中介
            navigationQuery.setAgent(true);
        } else if (userInsertDTO.getUserType().equals(31)) {
            // 催收
            navigationQuery.setCollection(true);
        } else if (userInsertDTO.getUserType().equals(2)) {
            // 委托
            navigationQuery.setEntrust(true);
        } else if (userInsertDTO.getUserType().equals(1)) {
            // 平台
            navigationQuery.setPlatform(true);
        } else if (userInsertDTO.getUserType().equals(0)) {
            // 普通用户(个人)
            navigationQuery.setPersonal(true);
        } else {
            return null;
        }
        // 角色部分
        if (userInsertDTO.getRoleId().equals(2)) {
            // 管理者
            navigationQuery.setGovernor(true);
        } else if (userInsertDTO.getRoleId().equals(1)) {
            // 管理员
            navigationQuery.setManager(true);
        } else if (userInsertDTO.getRoleId().equals(3)) {
            // 普通员工
            navigationQuery.setEmployee(true);
        } else {
            return null;
        }

//        UserInsertDTO userInsertDTO = (UserInsertDTO) userService.get(UserSession.getCurrent().getUserId()).getData();
//        if (CommonUtil.checkParam(userInsertDTO)) {
//            return null;
//        }
//        NavigationQuery navigationQuery = new NavigationQuery();
//        // 平台部分
//        if (userInsertDTO.getUserType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_LAW) + ",")) {
//            // 律所
//            navigationQuery.setLaw(true);
//        } else if (userInsertDTO.getUserType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_INTERMEDIARY) + ",")) {
//            // 中介
//            navigationQuery.setAgent(true);
//        } else if (userInsertDTO.getUserType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_URGE))) {
//            // 催收
//            navigationQuery.setCollection(true);
//        } else if (userInsertDTO.getUserType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_ENTRUST))) {
//            // 委托
//            navigationQuery.setEntrust(true);
//        } else if (userInsertDTO.getUserType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_PLATFORM))) {
//            // 平台
//            navigationQuery.setPlatform(true);
//        } else if (userInsertDTO.getUserType().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_COMMON))) {
//            // 普通用户(个人)
//            navigationQuery.setPersonal(true);
//        } else {
//            return null;
//        }
//        // 角色部分
//        if (userInsertDTO.getRoleId().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.ROLE_MANAGER_KEY))) {
//            // 管理者
//            navigationQuery.setGovernor(true);
//        } else if (userInsertDTO.getRoleId().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.ROLE_ADMINISTRATOR_KEY))) {
//            // 管理员
//            navigationQuery.setManager(true);
//        } else if (userInsertDTO.getRoleId().equals(NoSQLWithRedisTool.getValueObject(KeyEnum.ROLE_MANAGER_KEY))) {
//            // 普通员工
//            navigationQuery.setEmployee(true);
//        } else {
//            return null;
//        }

        return navigationQuery;
    }
}
