package com.dqys.business.service.utils.common;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 导航栏工具
 * Created by yan on 16-9-19.
 */
public class NavUtil implements ApplicationContextAware {
    private static final String COMMON_SOURCE_NAV_KEY = "common_source_nav_";//公共资源实勘分类
    private static RedisTemplate<String, Object> redisTemplate;
    private static SourceNavigationMapper sourceNavigationMapper;
    private static int isCustom=1;//是否是用户自定义导航栏,0是,1不是
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        sourceNavigationMapper = applicationContext.getBean(SourceNavigationMapper.class);

        this.loadCommonNav();
    }

    /**
     * 加载公共资源实勘分类
     */
    public static void loadCommonNav(){
        List<SourceNavigation> list=sourceNavigationMapper.ListByIsCustom(isCustom);
        for(SourceNavigation navigation:list){
            redisTemplate.boundHashOps(NavUtil.COMMON_SOURCE_NAV_KEY+SourceNavigation.class.getName())
                    .put(NavUtil.COMMON_SOURCE_NAV_KEY+navigation.getType(), navigation);
        }
    }

    /**
     * 得到其中一个公共资源分类
     * @param navType 资源类型
     * @return
     */
    public static SourceNavigation getCommonSourceNavigation(Integer navType){
        SourceNavigation sourceNavigation=NoSQLWithRedisTool.getHashObject(NavUtil.COMMON_SOURCE_NAV_KEY+SourceNavigation.class.getName()
                ,NavUtil.COMMON_SOURCE_NAV_KEY+navType);
        return sourceNavigation;
    }
}
