package com.dqys.core.utils;

import com.dqys.core.mapper.facade.TAreaMapper;
import com.dqys.core.model.TArea;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
            redisTemplate.boundHashOps(TArea.class.getName()).put(tArea.getId(), tArea);
            if(tArea.getIsLeaf()) {
                return;
            }
            loadAreaByUpper(tArea.getId());
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
                ids.add(tArea.getId());
                if(tArea.getIsLeaf()) {
                    return;
                }
                loadAreaByUpper(tArea.getId());
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
    }

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
        List<TArea> tAreaList = NoSQLWithRedisTool.getValueObject(AREA_RELATION_KEY + upperId);
        return tAreaList;
    }




}
