package com.dqys.business.service.utils.user;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.dto.company.UserInsertDTO;
import com.dqys.business.service.dto.company.UserListDTO;
import com.dqys.core.utils.AreaTool;

/**
 * Created by Yvan on 16/6/29.
 */
public class UserUtils {

    public static UserListDTO toUserListDTO(TUserInfo userInfo, TUserTag userTag, TCompanyInfo companyInfo){
        UserListDTO userListDTO = new UserListDTO();

        userListDTO.setId(userInfo.getId());
        userListDTO.setUserName(userInfo.getUserName());
        userListDTO.setRealName(userInfo.getRealName());
        userListDTO.setSex(userInfo.getSex() ? 1 : 0);
        userListDTO.setMobile(userInfo.getMobile());
        userListDTO.setEmail(userInfo.getEmail());
        userListDTO.setIdentity(userInfo.getIdentity());
        userListDTO.setStatus(userInfo.getStatus());
        if(companyInfo != null){
            userListDTO.setCompany(companyInfo.getCompanyName());
            userListDTO.setArea(AreaTool.getAreaById(companyInfo.getProvince()).getName()
                            + AreaTool.getAreaById(companyInfo.getCity()).getName()
                            + AreaTool.getAreaById(companyInfo.getArea()).getName()
            );
        }
        // todo 这里需要重新设定一下
        userListDTO.setUserStatus(userInfo.getStatus());

        return userListDTO;
    }


    public static UserInsertDTO toUserInsertDTO(TUserInfo userInfo, TUserTag userTag, TCompanyInfo companyInfo,
                                                Organization organization){
        UserInsertDTO userInsertDTO = new UserInsertDTO();

        // todo 这里需要添加头像地址|微信
        userInsertDTO.setAvg(null);
        userInsertDTO.setWechat(null);
        userInsertDTO.setQq(null);
        userInsertDTO.setUserName(userInfo.getUserName());
        userInsertDTO.setRealName(userInfo.getRealName());
        userInsertDTO.setSex(userInfo.getSex() ? 1 : 0);
        userInsertDTO.setMobile(userInfo.getMobile());
        userInsertDTO.setEmail(userInfo.getEmail());
        userInsertDTO.setAccount(userInfo.getAccount());
        if(userTag != null){
            userInsertDTO.setRoleId(userTag.getRoleId().intValue());
        }
        if(organization != null){
            // todo 这里需要补足
            userInsertDTO.setOccupationId(null);
            userInsertDTO.setOccupationTel(null);
            userInsertDTO.setApartmentId(null);
            userInsertDTO.setTeamId(null);
        }

        // todo 这里需要重新设定一下
        userInsertDTO.setDuty(null); // 职责
        userInsertDTO.setDutyMark(null); // 职责介绍
        userInsertDTO.setAreaId(null); // 职责区域

        return userInsertDTO;
    }

    public static TUserInfo toTUserInfo(UserInsertDTO userInsertDTO){
        TUserInfo tUserInfo = new TUserInfo();

        return tUserInfo;
    }


}
