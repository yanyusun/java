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
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.query.company.OrganizationQuery;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.user.UserFileDTO;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.excel.UserExcelUtil;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.mapper.facade.TAreaMapper;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.TArea;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_INTERMEDIARY)); // 中介
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_ENTRUST)); // 委托方
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_URGE)); // 催收方
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_LAW)); // 律所
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_PLATFORM)); // 平台
                } else {
                    // 个人
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_ENTRUST)); // 委托方
                    userTypes.add(NoSQLWithRedisTool.getValueObject(KeyEnum.U_TYPE_COMMON)); // 用户
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

        if (CommonUtil.isManage()) {
            // 总管理员
            if (CommonUtil.checkNullParam(query.getProvince(), query.getCity(), query.getDistrict())) {
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
            }
        } else {
            TUserInfo tUserInfo = getCurrentUser();
            if (tUserInfo != null) {
                tUserQuery.setCompanyId(tUserInfo.getCompanyId());
            }
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
            for (TUserInfo tUserInfo : tUserInfoList) {
                userListDTOList.add(_get(tUserInfo));
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
    public JsonResponse add(UserInsertDTO data) {
        if (data == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        TUserInfo userInfo = UserServiceUtils.toTUserInfo(data);
        // 掩码初始化
        userInfo.setSalt(RandomStringUtils.randomAlphabetic(6));
        try {
            // 密码初始化
            userInfo.setPassword(SignatureTool.md5Encode(INIT_PASSSWORD, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 保存头像信息
            FileTool.saveFileSync(data.getAvg());
        } catch (IOException e) {
            e.printStackTrace();
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

    @Override
    public JsonResponse update(UserInsertDTO userInsertDTO) {
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getId())) {
            return JsonResponseTool.paramErr("参数错误");
        }
        TUserTag tUserTag = new TUserTag();
        List<TUserTag> tUserTagList = tUserTagMapper.selectByUserId(userInsertDTO.getId());
        if (tUserTagList.size() > 0) {
            tUserTag = tUserTagList.get(0);
        }
        if (tUserTag == null) {
            return JsonResponseTool.failure("修改失败");
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
        if (CommonUtil.checkParam(uId) || tUserInfoMapper.selectByPrimaryKey(uId) == null) {
            return JsonResponseTool.paramErr("参数错误");
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
                try {
                    // 密码初始化
                    tUserInfo1.setPassword(SignatureTool.md5Encode(INIT_PASSSWORD, null));
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
        TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(id);
        if (tUserInfo == null) {
            return JsonResponseTool.failure("参数错误");
        }
        TUserInfo tUserInfo1 = new TUserInfo();
        tUserInfo1.setId(tUserInfo.getId());
        try {
            // 密码加密
            tUserInfo1.setPassword(SignatureTool.md5Encode(pwd, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer result = tUserInfoMapper.updateByPrimaryKeySelective(tUserInfo1);
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
        if (tUserInfo.getCompanyId() != null) {
            tCompanyInfo = tCompanyInfoMapper.selectByPrimaryKey(tUserInfo.getCompanyId());
        }
        return UserServiceUtils.toUserListDTO(tUserInfo, tCompanyInfo);
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
    public JsonResponse excelImport_tx(String file) {
        if (CommonUtil.checkParam(file)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        CompanyDetailInfo companyDetailInfo = tCompanyInfoMapper.get(UserSession.getCurrent().getUserId());
        if (companyDetailInfo == null) {
            return JsonResponseTool.paramErr("您不是管理员,没有权限导入成员");
        }

        Map<String, Object> map = UserExcelUtil.upLoadUserExcel(file);
        if (map.get("result") == null || map.get("result").equals("error")) {
            return JsonResponseTool.failure(map.get("data").toString());
        } else {
            // 返回CODE
            List<UserFileDTO> userFileDTOList = (List<UserFileDTO>) map.get("userFileDTOs");
            if (userFileDTOList == null || userFileDTOList.size() == 0) {
                return JsonResponseTool.failure("没有数据添加");
            }
            for (UserFileDTO userFileDTO : userFileDTOList) {
                // 用户基础信息
                TUserInfo userInfo = UserServiceUtils.toUserInfo(userFileDTO);
                userInfo.setCompanyId(companyDetailInfo.getCompanyId());
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
                Integer userAdd = tUserInfoMapper.insertSelective(userInfo);
                if (CommonUtil.checkResult(userAdd)) {
                    return JsonResponseTool.failure("添加用户信息失败");
                }
                Integer userId = userInfo.getId();
                userTag.setUserId(userId);
                Integer tagAdd = tUserTagMapper.insertSelective(userTag);
                if (CommonUtil.checkResult(tagAdd)) {
                    return JsonResponseTool.failure("添加用户信息失败");
                }
            }
        }
        return JsonResponseTool.success("添加成功");
    }

}
