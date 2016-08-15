package com.dqys.business.service.utils.asset;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/19.
 */
public class IouServiceUtils {

    public static final String MIDDLE_IOU_CODE = "JIE"; // 格式:JIE160101XXXX
    /**
     * 生成借据号
     * @return
     */
    public static String createIouCode(){
        return MIDDLE_IOU_CODE
                + DateFormatTool.format(DateFormatTool.DATE_FORMAT_6)
                + RandomStringUtils.randomNumeric(4);
    }

    public static List<IouDTO> toIouDTO(List<IOUInfo> iouInfoList){
        if(CommonUtil.checkParam(iouInfoList)){
            return null;
        }
        List<IouDTO> iouDTOList = new ArrayList<>();
        iouInfoList.forEach(iouInfo -> {
            iouDTOList.add(toIouDTO(iouInfo));
        });
        return iouDTOList;
    }

    public static IouDTO toIouDTO(IOUInfo iouInfo){
        IouDTO iouDTO = new IouDTO();

        iouDTO.setId(iouInfo.getId());
        iouDTO.setIouNo(iouInfo.getIouNo());
        iouDTO.setIouName(iouInfo.getName());
        iouDTO.setLenderId(iouInfo.getLenderId());
        iouDTO.setType(iouInfo.getType());
        iouDTO.setAgency(iouInfo.getAgency());
        iouDTO.setIouCode(iouInfo.getIouCode());
        iouDTO.setLoanTime(iouInfo.getLoanTime());
        iouDTO.setLoanAtTime(iouInfo.getLoanAttime());
        iouDTO.setAmount(iouInfo.getAmount());
        iouDTO.setPactRate(iouInfo.getPactRate());
        iouDTO.setOuttimeMultiple(iouInfo.getOuttimeMultiple());
        iouDTO.setAppropriationMultiple(iouInfo.getAppropriationMultiple());
        iouDTO.setAccrualRepay(iouInfo.getAccrualRepay());
        iouDTO.setLoanRepay(iouInfo.getLoanRepay());
        iouDTO.setLevelType(iouInfo.getLevelType());
        iouDTO.setOutDays(iouInfo.getOutDays());
        iouDTO.setLessCorpus(iouInfo.getLessCorpus());
        iouDTO.setAccrualArrears(iouInfo.getAccrualArrears());
        iouDTO.setPenalty(iouInfo.getPenalty());
        iouDTO.setArrears(iouInfo.getArrears());
        iouDTO.setEndAt(iouInfo.getEndAt());
        iouDTO.setWorth(iouInfo.getWorth());
        iouDTO.setAdvanceCorpus(iouInfo.getAdvanceCorpus());
        iouDTO.setEvaluateExcellent(iouInfo.getEvaluateExcellent());
        iouDTO.setEvaluateLevel(iouInfo.getEvaluateLevel());
        iouDTO.setMemo(iouInfo.getMemo());

        return iouDTO;
    }

    /**
     * 借据信息DTO转换成DAO
     * @param iouDTOList
     * @return
     */
    public static List<IOUInfo> toIouInfo(List<IouDTO> iouDTOList){
        if(CommonUtil.checkParam(iouDTOList)){
            return null;
        }
        List<IOUInfo> iouInfoList = new ArrayList<>();
        iouDTOList.forEach(iouDTO -> {
            iouInfoList.add(toIouInfo(iouDTO));
        });
        return iouInfoList;
    }

    /**
     * 借据信息DTO转换成DAO
     * @param iouDTO
     * @return
     */
    public static IOUInfo toIouInfo(IouDTO iouDTO){
        IOUInfo iouInfo = new IOUInfo();

        iouInfo.setId(iouDTO.getId());
        iouInfo.setIouNo(iouDTO.getIouNo());
        iouInfo.setName(iouDTO.getIouName());
        iouInfo.setLenderId(iouDTO.getLenderId());
        iouInfo.setType(iouDTO.getType());
        iouInfo.setAgency(iouDTO.getAgency());
        iouInfo.setIouCode(iouDTO.getIouCode());
        iouInfo.setLoanTime(iouDTO.getLoanTime());
        iouInfo.setLoanAttime(iouDTO.getLoanAtTime());
        iouInfo.setAmount(iouDTO.getAmount());
        iouInfo.setPactRate(iouDTO.getPactRate());
        iouInfo.setOuttimeMultiple(iouDTO.getOuttimeMultiple());
        iouInfo.setAppropriationMultiple(iouDTO.getAppropriationMultiple());
        iouInfo.setAccrualRepay(iouDTO.getAccrualRepay());
        iouInfo.setLoanRepay(iouDTO.getLoanRepay());
        iouInfo.setLevelType(iouDTO.getLevelType());
        iouInfo.setOutDays(iouDTO.getOutDays());
        iouInfo.setLessCorpus(iouDTO.getLessCorpus());
        iouInfo.setAccrualArrears(iouDTO.getAccrualArrears());
        iouInfo.setPenalty(iouDTO.getPenalty());
        iouInfo.setArrears(iouDTO.getArrears());
        iouInfo.setEndAt(iouDTO.getEndAt());
        iouInfo.setWorth(iouDTO.getWorth());
        iouInfo.setAdvanceCorpus(iouDTO.getAdvanceCorpus());
        iouInfo.setEvaluateExcellent(iouDTO.getEvaluateExcellent());
        iouInfo.setEvaluateLevel(iouDTO.getEvaluateLevel());
        iouInfo.setMemo(iouDTO.getMemo());

        return iouInfo;
    }
}
