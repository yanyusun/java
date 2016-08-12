package com.dqys.resource.extend;

import com.dqys.core.model.TArea;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yvan on 16/8/3.
 */
public class AreaList extends TArea implements Serializable {

    private List<AreaList> children; // 子项

    public List<AreaList> getChildren() {
        return children;
    }

    public void setChildren(List<AreaList> children) {
        this.children = children;
    }
}
