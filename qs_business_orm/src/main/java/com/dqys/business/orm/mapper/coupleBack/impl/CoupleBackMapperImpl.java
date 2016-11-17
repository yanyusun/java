package com.dqys.business.orm.mapper.coupleBack.impl;

import com.dqys.business.orm.mapper.coupleBack.CoupleBackMapper;
import com.dqys.business.orm.pojo.coupleBack.CoupleBack;
import com.dqys.business.orm.pojo.coupleBack.CoupleBackMessage;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackDTO;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackMessageDTO;
import com.dqys.core.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkfeng on 2016/11/16.
 */
@Repository
public class CoupleBackMapperImpl extends BaseDao implements CoupleBackMapper {

    @Override
    public Integer addCoupleBack(CoupleBack coupleBack) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).addCoupleBack(coupleBack);
    }

    @Override
    public Integer delCoupleBack(Integer id) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).delCoupleBack(id);
    }

    @Override
    public Integer addMessage(CoupleBackMessage message) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).addMessage(message);
    }

    @Override
    public Integer delMessage(Integer id) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).delMessage(id);
    }

    @Override
    public List<CoupleBackDTO> selectBackByDTO(CoupleBackDTO coupleBackDTO) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).selectBackByDTO(coupleBackDTO);
    }

    @Override
    public List<CoupleBackMessageDTO> listMessage(Integer tcbId) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).listMessage(tcbId);
    }

    @Override
    public CoupleBack get(Integer tcbId) {
        return super.getSqlSession().getMapper(CoupleBackMapper.class).get(tcbId);
    }


}
