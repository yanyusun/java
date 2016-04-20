package com.dqys.core.mapper.impl;

import com.dqys.core.base.BaseDao;
import com.dqys.core.mapper.facade.TAreaMapper;
import com.dqys.core.model.TArea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by pan on 16-4-7.
 */
@Repository
public class TAreaMapperImpl  extends BaseDao implements TAreaMapper {

    @Override
    public List<TArea> selectByUpper(Integer upper) {
        return super.getSqlSession().getMapper(TAreaMapper.class).selectByUpper(upper);
    }
}
