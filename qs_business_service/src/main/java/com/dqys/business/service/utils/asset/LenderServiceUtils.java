package com.dqys.business.service.utils.asset;

import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.pojo.asset.ContactInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.orm.query.asset.LenderQuery;
import com.dqys.business.service.dto.asset.ContactDTO;
import com.dqys.business.service.dto.asset.LenderDTO;
import com.dqys.business.service.dto.asset.LenderListDTO;
import com.dqys.business.service.query.asset.LenderListQuery;
import com.dqys.core.base.SysProperty;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Yvan on 16/7/19.
 */
public class LenderServiceUtils {

    public static final String PRE_LENDER_CODE = "JKR"; // 格式:JKR160612XXXX

    public static String createLenderNo() {
        return PRE_LENDER_CODE
                + DateFormatTool.format(DateFormatTool.DATE_FORMAT_6)
                + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 借款人基础信息DTO转化成DAO
     *
     * @param lenderDTO
     * @return
     */
    public static LenderInfo toLenderInfo(LenderDTO lenderDTO) {
        LenderInfo lenderInfo = new LenderInfo();

        lenderInfo.setId(lenderDTO.getId());
        lenderInfo.setLenderNo(lenderDTO.getLenderNo());
        lenderInfo.setAssetId(lenderDTO.getAssetId());
        lenderInfo.setStartAt(lenderDTO.getStartAt());
        lenderInfo.setEndAt(lenderDTO.getEndAt());
        lenderInfo.setOperator(lenderDTO.getOperatorId());
        lenderInfo.setAccrual(lenderDTO.getAccrual());
        lenderInfo.setLoan(lenderDTO.getLoan());
        lenderInfo.setAppraisal(lenderDTO.getAppraisal());
        lenderInfo.setLoanType(lenderDTO.getLoanType());
        lenderInfo.setLoanMode(lenderDTO.getLoanMode());
        lenderInfo.setLoanName(lenderDTO.getLoanName());
        lenderInfo.setEvaluateExcellent(lenderDTO.getEvaluateExcellent());
        lenderInfo.setEvaluateLevel(lenderDTO.getEvaluateLevel());
        lenderInfo.setDisposeMode(lenderDTO.getDisposeMode());
        lenderInfo.setTags(lenderDTO.getTags());
        lenderInfo.setUrgeType(lenderDTO.getUrgeType());
        lenderInfo.setEntrustBornType(lenderDTO.getEntrustBornType());
        lenderInfo.setEntrustBorn(lenderDTO.getEntrustBorn());
        lenderInfo.setGuaranteeType(lenderDTO.getGuaranteeType());
        lenderInfo.setGuaranteeMode(lenderDTO.getGuaranteeMode());
        lenderInfo.setGuaranteeSource(lenderDTO.getGuaranteeSource());
        lenderInfo.setIsGuaranteeConnection(lenderDTO.getIsGuaranteeConnection());
        lenderInfo.setGuarenteeEconomic(lenderDTO.getGuaranteeEconomic());
        lenderInfo.setIsLawsuit(lenderDTO.getIsLawsuit());
        lenderInfo.setIsDecision(lenderDTO.getIsDecision());
        lenderInfo.setRealUrgeTime(lenderDTO.getRealUrgeTime());
        lenderInfo.setPhoneUrgeTime(lenderDTO.getPhoneUrgeTime());
        lenderInfo.setEntrustUrgeTime(lenderDTO.getEntrustUrgeTime());
        lenderInfo.setCanContact(lenderDTO.getCanContact());
        lenderInfo.setCanPay(lenderDTO.getCanPay());
        lenderInfo.setIsWorth(lenderDTO.getIsWorth());
        lenderInfo.setMemo(lenderDTO.getMemo());

        return lenderInfo;
    }

    /**
     * 联系人DTO转化DAO
     *
     * @param contactDTO
     * @return
     */
    public static ContactInfo toContactInfo(ContactDTO contactDTO) {
        ContactInfo contactInfo = new ContactInfo();

        contactInfo.setMode(contactDTO.getMode());
        contactInfo.setModeId(contactDTO.getModeId());
        contactInfo.setName(contactDTO.getName());
        contactInfo.setCode(contactDTO.getCode());
        contactInfo.setType(contactDTO.getType());
        contactInfo.setAvg(contactDTO.getAvg());
        contactInfo.setGender(contactDTO.getGender());
        contactInfo.setIdCard(contactDTO.getIdcard());
        contactInfo.setCompany(contactDTO.getCompany());
        contactInfo.setMobile(contactDTO.getMobile());
        contactInfo.setHomeTel(contactDTO.getHomeTel());
        contactInfo.setOfficeTel(contactDTO.getOfficeTel());
        contactInfo.setEmail(contactDTO.getEmail());
        contactInfo.setProvince(contactDTO.getProvince());
        contactInfo.setCity(contactDTO.getCity());
        contactInfo.setDistrict(contactDTO.getDistrict());
        contactInfo.setAddress(contactDTO.getAddress());
        contactInfo.setOtherAddress(contactDTO.getOtherAddress());

        return contactInfo;
    }

    /**
     * 批量借款人基础信息DTO转换成DAO
     *
     * @param lenderDTOList
     * @return
     */
    public static List<LenderInfo> toLenderInfo(List<LenderDTO> lenderDTOList) {
        if (CommonUtil.checkParam(lenderDTOList)) {
            return null;
        }
        List<LenderInfo> lenderInfoList = new ArrayList<>();
        lenderDTOList.forEach(lenderDTO -> {
            lenderInfoList.add(toLenderInfo(lenderDTO));
        });
        return lenderInfoList;
    }

    /**
     * 批量联系人DTO转换DAO
     *
     * @param contactDTOList
     * @return
     */
    public static List<ContactInfo> toContactInfo(List<ContactDTO> contactDTOList) {
        if (CommonUtil.checkParam(contactDTOList)) {
            return null;
        }
        List<ContactInfo> contactInfoList = new ArrayList<>();
        contactDTOList.forEach(contactDTO -> {
            contactInfoList.add(toContactInfo(contactDTO));
        });
        return contactInfoList;
    }


    /**
     * 借款人基础信息DAO转换DTO
     *
     * @param lenderInfo
     * @return
     */
    public static LenderDTO toLenderDTO(LenderInfo lenderInfo) {
        LenderDTO lenderDTO = new LenderDTO();

        lenderDTO.setId(lenderInfo.getId());
        lenderDTO.setAssetId(lenderInfo.getAssetId());
        lenderDTO.setStartAt(lenderInfo.getStartAt());
        lenderDTO.setEndAt(lenderInfo.getEndAt());
        lenderDTO.setOperatorId(lenderInfo.getOperator());
        lenderDTO.setAccrual(lenderInfo.getAccrual());
        lenderDTO.setLoan(lenderInfo.getLoan());
        lenderDTO.setAppraisal(lenderInfo.getAppraisal());
        lenderDTO.setLoanType(lenderInfo.getLoanType());
        lenderDTO.setLoanMode(lenderInfo.getLoanMode());
        lenderDTO.setLoanName(lenderInfo.getLoanName());
        lenderDTO.setEvaluateExcellent(lenderInfo.getEvaluateExcellent());
        lenderDTO.setEvaluateLevel(lenderInfo.getEvaluateLevel());
        lenderDTO.setDisposeMode(lenderInfo.getDisposeMode());
        lenderDTO.setTags(lenderInfo.getTags());
        lenderDTO.setUrgeType(lenderInfo.getUrgeType());
        lenderDTO.setEntrustBornType(lenderInfo.getEntrustBornType());
        lenderDTO.setEntrustBorn(lenderInfo.getEntrustBorn());
        lenderDTO.setGuaranteeType(lenderInfo.getGuaranteeType());
        lenderDTO.setGuaranteeMode(lenderInfo.getGuaranteeMode());
        lenderDTO.setGuaranteeSource(lenderInfo.getGuaranteeSource());
        lenderDTO.setIsGuaranteeConnection(lenderInfo.getIsGuaranteeConnection());
        lenderDTO.setGuaranteeEconomic(lenderInfo.getGuarenteeEconomic());
        lenderDTO.setIsLawsuit(lenderInfo.getIsLawsuit());
        lenderDTO.setIsDecision(lenderInfo.getIsDecision());
        lenderDTO.setRealUrgeTime(lenderInfo.getRealUrgeTime());
        lenderDTO.setPhoneUrgeTime(lenderInfo.getPhoneUrgeTime());
        lenderDTO.setEntrustUrgeTime(lenderInfo.getEntrustUrgeTime());
        lenderDTO.setCanContact(lenderInfo.getCanContact());
        lenderDTO.setCanPay(lenderInfo.getCanPay());
        lenderDTO.setIsWorth(lenderInfo.getIsWorth());
        lenderDTO.setMemo(lenderInfo.getMemo());

        return lenderDTO;
    }

    /**
     * 批量借款人基础信息DAO转换DTO
     *
     * @param lenderInfoList
     * @return
     */
    public static List<LenderDTO> toLenderDTO(List<LenderInfo> lenderInfoList) {
        if (CommonUtil.checkParam(lenderInfoList)) {
            return null;
        }
        List<LenderDTO> lenderDTOList = new ArrayList<>();
        lenderInfoList.forEach(lenderInfo -> {
            lenderDTOList.add(toLenderDTO(lenderInfo));
        });
        return lenderDTOList;
    }

    /**
     * 批量联系人DAO转成DTO
     *
     * @param contactInfoList
     * @return
     */
    public static List<ContactDTO> toContactDTO(List<ContactInfo> contactInfoList) {
        if (CommonUtil.checkParam(contactInfoList)) {
            return null;
        }
        List<ContactDTO> contactDTOList = new ArrayList<>();
        contactInfoList.forEach(contactInfo -> {
            contactDTOList.add(toContactDTO(contactInfo));
        });
        return contactDTOList;
    }

    /**
     * 联系人DAO转化成DTO
     *
     * @param contactInfo
     * @return
     */
    public static ContactDTO toContactDTO(ContactInfo contactInfo) {
        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setMode(contactInfo.getMode());
        contactDTO.setModeId(contactInfo.getModeId());
        contactDTO.setName(contactInfo.getName());
        contactDTO.setCode(contactInfo.getCode());
        contactDTO.setType(contactInfo.getType());
        contactDTO.setAvg(contactInfo.getAvg());
        contactDTO.setGender(contactInfo.getGender());
        contactDTO.setIdcard(contactInfo.getIdCard());
        contactDTO.setCompany(contactInfo.getCompany());
        contactDTO.setMobile(contactInfo.getMobile());
        contactDTO.setHomeTel(contactInfo.getHomeTel());
        contactDTO.setOfficeTel(contactInfo.getOfficeTel());
        contactDTO.setEmail(contactInfo.getEmail());
        contactDTO.setProvince(contactInfo.getProvince());
        contactDTO.setCity(contactInfo.getCity());
        contactDTO.setDistrict(contactInfo.getDistrict());
        contactDTO.setAddress(contactInfo.getAddress());
        contactDTO.setOtherAddress(contactInfo.getOtherAddress());

        return contactDTO;
    }

    public static LenderQuery toLenderQuery(LenderQuery lenderQuery, LenderListQuery lenderListQuery){
        if(lenderQuery.getId() == null && lenderListQuery.getId() != null){
            lenderQuery.setId(lenderListQuery.getId());
        }
        if(lenderListQuery.getIsOutTime() != null){
            lenderQuery.setIsOutTime(true);
        }
        if(lenderListQuery.getIsOwn().equals(1)){
            if(lenderQuery.getOperator() != null && !lenderQuery.getOperator().equals(UserSession.getCurrent().getUserId())){
                lenderQuery.setOperator(0);
            }else {
                lenderQuery.setOperator(UserSession.getCurrent().getUserId());
            }
        }
        if(lenderListQuery.getIsAsset().equals(1)){
            lenderQuery.setIsAsset(true);
        }
        lenderQuery.setCanContact(lenderListQuery.getCanContact());
        lenderQuery.setIsWorth(lenderListQuery.getIsWorth());
        lenderQuery.setEntrustId(lenderListQuery.getEntrustId());

        return lenderQuery;
    }

    public static LenderListDTO toLenderListDTO(LenderInfo lenderInfo, ContactInfo contactInfo, List<TeamDTO> teamDTOList){
        LenderListDTO lenderListDTO = new LenderListDTO();

        lenderListDTO.setAvg(contactInfo.getAvg());
        lenderListDTO.setName(contactInfo.getName());
        lenderListDTO.setSex(contactInfo.getGender());
        lenderListDTO.setAvg(contactInfo.getAvg());
        lenderListDTO.setPhone(contactInfo.getMobile());
        lenderListDTO.setLenderId(lenderInfo.getId());
        lenderListDTO.setCode(lenderInfo.getLenderNo());
        lenderListDTO.setAccrual(lenderInfo.getAccrual());
        lenderListDTO.setLoan(lenderInfo.getLoan());
        lenderListDTO.setAppraisal(lenderInfo.getAppraisal());
        lenderListDTO.setUrgeType(lenderInfo.getUrgeType());
        lenderListDTO.setEvaluateExcellent(lenderInfo.getEvaluateExcellent());
        lenderListDTO.setEvaluateLevel(lenderInfo.getEvaluateLevel());
        Calendar calendar = Calendar.getInstance();
        lenderListDTO.setSourceType(lenderInfo.getEntrustBornType());// 来源类型
        if(calendar.getTime().compareTo(lenderInfo.getEndAt()) > 0){
            // 逾期
            lenderListDTO.setLessDay(0);
        }else{
            long timeMirr = lenderInfo.getEndAt().getTime() - calendar.getTime().getTime();
            lenderListDTO.setLessDay(Integer.parseInt(String.valueOf(timeMirr / 24 / 3600 / 1000)));
        }
        lenderListDTO.setLastFollow(lenderInfo.getFollowUpDate());
        lenderListDTO.setFollowTime(lenderInfo.getFollowUpTime());
        lenderListDTO.setMemo(lenderInfo.getMemo());
        lenderListDTO.setCoordinator(teamDTOList);
        if(teamDTOList != null && teamDTOList.size() > 0){
            teamDTOList.forEach(teamDTO -> {
                if (teamDTO.getRoleType().equals(TeammateReEnum.TYPE_AUXILIARY.getValue())) {
                    lenderListDTO.setBelongId(teamDTO.getUserId());
                    lenderListDTO.setBelongName(teamDTO.getRealName());
                }
            });
        }

        lenderListDTO.setManageTime(lenderInfo.getBelongFollowTimes());// 所属人催收次数
        if(lenderInfo.getIsCollection().equals(SysProperty.BOOLEAN_TRUE) &&
                lenderInfo.getIsLawsuit().equals(SysProperty.BOOLEAN_TRUE)){
            lenderListDTO.setStatus("常规催收司法化解同时进行");// 化解状态
        }else if(lenderInfo.getIsLawsuit().equals(SysProperty.BOOLEAN_TRUE)){
            lenderListDTO.setStatus("司法化解");
        }else if(lenderInfo.getIsAgent().equals(SysProperty.BOOLEAN_TRUE)){
            lenderListDTO.setStatus("市场处置");
        }else if(lenderInfo.getIsCollection().equals(SysProperty.BOOLEAN_TRUE)){
            lenderListDTO.setStatus("常规催收");
        }else{
            lenderListDTO.setStatus(null);
        }

        lenderListDTO.setMessage(null);// 消息

        return lenderListDTO;
    }

}