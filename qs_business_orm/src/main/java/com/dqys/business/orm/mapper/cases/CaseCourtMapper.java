package com.dqys.business.orm.mapper.cases;


import com.dqys.business.orm.pojo.cases.CaseCourt;

import java.util.List;

public interface CaseCourtMapper {

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 根据案件ID删除相关联法院
     * @param id
     * @return
     */
    Integer deleteByCaseId(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    Integer insert(CaseCourt record);

    /**
     * 根据ID单取
     * @param id
     * @return
     */
    CaseCourt get(Integer id);

    /**
     * 修改
     * @param record
     * @return
     */
    Integer update(CaseCourt record);

    /**
     * 根据案件ID查询出相关联的处置法院
     * @param id
     * @return
     */
    List<CaseCourt> listByCaseId(Integer id);

}