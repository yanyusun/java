package com.dqys.core.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author by pan on 16-3-15.
 */
public abstract class BaseDao extends SqlSessionDaoSupport implements Serializable {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
