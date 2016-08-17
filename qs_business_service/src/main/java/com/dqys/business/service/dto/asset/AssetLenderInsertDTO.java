package com.dqys.business.service.dto.asset;

import java.util.List;

/**
 * Created by Yvan on 16/8/16.
 */
public class AssetLenderInsertDTO {

    private Integer id;
    private LenderDTO lenderDTO; // 借款人基础属性
    private List<ContactDTO> contactDTOList; // 联系人集合
    private List<IouDTO> iouDTOList; // 借据集合
    private List<PawnDTO> pawnDTOList; // 抵押物集合


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LenderDTO getLenderDTO() {
        return lenderDTO;
    }

    public void setLenderDTO(LenderDTO lenderDTO) {
        this.lenderDTO = lenderDTO;
    }

    public List<ContactDTO> getContactDTOList() {
        return contactDTOList;
    }

    public void setContactDTOList(List<ContactDTO> contactDTOList) {
        this.contactDTOList = contactDTOList;
    }

    public List<IouDTO> getIouDTOList() {
        return iouDTOList;
    }

    public void setIouDTOList(List<IouDTO> iouDTOList) {
        this.iouDTOList = iouDTOList;
    }

    public List<PawnDTO> getPawnDTOList() {
        return pawnDTOList;
    }

    public void setPawnDTOList(List<PawnDTO> pawnDTOList) {
        this.pawnDTOList = pawnDTOList;
    }
}
