package com.dqys.business.service.utils.followUp;

import com.dqys.business.orm.pojo.followUp.FollowUpMessage;
import com.dqys.business.service.dto.followUp.FollowUpMessageDTO;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * Created by yan on 16-8-15.
 */
public class FollowUpUtil {
    public static FollowUpMessage toFollowUpMessage(FollowUpMessageDTO followUpMessageDTO){
        FollowUpMessage followUpMessage= new FollowUpMessage();
        followUpMessage.setObjectId(followUpMessageDTO.getObjectId());
        followUpMessage.setContent(followUpMessageDTO.getContent());
        followUpMessage.setObjectType(followUpMessageDTO.getObjectType());
        followUpMessage.setLiquidateStage(followUpMessageDTO.getLiquidateStage());
        if(null!=followUpMessageDTO.getSecondLiquidateStage()){
            followUpMessage.setSecondLiquidateStage(followUpMessageDTO.getLiquidateStage());
        }
        if(null!=followUpMessageDTO.getSecondObjectId()){
            followUpMessage.setSecondObjectType(followUpMessageDTO.getSecondObjectType());

        }
        return followUpMessage;
    }
}
