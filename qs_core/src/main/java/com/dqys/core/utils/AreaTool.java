package com.dqys.core.utils;

import com.dqys.core.mapper.facade.TAreaMapper;
import com.dqys.core.model.TArea;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author by pan on 16-4-8.
 */
@Component
public class AreaTool implements ApplicationContextAware {


    private static final String AREA_RELATION_KEY = "area_relation_";      //地区
    private static RedisTemplate<String, Object> redisTemplate;
    private static TAreaMapper tAreaMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        tAreaMapper = applicationContext.getBean(TAreaMapper.class);
    }

    /**
     * 加载地区配置
     */
    public static void loadArea() {
        List<TArea> tAreas = tAreaMapper.selectAll();
        for(TArea tArea : tAreas) {
            redisTemplate.boundHashOps(TArea.class.getName()).put(tArea.getValue(), tArea);
        }
        //省份
        loadAreaByUpper(0);
    }

    /**
     * 根据上级加载地区配置
     */
    private static void loadAreaByUpper(Integer upper) {
        List<TArea> tAreaList = tAreaMapper.selectByUpper(upper);
        if(null != tAreaList && !tAreaList.isEmpty()) {
            List<Integer> ids = new ArrayList<>();
            for(TArea tArea : tAreaList) {
                ids.add(tArea.getValue());
                if(tArea.getIsLeaf()) {
                    return;
                }
                loadAreaByUpper(tArea.getValue());
            }
            redisTemplate.boundValueOps(AREA_RELATION_KEY + upper).set(ids);
        }
    }

    /**
     * 验证区域有效性
     *
     * @param province
     * @param city
     * @param area
     * @return
     */
    public static String validateArea(Integer province, Integer city, Integer area) {

        if(null == NoSQLWithRedisTool.getHashObject(TArea.class.getName(), province)) {
            return "省份无效";
        }
        if(null == NoSQLWithRedisTool.getHashObject(TArea.class.getName(), city) || !String.valueOf(city).startsWith(String.valueOf(province))) {
            return "地市无效";
        }
        if(null == NoSQLWithRedisTool.getHashObject(TArea.class.getName(), area) || !String.valueOf(area).startsWith(String.valueOf(city))) {
            return "区县无效";
        }

        return null;
    }
   /* public static String validateArea(Integer province, Integer city, Integer area) {

        if(null == NoSQLWithRedisTool.getHashObject(TArea.class.getName(), String.valueOf(province))) {
            return "省份无效";
        }
        if(null == NoSQLWithRedisTool.getHashObject(TArea.class.getName(), String.valueOf(city)) || !String.valueOf(city).startsWith(String.valueOf(province))) {
            return "地市无效";
        }
        if(null == NoSQLWithRedisTool.getHashObject(TArea.class.getName(), String.valueOf(area)) || !String.valueOf(area).startsWith(String.valueOf(city))) {
            return "区县无效";
        }

        return null;
    }*/

    /**
     * 根据ID获取区域
     *
     * @param aid
     * @return
     */
    public static TArea getAreaById(Integer aid) {
        return NoSQLWithRedisTool.getHashObject(TArea.class.getName(), aid);
    }


    /**
     * 根据上级区域获取区域列表
     *
     * @param upperId
     * @return
     * @throws Exception
     */
    public static List<TArea> listAreaByUpperId(Integer upperId) throws Exception {
        List<Integer> tAreaIds = NoSQLWithRedisTool.getValueObject(AREA_RELATION_KEY + upperId);
        List<TArea> tAreaList = new ArrayList<>();
        for (Integer aid : tAreaIds) {
            tAreaList.add(getAreaById(aid));
        }

        return tAreaList;
    }


    /**
     * 筛选列表
     *
     * @param tAreaList
     * @param nameLike
     * @return
     */
    public static List<TArea> filterAreaByName(List<TArea> tAreaList, String nameLike) {
        if (null == tAreaList || tAreaList.isEmpty()) {
            return Collections.emptyList();
        }

        Iterator<TArea> tAreas = tAreaList.iterator();
        while (tAreas.hasNext()) {
            TArea tArea = tAreas.next();
            if (tArea.getLabel().contains(nameLike) || getFirstCharFromChinese(tArea.getLabel()).contains(nameLike)) {
                continue;
            }

            tAreas.remove();
        }


        return null;
    }


    /**
     * 获取中文字符串首字母
     *
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
