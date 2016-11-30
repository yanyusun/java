package com.dqys.business.service.service.permission.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.core.constant.UserInfoEnum;
import com.dqys.business.service.constant.asset.ObjectTabEnum;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.business.service.service.RepayService;
import com.dqys.business.service.service.companyTeam.CompanyTeamService;
import com.dqys.business.service.service.permission.Permission;
import com.dqys.business.service.utils.permission.*;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dqys.business.orm.constant.company.ObjectTypeEnum.IOU;
import static com.dqys.business.orm.constant.company.ObjectTypeEnum.PAWN;

/**
 * Created by yan on 16-10-3.
 */
@Service
@Primary
public class PermissionImp implements Permission {
    @Autowired
    private OperTypeService operTypeService;
    @Autowired
    private CompanyTeamService companyTeamService;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;
    @Autowired
    private IOUInfoMapper iouInfoMapper;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private ZcyEstatesMapper zcyEstatesMapper;
    @Autowired
    private RepayService repayService;

    public List<OperType> getOperTypes(Integer objectType, Integer objectId, Integer navId,int position) {
        UserSession userSession = UserSession.getCurrent();
        //// TODO: 16-10-4  
        Integer userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        Integer userRole = UserServiceUtils.headerStringToInt(userSession.getRoleId());
        OriginOperTypeFiler originOperTypeFiler = new OriginOperTypeFiler();
        if (navId != null) {
            originOperTypeFiler.decorate(new NavIdOperTypeFilter(navId, objectType,position));
        }
        if (ObjectTabEnum.handling_urge.getValue().intValue() == navId) {//正在处置
            handingEntrustFilter(objectType, objectId, userType, originOperTypeFiler);
        } else if (isOtherNavId(navId) && objectType != ObjectTypeEnum.ASSETSOURCE.getValue().intValue()) {//除了处置中,带审核,已驳回,待处置,延期其他流程不明确的搜所导航
            OtherTabAddOperTypeFilter otherTabAddOperTypeFilter = new OtherTabAddOperTypeFilter(businessService, objectId, objectType, userType, operTypeService);
            originOperTypeFiler.decorate(otherTabAddOperTypeFilter);
            handingEntrustFilter(objectType, objectId, userType, originOperTypeFiler);
        } else if (ObjectTabEnum.apply.getValue().intValue() == navId) {//如果为带申请为操作列表增加加入案组按钮
            AddApplyCompanyTeamOperTypeFilter addApplyCompanyTeamOperTypeFilter = new AddApplyCompanyTeamOperTypeFilter(navId, objectType, userType.toString(), userRole.toString());
            originOperTypeFiler.decorate(addApplyCompanyTeamOperTypeFilter);
        }
        if (userType == UserInfoEnum.USER_TYPE_COLLECTION.getValue()
                || userType == UserInfoEnum.USER_TYPE_JUDICIARY.getValue()) {//当为催收或者司法录入的资产包和借款人时
            AddEditOperTypeFilter addEditOperTypeFilter = new AddEditOperTypeFilter(lenderInfoMapper, assetInfoMapper, objectType, objectId, userType, userSession.getUserId());
            originOperTypeFiler.decorate(addEditOperTypeFilter);
        }
        if (objectType == ObjectTypeEnum.ASSETSOURCE.getValue().intValue() && isAssetSourceEdit(navId)) {//当为资产源时为除了带接收和带申请外的tab添加编辑功能
            AddAssetSourceEditOperTypeFilter addAssetSourceEditOperTypeFilter = new AddAssetSourceEditOperTypeFilter(objectId, userType, userSession.getUserId(), zcyEstatesMapper);
            originOperTypeFiler.decorate(addAssetSourceEditOperTypeFilter);
        }
        List<OperType> operTypes = operTypeService
                .getOperType(objectType, objectId);
        return originOperTypeFiler.getPermission(operTypes);
    }



    /**
     * 借款人详情是否具有添加还款的权限按钮
     *
     * @param lenderId
     * @return
     */
    public boolean hasRepayButton(Integer lenderId) {
        UserSession userSession = UserSession.getCurrent();
        int userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        int userRole = UserServiceUtils.headerStringToInt(userSession.getRoleId());

        if (UserServiceUtils.isPlatBoolean(userType, userRole)) {//为平台管理源就
            return true;
        } else {//当该用户有可还的抵押物或者借据时
            Map<String, List<Map>> map = new HashMap();
            repayService.getIouAndPawnByLender(lenderId, map);
            List<Map> ious = map.get("ious");
            List<Map> pawns = map.get("pawns");
            if (ious != null && ious.size() > 0) {
                return true;
            }
            if (pawns != null && pawns.size() > 0) {
                return true;
            }
            return false;
        }

    }


    /**
     * 正在处置中的对象方法过滤
     *
     * @param objectType
     * @param objectId
     * @param userType
     * @param originOperTypeFiler
     */
    private void handingEntrustFilter(Integer objectType, Integer objectId, Integer userType, OriginOperTypeFiler originOperTypeFiler) {
        if (userType == UserInfoEnum.USER_TYPE_COLLECTION.getValue()
                || userType == UserInfoEnum.USER_TYPE_JUDICIARY.getValue()
                || userType == UserInfoEnum.USER_TYPE_INTERMEDIARY.getValue()) {
            ObjectTypeEnum objectTypeEnum = ObjectTypeEnum.getObjectTypeEnum(objectType);
            switch (objectTypeEnum) {
                case LENDER:
                    originOperTypeFiler.decorate(new NotTheirOperTypeFilter(
                            companyTeamService, companyTeamReMapper, tUserInfoMapper, objectType, objectId));
                    break;
                case PAWN:
                    originOperTypeFiler.decorate(new OnHandleOperTypeFilter(
                            objectType, objectId, pawnInfoMapper, iouInfoMapper
                    ));
                    originOperTypeFiler.decorate(new InitBusinessOperTypeFilter(operTypeService, objectType, objectId, PAWN.getValue()));
                    break;
                case IOU:
                    originOperTypeFiler.decorate(new OnHandleOperTypeFilter(
                            objectType, objectId, pawnInfoMapper, iouInfoMapper
                    ));
                    originOperTypeFiler.decorate(new InitBusinessOperTypeFilter(operTypeService, objectType, objectId, IOU.getValue()));
                    break;
            }
        }
    }

    /**
     * 是否为非业务划分的标签
     *
     * @param navId
     * @return
     */
    private boolean isOtherNavId(int navId) {
        if (navId == ObjectTabEnum.focus.getValue() || navId == ObjectTabEnum.month.getValue()
                || navId == ObjectTabEnum.stock.getValue() || navId == ObjectTabEnum.joined.getValue()
                || navId == ObjectTabEnum.task.getValue() || navId == ObjectTabEnum.gongingOn.getValue()
                || navId == ObjectTabEnum.myUrge.getValue() || navId == ObjectTabEnum.all.getValue()) {
            return true;
        }
        return false;
    }

    /**
     * 是否为资产源可以编辑的tab
     *
     * @param navId
     * @return
     */
    private boolean isAssetSourceEdit(int navId) {
        if (navId == ObjectTabEnum.handling_urge.getValue() || navId == ObjectTabEnum.joined.getValue()
                || navId == ObjectTabEnum.handle.getValue() || navId == ObjectTabEnum.assign.getValue()
                || navId == ObjectTabEnum.handling_entrust.getValue() || navId == ObjectTabEnum.task.getValue() || navId == ObjectTabEnum.myUrge.getValue()) {
            return true;
        }
        return false;
    }

}
