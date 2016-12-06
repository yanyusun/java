package com.dqys.business.service.service;

import com.dqys.auth.orm.pojo.TUserTag;
import com.dqys.business.orm.pojo.coordinator.TeammateRe;
import com.dqys.business.orm.pojo.coordinator.UserTeam;
import com.dqys.business.orm.pojo.message.Message;
import com.dqys.business.orm.pojo.message.MessageOperinfo;

import java.util.List;
import java.util.Map;

/**
 * Created by mkfeng on 2016/7/8.
 */
public interface MessageService {
    /**
     * 消息列表
     *
     * @param message
     * @return
     */
    List<Message> selectByMessage(Message message);

    /**
     * 标记为已读（单个或是批量）
     *
     * @param ids
     * @return
     */
    Integer readMessage(Integer[] ids);

    /**
     * 删除消息（伪删除）
     *
     * @param ids
     * @return
     */
    Integer del(Integer[] ids);

    /**
     * @param title        标题
     * @param content      内容
     * @param sender_id    发送者id
     * @param receive_id   接受者id
     * @param label        标签
     * @param type         消息类型(0任务1产品2安全3服务)
     * @param businessType 业务类型(0任务1产品2安全3服务)
     * @param operUrl      操作地址参数
     * @return
     */
    Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type, Integer businessType, String operUrl);

    /**
     * @param title          标题
     * @param content        内容
     * @param sender_id      发送者id
     * @param receive_id     接受者id
     * @param label          标签
     * @param type           消息类型(0任务1产品2安全3服务)
     * @param businessType   业务类型(0任务1产品2安全3服务)
     * @param operUrl        操作地址参数
     * @param flowBusinessId 流转业务状态表id
     * @return
     */
    Integer add(String title, String content, Integer sender_id, Integer receive_id, String label, Integer type, Integer businessType, String operUrl, Integer flowBusinessId);

    /**
     * 查询消息记录数
     *
     * @param message
     * @return
     */
    Integer selectCount(Message message);

    /**
     * 发送短信
     *
     * @param receiveUserId 接受短信人的用户id(当手机传入为NUll时，根据用户id查询对应手机号发送)
     * @param mobilePhone   接收人的手机号
     * @param content       短信内容
     * @return
     */
    Integer sendSMS(Integer receiveUserId, String mobilePhone, String content);

    /**
     * 团队协作
     *
     * @param userTeam 协作器
     * @param map      发送者信息
     * @param uid      接收者
     * @param remark   备注
     */
    void sendSmsByTeammate(UserTeam userTeam, TeammateRe teammateRe, Map<String, Object> map, Integer uid, String remark);

    /**
     * 业务流转请求短信通知(发送给平台)
     *
     * @param objectId   对象id
     * @param objectType 对象类型
     * @param flowId     流转对象id
     * @param flowType   流转对象类型
     * @param operation  流转操作
     * @param userId     操作人id
     * @return
     */
    String businessFlow(Integer objectId, Integer objectType, Integer flowId, Integer flowType, String operation, Integer userId, String operUrl, Integer flowBusinessId);

    //    获取平台管理员账户
    public TUserTag getAdmin();

    /**
     * 流转结果，平台接受或拒绝
     *
     * @param objectId      对象id
     * @param objectType    对象类型
     * @param flowId        业务流转对象id
     * @param flowType      业务流转对象类型
     * @param operation     流转操作
     * @param sendUserId    发送者id（平台管理员）
     * @param receiveUserId 接收者id（请求公司）
     * @param status        状态（0拒绝1接收）
     * @return
     */
    String businessFlowResult(Integer objectId, Integer objectType, Integer flowId, Integer flowType, String operation, Integer sendUserId,
                              Integer receiveUserId, Integer status, List<Integer> inviteUserIds, Integer flowBusinessId);

    /**
     * 被邀请公司同意拒绝
     *
     * @param objectId      对象id
     * @param objectType    对象类型
     * @param flowId        业务流转对象id
     * @param flowType      业务流转对象类型
     * @param sendUserId    发送者id（被邀请公司)
     * @param receiveUserId 接收者id（请求公司）
     * @param status        状态（0拒绝1接收）
     * @return
     */
    String respondInvite(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer sendUserId, Integer receiveUserId,
                         Integer status, Integer flowBusinessId);

    /**
     * 执行司法化解（律所接收到通知）
     *
     * @param objectId   对象id
     * @param objectType 对象类型
     * @param flowId     流转对象id
     * @param flowType   流转对象类型
     * @param userId     操作人
     * @param operation  业务操作
     * @param onStatus   (0加入1移除)
     * @param modify     是否对数据修改（是true否false）
     * @return
     */
    String judicature(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation,
                      Integer onStatus, boolean modify, Integer flowBusinessId);

    /**
     * 市场处置（中介接收短信通知）
     *
     * @param objectId   对象id
     * @param objectType 对象类型
     * @param flowId     流转对象id
     * @param flowType   流转对象类型
     * @param userId     操作人
     * @param operation  业务操作
     * @param onStatus   (0加入1移除)
     * @param modify     是否对数据修改（是true否false）
     * @return
     */
    String intermediary(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation,
                        Integer onStatus, boolean modify, Integer flowBusinessId,Integer operType);

    /**
     * 维持常规催收（催收接收短信通知）
     *
     * @param objectId   对象id
     * @param objectType 对象类型
     * @param flowId     流转对象id
     * @param flowType   流转对象类型
     * @param userId     操作人
     * @param operation  业务操作
     * @param onStatus   (0加入1移除)
     * @param modify     是否对数据修改（是true否false）
     * @return
     */
    String collectiones(Integer objectId, Integer objectType, Integer flowId, Integer flowType, Integer userId, String operation,
                        Integer onStatus, boolean modify, Integer flowBusinessId);

    /**
     * 设置消息的操作状态
     *
     * @param id
     * @param status
     * @return
     */
    Map setOper(Integer id, Integer status);

    Integer getMessageNo();

    Integer insertMessageNoByOperinfo(MessageOperinfo messageOperinfo);

}
