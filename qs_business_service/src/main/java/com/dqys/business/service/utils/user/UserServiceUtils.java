package com.dqys.business.service.utils.user;

import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.service.dto.user.UserFileDTO;
import com.dqys.business.service.dto.user.UserInsertDTO;
import com.dqys.business.service.dto.user.UserListDTO;
import com.dqys.core.base.SysProperty;
import com.dqys.core.constant.RoleTypeEnum;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Yvan on 16/6/29.
 */
public class UserServiceUtils {


    public static UserListDTO toUserListDTO(TUserInfo userInfo, TCompanyInfo companyInfo, TUserTag userTag,
                                            String apartment, Integer total, Integer going) {
        if (CommonUtil.checkParam(userInfo, userInfo.getId())) {
            return null;
        }
        UserListDTO userListDTO = new UserListDTO();

        userListDTO.setId(userInfo.getId());
        userListDTO.setStatus(userInfo.getStatus());
        userListDTO.setAvg(userInfo.getAvg());
        userListDTO.setUserName(userInfo.getUserName());
        userListDTO.setRealName(userInfo.getRealName());
        userListDTO.setSex(
                (userInfo.getSex() == null || userInfo.getSex().equals(SysProperty.BOOLEAN_TRUE)) ? 1 : 0);
        userListDTO.setAccount(userInfo.getAccount());
        userListDTO.setMobile(userInfo.getMobile());
        userListDTO.setEmail(userInfo.getEmail());

        userListDTO.setApartment(apartment);
        userListDTO.setOccupation(userTag.getOccupation());
        userListDTO.setDuty(userTag.getDuty());
        userListDTO.setRoleName(RoleTypeEnum.get(Integer.valueOf(userTag.getRoleId())));
        userListDTO.setUseStatus(userInfo.getUseStatus());
        if (companyInfo != null) {
            userListDTO.setCompany(companyInfo.getCompanyName());
            userListDTO.setArea(AreaTool.getAreaById(companyInfo.getProvince()).getLabel()
                            + AreaTool.getAreaById(companyInfo.getCity()).getLabel()
                            + AreaTool.getAreaById(companyInfo.getArea()).getLabel()
            );
        }
        userListDTO.setTaskNum(total); // 总任务数量
        userListDTO.setOnGoingNum(going); // 正在进行
        // todo 默认在职
        userListDTO.setWork(SysProperty.BOOLEAN_TRUE);

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
            userInsertDTO.setYearsLimit(userTag.getYearsLimit());
            userInsertDTO.setEntryTime(userTag.getEntryTime());
            userInsertDTO.setWorkStatus(userTag.getWorkStatus());
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

    public static TUserTag toTUserTag(UserInsertDTO userInsertDTO, Integer userId) {
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

    public static TUserInfo toUserInfo(UserFileDTO userFileDTO) {
        TUserInfo userInfo = new TUserInfo();

        userInfo.setRealName(userFileDTO.getRealName());
        userInfo.setUserName(userFileDTO.getUserName());
        userInfo.setSex(userFileDTO.getSex().equals(SysProperty.BOOLEAN_TRUE));
        userInfo.setAccount(userFileDTO.getAccount());
        userInfo.setMobile(userFileDTO.getMobile());
        userInfo.setWechat(userFileDTO.getWechat());
        userInfo.setEmail(userFileDTO.getEmail());
        userInfo.setQQ(userFileDTO.getQq());
        userInfo.setCreateAt(userFileDTO.getJoinAt());
        userInfo.setRemark(userFileDTO.getRemark());
        userInfo.setSalt(RandomStringUtils.randomAlphabetic(6));

        return userInfo;
    }

    public static TUserTag toUserTag(UserFileDTO userFileDTO) {
        TUserTag userTag = new TUserTag();

        userTag.setRoleId(userFileDTO.getRole().byteValue());
        userTag.setDuty(userFileDTO.getDuty());
        userTag.setDutyMark(userFileDTO.getDutyMark());
        userTag.setOccupationTel(userFileDTO.getOfficeTel());
        userTag.setOccupation(userFileDTO.getOccupation());
        if (userFileDTO.getJoinAt() != null) {
            userTag.setEntryTime(DateFormatTool.format(userFileDTO.getJoinAt(), DateFormatTool.DATE_FORMAT_10_REG1));
        }
        userTag.setYearsLimit(userFileDTO.getYear());


        return userTag;
    }


    public static String checkData(UserInsertDTO userInsertDTO) {
        if (CommonUtil.checkParam(userInsertDTO, userInsertDTO.getRealName(),
                userInsertDTO.getUserName(), userInsertDTO.getSex(), userInsertDTO.getRoleId(),
                userInsertDTO.getAccount(), userInsertDTO.getMobile(), userInsertDTO.getWechat(),
                userInsertDTO.getEmail(), userInsertDTO.getOccupation(), userInsertDTO.getApartmentId(),
                userInsertDTO.getDuty(), userInsertDTO.getAreaId(), userInsertDTO.getUserType())) {
            return "参数错误";
        }
        return null;
    }

    public static Integer headerStringToInt(String head) {
        String s = head.split(",")[0];
        return Integer.valueOf(s);
    }

}
