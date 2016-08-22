package com.dqys.business.orm.mapper.asset.impl;

import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.query.asset.PawnQuery;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
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
public class PawnInfoMapperImpl extends BaseDao implements PawnInfoMapper {
    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).deleteByPrimaryKey(id);
    }

    @Override
    public Integer insert(PawnInfo record) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).insert(record);
    }

    @Override
    public PawnInfo get(Integer id) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).get(id);
    }

    @Override
    public Integer update(PawnInfo record) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).update(record);
    }

    @Override
    public Integer count() {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).count();
    }

    @Override
    public List<PawnInfo> listByLenderId(Integer lenderId) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).listByLenderId(lenderId);
    }

    @Override
    public List<PawnInfo> queryList(PawnQuery pawnQuery) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).queryList(pawnQuery);
    }

    @Override
    public Integer queryCount(PawnQuery pawnQuery) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).queryCount(pawnQuery);
    }

    @Override
    public List<PawnInfo> pawnListPage(@Param("zcyListQuery") ZcyListQuery zcyListQuery) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).pawnListPage(zcyListQuery);
    }

    @Override
    public Integer pawnListPageCount(@Param("zcyListQuery") ZcyListQuery zcyListQuery) {
        return super.getSqlSession().getMapper(PawnInfoMapper.class).pawnListPageCount(zcyListQuery);
    }

}

