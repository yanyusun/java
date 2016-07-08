package com.dqys.business.service.utils.asset;

import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;
import com.dqys.business.service.dto.asset.*;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.core.utils.DateFormatTool;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yvan on 16/6/16.
 */
public class AssetServiceUtils {

    public static final String PRE_ASSET_CODE = "QS"; // 格式:QS160612XXXX
    public static final String PRE_PAWN_CODE = "抵"; // 格式:抵AXXXXXX
    public static final String PRE_LENDER_CODE = "JKR"; // 格式:JKR160612XXXX
    public static final String MIDDLE_IOU_CODE = "-借"; // 格式:XXX-借01
    public static final String[] UPLETTER = {"A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N",
            "O","P","Q","R","S","T",
            "U","V","W","X","Y","Z"};
    public static final String[] LOWERLETTER = {"a","b","c","d","e","f","g",
            "h","i","j","k","l","m","n",
            "o","p","q","r","s","t",
            "u","v","w","x","y","z"};

    /**
     * 批量资产包DTO转换DAO
     * @param assetDTOList
     * @return
     */
    public List<AssetInfo> toAssetInfo(List<AssetDTO> assetDTOList){
        if(CommonUtil.checkParam(assetDTOList)){
            return null;
        }
        List<AssetInfo> assetInfoList = new ArrayList<>();
        assetDTOList.forEach(assetDTO -> {
            assetInfoList.add(toAssetInfo(assetDTO));
        });
        return assetInfoList;
    }

    /**
     * 将资产包DTO转换成DAO
     * @param assetDTO
     * @return
     */
    public static AssetInfo toAssetInfo(AssetDTO assetDTO){
        AssetInfo assetInfo = new AssetInfo();

        assetInfo.setId(assetDTO.getId());
        assetInfo.setAssetNo(assetDTO.getAssetNo());
        assetInfo.setStartAt(assetDTO.getStartAt());
        assetInfo.setEndAt(assetDTO.getEndAt());
        assetInfo.setAccrual(assetDTO.getAccrual());
        assetInfo.setDisposeMode(assetDTO.getDisposeMode());
        assetInfo.setType(assetDTO.getType());
        assetInfo.setOperator(assetDTO.getOperatorId());
        assetInfo.setLoan(assetDTO.getLoan());
        assetInfo.setAppraisal(assetDTO.getAppraisal());
        assetInfo.setName(assetDTO.getName());
        assetInfo.setEvaluateExcellent(assetDTO.getEvaluateExcellent());
        assetInfo.setEvaluateLevel(assetDTO.getEvaluateLevel());
        assetInfo.setProvince(assetDTO.getProvince());
        assetInfo.setCity(assetDTO.getCity());
        assetInfo.setDistrict(assetDTO.getDistrict());
        assetInfo.setAddress(assetDTO.getAddress());
        assetInfo.setLoanOrganization(assetDTO.getLoanOrganization());
        assetInfo.setLoanOrganizationDistrict(assetDTO.getLoanOrganizationDistrict());
        assetInfo.setTags(assetDTO.getTags());
        assetInfo.setIsshow(assetDTO.getIsshow());

        return assetInfo;
    }

    /**
     * 批量资产包DAO转成DTO
     * @param assetInfoList
     * @return
     */
    public static List<AssetDTO> toAssetDTO(List<AssetInfo> assetInfoList){
        if(CommonUtil.checkParam(assetInfoList)){
            return null;
        }
        List<AssetDTO> assetDTOList = new ArrayList<>();
        assetInfoList.forEach(assetInfo -> {
            assetDTOList.add(toAssetDTO(assetInfo));
        });
        return assetDTOList;
    }

