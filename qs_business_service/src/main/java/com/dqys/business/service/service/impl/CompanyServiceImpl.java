package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.company.OrganizationMapper;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.query.company.OrganizationQuery;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.business.service.constant.OrganizationTypeEnum;
import com.dqys.business.service.dto.company.CompanyDTO;
import com.dqys.business.service.dto.company.CompanyRelationDTO;
import com.dqys.business.service.dto.company.OrganizationInsertDTO;
import com.dqys.business.service.service.CompanyService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.ServiceResult;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.core.utils.SysPropertyTool;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/6/30.
 */
@Repository("b_companyService")
@Primary
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;
    @Autowired
    private CompanyRelationMapper companyRelationMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;

    @Override
    public List<CompanyDTO> listByType(Integer type) {
        List<TCompanyInfo> companyInfoList = companyInfoMapper.listByType(type);
        return CompanyServiceUtils.toCompanyDTO(companyInfoList);
    }

    @Override
    public JsonResponse listOrganization(Integer companyId, OrganizationTypeEnum organizationTypeEnum) {
        OrganizationQuery organizationQuery = new OrganizationQuery();

        organizationQuery.setCompanyId(companyId);
        organizationQuery.setType(organizationTypeEnum.name());

        return JsonResponseTool.success(
                CompanyServiceUtils.toOrganizationDTO(organizationMapper.list(organizationQuery)));
    }

    @Override
    public CompanyDTO get(Integer companyId) {
        if (companyId == null) {
            return null;
        }
        TCompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(companyId);
        if (companyInfo != null) {
            return CompanyServiceUtils.toCompanyDTO(companyInfo);
        }
        return null;
    }

    @Override
    public CompanyDTO getByUserId(Integer userId) {
        if (CommonUtil.checkParam(userId)) {
            return null;
        }
        TUserInfo tUserInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (tUserInfo != null && tUserInfo.getCompanyId() != null) {
            return CompanyServiceUtils.toCompanyDTO(
                    companyInfoMapper.selectByPrimaryKey(tUserInfo.getCompanyId()));
        }
        return null;
    }

    @Override
    public JsonResponse addOrganization(OrganizationInsertDTO organizationInsertDTO) {
        if (CommonUtil.checkParam(organizationInsertDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Organization organization = CompanyServiceUtils.toOrganization(organizationInsertDTO);
        Integer result = organizationMapper.insert(organization);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("添加失败");
        } else {
            return JsonResponseTool.success(organization.getId());
        }
    }

    @Override
    public JsonResponse deleteOrganization(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }

        return CommonUtil.responseBack(organizationMapper.deleteByPrimaryKey(id));
    }

    @Override
    public JsonResponse updateOrganization(OrganizationInsertDTO organizationInsertDTO) {
        if (CommonUtil.checkParam(organizationInsertDTO)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        Organization organization = CompanyServiceUtils.toOrganization(organizationInsertDTO);
        return CommonUtil.responseBack(organizationMapper.update(organization));
    }

    @Override
    public JsonResponse getOrganization(Integer id) {
        if (CommonUtil.checkParam(id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        return CommonUtil.responseBack(CompanyServiceUtils.toOrganizationDTO(organizationMapper.get(id)));
    }

    @Override
    public List<CompanyRelationDTO> getListRelation(Integer companyId) {
        if (CommonUtil.checkParam(companyId)) {
            return null;
        }
        return CompanyServiceUtils.toCompanyRelationDTO(companyRelationMapper.listByCompanyId(companyId));
    }

    @Override
    public List<CompanyDTO> listCompanyByServiceType(Integer type) {
        List<TCompanyInfo> companyInfoList = new ArrayList<>();
        if (type.equals(PawnEnum.MAINTAIN_REGULAR.getValue())
                || type.equals(IouEnum.MAINTAIN_REGULAR.getValue())) {
            companyInfoList = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_URGE).getPropertyValue()));
        } else if (type.equals(PawnEnum.MARKET_DISPOSITION.getValue())
                || type.equals(IouEnum.MARKET_DISPOSITION.getValue())) {
            companyInfoList = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_INTERMEDIARY).getPropertyValue()));
        } else if (type.equals(PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue())
                || type.equals(IouEnum.EXECUTE_JUSTICE_RESOLVE.getValue())) {
            companyInfoList = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_LAW).getPropertyValue()));
        } else if (type.equals(PawnEnum.CM_SIMULTANEOUS.getValue())
                || type.equals(IouEnum.CM_SIMULTANEOUS.getValue())) {
            List<TCompanyInfo> companyInfoList0 = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_URGE).getPropertyValue()));
            List<TCompanyInfo> companyInfoList1 = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_INTERMEDIARY).getPropertyValue()));
            for (TCompanyInfo companyInfo : companyInfoList0) {
                companyInfoList.add(companyInfo);
            }
            for (TCompanyInfo companyInfo : companyInfoList1) {
                companyInfoList.add(companyInfo);
            }
        } else if (type.equals(PawnEnum.CJ_SIMULTANEOUS.getValue())
                || type.equals(IouEnum.CJ_SIMULTANEOUS.getValue())) {
            List<TCompanyInfo> companyInfoList0 = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_URGE).getPropertyValue()));
            List<TCompanyInfo> companyInfoList1 = companyInfoMapper.listByType(
                    Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                            KeyEnum.U_TYPE_LAW).getPropertyValue()));
            for (TCompanyInfo companyInfo : companyInfoList0) {
                companyInfoList.add(companyInfo);
            }
            for (TCompanyInfo companyInfo : companyInfoList1) {
                companyInfoList.add(companyInfo);
            }
        } else {
            // 3种全查询
            return listByType(null);
        }
        return CompanyServiceUtils.toCompanyDTO(companyInfoList);
    }

    @Override
    public List<CompanyDTO> listRelationByServiceType(Integer type, Integer id) {
        if (type == null) {
            return null;
        }
        if (id == null) {
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
            if (userInfo == null) {
                return null;
            }
            id = userInfo.getCompanyId();
        }
        boolean urgeFlag = false;
        boolean agentFlag = false;
        boolean lawyerFlag = false;
        if (type.equals(PawnEnum.MAINTAIN_REGULAR.getValue())
                || type.equals(IouEnum.MAINTAIN_REGULAR.getValue())) {
            urgeFlag = true;
        } else if (type.equals(PawnEnum.MARKET_DISPOSITION.getValue())
                || type.equals(IouEnum.MARKET_DISPOSITION.getValue())) {
            agentFlag = true;
        } else if (type.equals(PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue())
                || type.equals(IouEnum.EXECUTE_JUSTICE_RESOLVE.getValue())) {
            lawyerFlag = true;
        } else if (type.equals(PawnEnum.CM_SIMULTANEOUS.getValue())
                || type.equals(IouEnum.CM_SIMULTANEOUS.getValue())) {
            urgeFlag = true;
            agentFlag = true;
        } else if (type.equals(PawnEnum.CJ_SIMULTANEOUS.getValue())
                || type.equals(IouEnum.CJ_SIMULTANEOUS.getValue())) {
            urgeFlag = true;
            lawyerFlag = true;
        } else {
            urgeFlag = true;
            agentFlag = true;
            lawyerFlag = true;
        }

        List<CompanyRelation> list = companyRelationMapper.listByCompanyId(id);
        List<CompanyDTO> result = new ArrayList<>();
        for (CompanyRelation companyRelation : list) {
            Integer companyId = companyRelation.getCompanyAId();
            if (companyRelation.getCompanyAId().equals(id)) {
                companyId = companyRelation.getCompanyBId();
            }
            CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyId);
            if (companyDetailInfo != null) {
                if (companyDetailInfo.getType().equals(Integer.valueOf(
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                                KeyEnum.U_TYPE_URGE).getPropertyValue())) && urgeFlag) {
                    result.add(CompanyServiceUtils.toCompanyDTO(
                            companyInfoMapper.selectByPrimaryKey(companyId)));
                } else if (companyDetailInfo.getType().equals(Integer.valueOf(
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                                KeyEnum.U_TYPE_LAW).getPropertyValue())) && lawyerFlag) {
                    result.add(CompanyServiceUtils.toCompanyDTO(
                            companyInfoMapper.selectByPrimaryKey(companyId)));
                } else if (companyDetailInfo.getType().equals(Integer.valueOf(
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE,
                                KeyEnum.U_TYPE_INTERMEDIARY).getPropertyValue())) && agentFlag) {
                    result.add(CompanyServiceUtils.toCompanyDTO(
                            companyInfoMapper.selectByPrimaryKey(companyId)));
                }
            }
        }
        return result;
    }

    @Override
    public List<CompanyDTO> listByTypeAndIsJoin(Integer type, Integer userId) {
        List<TCompanyInfo> companyInfoList = companyInfoMapper.listByTypeAndIsJoin(type, userId);
        return CompanyServiceUtils.toCompanyDTO(companyInfoList);
        //获取当前用户所参与过的分配器，并且这个分配器中的机构
    }

    @Override
    public ServiceResult<Integer> validateCompany(String credential) {
        CompanyQuery companyQuery = new CompanyQuery();
        companyQuery.setCredential(credential);
        List<TCompanyInfo> tCompanyInfoList = companyInfoMapper.queryList(companyQuery);
        if (null == tCompanyInfoList || tCompanyInfoList.isEmpty()) {
            return ServiceResult.failure("没有数据", ObjectUtils.NULL);
        }

        return ServiceResult.success(tCompanyInfoList.get(0).getId());
    }
}
