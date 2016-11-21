package com.dqys.business.service.utils.asset;

import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.dto.asset.IouDTO;
import com.dqys.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/19.
 */
public class IouServiceUtils {

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
        iouDTO.setAgent(iouInfo.getOnAgent());
        iouDTO.setUrge(iouInfo.getOnCollection());
        iouDTO.setLawyer(iouInfo.getOnLawyer());

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
        if(iouDTO.getAgent()!=null){
            iouInfo.setOnAgent(iouDTO.getAgent());
        }
        if(iouDTO.getUrge()!=null){
            iouInfo.setOnCollection(iouDTO.getUrge());
        }
        if(iouDTO.getLawyer()!=null){
            iouInfo.setOnLawyer(iouDTO.getLawyer());
        }
        return iouInfo;
    }

    public static String checkData(IouDTO iouDTO){
        if(CommonUtil.checkParam(iouDTO,
                iouDTO.getIouName(), iouDTO.getType(), iouDTO.getAgency(),
                iouDTO.getIouCode(), iouDTO.getLoanTime(), iouDTO.getLoanAtTime(),
                iouDTO.getAmount(), iouDTO.getPactRate(), iouDTO.getOuttimeMultiple(),
                iouDTO.getAppropriationMultiple(), iouDTO.getAccrualRepay(), iouDTO.getLoanRepay(),
                iouDTO.getLevelType(), iouDTO.getLessCorpus(), iouDTO.getAccrualArrears(),
                iouDTO.getPenalty(), iouDTO.getArrears(), iouDTO.getEndAt(),
                iouDTO.getWorth(), iouDTO.getAdvanceCorpus(), iouDTO.getEvaluateExcellent(),
                iouDTO.getEvaluateLevel(), iouDTO.getLenderId())){
            return "参数错误";
        }
        if(!CommonUtil.isMoneyFormat(iouDTO.getAmount(), iouDTO.getPactRate(), iouDTO.getOuttimeMultiple(),
                iouDTO.getAccrualRepay(), iouDTO.getLoanRepay(), iouDTO.getLessCorpus(),
                iouDTO.getAccrualArrears(), iouDTO.getPenalty(), iouDTO.getArrears(),
                iouDTO.getWorth(), iouDTO.getAdvanceCorpus())){
            return "存在非法金额参数";
        }
        if(ExcellentTypeEnum.getExcellentTypeEnum(iouDTO.getEvaluateExcellent()) == null){
            return "评优参数错误";
        }
        if(!CommonUtil.isExist(CommonUtil.UPLETTER, iouDTO.getEvaluateLevel())){
            return "评级参数错误";
        }
        return null;
    }

    public static String checkData(List<IouDTO> iouDTOList){
        if(CommonUtil.checkParam(iouDTOList) || iouDTOList.size() == 0){
            return "借据信息为空";
        }
        for (IouDTO iouDTO : iouDTOList) {
            String data = checkData(iouDTO);
            if(data != null){
                return data;
            }
        }
        return null;
    }

}
