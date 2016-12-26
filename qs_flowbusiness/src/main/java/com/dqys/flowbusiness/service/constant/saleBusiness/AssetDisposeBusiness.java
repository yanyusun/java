package com.dqys.flowbusiness.service.constant.saleBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness.CheckLevel;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness.OnDisposeLevel;
import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness.TenderLevel;

/**
 * 资产处置业务
 * Created by pan on 16-12-26.
 */
public class AssetDisposeBusiness {
    private static int type = BusinessTypeEnum.asset_dispose.getValue();
    /**
     * 招标中
     * @return
     */
    public static TenderLevel getTenderLevel() {
        return new TenderLevel();
    }

    /**
     * 待审核
     * @return
     */
    public static CheckLevel getCheckLevel(){return new CheckLevel();}

    /**
     *
     * @return
     */
    public static OnDisposeLevel getOnDisposeLevel(){return new OnDisposeLevel();}

}
