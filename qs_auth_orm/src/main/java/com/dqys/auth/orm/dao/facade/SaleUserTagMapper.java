package com.dqys.auth.orm.dao.facade;


import com.dqys.auth.orm.pojo.saleUser.SaleUserTag;

public interface SaleUserTagMapper extends BaseMapper<SaleUserTag> {
    /**
     * 根据用户id获取详细信息
     *
     * @param uid
     * @return
     */
    SaleUserTag selectTagByUserId(Integer uid);
}