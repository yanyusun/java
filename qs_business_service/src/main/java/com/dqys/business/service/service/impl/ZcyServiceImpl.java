package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.business.BusinessMapper;
import com.dqys.business.orm.mapper.business.BusinessObjReMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.zcy.*;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.business.Business;
import com.dqys.business.orm.pojo.business.BusinessObjRe;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.business.orm.pojo.zcy.*;
import com.dqys.business.orm.pojo.zcy.dto.ZcyPawnDTO;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.ZcyService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mkfeng on 2016/7/27.
 */
@Service
public class ZcyServiceImpl implements ZcyService {

    @Autowired
    private ZcyEstatesAddressMapper zcyEstatesAddressMapper;
    @Autowired
    private ZcyEstatesFacilityMapper zcyEstatesFacilityMapper;
    @Autowired
    private ZcyEstatesMapper zcyEstatesMapper;
    @Autowired
    private ZcyExpressMapper zcyExpressMapper;
    @Autowired
    private ZcyKeyMapper zcyKeyMapper;
    @Autowired
    private ZcyMaintainMapper zcyMaintainMapper;
    @Autowired
    private ZcyMaintainOtherMapper zcyMaintainOtherMapper;
    @Autowired
    private ZcyMaintainTaxMapper zcyMaintainTaxMapper;
    @Autowired
    private ZcyOwnerContactsMapper zcyOwnerContactsMapper;
    @Autowired
    private ZcyOwnerMapper zcyOwnerMapper;
    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private BusinessObjReMapper businessObjReMapper;
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private OURelationMapper ouRelationMapper;

    @Override
    public Map getEstates(Integer id) {
        Map map = new HashMap<>();
        map.put("estates", zcyEstatesMapper.selectByPrimaryKey(id));
        ZcyEstatesAddress address = new ZcyEstatesAddress();
        address.setEstatesId(id);
        map.put("address", zcyEstatesAddressMapper.selectBySelective(address));//房源地址
        ZcyEstatesFacility zcyEstatesFacility = new ZcyEstatesFacility();
        zcyEstatesFacility.setEstatesId(id);
        zcyEstatesFacility.setType(0);//配套设施
        map.put("facility", zcyEstatesFacilityMapper.selectBySelective(zcyEstatesFacility));
        zcyEstatesFacility.setType(1);//房源特色
        map.put("feature", zcyEstatesFacilityMapper.selectBySelective(zcyEstatesFacility));
        zcyEstatesFacility.setType(2);//房源缺点
        map.put("defect", zcyEstatesFacilityMapper.selectBySelective(zcyEstatesFacility));
        return map;
    }

    @Override
    public Map getOwner(Integer id) {
        Map map = new HashMap<>();
        ZcyOwner zcyOwner = new ZcyOwner();
        zcyOwner.setEstatesId(id);
        List<ZcyOwner> list = zcyOwnerMapper.selectBySelective(zcyOwner);
        if (list.size() > 0) {
            zcyOwner = list.get(0);
            ZcyOwnerContacts zcyOwnerContacts = new ZcyOwnerContacts();
            zcyOwnerContacts.setOwnerId(zcyOwner.getId());
            map.put("contacts", zcyOwnerContactsMapper.selectBySelective(zcyOwnerContacts));
        }
        map.put("owner", zcyOwner);
        return map;
    }

    @Override
    public Map getMaintain(Integer id) {
        Map map = new HashMap<>();
        ZcyMaintain zcyMaintain = new ZcyMaintain();
        zcyMaintain.setEstatesId(id);
        List<ZcyMaintain> list = zcyMaintainMapper.selectBySelective(zcyMaintain);
        if (list.size() > 0) {
            zcyMaintain = list.get(0);
            ZcyMaintainTax zcyMaintainTax = new ZcyMaintainTax();
            zcyMaintainTax.setMaintainId(zcyMaintain.getId());
            map.put("tax", zcyMaintainTaxMapper.selectBySelective(zcyMaintainTax));
            ZcyMaintainOther zcyMaintainOther = new ZcyMaintainOther();
            zcyMaintainOther.setMaintainId(zcyMaintain.getId());
            zcyMaintainOther.setType(0);//可上学校
            map.put("school", zcyMaintainOtherMapper.selectBySelective(zcyMaintainOther));
            zcyMaintainOther.setType(1);//看房时间;
            map.put("lookHouse", zcyMaintainOtherMapper.selectBySelective(zcyMaintainOther));
        }
        map.put("maintain", zcyMaintain);
        return map;
    }

