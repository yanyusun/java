package com.dqys.business.orm.mapper.cases;

import com.dqys.business.orm.pojo.cases.CaseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaseInfoMapper {
    /**
     * 删除案件
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     * 增加新案件
     *
     * @param caseInfo
     * @return
     */
    Integer insert(CaseInfo caseInfo);

    /**
     * 获取案件
     *
     * @param id
     * @return
     */
    CaseInfo get(Integer id);

    /**
     * 修改案件
     *
     * @param caseInfo
     * @return
     */
    Integer update(CaseInfo caseInfo);

    /**
     * 根据借款人查询当前借款人的案件情况
     * @param id 借款人ID
     * @return
     */
    Integer countByLender(Integer id);

    /**
     * 根据借款人查询的所属的第N条案件信息
     * @param id
     * @param index
     * @return
     */
    List<CaseInfo> listByLender(@Param("id")Integer id, @Param("index")Integer index);

    /**
     * 根据案件id统计子案件
     * @param id
     * @return
     */
    Integer countByCase(Integer id);

    /**
     * 根据案件ID以及位置查询子案件信息
     * @param id
     * @param index
     * @return
     */
    List<CaseInfo> listByCase(@Param("id")Integer id, @Param("index")Integer index);

}