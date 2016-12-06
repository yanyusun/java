package com.dqys.business.orm.mapper.flowBusiness.impl;

import com.dqys.business.orm.mapper.flowBusiness.FlowBusinessMapper;
import com.dqys.business.orm.pojo.flowBusiness.FlowBusiness;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/2.
 */
@Repository
public class FlowBusinessMapperImpl extends BaseDao implements FlowBusinessMapper {

    @Override
    public FlowBusiness get(@RequestParam("id") Integer id) {
        return super.getSqlSession().getMapper(FlowBusinessMapper.class).get(id);
    }

    @Override
    public List<FlowBusiness> selectByCondition(@RequestParam("flowBusiness") FlowBusiness flowBusiness) {
        return super.getSqlSession().getMapper(FlowBusinessMapper.class).selectByCondition(flowBusiness);
    }

    @Override
    public Integer add(@RequestParam("flowBusiness") FlowBusiness flowBusiness) {
        return super.getSqlSession().getMapper(FlowBusinessMapper.class).add(flowBusiness);
    }

    @Override
    public Integer updateById(@RequestParam("flowBusiness") FlowBusiness flowBusiness) {
        return super.getSqlSession().getMapper(FlowBusinessMapper.class).updateById(flowBusiness);
    }
}
