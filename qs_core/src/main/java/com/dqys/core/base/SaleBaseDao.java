package com.dqys.core.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by pan on 16-12-21.
 */
public class SaleBaseDao extends SqlSessionDaoSupport implements Serializable {
    @Resource(name = "sqlSessionFactorySale")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
