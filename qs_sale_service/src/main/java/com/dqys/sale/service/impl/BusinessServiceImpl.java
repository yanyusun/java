package com.dqys.sale.service.impl;

import com.dqys.core.model.UserSession;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetBusiness;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetDisposeBusiness;
import com.dqys.flowbusiness.service.util.BusinessResultEnum;
import com.dqys.flowbusiness.service.util.Result;
import com.dqys.sale.orm.mapper.UserBusTotalMapper;
import com.dqys.sale.orm.mapper.business.AssetUserReMapper;
import com.dqys.sale.orm.pojo.AssetUserRe;
import com.dqys.sale.orm.pojo.UserBusTotal;
import com.dqys.sale.orm.query.UserBusTotalQuery;
import com.dqys.sale.service.constant.AssetUserReEnum;
import com.dqys.sale.service.facade.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Override
    public Map collect(Integer status, Integer objectId, Integer objectType) {
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
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
            if (re.getIsCollection().intValue() == status && status == AssetUserReEnum.is_collection_no.getValue().intValue()) {
                map.put("msg", "已经取消过收藏");
                return map;
            }
            if (re.getIsCollection().intValue() == status && status == AssetUserReEnum.is_collection_yes.getValue().intValue()) {
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
            busTotal.setHasPublish(busTotal.getHasPublish() + hasPublish);
        }
        if (onCollection != null) {
            busTotal.setOnCollection(busTotal.getOnCollection() + onCollection);
        }
        if (onBusiness != null) {
            busTotal.setOnBusiness(busTotal.getOnBusiness() + onBusiness);
        }
        return userBusTotalMapper.updateByPrimaryKeySelective(busTotal);
    }

    @Override
    public Map applyDispose(Integer status, Integer objectId, Integer objectType) {
        Integer userId = UserSession.getCurrent().getUserId();
        Map map = new HashMap<>();
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
                map.put("result", "yes");
            } else {
                map.put("msg", "请稍后重试");
                return map;
            }
        }
        return map;
    }

    @Override
    public Map release(Integer businessId, Integer businessLevel, Integer operType) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Integer userId = UserSession.getCurrent().getUserId();
        Result result = businessService.flow(businessId, userId, AssetBusiness.type, businessLevel, operType);
        if (result.getCode() == BusinessResultEnum.sucesss.getValue().intValue()) {
            //还有后续的业务逻辑，如果是平台进行发布操作

            map.put("result", "yes");
            return map;
        } else {
            map.put("msg", result.getMsg());
        }
        return map;
    }

    @Override
    public Map dispose(Integer businessId, Integer businessLevel, Integer operType) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Integer userId = UserSession.getCurrent().getUserId();
        Result result = businessService.flow(businessId, userId, AssetDisposeBusiness.type, businessLevel, operType);
        if (result.getCode() == BusinessResultEnum.sucesss.getValue().intValue()) {
            //还有后续的业务逻辑，如果是平台进行处置操作

            map.put("result", "yes");
            return map;
        } else {
            map.put("msg", result.getMsg());
        }
        return map;
    }

}
