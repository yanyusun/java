package com.dqys.flowbusiness.service.constant.saleBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetOpenBusiness.*;

/**
 * 资产发布业务
 * Created by yan on 16-12-22.
 */
public class AssetBusiness {

    /**
     * 业务类型
     */
    public static int type = BusinessTypeEnum.asset_announce.getValue();

    /**
     * 用户待发布
     * @return
     */
    public static BeAnnouncedLevel getBeAnnounced() {
        return new BeAnnouncedLevel();
    }

    /**
     * 待审核
     * @return
     */
    public static CheckLevel getCheckLevel() {return new CheckLevel();}

    /**
     * 驳回
     * @return
     */
    public static HasRejectLevel getReject() {
        return new HasRejectLevel();
    }

    /**
     * 平台待发布
     * @return
     */
    public static BeAnnouncedAdmin getBeAnnouncedAdmin() {
        return new BeAnnouncedAdmin();
    }

    /**
     * 已发布
     * @return
     */
    public static HasAnnouncedLevel getHasAnnouncedLevel() {
        return new HasAnnouncedLevel();
    }

    /**
     * 下架
     * @return
     */
    public static HasUnderLine getHasUnderLine() {
        return new HasUnderLine();
    }

    /**
     * 无效
     * @return
     */
    public static UnableLevel getUnable() {
        return new UnableLevel();
    }


}
