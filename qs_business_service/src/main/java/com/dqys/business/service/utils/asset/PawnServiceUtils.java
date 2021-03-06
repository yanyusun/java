package com.dqys.business.service.utils.asset;

import com.dqys.business.orm.pojo.asset.PawnInfo;
import com.dqys.business.service.dto.asset.PawnDTO;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan on 16/7/19.
 */
public class PawnServiceUtils {

    /**
     * 量化转换抵押物DTO成DAO
     *
     * @param pawnDTOList
     * @return
     */
    public static List<PawnInfo> toPawnInfo(List<PawnDTO> pawnDTOList) {
        if (CommonUtil.checkParam(pawnDTOList)) {
            return null;
        }
        List<PawnInfo> pawnInfoList = new ArrayList<>();
        pawnDTOList.forEach(pawnDTO -> {
            pawnInfoList.add(toPawnInfo(pawnDTO));
        });
        return pawnInfoList;
    }

    /**
     * 抵押物信息装换成DAO
     *
     * @param pawnDTO
     * @return
     */
    public static PawnInfo toPawnInfo(PawnDTO pawnDTO) {
        PawnInfo pawnInfo = new PawnInfo();
        pawnInfo.setId(pawnDTO.getId());
        pawnInfo.setName(pawnDTO.getPawnName());
        pawnInfo.setLenderId(pawnDTO.getLenderId());
        pawnInfo.setPawnNo(pawnDTO.getPawnNo());
        pawnInfo.setAmount(pawnDTO.getAmount());
        pawnInfo.setType(pawnDTO.getType());
        pawnInfo.setEvaluateExcellent(pawnDTO.getEvaluateExcellent());
        pawnInfo.setEvaluateLevel(pawnDTO.getEvaluateLevel());
        pawnInfo.setSize(pawnDTO.getSize());
        pawnInfo.setProvince(pawnDTO.getProvince());
        pawnInfo.setCity(pawnDTO.getCity());
        pawnInfo.setDistrict(pawnDTO.getDistrict());
        pawnInfo.setAddress(pawnDTO.getAddress());
        pawnInfo.setPawnRate(pawnDTO.getPawnRate());
        pawnInfo.setDisposeStatus(pawnDTO.getDisposeStatus());
        pawnInfo.setWorth(pawnDTO.getWorth());
        pawnInfo.setMemo(pawnDTO.getMemo());
        pawnInfo.setProvince(pawnDTO.getProvince());
        pawnInfo.setCity(pawnDTO.getCity());
        pawnInfo.setDistrict(pawnDTO.getDistrict());
        if (pawnDTO.getAgent() != null) {
            pawnInfo.setOnAgent(pawnDTO.getAgent());
        }
        if (pawnDTO.getUrge() != null) {
            pawnInfo.setOnCollection(pawnDTO.getUrge());
        }
        if (pawnDTO.getLawyer() != null) {
            pawnInfo.setOnLawyer(pawnDTO.getLawyer());
        }
        return pawnInfo;
    }

    public static List<PawnDTO> toPawnDTO(List<PawnInfo> pawnInfoList) {
        if (CommonUtil.checkParam(pawnInfoList)) {
            return null;
        }
        List<PawnDTO> pawnDTOList = new ArrayList<>();
        pawnInfoList.forEach(pawnInfo -> {
            pawnDTOList.add(toPawnDTO(pawnInfo));
        });
        return pawnDTOList;
    }

    /**
     * 抵押物DAO转成DTO
     *
     * @param pawnInfo
     * @return
     */
    public static PawnDTO toPawnDTO(PawnInfo pawnInfo) {
        PawnDTO pawnDTO = new PawnDTO();

        pawnDTO.setId(pawnInfo.getId());
        pawnDTO.setPawnName(pawnInfo.getName());
        pawnDTO.setLenderId(pawnInfo.getLenderId());
        pawnDTO.setPawnNo(pawnInfo.getPawnNo());
        pawnDTO.setAmount(pawnInfo.getAmount());
        pawnDTO.setType(pawnInfo.getType());
        pawnDTO.setEvaluateExcellent(pawnInfo.getEvaluateExcellent());
        pawnDTO.setEvaluateLevel(pawnInfo.getEvaluateLevel());
        pawnDTO.setSize(pawnInfo.getSize());
        pawnDTO.setProvince(pawnInfo.getProvince());
        pawnDTO.setCity(pawnInfo.getCity());
        pawnDTO.setDistrict(pawnInfo.getDistrict());
        pawnDTO.setAddress(pawnInfo.getAddress());
        pawnDTO.setPawnRate(pawnInfo.getPawnRate());
        pawnDTO.setDisposeStatus(pawnInfo.getDisposeStatus());
        pawnDTO.setWorth(pawnInfo.getWorth());
        pawnDTO.setMemo(pawnInfo.getMemo());
        pawnDTO.setAgent(pawnInfo.getOnAgent());
        pawnDTO.setUrge(pawnInfo.getOnCollection());
        pawnDTO.setLawyer(pawnInfo.getOnLawyer());

        return pawnDTO;
    }

    public static String checkData(PawnDTO pawnDTO) {
        if (CommonUtil.checkParam(pawnDTO,
                pawnDTO.getPawnName(), pawnDTO.getAmount(), pawnDTO.getType(),
                pawnDTO.getEvaluateExcellent(), pawnDTO.getEvaluateLevel(), pawnDTO.getSize(),
                pawnDTO.getProvince(), pawnDTO.getCity(), pawnDTO.getDistrict(),
                pawnDTO.getAddress(), pawnDTO.getPawnRate(), pawnDTO.getWorth(),
                pawnDTO.getDisposeStatus(), pawnDTO.getLenderId())) {
            return "参数错误";
        }
        if (!CommonUtil.isMoneyFormat(pawnDTO.getAmount(), pawnDTO.getPawnRate(), pawnDTO.getWorth())) {
            return "存在非法金额参数";
        }
        String areaData = AreaTool.validateArea(pawnDTO.getProvince(), pawnDTO.getCity(), pawnDTO.getDistrict());
        if (areaData != null) {
            return areaData;
        }
        return null;
    }

    public static String checkData(List<PawnDTO> pawnDTOList) {
        if (CommonUtil.checkParam(pawnDTOList) || pawnDTOList.size() == 0) {
            return "抵押物参数错误";
        }
        for (PawnDTO pawnDTO : pawnDTOList) {
            String data = checkData(pawnDTO);
            if (data != null) {
                return data;
            }
        }
        return null;
    }

}
