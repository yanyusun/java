package com.dqys.business.service.service.permission.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.UserInfoEnum;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.business.service.service.companyTeam.CompanyTeamService;
import com.dqys.business.service.service.permission.Permission;
import com.dqys.business.service.utils.permission.*;
import com.dqys.business.service.utils.user.UserServiceUtils;
import com.dqys.core.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dqys.business.orm.constant.company.ObjectTypeEnum.IOU;
import static com.dqys.business.orm.constant.company.ObjectTypeEnum.PAWN;

/**
 * Created by yan on 16-10-3.
 */
@Service
@Primary
public class PermissionImp implements Permission{
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

    public List<OperType> getOperTypes( Integer objectType, Integer objectId, Integer navId) {
        UserSession userSession = UserSession.getCurrent();
        //// TODO: 16-10-4  
        Integer userType = UserServiceUtils.headerStringToInt(userSession.getUserType());
        OriginOperTypeFiler originOperTypeFiler = new OriginOperTypeFiler();
        if (navId != null) {
            originOperTypeFiler.decorate(new NavIdOperTypeFilter(navId, objectType));
        }
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
                    originOperTypeFiler.decorate(new InitBusinessOperTypeFilter(operTypeService,objectType,objectId,PAWN.getValue()));
                    break;
                case IOU:
                    originOperTypeFiler.decorate(new OnHandleOperTypeFilter(
                            objectType, objectId, pawnInfoMapper, iouInfoMapper
                    ));
                    originOperTypeFiler.decorate(new InitBusinessOperTypeFilter(operTypeService,objectType,objectId, IOU.getValue()));
                    break;
            }
        }
        List<OperType> operTypes = operTypeService
                .getOperType(objectType, objectId);
        return originOperTypeFiler.getPermission(operTypes);
    }
}
