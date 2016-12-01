package com.dqys.business.orm.mapper.asset;

import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.query.asset.LenderQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LenderInfoMapper {
    /**
     * 逻辑删除关系
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增借款人关系
     *
     * @param record
     * @return
     */
    Integer insert(LenderInfo record);

    /**
     * 修改联系关系
     *
     * @param lenderInfo
     * @return
     */
    Integer update(LenderInfo lenderInfo);

    /**
     * 获取借款人联系信息
     *
     * @param id
     * @return
     */
    LenderInfo get(Integer id);

    /**
     * 根据资产包Id查询借款人
     *
     * @param id
     * @return
     */
    List<LenderInfo> listByAssetId(Integer id);

    /**
     * 条件搜索借款人
     *
     * @param lenderQuery
     * @return
     */
    List<LenderInfo> queryList(LenderQuery lenderQuery);

    /**
     * 条件统计
     *
     * @param lenderQuery
     * @return
     */
    Integer queryCount(LenderQuery lenderQuery);

    /**
     * 模糊查询列表
     *
     * @param str
     * @return
     */
    List<Integer> likeList(String str);

    /**
     * 借款人相关的全部
     *
     * @param userId
     * @return
     */
    List<Integer> lenderAllByObjectUserRelation(@Param("userId") Integer userId, @Param("objectType") Integer objectType);

    /**
     * @param assetId
     * @return
     */
    LenderInfo getLenderBySumMoney(Integer assetId);

    List<Integer> findObjectIdByLender(@Param("userId") Integer userId, @Param("objectType") Integer objectType, @Param("roleType") Integer roleType);

    /**
     * 根据资产包id，获取底下所有借款人id
     */

    List<Integer> selectByAssetId(Integer assetId);

    /**
     * 获取借款人下所有借据id
     *
     * @param lenderIds
     * @return
     */
    List<Integer> selectIouIdByLenderId(List<Integer> lenderIds);

    /**
     * 获取借款人下所有抵押物id
     *
     * @param lenderIds
     * @return
     */
    List<Integer> selectPawnIdByLenderId(List<Integer> lenderIds);

    /**
     * 获取借据相关的案件id
     *
     * @param iouIds
     * @return
     */
    List<Integer> selectCaseIdByIouId(List<Integer> iouIds);
}