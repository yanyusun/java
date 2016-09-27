package com.dqys.business.controller;

import com.dqys.business.service.constant.UserStatusTypeEnum;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.service.UserService;
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
    @Autowired
    @Qualifier("b_companyService")
    private CompanyService companyService;

    /**
     * 二级导航统计
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
     * @api {GET} http://{url}/api/user/listUser 获取公司
     * @apiName listUser
     * @apiGroup User
     * @apiDescription 暂未补充
     * @apiSuccess {UserDTO} data 用户信息
     * @apiUse UserDTO
     */
    @RequestMapping(value = "/listUser")
    @ResponseBody
    public JsonResponse listUser() {

        TSysProperty property = SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM);
        String reg = property.getPropertyValue();

        return null;
    }

    /**
     * @api {GET} http://{url}/api/user/getInit 获取初始化列表
     * @apiName getInit
     * @apiGroup User
     * @apiSuccess {CompanyDTO} companyInfo 公司信息
     * @apiUse CompanyDTO
     * @apiSuccess {SelectonDTO} userStatus 用户状态集
     * @apiUse SelectonDTO
     * @apiSuccess {Property} userType 用户账号类型
     * @apiSuccess {Property} roleType 用户角色类型
     * @apiUse PropertyDTO
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
     * @api {GET} http://{url}/api/user/list 用户列表
     * @apiName list
     * @apiGroup User
     * @apiUse UserListQuery
     * @apiSuccess {UserList} data
     * @apiUse UserList
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonResponse list(@ModelAttribute UserListQuery userListQuery) {
        return userService.queryList(userListQuery);
    }

    /**
     * @api {POST} http://{url}/api/user/add 增加用户
     * @apiName add
     * @apiGroup User
     * @apiUse UserInsert
     * @apiSuccess {number} data 添加成功后的ID
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
     * @api {POST} http://{url}/api/user/get 查看用户信息
     * @apiName get
     * @apiGroup User
     * @apiParam {number} id 用户ID
     * @apiSuccess {UserInsertDTO} data 用户信息
     * @apiUse UserInsertDTO
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
     * @api {POST} http://{url}/api/user/update 修改用户信息
     * @apiName update
     * @apiGroup User
     * @apiUse UserInsert
     * @apiSuccess {number} data 添加后的Id
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
     * @param id
     * @return
     * @api {get} http://{url}/api/user/delete 删除用户信息
     * @apiName delete
     * @apiGroup User
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
     * @apiIgnore {GET} http://{url}/api/user/assignedBatch 批量分配<暂时不处理>
     * @apiName assignedBatch
     * @apiGroup User
     * @apiParam {string} ids 操作成员ID的集合,","分隔
     * @apiParam {number} id 交付给某人
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
     * @api {GET} http://{url}/api/user/statusBatch 批量设置状态
     * @apiName statusBatch
     * @apiGroup User
     * @apiParam {string} ids 操作成员ID的集合,","分隔
     * @apiParam {number} status 状态
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
     * @api {GET} http://{url}/api/user/userExcel 成员信息excel导入
     * @apiName userExcel
     * @apiGroup User
     * @apiParam {String} file 上传的excel文件
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
     * @api {GET} http://{url}/api/user/sendMsg 消息提醒(暂时废除)
     * @apiName sendMsg
     * @apiGroup User
     * @apiParam {string} ids 操作用户ID集合,","分隔
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
     * @api {GET} http://{url}/api/user/setPwdBatch 批量重置密码
     * @apiName setPwdBatch
     * @apiGroup User
     * @apiParam {string} ids 操作用户ID集合,","分隔
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
     * @return
     * @api {POST} http://{url}/api/user/setPwd 重置密码
     * @apiName setPwd
     * @apiGroup User
     * @apiParam {number} id 被操作用户
     * @apiParam {string} pwd 新密码
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

}
