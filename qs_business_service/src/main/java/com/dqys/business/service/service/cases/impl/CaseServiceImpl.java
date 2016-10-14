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
import com.dqys.business.service.dto.cases.*;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.BusinessService;
import com.dqys.business.service.service.cases.CaseService;
import com.dqys.business.service.utils.cases.CaseServiceUtils;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.constant.ResponseCodeEnum;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.JsonResponseTool;
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
    public JsonResponse deleteByPrimaryKey_tx(Integer id) throws BusinessLogException {
        Integer result = caseInfoMapper.deleteByPrimaryKey(id);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("删除失败");
        } else {
            businessLogService.add(id, ObjectTypeEnum.CASE.getValue(), ObjectLogEnum.delete.getValue(),
                    "", "", 0, 0);
            return JsonResponseTool.success(result);
        }
    }

    @Override
    public JsonResponse add_tx(CaseDTO caseDTO) throws BusinessLogException {
        String error = CaseServiceUtils.checkData(caseDTO);
        if (error != null) {
            return JsonResponseTool.paramErr(error);
        }
        // 案件基础信息
        CaseInfo caseInfo = CaseServiceUtils.toCaseInfo(caseDTO);
        if (caseInfo == null) {
            return JsonResponseTool.paramErr("参数转化错误，请重新再试！");
        }
        // 抵押物信息
        PawnInfo pawnInfo = pawnInfoMapper.get(caseDTO.getPawnId());
        if (CommonUtil.checkParam(pawnInfo)) {
            return JsonResponseTool.paramErr("参数错误，请检查抵押物关联正确性");
        }
        // 创建案件的编号
        caseInfo.setCaseNo(RandomUtil.getCode(RandomUtil.CASE_CODE));
        // 增加案件基础信息
        Integer result = caseInfoMapper.insert(caseInfo);
        if (CommonUtil.checkResult(result)) {
            return JsonResponseTool.failure("增加失败");
        }
        Integer caseId = caseInfo.getId();
        // 法院信息
        for (CaseCourtDTO caseCourtDTO : caseDTO.getCourtDTOList()) {
            CaseCourt caseCourt = CaseServiceUtils.toCaseCourt(caseCourtDTO);
            caseCourt.setCaseId(caseId);
            Integer courtAdd = caseCourtMapper.insert(caseCourt);
            if (CommonUtil.checkParam(courtAdd)) {
                // 增加法院信息失败
                return JsonResponseTool.failure("增加关联法院信息失败");
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
        return JsonResponseTool.success(caseId);
    }

    @Override
    public JsonResponse listAdd(CaseDTOList caseDTOList) throws BusinessLogException {
        if (CommonUtil.checkParam(caseDTOList, caseDTOList.getCaseDTOList())
                || caseDTOList.getCaseDTOList().size() == 0) {
            return JsonResponseTool.paramErr("参数错误");
        }
        for (CaseDTO caseDTO : caseDTOList.getCaseDTOList()) {
            JsonResponse add = add_tx(caseDTO);
            if (!add.getCode().equals(ResponseCodeEnum.SUCCESS.getValue())) {
                return JsonResponseTool.failure("添加失败");
            }
        }
        return JsonResponseTool.success(null);
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
    public JsonResponse update_tx(CaseDTO caseDTO) throws BusinessLogException {
        if (caseDTO.getId() == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        String error = CaseServiceUtils.checkData(caseDTO);
        if (error != null) {
            return JsonResponseTool.paramErr(error);
        }
        // 案件基础信息
        CaseInfo caseInfo = CaseServiceUtils.toCaseInfo(caseDTO);
        if (caseInfo == null) {
            return JsonResponseTool.paramErr("参数转化错误");
        }
        CaseInfo caseInfo1 = caseInfoMapper.get(caseDTO.getId());
        if (CommonUtil.checkParam(caseInfo1)) {
            return JsonResponseTool.paramErr("参数错误，案件信息未找到");
        }
        caseInfoMapper.update(caseInfo);
        Integer caseId = caseInfo.getId();
        // 法院信息
        caseCourtMapper.deleteByCaseId(caseId);
        // 添加新的法院信息
        for (CaseCourtDTO caseCourtDTO : caseDTO.getCourtDTOList()) {
            caseCourtDTO.setCaseId(caseId);
            caseCourtMapper.insert(CaseServiceUtils.toCaseCourt(caseCourtDTO));
        }

        // 案件与借据
        ciRelationMapper.deleteByCaseId(caseId);
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
        return JsonResponseTool.success(caseId);
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
        if (list == null || list.size() == 0) {
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
        if (list == null || list.size() == 0) {
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
        if (id != null) {
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
        if (id != null) {
            List<CaseDTO> result = new ArrayList<>();
            List<CaseInfo> caseInfoList = caseInfoMapper.listByCase(id, null);
            caseInfoList.forEach(caseInfo -> {
                result.add(createCaseDTOByInfo(caseInfo));
            });
            return result;
        }
        return null;
    }

    @Override
    public JsonResponse updateCaseBase(CaseBaseDTO caseBaseDTO) {
        String error = CaseServiceUtils.checkData(caseBaseDTO);
        if (error != null) {
            return JsonResponseTool.paramErr(error);
        }
        caseInfoMapper.update(CaseServiceUtils.toCaseInfo(caseBaseDTO));
        // 案件与借据
        ciRelationMapper.deleteByCaseId(caseBaseDTO.getId());
        String[] idStr = caseBaseDTO.getIouIds().split(",");
        CiRelation ciRelation = new CiRelation();
        ciRelation.setCaseId(caseBaseDTO.getId());
        for (String string : idStr) {
            ciRelation.setIouId(Integer.valueOf(string));
            Integer ciAdd = ciRelationMapper.insert(ciRelation);
            if (CommonUtil.checkResult(ciAdd)) {
                return JsonResponseTool.failure("增加借据关联失败，请重新操作");
            }
        }
        return JsonResponseTool.success(null);
    }

    @Override
    public JsonResponse updateCaseAttachment(CaseAttachmentDTO caseAttachmentDTO) {
        String error = CaseServiceUtils.checkData(caseAttachmentDTO);
        if (error != null) {
            return JsonResponseTool.paramErr(error);
        }
        return CommonUtil.responseBack(caseInfoMapper.update(CaseServiceUtils.toCaseInfo(caseAttachmentDTO)));
    }

    @Override
    public JsonResponse updateCaseLawsuit(CaseLawsuitDTO caseLawsuitDTO) {
        String error = CaseServiceUtils.checkData(caseLawsuitDTO);
        if (error != null) {
            return JsonResponseTool.paramErr(error);
        }
        return CommonUtil.responseBack(caseInfoMapper.update(CaseServiceUtils.toCaseInfo(caseLawsuitDTO)));
    }

    @Override
    public JsonResponse updateCaseMemo(Integer id, String memo) {
        if (memo == null || memo.equals("") || id == null) {
            return JsonResponseTool.paramErr("参数错误");
        }
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setId(id);
        caseInfo.setMemo(memo);
        return CommonUtil.responseBack(caseInfoMapper.update(caseInfo));
    }

    @Override
    public JsonResponse updateCaseCourt(Integer id, List<CaseCourtDTO> caseCourtDTOList) {
        // 删除该案件的关联法院
        caseCourtMapper.deleteByCaseId(id);
        // 添加新的法院信息
        for (CaseCourtDTO caseCourtDTO : caseCourtDTOList) {
            caseCourtDTO.setCaseId(id);
            Integer result = caseCourtMapper.insert(CaseServiceUtils.toCaseCourt(caseCourtDTO));
            if (result == null || !result.equals(1)) {
                return JsonResponseTool.failure("添加失败");
            }
        }
        return JsonResponseTool.success(null);
    }

    private CaseDTO createCaseDTOByInfo(CaseInfo caseInfo) {
        if (caseInfo == null) {
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
