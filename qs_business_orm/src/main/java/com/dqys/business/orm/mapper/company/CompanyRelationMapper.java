package com.dqys.business.orm.mapper.company;


import com.dqys.business.orm.pojo.coordinator.CompanyRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyRelationMapper {
    /**
     * 根据id删除公司之间关系
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 根据两公司Id删除关联关系
     *
     * @param aId
     * @param bId
     * @return
     */
    Integer deleteByCompanyId(@Param("aId") Integer aId, @Param("bId") Integer bId);

    /**
     * 清空该公司的关联关系表
     * @param id
     * @return
     */
    Integer clearByCompanyId(Integer id);

    /**
     * 添加公司关系
     *
     * @param record
     * @return
     */
    Integer insert(CompanyRelation record);

    /**
     * 根据单方面公司查询所有联系公司
     *
     * @param id
     * @return
     */
    List<CompanyRelation> listByCompanyId(Integer id);

    /**
     * 根据两公司ID获取关联关系
     *
     * @param aId
     * @param bId
     * @return
     */
    CompanyRelation getByCompanyId(@Param("aId") Integer aId, @Param("bId") Integer bId);

    /**
     * 根据Id获取关联关系
     *
     * @param id
     * @return
     */
    CompanyRelation get(Integer id);
}