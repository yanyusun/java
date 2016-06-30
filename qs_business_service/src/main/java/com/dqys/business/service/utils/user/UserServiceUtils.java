package com.dqys.business.service.utils.user;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.pojo.company.Organization;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.core.utils.AreaTool;

/**
 * Created by Yvan on 16/6/29.
 */
public class UserServiceUtils {

    public static UserListDTO toUserListDTO(TUserInfo userInfo, TCompanyInfo companyInfo) {
        UserListDTO userListDTO = new UserListDTO();

        userListDTO.setId(userInfo.getId());
        userListDTO.setStatus(userInfo.getStatus());
        userListDTO.setAvg(userInfo.getAvg());
        userListDTO.setUserName(userInfo.getUserName());
        userListDTO.setRealName(userInfo.getRealName());
        userListDTO.setSex(userInfo.getSex() ? 1 : 0);
        userListDTO.setAccount(userInfo.getAccount());
        userListDTO.setMobile(userInfo.getMobile());
        userListDTO.setEmail(userInfo.getEmail());
        if (companyInfo != null) {
            userListDTO.setCompany(companyInfo.getCompanyName());
            userListDTO.setArea(AreaTool.getAreaById(companyInfo.getProvince()).getName()
                            + AreaTool.getAreaById(companyInfo.getCity()).getName()
                            + AreaTool.getAreaById(companyInfo.getArea()).getName()
            );
        }

        // todo 暂时没有添加数据
        userListDTO.setTaskNum(null);

        return userListDTO;
    }


    public static UserInsertDTO toUserInsertDTO(TUserInfo userInfo, TUserTag userTag) {
        UserInsertDTO userInsertDTO = new UserInsertDTO();

        userInsertDTO.setId(userInfo.getId());
        userInsertDTO.setAvg(userInfo.getAvg());
        userInsertDTO.setWechat(userInfo.getWechat());
        userInsertDTO.setQq(userInfo.getQQ());
        userInsertDTO.setUserName(userInfo.getUserName());
        userInsertDTO.setRealName(userInfo.getRealName());
        userInsertDTO.setSex(userInfo.getSex() ? 1 : 0);
        userInsertDTO.setMobile(userInfo.getMobile());
        userInsertDTO.setEmail(userInfo.getEmail());
        userInsertDTO.setAccount(userInfo.getAccount());
        userInsertDTO.setRemark(userInfo.getRemark());
        userInsertDTO.setCompanyId(userInfo.getCompanyId());

        if (userTag != null) {
            userInsertDTO.setRoleId(userTag.getRoleId().intValue());
            userInsertDTO.setOccupation(userTag.getOccupation());
            userInsertDTO.setOccupationTel(userTag.getOccupationTel());
            userInsertDTO.setApartmentId(userTag.getApartmentId());
            userInsertDTO.setDuty(userTag.getDuty());
            userInsertDTO.setDutyMark(userTag.getDutyMark());
            userInsertDTO.setAreaId(userTag.getDutyArea());
            userInsertDTO.setTeamId(userTag.getTeamId());
            userInsertDTO.setUserType(userTag.getUserType().intValue());
        }

        return userInsertDTO;
    }

    public static TUserInfo toTUserInfo(UserInsertDTO userInsertDTO) {
        TUserInfo tUserInfo = new TUserInfo();

        tUserInfo.setId(userInsertDTO.getId());
        tUserInfo.setAvg(userInsertDTO.getAvg());
        tUserInfo.setUserName(userInsertDTO.getUserName());
        tUserInfo.setRemark(userInsertDTO.getRemark());
        tUserInfo.setRealName(userInsertDTO.getRealName());
        tUserInfo.setSex(userInsertDTO.getSex().equals(1));
        tUserInfo.setAccount(userInsertDTO.getAccount());
        tUserInfo.setMobile(userInsertDTO.getMobile());
        tUserInfo.setWechat(userInsertDTO.getWechat());
        tUserInfo.setQQ(userInsertDTO.getQq());
        tUserInfo.setEmail(userInsertDTO.getEmail());
        tUserInfo.setCompanyId(userInsertDTO.getCompanyId());

        return tUserInfo;
    }

    public static TUserTag toTUserTag(UserInsertDTO userInsertDTO, Integer userId){
        TUserTag userTag = new TUserTag();

        userTag.setUserId(userId);
        userTag.setApartmentId(userInsertDTO.getApartmentId());
        userTag.setUserType(userInsertDTO.getUserType().byteValue());
        userTag.setRoleId(userInsertDTO.getRoleId().byteValue());
        userTag.setOccupation(userInsertDTO.getOccupation());
        userTag.setOccupationTel(userInsertDTO.getOccupationTel());
        userTag.setDuty(userInsertDTO.getDuty());
        userTag.setDutyMark(userInsertDTO.getDutyMark());
        userTag.setDutyArea(userInsertDTO.getAreaId());
        userTag.setTeamId(userInsertDTO.getTeamId());

        return userTag;
    }

}
