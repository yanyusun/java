package com.dqys.business.service.utils.permission;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.asset.impl.AssetInfoMapperImpl;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.operType.OperType;
import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.core.constant.RoleTypeEnum;

import java.util.List;

/**
 * 对管理员与录入人员
 * 额外添加操作编辑按钮的选项
 * Created by yan on 16-11-11.
 */
public class AddEditOperTypeFilter extends OperTypeFilter{
    private LenderInfoMapper lenderInfoMapper;
    private AssetInfoMapperImpl assetInfoMapper;
    private Integer objectType;
    private Integer objectId;
    private int userType;
    private int useId;

    public AddEditOperTypeFilter(LenderInfoMapper lenderInfoMapper, AssetInfoMapperImpl assetInfoMapper, Integer objectType, Integer objectId, int userType, int useId) {
        this.lenderInfoMapper = lenderInfoMapper;
        this.assetInfoMapper = assetInfoMapper;
        this.objectType = objectType;
        this.objectId = objectId;
        this.userType = userType;
        this.useId = useId;
    }

    @Override
    public List<OperType> getPermission(List<OperType> list) {
        ObjectTypeEnum objectTypeEnum=ObjectTypeEnum.getObjectTypeEnum(objectType);
        if(RoleTypeEnum.ADMIN.getValue()==userType){//如果是管理员
            switch (objectTypeEnum){
                case ASSETPACKAGE:
                    addEditOperType(list, AssetPackageEnum.update.getValue(), AssetPackageEnum.update.getName());
                    break;
                case LENDER:
                    addEditOperType(list, LenderEnum.UPDATE_EDIT.getValue(), LenderEnum.UPDATE_EDIT.getName());
                    break;
            }
        }else{//如果是录入人
            switch (objectTypeEnum){
                case ASSETPACKAGE:
                    AssetInfo assetInfo=assetInfoMapper.get(objectId);
                    if(assetInfo.getOperator()==useId){
                        addEditOperType(list, AssetPackageEnum.update.getValue(), AssetPackageEnum.update.getName());
                    }
                    break;
                case LENDER:
                    LenderInfo lenderInfo=lenderInfoMapper.get(objectId);
                    if(lenderInfo.getOperator()==useId){
                        addEditOperType(list, LenderEnum.UPDATE_EDIT.getValue(), LenderEnum.UPDATE_EDIT.getName());
                    }
                    break;
            }
        }
        operTypes=list;
        return getNextPermission();
    }

    private void addEditOperType(List<OperType> list, Integer value, String name) {
        OperType operType = new OperType();
        operType.setOperType(value);
        operType.setOperName(name);
        list.add(operType);
    }


}
