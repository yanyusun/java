package com.dqys.core.utils;


import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.constant.KeyEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.model.UserSession;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author by pan on 9/21/15.
 */
public abstract class ProtocolTool {

    private static final String ENCODE = "UTF-8";

    private static final String USER_LOGIN_KEY = "user_login_";
    private static final String LOGIN_SUB_TIME = "login_time";

    /**
     * 创建会员头信息
     *  @param uid
     * @param userTypes
     * @param roleIds
     * @param status
     * @throws Exception
     */
    public static Map<String, Object> createUserHeader(Integer uid, String userTypes, String roleIds, String isCertifieds, Integer status) throws Exception {
        String headerValue = encodeHeader(uid, userTypes, roleIds, isCertifieds, status);
        Map<String, Object> userHeader = new HashMap();
        userHeader.put(AuthHeaderEnum.X_QS_USER.getValue(), headerValue);
        userHeader.put(AuthHeaderEnum.X_QS_TYPE.getValue(), userTypes);
        userHeader.put(AuthHeaderEnum.X_QS_ROLE.getValue(),  roleIds);
        userHeader.put(AuthHeaderEnum.X_QS_STATUS.getValue(), status);
        userHeader.put(AuthHeaderEnum.X_QS_CERTIFIED.getValue(), isCertifieds);

        refreshUserHeader(uid);

        return userHeader;
    }

    /**
     * 验证用户
     * @param userHeader
     * @param userTypes
     * @param roleIds
     * @param status
     * @return
     * @throws Exception
     */
    public static Integer validateUser(String userHeader, String userTypes, String roleIds, String isCertifieds, String status) throws Exception {
        if (StringUtils.isEmpty(userHeader)) return 0;

        String[] strs = decodeHeader(userHeader).split("\\|\\|\\|\\|");
        if(strs.length != 2) {
            return 0;
        }

        //长时间未登陆的需要重新登陆
        Object o = NoSQLWithRedisTool.getHashObject(USER_LOGIN_KEY + strs[1], LOGIN_SUB_TIME);
        if(null == o) {
            return 0;
        }

        String headerValue = encodeHeader(Integer.decode(strs[1]), userTypes, roleIds, isCertifieds, Integer.decode(status));

        if(userHeader.equals(headerValue)) {
            Integer userId = Integer.decode(strs[1]);
            UserSession userSession = new UserSession();
            userSession.setUserId(userId);
            userSession.setUserType(userTypes);
            userSession.setRoleId(roleIds);
            userSession.setIsCertified(isCertifieds);
            userSession.setStatus(status);
            UserSession.setCurrent(userSession);
            ProtocolTool.refreshUserHeader(userId);

            return userId;
        }

        return 0;
    }

    /**
     * 验证系统超级管理员
     * @param userTypes
     * @param roles
     * @param certifieds
     * @param status
     * @return
     */
    public static boolean validateSysAdmin(String userTypes, String roles, String certifieds, String status) {
        String[] typesTmp = userTypes.split(",");
        for(int i=0; i<typesTmp.length; i++) {
            if(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.UTYPE_PLATFORM_KEY).getPropertyValue().equals(typesTmp[i])) {
                if(Boolean.parseBoolean(status) && Boolean.parseBoolean(certifieds.split(",")[i]) &&
                        SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_ADMINISTRATOR_KEY).getPropertyValue().endsWith(roles.split(",")[i])) {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * 验证系统管理员
     * @param userTypes
     * @param roles
     * @param certifieds
     * @param status
     * @return
     */
    public static boolean validateSysManager(String userTypes, String roles, String certifieds, String status) {
        String[] typesTmp = userTypes.split(",");
        for(int i=0; i<typesTmp.length; i++) {
            if(SysPropertyTool.getProperty(SysPropertyTypeEnum.USER_TYPE, KeyEnum.UTYPE_PLATFORM_KEY).getPropertyValue().equals(typesTmp[i])) {
                if(Boolean.parseBoolean(status) && Boolean.parseBoolean(certifieds.split(",")[i]) &&
                        Integer.valueOf(SysPropertyTool.getProperty(SysPropertyTypeEnum.ROLE, KeyEnum.ROLE_MANAGER_KEY).getPropertyValue()).intValue() >=
                                Integer.valueOf(roles.split(",")[i]).intValue() &&
                        Integer.valueOf(roles.split(",")[i]).intValue() > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 刷新头信息的过期时间
     * @param uid
     */
    public static void refreshUserHeader(Integer uid) {
        NoSQLWithRedisTool.setHashObject(USER_LOGIN_KEY + uid, LOGIN_SUB_TIME, new Date());
        NoSQLWithRedisTool.setExpire(USER_LOGIN_KEY + uid,
                Integer.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_USER_EXPIRE_DAYS_KEY).getPropertyValue()),
                TimeUnit.DAYS);
    }

    /* 编码头信息 */
    private static String encodeHeader(Integer uid, String userTypes, String roleIds, String isCertifieds, Integer status) throws Exception {
        if(userTypes.split(",").length != roleIds.split(",").length || roleIds.split(",").length != isCertifieds.split(",").length) {
            return null;
        }
        return SignatureTool.base64Encode(
                SignatureTool.md5Encode(
                        "uid=" + uid +
                                "&type=" + userTypes.trim() +
                                "&salt=" + SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, KeyEnum.SYS_PROTOCOL_SALT_KEY).getPropertyValue().trim() +
                                "&roleId=" + roleIds.trim() +
                                "&isCertified=" + isCertifieds.trim()+
                                "&status=" + status, ENCODE ) +
                        "||||" + uid,
                ENCODE
        );
    }

    /* 解码头信息 */
    private static String decodeHeader(String header) throws Exception {
        return SignatureTool.base64Decode(header, ENCODE);
    }
}
