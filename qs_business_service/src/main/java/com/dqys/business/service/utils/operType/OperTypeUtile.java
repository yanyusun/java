package com.dqys.business.service.utils.operType;

import com.dqys.business.orm.constant.business.ObjectUserStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.pojo.business.ObjectUserRelation;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.orm.query.business.ObjectUserRelationQuery;
import com.dqys.business.service.constant.ObjectEnum.*;
import com.dqys.business.service.exception.bean.BusinessDataException;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化
 * Created by mkfeng on 2016/7/7.
 */
@Component
public class OperTypeUtile implements ApplicationContextAware {
    private static RedisTemplate<String, Object> redisTemplate;
    private static OperTypeService operTypeService;
    private static ObjectUserRelationMapper objectUserRelationMapper;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        operTypeService = applicationContext.getBean(OperTypeService.class);
        objectUserRelationMapper = applicationContext.getBean(ObjectUserRelationMapper.class);
        //// TODO: 16-9-18  上线要去掉下面的注释
         //opertype();
    }

    public static List<OperType> getInitBuisnesOperTypeList(Integer objectType, Integer objectId, Integer flowType) {
        List<OperType> list = operTypeService.getInitBuisnesOperTypeList(objectType, objectId, flowType);
        return list;
    }

    public void opertype() {
        List<OperType> list = operTypeService.getAll(null);
        redisTemplate.opsForValue().set("operTypeList", list);//获取全部的operType
        List<Integer> userIds = operTypeService.selectByUserIds();//?????
        List<Integer> roleIds = operTypeService.selectByRoleIds();
        List<Integer> objectIds = operTypeService.selectByObjectIds();
        for (Integer use : userIds) {
            for (Integer rol : roleIds) {
                for (Integer obj : objectIds) {
                    List<OperType> operTypes = operTypeService.selectByRoleToOperType(rol, use, obj);
                    redisTemplate.opsForValue().set(use + "_" + rol + "_" + obj, operTypes);
                }
            }
        }
    }

    /**
     *
     * @param userType 用户类型
     * @param userRole 用户角色
     * @param objType  对象类型
     * @return 该用户对这种操作对象所具有的操作列表
     */
    public static List<OperType> operTypes(int userType, int userRole, int objType) {
        String userId_roleId_objectId = userType + "_" + userRole + "_" + objType;
        List<OperType> list = NoSQLWithRedisTool.getValueObject(userId_roleId_objectId) == null ? new ArrayList<>() : NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
        if (list != null && list.size() != 0) {
            return list;
        } else {
            NoSQLWithRedisTool.getRedisTemplate().opsForValue().set(userId_roleId_objectId, operTypeService.selectByRoleToOperType(userRole, userType, objType));
        }
        return (List<OperType>) NoSQLWithRedisTool.getValueObject(userId_roleId_objectId);
    }

    /**
     * 请完善这个方法????
     *
     * @param userType 用户类型
     * @param userRole 用户角色
     * @param objType  对象类型
     * @param oper     操作类型
     * @return 该用户是否具有对应操作的权限
     */
    public static boolean hasAuth(int userType, int userRole, int objType, int oper) {
        List<OperType> operTypes = operTypes(userType,userRole,objType);
        for(OperType operType:operTypes){
            if(operType.getOperType()==oper){
                return true;
            }
        }
        return false;
    }

    /**
     * 是处在待接收的状态
     * @return
     */
    public static boolean isOnAccept(int objectType,int objectId,int userId) throws BusinessDataException{
        ObjectUserRelationQuery query = new ObjectUserRelationQuery();
        query.setUserId(userId);
        query.setObjectType(objectType);
        query.setObjectId(objectId);
        List<ObjectUserRelation> objectUserRelationList=objectUserRelationMapper.list(query);
        if(objectUserRelationList==null||objectUserRelationList.size()==0){//没有加入协作器
            return false;
        }else if(objectUserRelationList.size()>1){//数据异常
            //// TODO: 16-11-24 返回找到不止一个的错误信息
            throw new BusinessDataException("后台数据异常信息异常",BusinessDataException.OBJECT_USER_RELATION＿NOTONE);
        }else{
            ObjectUserRelation objectUserRelation=objectUserRelationList.get(0);
            if(objectUserRelation.getStatus().intValue()== ObjectUserStatusEnum.accept.getValue()){
                return true;
            }else{//不在带接收阶段
                return false;
            }

        }
    }

    public static List<OperType> getOperType(Integer navId, Integer objectType) {
        return  getOperTypeList(AllOperTypeNavBean.allOperTypeNavBean.getNav(navId), objectType);
    }
    public static List<OperType> getLenderRowOperType(Integer navId) {
        return  getOperTypeList(LenderRowOperTypeNavBean.lenderRowOperTypeNavBean.getNav(navId),ObjectTypeEnum.LENDER.getValue());
    }





    public static List<OperType> getOperTypeList(Object[] navs, int... objectTypes) {
        List<OperType> list = new ArrayList<>();
        for (Integer objectType : objectTypes) {
            if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
                for (AssetPackageEnum e : AssetPackageEnum.values()) {
                    addOperType(navs, list, e);
                }
            }
            if (objectType == ObjectTypeEnum.LENDER.getValue()) {
                for (LenderEnum e : LenderEnum.values()) {
                    addOperType(navs, list, e);
                }
            }
            if (objectType == ObjectTypeEnum.ASSETSOURCE.getValue()) {
                for (AssetSourceEnum e : AssetSourceEnum.values()) {
                    addOperType(navs, list, e);
                }
            }
            if (objectType == ObjectTypeEnum.PAWN.getValue()) {
                for (PawnEnum e : PawnEnum.values()) {
                    addOperType(navs, list, e);
                }
            }
            if (objectType == ObjectTypeEnum.IOU.getValue()) {
                for (IouEnum e : IouEnum.values()) {
                    addOperType(navs, list, e);
                }
            }
            if (objectType == ObjectTypeEnum.CASE.getValue()) {
                for (CaseEnum e : CaseEnum.values()) {
                    addOperType(navs, list, e);
                }
            }
        }
        return list;
    }

    /**
     * 根据navs为list增加ｅ的对应operType
     * @param navs
     * @param list
     * @param e
     */
    private static void addOperType(Object[] navs, List<OperType> list, ObjectEnumBase e) {
        OperType operType = new OperType();
        for (Object n : navs) {
            //遍历是否拥有权限，有权限的就加入
            if (e.getValue().equals(n)) {
                operType.setOperType(e.getValue());
                operType.setOperName(e.getName());
                list.add(operType);
                break;
            }
        }
    }

}
