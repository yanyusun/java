package com.dqys.business.service.dto.asset;

import java.util.List;

/**
 * Created by Yvan on 16/8/17.
 */
public class LenderInsertDTO {

    private List<ContactDTO> contactDTOList; // 联系人信息
    private LenderDTO lenderDTO; // 借款人信息

    public List<ContactDTO> getContactDTOList() {
        return contactDTOList;
    }

    public void setContactDTOList(List<ContactDTO> contactDTOList) {
        this.contactDTOList = contactDTOList;
    }

    public LenderDTO getLenderDTO() {
        return lenderDTO;
    }

    public void setLenderDTO(LenderDTO lenderDTO) {
        this.lenderDTO = lenderDTO;
    }
}
