package com.dqys.business.service.utils.operType;

import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;

/**
 * nav对应状态具有的所有权限
 * <p>
 * Created by yan on 16-11-30.
 */
public class AllOperTypeNavBean extends OperTypeNavBean{
    public Integer[] nav0 = {102, 1110, 1615, 1310, 1213};//待接收
    public Integer[] nav1 = {102, 1110, 1615, 1310, 11213};//待申请
    public Integer[] nav2 = {102, 1110, 1615, 1310, 1213, 100, 103, 104, 105, 107, 108, 109, 110,
            112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115,
            162, 163, 164, 165, 166, 167, 168, 169, 169, 1610, 1611, 1612, 1613, 1614,
            130, 131, 132, 133, 134, 135, 136, 137, 138, 1310, 1311,
            120, 121, 122, 123, 124, 125, 126, 127, 129, 1210, 1211, 1214, 1215, 1216, 1217, 1218,
            151, 152, 153, 154, 155, 156, 157, 158, 159, 1511, 1512, 1513, 1515, 1516};//正在处置
    public Integer[] nav3 = {102, 1110, 1615, 1310, 1213};//聚焦
    public Integer[] nav4 = {102, 1110, 1615, 1310, 1213};//当月
    public Integer[] nav5 = {102, 1110, 1615, 1310, 1213};//存量
    public Integer[] nav6 = {102, 1110, 1615, 1310, 1213};//完成
    public Integer[] nav7 = {102, 1110, 1615, 1310, 1213};//超时
    public Integer[] nav8 = {102, 1110, 1615, 1310, 1213, 108, 1118, 1613, 139};//无效
    public Integer[] nav9 = {102, 1110, 1615, 1310, 1213};//待参与
    public Integer[] nav10 = {102, 1110, 1615, 1310, 1213};//已参与
    public Integer[] nav11 = {102, 103, 104, 1110, 1113, 1114, 1615, 1310, 1213, 169, 1610, 1312, 101, 161, 111, 1220, 116, 1115, 105, 107, 1611, 166};//待审核
    public Integer[] nav12 = {102, 1110, 1112, 1615, 1310, 1213, 1115, 116, 105, 107, 1611, 166};//待处置
    public Integer[] nav13 = {102, 1110, 1615, 1310, 1213, 1115, 116, 105, 107, 1611, 166};//待分配
    public Integer[] nav14 = {102, 1110, 1615, 1310, 1213};//48H 新
    public Integer[] nav15 = {102, 1110, 1615, 1310, 1213, 100, 101, 103, 104, 105,107, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115,
            161, 162, 163, 164, 165, 166, 167, 168, 169, 169, 1610, 1611, 1612, 1613, 1614,
            130, 131, 132, 133, 134, 135, 136, 137, 138, 1310, 1311, 1312,
            120, 121, 122, 123, 124, 125, 126, 127, 129, 1210, 1211, 1214, 1215, 1216, 1217, 1218, 1220};//我的任务
    public Integer[] nav16 = {102, 1110, 1615, 1310, 1213, 100, 103, 104, 105,107, 110,
            112, 113, 114, 115, 116, 117, 118, 119, 1111, 1115,
            162, 163, 164, 165, 166, 167, 168, 169, 169, 1610, 1611, 1612, 1613, 1614,
            130, 131, 132, 133, 134, 135, 136, 137, 138, 1310, 1311,
            120, 121, 122, 123, 124, 125, 126, 127, 129, 1210, 1211, 1214, 1215, 1216, 1217, 1218};//处置中
    public Integer[] nav17 = {102, 1110, 1615, 1310, 1213, 105, 107, 101, 1115, 116, 111, 1611, 161, 1312,
            1219, 106, 1116, 1612, 103, 169};//已驳回
    public Integer[] nav18 = {102, 1110, 1615, 1310, 1213, 109, 1117, 1614, 116, 107, 166};//暂停
    public Integer[] nav19 = {102, 1110, 1615, 1310, 1213, 116, 1115, 105, 107, 1611};//正在进行
    public Integer[] nav20 = {102, 1110, 1615, 1310, 1213, 116, 1115, 105, 107, 1611};//我的催收
    public Integer[] nav21 = {102, 1110, 1615, 1310, 1213};//最新任务
    public Integer[] nav22 = {102, 1110, 1615, 1310, 1213, 111,1121,101, AssetPackageEnum.PUBLISH.getValue()};//待发布
    public Integer[] nav99 = {102, 1110, 1615, 1310, 1213};//全部


    public final static AllOperTypeNavBean allOperTypeNavBean = new AllOperTypeNavBean();

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
