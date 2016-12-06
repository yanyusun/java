package com.dqys.business.service.utils.operType;

import com.dqys.business.service.constant.asset.ObjectTabEnum;

/**
 * Created by yan on 16-11-30.
 */
abstract public class OperTypeNavBean {

    public final static int LENDER_ROW=1;

    abstract Integer[] getNav0();

    abstract Integer[] getNav1();

    abstract Integer[] getNav2();

    abstract Integer[] getNav3();

    abstract Integer[] getNav4();

    abstract Integer[] getNav5();

    abstract Integer[] getNav6();

    abstract Integer[] getNav7();

    abstract Integer[] getNav8();

    abstract Integer[] getNav9();

    abstract Integer[] getNav10();

    abstract Integer[] getNav11();

    abstract Integer[] getNav12();

    abstract Integer[] getNav13();

    abstract Integer[] getNav14();

    abstract Integer[] getNav15();

    abstract Integer[] getNav16();

    abstract Integer[] getNav17();

    abstract Integer[] getNav18();

    abstract Integer[] getNav19();

    abstract Integer[] getNav20();

    abstract Integer[] getNav99();

    abstract Integer[] getNav21();

    abstract Integer[] getNav22();

    public Integer[] getNav(Integer navId) {
        if (navId == ObjectTabEnum.accept.getValue()) {
            return getNav0();
        }
        if (navId == ObjectTabEnum.apply.getValue()) {
            return getNav1();
        }
        if (navId == ObjectTabEnum.handling_urge.getValue()) {
            return getNav2();
        }
        if (navId == ObjectTabEnum.focus.getValue()) {
            return getNav3();
        }
        if (navId == ObjectTabEnum.month.getValue()) {
            return getNav4();
        }
        if (navId == ObjectTabEnum.stock.getValue()) {
            return getNav5();
        }
        if (navId == ObjectTabEnum.over.getValue()) {
            return getNav6();
        }
        if (navId == ObjectTabEnum.outTime.getValue()) {
            return getNav7();
        }
        if (navId == ObjectTabEnum.invalid.getValue()) {
            return getNav8();
        }
        if (navId == ObjectTabEnum.join.getValue()) {
            return getNav9();
        }
        if (navId == ObjectTabEnum.joined.getValue()) {
            return getNav10();
        }
        if (navId == ObjectTabEnum.check.getValue()) {
            return getNav11();
        }
        if (navId == ObjectTabEnum.handle.getValue()) {
            return getNav12();
        }
        if (navId == ObjectTabEnum.assign.getValue()) {
            return getNav13();
        }
        if (navId == ObjectTabEnum.new48h.getValue()) {
            return getNav14();
        }
        if (navId == ObjectTabEnum.task.getValue()) {
            return getNav15();
        }
        if (navId == ObjectTabEnum.handling_entrust.getValue()) {
            return getNav16();
        }
        if (navId == ObjectTabEnum.refuse.getValue()) {
            return getNav17();
        }
        if (navId == ObjectTabEnum.stop.getValue()) {
            return getNav18();
        }
        if (navId == ObjectTabEnum.gongingOn.getValue()) {
            return getNav19();
        }
        if (navId == ObjectTabEnum.myUrge.getValue()) {
            return getNav20();
        }
        if(navId == ObjectTabEnum.new_task.getValue()){
            return getNav21();
        }
        if(navId == ObjectTabEnum.wait_publish.getValue()){
            return getNav22();
        }
        if (navId == ObjectTabEnum.all.getValue()) {
            return getNav99();
        }
        return null;
    }


}
