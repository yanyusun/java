package com.dqys.business.service.utils.cases;

import com.dqys.business.orm.pojo.cases.CaseCourt;
import com.dqys.business.orm.pojo.cases.CaseInfo;
import com.dqys.business.service.dto.cases.CaseCourtDTO;
import com.dqys.business.service.dto.cases.CaseDTO;
import com.dqys.core.base.BaseSelectonDTO;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Yvan on 16/7/26.
 */
public class CaseServiceUtils {

    public static final String CASE_NO = "AJ";
    public static String createCaseNo(){
        return CASE_NO
                + DateFormatTool.format(DateFormatTool.DATE_FORMAT_6)
                + RandomStringUtils.randomNumeric(4);
    }

    public static CaseInfo toCaseInfo(CaseDTO caseDTO){
        if(CommonUtil.checkParam(caseDTO, caseDTO.getPawnId())){
            return null;
        }
        CaseInfo caseInfo = new CaseInfo();

        caseInfo.setId(caseDTO.getId());
        caseInfo.setpId(caseDTO.getpId());
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
        caseInfo.setIsPreservation(caseDTO.getIsPreservation());
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

    public static CaseCourt toCaseCourt(CaseCourtDTO caseCourtDTO){
        if(CommonUtil.checkParam(caseCourtDTO)){
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
                                    String iouIds, List<BaseSelectonDTO> selectonDTOList){
        CaseDTO caseDTO = new CaseDTO();

        caseDTO.setId(caseInfo.getId());
        caseDTO.setpId(caseInfo.getpId());
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
        caseDTO.setIsPreservation(caseInfo.getIsPreservation());
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

        return caseDTO;
    }

    public static List<CaseCourtDTO> toCaseCourtDTO(List<CaseCourt> caseCourtList){
        if(caseCourtList == null){
            return null;
        }
        List<CaseCourtDTO> caseCourtDTOList = new ArrayList<>();

        return caseCourtDTOList;
    }

    public static CaseCourtDTO toCaseCourtDTO(CaseCourt caseCourt){
        if(CommonUtil.checkParam(caseCourt)){
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

}
