package com.dqys.sale.service.impl;

import com.dqys.auth.orm.dao.facade.SaleUserMapper;
import com.dqys.auth.orm.pojo.saleUser.SaleUser;
import com.dqys.auth.orm.pojo.saleUser.dto.UserDetailDTO;
import com.dqys.core.constant.MessageBTEnum;
import com.dqys.core.constant.SmsEnum;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.FormatValidateTool;
import com.dqys.core.utils.RabbitMQProducerTool;
import com.dqys.core.utils.SmsUtil;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetBusiness;
import com.dqys.flowbusiness.service.constant.saleBusiness.AssetDisposeBusiness;
import com.dqys.sale.orm.mapper.AssetPackageMapper;
import com.dqys.sale.orm.mapper.FixedAssetMapper;
import com.dqys.sale.orm.mapper.NewsMapper;
import com.dqys.sale.orm.mapper.UserBondMapper;
import com.dqys.sale.orm.mapper.business.BusinessORelationMapper;
import com.dqys.sale.orm.mapper.message.MessageMapper;
import com.dqys.sale.orm.pojo.AssetPackage;
import com.dqys.sale.orm.pojo.BusinessORelation;
import com.dqys.sale.orm.pojo.FixedAsset;
import com.dqys.sale.orm.pojo.UserBond;
import com.dqys.sale.orm.pojo.message.Message;
import com.dqys.sale.orm.pojo.message.MessageOperinfo;
import com.dqys.sale.service.constant.MessageEnum;
import com.dqys.sale.service.constant.ObjectTypeEnum;
import com.dqys.sale.service.facade.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/8.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SaleUserMapper saleUserMapper;
    @Autowired
    private BusinessORelationMapper businessORelationMapper;
    @Autowired
    private AssetPackageMapper assetPackageMapper;
    @Autowired
    private FixedAssetMapper fixedAssetMapper;
    @Autowired
    private UserBondMapper userBondMapper;
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<Message> selectByMessage(Message message) {
        List<Message> messageList = messageMapper.selectByMessage(message);
        return messageList;
    }

    @Override
    public Integer readMessage(Integer[] ids) {
        if (ids.length > 0) {
            return messageMapper.readMessage(ids);
        } else {
            return 0;
        }
    }

    @Override
    public Integer del(Integer[] ids) {
        if (ids.length > 0) {
            return messageMapper.del(ids);
        } else {
            return 0;
        }
    }

    @Override
    public Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type, Integer businessType, String operUrl) {
        if (title.equals("") || sender_id == null || receive_id == null || type == null) {
            return 0;
        } else {
            Message message = new Message();
            message.setContent(content);
            message.setLabel(label);
            message.setReceiveId(receive_id);
            message.setSenderId(sender_id);
            message.setStatus(0);
            message.setTitle(title);
            message.setType(type);
            message.setBusinessType(businessType);
            message.setOperUrl(operUrl);
            return messageMapper.add(message);
        }

    }

    @Override
    public Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type,
                       Integer businessType, String operUrl, Integer flowBusinessId) {
        if (title.equals("") || sender_id == null || receive_id == null || type == null) {
            return 0;
        } else {
            Message message = new Message();
            message.setContent(content);
            message.setLabel(label);
            message.setReceiveId(receive_id);
            message.setSenderId(sender_id);
            message.setStatus(0);
            message.setTitle(title);
            message.setType(type);
            message.setBusinessType(businessType);
            message.setOperUrl(operUrl);
            message.setFlowBusinessId(flowBusinessId);
            return messageMapper.add(message);
        }
    }

    @Override
    public Integer selectCount(Message message) {
        return messageMapper.selectCount(message);
    }

    @Override
    public Integer sendSMS(Integer receiveUserId, String mobilePhone, String content) {
        if (mobilePhone == null) {
            UserDetailDTO tUserInfo = saleUserMapper.getUserDetail(receiveUserId);
            if (tUserInfo != null) {
                mobilePhone = tUserInfo.getMobile();
            }
        }
        if (mobilePhone != null && FormatValidateTool.checkMobile(mobilePhone.trim())) {
            //发送短信接口
            RabbitMQProducerTool.addToSMSSendQueue(mobilePhone.toString(), content);//加入短信队列
        }
        return null;
    }


    @Override
    public Map setOper(Integer id, Integer status) {
        Map map = new HashMap<>();
        map.put("result", "no");
        Message message = messageMapper.get(id);
        if (message == null) {
            map.put("msg", "查询消息记录有误");
        } else {
            if (message.getMessageNo() != null) {//如果存在编号，就把统一编号的消息操作状态全改了
                message.setId(null);
                MessageOperinfo messageOperinfo = new MessageOperinfo();
                messageOperinfo.setMessageMo(message.getMessageNo());
                messageOperinfo.setUserId(UserSession.getCurrent().getUserId());
                messageOperinfo.setOperStatus(status);
                messageMapper.insertMessageNoByOperinfo(messageOperinfo);
            }
            message.setOperStatus(status);
            messageMapper.updateOperStatus(message);
            map.put("result", "yes");
        }
        return map;
    }

    @Override
    public Integer getMessageNo() {
        return messageMapper.getMessageNo();
    }

    @Override
    public Integer insertMessageNoByOperinfo(MessageOperinfo messageOperinfo) {
        return messageMapper.insertMessageNoByOperinfo(messageOperinfo);
    }

    @Override
    public Integer addMessageAndSendSMS(Integer sendUserId, Integer receiveUserId, Integer businessId, Integer businessType, Integer businessLevel, Integer operType) {
        if (sendUserId == receiveUserId) {
            return -1;
        }
        UserDetailDTO operC = saleUserMapper.getUserDetail(sendUserId);//发送者
        UserDetailDTO userC = saleUserMapper.getUserDetail(receiveUserId);//接收者
        String content = "";
        Integer num = 0;
        BusinessORelation oRelation = businessORelationMapper.getORelationByBusinessId(businessId);
        if (businessType == AssetBusiness.type) {
            //发布业务
            content = releaseSms(operC, userC, oRelation, businessLevel, operType);
            num = add("结果通知", content, sendUserId, receiveUserId, "", MessageEnum.SERVE.getValue(), MessageBTEnum.READ_ONLY.getValue(), "");
        }
        if (businessType == AssetDisposeBusiness.type) {
            //处置业务
            content = disposeSms(operC, userC, oRelation, businessLevel, operType);
            num = add("结果通知", content, sendUserId, receiveUserId, "", MessageEnum.SAFETY.getValue(), MessageBTEnum.READ_ONLY.getValue(), "");
        }
        return num;
    }

    //验证是否为管理员
    private boolean verifyAdmin(Integer sendUserId) {
        boolean flag = false;//操作人是否为平台（默认不是平台）
        SaleUser user = saleUserMapper.getAdmin();
        if (user.getId() == sendUserId) {
            flag = true;
        }
        return flag;
    }


    //资产处置业务操作
    private String disposeSms(UserDetailDTO sendUser, UserDetailDTO receiveUser, BusinessORelation oRelation, Integer businessLevel, Integer operType) {
        SmsUtil smsUtil = new SmsUtil();
        String content = "";
        String admin = "管理员";
        if (businessLevel == AssetDisposeBusiness.getCheckLevel().getLevel() && operType == AssetDisposeBusiness.getCheckLevel().dispose_check_OK) {//平台点击审核通过，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.DISPOSE_RESULT_YES.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetDisposeBusiness.getCheckLevel().getLevel() && operType == AssetDisposeBusiness.getCheckLevel().dispose_reject) {//平台点击驳回，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.DISPOSE_RESULT_NO.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetDisposeBusiness.getCheckLevel().getLevel() && operType == AssetDisposeBusiness.getCheckLevel().dispose_unable) {//用户或平台点击无效，信息发送给平台或用户
            if (verifyAdmin(sendUser.getId())) {
                content = smsUtil.sendSms(SmsEnum.WX_BUSINESS.getValue(), receiveUser.getMobile(),
                        getUserName(receiveUser),
                        admin,
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            } else {
                content = smsUtil.sendSms(SmsEnum.WX_BUSINESS.getValue(), receiveUser.getMobile(),
                        admin,
                        getUserName(sendUser),
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            }
        }
        if (businessLevel == AssetDisposeBusiness.getOnDisposeLevel().getLevel() && operType == AssetDisposeBusiness.getOnDisposeLevel().dispose_cancel) {//用户或平台点击取消处置，信息发送给平台或用户
            if (verifyAdmin(sendUser.getId())) {
                content = smsUtil.sendSms(SmsEnum.CANCEL_DISPOSE.getValue(), receiveUser.getMobile(),
                        getUserName(receiveUser),
                        admin,
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            } else {
                content = smsUtil.sendSms(SmsEnum.CANCEL_DISPOSE.getValue(), receiveUser.getMobile(),
                        admin,
                        getUserName(sendUser),
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            }
        }
        if (businessLevel == AssetDisposeBusiness.getOnDisposeLevel().getLevel() && operType == AssetDisposeBusiness.getOnDisposeLevel().dispose_Ok) {//平台点击已处置，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.HAS_DISPOSE.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetDisposeBusiness.getHasRejectLevel().getLevel() && operType == AssetDisposeBusiness.getHasRejectLevel().dispose_re_announce) {//用户点击重新申请，信息发送给平台
            content = smsUtil.sendSms(SmsEnum.CX_DISPOSE.getValue(), receiveUser.getMobile(),
                    admin,
                    getUserName(sendUser),
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetDisposeBusiness.getHasDisposeLevel().getLevel() && operType == AssetDisposeBusiness.getHasDisposeLevel().dispose_reset) {//平台点击重启，信息发送给用户

        }
        if (businessLevel == AssetDisposeBusiness.getHasDisposeLevel().getLevel() && operType == AssetDisposeBusiness.getHasDisposeLevel().dispose_reset) {//用户点击异议，信息发送给平台
            content = smsUtil.sendSms(SmsEnum.DISSENT_DISPOSE.getValue(), receiveUser.getMobile(),
                    admin,
                    getUserName(sendUser),
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        return content;
    }

    //资产发布业务操作
    private String releaseSms(UserDetailDTO sendUser, UserDetailDTO receiveUser, BusinessORelation oRelation, Integer businessLevel, Integer operType) {
        SmsUtil smsUtil = new SmsUtil();
        String content = "";
        String admin = "管理员";
        if (businessLevel == AssetBusiness.getBeAnnounced().getLevel() && operType == AssetBusiness.getBeAnnounced().AnnounceOperType) {//用户点击发布，信息发送给平台
            content = smsUtil.sendSms(SmsEnum.PUBLISH_BUSINESS.getValue(), receiveUser.getMobile(),
                    admin,
                    getUserName(sendUser),
                    "刚发布了",
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getCheckLevel().getLevel() && operType == AssetBusiness.getCheckLevel().unable) {//用户或平台点击无效，信息发送给平台或用户
            if (verifyAdmin(sendUser.getId())) {
                content = smsUtil.sendSms(SmsEnum.INVALID_BUSINESS.getValue(), receiveUser.getMobile(),
                        getUserName(receiveUser),
                        admin,
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            } else {
                content = smsUtil.sendSms(SmsEnum.INVALID_BUSINESS.getValue(), receiveUser.getMobile(),
                        admin,
                        getUserName(sendUser),
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            }
        }
        if (businessLevel == AssetBusiness.getCheckLevel().getLevel() && operType == AssetBusiness.getCheckLevel().reject) {//平台点击驳回，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.RESULT_BUSINESS.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    "驳回",
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getCheckLevel().getLevel() && operType == AssetBusiness.getCheckLevel().check_OK) {//平台点击审核通过，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.RESULT_BUSINESS.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    "审核通过",
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getReject().getLevel() && operType == AssetBusiness.getReject().re_announce) {//用户点击重新发布，信息发送给平台
            content = smsUtil.sendSms(SmsEnum.PUBLISH_BUSINESS.getValue(), receiveUser.getMobile(),
                    admin,
                    getUserName(sendUser),
                    "重新发布了",
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getReject().getLevel() && operType == AssetBusiness.getReject().check_OK) {//平台点击审核通过，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.RESULT_BUSINESS.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    "审核通过",
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getBeAnnouncedAdmin().getLevel() && operType == AssetBusiness.getBeAnnouncedAdmin().AnnounceOperType) {//平台点击发布，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.FB_BUSINESS.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getBeAnnouncedAdmin().getLevel() && operType == AssetBusiness.getBeAnnouncedAdmin().AnnounceOperType) {//*平台点击无效，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.WX_BUSINESS.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getHasAnnouncedLevel().getLevel() && operType == AssetBusiness.getHasAnnouncedLevel().under_line) {//用户或平台点击下架，信息发送给平台或用户
            if (verifyAdmin(sendUser.getId())) {
                content = smsUtil.sendSms(SmsEnum.XJ_BUSINESS.getValue(), receiveUser.getMobile(),
                        getUserName(receiveUser),
                        admin,
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            } else {
                content = smsUtil.sendSms(SmsEnum.XJ_BUSINESS.getValue(), receiveUser.getMobile(),
                        admin,
                        getUserName(sendUser),
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            }
        }
        if (businessLevel == AssetBusiness.getHasUnderLine().getLevel() && operType == AssetBusiness.getHasUnderLine().re_announce) {//用户点击重新发布，信息发送给平台
            content = smsUtil.sendSms(SmsEnum.PUBLISH_BUSINESS.getValue(), receiveUser.getMobile(),
                    admin,
                    getUserName(sendUser),
                    "重新发布了[已下架]的",
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getHasUnderLine().getLevel() && operType == AssetBusiness.getHasUnderLine().on_line) {//平台点击上架，信息发送给用户
            content = smsUtil.sendSms(SmsEnum.SJ_BUSINESS.getValue(), receiveUser.getMobile(),
                    getUserName(receiveUser),
                    admin,
                    ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                    getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
            );
        }
        if (businessLevel == AssetBusiness.getHasUnderLine().getLevel() && operType == AssetBusiness.getHasUnderLine().unable) {//用户或平台点击无效，信息发送给平台或用户
            if (verifyAdmin(sendUser.getId())) {
                content = smsUtil.sendSms(SmsEnum.WX_BUSINESS.getValue(), receiveUser.getMobile(),
                        getUserName(receiveUser),
                        admin,
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            } else {
                content = smsUtil.sendSms(SmsEnum.WX_BUSINESS.getValue(), receiveUser.getMobile(),
                        admin,
                        getUserName(sendUser),
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            }
        }
        if (businessLevel == AssetBusiness.getUnable().getLevel() && operType == AssetBusiness.getUnable().re_announce) {//用户或平台点击重新发布，信息发送给平台或用户
            if (verifyAdmin(sendUser.getId())) {
                content = smsUtil.sendSms(SmsEnum.CX_BUSINESS.getValue(), receiveUser.getMobile(),
                        getUserName(receiveUser),
                        admin,
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            } else {
                content = smsUtil.sendSms(SmsEnum.PUBLISH_BUSINESS.getValue(), receiveUser.getMobile(),
                        admin,
                        getUserName(sendUser),
                        "重新发布了[已无效]的",
                        ObjectTypeEnum.getNameByValue(oRelation.getObjectType()),
                        getObjectNO(oRelation.getObjectId(), oRelation.getObjectType())
                );
            }
        }
        return content;
    }

    private String getObjectNO(Integer objectId, Integer objectType) {
        String number = "";
        if (objectType == ObjectTypeEnum.fixed_asset.getValue().intValue()) {
            FixedAsset entity = fixedAssetMapper.selectByPrimaryKey(objectId);
            number = entity.getNo();
        } else if (objectType == ObjectTypeEnum.user_bond.getValue().intValue()) {
            UserBond entity = userBondMapper.selectByPrimaryKey(objectId);
            number = entity.getBondNo();
        } else if (objectType == ObjectTypeEnum.overdue_asset.getValue().intValue()) {
            UserBond entity = userBondMapper.selectByPrimaryKey(objectId);
            number = entity.getBondNo();
        } else if (objectType == ObjectTypeEnum.company_bond.getValue().intValue()) {
            UserBond entity = userBondMapper.selectByPrimaryKey(objectId);
            number = entity.getBondNo();
        } else if (objectType == ObjectTypeEnum.judicial_sale.getValue().intValue()) {

        } else if (objectType == ObjectTypeEnum.attention_asset.getValue().intValue()) {

        } else if (objectType == ObjectTypeEnum.asset_package.getValue().intValue()) {
            AssetPackage entity = assetPackageMapper.selectByPrimaryKey(objectId);
            number = entity.getAssetNo();
        } else if (objectType == ObjectTypeEnum.news.getValue().intValue()) {

        }
        return number;
    }

    private String getUserName(UserDetailDTO user) {
        return "[" + user.getName() + "]" + "[" + user.getAccount() + "]";
    }

}
