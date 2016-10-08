package com.dqys.business.service.service.companyTeam;

import com.dqys.business.orm.pojo.coordinator.CompanyTeam;

/**
 * Created by yan on 16-10-8.
 */

public interface CompanyTeamService {
   CompanyTeam getCompanyTeam(Integer objectType, Integer objectId);
}

