package com.dqys.business.service.dto.asset;

/**
 * Created by Yvan on 16/7/11.
 *
 * @apiDefine AssetLender
 */
public class AssetLenderDTO {

    private Integer id;
    private String name;
    private String type;
    private String born;
    private String assetName;
    private String excellent;
    private String level;
    private String dispose;
    private String tag;
    private String guaranteeType;
    private String guaranteeMode;
    private String guaranteeSource;
    private String guaranteeEconomic;
    private String guaranteeContact;
    private String isWorth;
    private String isLawsuit;
    private String isDecision;
    private Integer realUrgeTime;
    private Integer phoneUrgeTime;
    private Integer entrustTime;
    private String canContact;
    private String canPay;
    private String memo;
    private StatisticsLender statisticsLender;

    public StatisticsLender getStatisticsLender() {
        return statisticsLender;
    }

    public void setStatisticsLender(StatisticsLender statisticsLender) {
        this.statisticsLender = statisticsLender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getExcellent() {
        return excellent;
    }

    public void setExcellent(String excellent) {
        this.excellent = excellent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDispose() {
        return dispose;
    }

    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    public String getGuaranteeSource() {
        return guaranteeSource;
    }

    public void setGuaranteeSource(String guaranteeSource) {
        this.guaranteeSource = guaranteeSource;
    }

    public String getGuaranteeEconomic() {
        return guaranteeEconomic;
    }

    public void setGuaranteeEconomic(String guaranteeEconomic) {
        this.guaranteeEconomic = guaranteeEconomic;
    }

    public String getGuaranteeContact() {
        return guaranteeContact;
    }

    public void setGuaranteeContact(String guaranteeContact) {
        this.guaranteeContact = guaranteeContact;
    }

    public String getIsWorth() {
        return isWorth;
    }

    public void setIsWorth(String isWorth) {
        this.isWorth = isWorth;
    }

    public String getIsLawsuit() {
        return isLawsuit;
    }

    public void setIsLawsuit(String isLawsuit) {
        this.isLawsuit = isLawsuit;
    }

    public String getIsDecision() {
        return isDecision;
    }

    public void setIsDecision(String isDecision) {
        this.isDecision = isDecision;
    }

    public Integer getRealUrgeTime() {
        return realUrgeTime;
    }

    public void setRealUrgeTime(Integer realUrgeTime) {
        this.realUrgeTime = realUrgeTime;
    }

    public Integer getPhoneUrgeTime() {
        return phoneUrgeTime;
    }

    public void setPhoneUrgeTime(Integer phoneUrgeTime) {
        this.phoneUrgeTime = phoneUrgeTime;
    }

    public Integer getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Integer entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getCanContact() {
        return canContact;
    }

    public void setCanContact(String canContact) {
        this.canContact = canContact;
    }

    public String getCanPay() {
        return canPay;
    }

    public void setCanPay(String canPay) {
        this.canPay = canPay;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
