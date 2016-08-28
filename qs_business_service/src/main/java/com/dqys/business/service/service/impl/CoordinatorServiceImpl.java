package com.dqys.business.service.service.impl;

import com.dqys.auth.orm.dao.facade.TUserInfoMapper;
import com.dqys.auth.orm.pojo.TUserInfo;
import com.dqys.business.orm.constant.business.BusinessStatusEnum;
import com.dqys.business.orm.constant.company.ObjectAcceptTypeEnum;
import com.dqys.business.orm.constant.company.ObjectTypeEnum;
import com.dqys.business.orm.constant.coordinator.CoordinatorEnum;
import com.dqys.business.orm.constant.coordinator.OURelationEnum;
import com.dqys.business.orm.constant.coordinator.TeammateReEnum;
import com.dqys.business.orm.mapper.asset.AssetInfoMapper;
import com.dqys.business.orm.mapper.asset.LenderInfoMapper;
import com.dqys.business.orm.mapper.coordinator.CoordinatorMapper;
import com.dqys.business.orm.mapper.coordinator.OURelationMapper;
import com.dqys.business.orm.mapper.coordinator.TeammateReMapper;
import com.dqys.business.orm.mapper.coordinator.UserTeamMapper;
import com.dqys.business.orm.mapper.repay.RepayMapper;
import com.dqys.business.orm.pojo.asset.AssetInfo;
import com.dqys.business.orm.pojo.asset.LenderInfo;
import com.dqys.business.orm.pojo.coordinator.OURelation;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.coordinator.team.TeamDTO;
import com.dqys.business.service.constant.MessageBTEnum;
import com.dqys.business.service.constant.MessageEnum;
import com.dqys.business.service.constant.ObjectEnum.AssetPackageEnum;
import com.dqys.business.service.constant.ObjectEnum.LenderEnum;
import com.dqys.business.service.exception.bean.BusinessLogException;
import com.dqys.business.service.service.BusinessLogService;
import com.dqys.business.service.service.CoordinatorService;
import com.dqys.business.service.service.MessageService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mkfeng on 2016/7/12.
 */
@Service
public class CoordinatorServiceImpl implements CoordinatorService {

    @Autowired
    private CoordinatorMapper coordinatorMapper;
    @Autowired
    private LenderInfoMapper lenderInfoMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AssetInfoMapper assetInfoMapper;
    @Autowired
    private TeammateReMapper teammateReMapper;
    @Autowired
    private OURelationMapper ouRelationMapper;
    @Autowired
    private BusinessLogService businessLogService;
    @Autowired
    private RepayMapper repayMapper;
    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    @Override
    public void readByLenderOrAsset(Map<String, Object> map, Integer companyId, Integer objectId, Integer objectType, Integer userid) {
        UserTeam userTeam = new UserTeam();
        userTeam.setObjectType(objectType);
        userTeam.setObjectId(objectId);
        userTeam.setCompanyId(companyId);
        UserTeam team = new UserTeam();
        team = userTeamMapper.selectByPrimaryKeySelective(userTeam);
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {//借款人
            LenderInfo lenderInfo = getLenderInfo(map, objectId);
            if (lenderInfo == null) return;
            if (team == null) {
                userTeam.setObjectId(lenderInfo.getAssetId());
                userTeam.setObjectType(ObjectTypeEnum.ASSETPACKAGE.getValue());
                UserTeam teamAsset = userTeamMapper.selectByPrimaryKeySelective(userTeam);
                if (teamAsset != null) {
                    team = teamAsset;
                }
            }
        }
        if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {//资产包
            if (getAsset(map, objectId)) return;
        }

        if (team == null) {//判断是否在t_user_team表中添加了记录，添加了返回信息，没添加的返回id
            //需要判断userId是否拥有创建的权限？
            Map<String, Object> adminUser = coordinatorMapper.getAdminUser(companyId);
            Integer mangerId = MessageUtils.transMapToInt(adminUser, "id");
            userTeam.setMangerId(mangerId == null ? userid : mangerId);
            userTeam.setCtreaterId(userid);
            userTeamMapper.insertSelective(userTeam);//添加公司内成员协作器
            map.put("userTeamId", userTeam.getId());
            map.put("result", "yes_add");
        } else {
            List<TeamDTO> list = getLenderOrAsset(companyId, userTeam.getId(), userTeam.getObjectType());//获取借款人或是资产包的团队信息
            for (TeamDTO t : list) {//查询每个人员的任务数
                Map<String, Object> task = getTaskCount(companyId, t.getUserId(), objectType);
                t.setFinishTask(MessageUtils.transMapToInt(task, "finish"));
                t.setOngoingTask(MessageUtils.transMapToInt(task, "ongoing"));
                t.setTotalTask(MessageUtils.transMapToInt(task, "total"));
                t.setLeaveWordTime(MessageUtils.transMapToString(coordinatorMapper.getLastLeaveWord(t.getUserId()), "time"));//最后留言时间
            }
            map.put("companys", companyList(objectId, objectType));//对象类型相应的公司
            map.put("teams", list);//团队信息
            map.put("people", getPeopleNum(companyId, objectId, objectType));//团队人数
            map.put("userTeamId", team.getId());
            map.put("result", "yes");
        }
    }

