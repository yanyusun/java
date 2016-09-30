package com.dqys.business.service.service.cases.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.mapper.asset.CiRelationMapper;
import com.dqys.business.orm.mapper.asset.PawnInfoMapper;
import com.dqys.business.orm.mapper.cases.CaseCourtMapper;
import com.dqys.business.orm.mapper.cases.CaseInfoMapper;
import com.dqys.business.orm.pojo.asset.CiRelation;
import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.orm.pojo.cases.CaseCourt;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.service.constant.ObjectLogEnum;
import com.dqys.business.service.dto.cases.CaseCourtDTO;
import com.dqys.business.service.dto.cases.CaseDTO;
import com.dqys.business.service.dto.cases.CaseDTOList;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.cases.CaseService;
import com.dqys.business.service.utils.cases.CaseServiceUtils;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/26.
 */
@Repository
@Primary
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseInfoMapper caseInfoMapper;
    @Autowired
    private CaseCourtMapper caseCourtMapper;
    @Autowired
    private CiRelationMapper ciRelationMapper;
    @Autowired
    private PawnInfoMapper pawnInfoMapper;

    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessLogService businessLogService;

    @Override
    public Integer deleteByPrimaryKey_tx(Integer id) throws BusinessLogException {
        Integer result = caseInfoMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return null;
        } else {
            businessLogService.add(id, ObjectTypeEnum.CASE.getValue(), ObjectLogEnum.delete.getValue(),
                    "", "", 0, 0);
            return result;
        }
    }

    @Override
    public Integer add_tx(CaseDTO caseDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getIouIds(), caseDTO.getPawnId(), caseDTO.getCourtDTOList())) {
            return null;
        }
        // 案件基础信息
        CaseInfo caseInfo = CaseServiceUtils.toCaseInfo(caseDTO);
        if (caseInfo == null) {
            return null;
        }
        // 抵押物信息
        PawnInfo pawnInfo = pawnInfoMapper.get(caseDTO.getPawnId());
        if (CommonUtil.checkParam(pawnInfo)) {
            return null;
        }
        // 创建案件的编号
        caseInfo.setCaseNo(RandomUtil.getCode(RandomUtil.CASE_CODE));
        // 增加案件基础信息
        Integer result = caseInfoMapper.insert(caseInfo);
        if (CommonUtil.checkResult(result)) {
            return null;
        }
        Integer caseId = caseInfo.getId();
        // 法院信息
        for (CaseCourtDTO caseCourtDTO : caseDTO.getCourtDTOList()) {
            CaseCourt caseCourt = CaseServiceUtils.toCaseCourt(caseCourtDTO);
            caseCourt.setCaseId(caseId);
            Integer courtAdd = caseCourtMapper.insert(caseCourt);
            if (CommonUtil.checkParam(courtAdd)) {
                // 增加法院信息失败
                return null;
            }
        }

        // 案件与借据
        String[] idStr = caseDTO.getIouIds().split(",");
        CiRelation ciRelation = new CiRelation();
        ciRelation.setCaseId(caseId);
        for (String string : idStr) {
            ciRelation.setIouId(Integer.valueOf(string));
            Integer ciAdd = ciRelationMapper.insert(ciRelation);
            if (CommonUtil.checkResult(ciAdd)) {
                // TODO 增加案件与借据关联失败
            }
        }
        // 增加事务对象
        if (caseDTO.getpId() != null && caseDTO.getpId() > 0) {
            businessService.addServiceObject(ObjectTypeEnum.CASE.getValue(), caseId,
                    ObjectTypeEnum.CASE.getValue(), caseDTO.getpId());
        } else {
            businessService.addServiceObject(ObjectTypeEnum.CASE.getValue(), caseId, null, null);
        }
        // 增加操作记录
        businessLogService.add(caseId, ObjectTypeEnum.CASE.getValue(), ObjectLogEnum.add.getValue(), "", "", 0, 0);
        return caseId;
    }

    @Override
    public Integer listAdd(CaseDTOList caseDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTOList, caseDTOList.getCaseDTOList())
                || caseDTOList.getCaseDTOList().size() == 0) {
            return null;
        }
        for (CaseDTO caseDTO : caseDTOList.getCaseDTOList()) {
            Integer add = add_tx(caseDTO);
            if (add == null) {
                return null;
            }
        }
        return 1;
    }

    @Override
    public CaseDTO get(Integer id) {
        if (id == null) {
            return null;
        }
        // 基础信息
        CaseInfo caseInfo = caseInfoMapper.get(id);
        if (caseInfo == null) {
            return null;
        }
        return createCaseDTOByInfo(caseInfo);
    }

    @Override
    public Integer update_tx(CaseDTO caseDTO) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getIouIds(), caseDTO.getPawnId(), caseDTO.getCourtDTOList(),
                caseDTO.getId())) {
            return null;
        }
        // 案件基础信息
        CaseInfo caseInfo = CaseServiceUtils.toCaseInfo(caseDTO);
        if (caseInfo == null) {
            return null;
        }
        CaseInfo caseInfo1 = caseInfoMapper.get(caseDTO.getId());
        if (CommonUtil.checkParam(caseInfo1)) {
            return null;
        }
        Integer result = caseInfoMapper.update(caseInfo);
        if (!CommonUtil.checkResult(result)) {
            // 案件基础信息没有变动
        }
        Integer caseId = caseInfo.getId();
        // 法院信息
        List<CaseCourt> courtList = caseCourtMapper.listByCaseId(caseId);
        courtList.forEach(court -> {
            Boolean flag = true;
            for (CaseCourtDTO caseCourtDTO : caseDTO.getCourtDTOList()) {
                if (caseCourtDTO.getId() == null) {
                    break;
                }
                CaseCourt caseCourt = CaseServiceUtils.toCaseCourt(caseCourtDTO);
                caseCourt.setCaseId(caseId);
                if (caseCourt.getId().equals(court.getId())) {
                    if (!caseCourt.toCheckObject().equals(court.toCheckObject())) {
                        // 存在改变
                        Integer update = caseCourtMapper.update(caseCourt);
                        if (CommonUtil.checkParam(update)) {
                            // TODO 修改法院信息失败
                        }
                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                // 说明已经被删除了
                Integer delete = caseCourtMapper.deleteByPrimaryKey(court.getId());
                if (CommonUtil.checkParam(delete)) {
                    // TODO 删除法院信息失败
                }
            }
        });
        caseDTO.getCourtDTOList().forEach(caseCourtDTO -> {
            if (caseCourtDTO.getId() == null) {
                CaseCourt caseCourt = CaseServiceUtils.toCaseCourt(caseCourtDTO);
                caseCourt.setCaseId(caseId);
                Integer courtAdd = caseCourtMapper.insert(caseCourt);
                if (CommonUtil.checkParam(courtAdd)) {
                    // TODO 增加法院信息失败
                }
            }
        });

        // 案件与借据
        Integer delete = ciRelationMapper.deleteByCaseId(caseId);
        if (CommonUtil.checkResult(delete)) {
            // TODO 删除案件所有的关联数据失败
        }
        String[] idStr = caseDTO.getIouIds().split(",");
        CiRelation ciRelation = new CiRelation();
        ciRelation.setCaseId(caseId);
        for (String string : idStr) {
            ciRelation.setIouId(Integer.valueOf(string));
            Integer ciAdd = ciRelationMapper.insert(ciRelation);
            if (CommonUtil.checkResult(ciAdd)) {
                // TODO 增加案件与借据关联失败
            }
        }
        // 增加操作记录
        businessLogService.add(caseId, ObjectTypeEnum.CASE.getValue(), ObjectLogEnum.update.getValue(), "", "", 0, 0);
        return caseId;
    }

    @Override
    public Integer getCountByLender(Integer id) {
        return caseInfoMapper.countByLender(id);
    }

    @Override
    public CaseDTO getByLender(Integer id, Integer index) {
        if (CommonUtil.checkParam(id)) {
            return null;
        }
        if (index == null || index < 1) {
            index = 0;
        } else {
            index = index - 1;
        }
        List<CaseInfo> list = caseInfoMapper.listByLender(id, index);
        if(list == null || list.size() == 0){
            return null;
        }
        CaseInfo caseInfo = list.get(0);
        if (caseInfo == null) {
            return null;
        }
        return createCaseDTOByInfo(caseInfo);
    }

    @Override
    public Integer getCountByCase(Integer id) {
        return caseInfoMapper.countByCase(id);
    }

    @Override
    public CaseDTO getByCase(Integer id, Integer index) {
        if (CommonUtil.checkParam(id)) {
            return null;
        }
        if (index == null || index < 1) {
            index = 0;
        } else {
            index = index - 1;
        }
        List<CaseInfo> list = caseInfoMapper.listByCase(id, index);
        if(list == null || list.size() == 0){
            return null;
        }
        CaseInfo caseInfo = list.get(0);
        if (caseInfo == null) {
            return null;
        }
        return createCaseDTOByInfo(caseInfo);
    }


    @Override
    public List<CaseDTO> listByLender(Integer id) {
        if(id != null){
            List<CaseDTO> result = new ArrayList<>();
            List<CaseInfo> caseInfoList = caseInfoMapper.listByLender(id, null);
            caseInfoList.forEach(caseInfo -> {
                result.add(createCaseDTOByInfo(caseInfo));
            });
            return result;
        }
        return null;
    }

    @Override
    public List<CaseDTO> listByCase(Integer id) {
        if(id != null){
            List<CaseDTO> result = new ArrayList<>();
            List<CaseInfo> caseInfoList = caseInfoMapper.listByCase(id, null);
            caseInfoList.forEach(caseInfo -> {
                result.add(createCaseDTOByInfo(caseInfo));
            });
            return result;
        }
        return null;
    }

    private CaseDTO createCaseDTOByInfo(CaseInfo caseInfo) {
        if(caseInfo == null){
            return null;
        }
        // 法院信息
        List<CaseCourt> caseCourtList = caseCourtMapper.listByCaseId(caseInfo.getId());
        // 借据信息
        RelationQuery query = new RelationQuery();
        query.setCaseId(caseInfo.getId());
        List<CiRelation> ciList = ciRelationMapper.queryList(query);
        List<BaseSelectonDTO> selectDTOList = new ArrayList<>();
        String ids = "";
        for (CiRelation ciRelation : ciList) {
            ids += ciRelation.getIouId() + ",";
            BaseSelectonDTO selectDTO = new BaseSelectonDTO();
            selectDTO.setKey(ciRelation.getIouId().toString());
            selectDTO.setValue("借据" + ciRelation.getIouId());
            selectDTOList.add(selectDTO);
        }
        ids.substring(0, ids.length() - 1); // 去除尾部逗号
        return CaseServiceUtils.toCaseDTO(caseInfo, caseCourtList, ids, selectDTOList);
    }

}
