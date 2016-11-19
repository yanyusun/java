package com.dqys.business.service.utils.operType;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.*;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.service.OperTypeService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化
 * Created by mkfeng on 2016/7/7.
 */
@Component
public class OperTypeUtile implements ApplicationContextAware {
    private static RedisTemplate<String, Object> redisTemplate;
    private static OperTypeService operTypeService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        operTypeService = applicationContext.getBean(OperTypeService.class);
        //// TODO: 16-9-18  上线要去掉下面的注释
        // opertype();
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
     * 请完善这个方法????
     *
     * @param userType 用户类型
     * @param userRole 用户角色
     * @param objType  对象类型
     * @return 该用户对这种操作对象所具有的操作列表
     */
    public static List<OperType> operTypes(int userType, int userRole, int objType) {
        String key = userType + "_" + userRole + "_" + objType;
        return null;
    }

    /**
     * 请完善这个方法????
     *
     * @param userType 用户类型
     * @param userRole 用户角色
     * @param objType  对象类型
     * @param Oper     操作类型
     * @return 该用户是否具有对应操作的权限
     */
    public static boolean hasAuth(int userType, int userRole, int objType, int Oper) {
        return true;
    }

    private static Integer[] nav0 = {102, 1110, 1615, 1310, 1213};//待接收
    private static Integer[] nav1 = {102, 1110, 1615, 1310, 11213};//待申请
    private static Integer[] nav2 = {102, 1110, 1615, 1310, 1213, 100, 103, 104, 105, 106, 107, 108, 109, 110,
            112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115,
            162, 163, 164, 165, 166, 167, 168, 169, 169, 1610, 1611, 1612, 1613, 1614,
            130, 131, 132, 133, 134, 135, 136, 137, 138, 1310, 1311,
            120, 121, 122, 123, 124, 125, 126, 127, 129, 1210, 1211, 1214, 1215, 1216, 1217, 1218,
            151, 152, 153, 154, 155, 156, 157, 158, 159, 1511, 1512, 1513, 1515, 1516};//正在处置
    private static Integer[] nav3 = {102, 1110, 1615, 1310, 1213};//聚焦
    private static Integer[] nav4 = {102, 1110, 1615, 1310, 1213};//当月
    private static Integer[] nav5 = {102, 1110, 1615, 1310, 1213};//存量
    private static Integer[] nav6 = {102, 1110, 1615, 1310, 1213};//完成
    private static Integer[] nav7 = {102, 1110, 1615, 1310, 1213};//超时
    private static Integer[] nav8 = {102, 1110, 1615, 1310, 1213, 108, 1118, 1613, 139};//无效
    private static Integer[] nav9 = {102, 1110, 1615, 1310, 1213};//待参与
    private static Integer[] nav10 = {102, 1110, 1615, 1310, 1213};//已参与
    private static Integer[] nav11 = {102, 103, 104, 1110, 1113, 1114, 1615, 1310, 1213, 169, 1610, 1312, 101, 161, 111, 1220};//待审核
    private static Integer[] nav12 = {102, 1110, 1112, 1615, 1310, 1213};//待处置
    private static Integer[] nav13 = {102, 1110, 1615, 1310, 1213};//待分配
    private static Integer[] nav14 = {102, 1110, 1615, 1310, 1213};//48H 新
    private static Integer[] nav15 = {102, 1110, 1615, 1310, 1213, 100, 101, 103, 104, 105, 106, 107, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115, 1116,
            161, 162, 163, 164, 165, 166, 167, 168, 169, 169, 1610, 1611, 1612, 1613, 1614,
            130, 131, 132, 133, 134, 135, 136, 137, 138, 1310, 1311, 1312,
            120, 121, 122, 123, 124, 125, 126, 127, 129, 1210, 1211, 1214, 1215, 1216, 1217, 1218, 1220};//我的任务
    private static Integer[] nav16 = {102, 1110, 1615, 1310, 1213, 100, 103, 104, 105, 106, 107, 110,
            112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115, 1116,
            162, 163, 164, 165, 166, 167, 168, 169, 169, 1610, 1611, 1612, 1613, 1614,
            130, 131, 132, 133, 134, 135, 136, 137, 138, 1310, 1311,
            120, 121, 122, 123, 124, 125, 126, 127, 129, 1210, 1211, 1214, 1215, 1216, 1217, 1218};//处置中
    private static Integer[] nav17 = {102, 1110, 1615, 1310, 1213, 105, 107, 101, 1115, 116, 111, 1611, 166, 161, 1312,
            1219, 106, 1116, 1612, 103, 169};//已驳回
    private static Integer[] nav18 = {102, 1110, 1615, 1310, 1213, 109, 1117, 1614};//暂停
    private static Integer[] nav19 = {102, 1110, 1615, 1310, 1213};//正在进行
    private static Integer[] nav20 = {102, 1110, 1615, 1310, 1213};//我的催收
    private static Integer[] nav99 = {102, 1110, 1615, 1310, 1213};//全部