    /**
     * 资产包DAO转成DTO
     * @param assetInfo
     * @return
     */
    public static AssetDTO toAssetDTO(AssetInfo assetInfo){
        AssetDTO assetDTO = new AssetDTO();

        assetDTO.setId(assetInfo.getId());
        assetDTO.setAssetNo(assetInfo.getAssetNo());
        assetDTO.setStartAt(assetInfo.getStartAt());
        assetDTO.setEndAt(assetInfo.getEndAt());
        assetDTO.setAccrual(assetInfo.getAccrual());
        assetDTO.setDisposeMode(assetInfo.getDisposeMode());
        assetDTO.setType(assetInfo.getType());
        assetDTO.setOperatorId(assetInfo.getOperator());
        assetDTO.setLoan(assetInfo.getLoan());
        assetDTO.setAppraisal(assetInfo.getAppraisal());
        assetDTO.setName(assetInfo.getName());
        assetDTO.setEvaluateExcellent(assetInfo.getEvaluateExcellent());
        assetDTO.setEvaluateLevel(assetInfo.getEvaluateLevel());
        assetDTO.setProvince(assetInfo.getProvince());
        assetDTO.setCity(assetInfo.getCity());
        assetDTO.setDistrict(assetInfo.getDistrict());
        assetDTO.setAddress(assetInfo.getAddress());
        assetDTO.setLoanOrganization(assetInfo.getLoanOrganization());
        assetDTO.setLoanOrganizationDistrict(assetInfo.getLoanOrganizationDistrict());
        assetDTO.setTags(assetInfo.getTags());
        assetDTO.setIsshow(assetInfo.getIsshow());

        return assetDTO;
    }

    /**
     * 批量联系人DTO转换DAO
     * @param contactDTOList
     * @return
     */
    public static List<ContactInfo> toContactInfo(List<ContactDTO> contactDTOList){
        if(CommonUtil.checkParam(contactDTOList)){
            return null;
        }
        List<ContactInfo> contactInfoList = new ArrayList<>();
        contactDTOList.forEach(contactDTO -> {
            contactInfoList.add(toContactInfo(contactDTO));
        });
        return contactInfoList;
    }

    /**
     * 联系人DTO转化DAO
     * @param contactDTO
     * @return
     */
    public static ContactInfo toContactInfo(ContactDTO contactDTO){
        ContactInfo contactInfo = new ContactInfo();

        contactInfo.setMode(contactDTO.getMode());
        contactInfo.setModeId(contactDTO.getModeId());
        contactInfo.setName(contactDTO.getName());
        contactInfo.setCode(contactDTO.getCode());
        contactInfo.setType(contactDTO.getType());
        contactInfo.setAvg(contactDTO.getAvg());
        contactInfo.setGender(contactDTO.getGender());
        contactInfo.setIdcard(contactDTO.getIdcard());
        contactInfo.setCompany(contactDTO.getCompany());
        contactInfo.setMobile(contactDTO.getMobile());
        contactInfo.setHomeTel(contactDTO.getHomeTel());
        contactInfo.setOfficeTel(contactDTO.getOfficeTel());
        contactInfo.setEmail(contactDTO.getEmail());
        contactInfo.setProvince(contactDTO.getProvince());
        contactInfo.setCity(contactDTO.getCity());
        contactInfo.setDistrict(contactDTO.getDistrict());
        contactInfo.setAddress(contactDTO.getAddress());

        return contactInfo;
    }

