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
    private static Integer[] rightButtonArray3 = {LenderEnum.VIEW_OPERATION_LOG.getValue(),LenderEnum.VIEW_REAPPLY.getValue()};

    // TODO: 16-9-26  后期改为从数据库查询完成并且加入缓存,实现可以调控
    static {
        //--------------------------------->待审核
        putCheck(map,ObjectTypeEnum.LENDER);
        putCheck(map,ObjectTypeEnum.ASSETPACKAGE);
        //---------------------------------------->驳回
        putRefuse(map,ObjectTypeEnum.LENDER);
        putRefuse(map,ObjectTypeEnum.ASSETPACKAGE);
        //------------------------------------------------->待处置
        putHandle(map,ObjectTypeEnum.LENDER);
        putHandle(map,ObjectTypeEnum.ASSETPACKAGE);
        //--------------------------------------------------->待参与
        //待参与 平台管理员 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台管理者 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台所属人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台参与人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 委托管理员 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托管理者 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托所属人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托参与人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 催收管理员 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收管理者 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收所属人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收参与人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 律所司法管理员 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法管理者 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法所属人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法参与人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 中介管理员 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介管理者 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介所属人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介参与人 借款人
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));



        //待参与 平台管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 委托管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 催收管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 律所司法管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 中介管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETPACKAGE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));


        //待参与 平台管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 平台参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 委托管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 委托参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 催收管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 催收参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 律所司法管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 律所司法参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));

        //待参与 中介管理员 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介管理者 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介所属人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));
        //待参与 中介参与人 资产包
        map.put(getKey(ObjectTabEnum.join, ObjectTypeEnum.ASSETSOURCE, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , getAllFalseBean(
                        getUserTeamApply(new ListButtonShowerBean())));





        //-------------------------------------------------------------------->已参与
        //已参与 平台管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 委托管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 催收管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 律所司法管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 中介管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));



        //已参与 平台管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 委托管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 催收管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 律所司法管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 中介管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));






        //------------------------------------>超时
        //已参与 平台管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 平台参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 委托管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 委托参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 催收管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 催收参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_COLLECTION, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 律所司法管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 律所司法参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_JUDICIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

        //已参与 中介管理员 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.ADMIN)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介管理者 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.REGULATOR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介所属人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));
        //已参与 中介参与人 借款人
        map.put(getKey(ObjectTabEnum.joined, ObjectTypeEnum.LENDER, UserInfoEnum.USER_TYPE_INTERMEDIARY, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, ObjectTypeEnum.LENDER)));

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
    public  static ListButtonShowerBean getAllFalseBean(ListButtonShowerBean bean){
        bean.setHasRightButton(false);
        return bean;
    }

    /**
     * 待审核
     * @param map
     * @param objectTypeEnum
     */
    public static void putCheck(Map<String,ListButtonShowerBean> map,ObjectTypeEnum objectTypeEnum){
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
     * @param map
     * @param objectTypeEnum
     */
    public static void putRefuse(Map<String,ListButtonShowerBean> map,ObjectTypeEnum objectTypeEnum){
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

    public static void putHandle(Map<String,ListButtonShowerBean> map,ObjectTypeEnum objectTypeEnum){
        //待处置 平台管理员
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.ADMIN)
                , getUserTeamBeanAdd(
                        getCompanyTeamBeanAdd(
                                new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)))
                ));
        //待处置 平台管理者
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                ,getUserTeamBeanAdd(
                        new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        //待处置 平台所属人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待处置 平台参与人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));

        //待处置 委托管理员
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ENTRUST, RoleTypeEnum.ADMIN)
                ,getUserTeamBeanAdd(
                        new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        //待处置 委托管理者
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.REGULATOR)
                ,getUserTeamBeanAdd(
                        new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum))));
        //待处置 委托所属人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.THEIR)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
        //待处置 委托参与人
        map.put(getKey(ObjectTabEnum.handle, objectTypeEnum, UserInfoEnum.USER_TYPE_ADMIN, RoleTypeEnum.GENERAL)
                , new ListButtonShowerBean(getrightButtonList(rightButtonArray2, objectTypeEnum)));
    }

}
