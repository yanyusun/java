package com.dqys.business.controller;

import com.dqys.business.service.constant.UserStatusTypeEnum;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.excel.UserExcelUtil;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.HashMap;
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
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    /**
     * @api {GET} ../ listDataCount
     * @apiName listData
     * @apiGroup User-Object
     * @apiDescription 二级导航数据返回结果对象
     *
     * @apiSuccess {number} [plateform] 平台总公司用户
     * @apiSuccess {number} [plateformCompany] 平台分公司用户
     * @apiSuccess {number} [entrustTotal] 委托用户总数
     * @apiSuccess {number} [entrustAgency] 机构用户
     * @apiSuccess {number} [entrustSingle] 个人用户
     * @apiSuccess {number} [disposeTotal] 处置方全部用户
     * @apiSuccess {number} [urge] 催收方用户
     * @apiSuccess {number} [Judicial] 律所用户
     * @apiSuccess {number} [dispose] 中介用户
     */


    /**
     * @api {GET} http://{url}/api/user/listData 二级导航统计
     * @apiName listData
     * @apiGroup User
     * @apiDescription 二级导航数据统计
     *
     * @apiUse JsonResponse
     * @apiSuccess {listDataCount} data 返回数据
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
     * @api {GET} http://{url}/api/user/getInit 获取初始化配置
     * @apiName getInit
     * @apiGroup User
     * @apiDescription 增改页面初始化配置
     *
     * @apiUse JsonResponse
     * @apiSuccess {json} data 返回数据(以下为json返回内容)
     *
     * @apiSuccess {Object} [companyInfo] 平台总公司用户
     */
    @RequestMapping(value = "/getInit")
    @ResponseBody
    public JsonResponse getListInit() {
        Map resultMap = new HashMap<>();

        resultMap.put("companyInfo", companyService.get(UserSession.getCurrent().getUserId())); // 公司信息
        resultMap.put("userStatus", UserStatusTypeEnum.values()); // 用户状态
        resultMap.put("userType", SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE)); //账号类型
        resultMap.put("roleType", SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE)); // 角色类型

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
                userInsertDTO.getDuty(), userInsertDTO.getWechat(), userInsertDTO.getMobile(),
                userInsertDTO.getEmail(), userInsertDTO.getApartmentId(), userInsertDTO.getOccupation(),
                userInsertDTO.getAreaId(), userInsertDTO.getRoleId())) {
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
     * 修改用户
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
                userInsertDTO.getAreaId(), userInsertDTO.getRoleId(), userInsertDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return userService.update(userInsertDTO);
    }

    /**
     * 删除用户
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
     * @param id
     * @return
     */
    @RequestMapping(value = "/statusBatch")
    @ResponseBody
    public JsonResponse statusBatch(@RequestParam(required = true) String ids,
                                    @RequestParam(required = true) Integer id) {
        if (CommonUtil.checkParam(ids, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return userService.statusBatch(ids, id);
    }

    /**
     * 成员信息导入
     * @param file
     * @return
     */
    @RequestMapping(value = "/userExcel")
    @ResponseBody
    public JsonResponse userExcel(@RequestParam MultipartFile file){
        if(CommonUtil.checkParam(file)){
            return JsonResponseTool.paramErr("未上传文件");
        }
        Map<String, Object> map = UserExcelUtil.upLoadUserExcel(file);
        if(map.get("error") == null || map.get("").equals("")){
            return JsonResponseTool.failure("");
        }else{
            // 返回CODE

        }
        return null;
    }

}
