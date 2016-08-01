package com.dqys.core.utils;

import com.dqys.core.base.BasePropertyDTO;
import com.dqys.core.constant.SysPropertyTypeEnum;
import com.dqys.core.mapper.facade.TSysPropertyMapper;
import com.dqys.core.model.TSysProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        if(redisTemplate == null){
            this.initProperty();
        }
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
     * 刷新配置缓存
     * @param sysPropertyList
     */
    public static void refreshPropery(List<TSysProperty> sysPropertyList) {
        for (TSysProperty tSysProperty : sysPropertyList) {
            refreshPropery(tSysProperty);
        }
    }
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
     * 根据类型/模糊主键/值
     * @param type
     * @param keyLike
     * @param value
     * @return
     */
    public static List<TSysProperty> getProperty(SysPropertyTypeEnum type, String keyLike, String value) {
        List tSysPropertyList = getProperty(type);
        Iterator iterator = tSysPropertyList.iterator();
        while (iterator.hasNext()) {
            TSysProperty tmp = (TSysProperty) iterator.next();
            if(StringUtils.isNotBlank(keyLike) && tmp.getPropertyName().indexOf(keyLike) == -1) {
                iterator.remove();
            }
            if (StringUtils.isNotBlank(value) && !tmp.getPropertyValue().equals(value)) {
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



    /* 初始化系统配置 */
    public static void initProperty() {
        try {
            loadAllProperty();
            //AreaTool.loadArea();  // FIXME: 16-5-26 测试环境不加载
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        loadFileBusinessTypeProperty();
    }


    /**
     * 加载系统配置
     *
     * @throws Exception
     */
    public static void loadSysProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.SYS.getValue());
        refreshPropery(sysPropertyList);
    }

    /**
     * 加载API配置
     *
     * @throws Exception
     */
    public static void loadApiProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.API.getValue());
        refreshPropery(sysPropertyList);
    }

    /**
     * 加载用户类型配置
     * @throws Exception
     */
    public static void loadUserTypeProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.USER_TYPE.getValue());
        refreshPropery(sysPropertyList);
    }

    /**
     * 加载角色配置
     * @throws Exception
     */
    public static void loadRoleProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.ROLE.getValue());
        refreshPropery(sysPropertyList);
    }

    /**
     * 加载文件业务类型配置
     * @throws Exception
     */
    public static void loadFileBusinessTypeProperty() throws Exception {
        List<TSysProperty> sysPropertyList = tSysPropertyMapper.selectByType(SysPropertyTypeEnum.FILE_BUSINESS_TYPE.getValue());
        refreshPropery(sysPropertyList);
    }

    public static List<BasePropertyDTO> toPropertyDTO(List<TSysProperty> sysPropertieList){
        List<BasePropertyDTO> propertyDTOList = new ArrayList<>();
        sysPropertieList.forEach(sysProperty -> {
            propertyDTOList.add(toPropertyDTO(sysProperty));
        });
        return propertyDTOList;
    }

    public static BasePropertyDTO toPropertyDTO(TSysProperty sysProperty){
        BasePropertyDTO propertyDTO = new BasePropertyDTO();

        propertyDTO.setId(sysProperty.getId());
        propertyDTO.setCreateAt(sysProperty.getCreateAt());
        propertyDTO.setStatus(sysProperty.getStateflag().intValue());
        propertyDTO.setName(sysProperty.getRemark());
        propertyDTO.setValue(sysProperty.getPropertyValue());

        return propertyDTO;
    }
}
