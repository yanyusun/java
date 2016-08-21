package com.dqys.business.service.service.cases;

import com.dqys.business.service.dto.cases.CaseDTO;
import com.dqys.business.service.dto.cases.CaseDTOList;
import com.dqys.business.service.exception.bean.BusinessLogException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by Yvan on 16/7/26.
 */
@Service
public interface CaseService {
    /**
     * 删除案件
     *
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey_tx(Integer id) throws BusinessLogException;

    /**
     * 增加新案件
     *
     * @param caseDTO
     * @return
     */
    Integer add_tx(CaseDTO caseDTO) throws BusinessLogException;

    /**
     * 获取案件
     *
     * @param id
     * @return
     */
    CaseDTO get(Integer id);

    /**
     * 修改案件
     *
     * @param caseDTO
     * @return
     */
    Integer update_tx(CaseDTO caseDTO) throws BusinessLogException;


    /**
     * 根据借款人统计案件数量
     * @param id
     * @return
     */
    Integer getCountBylender(Integer id);

    /**
     * 根据借款人以及位置搜索第N件案件
     * @param id
     * @param index
     * @return
     */
    CaseDTO getByLender(@Param("id")Integer id, @Param("index")Integer index);

    /**
     * 根据母案件id统计子案件
     * @param id
     * @return
     */
    Integer getCountByCase(Integer id);

    /**
     * 根据母案件ID以及位置找寻子案件信息
     * @param id
     * @param index
     * @return
     */
    CaseDTO getByCase(@Param("id")Integer id, @Param("index")Integer index);

    /**
     * 批量增加案件信息
     * @param caseDTOList
     * @return
     */
    Integer listAdd(CaseDTOList caseDTOList) throws BusinessLogException;

}
