package com.dqys.sale.orm.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by yan on 16-12-19.
 */
public class BaseDao extends SqlSessionDaoSupport implements Serializable {
    @Resource(name="sqlSessionFactorySale")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