    @Override
    public Map getKey(Integer id) {
        Map map = new HashMap<>();
        ZcyKey zcyKey = new ZcyKey();
        zcyKey.setEstatesId(id);
        List<ZcyKey> list = zcyKeyMapper.selectBySelective(zcyKey);
        if (list.size() > 0) {
            zcyKey = list.get(0);
        }
        map.put("key", zcyKey);
        return map;
    }

    @Override
    public Map getExpress(Integer id) {
        Map map = new HashMap<>();
        ZcyExpress zcyExpress = new ZcyExpress();
        zcyExpress.setEstatesId(id);
        List<ZcyExpress> list = zcyExpressMapper.selectBySelective(zcyExpress);
        if (list.size() > 0) {
            zcyExpress = list.get(0);
        }
        map.put("express", zcyExpress);
        return map;
    }

    private String getHouseNo() {
        ZcyEstates estates = new ZcyEstates();
        for (int i = 0; i < 5; i++) {
            String houseNo = "ZC" + DateFormatTool.format(new Date(), "yyMM") + CommonUtil.getRandomNum(10000);//资产编号
            estates.setHouseNo(houseNo);
            List<ZcyEstates> estateses = zcyEstatesMapper.selectBySelective(estates);
            if (estateses.size() == 0) {
                return houseNo;
            }
        }
        return null;
    }