    /**
     * 获取资产包信息
     *
     * @param map
     * @param objectId
     * @return
     */
    private boolean getAsset(Map<String, Object> map, Integer objectId) {
        AssetInfo assetInfo = assetInfoMapper.get(objectId);
        if (assetInfo == null) {
            map.put("result", "no_asset");//资产包不存在
            return true;
        } else {
            map.put("name", assetInfo.getName());//资产包名称
            map.put("accrual", assetInfo.getAccrual() == null ? 0 : assetInfo.getAccrual());//总利息
            map.put("loan", assetInfo.getLoan() == null ? 0 : assetInfo.getLoan());//总贷款
            map.put("appraisal", assetInfo.getAppraisal() == null ? 0 : assetInfo.getAppraisal());//抵押物总评估
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(assetInfo.getEndAt());
            long nowTime = new Date().getTime();
            int day = (int) (calendar.getTimeInMillis() - nowTime) / (1000 * 3600 * 24);
            map.put("dayCount", day > 0 ? day : 0);//逾期天数
        }
        return false;
    }

    /**
     * 获取借款人信息
     *
     * @param map
     * @param objectId
     * @return
     */
    private LenderInfo getLenderInfo(Map<String, Object> map, Integer objectId) {
        LenderInfo lenderInfo = lenderInfoMapper.get(objectId);
        if (lenderInfo == null) {
            map.put("result", "no_lender");//借款人不存在
            return null;
        } else {
            map.put("accrual", lenderInfo.getAccrual() == null ? 0 : lenderInfo.getAccrual());//总利息
            map.put("loan", lenderInfo.getLoan() == null ? 0 : lenderInfo.getLoan());//总贷款
            map.put("appraisal", lenderInfo.getAppraisal() == null ? 0 : lenderInfo.getAppraisal());//抵押物总评估
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lenderInfo.getEndAt());
            long nowTime = new Date().getTime();
            int day = (int) (calendar.getTimeInMillis() - nowTime) / (1000 * 3600 * 24);
            map.put("dayCount", day > 0 ? day : 0);//逾期天数
        }
        return lenderInfo;
    }

    @Override
    public Map<String, Object> getCompanyUserList(String realName, Integer userId, Integer companyId) {
        Map map = new HashMap<>();
        List<Map<String, Object>> list = coordinatorMapper.getCompanyUserList(realName, userId, companyId);
        map.put("users", list);
        return map;
    }

    @Override
    public Map addTeammate(Integer userTeamId, Integer userId, String remark, Integer[] userIds) throws BusinessLogException {
        Map map = new HashMap<>();
        int num = 0;
        UserTeam team = new UserTeam();
        team.setId(userTeamId);
        UserTeam userTeam = userTeamMapper.selectByPrimaryKeySelective(team);
        String title = "未知";
        if (userTeam != null) {
            title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.INSIDE.getValue());
        }
        Map userAndCompany = coordinatorMapper.getUserAndCompanyByUserId(userId);
        for (Integer uid : userIds) {
            Integer flag = 0;
            TeammateRe teammateRe = new TeammateRe();
            teammateRe.setUserId(userId);
            teammateRe.setUserTeamId(userTeamId);
            teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue());
            teammateRe.setBusinessType(TeammateReEnum.BUSINESS_TYPE_TASK.getValue());
            flag = getTeammateFlag(teammateRe);//添加参与人
            if (flag == -3) {//人数已满
                break;
            }
            if (flag > 0) {
                num++;
                Integer result = messageService.add(title, remark, userId, uid, CoordinatorEnum.taskMes.getName(), MessageEnum.TASK.getValue(), MessageBTEnum.INSIDE.getValue(), "teammateId=" + teammateRe.getId());//添加消息记录
                if (result > 0) {
                    //发送短信
                    messageService.sendSmsByTeammate(userTeam, userAndCompany, uid, remark);
                }
            }
        }
        if (num > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
//        businessLogService.add(userId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.ADD_COMMON_USER.getValue(), "添加参与人", "", 0, 0);
        return map;
    }

    /**
     * 添加参与人信息
     *
     * @return
     */
    private Integer getTeammateFlag(TeammateRe teammateRe) {
        Integer flag = 0;
        List<TeammateRe> users = new ArrayList<>();
        List<TeammateRe> list = teammateReMapper.selectSelective(teammateRe);//查询用于判断人员类别的定位
        if (list.size() > 0) {
            users = teammateReMapper.selectSelective(teammateRe);//查询用于判断人员加入过这个协作器没有
        }
        if (users.size() > 0) {
            TeammateRe teammateRe1 = users.get(0);
            if (teammateRe1.getStatus() == TeammateReEnum.STATUS_REFUSE.getValue()) {//邀请过的并且以前拒绝过的就修改为待接收状态
                teammateRe1.setStatus(TeammateReEnum.STATUS_INIT.getValue());
                flag = teammateReMapper.updateByPrimaryKeySelective(teammateRe1);
            } else {
                flag = -1;//已经加入过案组
            }
        } else {
            if (list.size() > 5 && teammateRe.getJoinType().equals(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue())) {
                return -2;//主动加入人数已满
            }
            if (list.size() > 23 && teammateRe.getJoinType().equals(TeammateReEnum.JOIN_TYPE_PASSIVITY.getValue())) {
                return -3;//被邀请的人数已满
            }
            if (list.size() == 0) {
                teammateRe.setType(TeammateReEnum.TYPE_ADMIN.getValue());
            } else if (list.size() == 1) {
                teammateRe.setType(TeammateReEnum.TYPE_AUXILIARY.getValue());
            } else {
                teammateRe.setType(TeammateReEnum.TYPE_PARTICIPATION.getValue());
            }
            teammateRe.setStatus(ObjectAcceptTypeEnum.init.getValue());
            flag = teammateReMapper.insertSelective(teammateRe);//添加参与人
        }
        return flag;
    }

    /**
     * 设定业务id
     *
     * @param ouRelation
     * @return
     */
    private boolean checkExist(OURelation ouRelation) {
        OURelation our = new OURelation();
        our.setObjectId(ouRelation.getObjectId());
        our.setObjectType(ouRelation.getObjectType());
        our.setUserId(ouRelation.getUserId());
        List<OURelation> list = ouRelationMapper.selectBySelective(our);
        if (list.size() == 0) {
            Map map = coordinatorMapper.selectByBusinessId(ouRelation.getObjectType(), ouRelation.getObjectId());
            ouRelation.setBusinessId(MessageUtils.transMapToInt(map, "business_id"));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map isAccept(Integer teammateId, Integer status, Integer userId) throws BusinessLogException {
//        businessLogService.add(teammateId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.UPDATE_COMMON_USER.getValue(), "被邀请人同意或拒绝", "", 0, 0);
        Map<String, Object> map = new HashMap<>();
        TeammateRe teammateRe = teammateReMapper.selectByPrimaryKey(teammateId);
        if (teammateRe == null) {
            map.put("result", "no_exist");
            return map;
        }
        if (teammateRe.getUserId() != userId) {
            UserTeam userTeam = new UserTeam();
            userTeam.setId(teammateRe.getUserTeamId());
            UserTeam userT = userTeamMapper.selectByPrimaryKeySelective(userTeam);
            if (userT == null) {
                map.put("result", "no_exist");//不存在
                return map;
            }
//            if (userT.getMangerId() != userId) {
//                map.put("result", "no_authority");//没有权限
//                return map;
//            }
        }
        teammateRe.setStatus(status);
        Integer result = teammateReMapper.updateByPrimaryKeySelective(teammateRe);
        if (status == 1 && result > 0) {
            Map userTeammate = coordinatorMapper.selectByUserTeamAndMateRe(teammateId);//查询团队中的协作器信息
            OURelation ouRelation = new OURelation();
            ouRelation.setStatus(OURelationEnum.STATUS_ACCEPT.getValue());
            ouRelation.setType(OURelationEnum.TYPE_ALLOCATION_TEAM.getValue());
            ouRelation.setObjectType(MessageUtils.transMapToInt(userTeammate, "object_type"));
            ouRelation.setObjectId(MessageUtils.transMapToInt(userTeammate, "object_id"));
            ouRelation.setUserId(MessageUtils.transMapToInt(userTeammate, "user_id"));
            ouRelation.setEmployerId(MessageUtils.transMapToInt(userTeammate, "user_team_id"));
            if (checkExist(ouRelation)) {
                ouRelationMapper.insertSelective(ouRelation);
            }
            //如果是资产包，还需要加入资产包下的借款人
            if (ouRelation.getObjectType() == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
                LenderInfo lenderInfo = new LenderInfo();
                lenderInfo.setAssetId(ouRelation.getObjectId());
                List<LenderInfo> lends = coordinatorMapper.selectByLender(lenderInfo);
                for (LenderInfo len : lends) {
                    ouRelation.setObjectId(len.getId());
                    ouRelation.setObjectType(ObjectTypeEnum.LENDER.getValue());
                    if (checkExist(ouRelation)) {
                        ouRelationMapper.insertSelective(ouRelation);
                    }
                }
            }
            //发送消息
            map.put("result", "yes");
        } else if (status == 2 && result > 0) {
            map.put("result", "yes");
        } else {
            map.put("result", "no");
        }
        return map;
    }

    @Override
    public Map addTeammate(Integer userTeammateId, Integer userId) throws BusinessLogException {
        Map map = new HashMap<>();
        Integer flag = 0;
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setUserId(userId);
        teammateRe.setUserTeamId(userTeammateId);
        teammateRe.setJoinType(TeammateReEnum.JOIN_TYPE_INITIATIVE.getValue());
        teammateRe.setBusinessType(TeammateReEnum.BUSINESS_TYPE_TASK.getValue());
        flag = getTeammateFlag(teammateRe);
        if (flag > 0) {
            UserTeam userTeam = new UserTeam();
            userTeam.setId(userTeammateId);
            UserTeam userT = userTeamMapper.selectByPrimaryKeySelective(userTeam);//团队信息
            if (userT != null) {
                TUserInfo tUserInfo = tUserInfoMapper.selectByPrimaryKey(userT.getMangerId());//接受者
                TUserInfo sendUser = tUserInfoMapper.selectByPrimaryKey(userId);//请求者
                String content = "";//消息内容
                if (tUserInfo != null && sendUser != null) {
                    SmsUtil smsUtil = new SmsUtil();
                    content = smsUtil.sendSms(SmsEnum.INITIATIVE_JOIN.getValue(), tUserInfo.getMobile(), tUserInfo.getRealName(), sendUser.getRealName(), userT.getObjectType().toString(), ObjectTypeEnum.getObjectTypeEnum(userT.getObjectType()).getName());//发送短信
                    String title = getMessageTitle(userT.getObjectId(), userT.getObjectType(), MessageBTEnum.INITIATIVE.getValue());
                    messageService.add(title, content, userId, userT.getMangerId(), "", MessageEnum.SERVE.getValue(), MessageBTEnum.INITIATIVE.getValue(), "teammateId=" + teammateRe.getId());
                }
            }
            map.put("result", "yes");
        } else if (flag == -1) {
            map.put("result", "exist");//已经纯在
        } else if (flag == -2) {
            map.put("result", "limitation");//人数已满
        } else {
            map.put("result", "no");
        }
//        businessLogService.add(userId, ObjectTypeEnum.USER_INFO.getValue(), UserInfoEnum.ADD_COMMON_USER.getValue(), "主动加入案组", "", 0, 0);
        return map;
    }

    @Override
    public void auditBusiness(Map map, Integer userId, Integer objectId, Integer objectType, Integer status) throws BusinessLogException {
        Integer operType = 0;
        String text = "";
        Integer receive_id = 0;//录入人
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo len = coordinatorMapper.getLenderInfo(objectId);
            if (len != null) {
                receive_id = len.getOperator() == null ? 0 : len.getOperator();
            }
            operType = LenderEnum.UPDATE_EDIT.getValue();
            text = "借款人审核操作";
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = coordinatorMapper.getAssetInfo(objectId);
            if (assetInfo != null) {
                receive_id = assetInfo.getOperator() == null ? 0 : assetInfo.getOperator();
            }
            operType = AssetPackageEnum.update.getValue();
            text = "资产包审核操作";
        }
        List<Integer> ids = new ArrayList<>();
        ids.add(objectId);
        List<Integer> buinessIds = repayMapper.getBusinessId(objectType, ids);
//        businessLogService.add(objectId, objectType, operType, text, "", 0, 0);//添加操作日志
        if (buinessIds.size() > 0) {
            Integer result = repayMapper.updateBusinessStatus(buinessIds.get(0), status);
            if (result > 0) {
                SmsUtil smsUtil = new SmsUtil();//发送短信通知
                Integer code = 0;
                if (status == BusinessStatusEnum.platform_pass.getValue()) {
                    code = SmsEnum.BUSINESS_AUDIT_YES.getValue();
                } else {
                    code = SmsEnum.BUSINESS_AUDIT_NO.getValue();
                }
                Map userC = coordinatorMapper.getUserAndCompanyByUserId(receive_id);
                String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), objectType + "", ObjectTypeEnum.getObjectTypeEnum(objectType).getName());
                String title = getMessageTitle(objectId, objectType, MessageBTEnum.BUSINESS.getValue());
                messageService.add(title, content, userId, receive_id, "", MessageEnum.SERVE.getValue(), MessageBTEnum.BUSINESS.getValue(), "");//添加通知消息
                map.put("result", "yes");
                return;
            }
        }
        map.put("result", "no");
    }

    @Override
    public void isPause(Map map, Integer objectId, Integer objectType, Integer status, Integer userId) throws BusinessLogException {
        Integer operType = 0;//操作类型
        List<Map<String, Object>> receive_ids = new ArrayList<>();
        LenderInfo lenderInfo = new LenderInfo();
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            lenderInfo.setId(objectId);
            lenderInfo.setIsStop(status);
            LenderInfo lender = lenderInfoMapper.get(objectId);
            if (lender == null) {
                map.put("result", "noExist");//不存在记录
                return;
            }
            if (lender.getIsStop() == status) {
                map.put("result", "repetition");//重复操作
                return;
            }
            coordinatorMapper.updateLender(lenderInfo);//借款人暂停后开启
            operType = LenderEnum.UPDATE_EDIT.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToLender(objectType, objectId);
        } else if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = new AssetInfo();
            assetInfo.setId(objectId);
            assetInfo.setIsStop(status);
            AssetInfo asset = assetInfoMapper.get(objectId);
            if (asset == null) {
                map.put("result", "noExist");//不存在记录
                return;
            }
            if (asset.getIsStop() == status) {
                map.put("result", "repetition");//重复操作
                return;
            }
            if (assetInfoMapper.update(assetInfo) > 0) {//资产包暂停后，下面的借款人全部暂停了
                lenderInfo.setIsStop(status);
                lenderInfo.setAssetId(objectId);
                coordinatorMapper.updateLender(lenderInfo);
            }
            ;
            operType = AssetPackageEnum.update.getValue();
            receive_ids = coordinatorMapper.getUserIdByObjUserRelToAsset(objectType, objectId);//被通知人员
        }
        Integer code = 0;
        if (status == 1) {
            code = SmsEnum.BUSINESS_PAUSE.getValue();//开启暂停
        } else {
            code = SmsEnum.BUSINESS_OPEN.getValue();//关闭暂停
        }
        SmsUtil smsUtil = new SmsUtil();//发送短信通知
        for (Map receive_id : receive_ids) {
            Integer rec = MessageUtils.transMapToInt(receive_id, "user_id");
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(rec);
            Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
            String content = smsUtil.sendSms(code, MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"), MessageUtils.transMapToString(oper, "companyName"),
                    MessageUtils.transMapToString(oper, "companyType"), MessageUtils.transMapToString(oper, "realName"), objectType + "", ObjectTypeEnum.getObjectTypeEnum(objectType).getName());
            String title = getMessageTitle(objectId, objectType, MessageBTEnum.BUSINESS_PAUSE.getValue());
            messageService.add(title, content, userId, rec, "", MessageEnum.SERVE.getValue(), MessageBTEnum.BUSINESS_PAUSE.getValue(), "");//添加通知消息
        }
        map.put("result", "yes");
