package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.query.asset.IOUQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
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
    public List<IOUInfo> queryList(IOUQuery IOUQuery) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).queryList(IOUQuery);
    }

    @Override
    public Integer queryCount(IOUQuery iouQuery) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).queryCount(iouQuery);
    }

    @Override
    public List<IOUInfo> selectIouInfoByPawnId(Integer objectId) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).selectIouInfoByPawnId(objectId);
    }

    @Override
    public List<IOUInfo> listByName(@Param("lenderId") Integer id, @Param("name") String name) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).listByName(id, name);
    }

    @Override
    public List<IOUInfo> iouListByLenderId(Integer lenderId, Integer userId, Integer objectType, Integer userType) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).iouListByLenderId(lenderId, userId, objectType, userType);
    }

    @Override
    public List<IOUInfo> selectIouInfoByObjectIds(List<Integer> iouIds) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).selectIouInfoByObjectIds(iouIds);
    }

    @Override
    public List<IOUInfo> findByPawnId(Integer pawnId) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).findByPawnId(pawnId);
    }

    @Override
    public IOUInfo getIouBySumMoney(Integer lenderId) {
        return super.getSqlSession().getMapper(IOUInfoMapper.class).getIouBySumMoney(lenderId);
    }

}
