package com.dqys.core.utils;

import com.dqys.core.mapper.TAreaMapper;
import com.dqys.core.model.TArea;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author by pan on 16-4-8.
 */
@Component
public class AreaTool implements ApplicationContextAware {

    public static final String AREA_KEY = "sys_area_";

    private static RedisTemplate<String, Object> redisTemplate;
    private static TAreaMapper tAreaMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        tAreaMapper = applicationContext.getBean(TAreaMapper.class);
    }

    /**
     * 加载地区配置
     * @throws Exception
     */
    public static void loadArea() throws Exception {
        loadAreaByUpper(0);
    }
    private static void loadAreaByUpper(Integer upper) {
        List<TArea> tAreaList = tAreaMapper.selectByUpper(upper);
        if(null != tAreaList && !tAreaList.isEmpty()) {
            redisTemplate.boundValueOps(AREA_KEY + upper).set(tAreaList);
            for(TArea tArea : tAreaList) {
                if(tArea.getIsLeaf()) {
                    return;
                }
                loadAreaByUpper(tArea.getId());
            }
        }
    }

    /**
     * 根据上级区域获取区域列表
     * @param upperId
     * @return
     * @throws Exception
     */
    public static List<TArea> listAreaByUpperId(Integer upperId) throws Exception {
        List<TArea> tAreaList = NoSQLWithRedisTool.getValueObject(AREA_KEY + upperId);
        return tAreaList;
    }
}
