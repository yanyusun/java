package com.dqys.business.service.utils.common.buttonUtil;

/**
 * 列表页面模型
 * Created by yan on 16-9-24.
 */
public class ListButtonShowerBean {

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


    public ListButtonShowerBean( boolean hasUserTeamButton, boolean hasUserTeamButtonApply, boolean hasUserTeamButtonAdd, boolean hasCompanyTeamButton, boolean hasCompanyTeamButtonApply, boolean hasCompanyTeamButtonAdd) {
        this.hasUserTeamButton = hasUserTeamButton;
        this.hasUserTeamButtonApply = hasUserTeamButtonApply;
        this.hasUserTeamButtonAdd = hasUserTeamButtonAdd;
        this.hasCompanyTeamButton = hasCompanyTeamButton;
        this.hasCompanyTeamButtonApply = hasCompanyTeamButtonApply;
        this.hasCompanyTeamButtonAdd = hasCompanyTeamButtonAdd;
    }



    public ListButtonShowerBean() {
        super();
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


}
