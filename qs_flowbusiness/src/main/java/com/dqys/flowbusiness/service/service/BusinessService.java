package com.dqys.flowbusiness.service.service;

import com.dqys.flowbusiness.orm.pojo.Business;
import com.dqys.flowbusiness.service.dto.BusinessDto;
import com.dqys.flowbusiness.service.util.Result;

import java.util.List;

/**
 * Created by yan on 16-12-22.
 */
public interface BusinessService {
    /**
     *  得到业务表信息
     * @param id 对象信息
     * @return
     */
    Business getBusiness(Integer id);

    /**
     * 创建多个关联的业务
     * @param businessDtoList
     * @param userId
     * @param businessType
     * @param BusinessStatus
     * @return 业务id
     */
    int createBusiness_tx(List<BusinessDto> businessDtoList,Integer userId,Integer businessType,Integer BusinessStatus);

    /**
     * 创建单一对象的业务
     * @param businessDto 业务对象
     * @param userId 用户id
     * @param businessType 业务类型
     * @param BusinessStatus 业务状态
     * @return 业务id
     */
    int createBusiness_tx(BusinessDto businessDto,Integer userId,Integer businessType,Integer BusinessStatus);

    // TODO: 16-12-27  加缓存处理
    /**
     * 加事物控制!!!!
     * 业务控制
     * @param businessId　业务id
     * @param userId 用户id
     * @param businessType 业务类型
     * @param businessLevel 业务阶段
     * @param operType 操作类型
     * @return
     */
    Result flow_tx(Integer businessId,Integer userId,Integer businessType,Integer businessLevel, Integer operType);
}
