package com.dqys.business.service.utils.common;

import com.dqys.business.orm.mapper.common.SourceNavigationMapper;
import com.dqys.business.orm.pojo.common.SourceNavigation;
import com.dqys.business.service.constant.SourceInfoEnum;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 导航栏工具
 * Created by yan on 16-9-19.
 */
public class NavUtil implements ApplicationContextAware {
    private static final String COMMON_SOURCE_NAV_KEY = "common_source_nav_";//公共资源实勘分类
    private static final String COMMON_SOURCE_NAV_LIST_TYPE = "nav_list_type";//资料实勘列表类型
    private static final String COMMON_SOURCE_NAV_LIST_TYPE_MAP = "nav_list_type_map";//资料实勘根据type分类的map
    private static final String COMMON_SOURCE_NAV_TYPE_UNVIEW_MAP = "nav_type_unview_map";//资料实勘对应导航对应权限的不可见内容map
    private static RedisTemplate<String, Object> redisTemplate;
    private static SourceNavigationMapper sourceNavigationMapper;
    private static NavUnviewRoleService navUnviewRoleService;
    private static NavUnviewCompanyService navUnviewCompanyService;
    private static NavUnviewUserInfoService navUnviewUserInfoService;
    private static NavUnviewUserTypeService navUnviewUserTypeService;
    private static int isCustom = 1;//是否是用户自定义导航栏,0是,1不是
    private static int CERTIFICATE_TYPE = SourceInfoEnum.CERTIFICATE_TYPE.getValue();//合同类型
    private static int ENTITY_TYPE = SourceInfoEnum.ENTITY_TYPE.getValue();//资料实勘类型

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
        sourceNavigationMapper = applicationContext.getBean(SourceNavigationMapper.class);
        navUnviewRoleService = applicationContext.getBean(NavUnviewRoleService.class);
        navUnviewCompanyService = applicationContext.getBean(NavUnviewCompanyService.class);
        navUnviewUserInfoService = applicationContext.getBean(NavUnviewUserInfoService.class);
        navUnviewUserTypeService = applicationContext.getBean(NavUnviewUserTypeService.class);
        // TODO: 16-11-8 测试不加载
       // this.loadCommonNav();
    }

    /**
     * 加载公共资源实勘分类,已即对应的不可见关系
     */
    public static void loadCommonNav() {
        List<SourceNavigation> list = sourceNavigationMapper.ListByIsCustom(isCustom);
        List<SourceNavigation> certificateList = new LinkedList<>();
        List<SourceNavigation> entityTypeLsit = new LinkedList<>();
        Map<String, List<SourceNavigation>> commomNavMap = new HashMap<>();
        Map<String ,List<SelectDto>> selectDtoListMap = new HashMap<>();
        for (SourceNavigation navigation : list) {
            if (navigation.getType() == CERTIFICATE_TYPE) {//添加分类导航
                certificateList.add(navigation);
                setNavIdSelectDto(selectDtoListMap,navigation.getId());
            } else if (navigation.getType() == ENTITY_TYPE) {//添加实勘分类导航
                entityTypeLsit.add(navigation);
                setNavIdSelectDto(selectDtoListMap,navigation.getId());
            }
        }
        commomNavMap.put(COMMON_SOURCE_NAV_LIST_TYPE + "_" + CERTIFICATE_TYPE, certificateList);
        commomNavMap.put(COMMON_SOURCE_NAV_LIST_TYPE + "_" + ENTITY_TYPE, entityTypeLsit);
        // 载公共资源分类 Map<String type,List<SourceNavigation> navigationList>　ｔｙｐｅ与bt_source_nav的ｔｙｐｅ一致
        redisTemplate.boundHashOps(NavUtil.COMMON_SOURCE_NAV_KEY + SourceNavigation.class.getName())
                .put(COMMON_SOURCE_NAV_LIST_TYPE_MAP, commomNavMap);
        //  16-10-31  加载Map<String navId_type,List<SelectDto>>  其中type为公司类型，公司，角色，人员；
        redisTemplate.boundHashOps(NavUtil.COMMON_SOURCE_NAV_KEY + SourceNavigation.class.getName())
                .put(COMMON_SOURCE_NAV_TYPE_UNVIEW_MAP, selectDtoListMap);
    }




    /**
     * 得到其中一个公共资源分类
     *
     * @param navType 资源类型
     * @return
     */
    public static  List<SourceNavigation> getCommonSourceNavigation(Integer navType) {
        Map<String, List<SourceNavigation>> commomNavMap = NoSQLWithRedisTool.getHashObject(NavUtil.COMMON_SOURCE_NAV_KEY + SourceNavigation.class.getName()
                , COMMON_SOURCE_NAV_LIST_TYPE_MAP);
        if(commomNavMap!=null){
            return commomNavMap.get(COMMON_SOURCE_NAV_LIST_TYPE+"_"+navType);
        }else{
            loadCommonNav();
            return sourceNavigationMapper.listByTypeAndLenderId(0, null, navType);
        }
    }


    // TODO: 16-11-1 根据 navId_type获取List<SelectDto>(对应的NavUnviewEnum枚举),查询operUser为ｏ的记录，ｏ公共默认
    public static List<SelectDto> getSelectDtoList(int navId,int type) {
        Map<String ,List<SelectDto>> selectDtoListMap = NoSQLWithRedisTool.getHashObject(NavUtil.COMMON_SOURCE_NAV_KEY + SourceNavigation.class.getName(),COMMON_SOURCE_NAV_TYPE_UNVIEW_MAP);
        return selectDtoListMap.get(getNavIdTypeKey(navId,type));
    }

    private static String getNavIdTypeKey(int navId,int type){
        return navId+"_"+type;
    }

    /**
     * 设置某一个navId的
     * @param selectDtoListMap
     * @param navId
     */
    private static void setNavIdSelectDto(Map<String ,List<SelectDto>> selectDtoListMap,Integer navId){
        selectDtoListMap.put(getNavIdTypeKey(navId,NavUnviewEnum.USER_TYPE.getValue())
                ,navUnviewUserTypeService.getInit(navId));
        selectDtoListMap.put(getNavIdTypeKey(navId,NavUnviewEnum.COMPANY.getValue())
                ,navUnviewCompanyService.getInit(navId));
        selectDtoListMap.put(getNavIdTypeKey(navId,NavUnviewEnum.ROLE.getValue())
                ,navUnviewRoleService.getInit(navId));
        selectDtoListMap.put(getNavIdTypeKey(navId,NavUnviewEnum.USER_INFO.getValue())
                ,navUnviewUserInfoService.getInit(navId));
    }
    /**
     * 加入处reId外的值
     *
     * @param selectDtos
     * @return
     */
    public static List<Integer> selectDtosToIntegers(List<SelectDto> selectDtos) {
        List<Integer> integers = new LinkedList<>();
        for (SelectDto selectDto : selectDtos) {
            integers.add(selectDto.getReId());
        }
        return integers;
    }

}
