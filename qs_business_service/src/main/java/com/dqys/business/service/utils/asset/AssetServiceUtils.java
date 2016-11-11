package com.dqys.business.service.utils.asset;

import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.query.asset.AssetQuery;
import com.dqys.business.service.constant.asset.AssetTypeEnum;
import com.dqys.business.service.constant.asset.ExcellentTypeEnum;
import com.dqys.business.service.constant.asset.LenderTypeEnum;
import com.dqys.business.service.dto.asset.AssetDTO;
import com.dqys.business.service.dto.asset.AssetLenderDTO;
import com.dqys.business.service.dto.asset.AssetListDTO;
import com.dqys.business.service.query.asset.AssetListQuery;
import com.dqys.core.model.TArea;
import com.dqys.core.utils.AreaTool;
import com.dqys.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Yvan on 16/6/16.
 */
public class AssetServiceUtils {
    /**
     * 将资产包DTO转换成DAO
     *
     * @param assetDTO
     * @return
     */
    public static AssetInfo toAssetInfo(AssetDTO assetDTO) {
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
        assetInfo.setRepayStatus(assetDTO.getRepayStatus());
        assetInfo.setEntrustName(assetDTO.getEntrustName());
        assetInfo.setAttribute(assetDTO.getAttribute());
        assetInfo.setMemo(assetDTO.getMemo());
        return assetInfo;
    }

