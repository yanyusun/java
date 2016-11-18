package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.mapper.zcy.ZcyEstatesMapper;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.orm.pojo.zcy.ZcyEstates;
import com.dqys.business.service.constant.ObjectEnum.AssetSourceEnum;
import com.dqys.core.constant.RoleTypeEnum;

import java.util.List;


/**
 * Created by pan on 16-11-17.
 */
public class AddAssetSourceEditOperTypeFilter extends  OperTypeFilter {
    private Integer objectId;
    private int userType;
    private int useId;
    private ZcyEstatesMapper zcyEstatesMapper;

    public AddAssetSourceEditOperTypeFilter(Integer objectId, int userType, int useId, ZcyEstatesMapper zcyEstatesMapper) {
        this.objectId = objectId;
        this.userType = userType;
        this.useId = useId;
        this.zcyEstatesMapper = zcyEstatesMapper;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        if(RoleTypeEnum.ADMIN.getValue()==userType){//如果是管理员
            PermissionUtil.addEditOperType(list, AssetSourceEnum.UPDATE_EDIT.getValue(),AssetSourceEnum.UPDATE_EDIT.getName());
        }else{//如果是录入人
          ZcyEstates zcyEstates=zcyEstatesMapper.selectByPrimaryKey(objectId);
            if(Integer.parseInt(zcyEstates.getOperator())==useId){
                PermissionUtil.addEditOperType(list, AssetSourceEnum.UPDATE_EDIT.getValue(),AssetSourceEnum.UPDATE_EDIT.getName());
            }

        }
        operTypes=list;
        return getNextPermission();
    }

}
