package com.dqys.business.service.utils;

import com.dqys.business.orm.dto.AssetInfo;
import com.dqys.business.orm.dto.ContactsInfo;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pan on 16-5-19.
 */
public class GenUniqueNoTool {

    private static final String DAY_UNIQUE_KEY = "day_unique";

    /**
     * 获取借款人编号
     * @param city
     * @return
     */
    public static String getContactsNo(Integer city) {
        StringBuffer no = new StringBuffer("JKR");
        no.append(getFirstCharFromChinese(AreaTool.getAreaById(city).getName())).append(genDayStr()).append(genSeq(ContactsInfo.class.getName()));
        return no.toString();
    }

    /**
     * 获取抵押物编号
     *
     * @return
     */
    public static String getAssetNo() {
        StringBuffer no = new StringBuffer("DYW");
        no.append(genDayStr()).append(genSeq(AssetInfo.class.getName()));
        return no.toString();
    }


    private static String genDayStr() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    private static String genSeq(String key) {
        StringBuffer no = new StringBuffer();

        Long curNum = NoSQLWithRedisTool.getRedisTemplate().boundHashOps(key).increment(DAY_UNIQUE_KEY, 1);
        if (curNum < 10) {
            no.append("00000").append(curNum);
        } else if (curNum < 100) {
            no.append("0000").append(curNum);
        } else if (curNum < 1000) {
            no.append("000").append(curNum);
        } else if (curNum < 10000) {
            no.append("00").append(curNum);
        } else if (curNum < 100000) {
            no.append("0").append(curNum);
        } else {
            no.append(curNum);
        }

        return no.toString();
    }

    /**
     * 获取中文字符串首字母
     * @param chinese
     * @return
     */
    private static String getFirstCharFromChinese(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }
}
