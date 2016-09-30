package com.dqys.business.orm.mapper.asset;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.query.asset.IOUQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOUInfoMapper {
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    Integer insert(IOUInfo record);

    /**
     * 获取单个
     * @param id
     * @return
     */
    IOUInfo get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(IOUInfo record);

    /**
     * 统计所有的借据
     * @return
     */
    Integer count();

    /**
     * 查询借款人下的所有借据
     * @param lenderId
     * @return
     */
    List<IOUInfo> listByLenderId(Integer lenderId);

    /**
     * 多条件查询借据
     * @param IOUQuery
     * @return
     */
    List<IOUInfo> queryList(IOUQuery IOUQuery);

    /**
     * 多条件统计
     * @param iouQuery
     * @return
     */
    Integer queryCount(IOUQuery iouQuery);

    /**
     * 根据抵押物id获取抵押物下的所有借据
     * @param objectId 抵押物id
     * @return
     */
    List<IOUInfo> selectIouInfoByPawnId(Integer objectId);

    /**
     * 查询特定借款人下的特定名称的借据
     * @param id
     * @param name
     * @return
     */
    List<IOUInfo> listByName(@Param("lenderId")Integer id, @Param("name")String name);

    /**
     * 获取当前用户的借款人下的借据信息
     *
     * @param lenderId   借款人id
     * @param userId     用户id
     * @param objectType 借据类型
     * @return
     */
    List<IOUInfo> iouListByLenderId(@Param("lenderId") Integer lenderId, @Param("userId") Integer userId, @Param("objectType") Integer objectType, @Param("userType") Integer userType);

    /**
     * 获取所有借据
     * @param iouIds
     * @return
     */
    List<IOUInfo> selectIouInfoByObjectIds(List<Integer> iouIds);
}