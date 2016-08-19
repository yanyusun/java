package com.dqys.business.service.dto.common;

import com.dqys.core.base.BaseSelectonDTO;

import java.util.List;

/**
 * Created by Yvan on 16/8/19.
 */
public class SelectDTOList extends BaseSelectonDTO {

    private List<SelectDTOList> children;

    public List<SelectDTOList> getChildren() {
        return children;
    }

    public void setChildren(List<SelectDTOList> children) {
        this.children = children;
    }
}
