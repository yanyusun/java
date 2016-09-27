package com.dqys.business.service.utils.common.buttonUtil;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;
import com.dqys.business.service.constant.ObjectEnum.AssetSourceEnum;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.exception.bean.UndefinitionTypeException;
import com.dqys.core.constant.RoleTypeEnum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 列表界面按钮控制显示类
 * Created by yan on 16-9-24.
 */
public class ListButtonShowerUtil {

    public static Map<String, ListButtonShowerBean> map = new HashMap<>();
    //同意,拒绝,操作日志
    private static Integer[] rightButtonArray1 = {LenderEnum.VIEw_ACCPET.getValue(), LenderEnum.VIEW_REJECT.getValue(), LenderEnum.VIEW_OPERATION_LOG.getValue()};
    //操作日志
    private static Integer[] rightButtonArray2 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
    //操作日志,重新申请
    private static Integer[] rightButtonArray3 = {LenderEnum.VIEW_OPERATION_LOG.getValue(), LenderEnum.VIEW_REAPPLY.getValue()};
    //同意,拒绝
    private static Integer[] rightButtonArray4 = {LenderEnum.VIEw_ACCPET.getValue(), LenderEnum.VIEW_REJECT.getValue()};

    // TODO: 16-9-26  后期改为从数据库查询完成并且加入缓存,实现可以调控
    static {
        //--------------------------------->待审核
        putCheck(ObjectTypeEnum.LENDER);
        putCheck(ObjectTypeEnum.ASSETPACKAGE);
        //---------------------------------------->驳回
        putRefuse(ObjectTypeEnum.LENDER);
        putRefuse(ObjectTypeEnum.ASSETPACKAGE);
        //------------------------------------------------->待处置
        putHandle(ObjectTypeEnum.LENDER);
        putHandle(ObjectTypeEnum.ASSETPACKAGE);
        //--------------------------------------------------->待参与
        putJoin(ObjectTypeEnum.LENDER);
        putJoin(ObjectTypeEnum.ASSETPACKAGE);
        putJoin(ObjectTypeEnum.ASSETSOURCE);
        //-------------------------------------------------------------------->已参与
        putJoined(ObjectTypeEnum.LENDER);
        putJoined(ObjectTypeEnum.ASSETPACKAGE);
        putJoined(ObjectTypeEnum.ASSETSOURCE);
        //------------------------------------>超时
        putOutTime(ObjectTypeEnum.LENDER);
        putOutTime(ObjectTypeEnum.ASSETPACKAGE);
        putOutTime(ObjectTypeEnum.ASSETSOURCE);
        //---------------------------------------------->完成
        putOver(ObjectTypeEnum.LENDER);
        putOver(ObjectTypeEnum.ASSETPACKAGE);
        putOver(ObjectTypeEnum.ASSETSOURCE);
        //---------------------------------------------------->待分配
        putAssign(ObjectTypeEnum.LENDER);
        putAssign(ObjectTypeEnum.ASSETPACKAGE);
        putAssign(ObjectTypeEnum.ASSETSOURCE);
        //--------------------------------------------------------->待接受
        putAccept(ObjectTypeEnum.LENDER);
        putAccept(ObjectTypeEnum.ASSETPACKAGE);
        putAccept(ObjectTypeEnum.ASSETSOURCE);
        //--------------------------------------------->待申请
        putApply(ObjectTypeEnum.LENDER);
        putApply(ObjectTypeEnum.ASSETPACKAGE);
        putApply(ObjectTypeEnum.ASSETSOURCE);
        //------------------------------------------------->正在处置
        putHandling_urge(ObjectTypeEnum.LENDER);
        putHandling_urge(ObjectTypeEnum.ASSETPACKAGE);
        putHandling_urge(ObjectTypeEnum.ASSETSOURCE);
        //------------------------------------------------->处置中
        getHandling_entrust(ObjectTypeEnum.LENDER);
        getHandling_entrust(ObjectTypeEnum.ASSETPACKAGE);
        getHandling_entrust(ObjectTypeEnum.ASSETSOURCE);
        //------------------------------------------------->我的任务
        getTask(ObjectTypeEnum.LENDER);
        getTask(ObjectTypeEnum.ASSETPACKAGE);
        getTask(ObjectTypeEnum.ASSETSOURCE);

    }

    public static ListButtonShowerBean getListButtonShowerBean(Integer navId, Integer objectType, Integer userInfo, Integer roleType) throws UndefinitionTypeException {
        if (objectType == ObjectTypeEnum.LENDER.getValue() || objectType == ObjectTypeEnum.ASSETPACKAGE.getValue() || objectType == ObjectTypeEnum.ASSETSOURCE.getValue()) {
            ListButtonShowerBean listButtonShowerBean = map.get(navId + "_" + objectType + "_" + userInfo + "_" + roleType);
            if (listButtonShowerBean == null) {//找不到就返回默认
                listButtonShowerBean = new ListButtonShowerBean();
                listButtonShowerBean.setHasRightButton(false);
            }
            return listButtonShowerBean;
        } else {
            throw new UndefinitionTypeException("没有找到对应的对象类型", UndefinitionTypeException.TYPE_NOTFOUNFD_ERROR);
        }

    }

