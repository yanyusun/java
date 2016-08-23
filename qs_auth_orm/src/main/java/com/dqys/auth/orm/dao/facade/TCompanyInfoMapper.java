package com.dqys.auth.orm.dao.facade;

import com.dqys.auth.orm.pojo.CompanyDetailInfo;
import com.dqys.auth.orm.pojo.TCompanyInfo;
import com.dqys.auth.orm.query.CompanyQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCompanyInfoMapper {
    Integer deleteByPrimaryKey(Integer id);

    Integer insertSelective(TCompanyInfo record);

    TCompanyInfo selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(TCompanyInfo record);

    Integer countByQuery(CompanyQuery query);

    List<TCompanyInfo> queryList(CompanyQuery query);

    /**
     * 根据用户信息获取公司详情
     * @param id
     * @return
     */
    CompanyDetailInfo get(Integer id);

    /**
     * 根据公司类型获取公司列表
     * @param type
     * @return
     */
    List<TCompanyInfo> listByType(@Param("typeId")Integer type);
}