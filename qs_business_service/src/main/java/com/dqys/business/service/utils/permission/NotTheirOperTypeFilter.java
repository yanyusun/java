package com.dqys.business.service.utils.permission;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.mapper.company.CompanyTeamMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.pojo.coordinator.CompanyTeam;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.orm.query.company.CompanyTeamReQuery;
import com.dqys.business.service.service.companyTeam.CompanyTeamService;
import com.dqys.core.model.UserSession;

import java.util.List;

/**
 * 如果为处置机构，只有所属机构有操作权限
 * Created by yan on 16-10-4.
 */
public class NotTheirOperTypeFilter extends OperTypeFilter {
    private CompanyTeamService companyTeamService;
    private CompanyTeamReMapper companyTeamReMapper;
    private TUserInfoMapper tUserInfoMapper;
    private Integer objectType;
    private Integer objecId;

    public NotTheirOperTypeFilter(CompanyTeamService companyTeamService, CompanyTeamReMapper companyTeamReMapper, TUserInfoMapper tUserInfoMapper, Integer objectType, Integer objecId) {
        this.companyTeamService = companyTeamService;
        this.companyTeamReMapper = companyTeamReMapper;
        this.tUserInfoMapper = tUserInfoMapper;
        this.objectType = objectType;
        this.objecId = objecId;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        boolean isTheir = false;
        CompanyTeam companyTeam =companyTeamService.getCompanyTeam(objectType,objecId);
        if(companyTeam!=null) {
            CompanyTeamReQuery companyTeamReQuery = new CompanyTeamReQuery();
            companyTeamReQuery.setTeamId(companyTeam.getId());
            List<CompanyTeamRe> CompanyTeamReList = companyTeamReMapper.queryList(companyTeamReQuery);
            UserSession userSession = UserSession.getCurrent();
            TUserInfo userInfo = tUserInfoMapper.selectByPrimaryKey(userSession.getUserId());
            for (CompanyTeamRe companyTeamRe : CompanyTeamReList) {
                if (companyTeamRe.getAcceptCompanyId().intValue() == userInfo.getCompanyId().intValue() && companyTeamRe.getRoleType() == 0) {
                    isTheir = true;
                }
            }
        }else{//自己录入还未分配的情况
            isTheir=true;
        }
        if(isTheir){
            operTypes=list;
        }else{
            operTypes= PermissionUtil.getDefaultOperTypeList(list);
        }
       return getNextPermission();
    }
}
