package com.dqys.business.orm.mapper.synthLender;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/9/9.
 */
public interface SynthLenderMapper {

    /**
     *查询处置借款人的所属人姓名
     * @param objectType
     * @param objectId
     * @param status t_teammate_re表status
     * @param typeTTR t_teammate_re表的type
     * @param typeOUR object_user_relation表的type
     * @return
     */
    Map getRealNameByOURelation(@Param("objectType") Integer objectType, @Param("objectId") Integer objectId,
                                @Param("status") Integer status, @Param("typeTTR") Integer typeTTR, @Param("typeOUR") Integer typeOUR);

}
