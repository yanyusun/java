package com.dqys.business.controller.util.asset;

import com.dqys.business.controller.dto.asset.AssetDTO;
import com.dqys.business.controller.dto.asset.ContactDTO;
import com.dqys.business.controller.util.CommonUtil;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/6/16.
 */
public class AssetControllerUtils {
    /**
     * 资产包DTO量化转换DAO
     * @param assetDTOList
     * @return
     */
    public List<AssetInfo> toAssetInfo(List<AssetDTO> assetDTOList){
        if(CommonUtil.checkParam(assetDTOList)){
            return null;
        }
        List<AssetInfo> assetInfoList = new ArrayList<>();
        assetDTOList.forEach(assetDTO -> {
            assetInfoList.add(toAssetInfo(assetDTO));
        });
        return assetInfoList;
    }

    /**
     * 将资产包DTO转换成DAO
     * @param assetDTO
     * @return
     */
    public static AssetInfo toAssetInfo(AssetDTO assetDTO){
        AssetInfo assetInfo = new AssetInfo();

        assetInfo.setId(assetDTO.getId());

        return assetInfo;
    }

    /**
     * 量化转换联系人DTO为DAO
     * @param contactDTOList
     * @return
     */
    public static List<ContactInfo> toContactInfo(List<ContactDTO> contactDTOList){
        if(CommonUtil.checkParam(contactDTOList)){
            return null;
        }
        List<ContactInfo> contactInfoList = new ArrayList<>();
        contactDTOList.forEach(contactDTO -> {
            contactInfoList.add(toContactInfo(contactDTO));
        });
        return contactInfoList;
    }

    public static ContactInfo toContactInfo(ContactDTO contactDTO){
        ContactInfo contactInfo = new ContactInfo();

        contactInfo.setCode(contactDTO.getCode());

        return contactInfo;
    }

}
