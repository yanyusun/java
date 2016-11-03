package com.dqys.business.service.service.cases;

import com.dqys.business.service.dto.cases.*;
import com.dqys.business.service.dto.common.SelectDTOList;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.model.JsonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

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
    JsonResponse deleteByPrimaryKey_tx(Integer id) throws BusinessLogException;

    /**
     * 增加新案件
     *
     * @param caseDTO
     * @return
     */
    JsonResponse add_tx(CaseDTO caseDTO) throws BusinessLogException;

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
    JsonResponse update_tx(CaseDTO caseDTO) throws BusinessLogException;


    /**
     * 根据借款人统计案件数量
     *
     * @param id
     * @return
     */
    Integer getCountByLender(Integer id);

    /**
     * 根据借款人以及位置搜索第N件案件
     *
     * @param id
     * @param index
     * @return
     */
    CaseDTO getByLender(Integer id, Integer index);

    /**
     * 根据借款人查询全部的案件信息
     *
     * @param id
     * @return
     */
    List<CaseDTO> listByLender(Integer id);

    /**
     * 根据母案件id统计子案件
     *
     * @param id
     * @return
     */
    Integer getCountByCase(Integer id);

    /**
     * 根据母案件ID以及位置找寻子案件信息
     *
     * @param id
     * @param index
     * @return
     */
    CaseDTO getByCase(Integer id, Integer index);

    /**
     * 根据母案件查询子案件
     *
     * @param id
     * @return
     */
    List<CaseDTO> listByCase(Integer id);

    /**
     * 批量增加案件信息
     *
     * @param caseDTOList
     * @return
     */
    JsonResponse listAdd(CaseDTOList caseDTOList) throws BusinessLogException;

    JsonResponse updateCaseBase(CaseBaseDTO caseBaseDTO);

    JsonResponse updateCaseAttachment(CaseAttachmentDTO caseAttachmentDTO);

    JsonResponse updateCaseLawsuit(CaseLawsuitDTO caseLawsuitDTO);

    JsonResponse updateCaseMemo(Integer id, String memo);

    JsonResponse updateCaseCourt(Integer id, List<CaseCourtDTO> caseCourtDTOList);

    /**
     * 根据案件ID查询借据
     * @param id
     * @return
     */
    List<BaseSelectonDTO> listIouByCaseId(Integer id);

    Object delete(Integer id);
}
