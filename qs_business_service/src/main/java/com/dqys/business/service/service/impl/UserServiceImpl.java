package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserTagMapper;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.auth.orm.query.TUserQuery;
import com.dqys.auth.orm.query.TUserTagQuery;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yvan on 16/6/29.
 */
@Repository
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TUserTagMapper tUserTagMapper;
    @Autowired
    private TCompanyInfoMapper tCompanyInfoMapper;

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
        List<TUserInfo> tUserInfoList = tUserInfoMapper.queryList(tUserQuery);
        List<UserListDTO> userListDTOList = new ArrayList<>();
        if (tUserInfoList == null) {
            // 没有符合条件的数据
            resultMap.put("data", null);
            resultMap.put("total", 0);
            return JsonResponseTool.success(resultMap);
        } else {
            for(TUserInfo tUserInfo : tUserInfoList){
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
        Integer result = tUserInfoMapper.insertSelective(userInfo);
        if (result != null && result > 0) {
            Integer userInfoId = userInfo.getId();
            TUserTag userTag = UserServiceUtils.toTUserTag(data, userInfoId);
            result = tUserTagMapper.insertSelective(userTag);
            if (result != null && result > 0) {
                return JsonResponseTool.success(userInfoId);
            }
        }
        return JsonResponseTool.failure("添加失败");
    }

    @Override
    public JsonResponse update(UserInsertDTO userInsertDTO) {
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getId(), userInsertDTO.getCompanyId())) {
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
        Integer result = tUserInfoMapper.insertSelective(userInfo);
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
    public JsonResponse statusBatch(String ids, Integer id) {
        Integer result = tUserInfoMapper.queryUpdateStatus(ids, id);
        if (result > 0) {
            return JsonResponseTool.success(null);
        } else {
            return JsonResponseTool.failure("修改失败");
        }
    }

    /**
     * 将单条用户信息转化成列表DTO
     *
     * @param tUserInfo
     * @return
     */
    private UserListDTO _get(TUserInfo tUserInfo) {
        TCompanyInfo tCompanyInfo = new TCompanyInfo();
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

}
