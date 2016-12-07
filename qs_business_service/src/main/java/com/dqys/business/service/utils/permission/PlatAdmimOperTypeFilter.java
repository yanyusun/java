package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;
import com.dqys.business.service.constant.ObjectEnum.AssetSourceEnum;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;

import java.util.List;

/**
 * Created by pan on 16-12-7.
 */
public class PlatAdmimOperTypeFilter extends OperTypeFilter {
    private Integer objectType;

    public PlatAdmimOperTypeFilter(Integer objectType) {
        this.objectType = objectType;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {

        ObjectTypeEnum e=ObjectTypeEnum.getObjectTypeEnum(objectType);
        switch (e){
            case LENDER:
                boolean addLenderUPDATE_EDIT = true;
                for (OperType operType : list) {
                    if (operType.getOperType() == LenderEnum.UPDATE_EDIT.getValue().intValue()) {
                        addLenderUPDATE_EDIT = false;
                        break;
                    }
                }
                if(addLenderUPDATE_EDIT){
                    PermissionUtil.addEditOperType(list,LenderEnum.UPDATE_EDIT.getValue(),LenderEnum.UPDATE_EDIT.getName());
                }
                break;
            case ASSETPACKAGE:
                boolean addassetpackageUPDATE_EDIT = true;
                for (OperType operType : list) {
                    if (operType.getOperType() == AssetPackageEnum.update.getValue()) {
                        addassetpackageUPDATE_EDIT = false;
                        break;
                    }
                }
                if(addassetpackageUPDATE_EDIT){
                    PermissionUtil.addEditOperType(list,AssetPackageEnum.update.getValue(),AssetPackageEnum.update.getName());
                }
                break;
            case ASSETSOURCE:
                boolean addassetsourceUPDATE_EDIT = true;
                for (OperType operType : list) {
                    if (operType.getOperType() == AssetSourceEnum.UPDATE_EDIT.getValue().intValue()) {
                        addassetsourceUPDATE_EDIT = false;
                        break;
                    }
                }
                if(addassetsourceUPDATE_EDIT){
                    PermissionUtil.addEditOperType(list,LenderEnum.UPDATE_EDIT.getValue(),LenderEnum.UPDATE_EDIT.getName());
                }
                break;
        }
        operTypes = list;
        return getNextPermission();
    }
}
