package com.dqys.business.service.utils.permission;

/**
 * Created by yan on 16-10-1.
 */
public interface PermissionInterFace {
    void decorate(PermissionInterFace permission);
    Object getPermission();
}
