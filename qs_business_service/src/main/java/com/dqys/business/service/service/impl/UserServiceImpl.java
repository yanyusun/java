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
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.business.service.query.user.UserListQuery;
import com.dqys.business.service.service.UserService;
import com.dqys.business.service.utils.user.UserUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private OrganizationMapper organizationMapper;


    @Override
    public Integer count(UserListQuery userListQuery) {
        TUserQuery tUserQuery = toTUserQuery(userListQuery);
        return tUserInfoMapper.queryCount(tUserQuery);
    }

    @Override
    public List<UserListDTO> queryList(UserListQuery query) {
        TUserQuery tUserQuery = toTUserQuery(query);
        tUserQuery.setIsPaging(true); // 开启分页
        List<TUserInfo> tUserInfoList = tUserInfoMapper.queryList(tUserQuery);
        if (tUserInfoList == null) {
            // 没有符合条件的数据
            return null;
        } else {
            List<UserListDTO> userListDTOList = new ArrayList<>();
            tUserInfoList.forEach(tUserInfo -> {
                userListDTOList.add(_get(tUserInfo));
            });
            return userListDTOList;
        }
    }

    @Override
    public UserInsertDTO get(Integer id) {
        TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(id);
        if (tUserInfo == null) {
            return null;
        } else {
            // todo 理论上当前只有一条
            TUserTag tUserTag = new TUserTag();
            List<TUserTag> tUserTagList = tUserTagMapper.selectByUserId(id);
            if (tUserTagList.size() > 0) {
                tUserTag = tUserTagList.get(0);
            }
            TCompanyInfo tCompanyInfo = new TCompanyInfo();
            if (tUserInfo.getCompanyId() != null) {
                tCompanyInfo = tCompanyInfoMapper.selectByPrimaryKey(tUserInfo.getCompanyId());
            }
            Organization organization = new Organization();
            return UserUtils.toUserInsertDTO(tUserInfo, tUserTag, tCompanyInfo, organization);
        }
    }

    @Override
    public Integer add(UserInsertDTO data) {
        if (data == null) {
            return null;
        }

        return null;
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
        return UserUtils.toUserListDTO(tUserInfo, tCompanyInfo);
    }

    /**
     * 转化列表查询搜索条件
     *
     * @param query
     * @return
     */
    private TUserQuery toTUserQuery(UserListQuery query) {
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
                return null;
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
                    return null;
                }
                tUserQuery.setCompanyIds(companyIds);
            }
        }else{
            TUserInfo tUserInfo = getCurrentUser();
            if(tUserInfo != null){
                tUserQuery.setCompanyId(tUserInfo.getCompanyId());
            }
        }
        tUserQuery.setStatus(query.getStatus());
        tUserQuery.setStatuss(query.getStatuss());
        tUserQuery.setNameLike(query.getName());

        return tUserQuery;
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
