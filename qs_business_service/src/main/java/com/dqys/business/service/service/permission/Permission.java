package com.dqys.business.service.service.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pan on 16-10-1.
 */
public interface Permission {
    public List<OperType> getUserObjectNavidOperType( Integer objectType, Integer objectId, Integer navId);
}