    @Override
    public Map addEstates(ZcyEstates zcyEstates, List<ZcyEstatesAddress> address, List<ZcyEstatesFacility> facilities) throws BusinessLogException {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyEstates.getId() == null) {
            //添加资产源信息，并且加入到业务对象中，状态为正在处置
            String codeNo = RandomUtil.getCode(RandomUtil.ESTATES_CODE);//缓存中取编号
            if (codeNo == null) {
                codeNo = getHouseNo();
            }
            zcyEstates.setHouseNo(codeNo);
            TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(MessageUtils.transStringToInt(zcyEstates.getOperator()));
            if (tUserInfo != null) {
                zcyEstates.setCompanyId(tUserInfo.getCompanyId());
            }
            result = zcyEstatesMapper.insertSelective(zcyEstates);
            if (result > 0) {
                Integer burId = businessService.addServiceObject(ObjectTypeEnum.ASSETSOURCE.getValue(), zcyEstates.getId(), null, null);//添加业务对象
                if (burId != null) {//修改业务对象状态，资产源手动录入的不需要审核
                    BusinessObjRe businessObjRe = businessObjReMapper.get(burId);
                    if (businessObjRe != null) {
                        Business business = businessMapper.get(businessObjRe.getBusinessId());
                        business.setStatus(BusinessStatusEnum.dispose.getValue());
                        businessMapper.update(business);
                        //添加object_user_relation
                        OURelation our = new OURelation();
                        our.setObjectId(zcyEstates.getId());
                        our.setObjectType(ObjectTypeEnum.ASSETSOURCE.getValue());
                        our.setType(OURelationEnum.TYPE_ALLOCATION_ONESELF.getValue());
                        our.setStatus(OURelationEnum.STATUS_ACCEPT.getValue());
                        our.setBusinessId(businessObjRe.getBusinessId());
                        ouRelationMapper.insertSelective(our);
                    }
                }
                businessLogService.add(zcyEstates.getId(), ObjectTypeEnum.ASSETSOURCE.getValue(), ObjectLogEnum.add.getValue(), "", "", 0, 0);//添加操作日志
            }
        } else {//修改资产源信息
            result = zcyEstatesMapper.updateByPrimaryKeySelective(zcyEstates);
            zcyEstatesAddressMapper.deleteByPrimaryKey(zcyEstates.getId());
            zcyEstatesFacilityMapper.deleteByPrimaryKey(zcyEstates.getId());
        }
        if (result > 0) {
            //循环添加资产信息中的地址和标签
            if (address != null) {
                for (ZcyEstatesAddress addr : address) {
                    addr.setEstatesId(zcyEstates.getId());
                    zcyEstatesAddressMapper.insertSelective(addr);
                    break;
                }
            }
            if (facilities != null) {
                for (ZcyEstatesFacility fac : facilities) {
                    fac.setEstatesId(zcyEstates.getId());
                    zcyEstatesFacilityMapper.insertSelective(fac);
                }
            }
            map.put("result", "yes");
            map.put("estatesId", zcyEstates.getId());
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> contactses) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyOwner.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        //只有不存在才添加
        ZcyOwner owner = new ZcyOwner();
        owner.setEstatesId(zcyOwner.getEstatesId());
        List<ZcyOwner> list = zcyOwnerMapper.selectBySelective(owner);
        if (list.size() == 0) {
            result = zcyOwnerMapper.insertSelective(zcyOwner);
        } else {
            zcyOwner.setId(list.get(0).getId());
            result = zcyOwnerMapper.updateByPrimaryKeySelective(zcyOwner);
            zcyOwnerContactsMapper.deleteByPrimaryKey(zcyOwner.getId());
        }
        if (result > 0) {
            for (ZcyOwnerContacts con : contactses) {
                con.setOwnerId(zcyOwner.getId());
                zcyOwnerContactsMapper.insertSelective(con);
            }
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> others, List<ZcyMaintainTax> taxes) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyMaintain.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        ZcyMaintain maintain = new ZcyMaintain();
        maintain.setEstatesId(zcyMaintain.getEstatesId());
        List<ZcyMaintain> list = zcyMaintainMapper.selectBySelective(maintain);
        if (list.size() == 0) {
            result = zcyMaintainMapper.insertSelective(zcyMaintain);
        } else {
            zcyMaintain.setId(list.get(0).getId());
            result = zcyMaintainMapper.updateByPrimaryKeySelective(zcyMaintain);
            zcyMaintainOtherMapper.deleteByPrimaryKey(zcyMaintain.getId());
            zcyMaintainTaxMapper.deleteByPrimaryKey(zcyMaintain.getId());
        }
        if (result > 0) {
            for (ZcyMaintainOther oth : others) {
                oth.setMaintainId(zcyMaintain.getId());
                zcyMaintainOtherMapper.insertSelective(oth);
            }
            for (ZcyMaintainTax tax : taxes) {
                tax.setMaintainId(zcyMaintain.getId());
                zcyMaintainTaxMapper.insertSelective(tax);
            }
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addKey(ZcyKey zcyKey) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyKey.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        ZcyKey key = new ZcyKey();
        key.setEstatesId(zcyKey.getEstatesId());
        List<ZcyKey> list = zcyKeyMapper.selectBySelective(key);
        if (list.size() == 0) {
            result = zcyKeyMapper.insertSelective(zcyKey);
        } else {
            zcyKey.setId(list.get(0).getId());
            result = zcyKeyMapper.updateByPrimaryKeySelective(zcyKey);
        }
        if (result > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addExpress(ZcyExpress zcyExpress) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyExpress.getEstatesId() == null) {
            map.put("result", "no_estatesId");
            return map;
        }
        ZcyExpress express = new ZcyExpress();
        express.setEstatesId(zcyExpress.getEstatesId());
        List<ZcyExpress> list = zcyExpressMapper.selectBySelective(express);
        if (list.size() == 0) {
            result = zcyExpressMapper.insertSelective(zcyExpress);
        } else {
            zcyExpress.setId(list.get(0).getId());
            result = zcyExpressMapper.updateByPrimaryKeySelective(zcyExpress);
        }
        if (result > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map awaitReceive(ZcyListQuery zcyListQuery) {
        Map map = new HashMap<>();
        if (zcyListQuery.getStatus() == 0) {//待接收
            getWaitReceive(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == 1) {//待分配
            getWaitAllot(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == 2) {//正在处置
            getZcyDispose(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == 3) {//全部

        }
        map.put("result", "yes");
        return map;
    }

    /**
     * 资产源正在处置列表
     *
     * @param zcyListQuery
     * @param map
     */
    private void getZcyDispose(ZcyListQuery zcyListQuery, Map map) {
        List<ZcyPawnDTO> zcyPawnDTOs = new ArrayList<>();
        Integer count = 0;
        List<Integer> objectIds = coordinatorMapper.findObjectIdByOURelationAndBusiness(zcyListQuery.getUserId(), ObjectTypeEnum.ASSETSOURCE.getValue());
        if (objectIds.size() > 0) {
            zcyListQuery.setObjectIdList(objectIds);//设置显示的记录
            zcyListQuery.setUserId(null);
            zcyListQuery.setStartPage(zcyListQuery.getPage() * zcyListQuery.getPageCount());
            count = coordinatorMapper.selectByZCYListPageCount(zcyListQuery);
            zcyPawnDTOs = coordinatorMapper.selectByZCYListPage(zcyListQuery);
            setZcyPawnDTOs(zcyPawnDTOs);
        }
        map.put("zcyPawnDTOs", zcyPawnDTOs);
        map.put("count", count);
    }

    /**
     * 资产源待分配列表
     *
     * @param zcyListQuery
     * @param map
     */
    private void getWaitAllot(ZcyListQuery zcyListQuery, Map map) {
        List<ZcyPawnDTO> zcyPawnDTOs = new ArrayList<>();
        Integer count = 0;
        List<Integer> objectIds = coordinatorMapper.findObjectIdByOURelation(zcyListQuery.getUserId(), ObjectTypeEnum.ASSETSOURCE.getValue());
        if (objectIds.size() > 0) {
            zcyListQuery.setObjectIdList(objectIds);//设置显示的记录
            zcyListQuery.setUserId(null);
            zcyListQuery.setStartPage(zcyListQuery.getPage() * zcyListQuery.getPageCount());
            count = coordinatorMapper.selectByZCYListPageCount(zcyListQuery);
            zcyPawnDTOs = coordinatorMapper.selectByZCYListPage(zcyListQuery);
            setZcyPawnDTOs(zcyPawnDTOs);
        }
        map.put("zcyPawnDTOs", zcyPawnDTOs);
        map.put("count", count);
    }

    /**
     * 资产源待接收列表
     *
     * @param zcyListQuery
     * @param map
     * @return
     */
    private void getWaitReceive(ZcyListQuery zcyListQuery, Map map) {
        //资产源待接收分两部分，第一部分为协作器内部的分配来的待接收，第二部分为分配器转过来的抵押物待接收
        List<ZcyPawnDTO> zcyPawnDTOs = new ArrayList<>();
        Integer count = 0;
        Integer num = 0;
        List<Integer> objectIds = coordinatorMapper.getObjectIdList(ObjectTypeEnum.ASSETSOURCE.getValue(), zcyListQuery.getUserId(), zcyListQuery.getStatus());
        if (objectIds.size() > 0) {
            zcyListQuery.setObjectIdList(objectIds);//设置资产源待接收
            zcyListQuery.setUserId(null);
            zcyListQuery.setStartPage(zcyListQuery.getPage() * zcyListQuery.getPageCount());
            num = coordinatorMapper.selectByZCYListPageCount(zcyListQuery);
            count += num;
            zcyPawnDTOs = coordinatorMapper.selectByZCYListPage(zcyListQuery);
            setZcyPawnDTOs(zcyPawnDTOs);
        }
        //以下为转过来的抵押物待接收
        List<Integer> objectIdList = coordinatorMapper.getObjectIdList(ObjectTypeEnum.PAWN.getValue(), zcyListQuery.getUserId(), zcyListQuery.getStatus());//查询分配器中的所有待接收的抵押物id
        if (objectIdList.size() > 0) {
            zcyListQuery.setObjectIdList(objectIdList);
            Integer num2 = pawnInfoMapper.pawnListPageCount(zcyListQuery);
            count += num2;
            if (zcyPawnDTOs.size() < zcyListQuery.getPageCount()) {
                zcyListQuery.setPageCount(zcyListQuery.getPageCount() - zcyPawnDTOs.size());
                zcyListQuery.setStartPage(zcyListQuery.getStartPage() - num > 0 ? zcyListQuery.getStartPage() - num : 0);//起始点判断
                if (num2 >= zcyListQuery.getStartPage()) {
                    List<PawnInfo> pawnList = pawnInfoMapper.pawnListPage(zcyListQuery);
                    for (PawnInfo pawn : pawnList) {
                        ZcyPawnDTO dto = new ZcyPawnDTO();
                        dto.setPawnId(pawn.getId());
                        dto.setHouseNo(pawn.getPawnNo());
                        dto.setPriceTotal((pawn.getWorth() / 10000) + "");
                        dto.setAcreage(pawn.getSize());
                        dto.setMaintaining(MessageUtils.transMapToString(coordinatorMapper.getRealName(ObjectTypeEnum.PAWN.getValue(), pawn.getId(), 1), "real_name"));
                        zcyPawnDTOs.add(dto);
                    }
                }
            }
        }
        map.put("zcyPawnDTOs", zcyPawnDTOs);
        map.put("count", count);
    }

    /**
     * 完善资产源列表信息
     *
     * @param zcyPawnDTOs
     */

    private void setZcyPawnDTOs(List<ZcyPawnDTO> zcyPawnDTOs) {
        for (ZcyPawnDTO dto : zcyPawnDTOs) {
            if (dto.getEstatesId() != null) {//查询协作器内的资产源的所属人
                dto.setMaintaining(MessageUtils.transMapToString(coordinatorMapper.getRealName(ObjectTypeEnum.ASSETSOURCE.getValue(), dto.getEstatesId(), 1), "real_name"));//维护人
            }
            dto.setLabel(coordinatorMapper.findLabel(dto.getEstatesId()));//标签
            Integer timeCount = 0;//计算挂牌天数
            if (dto.getEntrustTime() != null) {
                String dateTime = dto.getEntrustTime();
                if (dateTime.matches(DateFormatTool.DATE_FORMAT_10_REG1)) {
                    long start = DateFormatTool.parse(dateTime, DateFormatTool.DATE_FORMAT_10_REG1).getTime();
                    timeCount = (int) (new Date().getTime() - start) / (1000 * 3600 * 24);
                }
            }
            dto.setHangShingle(timeCount.toString());
        }
    }

    @Override
    public Map verifyEstates(ZcyEstates zcyEstates, List<ZcyEstatesAddress> zcyEstatesAddressList, List<ZcyEstatesFacility> zcyEstatesFacilities) {
        Map<String, Object> map = new HashMap<>();

        map.put("result", "yes");
        return map;
    }

    @Override
    public Map verifyOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> zcyOwnerContactses) {
        Map<String, Object> map = new HashMap<>();

        map.put("result", "yes");
        return map;
    }

    @Override
    public Map verifyMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> zcyMaintainOthers, List<ZcyMaintainTax> zcyMaintainTaxes) {
        Map<String, Object> map = new HashMap<>();

        map.put("result", "yes");
        return map;
    }

    @Override
    public Map verifyKey(ZcyKey zcyKey) {
        Map<String, Object> map = new HashMap<>();

        map.put("result", "yes");
        return map;
    }

    @Override
    public Map verifyExpress(ZcyExpress zcyExpress) {
        Map<String, Object> map = new HashMap<>();

        map.put("result", "yes");
        return map;
    }

    @Override
    public Map receivePawn(Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        Map map = new HashMap<>();
        //转过来的抵押物加入到资产源
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        if (ObjectTypeEnum.PAWN.getValue() == objectType && status == 0) {//抵押物同意接收
            PawnInfo pawnInfo = pawnInfoMapper.get(objectId);
            if (pawnInfo == null) {
                map.put("result", "no_exist");
            } else {
                ZcyEstates zcyEstates = new ZcyEstates();
                zcyEstates.setHouseNo(RandomUtil.getCode(RandomUtil.ESTATES_CODE));
                zcyEstates.setObjectType(objectType);
                zcyEstates.setObjectId(objectType);
                zcyEstates.setSellingPrice((pawnInfo.getWorth() / 10000));
                if (pawnInfo.getSize().matches("\\d+([.]\\d+)?")) {
                    zcyEstates.setAcreage(Double.parseDouble(pawnInfo.getSize()));
                }
                zcyEstatesMapper.insert(zcyEstates);//抵押物信息转到资产源表
                Integer burId = businessService.addServiceObject(ObjectTypeEnum.ASSETSOURCE.getValue(), zcyEstates.getId(), objectType, objectId);//添加业务对象
                businessLogService.add(zcyEstates.getId(), ObjectTypeEnum.ASSETSOURCE.getValue(), ObjectLogEnum.add.getValue(), "", "", 0, 0);//添加操作日志
                OURelation ouRelation = new OURelation();
                ouRelation.setObjectId(objectId);
                ouRelation.setUserId(userId);
                ouRelation.setObjectType(objectType);
                List<OURelation> list = ouRelationMapper.selectBySelective(ouRelation);//查询抵押物的事物关系表
                if (list.size() > 0) {
                    OURelation relation = list.get(0);
                    relation.setAcceptStatus(OURelationEnum.ACCEPT_STATUS_INIT.getValue());
                    ouRelationMapper.updateByPrimaryKeySelective(relation);//修改接收状态
                    BusinessObjRe businessObjRe = businessObjReMapper.get(burId == null ? 0 : burId);
                    if (businessObjRe != null) {
                        //添加object_user_relation
                        OURelation our = new OURelation();
                        our.setObjectId(zcyEstates.getId());
                        our.setObjectType(ObjectTypeEnum.ASSETSOURCE.getValue());
                        our.setType(OURelationEnum.TYPE_ALLOCATION_COMPANY.getValue());
                        our.setStatus(OURelationEnum.STATUS_ACCEPT.getValue());
                        our.setBusinessId(businessObjRe.getBusinessId());
                        our.setEmployerId(relation.getEmployerId());
                        ouRelationMapper.insertSelective(our);//添加资产源的事物关系表信息
                    }
                }

                map.put("result", "yes");
            }
        } else if (ObjectTypeEnum.ASSETSOURCE.getValue().equals(objectType) && status == 0) {//资产源同意接收

        }
        return map;
    }

    @Override
    public Map zcyDetail(Integer estatesId) {
        Map map = new HashMap<>();
        Map<String, Object> zcy = zcyEstatesMapper.selectDetailByZcy(estatesId);
        if (zcy != null) {
            map.put("detail", zcy);
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }
}
