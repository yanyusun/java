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

    // TODO: 16-9-26  后期改为从数据库查询完成并且加入缓存,实现可以调控
    static {
        //--------------------------------->待审核
        //待审核 平台管理员 借款人
        Integer[] rightButtonArray1 = {LenderEnum.VIEw_ACCPET.getValue(), LenderEnum.VIEW_REJECT.getValue(), LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray1, ObjectTypeEnum.LENDER)));
        //待审核 委托管理员 借款人
        Integer[] rightButtonArray2 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //待审核 委托所属人 借款人
        Integer[] rightButtonArray3 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR), new ListButtonShowerBean(getrightButtonList(rightButtonArray3, ObjectTypeEnum.LENDER)));
        //待审核 委托管理者 借款人
        Integer[] rightButtonArray4 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR), new ListButtonShowerBean(getrightButtonList(rightButtonArray4, ObjectTypeEnum.LENDER)));
        //待审核 委托管理者 借款人
        Integer[] rightButtonArray5 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL), new ListButtonShowerBean(getrightButtonList(rightButtonArray5, ObjectTypeEnum.LENDER)));

        //待审核 平台管理员 资产包
        Integer[] rightButtonArray6 = {AssetPackageEnum.VIEw_ACCPET.getValue(), AssetPackageEnum.VIEW_REJECT.getValue(), AssetPackageEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray6, ObjectTypeEnum.ASSETPACKAGE)));
        //待审核 委托管理员 资产包
        Integer[] rightButtonArray7 = {AssetPackageEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray7, ObjectTypeEnum.ASSETPACKAGE)));
        //待审核 委托所属人 资产包
        Integer[] rightButtonArray8 = {AssetPackageEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR), new ListButtonShowerBean(getrightButtonList(rightButtonArray8, ObjectTypeEnum.ASSETPACKAGE)));
        //待审核 委托管理者 资产包
        Integer[] rightButtonArray9 = {AssetPackageEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR), new ListButtonShowerBean(getrightButtonList(rightButtonArray9, ObjectTypeEnum.ASSETPACKAGE)));
        //待审核 委托管理者 资产包
        Integer[] rightButtonArray10 = {AssetPackageEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.check, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL), new ListButtonShowerBean(getrightButtonList(rightButtonArray10, ObjectTypeEnum.ASSETPACKAGE)));

        //---------------------------------------->驳回
        //驳回 平台管理员 借款人
        Integer[] rightButtonArray11 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray11, ObjectTypeEnum.LENDER)));
        //驳回 委托管理员 借款人
        Integer[] rightButtonArray12 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray12, ObjectTypeEnum.LENDER)));
        //驳回 委托所属人 借款人
        Integer[] rightButtonArray13 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR), new ListButtonShowerBean(getrightButtonList(rightButtonArray13, ObjectTypeEnum.LENDER)));
        //驳回 委托管理者 借款人
        Integer[] rightButtonArray14 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR), new ListButtonShowerBean(getrightButtonList(rightButtonArray14, ObjectTypeEnum.LENDER)));
        //驳回 委托管理者 借款人
        Integer[] rightButtonArray15 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL), new ListButtonShowerBean(getrightButtonList(rightButtonArray15, ObjectTypeEnum.LENDER)));

        //驳回 平台管理员 资产包
        Integer[] rightButtonArray16 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray16, ObjectTypeEnum.ASSETPACKAGE)));
        //驳回 委托管理员 资产包
        Integer[] rightButtonArray17 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray17, ObjectTypeEnum.ASSETPACKAGE)));
        //驳回 委托所属人 资产包
        Integer[] rightButtonArray18 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR), new ListButtonShowerBean(getrightButtonList(rightButtonArray18, ObjectTypeEnum.ASSETPACKAGE)));
        //驳回 委托管理者 资产包
        Integer[] rightButtonArray19 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR), new ListButtonShowerBean(getrightButtonList(rightButtonArray19, ObjectTypeEnum.ASSETPACKAGE)));
        //驳回 委托管理者 资产包
        Integer[] rightButtonArray20 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL), new ListButtonShowerBean(getrightButtonList(rightButtonArray20, ObjectTypeEnum.ASSETPACKAGE)));

        //------------------------------------------------->待处置
        //驳回 平台管理员 借款人
        Integer[] rightButtonArray21 = {LenderEnum.VIEW_OPERATION_LOG.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray11, ObjectTypeEnum.LENDER)));



        //驳回 委托管理员 借款人
        Integer[] rightButtonArray12 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN), new ListButtonShowerBean(getrightButtonList(rightButtonArray12, ObjectTypeEnum.LENDER)));
        //驳回 委托所属人 借款人
        Integer[] rightButtonArray13 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR), new ListButtonShowerBean(getrightButtonList(rightButtonArray13, ObjectTypeEnum.LENDER)));
        //驳回 委托管理者 借款人
        Integer[] rightButtonArray14 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR), new ListButtonShowerBean(getrightButtonList(rightButtonArray14, ObjectTypeEnum.LENDER)));
        //驳回 委托管理者 借款人
        Integer[] rightButtonArray15 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};
        map.put(getKey(ObjectTabEnum.refuse, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL), new ListButtonShowerBean(getrightButtonList(rightButtonArray15, ObjectTypeEnum.LENDER)));










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
     *
     * @param bean
     * @return
     */
    private static ListButtonShowerBean getUserTeamBean(ListButtonShowerBean bean,boolean add,boolean apply){
        bean.setHasUserTeamButton(true);
        bean.setHasUserTeamButtonAdd(add);
        bean.setHasUserTeamButtonApply(apply);
        return bean;
    }


    public static ListButtonShowerBean getUserTeamBeanAdd(ListButtonShowerBean bean){
        return getUserTeamBean(bean,true,false);
    }

    public static ListButtonShowerBean getUserTeamApply(ListButtonShowerBean bean){
        return getUserTeamBean(bean,false,true);
    }

    public static ListButtonShowerBean getUserTeamBeanAddApply(ListButtonShowerBean bean){
        return getUserTeamBean(bean,true,true);
    }
    /**
     * 设置分配器按钮显示状态
     * @param bean
     * @return
     */
    public static ListButtonShowerBean getCompanyTeamBean(ListButtonShowerBean bean,boolean add,boolean apply){
        bean.setHasCompanyTeamButton(true);
        bean.setHasCompanyTeamButtonAdd(add);
        bean.setHasUserTeamButton(apply);
        return bean;
    }

    public static ListButtonShowerBean getCompanyTeamBeanAdd(ListButtonShowerBean bean){
        return getCompanyTeamBean(bean,true,false);
    }

    public static ListButtonShowerBean getCompanyTeamBeanApply(ListButtonShowerBean bean){
        return getCompanyTeamBean(bean,false,true);
    }

    public static ListButtonShowerBean getCompanyTeamBeanAddAndApply(ListButtonShowerBean bean){
        return getCompanyTeamBean(bean,true,true);
    }
}
