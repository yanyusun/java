package com.dqys.business.orm.query.common;

/**
 * Created by Yvan on 16/6/15.
 */
public class SourceQuery {

    private String mode; // 模块
    private Integer modeId; // 模块ID

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getModeId() {
        return modeId;
    }

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }
}
