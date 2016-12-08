package com.dqys.business.service.utils.operType;

import static com.dqys.business.service.constant.ObjectEnum.LenderEnum.*;

/**
 * nav对应借款人列表所有权限
 * <p>
 * Created by yan on 16-11-30.
 */
public class LenderRowOperTypeNavBean extends OperTypeNavBean {
    public Integer[] nav0 = {OPERATION_LOG.getValue()};//待接收
    public Integer[] nav1 = {OPERATION_LOG.getValue()};//待申请
    public Integer[] nav2 = {OPERATION_LOG.getValue(),INVALID_SET.getValue(),PAUSE.getValue(),UPDATE_EDIT.getValue()};//正在处置
    public Integer[] nav3 = {OPERATION_LOG.getValue()};//聚焦
    public Integer[] nav4 = {OPERATION_LOG.getValue()};//当月
    public Integer[] nav5 = {OPERATION_LOG.getValue()};//存量
    public Integer[] nav6 = {OPERATION_LOG.getValue()};//完成
    public Integer[] nav7 = {OPERATION_LOG.getValue()};//超时
    public Integer[] nav8 = {OPERATION_LOG.getValue(),INVALID_SET_RECOVER.getValue()};//无效
    public Integer[] nav9 = {OPERATION_LOG.getValue()};//待参与
    public Integer[] nav10 = {OPERATION_LOG.getValue()};//已参与
    public Integer[] nav11 = {OPERATION_LOG.getValue(), AUDIT_YES.getValue(),AUDIT_NO.getValue(),UPDATE_EDIT.getValue(), PAUSE.getValue()};//待审核
    public Integer[] nav12 = {OPERATION_LOG.getValue(), DISTRIBUTION_BORROWER.getValue(), PAUSE.getValue(),INVALID_SET.getValue()};//待处置
    public Integer[] nav13 = {OPERATION_LOG.getValue(), PAUSE.getValue(), INVALID_SET.getValue()};//待分配
    public Integer[] nav14 = {OPERATION_LOG.getValue()};//48H 新
    public Integer[] nav15 = {OPERATION_LOG.getValue(),INVALID_SET.getValue(),PAUSE.getValue()};//我的任务
    public Integer[] nav16 = {OPERATION_LOG.getValue(),INVALID_SET.getValue(),PAUSE.getValue()};//处置中
    public Integer[] nav17 = {OPERATION_LOG.getValue(), INVALID_SET.getValue(), PAUSE.getValue(),UPDATE_EDIT.getValue(), RESTART_APPLY.getValue()};//已驳回
    public Integer[] nav18 = {OPERATION_LOG.getValue(), PAUSE_RECOVER.getValue(), INVALID_SET.getValue()};//暂停
    public Integer[] nav19 = {OPERATION_LOG.getValue(), INVALID_SET.getValue(), PAUSE.getValue()};//正在进行
    public Integer[] nav20 = {OPERATION_LOG.getValue(), INVALID_SET.getValue(), PAUSE.getValue()};//我的催收
    public Integer[] nav21 = {OPERATION_LOG.getValue(),INVALID_SET.getValue(), PAUSE.getValue()};//最新任务
    public Integer[] nav22 = {OPERATION_LOG.getValue(), UPDATE_EDIT.getValue(),PUBLISH.getValue()};//待发布
    public Integer[] nav99 = {OPERATION_LOG.getValue()};//全部


    public final static LenderRowOperTypeNavBean lenderRowOperTypeNavBean = new LenderRowOperTypeNavBean();

    @Override
    Integer[] getNav0() {
        return nav0;
    }

    @Override
    Integer[] getNav1() {
        return nav1;
    }

    @Override
    Integer[] getNav2() {
        return nav2;
    }

    @Override
    Integer[] getNav3() {
        return nav3;
    }

    @Override
    Integer[] getNav4() {
        return nav4;
    }

    @Override
    Integer[] getNav5() {
        return nav5;
    }

    @Override
    Integer[] getNav6() {
        return nav6;
    }

    @Override
    Integer[] getNav7() {
        return nav7;
    }

    @Override
    Integer[] getNav8() {
        return nav8;
    }

    @Override
    Integer[] getNav9() {
        return nav9;
    }

    @Override
    Integer[] getNav10() {
        return nav10;
    }

    @Override
    Integer[] getNav11() {
        return nav11;
    }

    @Override
    Integer[] getNav12() {
        return nav12;
    }

    @Override
    Integer[] getNav13() {
        return nav13;
    }

    @Override
    Integer[] getNav14() {
        return nav14;
    }

    @Override
    Integer[] getNav15() {
        return nav15;
    }

    @Override
    Integer[] getNav16() {
        return nav16;
    }

    @Override
    Integer[] getNav17() {
        return nav17;
    }

    @Override
    Integer[] getNav18() {
        return nav18;
    }

    @Override
    Integer[] getNav19() {
        return nav19;
    }

    @Override
    Integer[] getNav20() {
        return nav20;
    }

    @Override
    Integer[] getNav21() {
        return nav21;
    }

    @Override
    Integer[] getNav22() {
        return nav22;
    }

    @Override
    Integer[] getNav99() {
        return nav99;
    }

}
