package com.dqys.flowbusiness.service.service;

import com.dqys.flowbusiness.orm.pojo.Business;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * Created by yan on 16-12-22.
 */
@Repository
@Primary
public interface BusinessService {
    /**
     *  得到业务表信息
     * @param id 对象信息
     * @return
     */
    Business getBusiness(Integer id);
}
