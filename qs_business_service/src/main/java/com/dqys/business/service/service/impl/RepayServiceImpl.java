package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.constant.CompanyTypeEnum;
import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.repay.RepayEnum;
import com.dqys.business.orm.mapper.asset.*;
import com.dqys.business.orm.mapper.business.ObjectUserRelationMapper;
import com.dqys.business.orm.mapper.company.CompanyTeamReMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.pojo.asset.*;
import com.dqys.business.orm.pojo.coordinator.CompanyTeamRe;
import com.dqys.business.orm.pojo.repay.DamageApply;
import com.dqys.business.orm.pojo.repay.Repay;
import com.dqys.business.orm.pojo.repay.RepayRecord;
import com.dqys.business.orm.query.asset.RelationQuery;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.*;
import com.dqys.business.service.exception.bean.ArtificialException;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.service.RepayService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.CommonUtil;
import com.dqys.core.utils.DateFormatTool;
import com.dqys.core.utils.FileTool;
import com.dqys.core.utils.SmsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.servlet.jsp.tagext.TagInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AssetInfoMapper assetInfoMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private BusinessLogService businessLogService;

    @Autowired
    private PawnInfoMapper pawnInfoMapper;

    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private CompanyTeamReMapper companyTeamReMapper;
    @Autowired
    private CoordinatorService coordinatorService;
    @Autowired
    private CiRelationMapper ciRelationMapper;
    @Autowired
    private ObjectUserRelationMapper objectUserRelationMapper;

    @Override
    public Map repayMoney(Integer userId, Integer objectId, Integer objectType, Integer repayType, Integer repayWay, Double money, String remark, String file) throws Exception {
        if (file != null && !"".equals(file)) {
            try {
                if (!FileTool.saveFileSync(file)) {
                    throw new UnexpectedRollbackException("保存附件失败");
                }
            } catch (IOException e) {
                throw new UnexpectedRollbackException("保存附件异常");
            }
        }
        if (objectType == RepayEnum.OBJECT_IOU.getValue().intValue()) {
            businessLogService.add(objectId, ObjectTypeEnum.IOU.getValue(), IouEnum.REIMBURSEMENT.getValue(), "还款操作", "", 0, 0);//操作日志
        } else if (objectType == RepayEnum.OBJECT_PAWN.getValue().intValue()) {
            businessLogService.add(objectId, ObjectTypeEnum.PAWN.getValue(), PawnEnum.REIMBURSEMENT.getValue(), "还款操作", "", 0, 0);//操作日志
        } else if (objectType == RepayEnum.OBJECT_CASE.getValue().intValue()) {
            businessLogService.add(objectId, ObjectTypeEnum.CASE.getValue(), CaseEnum.REPAY_YET.getValue(), CaseEnum.REPAY_YET.getName(), "", 0, 0);//操作日志
        }
        Map map = new HashMap<>();
        List<IOUInfo> ious = new ArrayList<>();
        ious = getIouInfos(objectId, objectType, ious);//根据对象类型获取所有借据
        if (ious == null || ious.size() == 0) {
            map.put("result", "no");
            map.put("msg", "查询不到对应的借据进行还款操作");
            return map;
        }
        //分别计算利息和本金的总金额
        BigDecimal principalTotal = BigDecimal.ZERO;//本金总和
        BigDecimal accrualTotal = BigDecimal.ZERO;//利息总和
        List<Integer> iouIds = new ArrayList<>();//用于查询业务是否需要改为已完成
        //循环得出借据金额
        for (IOUInfo iou : ious) {
            iouIds.add(iou.getId());
            if ((iou.getLessCorpus() != null && iou.getLessCorpus() == 0) && (iou.getAccrualArrears() != null && iou.getAccrualArrears() == 0) && (iou.getPenalty() != null && iou.getPenalty() == 0)) {//剔除不需要还款的借据
                continue;
            }
            if (iou.getLessCorpus() != null) {
                principalTotal = principalTotal.add(BigDecimal.valueOf(iou.getLessCorpus()));
            }

            if (iou.getPenalty() != null) {
                accrualTotal = accrualTotal.add(BigDecimal.valueOf(iou.getPenalty()));
            }
            if (iou.getAccrualArrears() != null) {
                accrualTotal = accrualTotal.add(BigDecimal.valueOf(iou.getAccrualArrears()));
            }
        }
        Repay repay = new Repay();
        BigDecimal paTotal = principalTotal.add(accrualTotal);//本金利息总和
        //只有是案件的时候还款金额赋值为借据本金利息总金额(还款类型：还利息加本金)
        if (objectType == RepayEnum.OBJECT_CASE.getValue().intValue() && repayType == RepayEnum.TYPE_A_P.getValue().intValue()) {
            money = paTotal.doubleValue();
        }
        //还款金额为0就返回无需还款操作
        if (money == 0) {
            map.put("result", "no");
            map.put("msg", "还款金额是为0，无效还款");
            return map;
        }
        //判断所得金额是否大于还款金额
        if (repayType == RepayEnum.TYPE_PRINCIPAL.getValue().intValue()) {
            if (getRepayStatus(money, principalTotal, repay)) return getMap(map, principalTotal);
        } else if (repayType == RepayEnum.TYPE_ACCRUAL.getValue().intValue()) {
            if (getRepayStatus(money, accrualTotal, repay)) return getMap(map, accrualTotal);
        } else if (repayType == RepayEnum.TYPE_A_P.getValue().intValue()) {
            if (getRepayStatus(money, paTotal, repay)) return getMap(map, paTotal);
        }
        //添加还款记录
        repay.setOperUserId(userId);
        repay.setRemark(remark);
        repay.setRepayFid(objectId);
        repay.setRepayType(repayType);
        repay.setRepayWay(repayWay);
        repay.setRepayFidType(objectType);
        repay.setRepayM(money);
        repay.setRepayBills(file);
        setRepayLenderId(repay);
        repayMapper.insertSelective(repay);
        map.put("repayId", repay.getId());
        //对还款金额进行操作
        boolean flag = false;
        for (IOUInfo iou : ious) {
            RepayRecord repayRecord = new RepayRecord();
            repayRecord.setRepayId(repay.getId());
            repayRecord.setIouId(iou.getId());
            if ((iou.getLessCorpus() != null && iou.getLessCorpus() == 0) && (iou.getAccrualArrears() != null && iou.getAccrualArrears() == 0) && (iou.getPenalty() != null && iou.getPenalty() == 0)) {//剔除不需要还款的借据
                continue;
            }
            if (money == 0) {
                break;
            }
            //三种情况的还款方式
            Double accMoney = 0.0;//利息
            Double penalty = 0.0;//罚息
            Double priMoney = 0.0;//本金
            if (repayType == RepayEnum.TYPE_PRINCIPAL.getValue()) {//还本金
                if (iou.getLessCorpus() != null) {
                    priMoney = iou.getLessCorpus() > money ? money : iou.getLessCorpus();
                    money -= priMoney;
                    repayRecord.setRepayPrincipal(priMoney);
                }
            } else if (repayType == RepayEnum.TYPE_ACCRUAL.getValue()) {//还利息
                if (iou.getAccrualArrears() != null) {
                    accMoney = iou.getAccrualArrears() > money ? money : iou.getAccrualArrears();
                    money -= accMoney;
                    repayRecord.setRepayInterest(accMoney);
                }
                if (iou.getPenalty() != null) {
                    penalty = iou.getPenalty() > money ? money : iou.getPenalty();
                    money -= penalty;
                    repayRecord.setRepayFine(penalty);
                }
            } else if (repayType == RepayEnum.TYPE_A_P.getValue()) {//先还利息再还本金
                if (iou.getAccrualArrears() != null) {
                    accMoney = iou.getAccrualArrears() > money ? money : iou.getAccrualArrears();
                    money -= accMoney;
                    repayRecord.setRepayInterest(accMoney);
                }
                if (iou.getPenalty() != null) {
                    penalty = iou.getPenalty() > money ? money : iou.getPenalty();
                    money -= penalty;
                    repayRecord.setRepayFine(penalty);
                }
                if (iou.getLessCorpus() != null) {
                    priMoney = iou.getLessCorpus() > money ? money : iou.getLessCorpus();
                    money -= priMoney;
                    repayRecord.setRepayPrincipal(priMoney);
                }
            }
            flag = dispose(iou.getLenderId(), iou.getId(), iou.getVersion(), priMoney, accMoney, penalty);
            //扣除金额成功后需要对每个借据添加还款详细记录
            if (!flag) {
                throw new Exception();
            } else {
                int num = repayMapper.insertRecordSelective(repayRecord);
                if (num == 0) {
                    throw new Exception();
                }
            }
        }
        //查询对应抵押物或借据是否全部还完了，还完款的就要在业务表上改为已完成
        setBusinessStatus(iouIds);
        //最后金额不是等于0那么前面的计算有问题
        if (money != 0) {
            throw new Exception();
        }
        map.put("result", "yes");
        return map;
    }

    /**
     * 根据对象id和对象类型
     * 设置还款记录中的借款人id
     *
     * @param repay
     */
    private void setRepayLenderId(Repay repay) {
        if (repay.getRepayFidType() == RepayEnum.OBJECT_IOU.getValue().intValue()) {
            IOUInfo iouInfo = iouInfoMapper.get(repay.getRepayFid());
            repay.setLenderId(iouInfo.getLenderId());
        } else if (repay.getRepayFidType() == RepayEnum.OBJECT_PAWN.getValue().intValue()) {
            PawnInfo pawnInfo = pawnInfoMapper.get(repay.getRepayFid());
            repay.setLenderId(pawnInfo.getLenderId());
        } else if (repay.getRepayFidType() == RepayEnum.OBJECT_UNLIMITED.getValue().intValue()) {
            repay.setLenderId(repay.getRepayFid());
        } else if (repay.getRepayFidType() == RepayEnum.OBJECT_CASE.getValue().intValue()) {
            RelationQuery query = new RelationQuery();
            query.setCaseId(repay.getRepayFid());
            List<CiRelation> list = ciRelationMapper.queryList(query);
            if (list.size() > 0) {
                IOUInfo info = iouInfoMapper.get(list.get(0).getIouId());
                if (info != null) {
                    repay.setLenderId(info.getLenderId());
                }
            }

        }
    }

    /**
     * 修改业务表状态
     */
    private void setBusinessStatus(List<Integer> iouIds) throws Exception {
        List<Integer> businessIds = repayMapper.getBusinessId(ObjectTypeEnum.IOU.getValue(), iouIds);
        for (Integer id : businessIds) {
            Map iouMap = repayMapper.getIouSumByBusinessId(id, ObjectTypeEnum.IOU.getValue());
            Double lessMoney = MessageUtils.transMapToDou(iouMap, "lessMoney");
            Double accMoney = MessageUtils.transMapToDou(iouMap, "accMoney");
            Double penMoney = MessageUtils.transMapToDou(iouMap, "penMoney");
            Integer num = 0;
            if ((lessMoney == null || lessMoney == 0) && (accMoney == null || accMoney == 0) && (penMoney == null || penMoney == 0)) {
                num = repayMapper.updateBusinessStatus(id, BusinessStatusEnum.end.getValue());//状态改为已完成
            } else {
                num = repayMapper.updateBusinessStatus(id, BusinessStatusEnum.platform_pass.getValue());//状态改为审核通过
            }
            if (num == 0) {
                throw new Exception();
            }
        }
    }

    /**
     * 设定还款状态
     *
     * @param money
     * @param principalTotal
     * @param repay
     * @return
     */
    private boolean getRepayStatus(Double money, BigDecimal principalTotal, Repay repay) {
        if (principalTotal.doubleValue() < money) {
            return true;
        } else if (principalTotal.doubleValue() == money) {
            repay.setStatus(0);
        } else {
            repay.setStatus(1);
        }
        return false;
    }

    private List<IOUInfo> getIouInfos(Integer objectId, Integer objectType, List<IOUInfo> ious) {
        if (objectType == RepayEnum.OBJECT_IOU.getValue().intValue()) {//借据
            IOUInfo iou = iouInfoMapper.get(objectId);
            if (iou != null) {
                ious.add(iou);
            }
        } else if (objectType == RepayEnum.OBJECT_PAWN.getValue().intValue()) {//抵押物
            List<IOUInfo> list = iouInfoMapper.selectIouInfoByPawnId(objectId);
            for (IOUInfo info : list) {
                if (info != null) {
                    ious.add(info);
                }
            }
        } else if (objectType == RepayEnum.OBJECT_UNLIMITED.getValue().intValue()) {
            //不限对象（objectId需要借款人id）
            Map map = new HashMap<>();
            getIouAndPawnByLender(objectId, map);//借款人下的所有借据和抵押物
            List<Map> iousList = (List<Map>) map.get("ious");
            // List<Map> pawnsList = (List<Map>) map.get("pawns");
            //一个借据必然对应一个抵押物
            List<Integer> iouIds = new ArrayList<>();
            if (iousList != null) {
                for (Map m : iousList) {//转成集合去查询
                    if (MessageUtils.transMapToInt(m, "id") != null) {
                        iouIds.add(MessageUtils.transMapToInt(m, "id"));
                    }
                }
            }
            if (iouIds.size() > 0) {
                List<IOUInfo> list = iouInfoMapper.selectIouInfoByObjectIds(iouIds);//所有借据
                for (IOUInfo info : list) {
                    if (info != null) {
                        ious.add(info);
                    }
                }
            }
        } else if (objectType == RepayEnum.OBJECT_CASE.getValue().intValue()) {
            //根据案件id相关联借据还款
            RelationQuery query = new RelationQuery();
            query.setCaseId(objectId);
            List<CiRelation> list = ciRelationMapper.queryList(query);
            for (CiRelation ci : list) {
                IOUInfo info = iouInfoMapper.get(ci.getIouId());
                if (info != null) {
                    ious.add(info);
                }
            }
        }
        //去除重复的数据
        List<IOUInfo> iouInfos = new ArrayList<>();
        List<Integer> iouIds = new ArrayList<>();
        for (IOUInfo info : ious) {
            if (!iouIds.contains(info.getId())) {
                iouInfos.add(info);
            }
        }
        return iouInfos;
    }

    private Map getMap(Map map, BigDecimal paTotal) {
        map.put("result", "money_oversize");
        map.put("repayMoney", paTotal.doubleValue());
        map.put("msg", "最高可还：" + paTotal.doubleValue() + "元");
        return map;
    }

    /**
     * 还款扣除金额
     * 减去借据金额和借款人金额和资产包金额
     */
    private boolean dispose(Integer lenderId, Integer iouId, Integer version, Double priMoney, Double accMoney, Double penalty) {
        int result = repayMapper.repayIou(iouId, version, priMoney, accMoney, penalty);//借据扣除
        if (result > 0) {
            setRepayStatus(iouId, 3);//修改借据还款状态
            LenderInfo len = lenderInfoMapper.get(lenderId);
            Integer num = repayMapper.repayLender(lenderId, len.getVersion(), priMoney, penalty + accMoney);//借款人扣除
            if (num > 0) {
                setRepayStatus(len.getId(), 2);//修改借款人还款状态
                if (len.getAssetId() != null) {
                    AssetInfo assetInfo = assetInfoMapper.get(len.getAssetId());
                    Integer count = repayMapper.repayAsset(assetInfo.getId(), assetInfo.getVersion(), priMoney, penalty + accMoney);//资产包扣除
                    if (count > 0) {
                        setRepayStatus(assetInfo.getId(), 1);//修改资产包还款状态
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 还款冲正
     * 把原扣的金额补还回去
     */

    private boolean reversalDispose(RepayRecord re) {
        IOUInfo iouInfo = iouInfoMapper.get(re.getIouId());
        int result = repayMapper.repayIouReversal(iouInfo.getId(), iouInfo.getVersion(), re.getRepayPrincipal(), re.getRepayInterest(), re.getRepayFine());//借据冲正
        if (result > 0) {
            setRepayStatus(iouInfo.getId(), 3);//修改借据还款状态
            LenderInfo len = lenderInfoMapper.get(iouInfo.getLenderId());
            Integer num = repayMapper.repayLenderReversal(len.getId(), len.getVersion(), re.getRepayPrincipal(), (re.getRepayFine() + re.getRepayInterest()));//借款人冲正
            if (num > 0) {
                setRepayStatus(len.getId(), 2);//修改借款人还款状态
                if (len.getAssetId() != null) {
                    AssetInfo assetInfo = assetInfoMapper.get(len.getAssetId());
                    Integer count = repayMapper.repayAssetReversal(assetInfo.getId(), assetInfo.getVersion(), re.getRepayPrincipal(), (re.getRepayFine() + re.getRepayInterest()));//资产包冲正
                    if (count > 0) {
                        setRepayStatus(assetInfo.getId(), 1);//修改资产包还款状态
                    }
                }
                RepayRecord record = new RepayRecord();
                record.setId(re.getId());
                record.setStatus(2);
                repayMapper.updateRecordSelective(record);//修改还款记录状态
                return true;
            }
        }
        return false;
    }

    /**
     * 还款状态
     *
     * @param id
     * @param type(1资产包2借款人3借据)
     * @return
     */

    private boolean setRepayStatus(Integer id, Integer type) {
        //计算借款人或是资产包或是借据的总金额，用于修改还款状态
        Map map = repayMapper.getSumMoney(id, type);
        Integer num = 0;

        if (MessageUtils.transMapToDou(map, "total") == null || MessageUtils.transMapToDou(map, "total") == 0) {
            num = repayMapper.updateRepayStatus(id, type, RepayEnum.REPAY_STATUS_YES.getValue());//完成
        } else {
            num = repayMapper.updateRepayStatus(id, type, RepayEnum.REPAY_STATUS_NO.getValue());//没有完成
        }
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void postpone(DamageApply damageApply, Map map) {
        DamageApply damageApply1 = new DamageApply();
        damageApply1.setStatus(0);
        damageApply1.setApply_object_id(damageApply.getApply_object_id());
        damageApply1.setObject_type(damageApply.getObject_type());
        damageApply1.setApply_user_id(damageApply.getApply_user_id());
        List<DamageApply> damages = repayMapper.selectByDamageApply(damageApply1);//查询待审核的延期申请记录
        Integer num = 0;
        Integer id = 0;
        String damage_date = "";//申请时间
        String original_time = "";//原来时间
        if (damages.size() > 0) {//存在已经还未审核的记录就只是修改
            //修改申请记录
            DamageApply damage = damages.get(0);
            damage.setDamage_date(damageApply.getDamage_date());
            num = repayMapper.updateDamageApply(damage);
            id = damage.getId();
            damage_date = DateFormatTool.format(damage.getDamage_date(), DateFormatTool.DATE_FORMAT_10_REG1);
            original_time = DateFormatTool.format(damage.getOriginal_time(), DateFormatTool.DATE_FORMAT_10_REG1);
        } else {
            //添加申请记录
            setDamage(damageApply);//完善申请记录信息
            num = repayMapper.addDamageApply(damageApply);
            if (num > 0) {
                id = damageApply.getId();
                damage_date = DateFormatTool.format(damageApply.getDamage_date(), DateFormatTool.DATE_FORMAT_10_REG1);
                original_time = DateFormatTool.format(damageApply.getOriginal_time(), DateFormatTool.DATE_FORMAT_10_REG1);
            }
        }
        List<Map> mapList = new ArrayList<>();
        SmsUtil smsUtil = new SmsUtil();//发送短信通知
        Integer code = SmsEnum.POSTPONE_APPLY.getValue();
        Map user = coordinatorMapper.getUserAndCompanyByUserId(damageApply.getEaxm_user_id());//接收者
        mapList.add(user);
        Map oper = coordinatorMapper.getUserAndCompanyByUserId(damageApply.getApply_user_id());//发送者、
//        判断申请用户和审核用户是否为相同公司，如果是同家公司不需要通知平台参与审核
        if (!MessageUtils.transMapToString(user, "companyName").equals(MessageUtils.transMapToString(oper, "companyName"))) {
            TUserTag tag = messageService.getAdmin();
            if (tag != null) {
                Map admin = coordinatorMapper.getUserAndCompanyByUserId(tag.getUserId());//接收者管理员
                mapList.add(admin);
            }
        }
        for (Map userC : mapList) {
            String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(oper, "companyName"),
                    CompanyTypeEnum.getCompanyTypeEnum(MessageUtils.transMapToInt(oper, "companyType")).getName(), MessageUtils.transMapToString(oper, "realName"),
                    ObjectTypeEnum.getObjectTypeEnum(damageApply.getObject_type()).getName(), coordinatorService.getObjectName(damageApply.getObject_type(), damageApply.getApply_object_id()), original_time, damage_date);
            String title = coordinatorService.getMessageTitle(damageApply.getApply_object_id(), damageApply.getObject_type(), MessageBTEnum.POSTPONE.getValue());
            String operUrl = MessageUtils.setOperUrl("/repay/auditPostpone?status=1&applyId=" + id, null, "/repay/auditPostpone?status=2&applyId=" + id, null, "");
            messageService.add(title, content, damageApply.getApply_user_id(), MessageUtils.transMapToInt(userC, "userId"), "", MessageEnum.TASK.getValue(), MessageBTEnum.POSTPONE.getValue(), operUrl);
        }

        map.put("result", "yes");
    }

    /**
     * @param sendUserId
     * @param receiveUserId
     * @param mapList
     */
    private void judge(Integer sendUserId, Integer receiveUserId, List<Map> mapList) {

    }

    @Override
    public void auditPostpone(Integer id, Integer statuas, Integer userId, Map map) throws Exception {

        DamageApply damageApply = repayMapper.getDamageApply(id);
        if (damageApply == null) {//无记录无效
            map.put("result", "no");
            map.put("msg", "查询申请记录失败");
            return;
        }
        if (damageApply.getStatus() != 0) {//已经审核过得无效
            map.put("result", "no");
            map.put("msg", "重复操作");
            return;
        }
//        if(damageApply.getEaxm_user_id()!=userId){//不是要求的审核人
//            map.put("result", "no");
//            return;
//        }
        setDamage(damageApply);
        damageApply.setStatus(statuas);
        Integer num = repayMapper.updateDamageApply(damageApply);//修改审核状态
        if (num > 0) {
            Integer result = 0;
            if (statuas == 1) {
                //延期申请审核通过的就要修改相应对象的委托结束时间
                if (damageApply.getObject_type() == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
                    AssetInfo assetInfo = new AssetInfo();
                    assetInfo.setId(damageApply.getApply_object_id());
                    assetInfo.setEndAt(damageApply.getDamage_date());
                    result = assetInfoMapper.update(assetInfo);
                    businessLogService.add(assetInfo.getId(), ObjectTypeEnum.ASSETPACKAGE.getValue(), AssetPackageEnum.update.getValue(), "延期申请审核操作", "", 0, 0);//操作日志
                }
                if (damageApply.getObject_type() == ObjectTypeEnum.LENDER.getValue()) {
                    LenderInfo lenderInfo = new LenderInfo();
                    lenderInfo.setId(damageApply.getApply_object_id());
                    lenderInfo.setEndAt(damageApply.getDamage_date());
                    result = lenderInfoMapper.update(lenderInfo);
                    businessLogService.add(lenderInfo.getId(), ObjectTypeEnum.LENDER.getValue(), LenderEnum.UPDATE_EDIT.getValue(), "延期申请审核操作", "", 0, 0);//操作日志
                }
            }
            //添加通知消息记录
            SmsUtil smsUtil = new SmsUtil();//发送短信通知
            Integer code = 0;
            if (statuas == 1) {
                code = SmsEnum.POSTPONE_AUDIT_YES.getValue();
            } else {
                code = SmsEnum.POSTPONE_AUDIT_NO.getValue();
            }
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(damageApply.getApply_user_id());
            Map oper = coordinatorMapper.getUserAndCompanyByUserId(damageApply.getEaxm_user_id());
            String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(oper, "companyName"),
                    CompanyTypeEnum.getCompanyTypeEnum(MessageUtils.transMapToInt(oper, "companyType")).getName(), MessageUtils.transMapToString(oper, "realName"),
                    ObjectTypeEnum.getObjectTypeEnum(damageApply.getObject_type()).getName(), coordinatorService.getObjectName(damageApply.getObject_type(), damageApply.getApply_object_id()));
            String title = coordinatorService.getMessageTitle(damageApply.getApply_object_id(), damageApply.getObject_type(), MessageBTEnum.POSTPONE_AUDIT.getValue());
            messageService.add(title, content, damageApply.getEaxm_user_id(), damageApply.getApply_user_id(), "", MessageEnum.SERVE.getValue(), MessageBTEnum.POSTPONE_AUDIT.getValue(), "");
            map.put("result", "yes");
        } else {
            map.put("result", "no");
            map.put("msg", "修改失败");
        }
    }

    @Override
    public void getIouAndPawnByLender(Integer lenderId, Map map) {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        List<CompanyTeamRe> list = companyTeamReMapper.teamReListByLenderIdAndUserid(lenderId, ObjectTypeEnum.LENDER.getValue(), userId, 1);//查询当前用户的分配器
        boolean flag = false;//是否为业务流转用户。默认不是.优先考虑是所属机构的
        for (CompanyTeamRe teamRe : list) {
            if (teamRe.getType() == 3) {
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        List<Map> ious = repayMapper.getIouByLenderId(lenderId);
        List<Map> pawns = repayMapper.getPawnByLenderId(lenderId);
        if (flag) {
            //业务流转用，查出流转借据
            ious = objectUserRelationMapper.findByObjectId(ObjectTypeEnum.IOU.getValue(), userId, ious);
            pawns = objectUserRelationMapper.findByObjectId(ObjectTypeEnum.PAWN.getValue(), userId, pawns);
        }
        map.put("ious", ious);//map的key（number和id）
        map.put("pawns", pawns);//map的key（number和id）
    }

    @Override
    public Map reversal(Integer objectId, Integer objectType) throws Exception {
        Map map = new HashMap<>();
        List<RepayRecord> res = repayMapper.getRepayRecord(objectId, objectType);//获取相关对象的还款记录
        //存在还款记录就进行相应记录的冲正操作
        if (res.size() == 0) {
            map.put("result", "no");
        } else {
            for (RepayRecord re : res) {
                boolean flag = reversalDispose(re);
                if (!flag) {
                    throw new Exception();
                }
            }
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Map reversal(Integer repayId) throws Exception {
        Map map = new HashMap<>();
        map.put("result", "no");
        Repay pay = repayMapper.get(repayId);
        if (pay == null) {
            map.put("msg", "查询还款记录失败");
            return map;
        }
        if (pay.getStatus() == 2) {
            map.put("msg", "已经设置为无效的还款记录");
            return map;
        }
        List<RepayRecord> res = repayMapper.getRepayRecordByRepayId(repayId);//根据还款记录id获取还款详细信息
        List<Integer> iouIds = new ArrayList<>();
        //存在还款记录就进行相应记录的冲正操作
        for (RepayRecord re : res) {
            iouIds.add(re.getIouId());
            boolean flag = reversalDispose(re);
            if (!flag) {
                throw new Exception();
            }
        }
        setBusinessStatus(iouIds);//变化业务状态
        Repay repay = new Repay();
        repay.setStatus(2);//还款无效
        repay.setId(repayId);
        repayMapper.updateByPrimaryKeySelective(repay);
        map.put("result", "yes");
        return map;
    }

    @Override
    public Map updateRepayMoney(Integer repayId, Integer userId, Integer objectId, Integer objectType, Integer
            repayType, Integer repayWay, Double money, String remark, String file) throws Exception {
        Map map = reversal(repayId);//冲正
        if (MessageUtils.transMapToString(map, "result").equals("yes")) {
            setBusinessStatus(repayMapper.getIouIdByRecord(repayId));//设置业务状态
            repayMapper.deleteByRepay(repayId);//删除冲正后的还款记录，重新进行新还款操作
            map = repayMoney(userId, objectId, objectType, repayType, repayWay, money, remark, file);//还款
            if (!MessageUtils.transMapToString(map, "result").equals("yes")) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                String json = objectMapper.writeValueAsString(map);
                throw new ArtificialException(MessageUtils.transMapToString(map, "msg"), ArtificialException.EXCEPTION_CODE);
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return map;
    }

    @Override
    public void getRepayList(Repay repay, Map map) {
        List<Repay> repays = repayMapper.selectByRepay(repay);
        map.put("result", "yes");
        map.put("repays", repays);
    }

    @Override
    public Map caseRepayMoney(Integer caseId, String remark, String file) throws Exception {
        Integer userId = UserSession.getCurrent() == null ? 0 : UserSession.getCurrent().getUserId();
        Map map = repayMoney(userId, caseId, RepayEnum.OBJECT_CASE.getValue(), RepayEnum.TYPE_A_P.getValue(), RepayEnum.WAY_DIRECT.getValue(), null, remark, file);
        return map;
    }


    /**
     * 给延期申请对象赋值
     *
     * @param damageApply
     */
    private void setDamage(DamageApply damageApply) {
        if (damageApply.getObject_type() == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = assetInfoMapper.get(damageApply.getApply_object_id());
            damageApply.setOriginal_time(assetInfo.getEndAt());
            damageApply.setEaxm_user_id(assetInfo.getOperator());
        }
        if (damageApply.getObject_type() == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo lenderInfo = lenderInfoMapper.get(damageApply.getApply_object_id());
            damageApply.setOriginal_time(lenderInfo.getEndAt());
            damageApply.setEaxm_user_id(lenderInfo.getOperator());
        }
        DamageApply damageApply1 = new DamageApply();
        damageApply1.setStatus(1);
        damageApply1.setApply_object_id(damageApply.getApply_object_id());
        damageApply1.setObject_type(damageApply.getObject_type());
        List<DamageApply> damages = repayMapper.selectByDamageApply(damageApply1);
        if (damages.size() > 0) {//设置申请延期类型(0原始9后续)
            damageApply.setDamage_type(9);
        } else {
            damageApply.setDamage_type(0);
        }
    }


}
