package com.dqys.business.service.utils.user;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.service.dto.company.UserListDTO;

/**
 * Created by Yvan on 16/6/29.
 */
public class UserUtils {

    public static UserListDTO toUserListDTO(TUserInfo userInfo, TUserTag userTag, TCompanyInfo companyInfo){
        UserListDTO userListDTO = new UserListDTO();

        userListDTO.setId(userInfo.getId());
        userListDTO.setUserName(userInfo.getUserName());
        userListDTO.setRealName(userInfo.getRealName());
        userListDTO.setSex(userInfo.getSex()?1:0);
        userListDTO.setMobile(userInfo.getMobile());
        userListDTO.setEmail(userInfo.getEmail());
        userListDTO.setIdentity(userInfo.getIdentity());
        userListDTO.setId(userInfo.getId());


        return userListDTO;
    }

}
