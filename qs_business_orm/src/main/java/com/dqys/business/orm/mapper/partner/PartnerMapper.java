package com.dqys.business.orm.mapper.partner;

import com.dqys.business.orm.pojo.partner.Partner;
import org.apache.ibatis.annotations.Param;

/**
 * Created by mkfeng on 2016/12/14.
 */
public interface PartnerMapper {
    //添加信息
    Integer insertByCondition(Partner partner);

    /**
     * 根据id修改状态或备注
     *
     * @param id
     * @param relationStatus
     * @param remark
     * @return
     */
    Integer updateByRelationStatusAndRemark(@Param("id") Integer id, @Param("relationStatus") Integer relationStatus, @Param("remark") Integer remark);

    //获取对象信息
    Partner get(Integer id);

    /**
     * 修改双方状态，为了同步双方的关系
     *
     * @param relationStatus
     * @param userId
     * @param partnerUserId
     * @return
     */
    Integer updateRSByUIAndPUI(@Param("relationStatus") Integer relationStatus, @Param("userId") Integer userId, @Param("partnerUserId") Integer partnerUserId);
}
