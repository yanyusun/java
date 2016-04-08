package com.dqys.core.utils;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * 签名加解码工具
 *
 * @author by pan on 9/21/15.
 */
public class SignatureTool {

    /**
     * 对字符串按指定字符集进行编码
     *
     * @param s
     * @param encode
     * @return
     * @throws Exception
     */
    public static String md5Encode(String s, String encode) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        if (StringUtils.isEmpty(encode)) {
            md.update(s.getBytes());
        } else {
            md.update(s.getBytes(encode));
        }
        StringBuffer buf = new StringBuffer();
        for (byte b : md.digest()) {
            buf.append(String.format("%02x", b & 0xff));
        }

        return buf.toString();
    }


    /**
     * Base64编码
     *
     * @param s
     * @param encode
     * @return
     * @throws Exception
     */
    public static String base64Encode(String s, String encode) throws Exception {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        if (StringUtils.isEmpty(encode)) {
            return base64Encoder.encode(s.getBytes());
        } else {
            return base64Encoder.encode(s.getBytes(encode));
        }
    }

    /**
     * Base64解码
     *
     * @param s
     * @param encode
     * @return
     * @throws Exception
     */
    public static String base64Decode(String s, String encode) throws Exception {
        return new String(new BASE64Decoder().decodeBuffer(s), encode);
    }
}
