package com.dqys.business.orm.mapper.partner.impl;

import com.dqys.auth.orm.query.CompanyQuery;
import com.dqys.business.orm.mapper.part.ParticipationMapper;
import com.dqys.business.orm.mapper.partner.PartnerMapper;
import com.dqys.business.orm.pojo.partner.Partner;
import com.dqys.business.orm.pojo.partner.PartnerDTO;
import com.dqys.business.orm.pojo.partner.PartnerQuery;
import com.dqys.core.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */
@Repository
public class PartnerMapperImpl extends BaseDao implements PartnerMapper {

    @Override
    public Integer insertByCondition(Partner partner) {
        return super.getSqlSession().getMapper(PartnerMapper.class).insertByCondition(partner);
    }

    @Override
    public Integer updateByRelationStatusAndRemark(@Param("id") Integer id, @Param("relationStatus") Integer relationStatus, @Param("remark") Integer remark) {
        return super.getSqlSession().getMapper(PartnerMapper.class).updateByRelationStatusAndRemark(id, relationStatus, remark);
    }

    @Override
    public Partner get(Integer id) {
        return super.getSqlSession().getMapper(PartnerMapper.class).get(id);
    }

    @Override
    public Integer updateRSByUIAndPUI(@Param("relationStatus") Integer relationStatus, @Param("userId") Integer userId, @Param("partnerUserId") Integer partnerUserId) {
        return super.getSqlSession().getMapper(PartnerMapper.class).updateRSByUIAndPUI(relationStatus, userId, partnerUserId);
    }

    @Override
    public List<PartnerDTO> partnerList(PartnerQuery query) {
        return super.getSqlSession().getMapper(PartnerMapper.class).partnerList(query);
    }
}
