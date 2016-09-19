package com.dqys.business.service.service.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;

import java.util.List;

/**
 * Created by yan on 16-8-11.
 */
public interface FollowUpMessageService {
    /**
     * 增加跟进信息并且增加跟进次数
     * @param followUpMessageDTO
     * @return
     */
    int insert(FollowUpMessageDTO followUpMessageDTO);

    /**
     * 查询跟进信息
     * @param followUpMessageQuery
     * @return
     */
    List<FollowUpMessage> list(FollowUpMessageQuery followUpMessageQuery);

    /**
     * 查询跟进信息,级联公司与用户
    * @param followUpMessageQuery
    * @return
            */
    List<FollowUpMessage> getlistWithUserAndTeam(FollowUpMessageQuery followUpMessageQuery);


    /**
     * 查询跟进并取消未读
     * @param followUpMessageQuery
     * @return
     */
    List<FollowUpMessage> listAndCancelUnread(FollowUpMessageQuery followUpMessageQuery);


}
