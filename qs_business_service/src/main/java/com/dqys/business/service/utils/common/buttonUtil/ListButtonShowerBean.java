package com.dqys.business.service.utils.common.buttonUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 列表页面模型
 * Created by yan on 16-9-24.
 */
public class ListButtonShowerBean {
    //右侧操作按钮
    private boolean hasRightButton = true;

    //协作器炒作操作按钮
    private boolean hasUserTeamButton = false;
    //协作器加入该按组按钮
    private boolean hasUserTeamButtonApply = false;
    //协作器邀请加入按钮
    private boolean hasUserTeamButtonAdd = false;

    //分配器操作按钮
    private boolean hasCompanyTeamButton = false;
    //分配器加入该按组按钮
    private boolean hasCompanyTeamButtonApply = false;
    //分配邀请加入按钮
    private boolean hasCompanyTeamButtonAdd = false;


    private List<String[]> rightButtonList = new LinkedList<>();


    public ListButtonShowerBean(List<String[]> rightButtonList,boolean hasRightButton, boolean hasUserTeamButton, boolean hasUserTeamButtonApply, boolean hasUserTeamButtonAdd, boolean hasCompanyTeamButton, boolean hasCompanyTeamButtonApply, boolean hasCompanyTeamButtonAdd) {
        this.hasRightButton = hasRightButton;
        this.hasUserTeamButton = hasUserTeamButton;
        this.hasUserTeamButtonApply = hasUserTeamButtonApply;
        this.hasUserTeamButtonAdd = hasUserTeamButtonAdd;
        this.hasCompanyTeamButton = hasCompanyTeamButton;
        this.hasCompanyTeamButtonApply = hasCompanyTeamButtonApply;
        this.hasCompanyTeamButtonAdd = hasCompanyTeamButtonAdd;
        this.rightButtonList = rightButtonList;
    }

    public ListButtonShowerBean(List<String[]> rightButtonList) {
        this.rightButtonList = rightButtonList;
    }

    public ListButtonShowerBean() {
        super();
    }


    public boolean isHasRightButton() {
        return hasRightButton;
    }

    public void setHasRightButton(boolean hasRightButton) {
        this.hasRightButton = hasRightButton;
    }

    public boolean isHasUserTeamButton() {
        return hasUserTeamButton;
    }

    public void setHasUserTeamButton(boolean hasUserTeamButton) {
        this.hasUserTeamButton = hasUserTeamButton;
    }

    public boolean isHasUserTeamButtonApply() {
        return hasUserTeamButtonApply;
    }

    public void setHasUserTeamButtonApply(boolean hasUserTeamButtonApply) {
        this.hasUserTeamButtonApply = hasUserTeamButtonApply;
    }

    public boolean isHasUserTeamButtonAdd() {
        return hasUserTeamButtonAdd;
    }

    public void setHasUserTeamButtonAdd(boolean hasUserTeamButtonAdd) {
        this.hasUserTeamButtonAdd = hasUserTeamButtonAdd;
    }

    public boolean isHasCompanyTeamButton() {
        return hasCompanyTeamButton;
    }

    public void setHasCompanyTeamButton(boolean hasCompanyTeamButton) {
        this.hasCompanyTeamButton = hasCompanyTeamButton;
    }

    public boolean isHasCompanyTeamButtonApply() {
        return hasCompanyTeamButtonApply;
    }

    public void setHasCompanyTeamButtonApply(boolean hasCompanyTeamButtonApply) {
        this.hasCompanyTeamButtonApply = hasCompanyTeamButtonApply;
    }

    public boolean isHasCompanyTeamButtonAdd() {
        return hasCompanyTeamButtonAdd;
    }

    public void setHasCompanyTeamButtonAdd(boolean hasCompanyTeamButtonAdd) {
        this.hasCompanyTeamButtonAdd = hasCompanyTeamButtonAdd;
    }

    public List<String[]> getRightButtonList() {
        return rightButtonList;
    }

    public void setRightButtonList(List<String[]> rightButtonList) {
        this.rightButtonList = rightButtonList;
    }

}
