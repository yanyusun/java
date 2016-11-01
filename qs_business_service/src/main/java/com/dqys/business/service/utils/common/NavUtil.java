package com.dqys.business.service.utils.common;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.dto.sourceAuth.SelectDto;
import com.dqys.business.service.service.common.NavUnviewCompanyService;
import com.dqys.business.service.service.common.NavUnviewRoleService;
import com.dqys.business.service.service.common.NavUnviewUserInfoService;
import com.dqys.business.service.service.common.NavUnviewUserTypeService;
import com.dqys.core.constant.NavUnviewEnum;
import com.dqys.core.utils.NoSQLWithRedisTool;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 导航栏工具
 * Created by yan on 16-9-19.
 */
public class NavUtil implements ApplicationContextAware {
    private static final String COMMON_SOURCE_NAV_KEY = "common_source_nav_";//公共资源实勘分类
    private static RedisTemplate<String, Object> redisTemplate;
    private static SourceNavigationMapper sourceNavigationMapper;
    private static NavUnviewRoleService navUnviewRoleService;
    private static NavUnviewCompanyService navUnviewCompanyService;
    private static NavUnviewUserInfoService navUnviewUserInfoService;
    private static NavUnviewUserTypeService navUnviewUserTypeService;
    private static int isCustom = 1;//是否是用户自定义导航栏,0是,1不是

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        sourceNavigationMapper = applicationContext.getBean(SourceNavigationMapper.class);
        navUnviewRoleService = applicationContext.getBean(NavUnviewRoleService.class);
        navUnviewCompanyService = applicationContext.getBean(NavUnviewCompanyService.class);
        navUnviewUserInfoService = applicationContext.getBean(NavUnviewUserInfoService.class);
        navUnviewUserTypeService = applicationContext.getBean(NavUnviewUserTypeService.class);
        this.loadCommonNav();
    }

    /**
     * 加载公共资源实勘分类,已即对应的不可见关系
     */
    public static void loadCommonNav() {
        List<SourceNavigation> list = sourceNavigationMapper.ListByIsCustom(isCustom);
       /* for(SourceNavigation navigation:list){
            redisTemplate.boundHashOps(NavUtil.COMMON_SOURCE_NAV_KEY+SourceNavigation.class.getName())
                    .put(NavUtil.COMMON_SOURCE_NAV_KEY+navigation.getType()+"_"+navigation.getName(), navigation);
        }*/
    }

    // TODO: 16-10-31　加载公共资源分类 Map<String type,List<SourceNavigation> navigationList>　ｔｙｐｅ与bt_source_nav的ｔｙｐｅ一致


    // TODO: 16-10-31  加载Map<String navId_type,List<SelectDto>>  其中type为公司类型，公司，角色，人员；公司类型关系表参考角色表自己建

    /**
     * 得到其中一个公共资源分类
     *
     * @param navType 资源类型
     * @return
     */
    public static SourceNavigation getCommonSourceNavigation(Integer navType) {
        SourceNavigation sourceNavigation = NoSQLWithRedisTool.getHashObject(NavUtil.COMMON_SOURCE_NAV_KEY + SourceNavigation.class.getName()
                , NavUtil.COMMON_SOURCE_NAV_KEY + navType);
        return sourceNavigation;
    }

    // TODO: 16-11-1  根据ｔｙｐｅ获取公共资源分类，实勘1|证件合同0(默认)|2根进'
    public static List<SourceNavigation> getSourceNavigationList(Integer type) {
        List<SourceNavigation> list = null;
        list = NoSQLWithRedisTool.getValueObject(type.toString());
        if (list == null) {
            list = sourceNavigationMapper.listByTypeAndLenderId(0, null, type);
            for (SourceNavigation sour : list) {
                if (sour != null) {
                    navUnviewCompanyService.getList(sour.getId());
                    navUnviewRoleService.getList(sour.getId());
                    navUnviewUserInfoService.getList(sour.getId());
                    navUnviewUserTypeService.getList(sour.getId());
                }
            }
            NoSQLWithRedisTool.setValueObject(type.toString(), list, 31L, TimeUnit.DAYS);
        }
        return list;
    }

    ;

    // TODO: 16-11-1 根据 navId_type获取List<SelectDto>(对应的NavUnviewEnum枚举),查询operUser为ｏ的记录，ｏ公共默认
    public static List<SelectDto> getSelectDtoList(String navId_type) {
        List<SelectDto> dtos = NoSQLWithRedisTool.getValueObject(navId_type);
        if (dtos == null) {
            String[] str = navId_type.split("_");
            if (str.length == 2) {
                String type = str[1];
                if (type.equals(NavUnviewEnum.COMPANY.getValue().toString())) {
                    dtos = navUnviewCompanyService.getList(Integer.parseInt(str[0]));
                } else if (type.equals(NavUnviewEnum.USER_INFO.getValue().toString())) {
                    dtos = navUnviewUserInfoService.getList(Integer.parseInt(str[0]));
                } else if (type.equals(NavUnviewEnum.ROLE.getValue().toString())) {
                    dtos = navUnviewRoleService.getList(Integer.parseInt(str[0]));
                } else if (type.equals(NavUnviewEnum.USER_TYPE.getValue().toString())) {
                    dtos = navUnviewUserTypeService.getList(Integer.parseInt(str[0]));
                }
            }
        }
        return dtos;
    }

    ;
}
