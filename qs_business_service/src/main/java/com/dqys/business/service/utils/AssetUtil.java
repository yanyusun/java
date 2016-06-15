package com.dqys.business.service.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Yvan on 16/6/12.
 */
public class AssetUtil {

    public static final String PRE_ASSET_CODE = "QS"; // 格式:QS160612XXXX
    public static final String PRE_PAWN_CODE = "抵"; // 格式:抵AXXXXXX
    public static final String PRE_LENDER_CODE = "JKR"; // 格式:JKR160612XXXX
    public static final String MIDDLE_IOU_CODE = "-借"; // 格式:XXX-借01
//    public static final String _CODE = "-借";

    public static final String DATE_FORMAT_6 = "yyMMdd";
    public static final String DATE_FORMAT_8 = "yyyyMMdd";
    public static final String DATE_FORMAT_10 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_101 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";

    public static final String format(String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }

    public static final String[] UPLETTER = {"A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N",
            "O","P","Q","R","S","T",
            "U","V","W","X","Y","Z"};
    public static final String[] LOWERLETTER = {"a","b","c","d","e","f","g",
            "h","i","j","k","l","m","n",
            "o","p","q","r","s","t",
            "u","v","w","x","y","z"};

    /**
     * 生成资产包号
     * @return
     */
    public static String createAssetCode(){
        return PRE_ASSET_CODE
                + format(DATE_FORMAT_6)
                + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 生成借据号
     * @param name
     * @return
     */
    public static String createIouCode(String name){
        return name + MIDDLE_IOU_CODE + RandomStringUtils.randomNumeric(6);
    }

    /**
     * 生成抵押物号
     * @return
     */
    public static String createPawnCode(){
        return PRE_PAWN_CODE
                + UPLETTER[Integer.valueOf(RandomStringUtils.randomNumeric(3))%24]
                + RandomStringUtils.randomNumeric(6);
    }
}