//        businessLogService.add(objectId, objectType, operType, text, "", 0, 0);//添加操作日志
    }

    /**
     * 获取借款人或是资产包的团队信息
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return
     */
    @Override
    public List<TeamDTO> getLenderOrAsset(Integer companyId, Integer objectId, Integer objectType) {
        return coordinatorMapper.getLenderOrAsset(companyId, objectId, objectType);
    }

    /**
     * 获取任务数
     *
     * @param companyId
     * @param userId
     * @param objectType
     * @return(finish,total,ongoing)
     */
    @Override
    public Map<String, Object> getTaskCount(Integer companyId, Integer userId, Integer objectType) {
        Map<String, Object> map = coordinatorMapper.getTaskRatio(companyId, userId, objectType);//业绩比例
        Map<String, Object> map2 = coordinatorMapper.getTaskOngoing(companyId, userId, objectType);//获取当前进行的任务数
        map.put("ongoing", map2.get("ongoing"));
        return map;
    }

    @Override
    public Map<String, Object> getObjectProperty(Integer objectId, Integer objectType) {
        Map<String, Object> map = new HashMap<>();
        String objectNo = "";//对象编号
        if (objectType == ObjectTypeEnum.ASSETPACKAGE.getValue()) {
            AssetInfo assetInfo = assetInfoMapper.get(objectId);
            objectNo = assetInfo.getAssetNo();
        }
        if (objectType == ObjectTypeEnum.LENDER.getValue()) {
            LenderInfo lenderInfo = lenderInfoMapper.get(objectId);
            objectNo = lenderInfo.getLenderNo();
        }
        map.put("objectNo", objectNo);
        return map;
    }

    @Override
    public String getMessageTitle(Integer objectId, Integer objectType, Integer businessType) {
        String title = ObjectTypeEnum.getObjectTypeEnum(objectType).getName();//标题：对象类型+对象名称+业务类型
        title += MessageUtils.transMapToString(getObjectProperty(objectId, objectType), "objectNo");//对象名称编号
        title += MessageBTEnum.get(businessType);
        return title;
    }

    @Override
    public Map deleteTeammatUser(Integer userId, Integer teamUserId, Integer userTeamId, Integer status, Integer substitutionUid) throws Exception {
        Map map = new HashMap<>();
        Map code = new HashMap<>();
        UserTeam ut = new UserTeam();
        ut.setId(userTeamId);
        UserTeam userTeam = userTeamMapper.selectByPrimaryKeySelective(ut);
        if (userTeam == null) {
            map.put("result", "no");
            map.put("msg", "协作器不存在");
            return map;
        }
        if (status == null && substitutionUid == null) {//删除协作器联系人
            code = teamDel(userId, teamUserId, userTeamId, substitutionUid);
        } else if (status != null && substitutionUid != null) {//替换协作器联系人
            if (userId.equals(substitutionUid)) {
                code = teamDel(userId, teamUserId, userTeamId, substitutionUid);//删除联系人
                if ("200".equals(code.get("code")) && status == 1) {
                    teamDel(userId, substitutionUid, userTeamId, null);//如果替换人在该团队已经存在，那么先删除之后
                    TeammateRe teammateRe = (TeammateRe) code.get("teammateRe");
                    teammateRe.setId(null);
                    teammateRe.setUserId(substitutionUid);
                    teammateReMapper.insert(teammateRe);
                    Map res = isAccept(teammateRe.getId(), status, substitutionUid);
                    if (MessageUtils.transMapToString(res, "result").equals("yes")) {
                        SmsUtil smsUtil = new SmsUtil();//发送短信通知
                        Map userC = coordinatorMapper.getUserAndCompanyByUserId(teamUserId);
                        Map oper = coordinatorMapper.getUserAndCompanyByUserId(substitutionUid);
                        String content = smsUtil.sendSms(SmsEnum.REPLACE.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                                MessageUtils.transMapToString(oper, "realName"), userTeam.getObjectType() + "", ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName(), TeammateReEnum.get(teammateRe.getType()));
                        String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.REPLACE.getValue());
                        messageService.add(title, content, userId, substitutionUid, "", MessageEnum.TASK.getValue(), MessageBTEnum.REPLACE.getValue(), "");//添加通知消息
                        map.put("result", "yes");
                    } else {
                        throw new Exception();
                    }
                }
            } else {
                map.put("result", "no");
            }
        } else if (status == null && substitutionUid != null) {//发送短信通知替补的人
            SmsUtil smsUtil = new SmsUtil();//发送短信通知
            Map userC = coordinatorMapper.getUserAndCompanyByUserId(substitutionUid);
            Map oper = coordinatorMapper.getUserAndCompanyByUserId(userId);
            String content = smsUtil.sendSms(SmsEnum.REPLACE_CONTACTS.getValue(), MessageUtils.transMapToString(userC, "mobile"), MessageUtils.transMapToString(userC, "realName"),
                    MessageUtils.transMapToString(oper, "realName"), userTeam.getObjectType() + "", ObjectTypeEnum.getObjectTypeEnum(userTeam.getObjectType()).getName());
            String title = getMessageTitle(userTeam.getObjectId(), userTeam.getObjectType(), MessageBTEnum.REPLACE_CONTACTS.getValue());
            String url = "?teamUserId=" + teamUserId + "&userTeamId=" + userTeamId + "&substitutionUid=" + substitutionUid;
            messageService.add(title, content, userId, substitutionUid, "", MessageEnum.TASK.getValue(), MessageBTEnum.REPLACE_CONTACTS.getValue(), url);//添加通知消息
        } else {
            map.put("result", "no_par");
            map.put("msg", "参数错误");
        }
        if (code != null) {
            if ("200".equals(code.get("code"))) {
                map.put("result", "yes");
            } else if ("505".equals(code.get("code"))) {
                throw new Exception();
            } else if ("501".equals(code.get("code"))) {
                map.put("result", "no");
                map.put("msg", "删除失败");
            } else if ("500".equals(code.get("code"))) {
                map.put("result", "no_tiBu");
                map.put("msg", "需要替补人");
            }
        }
        return map;
    }

    private Map teamDel(Integer userId, Integer teamUserId, Integer userTeamId, Integer substitutionUid) {
        Map map = new HashMap<>();
        TeammateRe teammateRe = new TeammateRe();
        teammateRe.setUserId(teamUserId);
        teammateRe.setUserTeamId(userTeamId);
        List<TeammateRe> list = teammateReMapper.selectSelective(teammateRe);//团队中用户
        List<TeammateRe> list1 = teammateReMapper.selectSelective(teammateRe);//操作用户
        if (list.size() > 0 && list1.size() > 0) {
            TeammateRe team = list.get(0);
            TeammateRe oper = list1.get(0);
            if (oper.getType() > team.getType()) {
                map.put("code", "502");//下级不能删除上级
                return map;
            }
            map.put("teammateRe", team);//参与处置的对象
            if (substitutionUid == null && (team.getType().equals(TeammateReEnum.TYPE_ADMIN.getValue()) || team.getType().equals(TeammateReEnum.TYPE_AUXILIARY.getValue()))) {//管理者0|所属人1|参与者2
                map.put("code", "500");//需要有人替补
                return map;
            }
            team.setStatus(TeammateReEnum.STATUS_DELETE.getValue());
            Integer result = teammateReMapper.updateByPrimaryKey(team);
            OURelation ouRelation = new OURelation();
            ouRelation.setUserId(teamUserId);
            ouRelation.setEmployerId(userTeamId);
            Integer result1 = ouRelationMapper.deleteByPrimaryKey(ouRelation);
            if (result > 0 && result1 > 0) {
                map.put("code", "200");//成功
            } else {
                map.put("code", "505");//数据操作有误
            }
        } else {
            map.put("code", "501");//失败
        }
        return map;
    }

    /**
     * 获取团队人数
     *
     * @param companyId
     * @param objectId
     * @param objectType
     * @return（type,peopleNum）
     */
    private List<Map<String, Object>> getPeopleNum(Integer companyId, Integer objectId, Integer objectType) {
        return coordinatorMapper.getPeopleNum(companyId, objectId, objectType);
    }

    /**
     * 获取相应的公司
     *
     * @param objectId
     * @param objectType
     * @return
     */
    private List<Map<String, Object>> companyList(Integer objectId, Integer objectType) {
        return coordinatorMapper.companyList(objectId, objectType);
    }

}
