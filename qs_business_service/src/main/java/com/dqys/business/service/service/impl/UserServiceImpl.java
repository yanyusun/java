package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.auth.orm.query.TUserTagQuery;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.query.company.OrganizationQuery;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.excel.ExcelMessage;
import com.dqys.business.service.dto.user.UserFileDTO;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.service.OperLogService;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.excel.UserExcelUtil;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.*;
import com.dqys.core.mapper.facade.TAreaMapper;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TArea;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/29.
 */
@Repository("b_loginService")
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;
    @Autowired
    private TAreaMapper areaMapper;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private OperLogService operLogService;

    public static final String INIT_PASSSWORD = "123456";

    @Override
    public JsonResponse queryList(UserListQuery query) {
        Map<String, Object> resultMap = new HashMap<>();

        TUserQuery tUserQuery = new TUserQuery();
        if (CommonUtil.checkNullParam(query.getAccountType(), query.getType(), query.getRole())) {
            TUserTagQuery tUserTagQuery = new TUserTagQuery();

            tUserTagQuery.setUserType(query.getAccountType());
            tUserTagQuery.setRole(query.getRole());
            if (query.getType() != null) {
                List<Integer> userTypes = new ArrayList<>();
                if (query.getType().equals(1)) {
                    // 企业
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_INTERMEDIARY)
                                    .getPropertyValue())); // 中介
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_ENTRUST)
                                    .getPropertyValue())); // 委托方
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                                    .getPropertyValue())); // 催收方
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                                    .getPropertyValue())); // 律所
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM)
                                    .getPropertyValue())); // 平台
                } else {
                    // 个人
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_ENTRUST)
                                    .getPropertyValue())); // 委托方
                    userTypes.add(Integer.valueOf(
                            SysPropertyTool.getProperty(
                                    SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_COMMON)
                                    .getPropertyValue())); // 用户
                }
                tUserTagQuery.setUserTypes(userTypes);
            }

            List<TUserTag> tUserTagList = tUserTagMapper.selectByQuery(tUserTagQuery);
            List<Integer> ids = new ArrayList<>();
            tUserTagList.forEach(tUserTag -> {
                ids.add(tUserTag.getUserId());
            });
            if (ids.isEmpty()) {
                // 没有符合条件的数据
                resultMap.put("data", null);
                resultMap.put("total", 0);
                return JsonResponseTool.success(resultMap);
            }
            tUserQuery.setIds(ids);
        }
        TUserInfo tUserInfo = getCurrentUser();
        if (tUserInfo != null) {
            tUserQuery.setCompanyId(tUserInfo.getCompanyId());
        }
        //组织架构中不需要
        if (query.getModule() != null && query.getModule() == 1) {
            // 总管理员

                List<Integer> companyIds = new ArrayList<>();

                CompanyQuery companyQuery = new CompanyQuery();
                companyQuery.setProvince(query.getProvince());
                companyQuery.setCity(query.getCity());
                companyQuery.setDistrict(query.getDistrict());

                List<TCompanyInfo> tCompanyInfoList = tCompanyInfoMapper.queryList(companyQuery);
                if (tCompanyInfoList != null) {
                    tCompanyInfoList.forEach(tCompanyInfo -> {
                        companyIds.add(tCompanyInfo.getId());
                    });
                }
                if (companyIds.isEmpty()) {
                    // 没有符合条件的数据
                    resultMap.put("data", null);
                    resultMap.put("total", 0);
                    return JsonResponseTool.success(resultMap);
                }
                tUserQuery.setCompanyIds(companyIds);
                tUserQuery.setCompanyId(null);
            }
        tUserQuery.setStatus(query.getStatus());
        tUserQuery.setStatuss(query.getStatuss());
        tUserQuery.setNameLike(query.getName());

        tUserQuery.setIsPaging(true); // 开启分页
        if (query.getPageCount() > 1) {
            tUserQuery.setPageSize(query.getPageCount());
        } else {
            tUserQuery.setPageSize(SysProperty.DEFAULT_PAGE_SIZE);
        }
        if (query.getPage() > 1) {
            tUserQuery.setStartPageNum((query.getPage() - 1) * query.getPageCount());
        } else {
            tUserQuery.setStartPageNum(0);
        }
        List<TUserInfo> tUserInfoList = tUserInfoMapper.queryList(tUserQuery);
        List<UserListDTO> userListDTOList = new ArrayList<>();
        if (tUserInfoList == null) {
            // 没有符合条件的数据
            resultMap.put("data", null);
            resultMap.put("total", 0);
            return JsonResponseTool.success(resultMap);
        } else {
            for (TUserInfo info : tUserInfoList) {
                userListDTOList.add(_get(info));
            }
        }
        Integer count = tUserInfoMapper.queryCount(tUserQuery);

        resultMap.put("data", userListDTOList);
        resultMap.put("total", count);
        return JsonResponseTool.success(resultMap);
    }

    @Override
    public JsonResponse get(Integer id) {
        TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(id);
        if (tUserInfo == null) {
            return JsonResponseTool.paramErr("参数错误");
        } else {
            // todo 理论上当前只有一条
            TUserTag tUserTag = new TUserTag();
            List<TUserTag> tUserTagList = tUserTagMapper.selectByUserId(id);
            if (tUserTagList.size() > 0) {
                tUserTag = tUserTagList.get(0);
            }
            return JsonResponseTool.success(UserServiceUtils.toUserInsertDTO(tUserInfo, tUserTag));
        }
    }

    @Override
    public TCompanyInfo getCompanyByUserId(Integer id) {
        if (id == null) {
            return null;
        }
        TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(id);
        if (userInfo == null) {
            return null;
        }
        return tCompanyInfoMapper.selectByPrimaryKey(userInfo.getCompanyId());
    }

    @Override
    public Map leaveWord(Integer userId, String content) {
        Integer uid = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        map.put("result", "no");
        Map userC = coordinatorMapper.getUserAndCompanyByUserId(userId);
        Map operC = coordinatorMapper.getUserAndCompanyByUserId(uid);
        SmsUtil smsUtil = new SmsUtil();
        Integer result = messageService.add(MessageUtils.transMapToString(operC, "realName") + "给您的留言信息", content, uid, userId, "", MessageEnum.SERVE.getValue(), null, "");
        if (result > 0) {
            smsUtil.sendSms(SmsEnum.LEAVE_WORD.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                    MessageUtils.transMapToString(operC, "companyName"), RoleTypeEnum.get(MessageUtils.transMapToInt(operC, "rold")), MessageUtils.transMapToString(operC, "realName"));
            map.put("result", "yes");
        } else {
            map.put("msg", "请稍后再试");
        }
        return map;
    }

    @Override
    public Map registerAudit(Integer userId, Integer status) {
        Integer operId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        map.put("result", "no");
        TUserInfo info = tUserInfoMapper.selectByPrimaryKey(userId);
        if (info != null && info.getCompanyId() != null) {
            TCompanyInfo company = tCompanyInfoMapper.selectByPrimaryKey(info.getCompanyId());
            boolean flag = false;
            if (company != null) {
                String text = "";
                if (company.getIsAuth() == 0 && status == 1) {
                    text = "成功";
                    company.setIsAuth(status);
                    flag = true;
                } else if (company.getIsAuth() == 0 && status == 2) {
                    text = "失败";
                    company.setIsAuth(status);
                    flag = true;
                } else {
                    map.put("msg", "重复审核");
                }
                if (flag && tCompanyInfoMapper.updateByPrimaryKeySelective(company) > 0) {
                    map.put("result", "yes");
                    Map userC = coordinatorMapper.getUserAndCompanyByUserId(userId);
                    SmsUtil sms = new SmsUtil();
                    String content = sms.sendSms(SmsEnum.REGISTER_AUDIT_RESULT.getValue(), MessageUtils.transMapToString(userC, "mobile"),
                            MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(userC, "companyName"), text);
                    messageService.add("注册审核结果答复", content, operId, userId, "", MessageEnum.SERVE.getValue(), null, "");
                }
            }
        } else {
            map.put("msg", "参数查询有误");
        }
        return map;
    }

    @Override
    public Map activateReminder(List<Integer> userIds) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        map.put("result", "no");
        Integer status = 0;//帐号未激活的用户
        List<TUserInfo> infos = tUserInfoMapper.findAccountByStatus(userIds, status);
        if (infos == null || infos.size() == 0) {
            map.put("msg", "未发现需要激活的帐号");
        } else {
            map.put("result", "yes");
            for (TUserInfo info : infos) {
                if (info != null) {
                    sendActivate(userId, info.getId());
                }
            }
        }
        return map;
    }

    @Override
    public Map updateAccountUse(List<Integer> userIds, Integer useStatus) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
        map.put("result", "no");
        if (userIds == null || userIds.size() == 0) {
            map.put("msg", "请选择人员");
            return map;
        }
        if (userIds != null && userIds.size() == 1 && userIds.contains(userId)) {
            map.put("msg", "不能对自己进行操作");
            return map;
        }
        if (userIds.contains(userId)) {
            userIds.remove(userId);
        }
        tUserInfoMapper.updateAccountUse(userIds, useStatus);
        map.put("result", "yes");
        return map;
    }

    //邮箱激活通知
    private void sendActivate(Integer sendUser, Integer receiveUser) {
        Map userC = coordinatorMapper.getUserAndCompanyByUserId(receiveUser);//接收者
        Map operC = coordinatorMapper.getUserAndCompanyByUserId(sendUser);//发送者
        SmsUtil smsUtil = new SmsUtil();
        String content = smsUtil.sendSms(SmsEnum.ACTIVATE_ACCOUNT.getValue(), MessageUtils.transMapToString(userC, "mobile"),
                MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(operC, "realName"), MessageUtils.transMapToString(userC, "email"));
//        messageService.add("帐号激活提醒", content, sendUser, receiveUser, "", MessageEnum.SERVE.getValue(), null, "");
    }

    @Override
    public JsonResponse add(UserInsertDTO data) {
        String checkData = UserServiceUtils.checkData(data);
        if (checkData != null) {
            return JsonResponseTool.paramErr(checkData);
        }
        JsonResponse jsp = verify(data);//格式的验证
        if (jsp.getCode() != ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            return jsp;
        }
        if (data.getAvg() != null && !data.getAvg().equals("")) {
            try {
                if (!FileTool.saveFileSync(data.getAvg())) {
                    throw new UnexpectedRollbackException("保存附件失败");
                }
            } catch (IOException e) {
                throw new UnexpectedRollbackException("保存附件异常");
            }
        }
        // 校验邮箱是否存在
        List<TUserInfo> isExist = tUserInfoMapper.verifyUser(null, null, data.getEmail());
        if (isExist != null && isExist.size() > 0) {
            return JsonResponseTool.failure("邮箱已存在");
        }
        List<TUserInfo> isExist2 = tUserInfoMapper.verifyUser(data.getAccount(), null, null);
        if (isExist2 != null && isExist2.size() > 0) {
            return JsonResponseTool.failure("帐号已存在");
        }
        TCompanyInfo companyInfo = getCompanyByUserId(UserSession.getCurrent().getUserId());
        if (companyInfo == null) {
            return JsonResponseTool.paramErr("当前用户存在数据异常");
        }
        TUserInfo userInfo = UserServiceUtils.toTUserInfo(data);
        // 掩码初始化
        userInfo.setSalt(RandomStringUtils.randomAlphabetic(6));
        try {
            // 密码初始化
            userInfo.setPassword(
                    SignatureTool.md5Encode(SignatureTool.md5Encode(INIT_PASSSWORD, "utf-8") + userInfo.getSalt(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 保存头像信息
            FileTool.saveFileSync(data.getAvg());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data.getCompanyId() != null && CommonUtil.isManage()) {
            userInfo.setCompanyId(data.getCompanyId());
        } else {
            userInfo.setCompanyId(companyInfo.getId());
        }
        Integer result = tUserInfoMapper.insertSelective(userInfo);
        if (result != null && result > 0) {
            Integer userInfoId = userInfo.getId();
            TUserTag userTag = UserServiceUtils.toTUserTag(data, userInfoId);
            result = tUserTagMapper.insertSelective(userTag);
            if (result != null && result > 0) {
                return JsonResponseTool.success(userInfoId);
            } else {
                // todo 标签增加失败,删除用户信息数据 -- 目前逻辑删除,需修复
                tUserInfoMapper.deleteByPrimaryKey(userInfoId);
            }
        }
        return JsonResponseTool.failure("添加失败");
    }

    private JsonResponse verify(UserInsertDTO dto) {
        if (!FormatValidateTool.checkMobile(dto.getMobile())) {
            return JsonResponseTool.failure("手机号格式不正确");
        }
        if (!FormatValidateTool.checkEmail(dto.getEmail())) {
            return JsonResponseTool.failure("邮箱格式不正确");
        }
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse update(UserInsertDTO userInsertDTO) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        JsonResponse jsp = verify(userInsertDTO);//格式的验证
        if (jsp.getCode() != ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            return jsp;
        }
        if (userInsertDTO.getAvg() != null && !userInsertDTO.getAvg().equals("")) {
            try {
                if (!FileTool.saveFileSync(userInsertDTO.getAvg())) {
                    throw new UnexpectedRollbackException("保存附件失败");
                }
            } catch (IOException e) {
                throw new UnexpectedRollbackException("保存附件异常");
            }
        }
        TUserTag tUserTag = new TUserTag();
        List<TUserTag> tUserTagList = tUserTagMapper.selectByUserId(userInsertDTO.getId());
        if (tUserTagList.size() > 0) {
            tUserTag = tUserTagList.get(0);
        }
        if (tUserTag == null) {
            return JsonResponseTool.failure("修改失败");
        }
        //不能自己在组织架构中修改自己的邮箱和帐号和角色，管理员有权修改其他员工的邮箱和帐号
        Map adminUser = coordinatorMapper.getAdminUser(userInsertDTO.getCompanyId());
        if (userId.toString().equals(MessageUtils.transMapToString(adminUser, "id")) && userInsertDTO.getId() != userId) {
            // 校验邮箱和帐号是否存在
            List<TUserInfo> isExist = tUserInfoMapper.verifyUser(null, null, userInsertDTO.getEmail());
            if (isExist != null && isExist.size() > 0) {
                if (isExist.get(0).getId().intValue() != tUserTag.getUserId()) {
                    return JsonResponseTool.failure("邮箱已存在");
                }
            }
            List<TUserInfo> isExist2 = tUserInfoMapper.verifyUser(userInsertDTO.getAccount(), null, null);
            if (isExist2 != null && isExist2.size() > 0) {
                if (isExist2.get(0).getId().intValue() != tUserTag.getUserId()) {
                    return JsonResponseTool.failure("帐号已存在");
                }
            }
        } else {
            userInsertDTO.setEmail(null);
            userInsertDTO.setAccount(null);
            userInsertDTO.setRoleId(null);
        }
        boolean flag = false;

        TUserInfo userInfo = UserServiceUtils.toTUserInfo(userInsertDTO);
        Integer result = tUserInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (result != null && result.equals(1)) {
            flag = true;
        }

        TUserTag userTag = UserServiceUtils.toTUserTag(userInsertDTO, userInsertDTO.getId());
        userTag.setId(tUserTag.getId());
        Integer tagResult = tUserTagMapper.updateByPrimaryKeySelective(userTag);
        if (tagResult != null && tagResult.equals(1)) {
            flag = true;
        }

        if (flag) {
            return JsonResponseTool.success(userInsertDTO.getId());
        } else {
            return JsonResponseTool.failure("修改失败");
        }
    }

    @Override
    public JsonResponse delete(Integer uId) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(uId) || tUserInfoMapper.selectByPrimaryKey(uId) == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (userId.intValue() == uId.intValue()) {
            return JsonResponseTool.failure("无法删除自己");
        }
        List<TUserTag> tags = tUserTagMapper.selectByUserId(uId);
        if (tags.size() > 0) {
            TUserTag tag = tags.get(0);
            if (tag.getRoleId().intValue() == RoleTypeEnum.ADMIN.getValue().intValue()) {
                return JsonResponseTool.failure("管理员帐号无法删除");
            }
        }
        TUserTag tUserTag = new TUserTag();
        List<TUserTag> tUserTagList = tUserTagMapper.selectByUserId(uId);
        if (tUserTagList.size() > 0) {
            tUserTag = tUserTagList.get(0);
        }
        if (tUserTag == null) {
            return JsonResponseTool.failure("参数错误");
        }

        Integer userResult = tUserInfoMapper.deleteByPrimaryKey(uId);
        if (userResult > 0) {
            operLogService.addOperLog("组织架构的用户信息删除操作", uId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.DEL_USER.getValue(), userId);
            tUserTagMapper.deleteByPrimaryKey(tUserTag.getId());
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("删除失败");
        }

    }

    @Override
    public JsonResponse assignedBatch(String ids, Integer id) {
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse statusBatch(String ids, Integer status) {
        if (ids == null || ids.length() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String[] idArr = ids.split(",");
        ArrayList idList = new ArrayList();
        for (String s : idArr) {
            idList.add(Integer.valueOf(s));
        }
        Integer result = tUserInfoMapper.queryUpdateStatus(idList, status);
        if (result > 0) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("修改失败");
        }
    }

    @Override
    public JsonResponse sendMsg(List<Integer> ids) {
        if (CommonUtil.checkParam(ids)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        for (Integer id : ids) {
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(id);
            if (tUserInfo != null) {
                // 手机号提醒
                if (tUserInfo.getMobile() != null) {
                    // TODO 设置消息体
                    RabbitMQProducerTool.addToSMSSendQueue(tUserInfo.getMobile(), "消息提请内容,请设置");
                }
            }
        }
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse setPwdBatch(List<Integer> ids) {
        if (CommonUtil.checkParam(ids)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!CommonUtil.isManage()) {
            return JsonResponseTool.failure("没有权限操作");
        }
        List<String> errList = new ArrayList<>();
        for (Integer id : ids) {
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(id);
            if (tUserInfo != null) {
                TUserInfo tUserInfo1 = new TUserInfo();
                tUserInfo1.setId(tUserInfo.getId());
                tUserInfo1.setSalt(RandomStringUtils.randomAlphabetic(6));
                try {
                    // 密码初始化
                    tUserInfo1.setPassword(SignatureTool.md5Encode(
                            SignatureTool.md5Encode(INIT_PASSSWORD, "utf-8") + tUserInfo1.getSalt(), "utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Integer result = tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo1);
                if (result < 1) {
                    String errStr = "用户: " + tUserInfo.getUserName() + " 重设密码失败";
                    errList.add(errStr);
                }
            }
        }
        return JsonResponseTool.success(errList);
    }

    @Override
    public JsonResponse setPwd(Integer id, String pwd) {
        if (CommonUtil.checkParam(id, pwd)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Integer userId = UserSession.getCurrent().getUserId();
        CompanyDetailInfo companyDetailInfo = tCompanyInfoMapper.getDetailByUserId(userId);
        if (companyDetailInfo == null || companyDetailInfo.getUserId().equals(userId)) {
            return JsonResponseTool.paramErr("您不是管理员,没有权限导入成员");
        }
        TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(id);
        if (userInfo == null) {
            return JsonResponseTool.failure("该用户不存在");
        }
        if (userInfo.getCompanyId().equals(companyDetailInfo.getCompanyId())) {
            return JsonResponseTool.failure("没有权限修改其他公司成员");
        }
        try {
            // 密码加密
            userInfo.setPassword(SignatureTool.md5Encode(
                    SignatureTool.md5Encode(INIT_PASSSWORD, "utf-8") + userInfo.getSalt(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer result = tUserInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (result < 1) {
            return JsonResponseTool.failure("设置失败");
        } else {
            return JsonResponseTool.success(id);
        }
    }

    /**
     * 将单条用户信息转化成列表DTO
     *
     * @param tUserInfo
     * @return
     */

    private UserListDTO _get(TUserInfo tUserInfo) {
        TCompanyInfo tCompanyInfo = null;
        TUserTag userTag = null;
        String apartment = null;
        Integer total = null;
        Integer going = null;
        if (tUserInfo.getCompanyId() != null) {
            tCompanyInfo = tCompanyInfoMapper.selectByPrimaryKey(tUserInfo.getCompanyId());
            Map<String, Object> lenderMap = coordinatorMapper.getTaskRatio(
                    tUserInfo.getCompanyId(), tUserInfo.getId(), ObjectTypeEnum.LENDER.getValue());
            Map<String, Object> assetMap = coordinatorMapper.getTaskRatio(
                    tUserInfo.getCompanyId(), tUserInfo.getId(), ObjectTypeEnum.ASSETPACKAGE.getValue());
            total = Integer.valueOf(String.valueOf(lenderMap.get("total")).trim())
                    + Integer.valueOf(String.valueOf(assetMap.get("total")).trim());
            going = total - Integer.valueOf(String.valueOf(lenderMap.get("finish")).trim())
                    - Integer.valueOf(String.valueOf(assetMap.get("finish")).trim());
        }
        List<TUserTag> tagList = tUserTagMapper.selectByUserId(tUserInfo.getId());
        if (tagList != null && tagList.size() > 0) {
            userTag = tagList.get(0);
            if (userTag.getApartmentId() != null) {
                OrganizationQuery query = new OrganizationQuery();
                query.setId(userTag.getApartmentId());
                List<Organization> organization = organizationMapper.list(query);
                if (organization != null && organization.size() > 0) {
                    apartment = organization.get(0).getName();
                }
            }
        }

        return UserServiceUtils.toUserListDTO(tUserInfo, tCompanyInfo, userTag, apartment, total, going);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    private TUserInfo getCurrentUser() {
        Integer userId = UserSession.getCurrent().getUserId();
        if (userId != null) {
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(userId);
            if (tUserInfo != null) {
                return tUserInfo;
            }
        }
        return null;
    }

    @Override
    public JsonResponse excelImport_tx(String file, Integer sendSmsStatus, Integer accountStatus) throws Exception {
        if (CommonUtil.checkParam(file)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        CompanyDetailInfo companyDetailInfo = tCompanyInfoMapper.getDetailByUserId(UserSession.getCurrent().getUserId());
        if (companyDetailInfo == null) {
            return JsonResponseTool.paramErr("您不是管理员,没有权限导入成员");
        }

        Map<String, Object> map = UserExcelUtil.upLoadUserExcel(file, tUserInfoMapper, accountStatus);
        if (map.get("result") == null || map.get("result").equals("error")) {
            List<ExcelMessage> error = (List<ExcelMessage>) map.get("data");
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setCode(ResponseCodeEnum.FAILURE.getValue());
            jsonResponse.setMsg("格式内容出错");
            jsonResponse.setData(error);
            return jsonResponse;
        } else {
            // 返回CODE
            List<UserFileDTO> userFileDTOList = (List<UserFileDTO>) map.get("userFileDTOs");
            if (userFileDTOList == null || userFileDTOList.size() == 0) {
                return JsonResponseTool.failure("没有数据添加");
            }
            List<Map<String, Object>> users = new ArrayList<>();//所有需要短信通知的用户
            StringBuffer buff = new StringBuffer();//成功的人员
            StringBuffer buffTotal = new StringBuffer();//导入总人员
            for (UserFileDTO userFileDTO : userFileDTOList) {
                //用于短信通知
                Map<String, Object> user = new HashMap<>();
                user.put("mobile", userFileDTO.getMobile() == null ? "" : userFileDTO.getMobile());
                user.put("realName", userFileDTO.getRealName() == null ? "" : userFileDTO.getRealName());
                user.put("email", userFileDTO.getEmail() == null ? "" : userFileDTO.getEmail());
                users.add(user);
                // 用户基础信息
                TUserInfo userInfo = UserServiceUtils.toUserInfo(userFileDTO);
                userInfo.setCompanyId(companyDetailInfo.getCompanyId());
                userInfo.setPassword(SignatureTool.md5Encode(SignatureTool.md5Encode(INIT_PASSSWORD, "utf-8") + userInfo.getSalt(), "utf-8"));//默认密码
                // 用户身份信息
                TUserTag userTag = UserServiceUtils.toUserTag(userFileDTO);
                userTag.setUserType(companyDetailInfo.getType().byteValue());
                if (userFileDTO.getJoinAt() != null) {
                    userTag.setCreateAt(userFileDTO.getJoinAt());
                }
                TArea area = areaMapper.getByName(userFileDTO.getDutyArea());
                if (area != null) {
                    userTag.setDutyArea(area.getValue());
                }
                OrganizationQuery organizationQuery = new OrganizationQuery();
                organizationQuery.setCompanyId(companyDetailInfo.getCompanyId());
                organizationQuery.setName(userFileDTO.getApartment());
                List<Organization> organizationList = organizationMapper.list(organizationQuery);
                if (organizationList != null && organizationList.size() > 0) {
                    userTag.setApartmentId(organizationList.get(0).getId());
                } else {
                    Organization organization = new Organization();
                    organization.setType(OrganizationTypeEnum.apartment.name());
                    organization.setName(userFileDTO.getApartment());
                    organization.setCompanyId(companyDetailInfo.getCompanyId());
                    organization.setUserId(companyDetailInfo.getUserId());
                    Integer result = organizationMapper.insert(organization);
                    if (CommonUtil.checkResult(result)) {
                        return JsonResponseTool.failure("添加部门信息时失败");
                    } else {
                        Integer id = organization.getId();
                        userTag.setApartmentId(id);
                    }
                }
                buffTotal.append(userInfo.getRealName() + ";");
                //进行数据入库
                String result = userAddOrEdit(userInfo, userTag, accountStatus);
                if ("yes".equals(result)) {
                    buff.append(userInfo.getRealName() + ";");
                }
            }
            if (sendSmsStatus == 1) {
                //发送帐号激活通知
                SmsUtil smsUtil = new SmsUtil();
                Map operC = coordinatorMapper.getUserAndCompanyByUserId(UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId());//发送邀请人
                for (Map m : users) {
                    smsUtil.sendSms(SmsEnum.ACTIVATE_ACCOUNT.getValue(), MessageUtils.transMapToString(m, "mobile"),
                            MessageUtils.transMapToString(m, "realName"), MessageUtils.transMapToString(operC, "realName"),
                            MessageUtils.transMapToString(m, "email"));
                }
            }

            operLogService.addOperLog("导入总人员：" + buffTotal.toString() + "//其中导入成功人员：" + buff.toString(), null, ObjectTypeEnum.USER_INFO.getValue(),
                    UserInfoEnum.ADD_COMMON_USER.getValue(), UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId());//操作日志
        }
        return JsonResponseTool.success("操作成功");
    }

    private String userAddOrEdit(TUserInfo userInfo, TUserTag userTag, Integer accountStatus) {
        TUserInfo info = null;
        boolean flag = true;//是否新增用户信息（默认：是）
        int result = 0;
        if (accountStatus == 1) {
            //修改操作
            List<TUserInfo> infos = tUserInfoMapper.verifyUser(userInfo.getAccount(), null, userInfo.getEmail());
            if (infos != null & infos.size() > 0) {
                flag = false;
                info = infos.get(0);
                if (info.getAccount().equals(userInfo.getAccount()) && info.getEmail().equals(userInfo.getEmail())) {
                    userInfo.setId(info.getId());
                    result = tUserInfoMapper.updateByPrimaryKeySelective(userInfo);
                    List<TUserTag> tags = tUserTagMapper.selectByUserId(userInfo.getId());
                    if (tags != null && tags.size() > 0) {
                        userTag.setId(tags.get(0).getId());
                        tUserTagMapper.updateByPrimaryKeySelective(userTag);
                    }
                }
            }
        }
        //添加操作
        if (flag) {
            result = tUserInfoMapper.insertSelective(userInfo);
            if (result > 0) {
                Integer userId = userInfo.getId();
                userTag.setUserId(userId);
                tUserTagMapper.insertSelective(userTag);
            }
        }
        if (result > 0) {
            return "yes";
        }
        return "no";
    }

}