    /**
     * 生成键
     *
     * @param objectTabEnum  导航类型
     * @param objectTypeEnum 对象类型
     * @param userInfoEnum   用户类型
     * @param roleTypeEnum   角色类型
     * @return
     */
    private static String getKey(ObjectTabEnum objectTabEnum, ObjectTypeEnum objectTypeEnum, UserInfoEnum userInfoEnum, RoleTypeEnum roleTypeEnum) {
        return objectTabEnum.getValue() + "_" + objectTypeEnum.getValue() + "_" + userInfoEnum.getValue() + "_" + roleTypeEnum.getValue();
    }

    /**
     * 生成操作按钮列表
     *
     * @param operTypeValues 需要的操作列表按钮
     * @param ObjectTypeEnum 对象类型
     * @return
     * @throws UndefinitionTypeException
     */
    public static List<String[]> getrightButtonList(Integer[] operTypeValues, ObjectTypeEnum ObjectTypeEnum) {
        List<String[]> list = new LinkedList<>();
        switch (ObjectTypeEnum) {
            case LENDER:
                for (Integer operTypeValue : operTypeValues) {
                    for (LenderEnum e : LenderEnum.values()) {
                        if (e.getValue() == operTypeValue) {
                            String[] oprtType = new String[2];
                            oprtType[0] = operTypeValue.toString();
                            oprtType[1] = e.getName();
                            list.add(oprtType);
                        }
                    }
                }
            case ASSETPACKAGE:
                for (Integer operTypeValue : operTypeValues) {
                    for (AssetPackageEnum e : AssetPackageEnum.values()) {
                        if (e.getValue() == operTypeValue) {
                            String[] oprtType = new String[2];
                            oprtType[0] = operTypeValue.toString();
                            oprtType[1] = e.getName();
                            list.add(oprtType);
                        }
                    }
                    ;
                }
            case ASSETSOURCE:
                for (Integer operTypeValue : operTypeValues) {
                    for (AssetSourceEnum e : AssetSourceEnum.values()) {
                        if (e.getValue() == operTypeValue) {
                            String[] oprtType = new String[2];
                            oprtType[0] = operTypeValue.toString();
                            oprtType[1] = e.getName();
                            list.add(oprtType);
                        }
                    }
                    ;
                }
        }
        return list;
    }

    /**
     * @param bean
     * @return
     */
    private static ListButtonShowerBean getUserTeamBean(ListButtonShowerBean bean, boolean add, boolean apply) {
        bean.setHasUserTeamButton(true);
        bean.setHasUserTeamButtonAdd(add);
        bean.setHasUserTeamButtonApply(apply);
        return bean;
    }


    public static ListButtonShowerBean getUserTeamBeanAdd(ListButtonShowerBean bean) {
        return getUserTeamBean(bean, true, false);
    }

    public static ListButtonShowerBean getUserTeamApply(ListButtonShowerBean bean) {
        return getUserTeamBean(bean, false, true);
    }

    public static ListButtonShowerBean getUserTeamBeanAddApply(ListButtonShowerBean bean) {
        return getUserTeamBean(bean, true, true);
    }

    public static ListButtonShowerBean getUserTeamBeanView(ListButtonShowerBean bean) {
        return getUserTeamBean(bean, false, false);
    }

    /**
     * 设置分配器按钮显示状态
     *
     * @param bean
     * @return
     */
    public static ListButtonShowerBean getCompanyTeamBean(ListButtonShowerBean bean, boolean add, boolean apply) {
        bean.setHasCompanyTeamButton(true);
        bean.setHasCompanyTeamButtonAdd(add);
        bean.setHasUserTeamButton(apply);
        return bean;
    }

    public static ListButtonShowerBean getCompanyTeamBeanAdd(ListButtonShowerBean bean) {
        return getCompanyTeamBean(bean, true, false);
    }

    public static ListButtonShowerBean getCompanyTeamBeanApply(ListButtonShowerBean bean) {
        return getCompanyTeamBean(bean, false, true);
    }

    public static ListButtonShowerBean getCompanyTeamBeanAddAndApply(ListButtonShowerBean bean) {
        return getCompanyTeamBean(bean, true, true);
    }

    public static ListButtonShowerBean getCompanyTeamBeanView(ListButtonShowerBean bean) {
        return getCompanyTeamBean(bean, false, false);
    }

    public static ListButtonShowerBean getAllFalseBean(ListButtonShowerBean bean) {
        bean.setHasRightButton(false);
        return bean;
    }

