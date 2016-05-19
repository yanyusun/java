package com.dqys.business.service.utils;

import com.dqys.core.model.TArea;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.NoSQLWithRedisTool;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by pan on 16-5-19.
 */
public class UniqueNoGenTool {

    /**
     * 获取借款人编号
     *
     * @param city
     * @return
     */
    public static String getJieKuanRenNo(Integer city) {
        StringBuffer no = new StringBuffer("JKR");

        //NoSQLWithRedisTool.getRedisTemplate().boundHashOps(TArea.class.getName()).hasKey(3303);

        //地市
        no.append(getFirstCharFromChinese(AreaTool.getAreaById(city).getName()));

        return "";
    }



    /**
     * 获取中文字符串首字母
     * @param chinese
     * @return
     */
    public static String getFirstCharFromChinese(String chinese) {
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
