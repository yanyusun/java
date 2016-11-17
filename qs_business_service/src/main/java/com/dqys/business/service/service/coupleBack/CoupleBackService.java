package com.dqys.business.service.service.coupleBack;

import com.dqys.business.orm.pojo.coupleBack.CoupleBack;
import com.dqys.business.orm.pojo.coupleBack.CoupleBackMessage;
import com.dqys.business.orm.pojo.coupleBack.dto.CoupleBackDTO;

import java.util.Map;

/**
 * Created by mkfeng on 2016/11/16.
 */
public interface CoupleBackService {
    /**
     * 添加反馈消息
     *
     * @param coupleBack
     * @return
     */
    Map addCoupleBack(CoupleBack coupleBack);

    /**
     * 删除反馈消息
     *
     * @param id
     * @return
     */
    Map delCoupleBack(Integer id);

    Map addMessage(CoupleBackMessage message);

    Map delMessage(Integer id);

    Map listBack(CoupleBackDTO coupleBackDTO);

    Map listMessage(Integer tcbId);
}
