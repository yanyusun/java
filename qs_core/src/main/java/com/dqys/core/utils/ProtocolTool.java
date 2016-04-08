package com.dqys.core.utils;


import com.dqys.core.constant.AuthHeaderEnum;
import com.dqys.core.constant.SysPropertyTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by pan on 9/21/15.
 */
public class ProtocolTool {

    private static ProtocolTool protocolTool = null;
    ;
    private static String encode = "UTF-8";

    private ProtocolTool() {
    }

    private static ProtocolTool getInstance() {
        if (protocolTool == null) {
            protocolTool = new ProtocolTool();
        }
        return protocolTool;
    }

    /**
     * 校验会员头信息，返回会员ID
     *
     * @param memberHeader
     * @param identity
     * @return
     * @throws Exception
     */
    public static Integer validateMemberHeader(String memberHeader, String identity) throws Exception {
        if (StringUtils.isEmpty(memberHeader)) return 0;

        String[] strs = memberHeader.split("\\|\\|\\|\\|");
        if (strs.length != 2) {
            return 0;
        }

        String encodeStr = ProtocolTool.getInstance().encodeMemberHeader(Integer.decode(strs[1]), identity);
        if (memberHeader.equalsIgnoreCase(encodeStr)) {
            return Integer.decode(strs[1]);
        }

        return 0;
    }

    /**
     * 创建会员头部信息
     *
     * @param memberId
     * @param identity
     * @param loginTime
     * @return
     * @throws Exception
     */
    public static Map<String, Object> createMemberHeader(Integer memberId, String identity, Date loginTime) throws Exception {
        String headerValue = ProtocolTool.getInstance().encodeMemberHeader(memberId, identity);
        //头部信息
        Map<String, Object> memberHeader = new HashMap();
        memberHeader.put(AuthHeaderEnum.X_QS_MEMBER.getValue(), headerValue);
        //身份信息
        memberHeader.put(AuthHeaderEnum.X_QS_IDENTITY.getValue(), identity);
        //登陆时间
        if (null == loginTime) {
            memberHeader.put(AuthHeaderEnum.X_QS_DATETIME.getValue(), System.currentTimeMillis());
        } else {
            memberHeader.put(AuthHeaderEnum.X_QS_DATETIME.getValue(), loginTime.getTime());
        }

        return memberHeader;
    }

    /* 加密会员头部信息 */
    private String encodeMemberHeader(Integer memberId, String identity) throws Exception {
        String salt = SysPropertyTool.getProperty(SysPropertyTypeEnum.GLOBAL, "protocol_salt").getPropertyValue();
        String headerValue = SignatureTool.base64Encode(SignatureTool.md5Encode("mid=" + memberId + "&" + "identity=" + identity, encode) + salt, encode) + "||||" + memberId;
        return headerValue;
    }
}
