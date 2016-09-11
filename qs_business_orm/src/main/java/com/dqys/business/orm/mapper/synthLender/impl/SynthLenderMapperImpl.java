package com.dqys.business.orm.mapper.synthLender.impl;

import com.dqys.business.orm.mapper.synthLender.SynthLenderMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/9/9.
 */
@Repository
public class SynthLenderMapperImpl extends BaseDao implements SynthLenderMapper {


    @Override
    public Map getRealNameByOURelation(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId, @Param("status") Integer status, @Param("typeTTR") Integer typeTTR, @Param("typeOUR") Integer typeOUR) {
        return super.getSqlSession().getMapper(SynthLenderMapper.class).getRealNameByOURelation(objectType, objectId, status, typeTTR, typeOUR);
    }
}
