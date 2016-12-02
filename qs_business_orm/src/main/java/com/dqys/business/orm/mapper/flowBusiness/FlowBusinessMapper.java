package com.dqys.business.orm.mapper.flowBusiness;

import com.dqys.business.orm.pojo.flowBusiness.FlowBusiness;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/2.
 */
public interface FlowBusinessMapper {

    FlowBusiness get(@RequestParam("id") Integer id);

    List<FlowBusiness> selectByCondition(@RequestParam("flowBusiness") FlowBusiness flowBusiness);

    Integer add(@RequestParam("flowBusiness") FlowBusiness flowBusiness);

    Integer updateById(@RequestParam("flowBusiness") FlowBusiness flowBusiness);

}
