package com.dqys.auth.orm.dao.impl.asset;

import com.dqys.auth.orm.dao.facade.asset.PawnInfoMapper;
import com.dqys.auth.orm.pojo.asset.PawnInfo;
import com.dqys.auth.orm.query.asset.PawnQuery;
import com.dqys.core.base.BaseDao;
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
}
