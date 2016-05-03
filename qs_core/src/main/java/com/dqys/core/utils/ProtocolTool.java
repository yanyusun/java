package com.dqys.core.utils;


import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.constant.SysKeyEnum;
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
     *
     * @param uid
     * @param userType
     * @param roleId
     * @param status
     *@param isCertified  @return
     * @throws Exception
     */
    public static Map<String, Object> createUserHeader(Integer uid, Byte userType, Byte roleId, Boolean status, Boolean isCertified) throws Exception {
        String headerValue = encodeHeader(String.valueOf(uid), String.valueOf(userType), null == roleId?"":String.valueOf(roleId), String.valueOf(status), String.valueOf(isCertified));
        Map<String, Object> userHeader = new HashMap();
        userHeader.put(AuthHeaderEnum.X_QS_USER.getValue(), headerValue);
        userHeader.put(AuthHeaderEnum.X_QS_TYPE.getValue(), userType);
        userHeader.put(AuthHeaderEnum.X_QS_ROLE.getValue(), (null==roleId?"":roleId));
        userHeader.put(AuthHeaderEnum.X_QS_STATUS.getValue(), status);
        userHeader.put(AuthHeaderEnum.X_QS_CERTIFIED.getValue(), isCertified);

        refreshUserHeader(uid);

        return userHeader;
    }

    public static Integer validateUser(String userHeader, String userType, String roleId, String status, String isCertified) throws Exception {
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

        String headerValue = encodeHeader(strs[1], userType, null == roleId?"":roleId, status, isCertified);


        if(userHeader.equals(headerValue)) {
            Integer userId = Integer.decode(strs[1]);
            UserSession userSession = new UserSession();
            userSession.setUserId(userId);
            userSession.setUserType(Integer.decode(userType));
            userSession.setRoleId(StringUtils.isNotBlank(roleId)?Integer.decode(roleId):null);
            userSession.setStatus(Boolean.parseBoolean(status));
            userSession.setCertified(Boolean.parseBoolean(isCertified));
            UserSession.setCurrent(userSession);
            ProtocolTool.refreshUserHeader(userId);

            return userId;
        }

        return 0;
    }

    /**
     * 刷新头信息的过期时间
     * @param uid
     */
    public static void refreshUserHeader(Integer uid) {
        NoSQLWithRedisTool.setHashObject(USER_LOGIN_KEY + uid, LOGIN_SUB_TIME, new Date());
        NoSQLWithRedisTool.setExpire(USER_LOGIN_KEY + uid,
                Integer.decode(SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, SysKeyEnum.SYS_USER_EXPIRE_DAYS_KEY).getPropertyValue()),
                TimeUnit.DAYS);
    }

    /* 编码头信息 */
    private static String encodeHeader(String uid, String userType, String roleId, String status, String isCertified) throws Exception {
        return SignatureTool.base64Encode(
                SignatureTool.md5Encode(
                        "uid=" + uid +
                                "&type=" + userType.trim() +
                                "&salt=" + SysPropertyTool.getProperty(SysPropertyTypeEnum.SYS, SysKeyEnum.SYS_PROTOCOL_SALT_KEY).getPropertyValue().trim() +
                                "&roleId=" + roleId.trim() +
                                "&isCertified=" + isCertified.trim(), ENCODE) +
                        "||||" + uid,
                ENCODE
        );
    }

    /* 解码头信息 */
    private static String decodeHeader(String header) throws Exception {
        return SignatureTool.base64Decode(header, ENCODE);
    }
}
