package com.dqys.sale.orm.mapper.business;


import com.dqys.sale.orm.mapper.BaseMapper;
import com.dqys.sale.orm.pojo.BusinessORelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessORelationMapper extends BaseMapper<BusinessORelation> {

    List<Integer> selectObjectIdByObjectType(@Param("objectType") Integer objectType, @Param("status") Integer... status);

}