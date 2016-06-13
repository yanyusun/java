package com.dqys.auth.orm.dao.impl.asset;

import com.dqys.auth.orm.dao.facade.asset.IOUInfoMapper;
import com.dqys.auth.orm.pojo.asset.IOUInfo;
import com.dqys.auth.orm.query.asset.IOUQuery;
import com.dqys.core.base.BaseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yvan on 16/6/8.
 */
@Repository
@Primary
public class IOUInfoMapperImpl extends BaseDao implements IOUInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(IOUInfo record) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).insert(record);
    }

    @Override
    public IOUInfo get(Integer id) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).get(id);
    }

    @Override
    public Integer update(IOUInfo record) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).update(record);
    }

    @Override
    public Integer count() {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).count();
    }

    @Override
    public List<IOUInfo> listByLenderId(Integer lenderId) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).listByLenderId(lenderId);
    }

    @Override
    public List<IOUInfo> queryList(IOUQuery iouQuery) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).queryList(iouQuery);
    }
}
