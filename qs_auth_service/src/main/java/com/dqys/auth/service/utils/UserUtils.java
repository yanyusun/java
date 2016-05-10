package com.dqys.auth.service.utils;

import com.dqys.auth.service.dto.UserDTO;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.auth.service.dto.UserTagDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by pan on 16-4-12.
 */
public class UserUtils {


    public static UserDTO toUserDTO(TUserInfo tUserInfo, TUserTag tUserTag) {
        List<TUserTag> tUserTags = new ArrayList<>();
        tUserTags.add(tUserTag);

        return toUserDTO(tUserInfo, tUserTags);
    }
    public static UserDTO toUserDTO(TUserInfo tUserInfo, List<TUserTag> tUserTags) {
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

        userDTO.setUserTagDTOList(toUserTagDTO(tUserTags));
        return userDTO;
    }

    public static UserTagDTO toUserTagDTO(TUserTag tUserTag) {
        UserTagDTO userTagDTO = new UserTagDTO();
        userTagDTO.setUserType(tUserTag.getUserType());
        userTagDTO.setRoleId(tUserTag.getRoleId());
        userTagDTO.setCertified(tUserTag.getIsCertified());
        return userTagDTO;
    }

    public static List<UserTagDTO> toUserTagDTO(List<TUserTag> tUserTags) {
        List<UserTagDTO> userTagDTOs = new ArrayList<>();
        for(TUserTag tUserTag : tUserTags) {
            userTagDTOs.add(toUserTagDTO(tUserTag));
        }
        return userTagDTOs;
    }
}
