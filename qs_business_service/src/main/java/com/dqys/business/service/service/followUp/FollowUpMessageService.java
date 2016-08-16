package com.dqys.business.service.service.followUp;


import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.orm.query.followUp.FollowUpMessageQuery;

import java.util.List;

/**
 * Created by yan on 16-8-11.
 */
public interface FollowUpMessageService {
    int insert(FollowUpMessage followUpMessage);

    List<FollowUpMessage> list(FollowUpMessageQuery followUpMessageQuery);

}
