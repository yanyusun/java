package com.dqys.business.service.utils.cases;

import com.dqys.business.orm.pojo.cases.CaseCourt;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.dto.cases.*;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/26.
 */
public class CaseServiceUtils {


    public static CaseInfo toCaseInfo(CaseDTO caseDTO) {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getPawnId())) {
            return null;
        }
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setCaseNo(caseDTO.getCaseNo());
        caseInfo.setId(caseDTO.getId());
        caseInfo.setPid(caseDTO.getpId());
        caseInfo.setName(caseDTO.getCaseName());
        caseInfo.setType(caseDTO.getType());
        caseInfo.setPawnId(caseDTO.getPawnId());
        caseInfo.setPlaintiff(caseDTO.getPlaintiff());
        caseInfo.setDefendant(caseDTO.getDefendant());
        caseInfo.setSpouse(caseDTO.getSpouse());
        caseInfo.setMortgagor(caseDTO.getMortgagor());
        caseInfo.setMortgageTime(caseDTO.getMortgageTime());
        caseInfo.setGuarantor(caseDTO.getGuarantor());
        caseInfo.setEvaluateExcellent(caseDTO.getEvaluateExcellent());
        caseInfo.setEvaluateLevel(caseDTO.getEvaluateLevel());
        caseInfo.setMemo(caseDTO.getMemo());
        caseInfo.setLawsuitAmount(caseDTO.getLawsuitAmount());
        caseInfo.setLawsuitAccrual(caseDTO.getLawsuitAccrual());
        caseInfo.setLawsuitCorpus(caseDTO.getLawsuitCorpus());
        caseInfo.setLawsuitMemo(caseDTO.getLawsuitMemo());
        caseInfo.setAttachmentStatus(caseDTO.getAttachmentStatus());
        caseInfo.setAttachmentDate(caseDTO.getAttachmentDate());
        caseInfo.setAttachmentCourt(caseDTO.getAttachmentCourt());
        caseInfo.setAttachmentTime(caseDTO.getAttachmentTime());
        caseInfo.setIsPreservation(caseDTO.getPreservation());
        caseInfo.setPreservationStart(caseDTO.getPreservationStart());
        caseInfo.setPreservationEnd(caseDTO.getPreservationEnd());
        caseInfo.setPreservationMemo(caseDTO.getPreservationMemo());
        caseInfo.setIsFirst(caseDTO.getIsFirst());
        caseInfo.setFirstAttachmentDate(caseDTO.getFirstAttachmentDate());
        caseInfo.setFirstAttachmentCourt(caseDTO.getFirstAttachmentCourt());
        caseInfo.setPreservationCourt(caseDTO.getPreservationCourt());
        caseInfo.setFirstAttachmentCode(caseDTO.getFirstAttachmentCode());
        caseInfo.setAttachmentMemo(caseDTO.getAttachmentMemo());

        return caseInfo;
    }

    public static CaseCourt toCaseCourt(CaseCourtDTO caseCourtDTO) {
        if (CommonUtil.checkParam(caseCourtDTO)) {
            return null;
        }
        CaseCourt caseCourt = new CaseCourt();

        caseCourt.setId(caseCourtDTO.getId());
        caseCourt.setCaseId(caseCourtDTO.getCaseId());
        caseCourt.setCourt(caseCourtDTO.getCourt());
        caseCourt.setCode(caseCourtDTO.getCode());
        caseCourt.setLawyer(caseCourtDTO.getLawyer());
        caseCourt.setMemo(caseCourtDTO.getLawyerMemo());
        caseCourt.setMobile(caseCourtDTO.getMobile());
        caseCourt.setTel(caseCourtDTO.getTel());
        caseCourt.setOther(caseCourtDTO.getOther());
        caseCourt.setGender(caseCourtDTO.getGender());
        caseCourt.setOtherLawyer(caseCourtDTO.getOtherLawyer());

        return caseCourt;
    }

    public static CaseDTO toCaseDTO(CaseInfo caseInfo, List<CaseCourt> caseCourtList,
                                    String iouIds, List<BaseSelectonDTO> selectonDTOList, String pawnName) {
        CaseDTO caseDTO = new CaseDTO();

        caseDTO.setId(caseInfo.getId());
        caseDTO.setpId(caseInfo.getPid());
        caseDTO.setType(caseInfo.getType());
        caseDTO.setCaseName(caseInfo.getName());
        caseDTO.setPawnId(caseInfo.getPawnId());
        caseDTO.setPlaintiff(caseInfo.getPlaintiff());
        caseDTO.setDefendant(caseInfo.getDefendant());
        caseDTO.setSpouse(caseInfo.getSpouse());
        caseDTO.setMortgagor(caseInfo.getMortgagor());
        caseDTO.setMortgageTime(caseInfo.getMortgageTime());
        caseDTO.setGuarantor(caseInfo.getGuarantor());
        caseDTO.setEvaluateExcellent(caseInfo.getEvaluateExcellent());
        caseDTO.setEvaluateLevel(caseInfo.getEvaluateLevel());
        caseDTO.setMemo(caseInfo.getMemo());
        caseDTO.setLawsuitAmount(caseInfo.getLawsuitAmount());
        caseDTO.setLawsuitAccrual(caseInfo.getLawsuitAccrual());
        caseDTO.setLawsuitCorpus(caseInfo.getLawsuitCorpus());
        caseDTO.setLawsuitMemo(caseInfo.getLawsuitMemo());
        caseDTO.setAttachmentStatus(caseInfo.getAttachmentStatus());
        caseDTO.setAttachmentDate(caseInfo.getAttachmentDate());
        caseDTO.setAttachmentCourt(caseInfo.getAttachmentCourt());
        caseDTO.setAttachmentTime(caseInfo.getAttachmentTime());
        caseDTO.setPreservation(caseInfo.getIsPreservation());
        caseDTO.setPreservationStart(caseInfo.getPreservationStart());
        caseDTO.setPreservationEnd(caseInfo.getPreservationEnd());
        caseDTO.setPreservationMemo(caseInfo.getPreservationMemo());
        caseDTO.setIsFirst(caseInfo.getIsFirst());
        caseDTO.setFirstAttachmentDate(caseInfo.getFirstAttachmentDate());
        caseDTO.setFirstAttachmentCourt(caseInfo.getFirstAttachmentCourt());
        caseDTO.setPreservationCourt(caseInfo.getPreservationCourt());
        caseDTO.setFirstAttachmentCode(caseInfo.getFirstAttachmentCode());
        caseDTO.setAttachmentMemo(caseInfo.getAttachmentMemo());

        caseDTO.setIouIds(iouIds);
        caseDTO.setIouList(selectonDTOList);
        caseDTO.setCourtDTOList(toCaseCourtDTO(caseCourtList));
        caseDTO.setPawnName(pawnName);
        caseDTO.setFirstStair(caseInfo.getFirstStair());
        caseDTO.setSecondStait(caseInfo.getSecondStait());
        return caseDTO;
    }

    public static List<CaseCourtDTO> toCaseCourtDTO(List<CaseCourt> caseCourtList) {
        if (caseCourtList == null) {
            return null;
        }
        List<CaseCourtDTO> caseCourtDTOList = new ArrayList<>();
        for (CaseCourt caseCourt : caseCourtList) {
            caseCourtDTOList.add(toCaseCourtDTO(caseCourt));
        }
        return caseCourtDTOList;
    }

    public static CaseCourtDTO toCaseCourtDTO(CaseCourt caseCourt) {
        if (CommonUtil.checkParam(caseCourt)) {
            return null;
        }
        CaseCourtDTO caseCourtDTO = new CaseCourtDTO();

        caseCourtDTO.setId(caseCourt.getId());
        caseCourtDTO.setCaseId(caseCourt.getCaseId());
        caseCourtDTO.setCourt(caseCourt.getCourt());
        caseCourtDTO.setCode(caseCourt.getCode());
        caseCourtDTO.setLawyer(caseCourt.getLawyer());
        caseCourtDTO.setLawyerMemo(caseCourt.getMemo());
        caseCourtDTO.setMobile(caseCourt.getMobile());
        caseCourtDTO.setTel(caseCourt.getTel());
        caseCourtDTO.setOther(caseCourt.getOther());
        caseCourtDTO.setGender(caseCourt.getGender());
        caseCourtDTO.setOtherLawyer(caseCourt.getOtherLawyer());

        return caseCourtDTO;
    }

    /**
     * 案件信息
     *
     * @param caseDTO
     * @return
     */
    public static String checkData(CaseDTO caseDTO) {
        if (CommonUtil.checkParam(caseDTO, caseDTO.getCaseName(), caseDTO.getPawnId(),
                caseDTO.getPlaintiff(), caseDTO.getDefendant(), caseDTO.getGuarantor(),
                caseDTO.getSpouse(), caseDTO.getMortgagor(), caseDTO.getEvaluateExcellent(),
                caseDTO.getEvaluateLevel(), caseDTO.getIouIds(), caseDTO.getLawsuitAccrual(),
                caseDTO.getLawsuitAmount(), caseDTO.getLawsuitCorpus())) {
            return "基础信息缺失";
        }
        if (CommonUtil.checkParam(caseDTO.getCourtDTOList())) {
            return "案件关联法院信息缺失";
        }
        if (CommonUtil.checkParam(caseDTO, caseDTO.getAttachmentStatus(),
                caseDTO.getPreservation(),
                caseDTO.getIsFirst())) {
            return "案件查封保全信息缺失";
        }
        if (ExcellentTypeEnum.getExcellentTypeEnum(caseDTO.getEvaluateExcellent()) == null) {
            return "评优类型参数错误";
        }
        if (!CommonUtil.isExist(CommonUtil.UPLETTER, caseDTO.getEvaluateLevel())) {
            return "评级参数错误";
        }
        if (!CommonUtil.isMoneyFormat(caseDTO.getLawsuitAccrual(), caseDTO.getLawsuitAmount(),
                caseDTO.getLawsuitCorpus())) {
            return "存在非法金额参数";
        }
        return null;
    }

    /**
     * 案件基础信息
     *
     * @param caseBaseDTO
     * @return
     */
    public static String checkData(CaseBaseDTO caseBaseDTO) {
        if (CommonUtil.checkParam(caseBaseDTO, caseBaseDTO.getId(), caseBaseDTO.getPawnId(),
                caseBaseDTO.getPlaintiff(), caseBaseDTO.getDefendant(), caseBaseDTO.getGuarantor(),
                caseBaseDTO.getSpouse(), caseBaseDTO.getMortgagor(), caseBaseDTO.getEvaluateExcellent(),
                caseBaseDTO.getEvaluateLevel(), caseBaseDTO.getIouIds())) {
            return "参数错误";
        }
        if (ExcellentTypeEnum.getExcellentTypeEnum(caseBaseDTO.getEvaluateExcellent()) == null) {
            return "评优类型参数错误";
        }
        if (!CommonUtil.isExist(CommonUtil.UPLETTER, caseBaseDTO.getEvaluateLevel())) {
            return "评级参数错误";
        }
        return null;
    }

    /**
     * 案件基础信息转化成DAO
     *
     * @param caseBaseDTO
     * @return
     */
    public static CaseInfo toCaseInfo(CaseBaseDTO caseBaseDTO) {
        CaseInfo caseInfo = new CaseInfo();

        caseInfo.setId(caseBaseDTO.getId());
        caseInfo.setPawnId(caseBaseDTO.getPawnId());
        caseInfo.setPlaintiff(caseBaseDTO.getPlaintiff());
        caseInfo.setDefendant(caseBaseDTO.getDefendant());
        caseInfo.setSpouse(caseBaseDTO.getSpouse());
        caseInfo.setMortgagor(caseBaseDTO.getMortgagor());
        caseInfo.setGuarantor(caseBaseDTO.getGuarantor());
        caseInfo.setEvaluateExcellent(caseBaseDTO.getEvaluateExcellent());
        caseInfo.setEvaluateLevel(caseBaseDTO.getEvaluateLevel());

        return caseInfo;
    }

    /**
     * 案件诉讼信息
     *
     * @param caseLawsuitDTO
     * @return
     */
    public static String checkData(CaseLawsuitDTO caseLawsuitDTO) {
        if (CommonUtil.checkParam(caseLawsuitDTO, caseLawsuitDTO.getId(), caseLawsuitDTO.getLawsuitAccrual(),
                caseLawsuitDTO.getLawsuitAmount(), caseLawsuitDTO.getLawsuitCorpus())) {
            return "参数错误";
        }
        if (!CommonUtil.isMoneyFormat(caseLawsuitDTO.getLawsuitAccrual(), caseLawsuitDTO.getLawsuitAmount(),
                caseLawsuitDTO.getLawsuitCorpus())) {
            return "存在非法金额参数";
        }
        return null;
    }

    /**
     * 案件诉讼信息转化成DAO
     *
     * @param caseLawsuitDTO
     * @return
     */
    public static CaseInfo toCaseInfo(CaseLawsuitDTO caseLawsuitDTO) {
        CaseInfo caseInfo = new CaseInfo();

        caseInfo.setId(caseLawsuitDTO.getId());
        caseInfo.setLawsuitAccrual(caseLawsuitDTO.getLawsuitAccrual());
        caseInfo.setLawsuitAmount(caseLawsuitDTO.getLawsuitAmount());
        caseInfo.setLawsuitCorpus(caseLawsuitDTO.getLawsuitCorpus());
        caseInfo.setLawsuitMemo(caseLawsuitDTO.getLawsuitMemo());

        return caseInfo;
    }

    /**
     * 案件查封保全信息
     *
     * @param caseAttachmentDTO
     * @return
     */
    public static String checkData(CaseAttachmentDTO caseAttachmentDTO) {
        if (CommonUtil.checkParam(caseAttachmentDTO, caseAttachmentDTO.getId(), caseAttachmentDTO.getAttachmentStatus(),
                caseAttachmentDTO.getPreservation(),
                caseAttachmentDTO.getIsFirst())) {
            return "参数错误";
        }
        return null;
    }

    /**
     * 案件查封保全信息转化成DAO
     *
     * @param caseAttachmentDTO
     * @return
     */
    public static CaseInfo toCaseInfo(CaseAttachmentDTO caseAttachmentDTO) {
        CaseInfo caseInfo = new CaseInfo();

        caseInfo.setId(caseAttachmentDTO.getId());
        caseInfo.setAttachmentStatus(caseAttachmentDTO.getAttachmentStatus());
        caseInfo.setAttachmentDate(caseAttachmentDTO.getAttachmentDate());
        caseInfo.setAttachmentCourt(caseAttachmentDTO.getAttachmentCourt());
        caseInfo.setAttachmentTime(caseAttachmentDTO.getAttachmentTime());
        caseInfo.setIsPreservation(caseAttachmentDTO.getPreservation());
        caseInfo.setPreservationStart(caseAttachmentDTO.getPreservationStart());
        caseInfo.setPreservationEnd(caseAttachmentDTO.getPreservationEnd());
        caseInfo.setPreservationMemo(caseAttachmentDTO.getPreservationMemo());
        caseInfo.setIsFirst(caseAttachmentDTO.getIsFirst());
        caseInfo.setFirstAttachmentDate(caseAttachmentDTO.getFirstAttachmentDate());
        caseInfo.setPreservationCourt(caseAttachmentDTO.getPreservationCourt());
        caseInfo.setFirstAttachmentCode(caseAttachmentDTO.getFirstAttachmentCode());
        caseInfo.setAttachmentMemo(caseAttachmentDTO.getAttachmentMemo());

        return caseInfo;
    }

    /**
     * 案件相关联法院信息
     *
     * @param caseCourtDTO
     * @return
     */
    public static String checkData(CaseCourtDTO caseCourtDTO) {
        if (CommonUtil.checkParam(caseCourtDTO, caseCourtDTO.getCourt(), caseCourtDTO.getCode(),
                caseCourtDTO.getLawyer(), caseCourtDTO.getMobile())) {
            return "参数错误";
        }
        return null;
    }

}
