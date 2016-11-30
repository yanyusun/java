package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.utils.operType.OperTypeNavBean;
import com.dqys.business.service.utils.operType.OperTypeUtile;

import java.util.List;

/**
 * 特定导航栏权限控制
 * Created by yan on 16-10-1.
 */
public class NavIdOperTypeFilter extends OperTypeFilter {
    //// TODO: 16-10-2 后期改成从数据库中得到


    private Integer navId;

    private Integer objectType;

    private int position;

    public NavIdOperTypeFilter(Integer navId, Integer objectType, int position) {
        this.navId = navId;
        this.objectType = objectType;
        this.position = position;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        List<OperType> operTypes2 = null;
        if (OperTypeNavBean.LENDER_ROW == position) {
            operTypes2 = OperTypeUtile.getLenderRowOperType(navId);
        } else {
            operTypes2 = OperTypeUtile.getOperType(navId, objectType);
        }
        PermissionUtil.intersection(list, operTypes2, operTypes);
        return getNextPermission();
    }

}
