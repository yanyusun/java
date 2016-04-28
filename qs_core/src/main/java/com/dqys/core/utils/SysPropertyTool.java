package com.dqys.core.utils;

import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.mapper.facade.TSysPropertyMapper;
import com.dqys.core.model.TSysProperty;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * @author by pan on 16-4-7.
 */
@Component
public class SysPropertyTool implements ApplicationContextAware {

    public static final String PROPERTY_KEY = TSysProperty.class.getName();

    private static RedisTemplate<String, Object> redisTemplate;
    private static TSysPropertyMapper tSysPropertyMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        tSysPropertyMapper = applicationContext.getBean(TSysPropertyMapper.class);
        redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
    }

    /**
     * 添加配置项
     * @param tSysProperty
     * @return
     */
    public static boolean addSysProperty(TSysProperty tSysProperty) {
        int count = tSysPropertyMapper.insertSelective(tSysProperty);
        if(1 == count) {
            tSysProperty = tSysPropertyMapper.selectByPromaryKey(tSysProperty.getId());
            refreshPropery(tSysProperty);
            return true;
        }
        return false;
    }

    /**
     * 更新配置项
     * @param tSysProperty
     * @return
     */
    public static boolean updateSysProperty(TSysProperty tSysProperty) {
        int count = tSysPropertyMapper.updateByPrimaryKeySelective(tSysProperty);
        if(1 == count) {
            removeProperty(tSysProperty.getId());
            tSysProperty = tSysPropertyMapper.selectByPromaryKey(tSysProperty.getId());
            refreshPropery(tSysProperty);
            return true;
        }

        return false;
    }

    /**
     * 删除配置项
     * @param id
     * @return
     */
    public static boolean deleteSysProperty(Integer id) {
        int count = tSysPropertyMapper.delete(id);
        if(1 == count) {
            removeProperty(id);
            return true;
        }

        return false;
    }

    /**
     * 加载全部配置项
     * @throws Exception
     */
    public static void loadAllProperty() throws Exception {
        loadSysProperty();
        loadApiProperty();
        loadUserTypeProperty();
        loadRoleProperty();
    }


    /**
     * 加载系统配置
     *
     * @throws Exception
     */
    public static void loadSysProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.SYS.getValue());
        for (TSysProperty tSysProperty : sysPropertyList) {
            refreshPropery(tSysProperty);
        }
    }

    /**
     * 加载API配置
     *
     * @throws Exception
     */
    public static void loadApiProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.API.getValue());
        for (TSysProperty tSysProperty : sysPropertyList) {
            refreshPropery(tSysProperty);
        }
    }

    /**
     * 加载用户类型配置
     * @throws Exception
     */
    public static void loadUserTypeProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.USER_TYPE.getValue());
        for (TSysProperty tSysProperty : sysPropertyList) {
            refreshPropery(tSysProperty);
        }
    }

    /**
     * 加载角色配置
     * @throws Exception
     */
    public static void loadRoleProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.ROLE.getValue());
        for (TSysProperty tSysProperty : sysPropertyList) {
            refreshPropery(tSysProperty);
        }
    }

    /**
     * 刷新配置缓存
     * @param tSysProperty
     */
    public static void refreshPropery(TSysProperty tSysProperty) {
        redisTemplate.boundHashOps(PROPERTY_KEY).put(tSysProperty.getType() + tSysProperty.getPropertyName(), tSysProperty);
    }

    /**
     * 获取全部配置项
     * @return
     */
    public static List<TSysProperty> getProperty() {
        return (List) redisTemplate.boundHashOps(TSysProperty.class.getName()).values();
    }

    /**
     * 根据ID获取系统配置
     * @param id
     * @return
     */
    public static TSysProperty getProperty(Integer id) {
        List tSysPropertyList = redisTemplate.boundHashOps(TSysProperty.class.getName()).values();
        Iterator iterator = tSysPropertyList.iterator();
        while (iterator.hasNext()) {
            TSysProperty tmp = (TSysProperty) iterator.next();
            if (!id.equals(tmp.getId())) {
                iterator.remove();
            }
        }

        if(tSysPropertyList.isEmpty()) {
            return null;
        }

        return (TSysProperty) tSysPropertyList.get(0);
    }

    /**
     * 根据主键获取系统配置
     *
     * @param type
     * @param key
     * @return
     */
    public static TSysProperty getProperty(SysPropertyTypeEnum type, String key) {
        TSysProperty tSysProperty = (TSysProperty) redisTemplate.boundHashOps(TSysProperty.class.getName()).get(type.getValue() + key);
        return tSysProperty;
    }

    /**
     * 根据类型获取系统配置
     *
     * @param type
     * @return
     */
    public static List<TSysProperty> getProperty(SysPropertyTypeEnum type) {
        List tSysPropertyList = redisTemplate.boundHashOps(TSysProperty.class.getName()).values();
        Iterator iterator = tSysPropertyList.iterator();
        while (iterator.hasNext()) {
            TSysProperty tmp = (TSysProperty) iterator.next();
            if (!type.getValue().equals(tmp.getType())) {
                iterator.remove();
            }
        }

        return tSysPropertyList;
    }

    /**
     * 根据模糊主键
     *
     * @param keyLike
     * @return
     */
    public static List<TSysProperty> getProperty(String keyLike) {
        List tSysPropertyList = redisTemplate.boundHashOps(TSysProperty.class.getName()).values();
        Iterator iterator = tSysPropertyList.iterator();
        while (iterator.hasNext()) {
            TSysProperty tmp = (TSysProperty) iterator.next();
            if (tmp.getPropertyName().indexOf(keyLike) == -1) {
                iterator.remove();
            }
        }

        return tSysPropertyList;
    }

    /**
     * 从缓存移除配置项
     * @param id
     */
    public static void removeProperty(Integer id) {
        TSysProperty tSysProperty = getProperty(id);
        if(null != tSysProperty) {
            redisTemplate.boundHashOps(TSysProperty.class.getName()).delete(tSysProperty.getType() + tSysProperty.getPropertyName());
        }
    }
}