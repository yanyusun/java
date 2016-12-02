package com.dqys.business.service.service.flowBusiness;

import com.dqys.business.orm.pojo.flowBusiness.FlowBusiness;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mkfeng on 2016/12/2.
 */
public interface FlowBusinessService {

    FlowBusiness get(Integer id);

    List<FlowBusiness> selectByCondition(FlowBusiness flowBusiness);

    Integer add(FlowBusiness flowBusiness);

    Integer updateById(FlowBusiness flowBusiness);
}
