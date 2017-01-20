package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TCompanyInfoMapper;
import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessRelationEnum;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.business.ObjectBusinessEnum;
import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectBusinessTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.flowBusiness.FlowBusinessEnum;
import com.dqys.business.orm.constant.partner.PartnerEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.message.MessageMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.pojo.coordinator.UserDetail;
import com.dqys.business.orm.pojo.flowBusiness.FlowBusiness;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.IouEnum;
import com.dqys.business.service.constant.ObjectEnum.PawnEnum;
import com.dqys.business.service.constant.asset.ContactTypeEnum;
import com.dqys.business.service.dto.company.BusinessServiceDTO;
import com.dqys.business.service.dto.company.CompanyTeamReDTO;
import com.dqys.business.service.dto.company.DistributionDTO;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.*;
import com.dqys.business.service.service.cases.CaseService;
import com.dqys.business.service.service.flowBusiness.FlowBusinessService;
import com.dqys.business.service.utils.company.CompanyServiceUtils;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.*;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.dqys.business.orm.constant.company.ObjectTypeEnum.PAWN;

/**
 * Created by Yvan on 16/7/21.
 */
@Repository
@Primary
public class DistributionServiceImpl implements DistributionService {


    @Autowired
    private CompanyTeamMapper companyTeamMapper;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;
    @Autowired
    private TCompanyInfoMapper companyInfoMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;
    @Autowired
    private CompanyRelationMapper companyRelationMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private CoordinatorService coordinatorService;
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private PiRelationMapper piRelationMapper;
    @Autowired
    private ZcyService zcyService;
    @Autowired
    private ContactInfoMapper contactInfoMapper;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private UserService userService;
    @Autowired
    private FlowBusinessService flowBusinessService;
    @Autowired
    private LenderService lenderService;
    @Autowired
    private CaseService caseService;

