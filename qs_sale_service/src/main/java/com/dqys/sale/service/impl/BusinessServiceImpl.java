package com.dqys.sale.service.impl;

import com.dqys.core.model.UserSession;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetBusiness;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetDisposeBusiness;
import com.dqys.flowbusiness.service.dto.BusinessDto;
import com.dqys.flowbusiness.service.util.BusinessResultEnum;
import com.dqys.flowbusiness.service.util.Result;
import com.dqys.sale.orm.mapper.*;
import com.dqys.sale.orm.mapper.business.AssetUserReMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.pojo.*;
import com.dqys.sale.orm.query.AssetUserReQuery;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.constant.AssetUserReEnum;
import com.dqys.sale.service.constant.ObjectDisposeEnum;
import com.dqys.sale.service.constant.ObjectTypeEnum;
import com.dqys.sale.service.facade.BusinessService;
import com.dqys.sale.service.facade.MessageService;
import com.dqys.sale.service.facade.TSaleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/12/26.
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private AssetUserReMapper assetUserReMapper;
    @Autowired
    private UserBusTotalMapper userBusTotalMapper;
    @Autowired
    @Qualifier("saleBusinessService")
    private com.dqys.flowbusiness.service.service.BusinessService businessService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private TSaleUserService tSaleUserService;
    @Autowired
    private BusinessORelationMapper businessORelationMapper;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private UserBondMapper userBondMapper;
    @Autowired
    private FixedAssetMapper fixedAssetMapper;
    @Autowired
    private AssetPackageMapper assetPackageMapper;


    @Override
    public Map collect(Integer status, Integer objectId, Integer objectType) {
        Map map = new HashMap<>();
        if (UserSession.getCurrent() == null || UserSession.getCurrent().getUserId() == 0) {
            map.put("result", "no_login");
            map.put("msg", "需要登入后才可以");
            return map;
        }
        Integer userId = UserSession.getCurrent().getUserId();
        map.put("result", "no");
        AssetUserRe userRe = new AssetUserRe();
        userRe.setUserId(userId);
        userRe.setAssetId(objectId);
        userRe.setAssetType(objectType);
        List<AssetUserRe> list = assetUserReMapper.selectByUserRe(userRe);
        if (list == null || list.size() == 0) {
            //不存在关系的情况>>添加用户与对象关系
            userRe.setIsCollection(status);
            if (assetUserReMapper.insertSelective(userRe) > 0) {
                map.put("result", "yes");
            } else {
                map.put("msg", "请稍后重试");
                return map;
            }
        } else {
            AssetUserRe re = list.get(0);
            if (re.getIsCollection().intValue() == status.intValue() && status.intValue() == AssetUserReEnum.is_collection_no.getValue().intValue()) {
                map.put("msg", "已经取消过收藏");
                return map;
            }
            if (re.getIsCollection().intValue() == status.intValue() && status.intValue() == AssetUserReEnum.is_collection_yes.getValue().intValue()) {
                map.put("msg", "已经加入过收藏");
                return map;
            }
            re.setIsCollection(status);
            if (assetUserReMapper.updateByPrimaryKeySelective(re) > 0) {
                map.put("result", "yes");
            } else {
                map.put("msg", "请稍后重试");
                return map;
            }
        }
        //加入收藏+1，取消收藏-1
        if (status == AssetUserReEnum.is_collection_yes.getValue().intValue()) {
            setUserBus(null, userId, null, 1, null);
        } else {
            setUserBus(null, userId, null, -1, null);
        }
        return map;
    }

    /**
     * 统计数量
     *
     * @param id
     * @param userId
     * @param hasPublish   //已发布数量
     * @param onCollection //已收藏数量
     * @param onBusiness   //正在处置数量
     * @return
     */
    @Override
    public Integer setUserBus(Integer id, Integer userId, Integer hasPublish, Integer onCollection, Integer
            onBusiness) {
        if (id == null && userId == null) {
            return 0;
        }
        UserBusTotal busTotal = null;
//        获取用户所拥有的对象
        if (id != null) {
            busTotal = userBusTotalMapper.selectByPrimaryKey(id);
        } else {
            UserBusTotalQuery query = new UserBusTotalQuery();
            query.setUserId(userId);
            List<UserBusTotal> list = userBusTotalMapper.list(query);
            if (list != null && list.size() == 1) {
                busTotal = list.get(0);
            }
        }
        if (busTotal == null) {
            return 0;
        }
        if (hasPublish != null) {
            busTotal.setHasPublish(busTotal.getHasPublish() + hasPublish);//已发布数量
        }
        if (onCollection != null) {
            busTotal.setOnCollection(busTotal.getOnCollection() + onCollection);//已收藏数量
        }
        if (onBusiness != null) {
            busTotal.setOnBusiness(busTotal.getOnBusiness() + onBusiness);//正在处置数量
        }
        return userBusTotalMapper.updateByPrimaryKeySelective(busTotal);
    }

    @Override
    public Map applyDispose(Integer status, Integer objectId, Integer objectType) {
        Map map = new HashMap<>();
        if (UserSession.getCurrent() == null || UserSession.getCurrent().getUserId() == 0) {
            map.put("result", "no_login");
            map.put("msg", "需要登入后才可以");
            return map;
        }
        Integer userId = UserSession.getCurrent().getUserId();
        map.put("result", "no");
        AssetUserRe userRe = new AssetUserRe();
        userRe.setUserId(userId);
        userRe.setAssetId(objectId);
        userRe.setAssetType(objectType);
        List<AssetUserRe> list = assetUserReMapper.selectByUserRe(userRe);
        if (list == null || list.size() == 0) {
            //不存在关系的情况>>添加用户与对象关系
            userRe.setIsDispose(status);
            if (assetUserReMapper.insertSelective(userRe) > 0) {
                map.put("result", "yes");
            } else {
                map.put("msg", "请稍后重试");
                return map;
            }
        } else {
            AssetUserRe re = list.get(0);
            if (re.getIsDispose().intValue() == status && status == AssetUserReEnum.is_dispose_no.getValue().intValue()) {
                map.put("msg", "已经取消过置申请");
                return map;
            }
            if (re.getIsDispose().intValue() == status && status == AssetUserReEnum.is_dispose_yes.getValue().intValue()) {
                map.put("msg", "已经加入过处置申请");
                return map;
            }
            re.setIsDispose(status);
            if (assetUserReMapper.updateByPrimaryKeySelective(re) > 0) {
                BusinessDto dto = new BusinessDto();
                dto.setObjcetType(objectType);
                dto.setObjectId(objectId);
                if (status == AssetUserReEnum.is_dispose_yes.getValue().intValue()) {
                    businessService.createBusiness_tx(dto, userId, AssetDisposeBusiness.type, AssetDisposeBusiness.getCheckLevel().getLevel());
                } else if (status == AssetUserReEnum.is_dispose_no.getValue().intValue()) {
                    businessService.createBusiness_tx(dto, userId, AssetDisposeBusiness.type, AssetDisposeBusiness.getHasCancel().getLevel());
                }
                map.put("result", "yes");
            } else {
                map.put("msg", "请稍后重试");
                return map;
            }
        }
        return map;
    }

    @Override
    public Map release(Integer reqUserId, Integer businessId, Integer businessLevel, Integer operType) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Integer userId = UserSession.getCurrent().getUserId();
        Result result = businessService.flow_tx(businessId, userId, AssetBusiness.type, businessLevel, operType);
        if (result.getCode() == BusinessResultEnum.sucesss.getValue().intValue()) {
            //平台待发布的发布操作
            if (AssetBusiness.getBeAnnouncedAdmin().AnnounceOperType == operType) {
                BusinessORelation oRelation = businessORelationMapper.getORelationByBusinessId(businessId);
                publish(oRelation.getObjectId(), oRelation.getObjectType());//添加发布时间
                setUserBus(null, reqUserId, 1, null, null);
            }
            //已发布的下架操作
            if (AssetBusiness.getHasAnnouncedLevel().under_line == operType) {
                setUserBus(null, reqUserId, -1, null, null);
            }

            sendMessage(reqUserId, businessId, AssetBusiness.type, businessLevel, operType, userId);//发送短信和消息通知
            map.put("result", "yes");
            return map;
        } else {
            map.put("msg", result.getMsg());
        }
        return map;
    }

    @Override
    public Map dispose(Integer reqUserId, Integer businessId, Integer businessLevel, Integer operType) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Integer userId = UserSession.getCurrent().getUserId();
        Result result = businessService.flow_tx(businessId, userId, AssetDisposeBusiness.type, businessLevel, operType);
        if (result.getCode() == BusinessResultEnum.sucesss.getValue().intValue()) {
            //待审核的审核通过操作
            if (AssetDisposeBusiness.getCheckLevel().dispose_check_OK == operType) {
                setUserBus(null, reqUserId, null, null, 1); //统计数据的加减
                updateObjectDisposeStatus(businessId, null, null, ObjectDisposeEnum.dispose_status_yes.getValue());//改为正在处置
            }
            //待审核的驳回操作
            if (AssetDisposeBusiness.getCheckLevel().dispose_reject == operType || AssetDisposeBusiness.getCheckLevel().dispose_unable == operType) {
                setAssetUserReIsDispose(userId, businessId, AssetUserReEnum.is_dispose_no.getValue());//申请处置状态改变
            }
            //正在处置的取消处置操作
            if (AssetDisposeBusiness.getOnDisposeLevel().dispose_cancel == operType) {
                if (!tSaleUserService.isAdmin()) {//不是管理员，则是自己取消的，自己的处置数量减掉
                    setUserBus(null, userId, null, null, -1); //统计数据的加减
                    setAssetUserReIsDispose(userId, businessId, AssetUserReEnum.is_dispose_no.getValue());//申请处置状态改变
                } else {
                    setUserBus(null, reqUserId, null, null, -1);//统计数据的加减
                    setAssetUserReIsDispose(reqUserId, businessId, AssetUserReEnum.is_dispose_no.getValue());//申请处置状态改变
                }
                updateObjectDisposeStatus(businessId, null, null, ObjectDisposeEnum.dispose_status_wait.getValue());//改为正在处置
            }
            //
            sendMessage(reqUserId, businessId, AssetDisposeBusiness.type, businessLevel, operType, userId);//发送短信和消息通知
            map.put("result", "yes");
            return map;
        } else {
            map.put("msg", result.getMsg());
        }
        return map;
    }

    @Override
    public Map collectionList(AssetUserReQuery query) {
        Map map = new HashMap<>();
        map.put("result","yes");
        query.getUserRe().setUserId(UserSession.getCurrent().getUserId());
        query.setStartPage(query.getStartPage());
        List<AssetUserRe> list = assetUserReMapper.selectByUserReList(query);
        query.setIsPage(false);
        Integer count = assetUserReMapper.selectByUserReList(query).size();
        query.setTotalCount(count);
        map.put("collectionList", list);
        map.put("query", query);
        return map;
    }

    private void sendMessage(Integer reqUserId, Integer businessId, Integer businessType, Integer businessLevel, Integer operType, Integer userId) {
        //发送短信通知
        if (reqUserId == null) {
            reqUserId = tSaleUserService.getAdmin().getId();//获取管理员
        }
        messageService.addMessageAndSendSMS(userId, reqUserId, businessId, businessType, businessLevel, operType);//发送短信和消息通知
    }

    //设置t_asset_user_re处置状态
    private void setAssetUserReIsDispose(Integer reqUserId, Integer businessId, Integer status) {
        if (!tSaleUserService.isAdmin()) {//不是管理者，就是当前用户
            reqUserId = UserSession.getCurrent().getUserId();
        }
        BusinessORelation relation = businessORelationMapper.getORelationByBusinessId(businessId);
        Integer objectType = relation.getObjectType();
        Integer objectId = relation.getObjectId();
        updateIsDispose(reqUserId, status, objectType, objectId);
    }

    private void updateIsDispose(Integer reqUserId, Integer status, Integer objectType, Integer objectId) {
        AssetUserRe re = new AssetUserRe();
        re.setAssetType(objectType);
        re.setAssetId(objectId);
        re.setUserId(reqUserId);
        List<AssetUserRe> assetUserRes = assetUserReMapper.selectByUserRe(re);
        if (assetUserRes.size() > 0) {
            re = assetUserRes.get(0);
            re.setIsDispose(status);
            assetUserReMapper.updateByPrimaryKeySelective(re);
        }
    }

    /**
     * 修改发布时间
     *
     * @param objectId
     * @param objectType
     */
    private void publish(Integer objectId, Integer objectType) {
        if (objectType == ObjectTypeEnum.news.getValue().intValue()) {
            News news = newsMapper.selectByPrimaryKey(objectId);
            if (news != null) {
                news.setOpenTime(new Date());
                newsMapper.updateByPrimaryKeySelective(news);
            }
        }
    }

    private void updateObjectDisposeStatus(Integer businessId, Integer objectType, Integer objectId, Integer status) {
        if (businessId != null) {
            BusinessORelation relation = businessORelationMapper.getORelationByBusinessId(businessId);
            if (relation != null) {
                objectType = relation.getObjectType();
                objectId = relation.getObjectId();
            }
        }
        if (objectType == null || objectId == null || status == null) {
            return;
        }
        if (objectType == ObjectTypeEnum.asset_package.getValue()) {
            AssetPackage assetPackage = assetPackageMapper.selectByPrimaryKey(objectId);
            assetPackage.setDisposeStatus(status);
            assetPackageMapper.updateByPrimaryKeySelective(assetPackage);
        } else if (objectType == ObjectTypeEnum.fixed_asset.getValue()) {
            FixedAsset fixedAsset = fixedAssetMapper.selectByPrimaryKey(objectId);
            fixedAsset.setDisposeStatus(status);
            fixedAssetMapper.updateByPrimaryKeySelective(fixedAsset);
        } else if (objectType == ObjectTypeEnum.user_bond.getValue() || objectType == ObjectTypeEnum.overdue_asset.getValue() || objectType == ObjectTypeEnum.company_bond.getValue()) {
            UserBond userBond = userBondMapper.selectByPrimaryKey(objectId);
            userBond.setDisposeStatus(status);
            userBondMapper.updateByPrimaryKeySelective(userBond);
        }
    }

}
