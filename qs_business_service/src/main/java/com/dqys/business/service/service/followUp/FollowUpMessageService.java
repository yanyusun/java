package com.dqys.business.service.service.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.pojo.followUp.FollowUpSource;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;

import java.io.IOException;
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
    int insert(FollowUpMessageDTO followUpMessageDTO) throws IOException;

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

    /**
     * 查询跟进信息,级联公司,用户,资源
     * @param followUpMessageQuery
     * @return
     */
    List<FollowUpMessage> getlistWithAll(FollowUpMessageQuery followUpMessageQuery);

    /**
     * 批量插入上传的资源
     * @param fileList
     */
    void insertBatchInsertSource(List<FollowUpSource> fileList,Integer followUpId) throws IOException;

}
