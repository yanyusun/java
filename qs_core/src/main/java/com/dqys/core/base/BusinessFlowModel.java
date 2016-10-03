package com.dqys.core.base;

/**
 * Created by yan on 16-10-4.
 */
public class BusinessFlowModel extends BaseModel {
    private Integer onCollection;//是否可以催收:0可以1不能',
    private Integer onLawyer;//是否可以司法处置:0可以1不能',
    private Integer onAgent;

    public Integer getOnCollection() {
        return onCollection;
    }

    public void setOnCollection(Integer onCollection) {
        this.onCollection = onCollection;
    }

    public Integer getOnLawyer() {
        return onLawyer;
    }

    public void setOnLawyer(Integer onLawyer) {
        this.onLawyer = onLawyer;
    }

    public Integer getOnAgent() {
        return onAgent;
    }

    public void setOnAgent(Integer onAgent) {
        this.onAgent = onAgent;
    }
}