    @Override
    public DistributionDTO listDistribution_tx(Integer type, Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return null;
        }
        // 获取分配器
        CompanyTeam companyTeam = getCompanyTeam(type, id);
        if (companyTeam == null) {
            return null;
        }
        // 查询分配器成员
        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setStateflag(SysProperty.DEFAULT); // null表示有效查询，0表示全查询，<>0表示已删除
        companyTeamReQuery.setTeamId(companyTeam.getId());
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        setCompanyTeamReList(companyTeamReList);//判断是否对分配器中的总平台记录删除
        List<CompanyTeamReDTO> companyTeamReDTOList = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        DistributionDTO distributionDTO = new DistributionDTO();
        distributionDTO = setVaule(type, id);//设置分配器返回的dto信息
        distributionDTO.setId(companyTeam.getId());
        int index = 0;
        Map<Integer, Integer> keyMap = new HashMap<>();
        for (CompanyTeamRe companyTeamRe : companyTeamReList) {
            // 填充业绩比率,当前任务
            Map<String, Object> map = new HashMap<>();
            if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                map = coordinatorService.getTaskCount(companyTeamRe.getAcceptCompanyId(),
                        UserSession.getCurrent().getUserId(),
                        ObjectTypeEnum.ASSETPACKAGE.getValue());
            } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                map = coordinatorService.getTaskCount(companyTeamRe.getAcceptCompanyId(),
                        UserSession.getCurrent().getUserId(),
                        ObjectTypeEnum.LENDER.getValue());
            }
            Integer total = MessageUtils.transMapToInt(map, "total");
            Integer finish = MessageUtils.transMapToInt(map, "finish");
            String rate = "";
            if (total.equals(0)) {
                rate += 0;
            } else {
                rate += finish * 100 / total + "%";
            }
            if (ObjectBusinessTypeEnum.join.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.platform.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.create.getValue().equals(companyTeamRe.getType())) {
                CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
                if (companyDetailInfo.getType().equals(Integer.valueOf(
                        SysPropertyTool.getProperty(
                                SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_PLATFORM)
                                .getPropertyValue()))) {
                    distributionDTO.setPlatformNum(distributionDTO.getPlatformNum() + 1); // 平台方
                } else if (companyDetailInfo.getType().equals(Integer.valueOf(
                        SysPropertyTool.getProperty(
                                SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_ENTRUST)
                                .getPropertyValue()))) {
                    distributionDTO.setMechanismNum(distributionDTO.getMechanismNum() + 1); // 机构方
                } else {
                    distributionDTO.setDisposeNum(distributionDTO.getDisposeNum() + 1); // 处置方
                }
                companyTeamReDTOList.add(CompanyServiceUtils.toCompanyTeamReDTO(companyTeamRe, companyDetailInfo, rate, total));
            } else if (ObjectBusinessTypeEnum.mechanism.getValue().equals(companyTeamRe.getType())) {
                // 业务流转做准备
                keyMap.put(companyTeamRe.getAccepterId(), index);
                userIds.add(companyTeamRe.getAccepterId());
                companyTeamReList.get(index).setVersion(total);
                companyTeamReList.get(index).setStatus(finish);
            }
            index++;
        }
        distributionDTO.setCompanyTeamReDTOList(companyTeamReDTOList);

        // 添加业务流成员(objectUserRelation.UserId=CompanyTeamRe.acceptId & objectUserRelation.objectId的借款人ID=companyTeam.objectId)
        if (type.equals(ObjectTypeEnum.LENDER.getValue())) {
            // 只有查询借款人分配器列表时候才显示
            setBusinessServiceDTOList(userIds, companyTeam, companyTeamReList, keyMap, distributionDTO);
        }

        return distributionDTO;
    }

    //判断是否对分配器中的总平台记录删除。当前对象为处置机构所录入的，并且没有业务流转的机构，就对参与进来的平台进行删除
    private void setCompanyTeamReList(List<CompanyTeamRe> companyTeamReList) {
        boolean flag = true;//默认删除分配器中的平台，只有在业务流转中存在机构了，才不做删除
        CompanyTeamRe companyTeamRe = new CompanyTeamRe();
        for (CompanyTeamRe teamRe : companyTeamReList) {
            if (teamRe.getStateflag() == 0) {//没有删除的
                com.dqys.auth.orm.pojo.UserDetail detail = userInfoMapper.getUserDetail(teamRe.getAccepterId());
                if (detail != null && detail.getUserType() == UserInfoEnum.USER_TYPE_ADMIN.getValue().intValue()) {
                    companyTeamRe = teamRe;
                }
                if (teamRe.getType() == 3) {
                    flag = false;//存在业务流转机构，不进行删除
                }
            }
        }
        if (companyTeamRe.getCompanyTeamId() != null) {
            //当前对象不是为处置机构时候不进行删除
            CompanyTeam team = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            if (team != null) {//分配器存在
                Integer objectType = team.getObjectType();
                Integer objectId = team.getObjectId();
                Integer userId = 0;
                if (objectType == ObjectTypeEnum.LENDER.getValue().intValue()) {
                    LenderInfo info = lenderInfoMapper.get(objectId);
                    userId = info.getOperator();
                } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue().intValue()) {
                    AssetInfo info = assetInfoMapper.get(objectId);
                    userId = info.getOperator();
                }
                com.dqys.auth.orm.pojo.UserDetail detail = userInfoMapper.getUserDetail(userId);
                if (detail != null && flag && (detail.getUserType() == UserInfoEnum.USER_TYPE_COLLECTION.getValue().intValue() ||
                        detail.getUserType() == UserInfoEnum.USER_TYPE_JUDICIARY.getValue().intValue() ||
                        detail.getUserType() == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().intValue())) {
                    flag = true;//不存在业务流转机构，并且所录入人是处置机构,进行剔除
                } else {
                    flag = false;
                }
                if (flag) {
                    companyTeamReList.remove(companyTeamRe);
                }
            }
        }
    }

    private DistributionDTO setVaule(Integer type, Integer id) {
        DistributionDTO dto = new DistributionDTO();
        if (ObjectTypeEnum.LENDER.getValue().intValue() == type) {
            LenderInfo info = lenderInfoMapper.get(id);
            if (info != null) {
                dto.setAccrual(info.getAccrual());
                dto.setLoan(info.getLoan());
                dto.setAppraisal(info.getAppraisal());
                dto.setNumberNo(info.getLenderNo());
                ContactInfo con = contactInfoMapper.getByModel(ObjectTypeEnum.LENDER.getValue().toString(), ContactTypeEnum.LENDER.getValue(), id);
                if (con != null) {
                    dto.setName(con.getName() != null ? con.getName() : "");
                    dto.setAvg(con.getAvg() != null ? con.getAvg() : "");
                    dto.setSex(con.getGender() != null ? con.getGender().toString() : "");
                }
            }
        } else if (ObjectTypeEnum.ASSETPACKAGE.getValue().intValue() == type) {
            AssetInfo info = assetInfoMapper.get(id);
            if (info != null) {
                dto.setAccrual(info.getAccrual());
                dto.setLoan(info.getLoan());
                dto.setAppraisal(info.getAppraisal());
                dto.setNumberNo(info.getAssetNo());
                dto.setName(info.getName());
            }
        }
        return dto;
    }

    /**
     * 填充业务流转
     *
     * @param userIds           参与人集合
     * @param companyTeam       分配器信息
     * @param companyTeamReList 分配器参与成员信息(这里用来反馈流转公司的公司信息)
     * @param keyMap            保存当前分配器成员ID在成员列表里面的下标值
     * @param distributionDTO   分配器信息
     */
    private void setBusinessServiceDTOList(List<Integer> userIds, CompanyTeam companyTeam, List<CompanyTeamRe> companyTeamReList,
                                           Map<Integer, Integer> keyMap, DistributionDTO distributionDTO) {
        List<BusinessServiceDTO> serviceDTOList = new ArrayList<>();
        ObjectUserRelationQuery objectUserRelationQuery = new ObjectUserRelationQuery();
        objectUserRelationQuery.setUserIds(userIds);
        objectUserRelationQuery.setType(BusinessRelationEnum.dispose.getValue());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(objectUserRelationQuery);
        for (ObjectUserRelation objectUserRelation : relationList) {
            // 对象遍历
            if (PAWN.getValue().equals(objectUserRelation.getObjectType())) {
                PawnInfo pawnInfo = pawnInfoMapper.get(objectUserRelation.getObjectId());
                if (pawnInfo != null) {
                    if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                        LenderInfo lenderInfo = lenderInfoMapper.get(pawnInfo.getLenderId());
                        if (lenderInfo != null && lenderInfo.getAssetId() != null) {
                            if (lenderInfo.getAssetId().equals(companyTeam.getObjectId())) {
                                CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                                CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                                if (ctr != null && detail != null) {
                                    BusinessServiceDTO dto = createBSDTO(ctr, detail, pawnInfo.getId(), PAWN.getValue(),
                                            pawnInfo.getName(), objectUserRelation.getCreateAt());
                                    serviceDTOList.add(dto);
                                }
                            }
                        }
                    } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                        if (pawnInfo.getLenderId().equals(companyTeam.getObjectId())) {
                            CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                            CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                            if (ctr != null && detail != null) {
                                BusinessServiceDTO dto = createBSDTO(ctr, detail, PAWN.getValue(),
                                        pawnInfo.getId(), pawnInfo.getName(), objectUserRelation.getCreateAt());
                                serviceDTOList.add(dto);
                            }
                        }
                    }
                }
            } else if (ObjectTypeEnum.IOU.getValue().equals(objectUserRelation.getObjectType())) {
                IOUInfo iouInfo = iouInfoMapper.get(objectUserRelation.getObjectId());
                if (iouInfo != null) {
                    if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                        LenderInfo lenderInfo = lenderInfoMapper.get(iouInfo.getLenderId());
                        if (lenderInfo != null && lenderInfo.getAssetId() != null) {
                            if (lenderInfo.getAssetId().equals(companyTeam.getObjectId())) {
                                CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                                CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                                if (ctr != null && detail != null) {
                                    BusinessServiceDTO dto = createBSDTO(ctr, detail, ObjectTypeEnum.IOU.getValue(),
                                            iouInfo.getId(), iouInfo.getName(), objectUserRelation.getCreateAt());
                                    serviceDTOList.add(dto);
                                }
                            }
                        }
                    } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                        if (iouInfo.getLenderId().equals(companyTeam.getObjectId())) {
                            CompanyTeamRe ctr = companyTeamReList.get(keyMap.get(objectUserRelation.getUserId()));
                            CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(ctr.getAcceptCompanyId());
                            if (ctr != null && detail != null) {
                                BusinessServiceDTO dto = createBSDTO(ctr, detail, ObjectTypeEnum.IOU.getValue(),
                                        iouInfo.getId(), iouInfo.getName(), objectUserRelation.getCreateAt());
                                serviceDTOList.add(dto);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < serviceDTOList.size(); i++) {
            BusinessServiceDTO dto = serviceDTOList.get(i);
            dto.setType(dto.getType() + (serviceDTOList.size() - i));
        }
        distributionDTO.setBusinessServiceDTOList(serviceDTOList);
    }


    /**
     * 创建业务流成员的书籍
     *
     * @param companyTeamRe 分配器成员信息
     * @param detail        当前公司的详细信息
     * @param name          对象名称
     * @param time          业务流时间
     * @return
     */
    private BusinessServiceDTO createBSDTO(CompanyTeamRe companyTeamRe, CompanyDetailInfo detail, Integer type,
                                           Integer id, String name, Date time) {
        BusinessServiceDTO result = new BusinessServiceDTO();

        result.setId(companyTeamRe.getId());
        result.setStatus(companyTeamRe.getStatus());
        result.setAvg(detail.getAvg());
        result.setCompanyName(detail.getCompanyName());
        result.setCompanyId(detail.getCompanyId());
//        result.setType(detail.getType().toString());
        result.setName(detail.getCompanyName());
        switch (detail.getType()) {
            case 1:
                result.setType("参与处置方");
                break;
            case 0:
                result.setType("参与处置方");
                break;
            case 31:
                result.setType("参与处置方");
                break;
            case 32:
                result.setType("参与处置方");
                break;
            case 33:
                result.setType("参与处置方");
                break;
            case 2:
                result.setType("参与处置方");
                break;
        }
        result.setAddress(AreaTool.getAreaById(detail.getProvince()).getLabel()
                + AreaTool.getAreaById(detail.getCity()).getLabel()
                + AreaTool.getAreaById(detail.getDistrict()).getLabel());
        result.setRate(companyTeamRe.getStatus() + "/" + companyTeamRe.getVersion()); // 临时存储,version全部,status完成数
        result.setTask(companyTeamRe.getVersion() - companyTeamRe.getStatus());
        result.setTarget(name);
        result.setTargetId(id);
        result.setTargetType(type);
        result.setTime(time);
        result.setStateflag(companyTeamRe.getStateflag().intValue());
        result.setUserType(detail.getType());
        return result;
    }

    /**
     * 获取分配器
     *
     * @param type
     * @param id
     * @return
     */
    private CompanyTeam getCompanyTeam(Integer type, Integer id) throws BusinessLogException {
        CompanyTeam isExist = companyTeamMapper.getByTypeId(type, id);
        if (isExist == null) {
            // 分配器不存在,判断是否存在该对象
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(type)) {
                AssetInfo assetInfo = assetInfoMapper.get(id);
                if (assetInfo != null) {
                    // 对象存在，创建分配器
                    JsonResponse result = addDistribution(type, id);
                    if (result.getData() == null
                            || CommonUtil.checkResult(Integer.valueOf(result.getData().toString()))) {
                        return null;
                    }
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(type)) {
                LenderInfo lenderInfo = lenderInfoMapper.get(id);
                if (lenderInfo != null) {
                    // 借款人存在，创建分配器
                    JsonResponse result = addDistribution(type, id);
                    if (result.getData() == null
                            || CommonUtil.checkResult(Integer.valueOf(result.getData().toString()))) {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
        return companyTeamMapper.getByTypeId(type, id);
    }

    @Override
    public JsonResponse addDistribution(Integer type, Integer id) throws BusinessLogException {
        if (CommonUtil.checkParam(type, id)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        // 判断该对象是否审核
        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(type, id);
        if (businessObjRe == null) {
            return JsonResponseTool.paramErr("参数错误，对象业务信息不存在");
        }
        Business business = businessMapper.get(businessObjRe.getBusinessId());
        if (!business.getStatus().equals(BusinessStatusEnum.platform_pass.getValue())
                && !business.getStatus().equals(BusinessStatusEnum.dispose.getValue())) {
            return JsonResponseTool.failure("添加失败，对象未审核或已经处于流转中"); // 处置方录入直接跳转到处置中
        }
        // 查询分配器
        Integer creatorId = 0;
        CompanyTeam companyTeam = companyTeamMapper.getByTypeId(type, id);
        if (companyTeam == null) {
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(type)) {
                AssetInfo assetInfo = assetInfoMapper.get(id);
                if (assetInfo == null) {
                    return JsonResponseTool.paramErr("参数错误，资产包不存在");
                } else {
                    creatorId = assetInfo.getOperator();
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(type)) {
                LenderInfo lenderInfo = lenderInfoMapper.get(id);
                if (lenderInfo == null) {
                    return JsonResponseTool.paramErr("参数错误，借款人不存在");
                } else {
                    if (lenderInfo.getAssetId() != null) {
                        CompanyTeam companyTeam1 = companyTeamMapper.getByTypeId(
                                ObjectTypeEnum.ASSETPACKAGE.getValue(), lenderInfo.getAssetId());
                        if (companyTeam1 != null) {
                            return JsonResponseTool.failure("分配失败，该借款人所属的资产包已经分配"); // 该借款人属于资产包，但是该资产包已经被分配，所以无法在创建
                        }
                    }
                    creatorId = lenderInfo.getOperator();
                }
            } else {
                // 对象类型不符合
                return JsonResponseTool.paramErr("参数错误，分配类型不正确");
            }
            // 存在该对象,创建分配器
            companyTeam = new CompanyTeam();
            companyTeam.setObjectId(id);
            companyTeam.setSenderId(creatorId);
            companyTeam.setObjectType(type);
            Integer result = companyTeamMapper.insert(companyTeam);
            if (CommonUtil.checkResult(result)) {
                // 创建失败
                return JsonResponseTool.failure("创建清收案组失败");
            }
            Integer teamId = companyTeam.getId();
            // 添加操作记录
            businessLogService.add(id, type, UserInfoEnum.DISTRIBUTION_ADD_THEIR.getValue(),
                    "", "创建清收案组", 0, 0);
            // 添加本家分配记录
            CompanyDetailInfo detailInfo = companyInfoMapper.getDetailByUserId(creatorId);
            if (detailInfo != null && detailInfo.getCompanyId() != null) {
                CompanyTeamRe companyTeamRe = new CompanyTeamRe();
                companyTeamRe.setCompanyTeamId(teamId);
                companyTeamRe.setAcceptCompanyId(detailInfo.getCompanyId());
                companyTeamRe.setStatus(ObjectAcceptTypeEnum.accept.getValue());
                companyTeamRe.setType(ObjectBusinessTypeEnum.create.getValue());
                companyTeamRe.setAccepterId(detailInfo.getUserId()); // 接收人为创建者
                companyTeamReMapper.insert(companyTeamRe);
                // 添加平台参与记录
                List<TCompanyInfo> managerList = companyInfoMapper.listByType(UserInfoEnum.USER_TYPE_ADMIN.getValue());
                if (managerList != null && managerList.size() != 0) {
                    // 当前只有一个平台方的管理员
                    detailInfo = companyInfoMapper.getDetailByCompanyId(managerList.get(0).getId());
                    if (detailInfo != null && detailInfo.getUserId().intValue() != companyTeamRe.getAccepterId().intValue()) {
                        companyTeamRe.setAcceptCompanyId(detailInfo.getCompanyId());
                        companyTeamRe.setStatus(ObjectAcceptTypeEnum.accept.getValue());
                        companyTeamRe.setType(ObjectBusinessTypeEnum.join.getValue());
                        companyTeamRe.setAccepterId(detailInfo.getUserId());
                        companyTeamReMapper.insert(companyTeamRe);
                    }
                }

            }
            return JsonResponseTool.success(teamId);
        }
        return JsonResponseTool.failure("清收案组已经存在");
    }

    @Override
    public JsonResponse joinDistribution(Integer id) throws BusinessLogException {
        if (id != null) {
            TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
            if (userInfo == null || userInfo.getCompanyId() == null) {
                return JsonResponseTool.failure("用户信息校验错误，请重新登录"); // 用户不存在
            }
            CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId());
            if (companyDetailInfo == null) {
                return JsonResponseTool.failure("公司不存在");
            }
            CompanyTeam companyTeam = companyTeamMapper.get(id);
            if (companyTeam == null) {
                return JsonResponseTool.failure("清收案组不存在");
            }

            CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
            companyTeamReQuery.setTeamId(id);
            companyTeamReQuery.setValid(true);
            List<CompanyTeamRe> countList = companyTeamReMapper.queryList(companyTeamReQuery);
            //处置机构只能存在一家
            if (setDisposalTo(id)) {
                return JsonResponseTool.failure("处置机构只能存在一家");
            }
            for (CompanyTeamRe companyTeamRe : countList) {
                if (companyTeamRe.getType().equals(ObjectBusinessTypeEnum.create.getValue())) {
                    // 如果创建方为处置方，默认处置机构为处置方
                    CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(
                            companyTeamRe.getAcceptCompanyId());
                    if (CommonUtil.isDispose(detail.getType().toString())) {
                        return JsonResponseTool.failure("加入失败，已经存在处置方");
                    }
                }
            }
//            if (countList != null && countList.size() >= 3) {
//                // 获取不到数据或已经超过3个参与(处置方只能有一个)
//                return null;
//            }

            companyTeamReQuery.setCompanyId(userInfo.getCompanyId());
            List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
            if (companyTeamReList != null && companyTeamReList.size() > 0) {
                // 已经存在在该分配器中无需再次申请或邀请
                return JsonResponseTool.failure("已经加入清收案组中");
            }
            CompanyTeamRe companyTeamRe = new CompanyTeamRe();
            companyTeamRe.setCompanyTeamId(id);
            companyTeamRe.setAcceptCompanyId(userInfo.getCompanyId());
            companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            companyTeamRe.setType(ObjectBusinessTypeEnum.join.getValue());
            companyTeamRe.setAccepterId(companyDetailInfo.getUserId());
            Integer result = companyTeamReMapper.insert(companyTeamRe);
            if (CommonUtil.checkResult(result)) {
                return JsonResponseTool.failure("添加失败，请重新操作");
            } else {
                // 添加操作记录
                UserDetail detail = coordinatorMapper.getUserDetail(userInfo.getId());
                businessLogService.add(companyTeam.getObjectId(), companyTeam.getObjectType(), UserInfoEnum.DISTRIBUTION_ADD_THEIR.getValue(),
                        "", "主动加入清收案组, 公司：" + detail.getCompanyName() + "发起人：" + detail.getRealName(), 0, 0);
                // 获取平台信息
                List<TCompanyInfo> companyInfoList = companyInfoMapper.listByType(UserInfoEnum.USER_TYPE_ADMIN.getValue());
                CompanyDetailInfo platformDetail = companyInfoMapper.getDetailByCompanyId(companyInfoList.get(0).getId());
                // 发送短信提醒
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        platformDetail.getName(),
                        userService.getCompayTypeToString(detail),
                        companyDetailInfo.getCompanyName(),
                        userService.getRoleNameToString(detail),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        coordinatorService.getObjectName(companyTeam.getObjectType(), companyTeam.getObjectId())
                };
                smsUtil.sendSms(SmsEnum.SMS_DISTRIBUTION_JOIN_CODE.getValue(), platformDetail.getPhone(), msg); // 平台接收
                // 添加消息
                Message message = new Message();
                message.setTitle(coordinatorService.getMessageTitle(companyTeam.getObjectType(), companyTeam.getObjectId(), MessageBTEnum.COMPANY_JOIN.getValue())); // 业务类型 对象类型 对象编号
                message.setContent(smsUtil.getSendContent(SmsEnum.SMS_DISTRIBUTION_JOIN_CODE.getValue(), msg));
                message.setSenderId(userInfo.getId());
                message.setReceiveId(platformDetail.getUserId()); // 平台接收审核
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.TASK.getValue());
                message.setBusinessType(MessageBTEnum.COMPANY_JOIN.getValue());
                message.setOperUrl(
                        MessageUtils.setOperUrl(
                                "/api/company/designDistribution?id=" + companyTeamRe.getId() + "&status=1",
                                "get",
                                "/api/company/designDistribution?id=" + companyTeamRe.getId() + "&status=0",
                                "get",
                                null
                        ));
                messageMapper.add(message);

                return JsonResponseTool.success(companyTeamRe.getId());
            }
        }
        return JsonResponseTool.paramErr("参数错误");
    }

    @Override
    public JsonResponse inviteDistribution(Integer id, Integer companyId) throws BusinessLogException {
        if (CommonUtil.checkParam(id, companyId)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!CommonUtil.isManage()) {
            return JsonResponseTool.failure("非平台管理员，没有权限邀请"); // 不是平台管理员
        }
        CompanyTeam companyTeam = companyTeamMapper.get(id);
        if (companyTeam == null) {
            return JsonResponseTool.paramErr("参数错误，清收案组不存在"); // 分配器不存在
        }
        //把创建者改为当前操作人
        companyTeam.setSenderId(UserSession.getCurrent().getUserId());
        companyTeamMapper.update(companyTeam);
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByUserId(UserSession.getCurrent().getUserId());
        if (companyDetailInfo == null) {
            return JsonResponseTool.paramErr("用户信息错误，请重新登录"); // 公司不存在
        }
        CompanyDetailInfo companyDetailInfo1 = companyInfoMapper.getDetailByCompanyId(companyId); // 被邀请公司信息
        if (companyDetailInfo1 == null) {
            return JsonResponseTool.paramErr("对象公司不存在"); // 找不到目标公司
        }

        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(id);
        companyTeamReQuery.setValid(true);
        List<CompanyTeamRe> countList = companyTeamReMapper.queryList(companyTeamReQuery);
        //处置机构只能存在一家
        if (setDisposalTo(id)) {
            return JsonResponseTool.failure("处置机构只能存在一家");
        }
        for (CompanyTeamRe companyTeamRe : countList) {
            if (companyTeamRe.getType().equals(ObjectBusinessTypeEnum.create.getValue())) {
                // 如果创建方为处置方，默认处置机构为处置方
                CompanyDetailInfo detail = companyInfoMapper.getDetailByCompanyId(
                        companyTeamRe.getAcceptCompanyId());
                if (CommonUtil.isDispose(detail.getType().toString())) {
                    return JsonResponseTool.paramErr("参数错误");
                }
            }
        }
//        if (countList != null && countList.size() >= 3) {
//            // 获取不到数据或已经超过3个参与(处置方只能有一个)
//            return null;
//        }

        for (CompanyTeamRe companyTeamRe : countList) {
            if (companyTeamRe.getAcceptCompanyId().equals(companyId)) {
                // 已经存在在该分配器中无需再次申请或邀请
                return JsonResponseTool.failure("该公司已经存在于清收案组中");
            }
        }


        CompanyTeamRe companyTeamRe = new CompanyTeamRe();
        companyTeamRe.setCompanyTeamId(id);
        companyTeamRe.setAcceptCompanyId(companyId);
        companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
        companyTeamRe.setType(ObjectBusinessTypeEnum.platform.getValue());
        companyTeamRe.setAccepterId(companyDetailInfo1.getUserId());
        Integer result = companyTeamReMapper.insert(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("邀请失败 ，请重新再试");
        } else {
            // 添加操作记录
            UserDetail detail = coordinatorMapper.getUserDetail(companyDetailInfo1.getUserId());
            businessLogService.add(companyTeam.getObjectId(), companyTeam.getObjectType(), UserInfoEnum.DISTRIBUTION_ADD_THEIR.getValue(),
                    "", "邀请加入清收案组，被邀请公司：" + detail.getCompanyName() + ",被邀请人：" + detail.getRealName(), 0, 0);
            // 发送短信提醒
            SmsUtil smsUtil = new SmsUtil();
            String[] msg = {
                    companyDetailInfo1.getName(),
                    ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                    coordinatorService.getObjectName(companyTeam.getObjectType(), companyTeam.getObjectId())
            };
            smsUtil.sendSms(SmsEnum.INVITE_DISTRIBUTOR.getValue(), companyDetailInfo1.getPhone(), msg);
            // 添加消息
            String code = ""; // 对象编号
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if (assetInfo != null) {
                    code = assetInfo.getAssetNo();
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if (lenderInfo != null) {
                    code = lenderInfo.getLenderNo();
                }
            }
            Message message = new Message();
            message.setTitle(MessageEnum.TASK.getName() + " > "
                            + ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName() + " > "
                            + code
            ); // 业务类型 对象类型 对象编号
            message.setContent(smsUtil.getSendContent(SmsEnum.INVITE_DISTRIBUTOR.getValue(), msg));
            message.setSenderId(companyDetailInfo.getUserId());
            message.setReceiveId(companyDetailInfo1.getUserId());
            message.setLabel(null);
            message.setStatus(0);
            message.setType(MessageEnum.TASK.getValue());
            message.setBusinessType(MessageBTEnum.COMPANY_BETWEEN.getValue());
            message.setOperUrl(
                    MessageUtils.setOperUrl(
                            "/api/company/designDistribution?id=" + companyTeamRe.getId() + "&status=1",
                            "get",
                            "/api/company/designDistribution?id=" + companyTeamRe.getId() + "&status=0",
                            "get",
                            null
                    ));
            messageMapper.add(message);


            return JsonResponseTool.success(companyTeamRe.getId());
        }
    }


    private boolean setDisposalTo(Integer companyTeamId) {
        Integer count = companyTeamReMapper.findNumByDisposal(companyTeamId);
        if (count >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public JsonResponse updateDistribution_tx(Integer id, Integer status) throws BusinessLogException {
        if (CommonUtil.checkParam(id, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return JsonResponseTool.paramErr("参数错误，清收案组不存在"); // 分配器记录不存在
        }
        if (!companyTeamRe.getStatus().equals(ObjectAcceptTypeEnum.init.getValue())) {
            return JsonResponseTool.failure("该对象已经审核过"); // 不是待审核状态
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return JsonResponseTool.paramErr("用户信息异常，请重新登录"); // 当前用户存在异常
        }

        if (companyTeamRe.getType().equals(ObjectBusinessTypeEnum.platform.getValue())) {
            // 平台分配
            if (!companyTeamRe.getAccepterId().equals(userInfo.getId())) {
                return JsonResponseTool.failure("权限错误，非被邀请公司"); // 不是被邀请公司的管理员，无法审核
            }
        } else if (companyTeamRe.getType().equals(ObjectBusinessTypeEnum.join.getValue())) {
            // 主动加入
            if (!CommonUtil.isManage()) {
                return JsonResponseTool.failure("权限错误，非平台管理员"); // 不是平台方管理员，无法审核
            }
        }

        companyTeamRe.setStatus(status);
        Integer result = companyTeamReMapper.update(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("修改失败，请重新操作");
        } else {
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId()); // 分配器信息
            // 添加操作记录
            UserDetail detail = coordinatorMapper.getUserDetail(userInfo.getId());
            if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                // 接收
                businessLogService.add(companyTeam.getObjectId(), companyTeam.getObjectType(), UserInfoEnum.DISTRIBUTION_ACCEPT_ADD.getValue(),
                        "", "决定是否接受清收案组成员：" + detail.getRealName() + "，接受状态：同意", 0, 0);
            } else {
                // 拒绝
                businessLogService.add(companyTeam.getObjectId(), companyTeam.getObjectType(), UserInfoEnum.DISTRIBUTION_REJECT_DEL.getValue(),
                        "", "决定是否接受清收案组成员：" + detail.getRealName() + "，接受状态：拒绝", 0, 0);
            }
            // 提醒消息
            if (ObjectBusinessTypeEnum.join.getValue().equals(companyTeamRe.getType())) {
                // 发送短信提醒
                TUserInfo applicant = userInfoMapper.selectByPrimaryKey(companyTeamRe.getAccepterId()); // 申请人信息
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        applicant.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        coordinatorService.getObjectName(companyTeam.getObjectType(), companyTeam.getObjectId())
                };
                Message message = new Message();
                if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    smsUtil.sendSms(SmsEnum.DISTRIBUTION_JOIN_YES.getValue(), applicant.getMobile(), msg); // 申请人接收
                    smsUtil.sendSms(SmsEnum.DISTRIBUTION_JOIN_YES.getValue(), creator.getMobile(), msg); // 创建人接收
                    message.setContent(smsUtil.getSendContent(SmsEnum.DISTRIBUTION_JOIN_YES.getValue(), msg));
                } else {
                    smsUtil.sendSms(SmsEnum.DISTRIBUTION_JOIN_NO.getValue(), applicant.getMobile(), msg); // 申请人接收
                    smsUtil.sendSms(SmsEnum.DISTRIBUTION_JOIN_NO.getValue(), creator.getMobile(), msg); // 创建人接收
                    message.setContent(smsUtil.getSendContent(SmsEnum.DISTRIBUTION_JOIN_NO.getValue(), msg));
                }
                // 添加消息
                message.setTitle(coordinatorService.getMessageTitle(companyTeam.getObjectType(), companyTeam.getObjectId(), MessageBTEnum.INSIDE_RESULT.getValue())); // 业务类型 对象类型 对象编号
                message.setSenderId(userInfo.getId());
                message.setReceiveId(companyTeamRe.getAccepterId()); // 申请人接收
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.SERVE.getValue());
                message.setBusinessType(MessageBTEnum.INSIDE_RESULT.getValue());
                message.setOperUrl(null);
                messageMapper.add(message);
                message.setReceiveId(companyTeam.getSenderId()); // 创建人
                messageMapper.add(message);
            } else if (ObjectBusinessTypeEnum.mechanism.getValue().equals(companyTeamRe.getType())
                    || ObjectBusinessTypeEnum.platform.getValue().equals(companyTeamRe.getType())) {
                // 获取平台信息
                List<TCompanyInfo> companyInfoList = companyInfoMapper.listByType(UserInfoEnum.USER_TYPE_ADMIN.getValue());
                CompanyDetailInfo platformDetail = companyInfoMapper.getDetailByCompanyId(companyInfoList.get(0).getId());
                // 发送短信提醒
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId()); // 被邀请公司信息
                UserDetail operC = coordinatorMapper.getUserDetail(userInfo.getId());
                SmsUtil smsUtil = new SmsUtil();
                String[] msg = {
                        creator.getRealName(),
                        userService.getCompayTypeToString(operC),
                        companyDetailInfo.getCompanyName(),
                        userService.getRoleNameToString(operC),
                        userInfo.getRealName(),
                        ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                        coordinatorService.getObjectName(companyTeam.getObjectType(), companyTeam.getObjectId())
                };
                Message message = new Message();
                if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    String content = smsUtil.sendSms(SmsEnum.SMS_DISTRIBUTION_PASS_CODE.getValue(), creator.getMobile(), msg); // 创建人接收
//                    msg[0] = platformDetail.getName();
//                    smsUtil.sendSms(SmsEnum.SMS_DISTRIBUTION_PASS_CODE.getValue(), platformDetail.getPhone(), msg); // 平台接收
                    message.setContent(content);
                } else {
                    String content = smsUtil.sendSms(SmsEnum.SMS_DISTRIBUTION_REFUSE_CODE.getValue(), creator.getMobile(), msg); // 创建人接收
//                    msg[0] = platformDetail.getName();
//                    smsUtil.sendSms(SmsEnum.SMS_DISTRIBUTION_REFUSE_CODE.getValue(), platformDetail.getPhone(), msg); // 平台接收
                    message.setContent(content);
                }
                message.setSenderId(userInfo.getId());
                message.setReceiveId(companyTeam.getSenderId()); // 创建人接收
                message.setLabel(null);
                message.setStatus(0);
                message.setType(MessageEnum.SERVE.getValue());
                message.setBusinessType(MessageBTEnum.INSIDE_RESULT.getValue());
                message.setOperUrl(null);
                message.setTitle(coordinatorService.getMessageTitle(companyTeam.getObjectId(), companyTeam.getObjectType(), MessageBTEnum.INSIDE_RESULT.getValue()));
                messageMapper.add(message);
            }

            Integer lawType = Integer.valueOf(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                            .getPropertyValue()); // 律所
            Integer urgeType = Integer.valueOf(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                            .getPropertyValue()); // 催收
            Integer intermediaryType = Integer.valueOf(
                    SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                            .getPropertyValue()); // 中介
            // 接收邀请,添加对象关系和实体类的标识
            if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                // 业务
                Integer businessId = null;
                BusinessObjRe businessObjRe =
                        businessObjReMapper.getByObject(companyTeam.getObjectType(), companyTeam.getObjectId());
                if (businessObjRe != null) {
                    businessId = businessObjRe.getBusinessId();
                }
                if (companyTeam != null) {
                    // 修改业务状态
                    Business business = businessMapper.get(businessObjRe.getBusinessId());
                    business.setStatus(BusinessStatusEnum.dispose.getValue());
                    businessMapper.update(business);

                    // 添加公司之间的关系
                    TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId());
                    CompanyDetailInfo companyDetailInfo =
                            companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
                    addCompanyRelation(creator.getCompanyId(), companyTeamRe.getAcceptCompanyId());

                    // 添加操作者与操作事物的关联
                    createObjectUserRelation(companyTeam.getObjectType(), companyTeam.getObjectId(), companyDetailInfo.getUserId(),
                            companyTeam.getId(), businessId);
                    if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                        // 借款人类型
                        addLenderDispose(companyTeam.getObjectId(), companyDetailInfo.getType(),
                                lawType, urgeType, intermediaryType);
                    } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                        // 资产包类型
                        addAssetDispose(companyTeam.getObjectId(), companyDetailInfo.getType(),
                                lawType, urgeType, intermediaryType);
                        // 添加资产包下的借款人关系信息
                        List<LenderInfo> lenderInfoList = lenderInfoMapper.listByAssetId(companyTeam.getObjectId());
                        for (LenderInfo lenderInfo : lenderInfoList) {
                            CompanyTeam companyTeam1 = companyTeamMapper.getByTypeId(ObjectTypeEnum.LENDER.getValue(), lenderInfo.getId());
                            if (companyTeam1 == null) {
                                createObjectUserRelation(ObjectTypeEnum.LENDER.getValue(), lenderInfo.getId(),
                                        companyDetailInfo.getUserId(), companyTeam.getId(), businessId);
                            }
                        }
                    }
                    String reg = UserSession.getCurrent() == null ? "" : UserSession.getCurrent().getUserType();
                    //是律所公司身份的流转需要把抵押物转变为案件
                    if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().toString().equals(reg.substring(0, reg.lastIndexOf(",")))) {
                        caseService.receivePawn(companyTeam.getObjectId(), companyTeam.getObjectType());//律所公司接受业务流转的抵押物添加到案件信息
                    }
                }
            }
            return JsonResponseTool.success(companyTeam);
        }
    }


    /**
     * 所属机构同意平台将其加入分配器
     *
     * @param id
     * @param status
     * @return
     */
    public JsonResponse designDistribution(Integer id, Integer status) throws BusinessLogException {
        JsonResponse result = updateDistribution_tx(id, status);
        //根据业务主体(资产包,借款人)跟新业务流转对象(借据,抵押物)
        if (result.getCode() == ResponseCodeEnum.SUCCESS.getValue().intValue()) {
            CompanyTeam companyTeam = (CompanyTeam) result.getData();
            businessService.updateBusinessFlowObjOnType(companyTeam.getObjectType(), companyTeam.getObjectId(),
                    ObjectBusinessEnum.PAWN_ON_BUSINESS.getValue(), ObjectBusinessEnum.IOU_ON_BUSINESS.getValue());
            if (status == ObjectAcceptTypeEnum.accept.getValue() && companyTeam.getObjectType() == ObjectTypeEnum.LENDER.getValue().intValue()) {
                //创建协作器
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                lenderInfo.setOperator(UserSession.getCurrent().getUserId());
                lenderService.addCoordinator(lenderInfo);
            }
            return JsonResponseTool.success(id);
        }
        return result;


    }

    @Override
    public JsonResponse exitDistribution_tx(Integer id) throws BusinessLogException {
        if (id == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!CommonUtil.isManage()) {
            return JsonResponseTool.paramErr("权限错误，非平台管理员");
        }
        // 存在该分配数据
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return JsonResponseTool.paramErr("参数错误，清收案组成员不存在");
        }
        // 已经移除
        if (companyTeamRe.getStateflag() > 0) {
            return JsonResponseTool.failure("已经从清收案组移除");
        }
        // 校验公司是否存在
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId());
        if (companyDetailInfo == null) {
            return JsonResponseTool.paramErr("信息错误，请联系管理员");
        }
        // 校验是否为参加的处置方
        if (companyTeamRe.getType().equals(ObjectBusinessTypeEnum.create.getValue())
                || companyTeamRe.getAccepterId().equals(UserSession.getCurrent().getUserId())) {
            return JsonResponseTool.failure("创建方和平台方无法删除");
        }
        Integer result = companyTeamReMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        } else {
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            // 业务状态还原
            BusinessObjRe businessObjRe =
                    businessObjReMapper.getByObject(companyTeam.getObjectType(), companyTeam.getObjectId());
            if (businessObjRe != null) {
                Business business = businessMapper.get(businessObjRe.getBusinessId());
                business.setStatus(BusinessStatusEnum.platform_pass.getValue());
                businessMapper.update(business);
            }

            // 添加操作记录
            UserDetail detail = coordinatorMapper.getUserDetail(companyTeamRe.getAccepterId());
            businessLogService.add(companyTeam.getObjectId(), companyTeam.getObjectType(), UserInfoEnum.DISTRIBUTION_DEL_THEIR.getValue(),
                    "", "移除清收案组对象成员:" + detail.getRealName(), 0, 0);

            // 发送短信提醒
            TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeamRe.getAccepterId()); // 申请人信息
            SmsUtil smsUtil = new SmsUtil();
            String[] msg = {
                    creator.getRealName(),
                    ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName(),
                    coordinatorService.getObjectName(companyTeam.getObjectType(), companyTeam.getObjectId())
            };
            Message message = new Message();
            smsUtil.sendSms(SmsEnum.SMS_OUT_CODE.getValue(), creator.getMobile(), msg);
            message.setContent(smsUtil.getSendContent(SmsEnum.SMS_OUT_CODE.getValue(), msg));

            // 去除公司的协作器
            coordinatorService.delCoordinator(companyTeamRe.getAcceptCompanyId(),
                    companyTeam.getObjectId(), companyTeam.getObjectType());
            // 添加消息
            String code = ""; // 对象编号
            if (ObjectTypeEnum.ASSETPACKAGE.getValue().equals(companyTeam.getObjectType())) {
                AssetInfo assetInfo = assetInfoMapper.get(companyTeam.getObjectId());
                if (assetInfo != null) {
                    code = assetInfo.getAssetNo();
                }
            } else if (ObjectTypeEnum.LENDER.getValue().equals(companyTeam.getObjectType())) {
                LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                if (lenderInfo != null) {
                    code = lenderInfo.getLenderNo();
                }
            }
            message.setTitle(MessageEnum.TASK.getName() + " > "
                            + ObjectTypeEnum.getObjectTypeEnum(companyTeam.getObjectType()).getName() + " > "
                            + code
            ); // 业务类型 对象类型 对象编号
            message.setSenderId(UserSession.getCurrent().getUserId());
            message.setReceiveId(companyTeamRe.getAccepterId());
            message.setLabel(null);
            message.setStatus(0);
            message.setType(MessageEnum.SERVE.getValue());
            message.setBusinessType(MessageBTEnum.INSIDE_RESULT.getValue());
            message.setOperUrl(null);
            messageMapper.add(message);
            // 去除介入信息
            if (companyTeam != null) {
                // 删除操作者与操作事物的关联
                if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                    deleteAssetObjectUserRelation(companyTeam.getObjectId(), companyTeam.getId(),
                            companyDetailInfo.getUserId());
                } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                    deleteLenderObjectUserRelation(companyTeam.getObjectId(), companyTeam.getId(),
                            companyDetailInfo.getUserId());
                }

                if (companyTeam.getObjectType().equals(ObjectTypeEnum.LENDER.getValue())) {
                    // 借款人类型
                    clearLenderDispose(companyTeam.getObjectId());
                } else if (companyTeam.getObjectType().equals(ObjectTypeEnum.ASSETPACKAGE.getValue())) {
                    // 资产包类型
                    clearAssetDispose(companyTeam.getObjectId());
                }
            }
            //根据业务主体(资产包,借款人)跟新业务流转对象(借据,抵押物)
            businessService.updateBusinessFlowObjOnType(companyTeam.getObjectType(), companyTeam.getObjectId(),
                    ObjectBusinessEnum.PAWN_NOTON_BUSINESS.getValue(), ObjectBusinessEnum.IOU_NOTON_BUSINESS.getValue());
            return JsonResponseTool.success(result);
        }
    }


    /**
     * 创建对象之间的关系
     *
     * @param type           类型
     * @param id             id
     * @param userId         操作人(管理员)
     * @param distributionId 分配器ID
     * @param businessId     业务ID
     * @return
     */
    private Integer createObjectUserRelation(Integer type, Integer id, Integer userId,
                                             Integer distributionId, Integer businessId) {
        // 增加对象与操作事物的联系
        ObjectUserRelation objectUserRelation = new ObjectUserRelation();
        objectUserRelation.setObjectType(type);
        objectUserRelation.setObjectId(id);
        objectUserRelation.setUserId(userId); // 只有管理员才可以接收
        objectUserRelation.setType(BusinessRelationEnum.company.getValue());
        objectUserRelation.setVisibleType(SysProperty.BOOLEAN_TRUE); // 可见
        objectUserRelation.setEmployerId(distributionId); // 分配器ID
        objectUserRelation.setVisibleType(businessId); // 业务ID
        Integer result = objectUserRelationMapper.insert(objectUserRelation);
        if (result == null) {
            // 添加事物关系失败
            return null;
        }
        return result;
    }

    @Override
    public JsonResponse addBusinessService(Integer type, Integer id, Integer distributionId, Integer businessType, Integer companyId,
                                           Integer businessRequestId, Integer flowBusinessId, List<Integer> inviteUserIds) throws BusinessLogException {
        Integer userId = UserSession.getCurrent().getUserId();
        if (!ObjectTypeEnum.IOU.getValue().equals(type) && !PAWN.getValue().equals(type)) {
            return JsonResponseTool.paramErr("参数错误，不是可流转对象"); // 流转对象不对
        }
        CompanyTeam companyTeam = companyTeamMapper.get(distributionId);
        if (companyTeam == null) {
            return JsonResponseTool.paramErr("参数错误，不存在该清收案组"); // 分配器不存在
        }
        TUserInfo userInfo = userInfoMapper.selectByPrimaryKey(UserSession.getCurrent().getUserId());
        if (userInfo == null || userInfo.getCompanyId() == null) {
            return JsonResponseTool.paramErr("信息错误，请重新登录"); // 用户不存在
        }
        CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(userInfo.getCompanyId());
        if (companyDetailInfo == null) {
            return JsonResponseTool.paramErr("信息错误，请重新登录"); // 公司不存在
        }
        CompanyTeamReQuery teamReQuery = new CompanyTeamReQuery();
        teamReQuery.setTeamId(companyTeam.getId());
        teamReQuery.setValid(true);
        List<CompanyTeamRe> teamReList = companyTeamReMapper.queryList(teamReQuery);
        boolean canAdd = false;
        for (CompanyTeamRe companyTeamRe : teamReList) {
            if (companyTeamRe.getType().equals(ObjectBusinessTypeEnum.platform.getValue())
                    || companyTeamRe.getType().equals(ObjectBusinessTypeEnum.join.getValue())) {
                if (companyTeamRe.getAcceptCompanyId().equals(userInfo.getCompanyId())
                        && companyTeamRe.getStatus().equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    canAdd = true;
                    break;
                }
            }
        }
        if (!canAdd) {
            return JsonResponseTool.failure("权限错误，无法操作"); // 只有平台或者所属处置方才能发起业务流转
        }
        if (userInfo.getCompanyId().equals(companyId)) {
            return JsonResponseTool.failure("流转失败，只能向其他公司流转"); // 必须非本公司
        }
        CompanyDetailInfo companyDetailInfo1 = companyInfoMapper.getDetailByCompanyId(companyId); // 被邀请公司信息
        if (companyDetailInfo1 == null) {
            return JsonResponseTool.paramErr("参数错误，目标公司不存在"); // 找不到目标公司
        }

        // 对象的关系
        String error = addBusinessObjectUserRelation(type, id, companyDetailInfo1.getUserId(),
                companyTeam, ObjectUserStatusEnum.accept.getValue(), true);
        if (error != null) {
            // 已经增加
            return JsonResponseTool.failure(error);
        } else {
            // 添加公司
            Integer result = addCompanyTeamRe(distributionId, companyDetailInfo1, businessRequestId);
            if (CommonUtil.checkResult(result)) {
                return JsonResponseTool.failure("流转失败，请重新流转");
            } else {
                // 添加操作记录
                businessLogService.add(id, type,
                        UserInfoEnum.DISTRIBUTION_ADD_GENERAL.getValue(),
                        "", "增加业务流转", 0, 0);
                // 发送短信提醒
                String operUrl = MessageUtils.setOperUrl(
                        "/api/company/designBusinessService?type=" + type
                                + "&id=" + id + "&distributionId=" + result + "&businessType=" + businessType
                                + "&status=1" + "&flowBusinessId=" + flowBusinessId + "&toPlatform=" + (userId == businessRequestId ? 0 : 1),
                        "get",
                        "/api/company/designBusinessService?type=" + type
                                + "&id=" + id + "&distributionId=" + result + "&businessType=" + businessType
                                + "&status=0" + "&flowBusinessId=" + flowBusinessId + "&toPlatform=" + (userId == businessRequestId ? 0 : 1),
                        "get",
                        null
                );
                String operation = "";
                if (ObjectTypeEnum.PAWN.getValue().intValue() == type) {
                    operation = PawnEnum.getPawnEnum(businessType).getName();
                } else if (ObjectTypeEnum.IOU.getValue().intValue() == type) {
                    operation = IouEnum.getIouEnum(businessType).getName();
                }
                setFlowBusiness(companyDetailInfo1.getUserId(), flowBusinessId, FlowBusinessEnum.FLOW_COMPANY_WAIT_AGREE.getValue());//修改业务状态
                sendBusinessFlow(userId, companyDetailInfo1.getUserId(), businessRequestId,
                        companyTeam.getObjectId(), companyTeam.getObjectType(), id, type, operUrl, operation, flowBusinessId);//发送短信给邀请的公司
                inviteUserIds.add(companyDetailInfo1.getUserId());//添加的业务公司管理员
                return JsonResponseTool.success(result);
            }
        }
    }

    /**
     * 平台为业务流转请求公司添加业务合作伙伴的时候把状态改为==>待同意
     *
     * @param userId
     * @param flowBusinessId
     */
    private void setFlowBusiness(Integer userId, Integer flowBusinessId, Integer status) {
        UserDetail detail = coordinatorMapper.getUserDetail(userId);
        FlowBusiness flowBusiness = flowBusinessService.get(flowBusinessId);
        if (detail.getUserType() == UserInfoEnum.USER_TYPE_COLLECTION.getValue().intValue()) {
            flowBusiness.setCollection(status);
        } else if (detail.getUserType() == UserInfoEnum.USER_TYPE_JUDICIARY.getValue().intValue()) {
            flowBusiness.setLawyer(status);
        } else if (detail.getUserType() == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().intValue()) {
            flowBusiness.setAgent(status);
        }
        flowBusinessService.updateById(flowBusiness);
    }

    /**
     * 设置操作状态，多个相同机构进行操作，其中有一个同意，其余的都不得继续操作,不能操作的机构进行删除
     *
     * @param flowBusinessId
     * @param userId
     * @param status
     */
    private boolean setOperStatus(Integer flowBusinessId, Integer userId, Integer status, CompanyTeamRe teamRe) throws BusinessLogException {
        UserDetail detail = coordinatorMapper.getUserDetail(userId);
        Integer operStatus = 0;
        List<Message> list = messageMapper.selectMessageByUFO(detail.getUserType(), flowBusinessId, operStatus, MessageBTEnum.COMPANY_BETWEEN_FLOW.getValue());//获取相同分配器和相同机构角色的成员
        if (list == null || list.size() == 1) {
            return true;
        }
        List<BusinessServiceDTO> businessServiceDTOList = null;//业务流转成员
        if (teamRe != null) {
            CompanyTeam team = companyTeamMapper.get(teamRe.getCompanyTeamId());
            DistributionDTO dto = listDistribution_tx(team.getObjectType(), team.getObjectId());//获取分配器列表中的业务流转成员，用于剔除其他相同的机构
            if (dto != null) {
                businessServiceDTOList = dto.getBusinessServiceDTOList() == null ? null : dto.getBusinessServiceDTOList();
            }
        }
        if (status.equals(ObjectAcceptTypeEnum.accept.getValue()) && list.size() > 1) {
            for (Message message : list) {
                if (message.getReceiveId() != userId) {
                    message.setOperStatus(3);
                    messageMapper.updateOperStatus(message);//修改操作状态
                    //做出删除行为
                    delFlowBusinessCompany(businessServiceDTOList, message);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private void delFlowBusinessCompany(List<BusinessServiceDTO> businessServiceDTOList, Message message) throws BusinessLogException {
        UserDetail detail = coordinatorMapper.getUserDetail(message.getReceiveId());
        if (businessServiceDTOList != null) {
            for (BusinessServiceDTO busDto : businessServiceDTOList) {
                if (busDto.getStateflag() == 0 && busDto.getCompanyId().intValue() == detail.getCompanyId().intValue()) {
                    exitBusinessService(busDto.getId(), busDto.getTargetType(), busDto.getTargetId());
                    break;
                }
            }
        }
    }

    /**
     * 增加对象关系
     *
     * @param type        对象类型
     * @param id          对象ID
     * @param managerId   对方管理员
     * @param companyTeam 分配器
     * @param status      接收状态
     * @param flag        是否开启增加其借款人的关联关系
     */

    private String addBusinessObjectUserRelation(Integer type, Integer id, Integer managerId,
                                                 CompanyTeam companyTeam, Integer status, boolean flag) {

        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(companyTeam.getObjectType(), companyTeam.getObjectId());
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(type);
        query.setObjectId(id);
        query.setUserId(managerId); // 只有管理员才可以接收
        query.setType(BusinessRelationEnum.dispose.getValue());
        query.setEmployerId(companyTeam.getId());
        query.setBusinessId(businessObjRe.getBusinessId());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(query);
        if (relationList != null && relationList.size() > 0) {
            // 已经增加
            return "流转失败，对方已经参与流转";
        } else {
            // 增加对象关系
            ObjectUserRelation objectUserRelation = new ObjectUserRelation();
            objectUserRelation.setObjectType(type);
            objectUserRelation.setObjectId(id);
            objectUserRelation.setUserId(managerId); // 只有管理员才可以接收
            objectUserRelation.setType(BusinessRelationEnum.dispose.getValue());
            objectUserRelation.setStatus(status);
            objectUserRelation.setVisibleType(SysProperty.BOOLEAN_TRUE); // 可见
            objectUserRelation.setEmployerId(companyTeam.getId());
            objectUserRelation.setBusinessId(businessObjRe.getBusinessId());
            objectUserRelationMapper.insert(objectUserRelation);
            if (flag) {
                // 增加所属借款人的对象
                query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                if (ObjectTypeEnum.IOU.getValue().equals(type)) {
                    IOUInfo iouInfo = iouInfoMapper.get(id);
                    query.setObjectId(iouInfo.getLenderId());
                    relationList = objectUserRelationMapper.list(query);
                    if (relationList == null || relationList.size() == 0) {
                        objectUserRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                        objectUserRelation.setObjectId(iouInfo.getLenderId());
                        objectUserRelationMapper.insert(objectUserRelation);
                    }
                } else if (PAWN.getValue().equals(type)) {
                    PawnInfo pawnInfo = pawnInfoMapper.get(id);
                    query.setObjectId(pawnInfo.getLenderId());
                    relationList = objectUserRelationMapper.list(query);
                    if (relationList == null || relationList.size() == 0) {
                        objectUserRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                        objectUserRelation.setObjectId(pawnInfo.getLenderId());
                        objectUserRelationMapper.insert(objectUserRelation);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public JsonResponse exitBusinessService(Integer id, Integer targetType, Integer targetId) throws BusinessLogException {
        if (id == null || targetId == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!CommonUtil.isManage()) {
            return JsonResponseTool.paramErr("权限错误，非平台管理员");
        }
        // 存在该分配数据
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(id);
        if (companyTeamRe == null) {
            return JsonResponseTool.paramErr("参数错误，清收案组成员不存在");
        }
        // 该数据已经删除
        if (companyTeamRe.getStateflag() > 0) {
            return JsonResponseTool.failure("删除失败，已经从分配中移除");
        }
        // 校验是否为业务流转类型
        if (companyTeamRe.getType() != ObjectBusinessTypeEnum.mechanism.getValue().intValue()) {
            return JsonResponseTool.failure("不是业务流转数据，无法删除");
        }
        Integer result = companyTeamReMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        } else {
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            // 添加操作记录
            UserDetail detail = coordinatorMapper.getUserDetail(companyTeamRe.getAccepterId());
            businessLogService.add(companyTeam.getObjectId(), companyTeam.getObjectType(), UserInfoEnum.DISTRIBUTION_DEL_GENERAL.getValue(),
                    "", "移除业务流转对象成员:" + detail.getRealName(), 0, 0);
            // 去除介入信息
            if (companyTeam != null) {
                // 删除操作者与操作事物的关联
                clearBusinessObjectUserRelation(targetType, targetId, companyTeamRe.getRequesterId(), companyTeam);
                // 清除相关联的抵押物或者借据状态值
                clearObjectBusinessStatus(targetType, targetId);
                RelationQuery relationQuery = new RelationQuery();
                if (targetType.equals(ObjectTypeEnum.IOU.getValue())) {
                    relationQuery.setIouId(targetId);
                    List<PiRelation> relationList = piRelationMapper.queryList(relationQuery);
                    for (PiRelation piRelation : relationList) {
                        clearObjectBusinessStatus(PAWN.getValue(), piRelation.getPawnId());
                    }
                } else if (targetType.equals(PAWN.getValue())) {
                    relationQuery.setPawnId(targetId);
                    List<PiRelation> relationList = piRelationMapper.queryList(relationQuery);
                    for (PiRelation piRelation : relationList) {
                        clearObjectBusinessStatus(ObjectTypeEnum.IOU.getValue(), piRelation.getIouId());
                    }
                }
            }
            return JsonResponseTool.success(result);
        }
    }

    /**
     * 清除业务流转对象关系
     *
     * @param type        对象类型
     * @param id          对象ID
     * @param managerId   对方管理员
     * @param companyTeam 分配器
     */
    private void clearBusinessObjectUserRelation(Integer type, Integer id, Integer managerId, CompanyTeam companyTeam) {

        BusinessObjRe businessObjRe = businessObjReMapper.getByObject(companyTeam.getObjectType(), companyTeam.getObjectId());
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setType(type);
        query.setId(id);
        query.setUserId(managerId); // 只有管理员才可以接收
        query.setType(BusinessRelationEnum.dispose.getValue());
        query.setEmployerId(companyTeam.getId());
        query.setBusinessId(businessObjRe.getBusinessId());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(query);
        if (relationList != null && relationList.size() > 0) {
            // 理论上只有一条
            objectUserRelationMapper.deleteByPrimaryKey(relationList.get(0).getId());
        }
        // 判断是否为最后一个关联关系,是则删除该借款人的关联关系
        Integer lenderId = null;
        if (type.equals(PAWN.getValue())) {
            PawnInfo pawnInfo = pawnInfoMapper.get(id);
            if (pawnInfo != null && pawnInfo.getLenderId() != null) {
                lenderId = pawnInfo.getLenderId();
            }
        } else if (type.equals(ObjectTypeEnum.IOU.getValue())) {
            IOUInfo iouInfo = iouInfoMapper.get(id);
            if (iouInfo != null && iouInfo.getLenderId() != null) {
                lenderId = iouInfo.getLenderId();
            }
        }
        query.setType(null);
        query.setId(null);
        relationList = objectUserRelationMapper.list(query);
        Boolean flag = true; // 是最后一个
        if (relationList != null && relationList.size() > 1) {
            for (ObjectUserRelation objectUserRelation : relationList) {
                if (objectUserRelation.getType().equals(PAWN.getValue())) {
                    if (pawnInfoMapper.get(objectUserRelation.getId()).getLenderId().equals(lenderId)) {
                        flag = false;
                        break;
                    }
                } else if (objectUserRelation.getType().equals(ObjectTypeEnum.IOU.getValue())) {
                    if (iouInfoMapper.get(objectUserRelation.getId()).getLenderId().equals(lenderId)) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        if (flag) {
            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
            if (type.equals(PAWN.getValue())) {
                PawnInfo pawnInfo = pawnInfoMapper.get(id);
                if (pawnInfo != null && pawnInfo.getLenderId() != null) {
                    query.setId(pawnInfo.getLenderId());
                    relationList = objectUserRelationMapper.list(query);
                    if (relationList != null && relationList.size() > 0) {
                        // 理论上只有一条
                        objectUserRelationMapper.deleteByPrimaryKey(relationList.get(0).getId());
                    }
                }
            } else if (type.equals(ObjectTypeEnum.IOU.getValue())) {
                IOUInfo iouInfo = iouInfoMapper.get(id);
                if (iouInfo != null && iouInfo.getLenderId() != null) {
                    query.setId(iouInfo.getLenderId());
                    relationList = objectUserRelationMapper.list(query);
                    if (relationList != null && relationList.size() > 0) {
                        // 理论上只有一条
                        objectUserRelationMapper.deleteByPrimaryKey(relationList.get(0).getId());
                    }
                }
            }
        }
    }


    /**
     * 平台或处置机构添加业务流转公司发送的短信接口
     *
     * @param userId            操作者
     * @param receiveUserId     邀请接收者
     * @param businessRequestId 业务请求者
     * @param objectId          对象id
     * @param objectType        对象类型
     * @param flowId            业务流转对象id
     * @param flowType          业务流转对象类型
     */
    private void sendBusinessFlow(Integer userId, Integer receiveUserId, Integer businessRequestId, Integer objectId,
                                  Integer objectType, Integer flowId, Integer flowType, String operUrl, String operation, Integer flowBusinessId) {

        SmsUtil smsUtil = new SmsUtil();
        UserDetail userC = coordinatorMapper.getUserDetail(receiveUserId);
        UserDetail oper = coordinatorMapper.getUserDetail(businessRequestId);
        //操作和业务请求为同一个人就是自己发起邀请的
        Integer code = 0;
        if (userId == businessRequestId) {
            code = SmsEnum.ADD_FLOW_ONESELF.getValue();
        } else {
            code = SmsEnum.ADD_FLOW_COMPANY.getValue();
        }
        String content = smsUtil.sendSms(code, userC.getMobile(),
                userC.getRealName(),
                userService.getCompayTypeToString(oper),
                oper.getCompanyName(),
                userService.getRoleNameToString(oper),
                oper.getRealName(),
                ObjectTypeEnum.getObjectTypeEnum(objectType).getName(),
                coordinatorService.getObjectName(objectType, objectId),
                ObjectTypeEnum.getObjectTypeEnum(flowType).getName(),
                coordinatorService.getObjectName(flowType, flowId),
                operation);
        String title = coordinatorService.getMessageTitle(flowId, flowType, MessageBTEnum.COMPANY_BETWEEN_FLOW.getValue());
        messageService.add(title, content, userId, receiveUserId, "", MessageEnum.TASK.getValue(), MessageBTEnum.COMPANY_BETWEEN_FLOW.getValue(), operUrl, flowBusinessId);
    }

    @Override
    public JsonResponse updateBusinessService(Integer type, Integer id, Integer distributionId,
                                              Integer businessType, Integer status, Integer flowBusinessId,
                                              Integer toPlatform) throws BusinessLogException {
        Integer userId = UserSession.getCurrent().getUserId();
        if (CommonUtil.checkParam(type, id, distributionId, businessType, status)) {
            return JsonResponseTool.paramErr("参数错误");
        }
        if (!ObjectAcceptTypeEnum.accept.getValue().equals(status) && !ObjectAcceptTypeEnum.refuse.getValue().equals(status)) {
            return JsonResponseTool.paramErr("参数错误，操作结果错误"); // 操作状态不对
        }
        if (!type.equals(PAWN.getValue()) && !type.equals(ObjectTypeEnum.IOU.getValue())) {
            return JsonResponseTool.paramErr("参数错误，对象类型不对");
        }
        CompanyTeamRe companyTeamRe = companyTeamReMapper.get(distributionId);
        if (companyTeamRe == null) {
            return JsonResponseTool.paramErr("参数错误，清收案组成员不存在"); // 分配器记录不存在
        }
        if (!companyTeamRe.getAccepterId().equals(userId)) {
            return JsonResponseTool.failure("非流转公司管理员，无权限操作"); // 非被邀请公司的管理员
        }
        companyTeamRe.setStatus(status);
        Integer result = companyTeamReMapper.update(companyTeamRe);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("操作失败，请重新再试");
        } else {
            // 添加操作记录
            if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                // 接收
                businessLogService.add(id, type, UserInfoEnum.DISTRIBUTION_ACCEPT_ADD.getValue(),
                        "", "决定(同意|拒绝)加入业务流转,状态:同意", 0, 0);
            } else {
                // 拒绝
                businessLogService.add(id, type, UserInfoEnum.DISTRIBUTION_REJECT_DEL.getValue(),
                        "", "决定(同意|拒绝)加入业务流转,状态(1接受):拒绝", 0, 0);
            }
            CompanyTeam companyTeam = companyTeamMapper.get(companyTeamRe.getCompanyTeamId());
            if (companyTeam != null) {
                // 获取平台信息
//                List<TCompanyInfo> companyInfoList = companyInfoMapper.listByType(UserInfoEnum.USER_TYPE_ADMIN.getValue());

                // 提醒消息
                TUserInfo creator = userInfoMapper.selectByPrimaryKey(companyTeam.getSenderId()); // 创建人信息
                CompanyDetailInfo companyDetailInfo = companyInfoMapper.getDetailByCompanyId(companyTeamRe.getAcceptCompanyId()); // 被邀请公司信息

                // 修改对象关系
                ObjectUserRelationQuery query = new ObjectUserRelationQuery();
                query.setObjectType(type);
                query.setObjectId(id);
                query.setType(BusinessRelationEnum.dispose.getValue());
                query.setEmployerId(companyTeam.getId());
                query.setUserId(userId);
                List<ObjectUserRelation> list = objectUserRelationMapper.list(query);
                if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                    // 修改数据对象
                    if (list != null && list.size() > 0) {
                        // 理论上只有一条
                        ObjectUserRelation relation = list.get(0);
                        relation.setStatus(ObjectUserStatusEnum.accepted.getValue()); // 这里0为接收
                        objectUserRelationMapper.update(relation);
                        if (PAWN.getValue().equals(type)) {
                            PawnInfo pawnInfo = pawnInfoMapper.get(id);
                            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                            query.setObjectId(pawnInfo.getLenderId());
                            list = objectUserRelationMapper.list(query);
                            if (list != null && list.size() > 0) {
                                // 设置借款人为接受
                                if (SysProperty.BOOLEAN_TRUE.equals(list.get(0).getStatus())) {
                                    list.get(0).setStatus(SysProperty.BOOLEAN_FALSE);
                                    objectUserRelationMapper.update(list.get(0));
                                }
                            }
                            // 修改抵押物数据状态
                            changePawnBusiness(id, businessType, companyDetailInfo);
                            // 修改相关联的借据类型
                            RelationQuery query1 = new RelationQuery();
                            query1.setPawnId(id);
                            List<PiRelation> relationList = piRelationMapper.queryList(query1);
                            relationList.forEach(piRelation -> {
                                // 创建对象关联关系
//                                addBusinessObjectUserRelation(ObjectTypeEnum.IOU.getValue(), piRelation.getIouId(),
//                                        relation.getUserId(), companyTeam,
//                                        ObjectUserStatusEnum.accepted.getValue(), false);
                                // 修改关联借据的相关状态值
                                changeIouBusiness(piRelation.getIouId(), businessType, companyDetailInfo);
                            });
                        } else if (ObjectTypeEnum.IOU.getValue().equals(type)) {
                            IOUInfo iouInfo = iouInfoMapper.get(id);
                            query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                            query.setObjectId(iouInfo.getLenderId());
                            list = objectUserRelationMapper.list(query);
                            if (list != null && list.size() > 0) {
                                // 设置借款人为接受
                                if (SysProperty.BOOLEAN_TRUE.equals(list.get(0).getStatus())) {
                                    list.get(0).setStatus(SysProperty.BOOLEAN_FALSE);
                                    objectUserRelationMapper.update(list.get(0));
                                }
                            }
                            // 修改借据数据状态
                            changeIouBusiness(id, businessType, companyDetailInfo);
                            // 修改相关联的抵押物类型
                            RelationQuery query1 = new RelationQuery();
                            query1.setIouId(id);
                            List<PiRelation> relationList = piRelationMapper.queryList(query1);
                            relationList.forEach(piRelation -> {
                                // 创建对象关联关系
//                                addBusinessObjectUserRelation(ObjectTypeEnum.PAWN.getValue(), piRelation.getPawnId(),
//                                        relation.getUserId(), companyTeam,
//                                        ObjectUserStatusEnum.accepted.getValue(), false);
                                // 修改关联抵押物的关联状态值
                                changePawnBusiness(piRelation.getPawnId(), businessType, companyDetailInfo);
                            });
                        }
                        // 添加公司之间的关系
                        addCompanyRelation(creator.getCompanyId(), companyTeamRe.getAcceptCompanyId());
                        //是中介公司身份的流转需要把抵押物转变为资产源
                        String reg = UserSession.getCurrent() == null ? "" : UserSession.getCurrent().getUserType();
                        if (UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue().toString().equals(reg.substring(0, reg.lastIndexOf(",")))) {
                            zcyService.receivePawn(id, type, 0);//中介公司接受业务流转的抵押物添加到资产源信息
                        }
                        //是律所公司身份的流转需要把抵押物转变为案件
                        if (UserInfoEnum.USER_TYPE_JUDICIARY.getValue().toString().equals(reg.substring(0, reg.lastIndexOf(",")))) {
                            caseService.receivePawn(id, type);//律所公司接受业务流转的抵押物添加到案件信息
                        }
                    }


                    if (companyTeam.getObjectType() == ObjectTypeEnum.LENDER.getValue().intValue()) {
                        //创建协作器
                        LenderInfo lenderInfo = lenderInfoMapper.get(companyTeam.getObjectId());
                        lenderInfo.setOperator(UserSession.getCurrent().getUserId());
                        lenderService.addCoordinator(lenderInfo);
                    }


                } else {
                    if (list != null && list.size() > 0) {
                        // 理论上只有一条
                        objectUserRelationMapper.deleteByPrimaryKey(list.get(0).getId());
                        // 检查是否最后一条数据
                        query = new ObjectUserRelationQuery();
                        query.setObjectType(ObjectTypeEnum.IOU.getValue());
                        query.setType(BusinessRelationEnum.dispose.getValue());
                        query.setEmployerId(companyTeam.getId());
                        query.setUserId(userId);
                        list = objectUserRelationMapper.list(query);
                        if (list == null || list.size() == 0) {
                            if (list == null || list.size() == 0) {
                                if (PAWN.getValue().equals(type)) {
                                    PawnInfo pawnInfo = pawnInfoMapper.get(id);
                                    query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                                    query.setObjectId(pawnInfo.getLenderId());
                                    list = objectUserRelationMapper.list(query);
                                    if (list != null && list.size() > 0) {
                                        // 删除借款人(理论上只有一条)
                                        objectUserRelationMapper.deleteByPrimaryKey(list.get(0).getId());
                                    }
                                } else if (ObjectTypeEnum.IOU.getValue().equals(type)) {
                                    IOUInfo iouInfo = iouInfoMapper.get(id);
                                    query.setObjectType(ObjectTypeEnum.LENDER.getValue());
                                    query.setObjectId(iouInfo.getLenderId());
                                    list = objectUserRelationMapper.list(query);
                                    if (list != null && list.size() > 0) {
                                        // 删除借款人(理论上只有一条)
                                        objectUserRelationMapper.deleteByPrimaryKey(list.get(0).getId());
                                    }
                                }
                            }
                        }
                    }
                    // 删除协作器
                    coordinatorService.delCoordinator(companyTeamRe.getAcceptCompanyId(), type, id);
                    // 删除业务流转数据
                    CompanyTeamRe ctr = new CompanyTeamRe();
                    ctr.setId(companyTeamRe.getId());
                    ctr.setStatus(ObjectAcceptTypeEnum.refuse.getValue());
                    companyTeamReMapper.update(ctr);

                }
                CompanyTeamRe teamRe = companyTeamReMapper.get(distributionId);
                if (setOperStatus(flowBusinessId, UserSession.getCurrent().getUserId(), status, teamRe)) {
                    //修改流转业务状态
                    if (status.equals(ObjectAcceptTypeEnum.accept.getValue())) {
                        setFlowBusiness(UserSession.getCurrent().getUserId(), flowBusinessId, FlowBusinessEnum.FLOW_COMPANY_AGREE.getValue());
                    } else {
                        setFlowBusiness(UserSession.getCurrent().getUserId(), flowBusinessId, FlowBusinessEnum.FLOW_COMPANY_REFUSE.getValue());
                    }
                }
                // 消息提醒
                messageService.respondInvite(companyTeam.getObjectId(), companyTeam.getObjectType(), id, type, userId,
                        companyTeamRe.getRequesterId(), status, flowBusinessId, businessType, toPlatform);
            }
            return JsonResponseTool.success(result);
        }
    }


    /**
     * 增加公司关联信息
     *
     * @param aId
     * @param bId
     * @return
     */
    private Integer addCompanyRelation(Integer aId, Integer bId) {
        if (CommonUtil.checkParam(aId, bId)) {
            return null; // 参数错误
        }
        CompanyRelation relation = companyRelationMapper.getByCompanyId(aId, bId);
        if (relation != null) {
            if (relation.getRelationStatus() != PartnerEnum.relation_status_agree.getValue()) {
                relation.setRelationStatus(PartnerEnum.relation_status_agree.getValue());
                companyRelationMapper.update(relation);
            }
            return null; // 已经存在
        } else {
            relation = new CompanyRelation();
            relation.setCompanyAId(aId);
            relation.setCompanyBId(bId);
            relation.setRelationStatus(PartnerEnum.relation_status_agree.getValue());
            Integer result = companyRelationMapper.insert(relation);
            if (CommonUtil.checkResult(result)) {
                return result;
            } else {
                return relation.getId();
            }
        }
    }


    /**
     * 添加分配器里业务流转的对象数据
     *
     * @param distributionId 分配器ID
     * @param opposite       被分配公司信息
     * @return
     */
    private Integer addCompanyTeamRe(Integer distributionId, CompanyDetailInfo opposite, Integer businessRequestId) {
        CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
        companyTeamReQuery.setTeamId(distributionId);
        companyTeamReQuery.setCompanyId(opposite.getCompanyId());
        companyTeamReQuery.setType(ObjectBusinessTypeEnum.mechanism.getValue());
        List<CompanyTeamRe> companyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
        if (companyTeamReList == null || companyTeamReList.size() == 0) {
            CompanyTeamRe companyTeamRe = new CompanyTeamRe();
            companyTeamRe.setCompanyTeamId(distributionId);
            companyTeamRe.setAcceptCompanyId(opposite.getCompanyId());
            companyTeamRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            companyTeamRe.setType(ObjectBusinessTypeEnum.mechanism.getValue());
            companyTeamRe.setAccepterId(opposite.getUserId());
            companyTeamRe.setRoleType(SysProperty.BOOLEAN_TRUE);
            companyTeamRe.setRequesterId(businessRequestId);
            Integer result = companyTeamReMapper.insert(companyTeamRe);
            if (CommonUtil.checkResult(result)) {
                return null;
            } else {
                return companyTeamRe.getId();
            }
        } else {
            // 理论上只有一条
            return companyTeamReList.get(0).getId();
        }
    }

    /**
     * 为资产包添加处置信息
     *
     * @param id          借款人ID
     * @param companyType 操作公司的类型
     * @param lawType     律所类型
     * @param urgeType    催收类型
     * @param agentType   中介类型
     * @return
     */
    private Integer addAssetDispose(Integer id, Integer companyType, Integer lawType,
                                    Integer urgeType, Integer agentType) {
        if (!CommonUtil.checkParam(id, companyType, lawType, urgeType, agentType)) {
            AssetInfo assetInfo = new AssetInfo();
            assetInfo.setId(id);
            if (companyType.equals(lawType)) { // 律所
                assetInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (companyType.equals(urgeType)) { // 催收
                assetInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
            } else if (companyType.equals(agentType)) { // 中介
                assetInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
            }
            Integer result = assetInfoMapper.update(assetInfo);
            if (CommonUtil.checkResult(result)) {
                return result;
            } else {
                List<LenderInfo> list = lenderInfoMapper.listByAssetId(id);
                list.forEach(lenderInfo -> {
                    if (!isIndependentLender(lenderInfo.getId())) {
                        addLenderDispose(lenderInfo.getId(), companyType, lawType, urgeType, agentType);
                    }
                });
                return id;
            }
        }
        return null;
    }

    /**
     * 为借款人添加处置信息
     *
     * @param id          借款人ID
     * @param companyType 操作公司的类型
     * @param lawType     律所类型
     * @param urgeType    催收类型
     * @param agentType   中介类型
     * @return
     */
    private Integer addLenderDispose(Integer id, Integer companyType, Integer lawType,
                                     Integer urgeType, Integer agentType) {
        if (!CommonUtil.checkParam(id, companyType, lawType, urgeType, agentType)) {
            LenderInfo lenderInfo = new LenderInfo();
            lenderInfo.setId(id);
            if (companyType.equals(lawType)) { // 律所
                lenderInfo.setIsLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (companyType.equals(urgeType)) { // 催收
                lenderInfo.setIsCollection(SysProperty.BOOLEAN_TRUE);
            } else if (companyType.equals(agentType)) { // 中介
                lenderInfo.setIsAgent(SysProperty.BOOLEAN_TRUE);
            }
            Integer result = lenderInfoMapper.update(lenderInfo);
            if (CommonUtil.checkResult(result)) {
                return result;
            } else {
                return id;
            }
        }
        return null;
    }

    /**
     * 清楚资产包以及资产包下的非独立分配的借款人的处置信息
     *
     * @param id 资产包ID
     * @return
     */
    private Integer clearAssetDispose(Integer id) {
        if (id == null) {
            return null;
        }
        AssetInfo assetInfo = new AssetInfo();
        assetInfo.setId(id);
        assetInfo.setIsAgent(SysProperty.DEFAULT);
        assetInfo.setIsCollection(SysProperty.DEFAULT);
        assetInfo.setIsLawyer(SysProperty.DEFAULT);
        Integer result = assetInfoMapper.update(assetInfo);
        if (CommonUtil.checkResult(result)) {
            return result;
        } else {
            List<LenderInfo> list = lenderInfoMapper.listByAssetId(id);
            list.forEach(lenderInfo -> {
                // 不是独立分配的借款人就清楚处置状态
                if (!isIndependentLender(lenderInfo.getId())) {
                    clearLenderDispose(lenderInfo.getId());
                }
            });
            return id;
        }
    }

    /**
     * 查看当前ID的借款人是否是
     *
     * @param id 借款人ID
     * @return true 是独立借款人
     */
    private boolean isIndependentLender(Integer id) {
        if (id != null) {
            CompanyTeam companyTeam = companyTeamMapper.getByTypeId(ObjectTypeEnum.LENDER.getValue(), id);
            if (companyTeam != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 清楚借款人全部处置信息
     *
     * @param id 借款人ID
     * @return
     */
    private Integer clearLenderDispose(Integer id) {
        if (id == null) {
            return null;
        }
        LenderInfo lenderInfo = new LenderInfo();
        lenderInfo.setId(id);
        lenderInfo.setIsAgent(SysProperty.DEFAULT);
        lenderInfo.setIsCollection(SysProperty.DEFAULT);
        lenderInfo.setIsLawyer(SysProperty.DEFAULT);
        Integer result = lenderInfoMapper.update(lenderInfo);
        if (CommonUtil.checkResult(result)) {
            return result;
        } else {
            return id;
        }
    }

    /**
     * 删除对象之间联系关系
     *
     * @param id         对象ID
     * @param employeeId 分配器ID
     * @param userId     被删除对象用户ID
     * @return
     */
    private Integer deleteAssetObjectUserRelation(Integer id, Integer employeeId, Integer userId) {
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
        query.setObjectId(id);
        query.setEmployerId(employeeId);
        query.setUserId(userId);
        query.setType(BusinessRelationEnum.company.getValue());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(query);
        relationList.forEach(objectUserRelation -> {
            objectUserRelationMapper.deleteByPrimaryKey(objectUserRelation.getId());
            // 删除资产包下的借款人
            List<LenderInfo> list = lenderInfoMapper.listByAssetId(id);
            for (LenderInfo lenderInfo : list) {
                deleteAssetObjectUserRelation(lenderInfo.getId(), employeeId, userId);
            }
        });
        return 1;
    }

    /**
     * 删除对象之间联系关系
     *
     * @param id         对象ID
     * @param employeeId 分配器ID
     * @param userId     被删除对象用户ID
     * @return
     */
    private Integer deleteLenderObjectUserRelation(Integer id, Integer employeeId, Integer userId) {
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setObjectType(ObjectTypeEnum.LENDER.getValue());
        query.setObjectId(id);
        query.setEmployerId(employeeId);
        query.setUserId(userId);
        query.setType(BusinessRelationEnum.company.getValue());
        List<ObjectUserRelation> relationList = objectUserRelationMapper.list(query);
        relationList.forEach(objectUserRelation -> {
            objectUserRelationMapper.deleteByPrimaryKey(objectUserRelation.getId());
        });
        return 1;
    }

    private Integer changeIouBusiness(Integer id, Integer businessType, CompanyDetailInfo companyDetailInfo) {
        if (!CommonUtil.checkParam(id, businessType, companyDetailInfo)) {
            IOUInfo iouInfo = iouInfoMapper.get(id);
            if (PawnEnum.MAINTAIN_REGULAR.getValue().equals(businessType)) {
                // 常规催收
                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.MARKET_DISPOSITION.getValue().equals(businessType)) {
                // 市场处置
                iouInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                iouInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.CM_SIMULTANEOUS.getValue().equals(businessType)) {
                // 市场处置&常规催收
                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                        .equals(companyDetailInfo.getType())) {
                    // 催收
                    iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_INTERMEDIARY)
                        .equals(companyDetailInfo.getType())) {
                    // 市场
                    iouInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                }
                iouInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue().equals(businessType)) {
                // 司法化解
                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                iouInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                iouInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
            } else if (PawnEnum.CJ_SIMULTANEOUS.getValue().equals(businessType)) {
                // 司法化解&常规催收
                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                        .equals(companyDetailInfo.getType())) {
                    // 催收
                    iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                        .equals(companyDetailInfo.getType())) {
                    // 律所
                    iouInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                }
                iouInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.CMJ_SIMULTANEOUS.getValue().equals(businessType)) {
                // 市场处置&司法化解&常规催收
                iouInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                iouInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                iouInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
            }
            Integer result = iouInfoMapper.update(iouInfo);
            if (!CommonUtil.checkResult(result)) {
                return id;
            }
        }
        return null;
    }

    /**
     * 改变抵押物的状态
     *
     * @param id                抵押物ID
     * @param businessType      业务流类型
     * @param companyDetailInfo 公司信息
     * @return
     */
    private Integer changePawnBusiness(Integer id, Integer businessType, CompanyDetailInfo companyDetailInfo) {
        if (!CommonUtil.checkParam(id, businessType, companyDetailInfo)) {
            PawnInfo pawnInfo = pawnInfoMapper.get(id);
            if (PawnEnum.MAINTAIN_REGULAR.getValue().equals(businessType)) {
                // 常规催收
                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.MARKET_DISPOSITION.getValue().equals(businessType)) {
                // 市场处置
                pawnInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.CM_SIMULTANEOUS.getValue().equals(businessType)) {
                // 市场处置&常规催收
                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                        .equals(companyDetailInfo.getType())) {
                    // 催收
                    pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_INTERMEDIARY)
                        .equals(companyDetailInfo.getType())) {
                    // 市场
                    pawnInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                }
                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.EXECUTE_JUSTICE_RESOLVE.getValue().equals(businessType)) {
                // 司法化解
                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                pawnInfoMapper.update(pawnInfo);
            } else if (PawnEnum.CJ_SIMULTANEOUS.getValue().equals(businessType)) {
                // 司法化解&常规催收
                if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_URGE)
                        .equals(companyDetailInfo.getType())) {
                    // 催收
                    pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                } else if (SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.U_TYPE_LAW)
                        .equals(companyDetailInfo.getType())) {
                    // 律所
                    pawnInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
                }
                pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
            } else if (PawnEnum.CMJ_SIMULTANEOUS.getValue().equals(businessType)) {
                // 市场处置&司法化解&常规催收
                pawnInfo.setOnAgent(SysProperty.BOOLEAN_FALSE);
                pawnInfo.setOnCollection(SysProperty.BOOLEAN_FALSE);
                pawnInfo.setOnLawyer(SysProperty.BOOLEAN_FALSE);
            }
            Integer result = pawnInfoMapper.update(pawnInfo);
            if (!CommonUtil.checkResult(result)) {
                return id;
            }
        }
        return null;
    }

    /**
     * 清除对象的业务流转状态值
     *
     * @param type 对象类型
     * @param id   对象id
     */
    private void clearObjectBusinessStatus(Integer type, Integer id) {
        if (!CommonUtil.checkParam(type, id)) {
            if (type.equals(PAWN.getValue())) {
                PawnInfo pawnInfo = pawnInfoMapper.get(id);
                if (pawnInfo != null) {
                    pawnInfo.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                    pawnInfo.setOnCollection(SysProperty.BOOLEAN_TRUE);
                    pawnInfo.setOnAgent(SysProperty.BOOLEAN_TRUE);
                    pawnInfoMapper.update(pawnInfo);
                }
            } else if (type.equals(ObjectTypeEnum.IOU.getValue())) {
                IOUInfo info = iouInfoMapper.get(id);
                if (info != null) {
                    info.setOnAgent(SysProperty.BOOLEAN_TRUE);
                    info.setOnCollection(SysProperty.BOOLEAN_TRUE);
                    info.setOnLawyer(SysProperty.BOOLEAN_TRUE);
                    iouInfoMapper.update(info);
                }
            }
        }
    }

}
