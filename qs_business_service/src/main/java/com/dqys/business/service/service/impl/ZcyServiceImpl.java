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
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.ZcyService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.FormatValidateTool;
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
            getMap(map, "操作失败");
        }
        return map;
    }

    @Override
    public Map addOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> contactses) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyOwner.getEstatesId() == null) {
            return getMap(map, "资产源id有误");
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
            getMap(map, "操作失败");
        }
        return map;
    }

    @Override
    public Map addMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> others, List<ZcyMaintainTax> taxes) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyMaintain.getEstatesId() == null) {
            return getMap(map, "资产源id有误");
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
            getMap(map, "操作失败");
        }
        return map;
    }

    @Override
    public Map addKey(ZcyKey zcyKey) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyKey.getEstatesId() == null) {
            return getMap(map, "资产源id有误");
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
            getMap(map, "操作失败");
        }
        return map;
    }

    @Override
    public Map addExpress(ZcyExpress zcyExpress) {
        Map map = new HashMap<>();
        Integer result = 0;
        if (zcyExpress.getEstatesId() == null) {
            return getMap(map, "资产源id有误");
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
            getMap(map, "操作失败");
        }
        return map;
    }

    /**
     * 组合失败返回信息
     *
     * @param map
     * @param msg
     * @return
     */
    private Map getMap(Map map, String msg) {
        map.put("result", "no");
        map.put("msg", msg);
        return map;
    }

    private String setMsg(String msg, String str) {
        msg += msg + str + "填写有误；";
        return msg;
    }

    @Override
    public Map awaitReceive(ZcyListQuery zcyListQuery) {
        Map map = new HashMap<>();
        if (zcyListQuery.getStatus() == ObjectTabEnum.accept.getValue().intValue()) {//待接收
            getWaitReceive(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.assign.getValue().intValue()) {//待分配
            getWaitAllot(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.handling_urge.getValue().intValue()) {//正在处置
            getZcyDispose(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.all.getValue().intValue()) {//全部
            getZcyAll(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.new48h.getValue().intValue()) {//48H 新
            //当前时间减去两天，两天内的记录
            zcyListQuery.setDayNum(2);
            getZcyAll(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.month.getValue().intValue()) {//当月
            //用当前年月去查询录入时间
            zcyListQuery.setSameMonth(DateFormatTool.format("yyyy-MM"));
            getZcyAll(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.over.getValue().intValue()) {//完成
            zcyListQuery.setResultStatus(3);
            getZcyAll(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.stop.getValue().intValue()) {//暂停
            //资产源的结果状态（0正常1暂停2无效3完成）
            zcyListQuery.setResultStatus(1);
            getZcyAll(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.invalid.getValue().intValue()) {//无效
            zcyListQuery.setResultStatus(2);
            getZcyAll(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.outTime.getValue().intValue()) {//超时
            //任务超过六小时没有接收的
            zcyListQuery.setHourNum(6);
            getWaitReceive(zcyListQuery, map);
        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.stock.getValue().intValue()) {//存量

        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.focus.getValue().intValue()) {//聚焦

        }
        if (zcyListQuery.getStatus() == ObjectTabEnum.apply.getValue().intValue()) {//待申请

        }
        map.put("result", "yes");
        return map;
    }

    /**
     * 资产源全部列表
     *
     * @param zcyListQuery
     * @param map
     */
    private void getZcyAll(ZcyListQuery zcyListQuery, Map map) {
        List<ZcyPawnDTO> zcyPawnDTOs = new ArrayList<>();
        Integer count = 0;
        Integer num = 0;
        List<Integer> objectIds = coordinatorMapper.findObjectIdByAssetSourceAll(zcyListQuery.getUserId(), ObjectTypeEnum.ASSETSOURCE.getValue());//查询自己所参与的和自己录入的资产源
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
//        List<Integer> objectIdList = coordinatorMapper.getObjectIdList(ObjectTypeEnum.PAWN.getValue(), zcyListQuery.getUserId(), 0);//查询分配器中的所有待接收的抵押物id
//        count = setWaitReceivePawn(zcyListQuery, zcyPawnDTOs, count, num, objectIdList);
        map.put("zcyPawnDTOs", zcyPawnDTOs);
        map.put("count", count);
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
//        List<Integer> objectIds = coordinatorMapper.findObjectIdByOURelationAndBusiness(zcyListQuery.getUserId(), ObjectTypeEnum.PAWN.getValue());//针对抵押物的
//        count = setWaitReceivePawn(zcyListQuery, zcyPawnDTOs, count, 0, objectIds);
        List<Integer> objectIds = coordinatorMapper.findObjectIdByOURelationAndBusiness(zcyListQuery.getUserId(), ObjectTypeEnum.ASSETSOURCE.getValue());//针对资产源的
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
        List<Integer> objectIds = coordinatorMapper.getObjectIdList(ObjectTypeEnum.ASSETSOURCE.getValue(), zcyListQuery.getUserId(), 0);
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
//        List<Integer> objectIdList = coordinatorMapper.getObjectIdList(ObjectTypeEnum.PAWN.getValue(), zcyListQuery.getUserId(), 0);//查询分配器中的所有待接收的抵押物id
//        count = setWaitReceivePawn(zcyListQuery, zcyPawnDTOs, count, num, objectIdList);
        map.put("zcyPawnDTOs", zcyPawnDTOs);
        map.put("count", count);
    }

    /**
     * 设置抵押物
     *
     * @param zcyListQuery
     * @param zcyPawnDTOs
     * @param count
     * @param num
     * @return
     */
    private Integer setWaitReceivePawn(ZcyListQuery zcyListQuery, List<ZcyPawnDTO> zcyPawnDTOs, Integer count, Integer num, List<Integer> objectIdList) {
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
        return count;
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
        String msg = "";
        if (CommonUtil.checkParam(zcyEstates)) {
            return getMap(map, "信息不可为空");
        }
        if (zcyEstates.getAnnotation() != null && zcyEstates.getAnnotation().getBytes().length > 200) {
            msg = setMsg(msg, "注释");
            return getMap(map, msg);
        }
        if (zcyEstates.getReplenish() != null && zcyEstates.getReplenish().getBytes().length > 20) {
            msg = setMsg(msg, "补充");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseS() != null && zcyEstates.getHouseS().getBytes().length > 5) {
            msg = setMsg(msg, "户型（室）");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseT() != null && zcyEstates.getHouseT().getBytes().length > 5) {
            msg = setMsg(msg, "户型（厅）");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseC() != null && zcyEstates.getHouseC().getBytes().length > 5) {
            msg = setMsg(msg, "户型（厨）");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseW() != null && zcyEstates.getHouseW().getBytes().length > 5) {
            msg = setMsg(msg, "户型（卫）");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseRemark() != null && zcyEstates.getHouseRemark().getBytes().length > 255) {
            msg = setMsg(msg, "户型备注");
            return getMap(map, msg);
        }
        if (zcyEstates.getDeck() != null && zcyEstates.getDeck().getBytes().length > 10) {
            msg = setMsg(msg, "层面");
            return getMap(map, msg);
        }
        if (zcyEstates.getQuota() != null && zcyEstates.getQuota().getBytes().length > 5) {
            msg = setMsg(msg, "限购");
            return getMap(map, msg);
        }
        if (zcyEstates.getAcreage() != null && !"".equals(zcyEstates.getAcreage()) && (!FormatValidateTool.isDecimals(zcyEstates.getAcreage(), 2) || zcyEstates.getSellingPrice().toString().length() > 10)) {
            msg = setMsg(msg, "面积");
            return getMap(map, msg);
        }
        if (zcyEstates.getSellingPrice() != null && !"".equals(zcyEstates.getSellingPrice()) && (!FormatValidateTool.isDecimals(zcyEstates.getSellingPrice(), 2) || zcyEstates.getSellingPrice().toString().length() > 10)) {
            msg = setMsg(msg, "平台售价");
            return getMap(map, msg);
        }
        if (zcyEstates.getDecade() != null && (!FormatValidateTool.isDecimals(zcyEstates.getDecade(), 0) || zcyEstates.getDecade().toString().length() > 4)) {
            msg = setMsg(msg, "年代");
            return getMap(map, msg);
        }
        if (zcyEstates.getProperty() != null && zcyEstates.getProperty().getBytes().length > 50) {
            msg = setMsg(msg, "产权");
            return getMap(map, msg);
        }
        if (zcyEstates.getOrientation() != null && zcyEstates.getOrientation().getBytes().length > 50) {
            msg = setMsg(msg, "朝向");
            return getMap(map, msg);
        }
        if (zcyEstates.getGuidePrice() != null && !"".equals(zcyEstates.getGuidePrice()) && (!FormatValidateTool.isDecimals(zcyEstates.getGuidePrice(), 2) || zcyEstates.getGuidePrice().toString().length() > 10)) {
            msg = setMsg(msg, "过户指导价");
            return getMap(map, msg);
        }
        if (zcyEstates.getGuideRemark() != null && zcyEstates.getGuideRemark().getBytes().length > 255) {
            msg = setMsg(msg, "过户指导价备注");
            return getMap(map, msg);
        }
        if (zcyEstates.getEntrustType() != null && zcyEstates.getEntrustType().getBytes().length > 255) {
            msg = setMsg(msg, "委托类型");
            return getMap(map, msg);
        }
        if (zcyEstates.getDecoratePrice() != null && !"".equals(zcyEstates.getDecoratePrice()) && (!FormatValidateTool.isDecimals(zcyEstates.getDecoratePrice(), 2) || zcyEstates.getDecoratePrice().toString().length() > 10)) {
            msg = setMsg(msg, "装修费用");
            return getMap(map, msg);
        }
        if (zcyEstates.getTenementPrice() != null && !"".equals(zcyEstates.getTenementPrice()) && (!FormatValidateTool.isDecimals(zcyEstates.getTenementPrice(), 2) || zcyEstates.getTenementPrice().toString().length() > 10)) {
            msg = setMsg(msg, "物业费");
            return getMap(map, msg);
        }
        if (zcyEstates.getFacility() != null && zcyEstates.getFacility().getBytes().length > 255) {
            msg = setMsg(msg, "嫌恶设施");
            return getMap(map, msg);
        }
        if (zcyEstates.getInternalNumber() != null && zcyEstates.getInternalNumber().getBytes().length > 100) {
            msg = setMsg(msg, "内部编号");
            return getMap(map, msg);
        }
        if (zcyEstates.getTitle() != null && zcyEstates.getTitle().getBytes().length > 255) {
            msg = setMsg(msg, "标题");
            return getMap(map, msg);
        }
        if (zcyEstates.getDecorateType() != null && zcyEstates.getDecorateType().getBytes().length > 50) {
            msg = setMsg(msg, "装修类型");
            return getMap(map, msg);
        }
        if (zcyEstates.getDecorateCase() != null && zcyEstates.getDecorateCase().getBytes().length > 100) {
            msg = setMsg(msg, "装修情况");
            return getMap(map, msg);
        }
        if (zcyEstates.getDecorateTime() != null && zcyEstates.getDecorateTime().getBytes().length > 50) {
            msg = setMsg(msg, "装修时间");
            return getMap(map, msg);
        }
        if (zcyEstates.getBuildType() != null && zcyEstates.getBuildType().getBytes().length > 50) {
            msg = setMsg(msg, "建筑类型");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseBelong() != null && zcyEstates.getHouseBelong().getBytes().length > 50) {
            msg = setMsg(msg, "房屋权属");
            return getMap(map, msg);
        }
        if (zcyEstates.getHouseUse() != null && zcyEstates.getHouseUse().getBytes().length > 50) {
            msg = setMsg(msg, "房屋用途");
            return getMap(map, msg);
        }
        if (zcyEstates.getHeatingWay() != null && zcyEstates.getHeatingWay().getBytes().length > 50) {
            msg = setMsg(msg, "供暖方式");
            return getMap(map, msg);
        }
        if (zcyEstates.getMetro() != null && zcyEstates.getMetro().getBytes().length > 50) {
            msg = setMsg(msg, "距地铁");
            return getMap(map, msg);
        }
        if (zcyEstates.getSchoolHouse() != null && zcyEstates.getSchoolHouse().getBytes().length > 50) {
            msg = setMsg(msg, "学区房");
            return getMap(map, msg);
        }
        if (!"".equals(msg)) {
            return getMap(map, msg);
        } else {
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Map verifyOwner(ZcyOwner zcyOwner, List<ZcyOwnerContacts> zcyOwnerContactses) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if (CommonUtil.checkParam(zcyOwner)) {
            return getMap(map, "信息不可为空");
        }
        if (zcyOwner.getSellPrice() != null && !"".equals(zcyOwner.getSellPrice()) && (!FormatValidateTool.isDecimals(zcyOwner.getSellPrice(), 2) || zcyOwner.getSellPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "出售价格");
            return getMap(map, msg);
        }
        if (zcyOwner.getOwnerPrice() != null && !"".equals(zcyOwner.getOwnerPrice()) && (!FormatValidateTool.isDecimals(zcyOwner.getOwnerPrice(), 2) || zcyOwner.getOwnerPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "业主净得价");
            return getMap(map, msg);
        }
        if (zcyOwner.getAnnotationName() != null && zcyOwner.getAnnotationName().getBytes().length > 255) {
            msg = setMsg(msg, "注释名称");
            return getMap(map, msg);
        }
        if (zcyOwner.getAnnotationContent() != null && zcyOwner.getAnnotationContent().getBytes().length > 255) {
            msg = setMsg(msg, "注释内容");
            return getMap(map, msg);
        }
        if (zcyOwner.getOwnerNumber() != null && zcyOwner.getOwnerNumber().getBytes().length > 100) {
            msg = setMsg(msg, "业主编号");
            return getMap(map, msg);
        }
        if (zcyOwner.getEntrustSource() != null && zcyOwner.getEntrustSource().getBytes().length > 10) {
            msg = setMsg(msg, "委托来源");
            return getMap(map, msg);
        }
        if (zcyOwner.getEntrustDetail() != null && zcyOwner.getEntrustDetail().getBytes().length > 10) {
            msg = setMsg(msg, "详细来源");
            return getMap(map, msg);
        }
        if (zcyOwner.getOwnerMariage() != null && zcyOwner.getOwnerMariage().getBytes().length > 5) {
            msg = setMsg(msg, "业主婚姻");
            return getMap(map, msg);
        }
        if (zcyOwner.getSpecialHouse() != null && zcyOwner.getSpecialHouse().getBytes().length > 5) {
            msg = setMsg(msg, "特殊房源");
            return getMap(map, msg);
        }
        if (!"".equals(msg)) {
            return getMap(map, msg);
        } else {
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Map verifyMaintain(ZcyMaintain zcyMaintain, List<ZcyMaintainOther> zcyMaintainOthers, List<ZcyMaintainTax> zcyMaintainTaxes) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if (CommonUtil.checkParam(zcyMaintain)) {
            return getMap(map, "信息不可为空");
        }
        if (zcyMaintain.getOriginalPrice() != null && !"".equals(zcyMaintain.getOriginalPrice()) && (!FormatValidateTool.isDecimals(zcyMaintain.getOriginalPrice(), 2) || zcyMaintain.getOriginalPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "原购房价格");
            return getMap(map, msg);
        }
        if (zcyMaintain.getMarketPrice() != null && !"".equals(zcyMaintain.getMarketPrice()) && (!FormatValidateTool.isDecimals(zcyMaintain.getMarketPrice(), 2) || zcyMaintain.getMarketPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "市场评估价");
            return getMap(map, msg);
        }
        if (zcyMaintain.getAgencyPrice() != null && !"".equals(zcyMaintain.getAgencyPrice()) && (!FormatValidateTool.isDecimals(zcyMaintain.getAgencyPrice(), 2) || zcyMaintain.getAgencyPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "代理价格");
            return getMap(map, msg);
        }
        if (zcyMaintain.getProfitPrice() != null && !"".equals(zcyMaintain.getProfitPrice()) && (!FormatValidateTool.isDecimals(zcyMaintain.getProfitPrice(), 2) || zcyMaintain.getProfitPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "建议设置利润价");
            return getMap(map, msg);
        }
        if (zcyMaintain.getRemodelingsPrice() != null && !"".equals(zcyMaintain.getRemodelingsPrice()) && (!FormatValidateTool.isDecimals(zcyMaintain.getRemodelingsPrice(), 2) || zcyMaintain.getRemodelingsPrice().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "装修费用");
            return getMap(map, msg);
        }
        if (zcyMaintain.getLoanAmount() != null && !"".equals(zcyMaintain.getLoanAmount()) && (!FormatValidateTool.isDecimals(zcyMaintain.getLoanAmount(), 2) || zcyMaintain.getLoanAmount().toString().getBytes().length > 10)) {
            msg = setMsg(msg, "贷款金额");
            return getMap(map, msg);
        }
        if (zcyMaintain.getAdvantage() != null && zcyMaintain.getAdvantage().getBytes().length > 255) {
            msg = setMsg(msg, "优劣势");
            return getMap(map, msg);
        }
        if (zcyMaintain.getAdaptability() != null && zcyMaintain.getAdaptability().getBytes().length > 255) {
            msg = setMsg(msg, "配合度");
            return getMap(map, msg);
        }
        if (zcyMaintain.getProposition() != null && zcyMaintain.getProposition().getBytes().length > 255) {
            msg = setMsg(msg, "销售建议");
            return getMap(map, msg);
        }
        if (zcyMaintain.getBonusPacks() != null && zcyMaintain.getBonusPacks().getBytes().length > 255) {
            msg = setMsg(msg, "附加赠送");
            return getMap(map, msg);
        }
        if (zcyMaintain.getOwnerClaim() != null && zcyMaintain.getOwnerClaim().getBytes().length > 255) {
            msg = setMsg(msg, "业主要求");
            return getMap(map, msg);
        }
        if (zcyMaintain.getWarn() != null && zcyMaintain.getWarn().getBytes().length > 255) {
            msg = setMsg(msg, "特别提醒");
            return getMap(map, msg);
        }
        if (zcyMaintain.getReplenish() != null && zcyMaintain.getReplenish().getBytes().length > 255) {
            msg = setMsg(msg, "补充");
            return getMap(map, msg);
        }
        if (zcyMaintain.getFulls() != null && zcyMaintain.getFulls().getBytes().length > 2) {
            msg = setMsg(msg, "满几年");
            return getMap(map, msg);
        }
        if (zcyMaintain.getSole() != null && zcyMaintain.getSole().getBytes().length > 2) {
            msg = setMsg(msg, "唯一");
            return getMap(map, msg);
        }
        if (zcyMaintain.getAccount() != null && zcyMaintain.getAccount().getBytes().length > 5) {
            msg = setMsg(msg, "户口状况");
            return getMap(map, msg);
        }
        if (zcyMaintain.getOwned() != null && zcyMaintain.getOwned().getBytes().length > 5) {
            msg = setMsg(msg, "是否共有");
            return getMap(map, msg);
        }
        if (zcyMaintain.getHouseCase() != null && zcyMaintain.getHouseCase().getBytes().length > 5) {
            msg = setMsg(msg, "房屋状况");
            return getMap(map, msg);
        }
        if (zcyMaintain.getDecorateCase() != null && zcyMaintain.getDecorateCase().getBytes().length > 2) {
            msg = setMsg(msg, "装修状况");
            return getMap(map, msg);
        }
        if (zcyMaintain.getDecorateType() != null && zcyMaintain.getDecorateType().getBytes().length > 2) {
            msg = setMsg(msg, "装修类型");
            return getMap(map, msg);
        }
        if (zcyMaintain.getDecorateTime() != null && zcyMaintain.getDecorateTime().getBytes().length > 20) {
            msg = setMsg(msg, "装修时间");
            return getMap(map, msg);
        }
        if (zcyMaintain.getSchoolHouseNumber() != null && zcyMaintain.getSchoolHouseNumber().getBytes().length > 10) {
            msg = setMsg(msg, "学区房名额");
            return getMap(map, msg);
        }
        if (zcyMaintain.getKeyes() != null && zcyMaintain.getKeyes().getBytes().length > 2) {
            msg = setMsg(msg, "是否能留钥匙");
            return getMap(map, msg);
        }
        if (zcyMaintain.getLookHouseTime() != null && zcyMaintain.getLookHouseTime().getBytes().length > 20) {
            msg = setMsg(msg, "看房时间");
            return getMap(map, msg);
        }
        if (zcyMaintain.getCertificateComplete() != null && zcyMaintain.getCertificateComplete().getBytes().length > 2) {
            msg = setMsg(msg, "两证是否齐全");
            return getMap(map, msg);
        }
        if (zcyMaintain.getEquityNum() != null && zcyMaintain.getEquityNum().getBytes().length > 20) {
            msg = setMsg(msg, "产权共有人数");
            return getMap(map, msg);
        }
        if (zcyMaintain.getSign() != null && zcyMaintain.getSign().getBytes().length > 2) {
            msg = setMsg(msg, "签约当日能否到场");
            return getMap(map, msg);
        }
        if (zcyMaintain.getLoan() != null && zcyMaintain.getLoan().getBytes().length > 2) {
            msg = setMsg(msg, "是否有贷款");
            return getMap(map, msg);
        }
        if (zcyMaintain.getLoanPrice() != null && !"".equals(zcyMaintain.getLoanPrice()) && (!FormatValidateTool.isDecimals(zcyMaintain.getLoanPrice(), 2) || zcyMaintain.getLoanPrice().getBytes().length > 10)) {
            msg = setMsg(msg, "贷款金额");
            return getMap(map, msg);
        }
        if (zcyMaintain.getInHouse() != null && zcyMaintain.getInHouse().getBytes().length > 2) {
            msg = setMsg(msg, "户口是否在本房屋内");
            return getMap(map, msg);
        }
        if (zcyMaintain.getGive() != null && zcyMaintain.getGive().getBytes().length > 2) {
            msg = setMsg(msg, "家具家电是否赠送");
            return getMap(map, msg);
        }
        if (zcyMaintain.getCarport() != null && zcyMaintain.getCarport().getBytes().length > 255) {
            msg = setMsg(msg, "是否有车位");
            return getMap(map, msg);
        }
        if (zcyMaintain.getHouseOccupy() != null && zcyMaintain.getHouseOccupy().getBytes().length > 2) {
            msg = setMsg(msg, "学区房是否被占用");
            return getMap(map, msg);
        }
        if (zcyMaintain.getOccupyTime() != null && zcyMaintain.getOccupyTime().getBytes().length > 2) {
            msg = setMsg(msg, "已占用多久");
            return getMap(map, msg);
        }
        if (zcyMaintain.getExpectTime() != null && zcyMaintain.getExpectTime().getBytes().length > 20) {
            msg = setMsg(msg, "预期售房时间");
            return getMap(map, msg);
        }
        if (zcyMaintain.getFullFive() != null && zcyMaintain.getFullFive().getBytes().length > 2) {
            msg = setMsg(msg, "是否满五唯一");
            return getMap(map, msg);
        }
        if (zcyMaintain.getPayWay() != null && zcyMaintain.getPayWay().getBytes().length > 10) {
            msg = setMsg(msg, "付款方式有何要求");
            return getMap(map, msg);
        }
        if (!"".equals(msg)) {
            return getMap(map, msg);
        } else {
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Map verifyKey(ZcyKey zcyKey) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if (CommonUtil.checkParam(zcyKey)) {
            return getMap(map, "信息不可为空");
        }
        if (zcyKey.getProtocolNo() != null && zcyKey.getProtocolNo().getBytes().length > 20) {
            msg = setMsg(msg, "协议编号");
            return getMap(map, msg);
        }
        if (zcyKey.getEntrustProtocolTime() != null && zcyKey.getEntrustProtocolTime().getBytes().length > 20) {
            msg = setMsg(msg, "委托协议时间");
            return getMap(map, msg);
        }
        if (zcyKey.getEntrustAbortTime() != null && zcyKey.getEntrustAbortTime().getBytes().length > 20) {
            msg = setMsg(msg, "委托截止时间");
            return getMap(map, msg);
        }
        if (zcyKey.getKeyNum() != null && zcyKey.getKeyNum().getBytes().length > 255) {
            msg = setMsg(msg, "钥匙套数");
            return getMap(map, msg);
        }
        if (zcyKey.getKeyPlace() != null && zcyKey.getKeyPlace().getBytes().length > 100) {
            msg = setMsg(msg, "钥匙存放位置");
            return getMap(map, msg);
        }
        if (zcyKey.getKeyFollow() != null && zcyKey.getKeyFollow().getBytes().length > 255) {
            msg = setMsg(msg, "钥匙跟进");
            return getMap(map, msg);
        }
        if (!"".equals(msg)) {
            return getMap(map, msg);
        } else {
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Map verifyExpress(ZcyExpress zcyExpress) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        if (CommonUtil.checkParam(zcyExpress)) {
            return getMap(map, "信息不可为空");
        }
        if (zcyExpress.getProtocolNo().getBytes().length > 20) {
            msg = setMsg(msg, "协议编号");
        }
        if (!FormatValidateTool.isDecimals(zcyExpress.getExpressPrice(), 2) || zcyExpress.getExpressPrice().getBytes().length > 10) {
            msg = setMsg(msg, "速卖价格");
        }
        if (zcyExpress.getEntrustAbortTime().getBytes().length > 20) {
            msg = setMsg(msg, "速卖委托截止时间");
        }
        if (zcyExpress.getEntrustProtocolTime().getBytes().length > 20) {
            msg = setMsg(msg, "速卖委托协议时间");
        }
        if (!FormatValidateTool.isDecimals(zcyExpress.getEntrustDeposit(), 2) || zcyExpress.getEntrustDeposit().getBytes().length > 10) {
            msg = setMsg(msg, "委托保证金");
        }
        if (zcyExpress.getExpressPeople().getBytes().length > 20) {
            msg = setMsg(msg, "速 卖 人");
        }
        if (zcyExpress.getTeam().getBytes().length > 10) {
            msg = setMsg(msg, "所属团队");
        }
        if (zcyExpress.getExpressFollow().getBytes().length > 255) {
            msg = setMsg(msg, "速卖跟进");
        }
        if (zcyExpress.getImgUrl().getBytes().length > 50) {
            msg = setMsg(msg, "协议扫描件");
        }
        if (!"".equals(msg)) {
            return getMap(map, msg);
        } else {
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Map receivePawn(Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        Map map = new HashMap<>();
        //转过来的抵押物加入到资产源
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        //抵押物同意接收
        if (ObjectTypeEnum.PAWN.getValue().intValue() == objectType && status == 0) {
            PawnInfo pawnInfo = pawnInfoMapper.get(objectId);
            if (pawnInfo == null) {
                map.put("result", "no_exist");
            } else {
                ZcyEstates zcyEstates = new ZcyEstates();
                zcyEstates.setHouseNo(RandomUtil.getCode(RandomUtil.ESTATES_CODE));
                zcyEstates.setObjectType(objectType);
                zcyEstates.setObjectId(objectType);
                zcyEstates.setStatus(1);//资产源来源是转过来
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
        } else if (ObjectTypeEnum.ASSETSOURCE.getValue().intValue() == objectType && status == 0) {//资产源同意接收

        }
        return map;
    }

    @Override
    public Map zcyDetail(Integer estatesId) {
        Map map = new HashMap<>();
        ZcyListQuery zcyListQuery = new ZcyListQuery();
        List<Integer> objectIds = new ArrayList<>();
        objectIds.add(estatesId);
        zcyListQuery.setObjectIdList(objectIds);//设置资产源待接收
        zcyListQuery.setStartPage(zcyListQuery.getPage() * zcyListQuery.getPageCount());
        List<ZcyPawnDTO> zcyPawnDTOs = coordinatorMapper.selectByZCYListPage(zcyListQuery);
        setZcyPawnDTOs(zcyPawnDTOs);
        if (zcyPawnDTOs.size() > 0) {
            map.put("zcyPawnDTO", zcyPawnDTOs.get(0));
        } else {
            map.put("zcyPawnDTO", null);
        }
        Map<String, Object> zcy = zcyEstatesMapper.selectDetailByZcy(estatesId);
        map.put("detail", zcy);
        map.put("result", "yes");
        return map;
    }
}
