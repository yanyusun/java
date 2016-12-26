package com.dqys.flowbusiness.service.constant.saleBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness.BeAnnouncedLevel;

/**
 * 资产处置业务
 * Created by pan on 16-12-26.
 */
public class AssetDisposeBusiness {
    private static int type = BusinessTypeEnum.asset_dispose.getValue();
    /**
     * 用户待发布
     * @return
     */
    public static BeAnnouncedLevel getBeAnnounced() {
        return new BeAnnouncedLevel();
    }


}
