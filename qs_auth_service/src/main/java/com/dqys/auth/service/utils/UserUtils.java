package com.dqys.auth.service.utils;

import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;

/**
 * @author by pan on 16-4-12.
 */
public class UserUtils {


    public static UserDTO toUserDTO(TUserInfo tUserInfo, TUserTag tUserTag) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(tUserInfo.getId());
        userDTO.setUserName(tUserInfo.getUserName());
        userDTO.setRealName(tUserInfo.getRealName());
        userDTO.setMobile(tUserInfo.getMobile());
        userDTO.setEmail(tUserInfo.getEmail());
        userDTO.setCompanyId(tUserInfo.getCompanyId());
        userDTO.setIdentity(tUserInfo.getIdentity());
        userDTO.setSex(tUserInfo.getSex());
        userDTO.setStatus(tUserInfo.getStatus());

        userDTO.setUserType(tUserTag.getUserType());
        userDTO.setRoleId(tUserTag.getRoleId());
        userDTO.setCertified(tUserTag.getIsCertified());

        return userDTO;
    }
}
