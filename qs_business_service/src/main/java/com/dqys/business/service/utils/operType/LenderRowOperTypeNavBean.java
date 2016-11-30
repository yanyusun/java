package com.dqys.business.service.utils.operType;

/**
 * nav对应借款人列表所有权限
 * <p>
 * Created by yan on 16-11-30.
 */
public class LenderRowOperTypeNavBean extends OperTypeNavBean {
    public Integer[] nav0 = {1110};//待接收
    public Integer[] nav1 = {1110};//待申请
    public Integer[] nav2 = {110, 116, 1115};//正在处置
    public Integer[] nav3 = {1110};//聚焦
    public Integer[] nav4 = {1110};//当月
    public Integer[] nav5 = {1110};//存量
    public Integer[] nav6 = {1110};//完成
    public Integer[] nav7 = {1110};//超时
    public Integer[] nav8 = {1110, 1118};//无效
    public Integer[] nav9 = {1110};//待参与
    public Integer[] nav10 = {1110};//已参与
    public Integer[] nav11 = {1110, 1113, 1114, 111, 1115};//待审核
    public Integer[] nav12 = {1110, 1112, 1115, 116};//待处置
    public Integer[] nav13 = {1110, 1213, 1115, 116};//待分配
    public Integer[] nav14 = {1110};//48H 新
    public Integer[] nav15 = {1110, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115, 1116};//我的任务
    public Integer[] nav16 = {116, 110, 1115};//处置中
    public Integer[] nav17 = {1110, 1115, 116, 111, 1116};//已驳回
    public Integer[] nav18 = {1110, 1117, 116};//暂停
    public Integer[] nav19 = {1110, 116, 1115};//正在进行
    public Integer[] nav20 = {1110, 116, 1115};//我的催收
    public Integer[] nav99 = {1110};//全部

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
    Integer[] getNav99() {
        return nav99;
    }

}
