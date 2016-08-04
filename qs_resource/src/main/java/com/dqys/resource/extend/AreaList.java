package com.dqys.resource.extend;

import com.dqys.core.model.TArea;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yvan on 16/8/3.
 */
public class AreaList extends TArea implements Serializable {

    private List<AreaList> areaList; // 子项

    public List<AreaList> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaList> areaList) {
        this.areaList = areaList;
    }
}