    /**
     * 批量联系人DAO转成DTO
     * @param contactInfoList
     * @return
     */
    public static List<ContactDTO> toContactDTO(List<ContactInfo> contactInfoList){
        if(CommonUtil.checkParam(contactInfoList)){
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
     * @param contactInfo
     * @return
     */
    public static ContactDTO toContactDTO(ContactInfo contactInfo){
        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setMode(contactInfo.getMode());
        contactDTO.setModeId(contactInfo.getModeId());
        contactDTO.setName(contactInfo.getName());
        contactDTO.setCode(contactInfo.getCode());
        contactDTO.setType(contactInfo.getType());
        contactDTO.setAvg(contactInfo.getAvg());
        contactDTO.setGender(contactInfo.getGender());
        contactDTO.setIdcard(contactInfo.getIdcard());
        contactDTO.setCompany(contactInfo.getCompany());
        contactDTO.setMobile(contactInfo.getMobile());
        contactDTO.setHomeTel(contactInfo.getHomeTel());
        contactDTO.setOfficeTel(contactInfo.getOfficeTel());
        contactDTO.setEmail(contactInfo.getEmail());
        contactDTO.setProvince(contactInfo.getProvince());
        contactDTO.setCity(contactInfo.getCity());
        contactDTO.setDistrict(contactInfo.getDistrict());
        contactDTO.setAddress(contactInfo.getAddress());

        return contactDTO;
    }

    /**
     * 批量借款人基础信息DTO转换成DAO
     * @param lenderDTOList
     * @return
     */
    public static List<LenderInfo> toLenderInfo(List<LenderDTO> lenderDTOList){
        if(CommonUtil.checkParam(lenderDTOList)){
            return null;
        }
        List<LenderInfo> lenderInfoList = new ArrayList<>();
        lenderDTOList.forEach(lenderDTO -> {
            lenderInfoList.add(toLenderInfo(lenderDTO));
        });
        return lenderInfoList;
    }

    /**
     * 借款人基础信息DTO转化成DAO
     * @param lenderDTO
     * @return
     */
    public static LenderInfo toLenderInfo(LenderDTO lenderDTO){
        LenderInfo lenderInfo = new LenderInfo();

        lenderInfo.setId(lenderDTO.getId());
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
        lenderInfo.setGuarenteeEconomic(lenderDTO.getGuarenteeEconomic());
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
     * 批量借款人基础信息DAO转换DTO
     * @param lenderInfoList
     * @return
     */
    public static List<LenderDTO> toLenderDTO(List<LenderInfo> lenderInfoList){
        if(CommonUtil.checkParam(lenderInfoList)){
            return null;
        }
        List<LenderDTO> lenderDTOList = new ArrayList<>();
        lenderInfoList.forEach(lenderInfo -> {
            lenderDTOList.add(toLenderDTO(lenderInfo));
        });
        return lenderDTOList;
    }

    /**
     * 借款人基础信息DAO转换DTO
     * @param lenderInfo
     * @return
     */
    public static LenderDTO toLenderDTO(LenderInfo lenderInfo){
        LenderDTO lenderDTO = new LenderDTO();

        lenderDTO.setId(lenderInfo.getId());
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
        lenderDTO.setGuarenteeEconomic(lenderInfo.getGuarenteeEconomic());
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
     * 量化转换抵押物DTO成DAO
     * @param pawnDTOList
     * @return
     */
    public static List<PawnInfo> toPawnInfo(List<PawnDTO> pawnDTOList){
        if(CommonUtil.checkParam(pawnDTOList)){
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
     * @param pawnDTO
     * @return
     */
    public static PawnInfo toPawnInfo(PawnDTO pawnDTO){
        PawnInfo pawnInfo = new PawnInfo();

        pawnInfo.setId(pawnDTO.getId());
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

        return pawnInfo;
    }

    public static List<PawnDTO> toPawnDTO(List<PawnInfo> pawnInfoList){
        if(CommonUtil.checkParam(pawnInfoList)){
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
     * @param pawnInfo
     * @return
     */
    public static PawnDTO toPawnDTO(PawnInfo pawnInfo){
        PawnDTO pawnDTO = new PawnDTO();

        pawnDTO.setId(pawnInfo.getId());
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

        return pawnDTO;
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
        iouInfo.setLenderId(iouDTO.getLenderId());
        iouInfo.setType(iouDTO.getType());
        iouInfo.setAgency(iouDTO.getAgency());
        iouInfo.setIouCode(iouDTO.getIouCode());
        iouInfo.setLoanTime(iouDTO.getLoanTime());
        iouInfo.setLoanAttime(iouDTO.getLoanAttime());
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
        iouDTO.setLenderId(iouInfo.getLenderId());
        iouDTO.setType(iouInfo.getType());
        iouDTO.setAgency(iouInfo.getAgency());
        iouDTO.setIouCode(iouInfo.getIouCode());
        iouDTO.setLoanTime(iouInfo.getLoanTime());
        iouDTO.setLoanAttime(iouInfo.getLoanAttime());
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

    public static AssetQuery toAssetQuery(AssetListQuery assetListQuery){
        AssetQuery assetQuery = new AssetQuery();

        if(assetListQuery.getPage() != null || assetListQuery.getPageCount() != null){
            assetQuery.setIsPaging(true);
            if(assetListQuery.getPage() != null && assetListQuery.getPage() > 0){
                if(assetListQuery.getPageCount() != null){
                    assetQuery.setStartPageNum(
                            (assetListQuery.getPage() - 1) * assetListQuery.getPageCount());
                }
            }else{
                assetQuery.setStartPageNum(0);
            }
        }
        assetQuery.setPageSize(assetListQuery.getPageCount());

        assetQuery.setAreaId(assetListQuery.getAreaId());
        assetQuery.setType(assetListQuery.getType());
        assetQuery.setStartAt(assetListQuery.getStartAt());
        assetQuery.setEndAt(assetListQuery.getEndAt());
        assetQuery.setCode(assetListQuery.getCode());
        assetQuery.setOperator(assetListQuery.getOperator());

        return assetQuery;
    }

    public static List<AssetListDTO> toAssetListDTO(List<AssetInfo> assetInfoList){
        List<AssetListDTO> assetListDTOList = new ArrayList<>();
        assetInfoList.forEach(assetInfo -> {
            assetListDTOList.add(toAssetListDTO(assetInfo));
        });
        return assetListDTOList;
    }

    public static AssetListDTO toAssetListDTO(AssetInfo assetInfo){
        AssetListDTO assetListDTO = new AssetListDTO();

        assetListDTO.setId(assetInfo.getId());
        assetListDTO.setCode(assetInfo.getAssetNo());
        assetListDTO.setType(assetInfo.getType());
        assetListDTO.setAccrual(assetInfo.getAccrual());
        assetListDTO.setLoan(assetInfo.getLoan());
        assetListDTO.setAppraisal(assetInfo.getAppraisal());
        assetListDTO.setName(assetInfo.getName());
        assetListDTO.setCreateAt(assetInfo.getCreateAt());
        assetListDTO.setRemark(assetInfo.getRemark());
        assetListDTO.setFlag(assetInfo.getStateflag().equals(0) ? 1 : 0);
        assetListDTO.setCity(AreaTool.getAreaById(assetInfo.getCity()).getName());

        long dayTime = assetInfo.getEndAt().getTime()- Calendar.getInstance().getTime().getTime();
        assetListDTO.setLessDay(dayTime > 0 ? dayTime / 1000 / 3600 / 24 : 0);

        // TODO 需要补充
        assetListDTO.setRate(null);
        assetListDTO.setOperator(null);

        return assetListDTO;
    }

    /**
     * 生成资产包号
     * @return
     */
    public static String createAssetCode(){
        return PRE_ASSET_CODE
                + DateFormatTool.format(DateFormatTool.DATE_FORMAT_6)
                + RandomStringUtils.randomNumeric(4);
    }

    /**
     * 生成借据号
     * @param name
     * @return
     */
    public static String createIouCode(String name){
        return name + MIDDLE_IOU_CODE + RandomStringUtils.randomNumeric(6);
    }

    /**
     * 生成抵押物号
     * @return
     */
    public static String createPawnCode(){
        return PRE_PAWN_CODE
                + UPLETTER[Integer.valueOf(RandomStringUtils.randomNumeric(3))%24]
                + RandomStringUtils.randomNumeric(6);
    }

}
