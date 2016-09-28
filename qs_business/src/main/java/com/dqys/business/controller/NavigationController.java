package com.dqys.business.controller;

import com.dqys.business.orm.pojo.company.Navigation;
import com.dqys.business.orm.query.company.NavigationQuery;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.dto.company.NavigationDTO;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.service.company.NavigationService;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Yvan on 16/7/25.
 */
@RequestMapping(value = "/nav")
@Controller
public class NavigationController {

    @Autowired
    private NavigationService navigationService;
    @Autowired  @Qualifier("b_loginService")
    private UserService userService;


    /**
     * 增加导航栏
     * @param navigation
     * @return
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
     * 删除导航栏
     * @param id
     * @return
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
     * 修改导航栏
     * @param navigation
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute Navigation navigation) {
        if (CommonUtil.checkParam(navigation, navigation.getId(), navigation.getName())) {
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
     * 获取导航栏
     * @param id
     * @return
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
     * 获取初始导航栏
     * @return
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
     * 根据导航栏key获取子导航栏
     * @param id
     * @return
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
        // 这样重复调用数据库造成资源的多次请求,
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

//        // 采用纯数据处理,查全,去顶部菜单栏
//        NavigationQuery navigationQuery = createNavigationQuery();
//        List<NavigationDTO> navigationDTOList = navigationService.queryList(navigationQuery);
//
//        List<Navigation> navigationList = new ArrayList<>();
//        Map<Integer, List<Navigation>> navigationDTOMap = new HashMap<>();
//        Collections.sort(navigationList, (a,b) -> b.getPid().compareTo(a.getPid()));
//        navigationList.forEach(navigation -> {
//            if(!navigation.getPid().equals(0)){
//                // 非顶部导航栏
//
//            }
//        });
//
//
//
////        navigationDTOList.forEach(navigationDTO1 -> {
////            if(!navigationDTO1.getKey().equals(0)){
////                // 非顶部菜单
////                if)
////            }
////        });
//
//
//
//        List<NavigationDTO> result = new ArrayList<>();
//        if (navigationDTOList != null && navigationDTOList.size() > 0) {
//            for (NavigationDTO navigationDTO : navigationDTOList) {
//                if (navigationDTO.getChild() != null && navigationDTO.getChild()) {
//                    navigationDTO.setGroup(listChild(navigationDTO.getKey()));
//                }
//                result.add(navigationDTO);
//            }
//            return result;
//        } else {
//            return null;
//        }
    }


    /**
     * 根据用户不同的导航栏搜索选项
     */
    private NavigationQuery createNavigationQuery() {
        // 本地调试时开启
        UserInsertDTO userInsertDTO = (UserInsertDTO) userService.get(UserSession.getCurrent().getUserId()).getData();
//        UserInsertDTO userInsertDTO = (UserInsertDTO) userService.get(53).getData();
        if (CommonUtil.checkParam(userInsertDTO)) {
            return null;
        }
        NavigationQuery navigationQuery = new NavigationQuery();
        // 用户类型
        String userType = UserSession.getCurrent().getUserType();
        navigationQuery.setType(userType.substring(0, userType.indexOf(",")));
//        // 平台部分
//        if (userInsertDTO.getUserType().equals(UserInfoEnum.USER_TYPE_JUDICIARY.getValue())) {
//            // 律所
//            navigationQuery.setLaw(true);
//        } else if (userInsertDTO.getUserType().equals(UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue())) {
//            // 中介
//            navigationQuery.setAgent(true);
//        } else if (userInsertDTO.getUserType().equals(UserInfoEnum.USER_TYPE_COLLECTION.getValue())) {
//            // 催收
//            navigationQuery.setCollection(true);
//        } else if (userInsertDTO.getUserType().equals(UserInfoEnum.USER_TYPE_ENTRUST.getValue())) {
//            // 委托
//            navigationQuery.setEntrust(true);
//        } else if (userInsertDTO.getUserType().equals(UserInfoEnum.USER_TYPE_ADMIN.getValue())) {
//            // 平台
//            navigationQuery.setPlatform(true);
//        } else if (userInsertDTO.getUserType().equals(UserInfoEnum.USER_TYPE_COMMON.getValue())) {
//            // 普通用户(个人)
//            navigationQuery.setPersonal(true);
//        } else {
//            return null;
//        }
        // 角色部分
        if (userInsertDTO.getRoleId().equals(RoleTypeEnum.REGULATOR.getValue())) {
            // 管理者
            navigationQuery.setGovernor(true);
        } else if (userInsertDTO.getRoleId().equals(RoleTypeEnum.ADMIN.getValue())) {
            // 管理员
            navigationQuery.setManager(true);
        } else if (userInsertDTO.getRoleId().equals(RoleTypeEnum.GENERAL.getValue())) {
            // 普通员工
            navigationQuery.setEmployee(true);
        } else {
            return null;
        }
        return navigationQuery;
    }
}
