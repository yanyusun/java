package com.dqys.business.service.service.impl;

import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.repay.RepayEnum;
import com.dqys.business.orm.mapper.asset.IOUInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.pojo.asset.IOUInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.repay.Repay;
import com.dqys.business.service.service.RepayService;
import com.dqys.business.service.utils.message.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mkfeng on 2016/7/19.
 */
@Service
public class RepayServiceImpl implements RepayService {

    @Autowired
    private RepayMapper repayMapper;

    @Autowired
    private IOUInfoMapper iouInfoMapper;

    @Autowired
    private LenderInfoMapper lenderInfoMapper;

    @Override
    public Map repayMoney(Integer userId, Integer objectId, Integer objectType, Integer repayType, Integer repayWay, Double money, String remark, MultipartFile file) throws Exception {
        Map map = new HashMap<>();
        List<IOUInfo> ious = new ArrayList<>();
        ious = getIouInfos(objectId, objectType, ious);//根据对象类型获取所有借据
        //分别计算利息和本金的总金额
        BigDecimal principalTotal = BigDecimal.ZERO;//本金总和
        BigDecimal accrualTotal = BigDecimal.ZERO;//利息总和
        List<Integer> iouIds = new ArrayList<>();//用于查询业务是否需要改为已完成
        for (IOUInfo iou : ious) {
            iouIds.add(iou.getId());
            if (iou.getLessCorpus() == 0 && ((iou.getPenalty() == null && iou.getAccrualArrears() == 0) || (iou.getPenalty() != null && iou.getPenalty() == 0))) {//剔除不需要还款的借据
                continue;
            }
            principalTotal = principalTotal.add(BigDecimal.valueOf(iou.getLessCorpus()));
            if (iou.getPenalty() != null) {
                accrualTotal = accrualTotal.add(BigDecimal.valueOf(iou.getPenalty()));
            } else {
                accrualTotal = accrualTotal.add(BigDecimal.valueOf(iou.getAccrualArrears()));
            }
        }
        BigDecimal paTotal = principalTotal.add(accrualTotal);//本金利息总和
        //判断所得金额是否大于还款金额
        if (repayType == RepayEnum.TYPE_PRINCIPAL.getValue() && principalTotal.doubleValue() < money) {
            return getMap(map, principalTotal);
        } else if (repayType == RepayEnum.TYPE_ACCRUAL.getValue() && accrualTotal.doubleValue() < money) {
            return getMap(map, accrualTotal);
        } else if (repayType == RepayEnum.TYPE_A_P.getValue() && paTotal.doubleValue() < money) {
            return getMap(map, paTotal);
        }
        //对还款金额进行操作
        boolean flag = false;
        for (IOUInfo iou : ious) {
            if (iou.getLessCorpus() == 0 && ((iou.getPenalty() == null && iou.getAccrualArrears() == 0) || (iou.getPenalty() != null && iou.getPenalty() == 0))) {//剔除不需要还款的借据
                continue;
            }
            Double repayM = 0.0;
            if (money == 0) {
                break;
            }
            //三种情况的还款方式
            if (repayType == RepayEnum.TYPE_PRINCIPAL.getValue()) {//还本金
                Double priMoney = iou.getLessCorpus() > money ? money : iou.getLessCorpus();
                money -= priMoney;
                repayM += priMoney;
                flag = dispose(iou.getLenderId(), iou.getId(), iou.getVersion(), priMoney, null, null);
            } else if (repayType == RepayEnum.TYPE_ACCRUAL.getValue()) {//还利息
                Double accMoney = iou.getAccrualArrears() > money ? money : iou.getAccrualArrears();
                if (iou.getPenalty() != null) {
                    Double penalty = iou.getPenalty() > money ? money : iou.getPenalty();
                    money -= penalty;
                    repayM += penalty;
                    flag = dispose(iou.getLenderId(), iou.getId(), iou.getVersion(), null, accMoney, penalty);
                } else {
                    money -= accMoney;
                    repayM += accMoney;
                    flag = dispose(iou.getLenderId(), iou.getId(), iou.getVersion(), null, accMoney, null);
                }
            } else if (repayType == RepayEnum.TYPE_A_P.getValue()) {//先还利息再还本金
                Double accMoney = iou.getAccrualArrears() > money ? money : iou.getAccrualArrears();
                if (iou.getPenalty() != null) {
                    Double penalty = iou.getPenalty() > money ? money : iou.getPenalty();
                    money -= penalty;
                    Double priMoney = iou.getLessCorpus() > money ? money : iou.getLessCorpus();
                    money -= priMoney;
                    repayM += (penalty + priMoney);
                    flag = dispose(iou.getLenderId(), iou.getId(), iou.getVersion(), priMoney, accMoney, penalty);
                } else {
                    money -= accMoney;
                    Double priMoney = iou.getLessCorpus() > money ? money : iou.getLessCorpus();
                    money -= priMoney;
                    repayM += (accMoney + priMoney);
                    flag = dispose(iou.getLenderId(), iou.getId(), iou.getVersion(), priMoney, accMoney, null);
                }
            }
            //扣除金额成功后需要添加还款记录
            if (!flag) {
                throw new Exception();
            } else {
                InputStream in = file.getInputStream();
                byte[] bytes = new byte[(int) file.getSize()];
                in.read(bytes);
                Repay repay = new Repay(null, new Date(), repayType, userId, iou.getId(), repayM, objectId, repayWay, remark, null, new Date(), new Date(), null, bytes);
                int num = repayMapper.insertSelective(repay);
                if (num == 0) {
                    throw new Exception();
                }
            }
        }
        //查询对应抵押物或借据是否全部还完了，还完款的就要在业务表上改为已完成
        List<Integer> businessIds = repayMapper.getBusinessId(ObjectTypeEnum.IOU.getValue(),iouIds);
        for (Integer id : businessIds) {
            Map iouMap = repayMapper.getIouSumByBusinessId(id, ObjectTypeEnum.IOU.getValue());
            Double lessMoney = MessageUtils.transMapToDou(iouMap, "lessMoney");
            Double accMoney = MessageUtils.transMapToDou(iouMap, "accMoney");
            Double penMoney = MessageUtils.transMapToDou(iouMap, "penMoney");
            if ((lessMoney == null || lessMoney == 0) && (accMoney == null || accMoney == 0) && (penMoney == null || penMoney == 0)) {
                Integer num = repayMapper.updateBusinessStatus(id, 1);
                if (num == 0) {
                    throw new Exception();
                }
            }
        }
        //最后金额不是等于0那么前面的计算有问题
        if (money != 0) {
            throw new Exception();
        }
        map.put("result","yes");
        return map;
    }

    private List<IOUInfo> getIouInfos(Integer objectId, Integer objectType, List<IOUInfo> ious) {
        if (objectType == RepayEnum.OBJECT_IOU.getValue()) {//借据
            IOUInfo iou = iouInfoMapper.get(objectId);
            ious.add(iou);
        } else if (objectType == RepayEnum.OBJECT_PAWN.getValue()) {//抵押物
            ious = iouInfoMapper.selectIouInfoByPawnId(objectId);
        }
        return ious;
    }

    private Map getMap(Map map, BigDecimal paTotal) {
        map.put("result", "money_oversize");
        map.put("repayMoney", paTotal.doubleValue());
        return map;
    }

    /**
     * 减去借据金额和借款人金额
     */
    private boolean dispose(Integer lenderId, Integer iouId, Integer version, Double priMoney, Double accMoney, Double penalty) {
        int result = repayMapper.repayIou(iouId, version, priMoney, accMoney, penalty);
        if (result > 0) {
            LenderInfo len = lenderInfoMapper.get(lenderId);
            Integer num = repayMapper.repayLender(lenderId, len.getVersion(), priMoney, penalty != null ? penalty : accMoney);
            if (num > 0) {
                return true;
            }
        }
        return false;
    }

}
