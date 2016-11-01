package com.dqys.business.service.utils.common;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
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
     * 加载公共资源实勘分类,已即对应的不可见关系
     */
    public static void loadCommonNav(){
        List<SourceNavigation> list=sourceNavigationMapper.ListByIsCustom(isCustom);
       /* for(SourceNavigation navigation:list){
            redisTemplate.boundHashOps(NavUtil.COMMON_SOURCE_NAV_KEY+SourceNavigation.class.getName())
                    .put(NavUtil.COMMON_SOURCE_NAV_KEY+navigation.getType()+"_"+navigation.getName(), navigation);
        }*/
    }

    // TODO: 16-10-31　加载公共资源分类 Map<String type,List<SourceNavigation> navigationList>　ｔｙｐｅ与bt_source_nav的ｔｙｐｅ一致



    // TODO: 16-10-31  加载Map<String navId_type,List<SelectDto>>  其中type为公司类型，公司，角色，人员；公司类型关系表参考角色表自己建
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
    // TODO: 16-11-1  根据ｔｙｐｅ获取公共资源分类
    public static List<SourceNavigation> getSourceNavigationList(String type){
        return null;
    };
    // TODO: 16-11-1 根据 navId_type获取List<SelectDto>
    public static List<SelectDto> getSelectDtoList(String navId_type){
        return null;
    };
}