    /**
     * 批量资产包DAO转成DTO
     *
     * @param assetInfoList
     * @return
     */
    public static List<AssetDTO> toAssetDTO(List<AssetInfo> assetInfoList) {
        if (CommonUtil.checkParam(assetInfoList)) {
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
     *
     * @param assetInfo
     * @return
     */
    public static AssetDTO toAssetDTO(AssetInfo assetInfo) {
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
        assetDTO.setRepayStatus(assetInfo.getRepayStatus());
        assetDTO.setEntrustName(assetInfo.getEntrustName());
        assetDTO.setAttribute(assetInfo.getAttribute());

        return assetDTO;
    }

    public static AssetQuery toAssetQuery(AssetListQuery assetListQuery, AssetQuery assetQuery) {
        // 分页
        if (assetListQuery.getPage() != null || assetListQuery.getPageCount() != null) {
            assetQuery.setIsPaging(true);
            if (assetListQuery.getPage() != null && assetListQuery.getPage() > 0) {
                if (assetListQuery.getPageCount() != null) {
                    assetQuery.setStartPageNum(
                            (assetListQuery.getPage() - 1) * assetListQuery.getPageCount());
                }
            } else {
                assetQuery.setStartPageNum(0);
            }
        }
        assetQuery.setPageSize(assetListQuery.getPageCount());

        assetQuery.setAreaId(assetListQuery.getAreaId());
        assetQuery.setType(assetListQuery.getType());
        assetQuery.setStartAt(assetListQuery.getStartAt());
        assetQuery.setEndAt(assetListQuery.getEndAt());
        assetQuery.setCode(assetListQuery.getCode());
        assetQuery.setEntrustName(assetListQuery.getEntrustName());

        return assetQuery;
    }

    /**
     * 将数据拼装成列表DTO
     *
     * @param assetInfo 资产包信息
     * @param userInfo  创建人信息
     * @param rate      业绩比例
     * @param belong    所属人名称
     * @return
     */
    public static AssetListDTO toAssetListDTO(AssetInfo assetInfo, TUserInfo userInfo, String rate,
                                              String belong, Integer followNum) {
        AssetListDTO assetListDTO = new AssetListDTO();

        assetListDTO.setId(assetInfo.getId());
        assetListDTO.setCode(assetInfo.getAssetNo());
        assetListDTO.setType(AssetTypeEnum.getAssetTypeEnum(assetInfo.getType()).getName());
        assetListDTO.setAccrual(assetInfo.getAccrual());
        assetListDTO.setLoan(assetInfo.getLoan());
        assetListDTO.setAppraisal(assetInfo.getAppraisal());
        assetListDTO.setName(assetInfo.getName());
        assetListDTO.setCreateAt(assetInfo.getCreateAt());
        assetListDTO.setRemark(assetInfo.getRemark());
        assetListDTO.setOperatorId(assetInfo.getOperator());
        assetListDTO.setEntrustName(assetInfo.getEntrustName());
        assetListDTO.setAttribute(assetInfo.getAttribute());
        assetListDTO.setLastFollowUpTime(assetInfo.getFollowUpTime());
        assetListDTO.setFlag(assetInfo.getStateflag().equals(0) ? 1 : 0);
        assetListDTO.setMemo(assetInfo.getMemo());
        if (assetInfo.getCity() != null) {
            TArea area = AreaTool.getAreaById(assetInfo.getDistrict());
            if (area != null) {
                assetListDTO.setCity(area.getLabel());
            }
        }
        long dayTime = assetInfo.getEndAt().getTime() - Calendar.getInstance().getTime().getTime();
        assetListDTO.setLessDay(dayTime > 0 ? dayTime / 1000 / 3600 / 24 : 0);

        assetListDTO.setRate(rate);
        assetListDTO.setOperator(userInfo.getUserName());
        assetListDTO.setCompany(assetInfo.getLoanOrganization());
        assetListDTO.setBelong(belong);
        assetListDTO.setFollowNum(followNum);

        return assetListDTO;
    }

    public static List<AssetLenderDTO> toAssetLenderDTO(List<LenderInfo> lenderInfoList) {
        if (lenderInfoList == null || lenderInfoList.size() == 0) {
            return null;
        }
        List<AssetLenderDTO> assetLenderDTOList = new ArrayList<>();
        lenderInfoList.forEach(lenderInfo -> {
            assetLenderDTOList.add(toAssetLenderDTO(lenderInfo));
        });
        return assetLenderDTOList;
    }

    public static AssetLenderDTO toAssetLenderDTO(LenderInfo lenderInfo) {
        if (lenderInfo == null) {
            return null;
        }
        AssetLenderDTO assetLenderDTO = new AssetLenderDTO();

        assetLenderDTO.setId(lenderInfo.getId());
        assetLenderDTO.setType(LenderTypeEnum.lender.getName());
        assetLenderDTO.setBorn(lenderInfo.getEntrustBornType() + "-" + lenderInfo.getEntrustBorn());
        assetLenderDTO.setExcellent(ExcellentTypeEnum.getExcellentTypeEnum(lenderInfo.getEvaluateExcellent()).getName());
        assetLenderDTO.setLevel(lenderInfo.getEvaluateLevel());
        assetLenderDTO.setDispose(lenderInfo.getDisposeMode());
        assetLenderDTO.setTag(lenderInfo.getTags());
        assetLenderDTO.setGuaranteeType(lenderInfo.getGuaranteeType());
        assetLenderDTO.setGuaranteeMode(lenderInfo.getGuaranteeMode());
        assetLenderDTO.setGuaranteeSource(lenderInfo.getGuaranteeSource());
        assetLenderDTO.setGuaranteeContact(booleanBack(lenderInfo.getIsGuaranteeConnection()));
        assetLenderDTO.setIsWorth(booleanBack(lenderInfo.getIsWorth()));
        assetLenderDTO.setIsLawsuit(booleanBack(lenderInfo.getIsLawsuit()));
        assetLenderDTO.setIsDecision(booleanBack(lenderInfo.getIsDecision()));
        assetLenderDTO.setRealUrgeTime(lenderInfo.getRealUrgeTime());
        assetLenderDTO.setPhoneUrgeTime(lenderInfo.getPhoneUrgeTime());
        assetLenderDTO.setEntrustTime(lenderInfo.getEntrustUrgeTime());
        assetLenderDTO.setRealUrgeTime(lenderInfo.getRealUrgeTime());
        assetLenderDTO.setCanContact(booleanBack(lenderInfo.getCanContact()));
        assetLenderDTO.setCanPay(booleanBack(lenderInfo.getCanPay()));
        assetLenderDTO.setMemo(lenderInfo.getRemark());

        return assetLenderDTO;
    }

    public static String booleanBack(Integer value) {
        if (value.equals(1)) {
            return "是";
        } else {
            return "否";
        }
    }

    public static String checkAssetData(AssetDTO assetDTO) {
        if (CommonUtil.checkParam(assetDTO,
                assetDTO.getType(), assetDTO.getStartAt(), assetDTO.getEndAt(),
                assetDTO.getAccrual(), assetDTO.getLoan(), assetDTO.getAppraisal(),
                assetDTO.getName(), assetDTO.getEvaluateExcellent(), assetDTO.getEvaluateLevel(),
                assetDTO.getProvince(), assetDTO.getAddress(), assetDTO.getLoanOrganization(),
                assetDTO.getLoanOrganizationDistrict(), assetDTO.getDisposeMode(), assetDTO.getEntrustName(), assetDTO.getAttribute())) {
            return "存在非法参数";
        }
        if (ExcellentTypeEnum.getExcellentTypeEnum(assetDTO.getEvaluateExcellent()) == null) {
            return "评优类型参数错误";
        }
        if (!CommonUtil.isExist(CommonUtil.UPLETTER, assetDTO.getEvaluateLevel())) {
            return "评级参数错误";
        }
        if (!CommonUtil.isMoneyFormat(assetDTO.getAccrual(), assetDTO.getLoan(), assetDTO.getAppraisal())) {
            return "存在非法金额参数";
        }
        if (AreaTool.validateArea(assetDTO.getProvince(), assetDTO.getCity(), assetDTO.getDistrict()) != null) {
            return "地区错误";
        }
        return null;
    }

    /**
     * 批量资产包DTO转换DAO
     *
     * @param assetDTOList
     * @return
     */
    public List<AssetInfo> toAssetInfo(List<AssetDTO> assetDTOList) {
        if (CommonUtil.checkParam(assetDTOList)) {
            return null;
        }
        List<AssetInfo> assetInfoList = new ArrayList<>();
        assetDTOList.forEach(assetDTO -> {
            assetInfoList.add(toAssetInfo(assetDTO));
        });
        return assetInfoList;
    }

}
