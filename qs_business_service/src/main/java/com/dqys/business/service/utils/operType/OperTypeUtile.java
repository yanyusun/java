package com.dqys.business.service.utils.operType;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.service.OperTypeService;
import com.dqys.core.cache.MybatisRedisCache;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化
 * Created by mkfeng on 2016/7/7.
 */
@Component
public class OperTypeUtile implements ApplicationContextAware {
    private static RedisTemplate<String, Object> redisTemplate;
    private static OperTypeService operTypeService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        operTypeService = applicationContext.getBean(OperTypeService.class);
        opertype();
    }

    public void opertype() {
        MybatisRedisCache mybatisRedisCache = new MybatisRedisCache();
        List<Integer> userIds = operTypeService.selectByUserIds();
        List<Integer> roleIds = operTypeService.selectByRoleIds();
        List<Integer> objectIds = operTypeService.selectByObjectIds();
        for (Integer use : userIds) {
            for (Integer rol : roleIds) {
                for (Integer obj : objectIds) {
                    List<OperType> operTypes = operTypeService.selectByRoleToOperType(rol, use, obj);
                    mybatisRedisCache.putObject(use + "_" + rol + "_" + obj, operTypes);
                }
            }
        }
    }
}