    public static List<OperType> getOperType(Integer navId, Integer objectType) {
        Map<String, Object> map = new HashMap<>();
        List<OperType> operTypes = getOperTypeList(getNav(navId), objectType);
        map.put(navId + "_" + objectType, operTypes);
        return operTypes;
    }

    private static Integer[] getNav(Integer navId) {
        if (navId == ObjectTabEnum.accept.getValue()) {
            return nav0;
        }
        if (navId == ObjectTabEnum.apply.getValue()) {
            return nav1;
        }
        if (navId == ObjectTabEnum.handling_urge.getValue()) {
            return nav2;
        }
        if (navId == ObjectTabEnum.focus.getValue()) {
            return nav3;
        }
        if (navId == ObjectTabEnum.month.getValue()) {
            return nav4;
        }
        if (navId == ObjectTabEnum.stock.getValue()) {
            return nav5;
        }
        if (navId == ObjectTabEnum.over.getValue()) {
            return nav6;
        }
        if (navId == ObjectTabEnum.outTime.getValue()) {
            return nav7;
        }
        if (navId == ObjectTabEnum.invalid.getValue()) {
            return nav8;
        }
        if (navId == ObjectTabEnum.join.getValue()) {
            return nav9;
        }
        if (navId == ObjectTabEnum.joined.getValue()) {
            return nav10;
        }
        if (navId == ObjectTabEnum.check.getValue()) {
            return nav11;
        }
        if (navId == ObjectTabEnum.handle.getValue()) {
            return nav12;
        }
        if (navId == ObjectTabEnum.assign.getValue()) {
            return nav13;
        }
        if (navId == ObjectTabEnum.new48h.getValue()) {
            return nav14;
        }
        if (navId == ObjectTabEnum.task.getValue()) {
            return nav15;
        }
        if (navId == ObjectTabEnum.handling_entrust.getValue()) {
            return nav16;
        }
        if (navId == ObjectTabEnum.refuse.getValue()) {
            return nav17;
        }
        if (navId == ObjectTabEnum.stop.getValue()) {
            return nav18;
        }
        if (navId == ObjectTabEnum.gongingOn.getValue()) {
            return nav19;
        }
        if (navId == ObjectTabEnum.myUrge.getValue()) {
            return nav20;
        }
        if (navId == ObjectTabEnum.all.getValue()) {
            return nav99;
        }
        return null;
    }

    public static List<OperType> getOperTypeList(Object[] navs, Integer... objectTypes) {
        List<OperType> list = new ArrayList<>();
        for (Integer objectType : objectTypes) {
            if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
                for (AssetPackageEnum e : AssetPackageEnum.values()) {
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
            if (objectType == ObjectTypeEnum.LENDER.getValue()) {
                for (LenderEnum e : LenderEnum.values()) {
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
            if (objectType == ObjectTypeEnum.ASSETSOURCE.getValue()) {
                for (AssetSourceEnum e : AssetSourceEnum.values()) {
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
            if (objectType == ObjectTypeEnum.PAWN.getValue()) {
                for (PawnEnum e : PawnEnum.values()) {
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
            if (objectType == ObjectTypeEnum.IOU.getValue()) {
                for (IouEnum e : IouEnum.values()) {
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
            if (objectType == ObjectTypeEnum.CASE.getValue()) {
                for (CaseEnum e : CaseEnum.values()) {
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
        }
        return list;
    }

}
