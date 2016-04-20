package com.dqys.core.mapper.facade;


import com.dqys.core.model.TSysProperty;

import java.util.List;

/**
 * @author by pan on 16-3-15.
 */
public interface TSysPropertyMapper {

    List<TSysProperty> selectAll() throws Exception;

    /**
     * 根据配置类型加载
     *
     * @param type
     * @return
     */
    List<TSysProperty> selectByType(Integer type);

    int insertSelective(TSysProperty tSysProperty);

    int updateByPrimaryKeySelective(TSysProperty tSysProperty);

    int delete(Integer id);

    TSysProperty selectByPromaryKey(Integer id);
}
