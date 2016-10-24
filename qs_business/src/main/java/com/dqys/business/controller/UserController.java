package com.dqys.business.controller;

import com.dqys.business.service.constant.UserStatusTypeEnum;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TSysProperty;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/30.
 * <p/>
 * 成员管理
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    @Qualifier("b_loginService")
    private UserService userService;

    /**
     * 二级导航统计(没用，先预留)
     */
    @RequestMapping(value = "/listData")
    @ResponseBody
    public JsonResponse listData() {
        Map resultMap = new HashMap<>();
        // 读取当前用户的权限
        if (CommonUtil.isManage()) {
            // 总管理用户
            resultMap.put("plateform", 1);
            resultMap.put("plateformCompany", 3);
        } else {
            String typeStr = UserSession.getCurrent().getUserType();
            String[] values = typeStr.split(",");
        }
        //平台号


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
     * 获取公司(没用，先预留)
     *
     * @return
     */
    @RequestMapping(value = "/listUser")
    @ResponseBody
    public JsonResponse listUser() {

        TSysProperty property = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM);
        String reg = property.getPropertyValue();

        return null;
    }

    /**
     * 获取初始化列表
     *
     * @return
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getListInit() {
        Map resultMap = new HashMap<>();

        resultMap.put("companyInfo", userService.getCompanyByUserId(UserSession.getCurrent().getUserId())); // 公司信息
        resultMap.put("userStatus", UserStatusTypeEnum.listUserStatusTypeEnum()); // 用户状态
        resultMap.put("userType", SysPropertyTool.toPropertyDTO(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE))); //账号类型
        resultMap.put("roleType", SysPropertyTool.toPropertyDTO(
                SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE))); // 角色类型

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
        return userService.queryList(userListQuery);
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
                userInsertDTO.getRealName(), userInsertDTO.getSex(), userInsertDTO.getAccount(),
                userInsertDTO.getDuty(), userInsertDTO.getMobile(),
                userInsertDTO.getEmail(), userInsertDTO.getApartmentId(), userInsertDTO.getOccupation(),
                userInsertDTO.getAreaId(), userInsertDTO.getRoleId(), userInsertDTO.getUserType())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // 其他校验
        return userService.add(userInsertDTO);
    }

    /**
     * 查看用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get")
    @ResponseBody
    public JsonResponse get(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return userService.get(id);
    }

    /**
     * 修改用户信息
     *
     * @param userInsertDTO
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse update(@ModelAttribute UserInsertDTO userInsertDTO) {
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getUserName(),
                userInsertDTO.getRealName(), userInsertDTO.getSex(), userInsertDTO.getAccount(),
                userInsertDTO.getDuty(), userInsertDTO.getWechat(), userInsertDTO.getMobile(),
                userInsertDTO.getEmail(), userInsertDTO.getApartmentId(), userInsertDTO.getOccupation(),
                userInsertDTO.getAreaId(), userInsertDTO.getRoleId(), userInsertDTO.getId(),
                userInsertDTO.getUserType())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return userService.update(userInsertDTO);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonResponse delete(@RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return userService.delete(id);
    }


    /**
     * 批量分配<暂时不处理>
     *
     * @param ids
     * @param id
     * @return
     */
    @RequestMapping(value = "/assignedBatch")
    @ResponseBody
    public JsonResponse assignedBatch(@RequestParam(required = true) String ids,
                                      @RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(ids, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return JsonResponseTool.success("");
    }

    /**
     * 批量设置状态
     *
     * @param ids
     * @param status
     * @return
     */
    @RequestMapping(value = "/statusBatch")
    @ResponseBody
    public JsonResponse statusBatch(@RequestParam(required = true) String ids,
                                    @RequestParam(required = true) Integer status) {
        if (CommonUtil.checkParam(ids, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return userService.statusBatch(ids, status);
    }

    /**
     * 成员信息excel导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userExcel")
    @ResponseBody
    public JsonResponse userExcel(@RequestParam String file) throws Exception {
        if (CommonUtil.checkParam(file)) {
            return JsonResponseTool.paramErr("未上传文件");
        }

        return userService.excelImport_tx(file);
    }

    /**
     * 消息提醒(暂时废除)
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/sendMsg")
    @ResponseBody
    public JsonResponse sendMsg(@RequestParam(required = true) String ids) {
        if (CommonUtil.checkParam(ids)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String[] idsArr = ids.split(",");
        List idList = new ArrayList<>();
        for (String id : idsArr) {
            idList.add(Integer.valueOf(id));
        }
        return userService.sendMsg(idList);
    }

    /**
     * 批量重置密码
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/setPwdBatch")
    @ResponseBody
    public JsonResponse setPwdBatch(@RequestParam(required = true) String ids) {
        if (CommonUtil.checkParam(ids)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String[] idsArr = ids.split(",");
        List idList = new ArrayList<>();
        for (String id : idsArr) {
            idList.add(Integer.valueOf(id));
        }
        return userService.setPwdBatch(idList);
    }

    /**
     * 重置密码
     *
     * @param id
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/setPwd", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse setPwd(@RequestParam(required = true) Integer id, @RequestParam(required = true) String pwd) {
        if (CommonUtil.checkParam(id, pwd)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (pwd.length() < 6) {
            return JsonResponseTool.paramErr("密码最少6位数");
        }
        return userService.setPwd(id, pwd);
    }

    /**
     * 用户留言
     *
     * @param userId
     * @param content
     * @return
     */
    @RequestMapping("/leaveWord")
    @ResponseBody
    public JsonResponse leaveWord(@RequestParam Integer userId, @RequestParam String content) {
        Map map = userService.leaveWord(userId, content);
        return CommonUtil.jsonResponse(map);
    }


    /**
     * 注册用户审核
     *
     * @param userId
     * @param status(0待审核1审核通过2审核不通过)
     * @return
     */
    @RequestMapping("/registerAudit")
    @ResponseBody
    public JsonResponse registerAudit(@RequestParam Integer userId, @RequestParam Integer status) {
        Map map = userService.registerAudit(userId, status);
        return CommonUtil.jsonResponse(map);
    }

}