    /**
     * 待审核
     *
     * @param objectTypeEnum
     */
    public static void putCheck(ObjectTypeEnum objectTypeEnum) {
        //待审核 平台管理员
        map.put(getKey(ObjectTabEnum.check, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray1, objectTypeEnum)));
        //待审核 委托管理员
        map.put(getKey(ObjectTabEnum.check, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待审核 委托所属人
        map.put(getKey(ObjectTabEnum.check, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待审核 委托管理者
        map.put(getKey(ObjectTabEnum.check, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待审核 委托管理者
        map.put(getKey(ObjectTabEnum.check, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
    }

    /**
     * 驳回
     *
     * @param objectTypeEnum
     */
    public static void putRefuse(ObjectTypeEnum objectTypeEnum) {
        //驳回 平台管理员
        map.put(getKey(ObjectTabEnum.refuse, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //驳回 委托管理员
        map.put(getKey(ObjectTabEnum.refuse, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray3, objectTypeEnum)));
        //驳回 委托所属人
        map.put(getKey(ObjectTabEnum.refuse, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray3, objectTypeEnum)));
        //驳回 委托管理者
        map.put(getKey(ObjectTabEnum.refuse, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray3, objectTypeEnum)));
        //驳回 委托管理者
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL),
                new ListButtonShowerBean(getrightButtonList(rightButtonArray3, objectTypeEnum)));

    }
    //待处置
    public static void putHandle(ObjectTypeEnum objectTypeEnum) {
         //平台管理员
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(
                        getCompanyTeamBeanAdd(
                                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))
                ));
        //待处置 平台管理者
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(
                        new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        //待处置 平台所属人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待处置 平台参与人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //待处置 委托管理员
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(
                        new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        //待处置 委托管理者
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(
                        new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        //待处置 委托所属人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待处置 委托参与人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
    }

    //待参与
    public static void putJoin(ObjectTypeEnum objectTypeEnum) {
        //待参与 平台管理员
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台管理者
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台所属人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台参与人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 委托管理员
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托管理者
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托所属人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托参与人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 催收管理员
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收管理者
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收所属人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收参与人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 律所司法管理员
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法管理者
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法所属人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法参与人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 中介管理员
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介管理者
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介所属人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介参与人
        map.put(getKey(ObjectTabEnum.join, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
    }

    //已参与
    public static void putJoined(ObjectTypeEnum objectTypeEnum) {
        //已参与 平台管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 委托管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 催收管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 律所司法管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 中介管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 平台管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 委托管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 催收管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 律所司法管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 中介管理员
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介管理者
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介所属人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介参与人
        map.put(getKey(ObjectTabEnum.joined, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

    }

    /**
     * 超时
     *
     * @param objectTypeEnum
     */
    public static void putOutTime(ObjectTypeEnum objectTypeEnum) {
        //已参与 平台管理员
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台管理者
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台所属人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台参与人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 委托管理员
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托管理者
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托所属人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托参与人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 催收管理员
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收管理者
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收所属人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收参与人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 律所司法管理员
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法管理者
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法所属人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法参与人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 中介管理员
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介管理者
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介所属人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介参与人
        map.put(getKey(ObjectTabEnum.outTime, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
    }

    /**
     * 已完成
     *
     * @param objectTypeEnum
     */
    public static void putOver(ObjectTypeEnum objectTypeEnum) {
        //已参与 平台管理员
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(
                        getUserTeamBeanView(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 平台管理者
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanView(
                        getUserTeamBeanAdd(
                                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 平台所属人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 平台参与人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 委托管理员
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(
                        getUserTeamBeanAdd(
                                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 委托管理者
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 委托所属人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 委托参与人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 催收管理员
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 催收管理者
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 催收所属人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 催收参与人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 律所司法管理员
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 律所司法管理者
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 律所司法所属人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 律所司法参与人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //已参与 中介管理员
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 中介管理者
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        //已参与 中介所属人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //已参与 中介参与人
        map.put(getKey(ObjectTabEnum.over, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
    }

    /**
     * 待分配
     *
     * @param objectTypeEnum
     */
    public static void putAssign(ObjectTypeEnum objectTypeEnum) {

        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));

        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;

        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;

        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;

        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                ,getCompanyTeamBeanView(getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))));;
    }

    /**
     * 待分配
     *
     * @param objectTypeEnum
     */
    public static void putAccept(ObjectTypeEnum objectTypeEnum) {

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));


        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.assign, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray4, objectTypeEnum)));

    }

    /**
     * 待申请
     *
     * @param objectTypeEnum
     */
    public static void putApply(ObjectTypeEnum objectTypeEnum) {
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));

        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
        map.put(getKey(ObjectTabEnum.accept, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , getCompanyTeamBeanApply(new ListButtonShowerBean()));
    }

    /**
     * 正在处置
     *
     * @param objectTypeEnum
     */
    public static void putHandling_urge(ObjectTypeEnum objectTypeEnum) {

        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));

        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));

        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_urge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
    }

    /**
     * 处置中
     *
     * @param objectTypeEnum
     */
    public static void getHandling_entrust(ObjectTypeEnum objectTypeEnum) {
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));

        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        map.put(getKey(ObjectTabEnum.handling_entrust, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , getUserTeamBeanAdd(new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));

    }

    /**
     * 我的任务
     *
     * @param objectTypeEnum
     */
    public static void getTask(ObjectTypeEnum objectTypeEnum) {
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.task, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

    }

    /**
     * 我的任务
     *
     * @param objectTypeEnum
     */
    public static void getMyUrge(ObjectTypeEnum objectTypeEnum) {
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        map.put(getKey(ObjectTabEnum.myUrge, objectTypeEnum, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
    }


}
