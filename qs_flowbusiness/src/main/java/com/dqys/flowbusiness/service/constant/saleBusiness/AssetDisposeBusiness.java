package com.dqys.flowbusiness.service.constant.saleBusiness;

import com.dqys.flowbusiness.service.constant.saleBusiness.businessLevel.AssetDisposeBusiness.*;

/**
 * 资产处置业务
 * Created by pan on 16-12-26.
 */
public class AssetDisposeBusiness {
    // TODO: 16-12-28 改成单列


    public static int type = BusinessTypeEnum.asset_dispose.getValue();
    /**
     * 带审核
     * @return
     */
    public static CheckLevel getCheckLevel() {
        return new CheckLevel();
    }
    /**
     * 正在处置
     */
    public static OnDisposeLevel getOnDisposeLevel(){
        return new OnDisposeLevel();
    }
    /**
     * 已驳回
     */
    public static HasRejectLevel getHasRejectLevel(){
        return new HasRejectLevel();
    }
    /**
     *已处置
     */
    public static HasDisposeLevel getHasDisposeLevel(){
        return new HasDisposeLevel();
    }

    /**
     * 已取消
     * @return
     */
    public static HasCancel getHasCancel(){
        return new HasCancel();
    }

    /**
     * 无效
     * @return
     */
    public static UnableLevel getUnableLevel(){
        return new UnableLevel();
    }

}
