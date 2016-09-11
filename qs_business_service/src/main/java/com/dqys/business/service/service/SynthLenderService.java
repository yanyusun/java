package com.dqys.business.service.service;

import java.util.Map;

/**
 * Created by mkfeng on 2016/9/9.
 */
public interface SynthLenderService {
    /**
     * 根据借款人id获取信息，下面的抵押物和借据
     *
     * @param lenderId
     * @return
     */
    Map pawnList(Integer lenderId);

}
