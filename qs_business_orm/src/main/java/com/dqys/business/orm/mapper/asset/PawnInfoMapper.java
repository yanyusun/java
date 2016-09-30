package com.dqys.business.orm.mapper.asset;


import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.query.asset.PawnQuery;
import com.dqys.business.orm.query.coordinator.ZcyListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PawnInfoMapper {
    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增
     *
     * @param record
     * @return
     */
    Integer insert(PawnInfo record);

    /**
     * 获取单个实例
     *
     * @param id
     * @return
     */
    PawnInfo get(Integer id);

    /**
     * 修改
     *
     * @param record
     * @return
     */
    Integer update(PawnInfo record);

    /**
     * 统计
     *
     * @return
     */
    Integer count();

    /**
     * 根据借款人查看抵押物
     *
     * @param lenderId
     * @return
     */
    List<PawnInfo> listByLenderId(Integer lenderId);


    /**
     * 多条件查询抵押物
     *
     * @param pawnQuery
     * @return
     */
    List<PawnInfo> queryList(PawnQuery pawnQuery);

    /**
     * 多条件统计
     *
     * @param pawnQuery
     * @return
     */
    Integer queryCount(PawnQuery pawnQuery);

    /**
     * 根据特定借款人的特定名称查询抵押物
     *
     * @param id
     * @param name
     * @return
     */
    List<PawnInfo> listByName(@Param("lenderId") Integer id, @Param("name") String name);

    //    <!--中介抵押物-->
    List<PawnInfo> pawnListPage(@Param("zcyListQuery") ZcyListQuery zcyListQuery);

    Integer pawnListPageCount(@Param("zcyListQuery") ZcyListQuery zcyListQuery);

    /**
     * 获取当前用户的借款人下的抵押物信息
     *
     * @param lenderId   借款人id
     * @param userId     用户id
     * @param objectType 抵押物类型
     * @return
     */
    List<PawnInfo> pawnListByLenderId(@Param("lenderId") Integer lenderId, @Param("userId") Integer userId, @Param("objectType") Integer objectType, @Param("userType") Integer userType);

}