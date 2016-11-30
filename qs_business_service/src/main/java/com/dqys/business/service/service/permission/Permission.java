package com.dqys.business.service.service.permission;

import com.dqys.business.orm.pojo.operType.OperType;

import java.util.List;

/**
 * Created by yan on 16-10-1.
 */
public interface Permission {
   List<OperType> getOperTypes( Integer objectType, Integer objectId, Integer navId,int position);

   boolean hasRepayButton(Integer lenderId);
}
