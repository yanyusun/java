package com.dqys.business.orm.mapper.coupleBack;

import com.dqys.business.orm.pojo.coupleBack.CoupleBack;
import com.dqys.business.orm.pojo.coupleBack.CoupleBackMessage;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackDTO;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackMessageDTO;

import java.util.List;

/**
 * Created by mkfeng on 2016/11/16.
 */
public interface CoupleBackMapper {
    /**
     * t_couple_back表添加数据
     *
     * @param coupleBack
     * @return
     */
    Integer addCoupleBack(CoupleBack coupleBack);

    /**
     * t_couple_back表删除数据（伪删除）
     *
     * @param id
     * @return
     */
    Integer delCoupleBack(Integer id);

    /**
     * t_couple_back_message 表添加数据
     *
     * @param message
     * @return
     */
    Integer addMessage(CoupleBackMessage message);

    /**
     * t_couple_back_message 表删除数据（伪删除）
     *
     * @param id
     * @return
     */
    Integer delMessage(Integer id);

    List<CoupleBackDTO> selectBackByDTO(CoupleBackDTO coupleBackDTO);

    List<CoupleBackMessageDTO> listMessage(Integer tcbId);

    CoupleBack get(Integer tcbId);

    Integer selectBackByDTOCount(CoupleBackDTO coupleBackDTO);
}
