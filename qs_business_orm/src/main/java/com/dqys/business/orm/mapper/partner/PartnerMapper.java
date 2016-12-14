package com.dqys.business.orm.mapper.partner;

import com.dqys.business.orm.pojo.partner.Partner;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mkfeng on 2016/12/14.
 */
public interface PartnerMapper {

    Integer insertByCondition(Partner partner);

    Integer updateByRelationStatusAndRemark(@Param("id") Integer id, @Param("relationStatus") Integer relationStatus, @Param("remark") Integer remark);

    Partner get(Integer id);
}
