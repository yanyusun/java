package com.dqys.business.orm.mapper.zcy;


import com.dqys.business.orm.pojo.zcy.ZcyOwnerContacts;

import java.util.List;

public interface ZcyOwnerContactsMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(ZcyOwnerContacts record);

    Integer insertSelective(ZcyOwnerContacts record);

    ZcyOwnerContacts selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(ZcyOwnerContacts record);

    Integer updateByPrimaryKey(ZcyOwnerContacts record);

    List<ZcyOwnerContacts> selectBySelective(ZcyOwnerContacts record);
}